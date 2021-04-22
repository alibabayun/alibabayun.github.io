
import java.util.Scanner;
class FUtil{
	private static Scanner Scanner = new Scanner(System.in);


	/*
	用户菜单页面的选择，如果是1-4方法返回，否则提示错误
	*/
	public static char readMenuSelection(){
		char c;
		for (; ; ) {
			String str = readKeyBoard(2);
			c = str.charAt(0);
			if (c != '1' && c!='2' && c!='3' && c!='4'){
				System.out.println("请输入1-4中的数");
			}else break;
		}
		return c;
	}

	/*
	监听是否退出
	*/
	public static char readExitSelect(){
		for (; ; ) {
			String str = readKeyBoard(1);
			char c = (str.toUpperCase()).charAt(0);
			if (c == 'Y' || c == 'N'){
				return c;
			}else{
				return c;
			}
		}
	}

	/*
	入账
	*/
	public static String entryAccount(){
		
		System.out.println("请输入金额：");
		double money  = Scanner.nextDouble();
		// totalMoney +=money;
		System.out.println("请输入备注：");
		String remark = Scanner.next();
		String str = 
			"入 账\t"+money+"\t"+remark+"\n"+
		"";
		return str;
	}

	/*
	出账
	*/
	public static String outAccount(){
		System.out.println("请输入金额：");
		double money  = Scanner.nextDouble();
		// totalMoney -=money;
		System.out.println("请输入备注：");
		String remark = Scanner.next();
		String str = 
			"出账\t"+money+"\t"+remark+"\n"+
		"";
		return str;
	}

	/*
	从键盘输入的值
	*/
	public static String readKeyBoard(int args){
		// System.out.println("a="+args);
		// Scanner scan = new Scanner();
		String str = Scanner.next();
		return str;
	}	
}