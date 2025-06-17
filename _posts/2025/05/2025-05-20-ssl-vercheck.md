---
layout: post
title: "全球根证书升级DigiCertG2根影响分析&解决思路"
date: 2025-05-20 10:00:00 +0800
categories: ["ssl"]
tags: ["ssl"]
---

## 前言
全球知名信任库 Mozilla 对于 CA 根证书的信任策略包含：全球所有 CA 的可信根证书生成后最少 15 年更换一次。过期的根证书将会逐步被 Mozilla 停止信任。（sha1签名要逐步替换，当前用到的根也要替换）
从 2025 年开始，Mozilla 将停止信任 DigiCert 下的一些老的根证书。
DigiCert 是全球的顶级CA机构 （最常见畅销的品类，曾收购赛门铁克CA业务） ；在23年3月在全球范围进行下一代根证书轮换，将这些根证书升级到新的 G2 根体系，以避免 DigiCert 证书的使用受到影响。目前新申请Digicert证书均为DigiCert Global Root G2签发
提醒：后续几年仍持续有其他CA机构会做根升级操作，如果能实现授信列表更新 能降低未来风险

## 参考抖音文档
https://bytedance.larkoffice.com/docx/JSl0dSrp2oZ6XkxJZrgcp4EQnLw


## 验证
### 方式一：
```
ll /etc/ssl/certs/ | grep DigiCert
```
如果有找到就表示成功

###方式二：
```
openssl s_client -connect www.digicert.com:443 -servername www.digicert.com
```
2
```
openssl s_client -connect www.digicert.com:443 -servername www.digicert.com 2>&1 | grep -o 'Verify return code: 0'
```
预期成功输出：
verify return: 0（所有证书链节点验证通过）。
证书链中 depth=2 的根证书验证状态变为 verify return: 0。

