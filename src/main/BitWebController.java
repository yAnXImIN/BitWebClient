package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class BitWebController {
	private final String LOGINURL = "http://10.0.0.55:801/srun_portal_pc.php?ac_id=1&";
	private Map<String, String> params;
	public BitWebController(){
		params = new HashMap<>();
		
		params.put("ac_id", "1");
		params.put("user_ip", "");
		params.put("nas_ip", "");
		params.put("user_mac", "");
		params.put("save_me", "1");
		params.put("ajax", "1");
	}
	public String doLogin(String username,String password) throws IOException{
		
		params.put("action", "login");
		params.put("username", username);
		params.put("password", password);
		if(sendPost().startsWith("login_ok")){
			return "登陆成功";
		}else{
			return "登陆失败";
		}
	}
	public String doLogout(String username,String password) throws IOException{
		params.put("action", "logout");
		params.put("username", username);
		params.put("password", password);
		sendPost();
		return "注销成功";
	}
	
	private String sendPost() throws IOException{
		String parameterData = changeMapToString(params);
		URL localUrl = new URL(LOGINURL);
		URLConnection connection = localUrl.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
		
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(parameterData.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        outputStream = httpURLConnection.getOutputStream();
        outputStreamWriter = new OutputStreamWriter(outputStream);
        
        outputStreamWriter.write(parameterData.toString());
        outputStreamWriter.flush();
        
        if (httpURLConnection.getResponseCode() >= 300) {
            return "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode();
        }
        
        inputStream = httpURLConnection.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);
        
        while ((tempLine = reader.readLine()) != null) {
            resultBuffer.append(tempLine);
        }
        if (outputStreamWriter != null) {
            outputStreamWriter.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
        if (reader != null) {
            reader.close();
        }
        
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        
        if (inputStream != null) {
            inputStream.close();
        }
        return resultBuffer.toString();
	}
	private String changeMapToString(Map<String, String> map){
		StringBuffer sb = new StringBuffer();
		for(String key:map.keySet()){
			sb.append(key+"="+map.get(key)+"&");
		}
		return sb.substring(0, sb.length()-1);
	}
}
