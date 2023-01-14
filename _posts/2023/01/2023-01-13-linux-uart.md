---
layout: post
title: "BusyBox 命令和工具的软件"
keywords: ["linux"]
description: "BusyBox 命令和工具的软件"
category: "linux"
tags: ["linux"]
---
## 前言
BusyBox 是一个集成了三百多个最常用Linux命令和工具的软件。BusyBox 包含了一些简单的工具，例如ls、cat和echo等等，还包含了一些更大、更复杂的工具，例grep、find、mount以及telnet。有些人将 BusyBox 称为 Linux 工具里的瑞士军刀。

## 下载地址
[https://busybox.net/downloads/binaries/1.31.0-defconfig-multiarch-musl/](https://busybox.net/downloads/binaries/1.31.0-defconfig-multiarch-musl/)

## BusyBox命令 替换 原始命令
1. 查看连接的串口`ls -al /dev/tty*`或`dmesg | grep ttyS*` 新命令`microcom -s 9600 /dev/ttymxc4`
2. 发送modebus命令` echo -e -n "\x01\x03\x00\x07\x00\x02\x75\xCA"  > /dev/ttyS1`
3. 监听串口是否有回复消息 `tail -f /dev/tty` 或 `cat /dev/ttyS1`  (cat会占用串口)


## 命令说明 
- acpid [监听ACPI事件并处理](https://blog.csdn.net/weixin_28712661/article/details/116674875)

- add-shell [添加shell](https://blog.csdn.net/lile777/article/details/79484681)

- addgroup [创建用户和组](https://blog.csdn.net/weixin_30701169/article/details/116553794)

- adduser [创建用户和组](https://blog.csdn.net/weixin_30701169/article/details/116553794)

- adjtimex [修正时间偏差值](http://www.itjcw123.cn/3682.html)

- arch [使用arch命令可以显示计算机主机的体系结构。如：armv7l](https://deepinout.com/linux-cmd/linux-hardware-management-related-cmd/linux-cmd-arch.html)

- arp [显示和修改IP到MAC转换表](https://ipcmen.com/arp)

- arping [arping命令将每秒发送一次ARP（或ICMP）请求](https://blog.csdn.net/allway2/article/details/106962122/)

- ash [ash 类型bash](https://blog.csdn.net/iriczhao/article/details/127500315)

- awk [执行如下命令](https://blog.csdn.net/davion_zhang/article/details/50594668)

- base64 [使用base64 文件 进行加密和解密](https://www.jianshu.com/p/ce273012073e)

- basename [用于打印目录或者文件的基本名称](https://itpcb.com/linux/c/basename.html)

- bc [计算器](https://www.runoob.com/linux/linux-comm-bc.html)

- beep [命令行控制电脑发出滴滴声——使用beep把警告变为music](https://blog.51cto.com/ganmu/1970789)

- blkdiscard [回收并擦除(discard)整个SSD块设备](https://zhuanlan.zhihu.com/p/370433233)

- blkid [命令主要用来对系统的块设备（包括交换分区）所使用的文件系统类型](https://getiot.tech/linux-command/blkid.htm)

- blockdev [调用“ioxtls”函数，以实现对设备的控制](https://ywnz.com/linux/blockdev/)

- bootchartd [分析系统的启动速度](https://blog.csdn.net/wangeen/article/details/49935057/)

- brctl [管理以太网网桥](https://www.linuxcool.com/brctl)

- bunzip2 [命令是.bz2文件的解压缩程序](https://www.runoob.com/linux/linux-comm-bunzip2.html)

- bzcat [示解压缩后的文件内容](https://ipcmen.com/bzcat)

- bzip2 [压缩文件（.bz2格式](http://c.biancheng.net/view/786.html)

- cal [日历](https://zhuanlan.zhihu.com/p/361413082)

- cat [查看文件](https://www.runoob.com/linux/linux-comm-cat.html)

- chat [自动对话脚本与调制解调器](https://blog.csdn.net/qq_20677327/article/details/107950966)

- chattr [用于改变文件属性](https://www.runoob.com/linux/linux-comm-chattr.html)

- chgrp [变更文件与目录所属群组](https://blog.csdn.net/qq_45988641/article/details/117223530)

- chmod [控制用户对文件的权限的命令](https://www.runoob.com/linux/linux-comm-chmod.html)

- chown [文件所有者和文件关联组的命令](https://www.runoob.com/linux/linux-comm-chown.html)

- chpasswd [是批量更新用户口令的工具](https://ipcmen.com/chpasswd)

- chpst [命令用来指定使用哪个用户执行命令](https://cloud.tencent.com/developer/article/1195794)

- chroot [命令用来在指定的根目录下运行指令](https://www.cnblogs.com/charlieroro/p/9259675.html)

- chrt [chrt是用来操纵进程的实时属性。用户使用chrt命令可以很简单地更改调度策略](https://blog.csdn.net/u011641865/article/details/73826531)

- chvt [修改虚拟终端的前台环境](https://blog.csdn.net/clozxy/article/details/5489609)

- cksum [cksum命令是检查文件的CRC是否正确](https://blog.csdn.net/yexiangCSDN/article/details/80942006)

- clear [清除窗口](https://www.linuxcool.com/clear)

- cmp [用于比较两个文件是否有差异](https://www.runoob.com/linux/linux-comm-cmp.html)

- comm [比较两个已排过序的文件](https://www.runoob.com/linux/linux-comm-comm.html)

- conspy [实时查看和控制远程Linux虚拟控制台](https://cn.linux-console.net/?p=2373)

- cp  [复制文件或目录](https://baike.baidu.com/item/CP/3906614)

- cpio [是用来建立，还原备份档的工具程](https://www.runoob.com/linux/linux-comm-cpio.html)

- crond [设备读取指令，并将其存放于“crontab”文件中](https://blog.csdn.net/youngyoungla/article/details/51674701)

- crontab [定期执行程序的命令](https://www.runoob.com/linux/linux-comm-crontab.html)

- cryptpw [对密码加密的软件](https://bbs.kanxue.com/thread-45408.htm)

- cttyhack [无法退出进程](https://www.jianshu.com/p/cb0d7ddb037d)

- cut [显示行中的指定部分](https://www.cnblogs.com/Spiro-K/p/6361646.html)

- date [用来显示或设定系统的日期与时间](https://www.runoob.com/linux/linux-comm-date.html)

- dc [计算器](https://www.linuxcool.com/dc#:~:text=dc%E5%91%BD%E4%BB%A4%E7%9A%84%E4%BD%9C%E7%94%A8%E6%98%AF,%E4%BB%A5%E5%AE%9A%E4%B9%89%E5%92%8C%E8%B0%83%E7%94%A8%E5%AE%8F%E3%80%82)

- dd [用于读取、转换并输出数据。](https://www.runoob.com/linux/linux-comm-dd.html)

- deallocvt [释放所有未使用的虚拟终端的核心内存和数据结构.](https://www.cnblogs.com/fanweisheng/p/11080744.html)

- depmod [用于分析可载入模块的相依性](https://www.runoob.com/linux/linux-comm-depmod.html)

- devmem [命令直接操作寄存器](https://blog.csdn.net/weixin_44498318/article/details/120027931)

- df [列出文件系统的整体磁盘空间使用情况](https://blog.csdn.net/gnail_oug/article/details/70217446)

- dhcprelay [接口上开启DHCP代理的功能](http://www.h3c.com/cn/d_202112/1510075_30005_0.htm)

- diff [逐行比较两个文本文件](https://math.ecnu.edu.cn/~jypan/Teaching/Linux/command/diff.htm)

- dirname [从文件路径中获取文件目录](https://blog.csdn.net/qfxietian/article/details/122675169)

- dmesg [检查和控制内核的环形缓冲区](https://blog.csdn.net/K346K346/article/details/127533022)

- dnsd [小型静态DNS服务器守护程序。](https://boxmatrix.info/wiki/Property:dnsd)

- dnsdomainname [显示系统的DNS域名。](https://boxmatrix.info/wiki/Property:dnsdomainname)

- dos2unix [将文件从DOS转换为Unix格式。](https://boxmatrix.info/wiki/Property:dos2unix)

- dpkg [安装、删除和管理Debian软件包。](https://boxmatrix.info/wiki/Property:dpkg)

- dpkg-deb [对Debian包（.deb）执行操作。](https://boxmatrix.info/wiki/Property:dpkg-deb)

- dumpkmap [将二进制键盘转换表打印为标准输出。](https://boxmatrix.info/wiki/Property:dumpkmap)

- dumpleases [显示udhcpd授予的DHCP租约](https://boxmatrix.info/wiki/Property:dumpleases_(bbcmd))

- ed [面向行的文本编辑器。](https://www.runoob.com/linux/linux-comm-ed.html)

- egrep [可以任意搜索文件中的字符串和符号](https://baike.baidu.com/item/egrep/469137)

- eject [弹出usb](https://www.runoob.com/linux/linux-comm-eject.html)

- env [显示系统中已存在的环境变量](https://www.linuxcool.com/env)

- envdir [从目录中的文件设置环境变量并运行程序。](https://www.cnblogs.com/rongfengliang/p/12572576.html)

- envuidgid [将uid和gid设置为用户的uid和gid并运行程序。](https://boxmatrix.info/wiki/Property:envuidgid)

- ether-wake [网络唤醒功能](https://jingyan.baidu.com/article/e73e26c06e2eb624adb6a71f.html)

- expand [将制表符转换为空格，写入标准输出](https://boxmatrix.info/wiki/Property:expand)

- expr [将表达式的值打印为标准输出。不运算](https://boxmatrix.info/wiki/Property:expr)

- factor [分解因数](https://www.linuxcool.com/factor)

- fakeidentd [提供假身份验证（auth）服务。](https://boxmatrix.info/wiki/Property:fakeidentd)

- fallocate[将块预分配给文件](https://www.onitroad.com/jc/linux/linux/command/fallocate.html)

- fatattr [更改FAT文件系统上的文件属性。](https://boxmatrix.info/wiki/Property:fatattr)

- fbset [设置景框缓冲区 调整画面之分辨率](https://www.runoob.com/linux/linux-comm-fbset.html)

- fbsplash [启动屏幕的用户空间实现](https://wiki.archlinux.org/title/Fbsplash)

- fdflush [强制软盘驱动器检测磁盘更改。](https://boxmatrix.info/wiki/Property:fdflush)

- fdformat [格式化软盘。](https://boxmatrix.info/wiki/Property:fdformat)

- fdisk [管理磁盘分区](https://zhuanlan.zhihu.com/p/56273534)

- fgconsole [显示活动的虚拟终端数量](https://www.linuxcool.com/fgconsole

- fgrep [搜索文字字符串,fgrep命令 是用来搜索 file 参数指定的输入文件（缺省为标准输入）中的匹配模式的行](https://cloud.tencent.com/developer/article/1869305)

- find [搜索命令](http://c.biancheng.net/view/779.html)

- findfs [通过卷标名查找对应的文件系统](https://man.5ibc.net/findfs)

- flock [任务互斥](https://juejin.cn/post/6844903829947809800)

- fold [限制文件列宽](https://www.runoob.com/linux/linux-comm-fold.html)

- free [系统内存的使用情况](https://www.cnblogs.com/ultranms/p/9254160.html)

- freeramdisk [释放指定ramdisk使用的所有内存。](https://boxmatrix.info/wiki/Property:freeramdisk)

- fsck [检查文件系统并尝试修复出现的错误](http://c.biancheng.net/view/887.html)

- fsfreeze [拍摄快照前，使用 fsfreeze 命令暂停对文件系统的访问](https://blog.51cto.com/u_15127570/2711288)

- fstrim [回收文件系统中未使用的块资源](https://www.linuxcool.com/fstrim)

- fsync [对由文件描述符filedes指定的单一文件起作用](https://blog.csdn.net/hmxz2nn/article/details/82868980)

- ftpd [自带ftp服务器](http://ljg.farbox.com/post/2015-03-19-zai-busyboxshang-qi-dong-ftpfu-wu)

- ftpget []()

- ftpput []()

- fuser []()

- getopt []()

- getty []()

- grep []()

- groups []()

- gunzip []()

- gzip []()

- halt []()

- hd []()

- hdparm []()

- head []()

- hexdump []()

- hexedit []()

- hostid []()

- hostname []()

- httpd []()

- hush []()

- hwclock []()

- i2cdetect []()

- i2cdump []()

- i2cget []()

- i2cset []()

- i2ctransfer []()

- id []()

- ifconfig []()

- ifdown []()

- ifenslave []()

- ifplugd []()

- ifup []()

- inetd []()

- init []()

- insmod []()

- install []()

- ionice []()

- iostat []()

- ip []()

- ipaddr []()

- ipcalc []()

- ipcrm []()

- ipcs []()

- iplink []()

- ipneigh []()

- iproute []()

- iprule []()

- iptunnel []()

- kbd_mode []()

- kill []()

- killall []()

- killall5 []()

- klogd []()

- last []()

- less []()

- link []()

- linux32 []()

- linux64 []()

- linuxrc []()

- ln []()

- loadfont []()

- loadkmap []()

- logger []()

- login []()

- logname []()

- logread []()

- losetup []()

- lpd []()

- lpq []()

- lpr []()

- ls []()

- lsattr []()

- lsmod []()

- lsof []()

- lspci []()

- lsscsi []()

- lsusb []()

- lzcat []()

- lzma []()

- lzop []()

- makedevs []()

- makemime []()

- man []()

- md5sum []()

- mdev []()

- mesg []()

- microcom []()

- mkdir []()

- mkdosfs []()

- mke2fs []()

- mkfifo []()

- mkfs []()
.ext2
- mkfs []()
.minix
- mkfs []()
.vfat
- mknod []()

- mkpasswd []()

- mkswap []()

- mktemp []()

- modinfo []()

- modprobe []()

- more []()

- mount []()

- mountpoint []()

- mpstat []()

- mt []()

- nameif []()

- nanddump []()

- nandwrite []()

- nbd []()
-client
- nc []()

- netstat []()

- nice []()

- nl []()

- nmeter []()

- nohup []()

- nologin []()

- nproc []()

- nsenter []()

- nslookup []()

- ntpd []()

- nuke []()

- od []()

- openvt []()

- partprobe []()

- passwd []()

- paste []()

- patch []()

- pgrep []()

- pidof []()

- ping []()

- ping6 []()

- pipe_progress []()

- pivot_root []()

- pkill []()

- pmap []()

- popmaildir []()

- poweroff []()

- powertop []()

- printenv []()

- printf []()

- pscan []()

- pstree []()

- pwdx []()

- raidautorun []()

- rdate []()

- rdev []()

- readahead []()

- readlink []()

- readprofile []()

- realpath []()

- reboot []()

- reformime []()

- remove []()
-shell
- renice []()

- reset []()

- resize []()

- resume []()

- rev []()

- rmdir []()

- rmmod []()

- route []()

- rpm []()

- rpm2cpio []()

- rtcwake []()

- run []()
-init
- run []()
-parts
- runlevel []()

- runsv []()

- runsvdir []()

- rx []()

- script []()

- scriptreplay []()

- sed []()

- sendmail []()

- seq []()

- setarch []()

- setconsole []()

- setfattr []()

- setfont []()

- setkeycodes []()

- setlogcons []()

- setpriv []()

- setserial []()

- setsid []()

- setuidgid []()

- sh []()

- sha1sum []()

- sha256sum []()

- sha3sum []()

- sha512sum []()

- showkey []()

- shred []()

- shuf []()

- slattach []()

- smemcap []()

- softlimit []()

- sort []()

- split []()

- ssl_client []()

- start []()
-stop-daemon
- stat []()

- strings []()

- stty []()

- su []()

- sulogin []()

- sum []()

- sv []()

- svc []()

- svlogd []()

- svok []()

- swapoff []()

- swapon []()

- switch_root []()

- sync []()

- sysctl []()

- syslogd []()

- tac []()

- tail []()

- tar []()

- taskset []()

- tc []()

- tcpsvd []()

- tee []()

- telnet []()

- telnetd []()

- test []()

- tftp []()

- tftpd []()

- time []()

- timeout []()

- top []()

- touch []()

- tr []()

- traceroute []()

- traceroute6 []()

- true []()

- truncate []()

- ts []()

- tty []()

- ttysize []()

- tunctl []()

- ubiattach []()

- ubidetach []()

- ubimkvol []()

- ubirename []()

- ubirmvol []()

- ubirsvol []()

- ubiupdatevol []()

- udhcpc []()

- udhcpc6 []()

- udhcpd []()

- udpsvd []()

- uevent []()

- umount []()

- uname []()

- unexpand []()

- uniq []()

- unix2dos []()

- unlink []()

- unlzma []()

- unshare []()

- unxz []()

- unzip []()

- uptime []()

- users []()

- usleep []()

- uudecode []()

- uuencode []()

- vconfig []()

- vi []()

- vlock []()

- volname []()

- w []()

- wall []()

- watch []()

- watchdog []()

- wc []()

- wget []()

- which []()

- who []()

- whoami []()

- whois []()

- xargs []()

- xxd []()

- xz []()

- xzcat []()

- yes []()

- zcat []()

zcip
```