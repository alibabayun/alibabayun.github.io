---
layout: post
title: "ufi-openwrt常用配置"
keywords: ["linux"]
description: "ufi-openwrt常用配置"
category: "linx"
tags: ["linux"]
---

## 前言
ufi第一次连接时，需要插入usb, 并用adb shell进入


## 网络配置目录
进入`/etc/config`修改 `network`和 `wireless`文件

network
```
config interface 'loopback'
	option device 'lo'
	option proto 'static'
	option ipaddr '127.0.0.1'
	option netmask '255.0.0.0'

config globals 'globals'
	option ula_prefix 'auto'

config device
	option name 'br-lan'
	option type 'bridge'
	list ports 'eth0'

config interface 'lan'
	option device 'br-lan'
	option proto 'static'
	option ipaddr '192.168.2.1'
	option netmask '255.255.255.0'
	option ip6assign '60'

config interface 'docker'
	option device 'docker0'
	option proto 'none'
	option auto '0'

config device
	option type 'bridge'
	option name 'docker0'

config interface 'wwan'
	option proto 'dhcp'
```


wireless
```
config wifi-device 'radio0'
	option type 'mac80211'
	option path 'platform/soc/a204000.remoteproc/remoteproc/remoteproc1/remoteproc1:smd-edge/remoteproc1:smd-edge.WCNSS_CTRL.-1.-1/a204000.remoteproc:smd-edge:wcnss:wifi'
	option channel '1'
	option band '2g'
	option htmode 'HT20'
	option country 'US'
	option cell_density '0'
	#option disabled '1'

config wifi-iface 'wifinet1'
	option device 'radio0'
	option mode 'sta'
	option network 'wwan'
	option ssid 'wifiname'
	option encryption 'psk2'
	option key 'wifipassword'
	option bssid '50:D2:F5:61:5F:BE'
	#option disabled '1'
```


### 这里做一个重启的脚本 `recovery.sh `
```
#!/bin/ash
cp wireless_bak  wireless
cp network_bak  network
cp firewall_bak firewall
wifi up
```

### 关闭防火墙
```
/etc/init.d/firewall stop
#https://blog.csdn.net/weixin_30906425/article/details/94849942
/etc/init.d/firewall disable
```