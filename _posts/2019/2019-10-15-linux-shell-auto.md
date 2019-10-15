---
layout: post
title: "记录一个自动配置线上项目的shell脚本"
keywords: ["linux"]
description: "记录一个自动配置线上项目的shell脚本"
category: "linux"
tags: ["linux"]
---
有时为了方便在线上快速自动部署可参考以下脚本配置

## 脚本 
```
#!/bin/sh
project_name=$1
if [ -n "$1" ]; then
    echo "开始配置项目: $project_name"
    git_url="git@gitee.com:you_name/$project_name.git"

    cd /data/git/
    git clone $git_url
    mkdir  "/data/www/"$project_name
    git_path_file="/opt/bin/git_update_"$project_name".sh"
        cat > $git_path_file <<EOF
#!/bin/sh
cd /data/git/${project_name}/
git checkout -- *
git pull origin master
if [ $? -eq 0 ];then
    chown -R www.www /data/git/${project_name}/
    chmod -R 755 /data/git/${project_name}/
    rsync -a --exclude='.git/' --exclude='Doc/' --exclude='README.md'  /data/git/${project_name}/ /data/www/${project_name}/

    #find /data/www/${project_name}/Application/ -name common~runtime.php -ok rm -rf {} \;
    rm -fr /data/www/${project_name}/Application/Runtime*
    #service php-fpm reload
else
    echo "代码拉取失败"
fi
EOF
    chmod +x $git_path_file
    sh $git_path_file
else
    echo "没设置项目名称参数"
fi
```

## 效果为
- 添加目录`/data/www/project`
- 添加目录`/data/git/project`
- 添加更新脚本`/opt/bin/git_update_project.sh`