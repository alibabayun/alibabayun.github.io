---
layout: post
title: "Python中的easy_install 时报错问题"
keywords: ["Python中的easy_install 时报错问题"]
description: "Python中的easy_install 时报错问题"
category: "python"
tags: ["python", "easy_install"]
---
{% include JB/setup %}

## error: command 'gcc' failed with exit status when installing psycopg2


### 报如下错：

```
[root@server01 ~]# easy_install psycopg2    
Searching for psycopg2    
Reading http://pypi.python.org/simple/psycopg2/    
Reading http://initd.org/psycopg/    
Reading http://initd.org/projects/psycopg2    
Best match: psycopg2 2.4.5    
Downloading http://initd.org/psycopg/tarballs/PSYCOPG-2-4/psycopg2-2.4.5.tar.gz    
Processing psycopg2-2.4.5.tar.gz    
Running psycopg2-2.4.5/setup.py -q bdist_egg --dist-dir /tmp/easy_install-anWVvJ/psycopg2-2.4.5/egg-dist-tmp-cZbdtn

no previously-included directories found matching 'doc/src/_build' In file included from psycopg/psycopgmodule.c:27:
./psycopg/psycopg.h:31:22: error: libpq-fe.h: No such file or directory In file included from psycopg/psycopgmodule.c:29:

...

error: Setup script exited with error: command 'gcc' failed with exit status 1
```

### 解决方案：

```
sudo yum install postgresql-libs
sudo yum install postgresql-devel
sudo yum install python-devel
```

### 参考资料
http://stackoverflow.com/questions/12911717/error-command-gcc-failed-with-exit-status-when-installing-psycopg2