/*
for循环
组成
1、初始化条件
2、循环条件
3、循环体
4、迭代条件

流程
1-2-3-4 ---> 2-3-4 ----> 2-3-4 

*/
class ForExer{
	public static void main(String[] args) {
		int a = 3;
		for ( int i = 1; i<=3; ++i ) {
			System.out.println( "num\t=\t"+  i );
		}

		int num = 1;
		for ( System.out.print('e');num<=3;System.out.print('g'),num++ ) {
			System.out.print('f');
		}
		System.out.println();
		//结果： efgfgfg

		/* 举例1：
			遍历100以内的偶数
			输出所有偶数的和
		*/
		int num2 = 0;
		for (int a1=1; a1<=100 ; a1++ ) {
			if (a1 % 2 == 0) {
				num2 += a1;
				System.out.println(a1);
			}
		}
		System.out.println("偶数的和=\t"+num2);

		/* 举例2：
			从1-150中
				每行打出循环编号
				3的倍数 输出foo
				5的倍数 输出biz
				7的倍数 输出baz
			如果是3 5 7的倍数都输出一行
		*/
		for ( int i=1; i<=150 ; i++ ) {
			System.out.print(i);
			if (i%3 == 0) {
				System.out.print("\t"+"foo");
			}
			if (i%5 == 0) {
				System.out.print("\t"+"biz");
			}
			if (i%7 == 0) {
				System.out.print("\t"+"baz");
			}
			System.out.println();
		}

	}
}