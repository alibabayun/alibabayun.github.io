---
layout: post
title: "Linux增加新盘挂载和分区-centOS和ubuntu"
keywords: ["Linux增加新盘挂载和分区-centOS和ubuntu"]
description: "Linux增加新盘挂载和分区-centOS和ubuntu"
category: "挂载"
tags: ["linux", "挂载"]
---

* centOS
```
fdisk -l               #查看当前没有被挂载的盘，比如/dev/xvdb1。也就是用df -h看不到的盘
fdisk /dev/xvdb1       #分区，并依次输入 n，p，1，两次回车，wq。如果输入1时主分区被用了，用d删除。
fdisk -l               #这时，能看到新的分区
mkfs.ext4 /dev/xvdb1   #对新分区进行格式化，格式化成ext4类型的文件系统
mkdir /data            #在根目录下新建data目录，用于挂载新分区。
mount -t ext4 /dev/xvdb1 /data    #把/data目录挂载到/dev/xvdb1分区下。
mount -a              #使挂载生效。之后，再运行mount，查看所有挂载情况
echo /dev/xvdb1 /data ext4 defaults 0 0 >> /etc/fstab    #永久挂载，即机器重启后依然有效
df -h                  #这时，已经可以看到名字为data的新分区了
```

* ubuntu
```
fdisk -lu               #查看当前没有被挂载的盘，比如/dev/xvdb1。也就是用df -h看不到的盘
fdisk /dev/xvdb1       #分区，并依次输入 n，p，1，两次回车，w(保存)。如果输入1时主分区被用了，用d删除。
fdisk -lu               #这时，能看到新的分区
mkfs.ext4 /dev/xvdb1   #对新分区进行格式化，格式化成ext4类型的文件系统
mkdir /data            #在根目录下新建data目录，用于挂载新分区。
mount -t ext4 /dev/xvdb1 /data    #把/data目录挂载到/dev/xvdb1分区下。
mount -a              #使挂载生效。之后，再运行mount，查看所有挂载情况
echo /dev/xvdb1 /data ext4 defaults 0 0 >> /etc/fstab    #永久挂载，即机器重启后依然有效
df -h                  #这时，已经可以看到名字为data的新分区了
```

* fstab文件说明
```
　fstab通常都位于/etc目录下，它包括了所有分区和存储设备的信息，以及它们应该挂载到哪里，以什么样子的方式挂载。 
　　在这个文件中，每个文件系统（包括分区或者设备）用一行来描述，在每一行中，用空格或TAB符号来分隔各个字段，文件中以*开头的行是注释信息。 
　　fstab文件中的纪录的排序十分重要。因为 fsck，mount或umount等程序在做它们的工作时会按此顺序进行。
```

以如下一行内容为例子进行讲解：

```
/dev/hdb1  /home        ext4 defaults 1 2
/dev/cdrom /media/cdrom auto ro,noauto,user,exec 0 0
```
----
* 第一列：设备的名称。 
* 第二列：该设备的挂载点。 
* 第三列：文件系统。 
* 第四列：挂载选项。 
* 第五列：dump选项（用一个数字表示） 
* 第六列：（接下来的数字）表示文件系统检查选项。

-----
* 第一、二列是设备和默认挂载点。 
　　比如，你终端中敲入：mount /dev/cdrom，光驱就会默认的挂载到/media/cdrom这个目录下。 
　　除了使用确切的设备名外，也可以使用设备的UUID或设备的卷标签，例如，可以在这个字段写成“LABAL=root”或“UUID= 3e6be9de-8139-11d1-9106-a43f08d823a6”，这将使系统更具伸缩性。例如，如果你的系统添加或移除了一个SCSI硬盘，这有可以改变你的设备名，但它不会修改你的卷标签。
*  第四列：挂载选项 
　　第四列表示设备或者分区所需要的挂载选项。这一列也是fstab中最复杂最容易出错的一列。 
　　常用选项： 
　　auto 和 noauto： 这是控制设备是否自动挂载的选项。 
　　user 和 nouser：这是一个非常有用的选项，user选项允许普通用户也能挂载设备，而nouser则只允许root用户挂载。nouser是默认选项，会导致没有办法正常挂载光驱。 
　　exec 和 noexec： 允许(不允许)在分区中的可执行二进制程序。 
　　ro： 以只读来挂载文件系统。 
　　rw： 以可读可写的属性来挂载系统。 
　　defaults： 所有选项全部使用默认配置，包括rw, suid, dev, exec, auto, nouser, 和 async。
*  第五、六列：dump和fsck选项 
　　dump是一个备份工具，而fsck是一个文件系统扫描检查工具。 
　　第五列，dump工具通过这个选项位置上的数字来决定文件系统是否需要备份。如果是0，dump就会被忽略，事实上，大多数的dump设置都是0。 
　　第六列，fsck命令通过检测该字段来决定文件系统通过什么顺序来扫描检查，根文件系统/对应该字段的值应该为1，其他文件系统应该为2。若文件系统无需在启动时扫描检查，则设置该字段为0。