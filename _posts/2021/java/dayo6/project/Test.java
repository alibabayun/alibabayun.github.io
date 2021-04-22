
class Test{

	public static void main(String[] args) {
		/*
		############ 记录软件 ############
		请选择你要操作的功能
		1、明细
		2、入账
		3、出账
		4、退出
		*/
		
		boolean isExit = false;
		String entryAccount = "";
		String outAccount   = "";
		while (true) {
			System.out.println(
				"############ 记录软件 ############\n\n"+
				"       请选择你要操作的功能?\n"+
				"        	  1、明 细\n"+
				"         	  2、入 账\n"+
				"        	  3、出 账\n"+
				"        	  4、退 出\n\n"+
				"       (请输入1-4选择：)\n"+
				"");

			char word = FUtil.readMenuSelection();
			switch (word) {
				case '1':
					System.out.println(
						"================ 记账本 ================\n"+
						"类 型\t金 额\t备 注\n"+
						entryAccount+""+
						outAccount+
					    "=======================================");
					break;	
				case '2':
					String str1  = FUtil.entryAccount();//入账
					entryAccount = str1 + "" + entryAccount;
					break;	
				case '3':
					String str2  = FUtil.outAccount();//出账
					outAccount   = str2 + "" + outAccount;
					break;	
				case '4':
					for (; ; ) {
						System.out.println("是否要退出(Y/N)?");
						char e = FUtil.readExitSelect();
						if (e == 'Y') {
							isExit = true;
							break;
						}else if(e == 'N'){
							break;
						}else {
							System.out.println("  ^-^ 没有这个选项，请重新选择\n");
						}
					}
					
					break;	
			}
			//退出程序
			if (isExit) {
				break;
			}
		}
	}
}