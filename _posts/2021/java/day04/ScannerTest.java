/*

从键盘输入一个成绩来做if else判断
1、导包

编写一个程序，键盘输入3个数据 ，用if-else 将三个数从大到小
输出
*/

import java.util.Scanner;


class ScannerTest{

	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		int num1, num2, num3;
		System.out.println("请输入第1个数：");
		num1 = scan.nextInt();
		System.out.println(num1);

		System.out.println("请输入第2个数：");
		num2 = scan.nextInt();
		System.out.println(num2);

		System.out.println("请输入第3个数：");
		num3 = scan.nextInt();
		System.out.println(num3);


		if (num1 > num2 && num2 > num3){
			System.out.println("num1->num2->num3 = "+ num1 + "\t" + "\t" + num2 + "\t" + num3);
		}else if(num1 > num2 && num2 < num3){
			System.out.println("num1->num3->num2 = "+ num1 + "\t" + "\t" + num3 + "\t" + num2);
		}

		if (num2 > num1 && num1 > num3){
			System.out.println("num2->num1->num3 = "+ num2 + "\t" + "\t" + num1 + "\t" + num3);
		}else if(num2 > num1 && num2 < num3){
			System.out.println("num2->num3->num1 = "+ num2 + "\t" + "\t" + num3 + "\t" + num1);
		}else if (num3 > num1 && num1 > num2){
			System.out.println("num3->num1->num2 = "+ num3 + "\t" + "\t" + num1 + "\t" + num2);
		}else if(num3 > num1 && num1 < num2){
			System.out.println("num3->num2->num1 = "+ num3 + "\t" + "\t" + num2 + "\t" + num1);
		}

		String s1 = "你好";
		char s2   = s1.charAt(0);
		System.out.println(s2);
	}
}