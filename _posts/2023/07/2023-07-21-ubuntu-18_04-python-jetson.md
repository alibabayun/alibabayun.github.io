---
layout: post
title: "Jetson Nano （aarch64）"
keywords: ["linux"]
description: "Jetson Nano （aarch64）"
category: "linux"
tags: ["linux"]
---

### Anaconda介绍
Anaconda 是一个流行的 Python 数据科学平台，它提供了一个开源的发行版，包括 Python 解释器以及用于科学计算和数据分析所需的许多常用软件包。Anaconda 包含了大量的科学计算库，如NumPy、Pandas、Matplotlib、SciPy 等，简化了安装和管理这些库的过程。

Anaconda 还提供了一个名为 Anaconda Navigator 的图形用户界面 (GUI)，可用于浏览和启动安装在 Anaconda 中的软件包和工具。此外，Anaconda 还提供了一个名为 conda 的包管理工具，可以帮助用户轻松地创建、安装和管理独立的 Python 环境，以及管理不同环境中的软件包版本依赖关系。

总之，Anaconda 提供了一个方便的平台，使得 Python 数据科学工作变得更加简单和高效。它在数据分析、机器学习、人工智能等领域被广泛应用。

### Anaconda参考资料
* [Anaconda 下载地址](https://repo.anaconda.com/archive/)
* [Anaconda 安装](https://zhuanlan.zhihu.com/p/349345180)
* [Anaconda 常用命令](https://zhuanlan.zhihu.com/p/123188004)
* [Anaconda环境离线迁移移植（可行有效）](https://blog.csdn.net/FY_2018/article/details/119710831)
* [如何更新 Anaconda 中的 conda](https://blog.csdn.net/mrmengj/article/details/112802808)
* [conda创建环境失败(Collecting package metadata (repodata.json): failed) conda降级处理](https://blog.csdn.net/donaldsy/article/details/104322223)
* [conda创建环境速度慢解决办法](https://blog.csdn.net/qq_34301511/article/details/106798313)
* [conda国内源](https://zhuanlan.zhihu.com/p/260034241)
* [Conda环境离线迁移](https://blog.csdn.net/dendi_hust/article/details/115475741)

### conda 常用命令
```
# 创建环境
conda create --name yt python=3.6.15

# 删除一个已有的环境
conda remove --name python34 --all

# 查看全部环境
conda env list

# 进入环境
activate python34 # for Windows
source activate python34 # for Linux & Mac

# 退出环境
conda deactivate

# 导出当前环境的包信息
conda env export > environment.yaml

# 用配置文件创建新的虚拟环境
conda env create -f environment.yaml

# 离线导入导出环境 ，安装工具
conda install -c conda-forge conda-pack

# 离线导出环境
conda pack -n env_name -o env_name.tar.gz --ignore-editable-packages

```
### 查看环境`cat /etc/lsb-release`
```
DISTRIB_ID=Ubuntu
DISTRIB_RELEASE=18.04
DISTRIB_CODENAME=bionic
DISTRIB_DESCRIPTION="Ubuntu 18.04.6 LTS"
```
[换apt-get 源](https://www.cnblogs.com/dream4567/p/9690850.html)

### 硬件准备
[MAKENVIDIA英伟达Jetson TX2 NX开发板套件深度学习 AI人工智能 嵌入式边缘计算 TX2 NX基础套餐](https://ic-item.jd.com/10050838324138.html#crumb-wrap)

### 环境搭建
1. [Jetson NX设置nvme固态硬盘为系统盘 刷新教程](https://blog.csdn.net/m0_37605642/article/details/123804021)
```
it clone https://github.com/jetsonhacks/rootOnNVMe.git
cd rootOnNvMe/
cd rootOnNVMe/
ls
./copy-rootfs-ssd.sh
./setup-service.sh
sudo reboot
df -h
sudo apt-get update
sudo apt-get full-upgrade
sudo apt install nvidia-jetpack
8
nvcc -V
```
2. [Python 3.6.1安装 - 使用pyenv管理不同的Python版本](https://github.com/pyenv/pyenv)
3. [Jetson Nano （aarch64）搭建miniconda 和mmdetection环境](https://blog.csdn.net/ckq707718837/article/details/123346043)
4. [Github port 443 : Timed out](https://zhuanlan.zhihu.com/p/636418854)
```
git config --global --unset http.proxy
```
5. [VMware虚拟机扩容磁盘，有很详细图文](https://blog.csdn.net/hktkfly6/article/details/123302335)


### 软件版本
1. python=3.6.15
```
# 创建环境
conda create --name yt python=3.6.15
```

2. opencv安装指定版本
```
pip install --upgrade opencv-python==4.1.1.26 -i https://pypi.tuna.tsinghua.edu.cn/simple
```
