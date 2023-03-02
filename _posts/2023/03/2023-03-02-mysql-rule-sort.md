---
layout: post
title: "MySQL排序规则批量修改"
keywords: ["mysql"]
description: "MySQL排序规则批量修改"
category: "mysql"
tags: ["mysql"]
---

## 前言
如果使用了UNION联合查询，当不同排序规则 时可能会报以下错误
可能会报以下错误
```
Error 1271: Illegal mix of collations for operation 'UNION'
```

## 参考资料
[https://blog.csdn.net/Bruce1114/article/details/119035811](https://blog.csdn.net/Bruce1114/article/details/119035811)


## sql
```
-- 批量拼接修改字段排序规则SQL
SELECT TABLE_SCHEMA                  '数据库',
       TABLE_NAME                    '表',
       COLUMN_NAME                   '字段',
       COLUMN_TYPE                   '字段类型',
       COLUMN_DEFAULT                '字段默认值',
       COLUMN_COMMENT                '字段注释',
       COLUMN_KEY                    '主键,唯一索引,非唯一索引',
       IS_NULLABLE                   '字段是否可为NULL',
       CHARACTER_SET_NAME            '原字符集',
       COLLATION_NAME                '原排序规则',
       CONCAT('ALTER TABLE ', TABLE_SCHEMA, '.', TABLE_NAME, ' MODIFY COLUMN ', COLUMN_NAME, ' ', COLUMN_TYPE,
              ' CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ',
              IF(IS_NULLABLE = 'NO', 'not null ', ''),
              IF(COLUMN_DEFAULT is null, ' ', CONCAT('default \'', COLUMN_DEFAULT, '\' ')), 'comment \'',
              COLUMN_COMMENT, '\';') '修正SQL'
FROM information_schema.`COLUMNS`
WHERE TABLE_SCHEMA = 'iot'
and TABLE_NAME in ("device_camera",
"device_wisdom_gateway",
"device_light_control",
"device_switch_box",
"device_info_board",
"device_opto_sensor",
"device_zigbee",
"device_a_key_alarm_terminal",
"device_a_key_alarm_serve",
"device_transformer",
"device_lamp_pole",
"device_lamp_pole",
"device_bridge_sensor",
"device_ip_broadcast",
"device_ip_broadcast")
  and COLLATION_NAME RLIKE 'utf8mb4_0900_ai_ci';

```
