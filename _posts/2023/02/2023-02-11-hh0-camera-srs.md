---
layout: post
title: "海康摄相头对接 srs"
keywords: ["srs"]
description: "海康摄相头对接 srs"
category: "srs"
tags: ["srs"]
---

### 参考资料
参考1： https://blog.csdn.net/Alan_ran/article/details/126739871
参考2： https://github.com/ossrs/srs/issues/3176
参考3： https://github.com/ossrs/srs-gb28181/issues/4

### 海康rtsp
rtsp://admin:passwd@192.168.110.12:554/h264/ch1/main/av_stream

### 从0开始搭建
[搭建centos 环境](http://mirrors.aliyun.com/centos/7.9.2009/isos/x86_64/?spm=a2c6h.12873639.article-detail.7.64aa2422mqAnOd)

这里使用：CentOS-7-x86_64-Minimal-2009.iso

### ffmpeg
```
ffmpeg -re -i demo.flv -c copy -f flv rtmp://43.136.182.190/live/livestream?secret=xxxx
ffmpeg -f flv -listen 1 -i 'rtsp://admin:xxxx@@192.168.110.12:554/h264/ch1/main/av_stream' -c copy 'rtmp://43.136.182.190/live/livestream?secret=9d7c20422d3b43be8a2e6f35dec12dda'
```

### 海康gb2818接入srs
https://blog.csdn.net/Alan_ran/article/details/126739871

### srs安装 (官方)
```
git clone -b develop https://gitee.com/ossrs/srs.git &&
cd srs/trunk && ./configure && make && ./objs/srs -c conf/srs.conf
```
注意：如果要用gb28181 ,要加`./configure --gb28181=on`
```
cd srs/trunk && ./configure --gb28181=on && make && ./objs/srs -c conf/srs.conf
```
### srs配置文档
`vi config/test.conf`添加以下配置
```
listen              1935;
pid                 ./objs/srs.pid;
chunk_size          60000;
ff_log_dir          ./objs;
ff_log_level        info;
srs_log_tank        file;
srs_log_level       trace;
srs_log_file        ./objs/srs.log;
max_connections     1000;
daemon              on;
utc_time            off;
pithy_print_ms      10000;
work_dir ./;
asprocess off;
empty_ip_ok on;
grace_start_wait 2300;
grace_final_wait 3200;
force_grace_quit off;
disable_daemon_for_docker on;
inotify_auto_reload off;
auto_reload_for_docker on;
tcmalloc_release_rate 0.8;

http_api {
    enabled         on;
    listen          1985;
    crossdomain     on;
    raw_api {
        enabled             off;
        allow_reload        off;
        allow_query         off;
        allow_update        off;
    }
    https {
        enabled on;
        listen 1990;
        key ./conf/selfsigned.key;
        cert ./conf/selfsigned.crt;
    }
}

http_server {
    enabled         on;
    listen          18080;
    dir             ./objs/nginx/html;
    crossdomain     on;
}


# RTSP
#stream_caster {
#    enabled         on;
#    caster          rtsp;
#    output          rtmp://192.168.110.28/[app]/[stream];
#    listen          554;
#    rtp_port_min    57200;
#    rtp_port_max    57300;
#}

# FLV
#stream_caster {
#    enabled         on;
#    caster          flv;
#    output          rtmp://192.168.110.28/[app]/[stream];
#    listen          8936;
#}

# GB28181
stream_caster {
    enabled         on;
    caster          gb28181;
    output          rtmp://192.168.110.28/live/[stream];
    listen          9181;
    tcp_enable      off;
    rtp_port_min        58200;
    rtp_port_max        58300;
    wait_keyframe       on;
    rtp_idle_timeout    30;
    audio_enable        off;
    host                192.168.110.28;
    auto_create_channel  off;

    sip {
        enabled         on;
        listen          5060;
        serial          34020000002000000001;
        realm           3402000000;
        ack_timeout         30;
        keepalive_timeout   30;
        auto_play           on;
        invite_port_fixed   on;
        query_catalog_interval 60;
    }
}

rtc_server {
    enabled         on;
    listen          8000;
    candidate       192.168.110.28;
}

vhost __defaultVhost__ {
    rtc {
        enabled     on;
        bframe      discard;
    }

    tcp_nodelay     on
    min_latency     on;

    play {
        gop_cache       off;
        queue_length    10;
        mw_latency      100;
    }

    publish {
        mr off;
    }

    http_hooks {
        # 线上需要开启这些监听的回调
        #enabled         on;
        enabled         off;
        on_connect      http://127.0.0.1:8085/api/v1/clients;
        on_close        http://127.0.0.1:8085/api/v1/clients;
        on_publish      http://127.0.0.1:8085/api/v1/streams;
        on_unpublish    http://127.0.0.1:8085/api/v1/streams;
        on_play         http://127.0.0.1:8085/api/v1/sessions;
        on_stop         http://127.0.0.1:8085/api/v1/sessions;
        on_dvr          http://127.0.0.1:8085/api/v1/dvrs;
        on_hls          http://127.0.0.1:8085/api/v1/hls;
        on_hls_notify   http://127.0.0.1:8085/api/v1/hls/[app]/[stream]/[ts_url][param];
    }

    dvr {
        enabled         on;
        dvr_apply       all;
        dvr_plan        segment;
        dvr_path        /data/[app]/[stream]/[2006]-[01]-[02]/[15].[04].mp4;
	dvr_duration    1800;
        dvr_wait_keyframe       on;
        time_jitter             full;
    }
}
```

### 启动命令
```
./objs/srs -c conf/test.conf &
```

### 关防火墙
```
systemctl status firewalld.service
systemctl stop firewalld.service
systemctl disable firewalld.service
```

### 测试
```
ffmpeg -re -i ./doc/source.flv -c copy -f flv -y rtmp://localhost/live/livestream
```


### 调试软件
1. [VLC media player](https://www.videolan.org/vlc/)
2. [OBS Studio](https://obsproject.com/)
