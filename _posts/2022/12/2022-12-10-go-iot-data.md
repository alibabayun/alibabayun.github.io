---
layout: post
title: "go解析传感器16进制6合一数据"
keywords: ["go"]
description: "go解析传感器16进制6合一数据"
category: "go"
tags: ["go"]
---
## 背景
在对接硬件设备时，一般会使用16进制接口来边硬件边缘进行数据交互，记录一下

## 示例 六合一数据 文档
```
03地址位、03功能码、00 00寄存器起始地址、00 06寄存器个数、CRC校验位       oc数据长度
03 03 00 00 00 06 CRC
03 03 0C     00 84     00 00      00 00    00 00   00 00    00 00 CRC       
数据运算   温度*0.1   湿度*0.1    EC*1      氮*1     磷*1      钾*1
00 84=132 × 0.1 =13.2℃
HEX DEC 

05PH
05地址位、03功能码、00 00寄存器起始地址、01数据长度、8542校验位
05 03 00 00 00 01 85 42
05地址位、03功能码、02数据长度、02 EA传感器数据、 C9 6B校验位
05 03 02 02 EA C9 6B
数据运算02 EA=746×0.01=7.46       PH

甲烷
04 03 00 00 00 0B 84 28
040316 00 00  0000 0000 0000 0000  0000  0000  0000 000A（甲烷位置）0000000000000000000000000
040316 ff ff  ffff 0000 0000 0000 0000 0000 ffff ffff 000000009ebf
数据运算00 0A=10*1=10

06氨气
060300010001D47D
060302XXXX0000
*0.01

07氧气
070300010001D5AC
070302XXXX0000
*0.1

设备编码
8662500680621850

Sli
                                30                       50                                                        96
8662500680621850 AAAA 03 030C 0087 0000 0000 0000 0000 0000 A2C7 AAAA 04 0316 FFFF FFFF 0000 0000 0000 0000 0000 FFFF FFFF 0000 0000 9EBF AAAA 05030202F308A1AAAA06030202940D4BAAAA07030200C6B016

8662500680621850 AAAA 03 030C 009B 00000000000000000000C957AAAA 040316 FFFFFFFF00000000000000000000FFFFFFFF000000009EBFAAAA05030202DC497DAAAA06030202880C82AAAA07030200C771D6
```

## go解析示例
``` go
package main

import (
	"common"
	"logger"
)

// 解析第三方数据
// 8662500680621850AAAA03030C00D900000000000000000000972EAAAA040316FFFFFFFF00000000000000000000FFFFFFFF000000009EBFAAAA05030202D448BBAAAA06030202940D4BAAAA07030200C771D6
// 第三方设备id（前16位）: 8662500680621850 取15位
// 土壤6合1数据: AAAA03030C00D900000000000000000000972E 取16-54 长度38
// 甲烷数据: AAAA040316FFFFFFFF00000000000000000000FFFFFFFF000000009EBF 取54-112 长度58
// PH数据: AAAA05030202E708AE 取112-130 长度18
// 氨气数据: AAAA06030202940D4B 取130-148 长度18
// 氧气数据: AAAA07030200C831D2 取148-166 长度18
func ParseThirdData(data string) (string, map[string]interface{}) {
	if len(data) < 166 {
		return "", nil
	}
	value := make(map[string]interface{})
	thirdId := data[:15]
	soilStr := data[16:54]      //AAAA03030C00D900000000000000000000972E
	methaneStr := data[54:112]  //AAAA040316FFFFFFFF00000000000000000000FFFFFFFF000000009EBF
	phStr := data[112:130]      //AAAA05030202D448BB
	ammoniaStr := data[130:148] //AAAA06030202940D4B
	oxygenStr := data[148:166]  //AAAA07030200C771D6
	logger.Logger.Debugf("ParseThirdData thirdId = %s", thirdId)
	logger.Logger.Debugf("ParseThirdData soilStr = %s", soilStr)
	logger.Logger.Debugf("ParseThirdData methaneStr = %s", methaneStr)
	logger.Logger.Debugf("ParseThirdData phStr = %s", phStr)
	logger.Logger.Debugf("ParseThirdData ammoniaStr = %s", ammoniaStr)
	logger.Logger.Debugf("ParseThirdData oxygenStr = %s", oxygenStr)

	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[10:14])
	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[14:18])
	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[18:22])
	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[22:26])
	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[26:30])
	logger.Logger.Debugf("ParseThirdData soilStr1 = %s", soilStr[30:34])

	//土壤6合1
	temperature := common.StringToFloat(soilStr[10:14], 0.1)
	humidity := common.StringToFloat(soilStr[14:18], 0.1)
	ec := common.StringToFloat(soilStr[18:22], 1.0)
	nitrogen := common.StringToFloat(soilStr[22:26], 1.0)
	phosphorus := common.StringToFloat(soilStr[26:30], 1.0)
	potassium := common.StringToFloat(soilStr[30:34], 1.0)
	value[DeviceTypeSoilTemperature] = temperature
	value[DeviceTypeSoilHumidity] = humidity
	value[DeviceTypeSoilEC] = ec
	value[DeviceTypeSoilNitrogen] = nitrogen
	value[DeviceTypeSoilPhosphorus] = phosphorus
	value[DeviceTypeSoilPotassium] = potassium
	//甲烷
	methane := common.StringToFloat(methaneStr[34:38], 1.0)
	value[DeviceTypeAirMethane] = methane
	//PH
	ph := common.StringToFloat(phStr[10:14], 0.01)
	value[DeviceTypeSoilPH] = ph
	//氨气
	ammonia := common.StringToFloat(ammoniaStr[10:14], 0.01)
	value[DeviceTypeAirAmmonia] = ammonia
	//氧气
	oxygen := common.StringToFloat(oxygenStr[10:14], 0.1)
	value[DeviceTypeAirOxygen] = oxygen

	return thirdId, value
}

type Data struct {
	Unit  string `json:"unit"`  //单位
	Code  string `json:"code"`  //设备类型
	Name  string `json:"name"`  //显示名称
	Value string `json:"value"` //数值
}

func MapDataToValue(m map[string]interface{}) []Data {
	var values []Data
	for deviceType, data := range m {
		values = append(values, Data{
			Unit:  DeviceTypeUnitMap[deviceType],
			Code:  deviceType,
			Name:  DeviceTypeStrMap[deviceType],
			Value: data.(string),
		})
	}
	return values
}
```

### `common/util.go` 代码为
``` go
package common

import (
	"crypto/sha1"
	"fmt"
	"logger"
	"io"
	"math/rand"
	"regexp"
	"sort"
	"strconv"
	"strings"
	"time"
)

func StringToInt(id string) int {
	if id != "" {
		id, err := strconv.Atoi(id)
		if err == nil {
			return id
		}
	}
	return -1
}

func RandomString(n int) string {
	var letters = []rune("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
	rand.Seed(time.Now().Unix())
	b := make([]rune, n)
	for i := range b {
		b[i] = letters[rand.Intn(len(letters))]
	}
	return string(b)
}

func RandomString2(n int) string {
	var letters = []rune("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")
	rand.Seed(time.Now().Unix())
	b := make([]rune, n)
	for i := range b {
		b[i] = letters[rand.Intn(len(letters))]
	}
	return string(b)
}

func StringToIntArray(str string) []int64 {
	tmp := strings.Split(str, ",")
	var result []int64
	for _, t := range tmp {
		i, _ := strconv.ParseInt(t, 10, 64)
		result = append(result, i)
	}
	return result
}

// string转float保留2位小数点
func StringToFloat(str string, ratio float64) string {
	i, _ := strconv.ParseInt(str, 16, 64)
	return fmt.Sprintf("%.2f", float64(i)*ratio)
}

func IntArrayToSortString(arr []int) string {
	result := ""
	sort.Ints(arr)
	for _, v := range arr {
		result += strconv.Itoa(v) + ","
	}
	if len(result) > 0 {
		result = result[:len(result)-1]
	}
	return result
}

func StringArrayToSortString(arr []string) string {
	result := ""
	sort.Strings(arr)
	for _, v := range arr {
		result += v + ","
	}
	if len(result) > 0 {
		result = result[:len(result)-1]
	}
	return result
}

func MapToString(m map[string]interface{}) string {
	result := ""
	for deviceType, value := range m {
		if value != nil {
			v, ok := value.(string)
			if ok {
				result += deviceType + ":" + v + ","
			} else {
				logger.Logger.Debugf("MapToString is no ok, deviceType = %s", deviceType)
				logger.Logger.Debugf("MapToString is no ok, value = %v", value)
			}

		}
	}
	if len(result) > 0 {
		result = result[:len(result)-1]
	}
	return result
}

// CheckMobile 检验手机号
func CheckMobile(phone string) bool {
	if len(phone) != 11 {
		return false
	}
	// 匹配规则
	// ^1第一位为一
	// [345789]{1} 后接一位345789 的数字
	// \\d \d的转义 表示数字 {9} 接9位
	// $ 结束符
	regRuler := "^1[345789]{1}\\d{9}$"

	// 正则调用规则
	reg := regexp.MustCompile(regRuler)

	// 返回 MatchString 是否匹配
	return reg.MatchString(phone)
}

//密码加密
func Encrypt(password string) string {
	t := sha1.New()
	_, _ = io.WriteString(t, password)
	return fmt.Sprintf("%x", t.Sum(nil))
}

```

### `common/logger.go` 为
```go
package logger

import (
	"bytes"
	"github.com/gin-gonic/gin"
	"github.com/sirupsen/logrus"
	"io/ioutil"
	"net/http"
	"time"
)

// LoggerToFile 日志记录到文件
func LogToFile() gin.HandlerFunc {
	return func(ctx *gin.Context) {
		// 初始化bodyLogWriter
		blw := &bodyLogWriter{
			body:           bytes.NewBufferString(""),
			ResponseWriter: ctx.Writer,
		}
		ctx.Writer = blw

		// 开始时间
		startTime := time.Now()
		// 请求方式
		reqMethod := ctx.Request.Method
		// 请求路由
		reqUri := ctx.Request.RequestURI
		//请求参数
		request := getRequestBody(ctx)
		// 请求IP
		clientIP := ctx.ClientIP()

		// 处理请求
		ctx.Next()

		// 结束时间
		endTime := time.Now()
		// 执行时间
		latencyTime := endTime.Sub(startTime)
		// 状态码
		statusCode := ctx.Writer.Status()
		// 响应
		response := blw.body.String()

		//日志格式
		Logger.WithFields(logrus.Fields{
			"status_code":  statusCode,
			"latency_time": latencyTime,
			"client_ip":    clientIP,
			"req_method":   reqMethod,
			"request":      request,
			"response":     response,
			"req_uri":      reqUri,
		}).Info()
	}
}

func getRequestBody(ctx *gin.Context) interface{} {
	switch ctx.Request.Method {
	case http.MethodGet:
		fallthrough
	case http.MethodDelete:
		return ctx.Request.URL.Query()
	case http.MethodPost:
		fallthrough
	case http.MethodPut:
		fallthrough
	case http.MethodPatch:
		var bodyBytes []byte
		bodyBytes, err := ioutil.ReadAll(ctx.Request.Body)
		if err != nil {
			return nil
		}
		ctx.Request.Body = ioutil.NopCloser(bytes.NewBuffer(bodyBytes))
		return string(bodyBytes)
	}
	return nil
}

// bodyLogWriter 定义一个存储响应内容的结构体
type bodyLogWriter struct {
	gin.ResponseWriter
	body *bytes.Buffer
}

// Write 读取响应数据
func (w bodyLogWriter) Write(b []byte) (int, error) {
	w.body.Write(b)
	return w.ResponseWriter.Write(b)
}

```