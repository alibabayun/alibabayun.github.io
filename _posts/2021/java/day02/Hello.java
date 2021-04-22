/**
第一个java程序
@author dsx
@version v1.0

不要急躁哈，慢慢积累
*/

/*
一、注释方式
	1、单行注释
		//这是一个注释
	2、多行注释
		/*
	3、文档注释
		/**

二、文档注释生成
	javadoc -d mydoc -author -version Hello.java
*/
public class Hello{

	/**
	这是入口文件，java文件一定要写的
	*/
	public static void main(String[] args)
	{
		System.out.println("Hello Word!");
	}

}