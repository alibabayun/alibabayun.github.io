---
layout: post
title: "PHP-阿里OSS列出bucket下或者‘某个目录下’的所有文件"
keywords: ["PHP"]
description: "PHP-阿里OSS列出bucket下或者‘某个目录下’的所有文件"
category: "PHP"
tags: ["PHP"]
---

阿里的oss中是没有目录这个概念的，所谓目录 ，是文件的前缀来区分的。
```
/**
     * 列出Bucket内所有目录和文件， 根据返回的nextMarker循环调用listObjects接口得到所有文件和目录
     * 
     * @param $prefix 你要列出的文件所在的目录名
     * @param $nextMarker 从上一次listObjects读到的最后一个文件的下一个文件开始继续获取文件列表
     * @param $delimiter 为行使文件夹功能的分割符号，如 /
     * @param $maxkeys max-keys用于限定此次返回object的最大数
     */
    public function olists($prefix,$nextMarker='',$delimiter='/',$maxkeys=30)
    {
        $options = array(
            'delimiter' => $delimiter,
            'prefix' => $prefix,
            'max-keys' => $maxkeys,
            'marker' => $nextMarker,
        );
        try {
            $listObjectInfo = $this->ossClient->listObjects($this->bucket, $options);
        } catch (OssException $e) {
            return $this->ci->error_code->oss_obj_lists_no($e->getMessage());
        }
        // 得到nextMarker，从上一次listObjects读到的最后一个文件的下一个文件开始继续获取文件列表
        $nextMarker = $listObjectInfo->getNextMarker();
        $listObject = $listObjectInfo->getObjectList();
        $listPrefix = $listObjectInfo->getPrefixList();
        $list = array();
        $list['nextMarker'] = $nextMarker;
        foreach($listObject as $info){
            $list['file'][] = array(
                    'name' => $info->getKey(),
                    'lastModified' => $info->getLastModified()
                );
        }
        foreach($listPrefix as $info){
            $list['dir'][] = array('name' => $info->getPrefix());
        }
        return $this->ci->error_code->oss_obj_lists_yes($list);
    }
```