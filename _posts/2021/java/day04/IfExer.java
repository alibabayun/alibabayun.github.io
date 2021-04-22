
/*
练习：
	身高？
	多少钱？
	是否帅哥？

如果
	三都符合     马上嫁
	有一个符合   嫁吧，比上不足，比下有余
	都不符合     不嫁

*/
import java.util.Scanner;

class IfExer{

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("身高？ (cm)");
		int height = scan.nextInt();

		System.out.println("多少钱？（千万）");
		double wealth = scan.nextDouble();

		System.out.println("是否帅哥？(是/否)");
		String isHandsome = scan.next();

		if( height >= 180 && wealth>=1 && isHandsome.equals("是")){
			System.out.println("马上嫁");
		} else if ( height >= 180 || wealth>=1 || isHandsome.equals("是") ) {
			System.out.println("嫁吧，比上不足，比下有余");
		} else {
			System.out.println("不嫁");
		}
	}
}