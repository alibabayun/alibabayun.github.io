class ForExer1{
	public static void main(String[] args) {
		//做一个9 9 乘法表
		/*
			1*1 = 1
			2*1 = 2, 2*2 = 4
			3*1 = 3, 2*3 = 6, 3*3 = 9
		*/
		// for (int i=1; i<=9; i++) {
		// 	for (int i2=1; i2<=i; i2++) {
		// 		System.out.print( i + " * " + i2 + " = " + (i*i2) + ",");
		// 	}
		// 	System.out.println();
		// }

/*
做一个棱形
----*
---* * 
--* * * 
-* * * * 
* * * * *
-* * * *
--* * *
---* *
----*
*/


		for (int i = 1; i<=4; i++) {
			for (int j=1; j<=(5-i); j++) {
				System.out.print(" ");
				
			}
			for (int k=1; k<=i; k++) {
				System.out.print(" *");
			}
			System.out.println();
		}
		System.out.println(" * * * * *");
		for (int i = 4; i>=1; i--) {
			for (int j=1; j<=(5-i); j++) {
				System.out.print(" ");
				
			}
			for (int k=1; k<=i; k++) {
				System.out.print(" *");
			}
			System.out.println();
		}

		//找出1-100中的质数
		// “质数又称素数。一个大于1的自然数，除了1和它自身外，
		// 不能被其他自然数整除的数叫做质数。最小的质数是2，
		// 它也是唯一的偶数质数。最前面的质数依次排列为：2，3，5，7，11等
		// 。比1大但不是质数的数称为合数。”
		boolean flag = true;
		for (int i=2; i<=100; i++) {
			flag = true;
			for (int j=1; j<=100; j++ ) {
				if( i%j == 0 && j!=1 && j!=i ){
					flag = false;
					break;
				}
			}
			//如果能
			if (flag) {
				System.out.println(i);
			}
		}
	}



/**
心形
   * *	   * *
  * * *   * * *
 * * * * * * * * 
* * * * * * * * *
* * * * * * * * *
* * * * * * * * *
  * * * * * * *
    * * * * *
     * * * *
       * * 
       	*
*/

}