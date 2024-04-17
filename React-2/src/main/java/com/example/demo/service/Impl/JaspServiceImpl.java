package com.example.demo.service.Impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
@Service
public class JaspServiceImpl {
	

    @Autowired
    private DataSource dataSource;
    
    public byte[] generateReport(String format) throws SQLException, JRException, IOException {
    			
    		//連接資料庫
        try (Connection connection = dataSource.getConnection()) {
        	
        	//獲取路徑下的jasperReport模板
        	//getResourceAsStream獲取路徑資源流
        	//getClassLoader加載該資源
        	//getClass獲取類型
        	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jasper/Blank_A4.jasper");
        	
        	//將獲取的jasperReport模板 轉化為jasperReport對象
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
	        
	        //傳遞報表參數的 Map 對象。報表參數可以用於動態生成報表
            Map<String, Object> params = new HashMap<>();
            
            //填充之前加載的 JasperReport 模板，生成了 JasperPrint 對象。
            //JasperPrint 對象包含了已填充的報表數據，可以用於導出報表。
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);
            
            // 导出报表为字节数组
            return exportReportToBytes(jasperPrint,format);
        }
    }

    private byte[] exportReportToBytes(JasperPrint jasperPrint, String format) throws JRException, IOException {
    	
    	//將導出的報表數據寫入內存中的字節數組
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        if ("pdf".equalsIgnoreCase(format)) {
        	//將資料作為pdf檔導出給outputSteam
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            
        } else if ("xlsx".equalsIgnoreCase(format)) {
        	//創建一個xslx對象
            JRXlsxExporter exporter = new JRXlsxExporter();
            
            //將準備好的jasperPrint寫入
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            
            //將寫好的xlsx輸出給outputStream
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            
            //導出報表
            exporter.exportReport();
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }
        
        return outputStream.toByteArray();
    }
   }
		
		


