---
layout: post
title: "go cgo c++交叉编辑"
keywords: ["vm"]
description: "go cgo c++交叉编辑"
category: "go"
tags: ["go"]
---

## 前言
交叉编译其实是相对于本地编译(native build)来说的，我相信大家最开始学习 C/C++ 这些语言的时候，都是在电脑上写程序，然后在电脑上编译生成可执行文件，最后在电脑上运行。程序的编辑——》编译——》运行，整个过程都是在一台 X86 电脑上。

当我们开始接触嵌入式开发后，事情变的不一样了，你在电脑上写程序，在电脑上编译出可执行文件，最后这个可执行文件需要下载到你的开发板上运行。程序最后运行的环境变了，比如你的开发板是基于 Arm 的——程序在 X86 上编辑，编译，最终运行在另一个和 X86 完全不同的架构的 Arm 芯片上。

## 参考资料
1. [https://cloud.tencent.com/developer/article/1571937](https://cloud.tencent.com/developer/article/1571937)

2. [Golang : multiple definition of CGO ported package](https://www.coder.work/article/7496938)


## 主要配置
1. `~/.bash_profile`
```
PATH=$PATH:$HOME/bin
export STAGING_DIR=/usr/staging_dir/toolchain-arm_cortex-a7+neon_gcc-4.8-linaro_uClibc-0.9.33.2_eabi/bin:$STAGING_DIR
PATH=$PATH:$STAGING_DIR
export PATH
```

2. `Makefile`文件
```
#打包程序名称
APP_NAME="its"

BINARY="../../build/edge/${APP_NAME}"
VERSION=1.0.0
export GOARCH=arm
export GOOS=linux
export GOARM=7
export CGO_ENABLED=1
export CC=arm-openwrt-linux-gcc -I/usr/staging_dir/target-arm_cortex-a7+neon_uClibc-0.9.33.2_eabi/usr/include -I/root/tmppath/edge/its/include -L/root/tmppath/edge/its/lib -limos_mw_sdk -lmw_sdk_bp -lmw_sdk_ipc -lmw_sdk_player -lmw_sdk_rm -lm -L/usr/staging_dir/target-arm_cortex-a7+neon_uClibc-0.9.33.2_eabi/usr/lib/libiconv-stub/lib -liconv
export CXX=arm-openwrt-linux-uclibcgnueabi-c++

clear:
	@$(RM) ${BINARY}
build:
	@go build -o ${BINARY} ./
```


2. goc文件
```
package main

/*
//linux
//#cgo CFLAGS:-I./include
//#cgo LDFLAGS:-L./dll -limos_mw_sdk -lmw_sdk_bp -lmw_sdk_ipc -lmw_sdk_player -lmw_sdk_rm -lcurl -lm -Wl,-rpath=./dll

//arm linux
#cgo CFLAGS:-I./include
#cgo LDFLAGS:-L./lib -limos_mw_sdk -lmw_sdk_bp -lmw_sdk_ipc -lmw_sdk_player -lmw_sdk_rm -lm -Wl,--allow-multiple-definition -L/usr/staging_dir/target-arm_cortex-a7+neon_uClibc-0.9.33.2_eabi/usr/lib/libiconv-stub/lib -liconv

#include "imos_sdk_def.h"
#include "imos_sdk_pub.h"
#include "imos_sdk_func.h"
#include <stdlib.h>
#include <string.h>

void STDCALL* CgoImosMwStatusReportCallback(IN CHAR *pcUserID,IN ULONG ulReportType,IN VOID *pParam);
void STDCALL* CgoImosMwTmsMultiuserPicUpload(IN IMOS_MW_MULTI_UNIVIEW_PROTOCOL_HEADER_S *pstUniviewData,IN  ULONG ulStreamHandle);
*/
import "C"
import (
	"errors"
	"fmt"
	"math/rand"
	"time"
	"unsafe"

	"github.com/sirupsen/logrus"

	"lc/common/protocol"
	"lc/common/util"
)

//export CgoImosMwStatusReportCallback
func CgoImosMwStatusReportCallback(pcUserID *C.char, ulReportType C.ulong, pParam unsafe.Pointer) unsafe.Pointer {
	userid := C.GoString(pcUserID)
	switch ulReportType {
	case C.IMOS_MW_STATUS_KEEPALIVE: //用户保活失败，无需调用退出接口
		logrus.Warnf("用户保活失败,userid:%v,data:%v", userid, *(*C.ulong)(pParam))
		GetITSDeviceMgr().NotifyLogout(userid)
	case C.IMOS_MW_STATUS_UPDATE: //升级状态
		//logrus.Infof("升级状态,userid:%s,data:%d", userid, *(*C.ulong)(pParam))
	case C.IMOS_MW_STATUS_PLAYER_MEDIA_PROCESS: //播放状态，不处理
	}
	return nil
}

//export CgoImosMwTmsMultiuserPicUpload
func CgoImosMwTmsMultiuserPicUpload(pstUniviewData *C.IMOS_MW_MULTI_UNIVIEW_PROTOCOL_HEADER_S, ulStreamHandle C.ULONG) unsafe.Pointer {
	if pstUniviewData == nil {
		return nil
	}
	PassTime := C.GoStringN(&pstUniviewData.szPassTime[0], MinLength(C.int(C.strlen(&pstUniviewData.szPassTime[0])), C.IMOS_MW_UNIVIEW_MAX_TIME_LEN))
	if len(PassTime) >= 14 {
		PassTime = PassTime[0:14]
	}
	t, err := util.MlParseTimeX("20060102150405", PassTime)
	if err != nil {
		return nil
	}
	v := tagVehicleInfo{
		VehiclePlate:     C.GoStringN(&pstUniviewData.szCarPlate[0], MinLength(C.int(C.strlen(&pstUniviewData.szCarPlate[0])), C.IMOS_MW_CAR_PLATE_MAX_LEN)),
		VehicleType:      int8(pstUniviewData.lVehicleType),
		VehicleColor:     byte(pstUniviewData.cVehicleColor),
		VehicleSpeed:     int(pstUniviewData.lVehicleSpeed),
		PassTime:         t,
		PlaceName:        C.GoStringN(&pstUniviewData.szPlaceName[0], MinLength(C.int(C.strlen(&pstUniviewData.szPlaceName[0])), C.IMOS_MW_PLACE_NAME_MAX_LEN)),
		VehicleDirection: int8(pstUniviewData.lDirection),
		LaneID:           int8(pstUniviewData.lLaneID),
		LaneType:         int8(pstUniviewData.lLaneType),
		CamID:            C.GoStringN(&pstUniviewData.szCamID[0], MinLength(C.int(C.strlen(&pstUniviewData.szCamID[0])), C.IMOS_MW_DEV_ID_MAX_LEN)),
		TollgateID:       C.GoStringN(&pstUniviewData.szTollgateID[0], MinLength(C.int(C.strlen(&pstUniviewData.szTollgateID[0])), C.IMOS_MW_DEV_ID_MAX_LEN)),
		IdentifyStatus:   int8(pstUniviewData.lIdentifyStatus),
		StreamHandle:     int(ulStreamHandle),
	}
	logrus.Debugln(v)
	GetITSDeviceMgr().PushData(&v)
	return nil
}

var ErrNotLogin = errors.New("未登陆")

func ResultToError(res int) error {
	return errors.New(fmt.Sprintf("错误码:%d", res))
}
func MinLength(A, B C.int) C.int {
	if A > B {
		return B
	}
	return A
}

type ITSDevice struct {
	CamID      string                  //配置文件，相机编号
	TollgateID string                  //卡口编号:产生该信息的卡口代码
	User       string                  //配置文件，登陆用户名
	Pass       string                  //配置文件，登陆用户密码
	IP         string                  //配置文件，摄像机IP
	Port       uint16                  //配置文件，摄像机端口
	UserID     string                  //login后返回
	Handle     uint64                  //抓拍
	Static     *protocol.VehicleStatic //数据统计
	Reconn     int                     //
}

func NewITSDevice(tollgateid, ID, User, Pass, IP string, Port uint16) *ITSDevice {
	obj := ITSDevice{
		CamID:      ID,
		TollgateID: tollgateid,
		User:       User,
		Pass:       Pass,
		IP:         IP,
		Port:       Port,
		UserID:     "",
		Handle:     0,
		Static:     protocol.NewVehicleStatic(),
	}
	return &obj
}

func (o *ITSDevice) Login() error {
	cUserID := (*C.char)(C.malloc(C.IMOS_MW_RES_CODE_LEN))
	user := C.CString(o.User)
	pass := C.CString(o.Pass)
	cip := C.CString(o.IP)
	defer C.free(unsafe.Pointer(cUserID))
	defer C.free(unsafe.Pointer(user))
	defer C.free(unsafe.Pointer(pass))
	defer C.free(unsafe.Pointer(cip))
	res := C.IMOS_MW_Login(user, pass, cip, C.ushort(o.Port), cUserID)
	if res == C.ERR_COMMON_SUCCEED {
		o.UserID = C.GoString((*C.char)(unsafe.Pointer(cUserID)))
		return nil
	}
	return ResultToError(int(res))
}

func (o *ITSDevice) Logout() error {
	if o.UserID == "" {
		return ErrNotLogin
	}
	o.StopMediaStream()
	usrid := C.CString(o.UserID)
	defer C.free(unsafe.Pointer(usrid))
	res := C.IMOS_MW_Logout(usrid)
	if res == C.ERR_COMMON_SUCCEED {
		//退出置空userid
		o.UserID = ""
		return nil
	}
	return ResultToError(int(res))
}

func (o *ITSDevice) GetDeviceStatus() (*tagMwBasicDeviceInfo, error) {
	if o.UserID == "" {
		return nil, ErrNotLogin
	}
	usrid := C.CString(o.UserID)
	defer C.free(unsafe.Pointer(usrid))
	var stBasicInfo C.IMOS_MW_BASIC_DEVICE_INFO_S
	res := C.IMOS_MW_GetDeviceStatus(usrid, 0, C.IMOS_MW_STATUS_BASIC_INFO, (unsafe.Pointer)(&stBasicInfo))
	if res == C.ERR_COMMON_SUCCEED {
		var bdi tagMwBasicDeviceInfo
		bdi.Manufacturer = C.GoStringN(&stBasicInfo.szManufacturer[0], MinLength(C.int(C.strlen(&stBasicInfo.szManufacturer[0])), C.IMOS_MW_VERSION_LEN))          //厂商
		bdi.DeviceModel = C.GoStringN(&stBasicInfo.szDeviceModel[0], MinLength(C.int(C.strlen(&stBasicInfo.szDeviceModel[0])), C.IMOS_MW_VERSION_LEN))             //设备类型
		bdi.SerialNumber = C.GoStringN(&stBasicInfo.szSerialNumber[0], MinLength(C.int(C.strlen(&stBasicInfo.szSerialNumber[0])), C.IMOS_MW_VERSION_LEN))          //设备序列号
		bdi.DeviceMAC = C.GoStringN(&stBasicInfo.szDeviceMAC[0], MinLength(C.int(C.strlen(&stBasicInfo.szDeviceMAC[0])), C.IMOS_MW_VERSION_LEN))                   //设备MAC地址
		bdi.FirmwareVersion = C.GoStringN(&stBasicInfo.szFirmwareVersion[0], MinLength(C.int(C.strlen(&stBasicInfo.szFirmwareVersion[0])), C.IMOS_MW_VERSION_LEN)) //软件版本, program版本
		bdi.HardwareID = C.GoStringN(&stBasicInfo.szHardwareID[0], MinLength(C.int(C.strlen(&stBasicInfo.szHardwareID[0])), C.IMOS_MW_VERSION_LEN))                //硬件标识
		bdi.PCBVersion = C.GoStringN(&stBasicInfo.szPCBVersion[0], MinLength(C.int(C.strlen(&stBasicInfo.szPCBVersion[0])), C.IMOS_MW_VERSION_LEN))                //PCB版本
		bdi.UbootVersion = C.GoStringN(&stBasicInfo.szUbootVersion[0], MinLength(C.int(C.strlen(&stBasicInfo.szUbootVersion[0])), C.IMOS_MW_VERSION_LEN))          //UBOOT引导版本
		return &bdi, nil
	}
	return nil, ResultToError(int(res))
}

func (o *ITSDevice) Reboot() error {
	if o.UserID == "" {
		return ErrNotLogin
	}
	o.StopMediaStream()
	usrid := C.CString(o.UserID)
	defer C.free(unsafe.Pointer(usrid))
	res := C.IMOS_MW_Reboot(usrid)
	if res == C.ERR_COMMON_SUCCEED {
		return nil
	}
	return ResultToError(int(res))
}

func (o *ITSDevice) SetPicStreamDataCallback() error {
	if o.UserID == "" {
		return ErrNotLogin
	}
	usrid := C.CString(o.UserID)
	ip := C.CString("")
	defer C.free(unsafe.Pointer(usrid))
	defer C.free(unsafe.Pointer(ip))
	var ulCaptureStreamHandle C.ulong
	res := C.IMOS_MW_SetPicStreamDataCallback(usrid, 0, ip, C.IMOS_MW_TMS_MULTIUSER_PIC_UPLOAD_PF(C.CgoImosMwTmsMultiuserPicUpload), &ulCaptureStreamHandle)
	if res == C.ERR_COMMON_SUCCEED {
		o.Handle = uint64(ulCaptureStreamHandle)
		return nil
	}
	return ResultToError(int(res))
}

func (o *ITSDevice) StopMediaStream() error {
	if o.UserID == "" {
		return ErrNotLogin
	}
	usrid := C.CString(o.UserID)
	defer C.free(unsafe.Pointer(usrid))
	res := C.IMOS_MW_StopMediaStream(usrid, C.ulong(o.Handle))
	if res == C.ERR_COMMON_SUCCEED {
		o.Handle = 0
		return nil
	}
	return ResultToError(int(res))
}

func (o *ITSDevice) ToStatic(data *tagVehicleInfo) {
	o.Static.AddDirection(data.VehicleDirection)
	VehiclePlate := []rune(data.VehiclePlate)
	if len(VehiclePlate) > 2 {
		Province := string(VehiclePlate[0:1])
		ProvinceCity := string(VehiclePlate[0:2])
		o.Static.AddProvince(Province)
		o.Static.AddProvinceCity(ProvinceCity)
	} else {
		o.Static.AddProvince(itsConfig.DefProvince)
		o.Static.AddProvinceCity(itsConfig.DefCity)
	}
	pvs := &protocol.VehicleSpeed{
		Plate:     data.VehiclePlate,
		Time:      protocol.BJTime(data.PassTime),
		Type:      data.VehicleType,
		Speed:     data.VehicleSpeed,
		Direction: uint(data.VehicleDirection),
	}
	o.Static.AddVehicleSpeed(pvs)
	o.Static.AddVehicleType(data.VehicleType)
}

func (o *ITSDevice) SendStatic(t time.Time) {
	if o.Static == nil {
		return
	}
	logrus.Debugf("时间段:%s卡口%s检测到的车辆数%d", t.Format("2006-01-02 15:04:05"), o.CamID, len(o.Static.SliceVehicleSpeed))
	o.Static.StaticTime = t
	o.Static.TollgateID = o.TollgateID
	var obj protocol.Pack_VehicleStatic
	if str, err := obj.EnCode(o.CamID, appConfig.GID, GetNextUint64(), o.Static); err == nil {
		topic := GetTopic(protocol.DT_ITS, o.CamID, protocol.TP_ITS_VEHICLESTATIC)
		if buf, err := util.GzipEncode([]byte(str)); err == nil {
			GetMQTTMgr().Publish(topic, string(buf), 0, ToCloud)
		}
	}
	o.Static.Reset()
}

// GetSDKVersion 全局函数
func GetSDKVersion() (string, error) {
	cDataver := (*C.uchar)(C.malloc(C.size_t(C.IMOS_MW_SDK_CLIENT_VERSION_LEN + 1)))
	defer C.free(unsafe.Pointer(cDataver))
	res := C.IMOS_MW_GetSDKVersion(cDataver)
	if res == C.ERR_COMMON_SUCCEED {
		return C.GoString((*C.char)(unsafe.Pointer(cDataver))), nil
	}
	return "", ResultToError(int(res))
}

func SetLog(dir string) error {
	cdir := C.CString(dir)
	defer C.free(unsafe.Pointer(cdir))
	res := C.IMOS_MW_SetLog(C.IMOS_SDK_LOG_DEBUG, cdir, 10*1024)
	if res == C.ERR_COMMON_SUCCEED {
		return nil
	}
	return ResultToError(int(res))
}

func init() {
	res := C.IMOS_MW_Initiate()
	if res != C.ERR_COMMON_SUCCEED {
		msg := fmt.Sprintf("调用IMOS_MW_Initiate发生错误:%v", res)
		panic(msg)
	}
	SetLog(util.GetPath(3))
	C.IMOS_MW_SetStatusCallback(C.IMOS_MW_STATUS_REPORT_CALLBACK_PF(C.CgoImosMwStatusReportCallback))
}

func uninit() {
	C.IMOS_MW_Cleanup()
}

func Test() {
	for {
		var x C.IMOS_MW_MULTI_UNIVIEW_PROTOCOL_HEADER_S

		plate := C.CString("湘A88888")
		C.memcpy(unsafe.Pointer(&x.szCarPlate[0]), unsafe.Pointer(plate), C.size_t(C.strlen(plate)))
		C.free(unsafe.Pointer(plate))

		x.lVehicleType = 1
		x.cVehicleColor = C.char('A')
		x.lVehicleSpeed = C.long(RandInt(1, 120))

		//经过时间
		t := C.CString(util.MlNow().Format("20060102150405"))
		C.memcpy(unsafe.Pointer(&x.szPassTime[0]), unsafe.Pointer(t), C.size_t(C.strlen(t)))
		C.free(unsafe.Pointer(t))

		//位置
		l := C.CString(time.Now().Format("某路口"))
		C.memcpy(unsafe.Pointer(&x.szPlaceName[0]), unsafe.Pointer(l), C.size_t(C.strlen(l)))
		C.free(unsafe.Pointer(l))

		x.lDirection = 1
		x.lLaneID = 1
		x.lLaneType = 0

		s := C.CString("SXT000000001")
		C.memcpy(unsafe.Pointer(&x.szCamID[0]), unsafe.Pointer(s), C.size_t(C.strlen(s)))
		C.free(unsafe.Pointer(s))

		k := C.CString("KK0000000001")
		C.memcpy(unsafe.Pointer(&x.szTollgateID[0]), unsafe.Pointer(k), C.size_t(C.strlen(k)))
		C.free(unsafe.Pointer(k))

		x.lIdentifyStatus = 0

		CgoImosMwTmsMultiuserPicUpload(&x, 11)

		time.Sleep(time.Duration(RandInt(400, 10000)) * time.Millisecond)

		pcUserID := C.CString("1122222333YYY3")
		dat := C.ulong(2222)

		CgoImosMwStatusReportCallback(pcUserID, C.ulong(1), unsafe.Pointer(&dat))
		C.free(unsafe.Pointer(pcUserID))

	}
}

// RandInt 随机整数
func RandInt(min, max int) int {
	if min >= max || min == 0 || max == 0 {
		return max
	}
	return rand.Intn(max-min) + min
}

/*
char -->  C.char -->  byte
signed char -->  C.schar -->  int8
unsigned char -->  C.uchar -->  uint8
short int -->  C.short -->  int16
short unsigned int -->  C.ushort -->  uint16
int -->  C.int -->  int
unsigned int -->  C.uint -->  uint32
long int -->  C.long -->  int32 or int64
long unsigned int -->  C.ulong -->  uint32 or uint64
long long int -->  C.longlong -->  int64
long long unsigned int -->  C.ulonglong -->  uint64
float -->  C.float -->  float32
double -->  C.double -->  float64
wchar_t -->  C.wchar_t  -->
void * -> unsafe.Pointer
*/


```
