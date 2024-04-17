package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("test")
public class TestController {

	@GetMapping("jasp")
	public void getReport(@RequestParam("type") String reportType,HttpServletResponse response) throws Exception{
		HashMap<String,Object> parameters=new HashMap<String,Object>();
		parameters.put("name", "xiao ming");
		
		List<HashMap> l=new ArrayList<>();
		for(int i=0;i<100;i++) {
			HashMap<String,String> item=new HashMap<String,String>();
			item.put("Field1", "Field1-"+i);
			item.put("Field2", "Field2-"+i);
			l.add(item);
		}
		
	}
}
