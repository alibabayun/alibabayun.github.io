/*
算术运算符
+  正
-  负
+  加
-  减
*  乘
/  除
%  取余
+  连接运算
++ 前++
++ 后++
-- 前--
-- 后--

*/
class AriTest{

	public static void main(String[] args) {
		//除法
		int a1 = 19;
		int b1 = 5;
		System.out.println( "整形 a1 / b1 = " + a1 / b1 ); //结果： 3
		System.out.println( "浮点1 a1 / b1 = " + a1 / (b1+0.0) ); //结果： 3.8
		System.out.println( "浮点2 a1 / b1 = " + (a1+0.0) / b1 ); //结果： 3.8
		System.out.println( "浮点3 a1 / b1 = " + (double)a1 / b1 ); //结果： 3.8
		/*****负数相除******/
		int a2 = -19;
		int b2 = -3;
		int c2 = 3;
		System.out.println( "负整形 a2 / b2 = " + a2 / c2 ); //结果：-6
		System.out.println( "负整形 a2 / b2 = " + a1 / b2 ); //结果：-6
		System.out.println( "负整形 b2 / c2 = " + b2 / c2 ); //结果：-1
		System.out.println( "负整形 c2 / b2 = " + c2 / b2 ); //结果：-1

		//前++
		//后++
		int a3 = 4;
		int b3 = ++a3;
		System.out.println("a3="+a3+"\t"+"b3="+b3); // 结果：a3=5 b3=5
		int d3 = 4;
		int c3 = d3++;
		System.out.println("d3="+d3+"\t"+"c3="+c3); // 结果：d3=5 c3=4

		//前--
		//后--
		int a4 = 10;
		int b4 = --a4;
		System.out.println("a4="+a4+"\t"+"b4="+b4); // a4=9 b4=9

		int c4 = 10;
		int d4 = c4--;
		System.out.println("c4="+c4+"\t"+"d4="+d4); // c4=9 d4=10

		// 注意点：
		short s1 = 33;
		// s1 = s1 + 1;//编译出错
		// s1 = (short)(s1 + 1); //ok
		// s1++; //ok
		++s1; //自增1 不会修改本身数据类型
		System.out.println("s1 = "+s1);

		byte bb1 = 127;
		++bb1;
		++bb1;
		System.out.println("bb1 = "+bb1);

	}
}