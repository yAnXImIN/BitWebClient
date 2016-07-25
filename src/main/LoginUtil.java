package main;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;



public class LoginUtil {
	private final String LOGINURL = "http://10.0.0.55:801/srun_portal_pc.php?ac_id=1&";
	private String userName = "yanximin";
	private String password = "wanglei627";
	public String doLogin(){
		HttpClient httpClient = new HttpClient();
		Map<String, String> params = new HashMap<>();
		params.put("action", "login");
		params.put("username", "");
		params.put("password", "");
		params.put("ac_id", "1");
		params.put("user_ip", "");
		params.put("nas_ip", "");
		params.put("user_mac", "");
		params.put("save_me", "1");
		params.put("ajax", "1");
		return null;
	};
    
}
