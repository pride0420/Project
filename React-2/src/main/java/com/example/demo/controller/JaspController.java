package com.example.demo.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.JaspServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@CrossOrigin
@RestController
@RequestMapping("jasp")
public class JaspController {
	
	@Autowired
	public JaspServiceImpl jasp;
	
	@PostMapping("/generateReport")
	public void generateReport(@RequestParam("format") String format, JasperPrint jasperPrint, HttpServletResponse response) throws SQLException, IOException {
	    byte[] reportBytes;
	    try {
	        reportBytes = jasp.generateReport(format);
	        // 设置响应头，指定内容类型为对应的格式
	        if ("pdf".equalsIgnoreCase(format)) {
	        	//設置內容類型
	            response.setContentType("application/pdf");
	            
	            //設置標頭信息
	            response.setHeader("Content-Disposition", "inline; filename=report.pdf");
	            
	            //將內容寫進輸出流中
	            response.getOutputStream().write(reportBytes);
	            
	            //確保報表的完整內容被正確地發送給客戶端
	            response.getOutputStream().flush();
	            
	        } else if ("xlsx".equalsIgnoreCase(format)) {
	            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	            response.setHeader("Content-Disposition", "inline; filename=report.xlsx");
	            response.getOutputStream().write(reportBytes);
	            response.getOutputStream().flush();
	        } else {
	            throw new IllegalArgumentException("Unsupported format: " + format);
	        }
	        // 设置响应内容长度
	        response.setContentLength(reportBytes.length);
	    } catch (SQLException | JRException | IOException e) {
	        e.printStackTrace();
	    }
	}
	
	}

