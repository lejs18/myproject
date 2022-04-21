package com.example.myproject.controller.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.myproject.service.board.BoardService;
import com.example.myproject.util.UploadFileUtils;

@Controller
public class AjaxUploadController {

	@Inject
	BoardService boardService;
	
	@Resource(name = "upload_path") // servlet-context에 정의된 bean
	String upload_path;

	@RequestMapping("/upload/ajax_form")
	public String upload_form() {
		return "/upload/ajax_form";
	}

	// ResponseEntity 데이터 + http상태코드 리턴 
	@ResponseBody // 페이지로 포워드되지 않고 데이터를 리턴하는 경우 
	@RequestMapping(value = "/upload/ajax_upload", produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> ajax_upload(MultipartFile file) throws Exception {
		// 디렉토리 생성 및 파일 저장, 저장된 디렉토리 문자열이 리턴됨  
		String filename = UploadFileUtils.uploadFile(upload_path, file.getOriginalFilename(), file.getBytes());
		// 호출한 곳으로 파일 경로와 OK 코드 리턴 
		return new ResponseEntity<String>(filename, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping("/upload/display_file")
	public ResponseEntity<byte[]> display_file(String file_name) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		try {
			HttpHeaders headers = new HttpHeaders(); // http header 객체 
			in = new FileInputStream(file_name); // 파일 입력 스트림 생성 
			// uuid를 제외한 실제 파일 이름 
			// uuid_파일이름
			file_name = file_name.substring(file_name.indexOf("_") + 1);
			// 컨텐트 타입 지정
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			// 첨부파일 정보 
			headers.add("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
			// 첨부파일과 OK코드 리턴
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) { 
			e.printStackTrace();
			//에러가 발생할 경우 에러 코드 리턴 
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null) // 스트림 닫기 
				in.close();
		}
		return entity;
	}

	@ResponseBody
	@RequestMapping(value = "/upload/delete_file", method = RequestMethod.POST)
	public ResponseEntity<String> delete_file(String file_name) {
		//첨부 파일 삭제 
		// /2022/04/14/uuid_파일이름
		// 윈도우 \ , 리눅스 /
		new File(file_name.replace("/", File.separator)).delete();
		boardService.delete_attach(file_name); // 레코드 삭제 
		// 문자열과 OK코드 리턴 
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}












