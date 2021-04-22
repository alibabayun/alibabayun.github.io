/*
求出3位数的水仙数数
水仙花数又称阿姆斯特朗数。

999 求出第一位

*/
class ForTest{
	public static void main(String[] args) {
		int ge = 0, shi = 0, bai = 0;
		for (int i=100; i<1000; i++) {
			ge  = i%10;
			shi = i%100/10;
			bai = i/100;
			if(i == ge*ge*ge + shi*shi*shi + bai*bai*bai){
				System.out.println("3的水仙花数是有："+i);
			}
		}
	}
}