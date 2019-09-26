---
layout: post
title: "js prototype 属性使您有能力向对象添加属性和方法。"
keywords: ["js prototype 属性使您有能力向对象添加属性和方法。"]
description: "js prototype 属性使您有能力向对象添加属性和方法。"
category: "js"
tags: ["js"]
---
{% include JB/setup %}

给js写成类的形式

````
<script type="text/javascript">
	var saveTel = function(){
		this.num = 0;
	};
	saveTel.prototype = {
		// 保存手机号
		ajax_save_tel:function(phone){
			$.post("api.php",{tel:phone, action:'ajax_save_tel', sid:HUODONGID},function(json_data){
				alert(json_data.msg);
			}, 'json');
		},
		// 是否有保存过手机号
		ajax_is_tel:function(callback){
			$.post("api.php",{action:'ajax_is_tel', sid:HUODONGID},function(json_data){
				callback&&callback(json_data.status);
			}, 'json');
		},
		// 显示提示框
		show_prompt:function(callback){
			var phone=prompt("请输入您的手机号码，方便中奖后联系到您~","");
			if(!(/^1[34578]\d{9}$/.test(phone))){ 
				alert("手机号码有误，请重填");
				return false;
			}
			callback&&callback(phone)
		},
		// 隐藏提示框
		hide_prompt:function(){

		}
	};

	var obj = new saveTel();
	is_tel = obj.ajax_is_tel(function(is_tel){
		// 如果没有填写过手机号码
		if (is_tel == 0) {
			obj.show_prompt(function(phone){
				obj.ajax_save_tel(phone)
			});
		} else {
			obj.hide_prompt();
		}
	});
</script>
````