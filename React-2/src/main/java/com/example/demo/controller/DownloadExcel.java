package com.example.demo.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.Impl.MemberServiceImpl;
import com.example.demo.vo.Member;

@CrossOrigin
@RestController
@RequestMapping("download")
public class DownloadExcel {
	
	@Autowired
	private MemberServiceImpl ms;

	@GetMapping("/downloadExcel")
    public ResponseEntity<Resource> downloadExcel() throws IOException {
		
		List<Member> data=ms.queryAllMember();
		
		//建立一個新的 Excel 檔案
		Workbook workbook=new XSSFWorkbook();
		//在該 Excel 檔案中創建一個名為 "帳號資料" 的工作表
		Sheet sheet=workbook.createSheet("帳號資料");
		
		//在工作表中創建一行，並設置列標題
		Row header=sheet.createRow(0);
		header.createCell(0).setCellValue("id");
		header.createCell(1).setCellValue("帳號");
		header.createCell(2).setCellValue("密碼");
		header.createCell(3).setCellValue("暱稱");
		header.createCell(4).setCellValue("電話");
		header.createCell(5).setCellValue("信箱");
		
		
		int rowNum=1;
		//將資料存入excel
		for(Member o:data) {
			Row row=sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(o.getMemberId());
			row.createCell(1).setCellValue(o.getUsername());
			row.createCell(2).setCellValue(o.getPassword());
			row.createCell(3).setCellValue(o.getName());
			row.createCell(4).setCellValue(o.getPhone());
			row.createCell(5).setCellValue(o.getEmail());
		}
		//在系統的臨時目錄中創建一個臨時的 Excel 檔案
		Path tempFile=Files.createTempFile("帳號資料",".xlsx");
		//初始化創建的臨時檔
		FileOutputStream fileOut=new FileOutputStream(tempFile.toFile());
		//將剛剛建立的 Excel 檔案寫入到剛創建的臨時檔案中
		workbook.write(fileOut);
		fileOut.close();
		
		//創建一個檔案輸入流(File) 將內容讀取為字結流(to.file)  讀取輸入流的資料(input) 並包裝成物件 給後續使用
		InputStreamResource resource=new InputStreamResource(new FileInputStream(tempFile.toFile()));
		//設置下載檔案的相關 HTTP 標頭，包括 Content-Disposition 和 Content-Type
		HttpHeaders headers=new HttpHeaders();
		//設置標頭
		//設置如何處理想印內容(CONTENT_DISPOSITION) attachment為下載 檔名為data.xlsx
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xlsx");
		
		//將準備好的 InputStreamResource 作為 ResponseEntity 的內容返回給用戶端，這樣用戶端就可以下載該 Excel 檔案
		return ResponseEntity.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(resource);
	}
}
