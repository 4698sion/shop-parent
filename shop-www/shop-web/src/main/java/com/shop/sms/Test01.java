package com.shop.sms;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    //通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
		
    public static void main(String[] args) throws IOException {
    	//修改为您的apikey
        String apikey = "c4b8ae724295a17da12e0a1a3bad83cb";
        //修改为您要发送的手机号
        String mobile = "15216639531";
        
        //设置您要发送的内容 (内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = "【旅人救世剑】您的车牌号码是magnet:?xt=urn:btih:7A96FC59B4754EA6AFBE3F5A11066A4999D789DE";
        //发短信调用示例
        System.out.println(Test01.sendSms(apikey, text, mobile));
        
        
	}
    
    public static String sendSms(String apikey, String text, String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("apikey", apikey);
    	params.put("text", text);
    	params.put("mobile", mobile);
    	return post(URI_SEND_SMS, params);
    }
    
    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
            try {
                HttpPost method = new HttpPost(url);
                if (paramsMap != null) {
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                        paramList.add(pair);
                    }
                    method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
                }
                response = client.execute(method);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return responseText;
        }
}
