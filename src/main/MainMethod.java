package main;

import java.io.IOException;

public class MainMethod {
	public static void main(String[] args) throws IOException {
		if(args.length<3){
			System.out.println("感谢使用");
			System.out.println("请使用命令行启动");
			System.out.println("格式如：java -jar BitWebClient.jar <login|logout> <username> <password>");
			System.out.println("GitHub地址：https://github.com/yAnXImIN/BitWebClient");
			return;
		}
		String method = args[0];
		String username = args[1];
		String password = args[2];
		BitWebController b = new BitWebController();
		String result = null;
		if(method.equals("login")){
			result = b.doLogin(username, password);
		}
		if(method.equals("logout")){
			result = b.doLogout(username, password);
		}
		System.out.println(result);
	}
}
