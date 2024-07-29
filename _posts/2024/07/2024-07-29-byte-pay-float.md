---
layout: post
title: "抖音小程序支付金额出现小数不对的问题"
keywords: ["php"]
description: "抖音小程序支付金额出现小数不对的问题"
category: "php"
tags: ["php"]
---


### 前言
最近在做抖音支付下单和退款时，老是会将19.9下单变成19.8，退款时19.9*100 可能变成1989.9999

### bug示例
```
    /**
     * [createRefund 订单退款]
     * @param  [type] $order [订单相关信息]
     * @return [type]        [description]
     * $order = array(
     *      'order_sn'     => '', // 订单编号
     *      'refund_sn'    => '', // 退款编号
     *      'total_amount' => '', // 订单金额（分）
     *      'body'         => '', // 退款原因
     * );
     */
    public function createRefund($order)
    {
        $config = $this->config;

        $params = [
            'order_id'       =>(string)$order['transaction_id'],//交易系统侧订单号
            'out_refund_no'  =>getOrdersn(99999,'B'),//开发者侧退款单号
            'cp_extra' => "",
            'order_entry_schema' =>[
				"path"=>"pages/store/index",
				"params"=>"",
			],
			'notify_url'=>$config['refund_notify_url'],
			'refund_total_amount'=>$order['money']*100,
			'refund_reason'=>[["code"=>999,"text"=>"其他"]],
			'refund_all'=>true
        ];

        $url = $this->createRefundUrl;

        $response = Http::post($url, json_encode($params),[
            'Content-Type:application/json',
            'access-token:'.$this->getClientToken()
        ]);
        $result = json_decode($response, true);
        return $result;
    }
```


### 修复后
```
    /**
     * [createRefund 订单退款]
     * @param  [type] $order [订单相关信息]
     * @return [type]        [description]
     * $order = array(
     *      'order_sn'     => '', // 订单编号
     *      'refund_sn'    => '', // 退款编号
     *      'total_amount' => '', // 订单金额（分）
     *      'body'         => '', // 退款原因
     * );
     */
    public function createRefund($order)
    {
        $config = $this->config;
        $amount = intval(strval($order['money']*100));
        $params = [
            'order_id'       =>(string)$order['transaction_id'],//交易系统侧订单号
            'out_refund_no'  =>getOrdersn(99999,'B'),//开发者侧退款单号
            'cp_extra' => "",
            'order_entry_schema' =>[
                "path"=>"pages/store/index",
                "params"=>"",
            ],
            'notify_url'=>$config['refund_notify_url'],
            'refund_total_amount'=> $amount,
            'refund_reason'=>[["code"=>999,"text"=>"其他"]],
            'refund_all'=>true
        ];

        $url = $this->createRefundUrl;

        $response = Http::post($url, json_encode($params),[
            'Content-Type:application/json',
            'access-token:'.$this->getClientToken()
        ]);
        $result = json_decode($response, true);
        return $result;
    }
```

### 主要问题
主要是`refund_total_amount`金额 不能直接在数组中*100， 需要 这样处理下
```
$amount = intval(strval($order['money']*100));
```
