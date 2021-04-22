/*
位运算符号
<<  左移位
>>  右移位
>>> 无符号右移 。（正数）

根据2进制位来移
&  与
|  或
^  异或

结论：
1、位运算都是整形类型的数据
2、
	<< : 在一定范围内，每向左位移一位 * 2
	>> : 在一定范围内，每向右位移一位 / 2

*/
class WeiTest{
	public static void main(String[] args) {

		//位运算 50 * 2^4
		int num1 = 50;
		System.out.println("num1 << 4 = "+ (num1 << 4)); //结果800

		//位运算 50 / 2^4
		int num3 = 50;
		System.out.println("num3 >> 4 = "+ (num3 >> 4)); //结果3

		// 练习：用异或交换2个变量的值
		int y1 = 20;
		int y2 = 99;
		int y3 = y1 ^ y2;

		y1 = y1 ^ y3;
		y2 = y2 ^ y3;
		System.out.println("y1 = " + y1 + "\ty2 = " + y2);

		// 将一个小于256的数值，转成16进制
		// 因为 255的二进制为 1111 1111 ，8bit全是1
		// 求出 低位 与 高位 再将这高位 与 低位 转成16进制
		// 15 的 二进制为 1111
		int t1 = 255;
		int t2 = t1 & 15; //低位

		String t3 = t2 > 9 ? ((char)((t2 - 10)+'A'))+"" : t2 + "";
		System.out.println( "低位=" + t3 );

		int t4 = t1 >> 4;
		int t5 = t4 & 15;
		String t6 = t5 > 9 ? ((char)((t5-10)+'A'))+"": t5 + "";
		System.out.println( "高位=" + t6 );

		// 求一个数的异或值
		// 15的 二进制 1111
		// 1的  二进制 0001

		// 手动异或会上 1110
		// 8+4+2 = 14
		System.out.println("15 ^ 1 = " + (15 ^ 1) );

		int ab1 = 60;
		ab1 =~ab1;
		System.out.println("ab1=" + ab1 );

	}
	
}