/*
while循环

结构
	> 1.初始条件
	> 2.循环条件
	> 3.循环体
	> 4.迭代条件

1
while(2){
	3
	4
}

*/
class WhileTest{

	public static void main(String[] args){
		// 遍历100以内的偶数
		int i=100;
		while(i>0) {
			if (i%2 == 0) {
				System.out.println(i);
			}
			i--;
		}
	}
}