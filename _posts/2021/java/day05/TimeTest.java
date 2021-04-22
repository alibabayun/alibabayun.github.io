class TimeTest{
	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		int count = 0;
		boolean isFlag = true;

		//求出 1-100000中所有的质数
		for (int i = 2; i<100000; i++) {
			isFlag = true;
			for (int j=2; j<=Math.sqrt(i); j++) {
				if (i % j == 0){
					isFlag = false;
					break;
				}
			}
			if (isFlag) {
				count++;
			}
		}
		long end   = System.currentTimeMillis();
		long time = end - start;
		
		System.out.println("得到的质数个数为：" + count);
		System.out.println("耗时：" +time);
	}
}