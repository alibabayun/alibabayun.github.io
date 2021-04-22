/*
监控键盘输入的正整数或负数，当为0时程序结束
*/

import java.util.Scanner;
class DoWhileExer{
	public static void main(String[] args) {
		
		System.out.println("请输入数值：");
		Scanner scan = new Scanner(System.in);
		int val;
		int znum = 0,fnum=0;
		do{
			val = scan.nextInt();
			if (val < 0) {
				++fnum;
			}
			if (val > 0) {
				++znum;
			}
			System.out.println("当前输入的值为："+val);
		}while(val != 0);
		System.out.println("输入的正数个数为："+znum);
		System.out.println("输入的负数个数为："+fnum);
	}
}