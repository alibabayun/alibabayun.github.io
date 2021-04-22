/*
swith后面使用的表达式可以是哪些数据类型的
	byte short char int 枚举 String

*/

import java.util.Scanner;

class SwithExer{
	public static void main(String[] args) {
		// 示例1
		// default 是可以写在上面的
		// 但要注意的是，如果后面没有加break的话，会继续执行后面的语句，走到出现break才会停止
		int a = 13;
		switch (a) {
			default:
				System.out.println("def");
			case 11:
				System.out.println("11");
				break;
			case 10:
				System.out.println("10");
				break;
		}
		// 示例2
		// 找出某年的某月的第几天，是当年的第多少天
		Scanner scan = new Scanner(System.in);

		System.out.println("请输入year?");
		int year  = scan.nextInt();

		System.out.println("请输入month?");
		int month = scan.nextInt();

		System.out.println("请输入day?");
		int day = scan.nextInt();
		int sumDay = 0;
		switch (month) {
			case 12://
				sumDay +=31;
			case 11:
				sumDay +=30;
			case 10://
				sumDay +=31;
			case 9:
				sumDay +=30;
			case 8://
				sumDay +=31;
			case 7://
				sumDay +=31;
			case 6:
				sumDay +=30;
			case 5://
				sumDay +=31;
			case 4:
				sumDay +=30;
			case 3://
				sumDay +=31;
			case 2:
				// 2月份bai有29天的那一年叫闰年
				// 普通年:能被du4整除zhi但不dao能被100整除的年份为普通闰年。（如2004年就zhuan是闰shu年，1999年不是闰年）；
				// 世纪年:能被400整除的为世纪闰年。（如2000年是闰年，1900年不是闰年）
				sumDay += ( (year%4 == 0 && year%100 != 0) || (year % 400 == 0) ) ? 29 : 28;
			case 1:
				sumDay +=31;
		}

		System.out.println( year+"年"+month+"月"+day+"日 是当年的第"+sumDay+"天");
	}
}