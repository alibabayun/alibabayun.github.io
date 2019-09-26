---
layout: post
title: "mysql存储过程示例"
keywords: ["mysql", "性能", "数据库"]
description: "mysql存储过程创建与调用示例"
category: "技术分享"
tags: ["mysql", "存储过程"]
---
{% include JB/setup %}

### 创建存储过程
```
# 存储过程
# 参数：jid -> 活动参与id, kanjiaPrice -> 砍价金额, gid -> 商品id, hid -> 活动id,
# useropenid -> 用户openid, promotionopenid -> 被砍价用户openid
# tablename -> 砍价记录表表名（分表表名）
# 返回值：returnValue -> -1 为失败，其它值为成功砍价后剩下的金额
# 返回值：returnTime 插入时间戳
# 返回值：returnId 砍价用户表自增ID

DROP PROCEDURE IF EXISTS weikanjia_addpromotionrecord;

DELIMITER //

CREATE PROCEDURE weikanjia_addpromotionrecord
    (
      par_jid int,
      par_kanjiaPrice DECIMAL(9,2),
      par_dijiaPrice DECIMAL(9,2),
      par_gid varchar(50),
      par_hid int,
      par_useropenid varchar(70), par_promotionopenid varchar(70),
      OUT returnValue DECIMAL(9,2),
      OUT returnId int
    )
BEGIN
    #异常捕获handler变量定义
    DECLARE _err int default 1;
    DECLARE back_promotionprice DECIMAL(9,2) default 0;
    DECLARE continue handler for sqlexception, sqlwarning, not found set _err=0;

    #开始事务
    START TRANSACTION;

    #关闭自动提交
    SET autocommit=0;

    #更新砍后的剩余金额
    UPDATE
        kj_joinpromotionrecord
    SET
        currentprice = currentprice - par_kanjiaPrice, totalpromotionquantity = totalpromotionquantity + 1
    WHERE
        jid = par_jid;


    #返回砍价之后剩下的金额
    SELECT
        currentprice into back_promotionprice
    FROM
        kj_joinpromotionrecord
    WHERE
        jid = par_jid;

    if back_promotionprice>=par_dijiaPrice then

        #插入砍价用户openid
        INSERT IGNORE INTO
            kj_kanjiauser(gid, openid)
        VALUES
            (
                par_gid, par_useropenid
            );


        #插入唯一砍价记录
        INSERT INTO
            kj_kanjiajilu(gid, openid, promotionopenid)
        VALUES
            (
                par_gid, par_useropenid, par_promotionopenid
            );

        set returnId = last_insert_id();

    else
        set _err=0;
        set returnValue=-2;

    end if;
    

    #异常判断
    if _err=0 then
        if returnValue!=-2 then
            set returnValue=-1;
        end if;
        ROLLBACK;

    else
        #返回值设置
        set returnValue=back_promotionprice;
        COMMIT;

    end if;

    SET autocommit=1;

END;

//

DELIMITER ;
```


#### php调用示例
```
        $sql = "call weikanjia_addpromotionrecord({$jid}, {$bkmoney}, {$goodsMinPrice}, '{$gid}', {$hid}, '{$bkoid}', '{$fqoid}', @priceleft, @kanjiaId)";
        $res_1 = $this->dao_write->dao_write_query($sql);
        $res = $this->dao_write->dao_write_query('select @priceleft as priceleft, @kanjiaId as kanjiaid');
```
