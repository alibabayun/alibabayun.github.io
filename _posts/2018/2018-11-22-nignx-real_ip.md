---
layout: post
title: "openresty添加http_realip_module"
keywords: ["ssh"]
description: "openresty添加http_realip_module"
category: "ssh"
tags: ["ssh"]
---

### 背景
因作负载时nginx得不到真实的ip，所以要安装http_realip_module。这里简单记录下操作步骤

### 参考资料
阿里云提供的资料[https://help.aliyun.com/document_detail/54007.html](https://help.aliyun.com/document_detail/54007.html)

### 如果你是使用openresty请参考以下
1.找到openresty的源码目录 
```
locate openresty
...
/data/software/openresty-1.9.15.1/util/package-win32.sh
[admin@aliyun-XXX nginx-1.9.15]# 
```
2.查看之前安装的模块
```
nginx -V

### 以下是我之前安装的
 ./configure  --prefix=/data/app/openresty/nginx --with-cc-opt=-O2 --add-module=../ngx_devel_kit-0.3.0 --add-module=../echo-nginx-module-0.59 --add-module=../xss-nginx-module-0.05 --add-module=../ngx_coolkit-0.2rc3 --add-module=../set-misc-nginx-module-0.30 --add-module=../form-input-nginx-module-0.12 --add-module=../encrypted-session-nginx-module-0.05 --add-module=../srcache-nginx-module-0.31 --add-module=../ngx_lua-0.10.5 --add-module=../ngx_lua_upstream-0.05 --add-module=../headers-more-nginx-module-0.30 --add-module=../array-var-nginx-module-0.05 --add-module=../memc-nginx-module-0.17 --add-module=../redis2-nginx-module-0.13 --add-module=../redis-nginx-module-0.3.7 --add-module=../rds-json-nginx-module-0.14 --add-module=../rds-csv-nginx-module-0.07 --with-ld-opt=-Wl,-rpath,/data/app/openresty/luajit/lib --with-http_stub_status_module --with-http_ssl_module 
```
3.添加http_realip_module模块并make && make install
```
 ./configure  --prefix=/data/app/openresty/nginx --with-cc-opt=-O2 --add-module=../ngx_devel_kit-0.3.0 --add-module=../echo-nginx-module-0.59 --add-module=../xss-nginx-module-0.05 --add-module=../ngx_coolkit-0.2rc3 --add-module=../set-misc-nginx-module-0.30 --add-module=../form-input-nginx-module-0.12 --add-module=../encrypted-session-nginx-module-0.05 --add-module=../srcache-nginx-module-0.31 --add-module=../ngx_lua-0.10.5 --add-module=../ngx_lua_upstream-0.05 --add-module=../headers-more-nginx-module-0.30 --add-module=../array-var-nginx-module-0.05 --add-module=../memc-nginx-module-0.17 --add-module=../redis2-nginx-module-0.13 --add-module=../redis-nginx-module-0.3.7 --add-module=../rds-json-nginx-module-0.14 --add-module=../rds-csv-nginx-module-0.07 --with-ld-opt=-Wl,-rpath,/data/app/openresty/luajit/lib --with-http_stub_status_module --with-http_ssl_module --with-http_realip_module

 make 
 make install
```

4.如出现ngx_http_lua_module报错
```
./configure: error: ngx_http_lua_module requires the Lua library.
```
请重新安新一下
```
yum install lua-devel
```
参考[https://blog.csdn.net/ygm_linux/article/details/81584382](https://blog.csdn.net/ygm_linux/article/details/81584382)

5.检查是否安装ok
```
[admin@XXX logs]# nginx -V
nginx version: openresty/1.9.15.1
built by gcc 4.4.7 20120313 (Red Hat 4.4.7-17) (GCC) 
built with OpenSSL 1.0.1e-fips 11 Feb 2013
TLS SNI support enabled
configure arguments: --prefix=/data/app/openresty/nginx --with-cc-opt=-O2 --add-module=../ngx_devel_kit-0.3.0 --add-module=../echo-nginx-module-0.59 --add-module=../xss-nginx-module-0.05 --add-module=../ngx_coolkit-0.2rc3 --add-module=../set-misc-nginx-module-0.30 --add-module=../form-input-nginx-module-0.12 --add-module=../encrypted-session-nginx-module-0.05 --add-module=../srcache-nginx-module-0.31 --add-module=../ngx_lua-0.10.5 --add-module=../ngx_lua_upstream-0.05 --add-module=../headers-more-nginx-module-0.30 --add-module=../array-var-nginx-module-0.05 --add-module=../memc-nginx-module-0.17 --add-module=../redis2-nginx-module-0.13 --add-module=../redis-nginx-module-0.3.7 --add-module=../rds-json-nginx-module-0.14 --add-module=../rds-csv-nginx-module-0.07 --with-ld-opt=-Wl,-rpath,/data/app/openresty/luajit/lib --with-http_stub_status_module --with-http_ssl_module --with-http_realip_module

```
上面有http_realip_module表示安装成功

6.重新启动nginx
注意安装后,一定要重新启动一次nginx,否则是不生效的

### 后端nginx使用set_real_ip_from获取用户真实IP
```
set_real_ip_from 0.0.0.0/0;
real_ip_header  X-Forwarded-For;
```
参考[https://www.centos.bz/2017/03/nginx-using-set_real_ip_from-get-client-ip/](https://www.centos.bz/2017/03/nginx-using-set_real_ip_from-get-client-ip/)