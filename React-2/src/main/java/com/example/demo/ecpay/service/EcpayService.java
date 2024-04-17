package com.example.demo.ecpay.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.ecpay.payment.integration.AllInOne;
import com.example.demo.ecpay.payment.integration.domain.AioCheckOutALL;
@Service
public class EcpayService {
	
	
public String ecpayCheckout(int toto) {
	String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
	 

     AllInOne all = new AllInOne("");
    String amount= Integer.toString(toto);;
		
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);
		obj.setMerchantTradeDate("2017/01/01 08:05:23");
		obj.setTotalAmount(amount);
		obj.setTradeDesc("test Description");
		obj.setItemName("TestItem");
	    // 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok	
        obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
        // 商店轉跳網址 (Optional)
        //obj.setClientBackURL("http://192.168.1.37:8080/");
		String form = all.aioCheckOut(obj, null);
		
		return form;
	}
}
