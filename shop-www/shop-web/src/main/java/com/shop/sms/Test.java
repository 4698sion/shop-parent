package com.shop.sms;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;



public class Test {

    public static void main(String[] args) {
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23782541";
        String secret = "33f5fad8655b4c9f02ddec2159f5300b";
        

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "郁文景" );
        req.setSmsParamString( "{number:'123456'}" );
        req.setRecNum( "15216639531" );
        req.setSmsTemplateCode( "SMS_63970776" );
        AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
		} catch (ApiException e1) {
			System.out.println("error");
		}
        
    }
}
