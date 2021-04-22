/*
练习
任意给一个3位数，求出 个位、十位、百位
*/
class AriExer{
	public static void main(String[] args) {
		int num = 1332;

		int ge   = num%10;
		int shi  = num%100/10;
		int bai  = num%1000/100;
		int qian = num/1000;

		System.out.println("个位="+ ge);
		System.out.println("十位="+ shi);
		System.out.println("百位="+ bai);
		System.out.println("千位="+ qian);
	}
}