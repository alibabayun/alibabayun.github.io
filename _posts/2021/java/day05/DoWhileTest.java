/*
do-while循环
结构
	1、初始化条件
	2、循环条件
	3、循环体
	4、迭代条件

示例
1
do{
	3
	4
}while(2)

*/
import java.util.Scanner;
class DoWhileTest{

	public static void main(String[] args) {
		// int i = 1;
		// do{

		// 	i++;
		// }while(i<=100);
		// System.out.println(i);

		/*demo测试
		用键盘输入2个正整数，找出最小公约数 和 最大公倍数
			什么是最大约数？
				2个数能同时被除尽的数
				最大是12
			什么是最最公倍倍数？
				者是2个数的倍数
			以上2个数据 合并，共同的就是公约娄或公倍数
		*/

		Scanner scan = new Scanner(System.in);
		System.out.println("请输入第一个数：");
		int num1 = scan.nextInt();

		System.out.println("请输入第二个数：");
		int num2 = scan.nextInt();

		int min = num1 < num2 ? num1: num2;
		int max = num1 > num2 ? num1: num2;

		int i = min;
		do{
			if ( (num1 % i) == 0 && (num2 % i) == 0) {
				System.out.println("最大公约数为："+i);
				break;
			}
			i--;
		}while(i>1);

		int i2 = max;
		do{
			if ( (i2%max == 0) && (i2%min == 0) ){
				System.out.println("最小公倍数为："+i2);
				break;
			}
			i2++;
		}while( i2 <= (max*min) );



	}
}