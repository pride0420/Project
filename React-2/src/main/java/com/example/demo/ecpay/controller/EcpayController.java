package com.example.demo.ecpay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.ecpay.service.EcpayService;
import com.example.demo.service.Impl.ShopServiceImpl;
import com.example.demo.vo.Member;

import jakarta.servlet.http.HttpSession;

/**
 * 綠界ecpay的controller
 */
@CrossOrigin
@RestController
@RequestMapping("/shop")
public class EcpayController {

	@Autowired
	EcpayService ecpayService;
	
	@Autowired
	public ShopServiceImpl si;
	@PostMapping("/ecpayCheckout")
	
	public String ecpayCheckout(Integer memberId) {
		int x=si.querySum(memberId);
		String aioCheckOutALLForm = ecpayService.ecpayCheckout(x);
        return aioCheckOutALLForm;
	} 
}

