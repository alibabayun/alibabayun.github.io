---
layout: post
title: "php-cli下的一个批量下载图片程序"
keywords: ["php-cli下的一个批量下载图片程序"]
description: "php-cli下的一个批量下载图片程序"
category: "php-cli"
tags: ["php-cli"]
---
{% include JB/setup %}


````
/**
 * 图片下载 功能
 * @return [type] [description]
 */
public function save_img()
{
    // 数据表
    
    // CREATE TABLE `bs_album_img` (
    //     `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    //     `old_imgurl` VARCHAR(500) NULL DEFAULT NULL COMMENT '原图片',
    //     `new_imgurl` VARCHAR(500) NULL DEFAULT NULL COMMENT '下载后的新图片地址',
    //     `img_path` VARCHAR(500) NULL DEFAULT NULL COMMENT '下载路径',
    //     `is_down` INT(1) NOT NULL DEFAULT '0' COMMENT '是否下载',
    //     `md5_uqique` VARCHAR(100) NULL DEFAULT NULL,
    //     `add_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
    //     PRIMARY KEY (`id`),
    //     INDEX `md5_uqique` (`md5_uqique`)
    // )
    // COMMENT='图片下载'
    // COLLATE='utf8_general_ci'
    // ENGINE=InnoDB
    // ROW_FORMAT=DYNAMIC
    // AUTO_INCREMENT=2282
    // ;

    // ignore_user_abort();//关闭浏览器仍然执行
    set_time_limit(0);//让程序一直执行下去
    ini_set('meory_limit', '160M');

    $opts = array( 
        'http'=>array( 
            'method'=>'GET', 
            'timeout'=>3, 
        ) 
    ); 
    $context = stream_context_create($opts); 
    $condition =  array('is_down' => 0);
    $arr = M('album_img')->where($condition)->order('id asc')->limit(500)->select();
    $i = 1;
    echo "\n------------------- start run ----------------------\n";
    foreach ($arr as $key => $value) {
        $imgurl = $value['old_imgurl'];
        $img_path = $value['img_path'];
        $arr = explode('/', $imgurl);
        $img_name = array_pop($arr);
        $img_path_cgi = C('SAVE_PIC_PATH').'/'.$img_path.$img_name;
        $content_file = file_get_contents($imgurl, 0, $context);
        $res = false;
        $file = '';
        if( $content_file ) {
            // $res = file_put_contents($img_path_cgi, $content_file);
            $file = fopen($img_path_cgi,"w");
            $res =  fwrite($file, $content_file);
            fclose($file);
        }

        $img = $img_path.$img_name;
        if ($res) {
            M('album_img')->where( array('id' => $value['id']) )->save(array('is_down' => 1));
            // $img =  C('CDN_URL').$img_path.$img_name;
            echo "\n$i\t[success]\t".  $img;
        } else {
            M('album_img')->where( array('id' => $value['id']) )->save(array('is_down' => 2));
            echo "\n$i\t[faild]\t".  $img;
        }
        ++$i;
        unset($content_file);
        unset($file);
        if ($i%10 == 0) {
            sleep(1);
        }
    }
    $sql_obj = M();
    $data = $sql_obj->query("select 
                    (select count(id) from bs_album_img where is_down=1) as '已下载',
                    (select count(id) from bs_album_img where is_down=0) as '未下载',
                    (select count(id) from bs_album_img where is_down=3) as '图片不存在',
                    (select count(id) from bs_album_img where is_down=2) as '下载失败';");
    foreach ($data as $key => $value) {
        echo  "\n\t". '[is_down=1] 已下载: ' .$value['已下载'];
        echo  "\n\t". '[is_down=0] 未下载: ' .$value['未下载'];
        echo  "\n\t". '[is_down=3] 图片不存在: ' .$value['图片不存在'];
        echo  "\n\t". '[is_down=2] 下载失败: ' .$value['下载失败'];
        echo  "\n";
    }
    die("\n------------------- run done ----------------------\n");
}  

````