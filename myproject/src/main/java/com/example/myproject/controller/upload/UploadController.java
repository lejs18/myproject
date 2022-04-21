package com.example.myproject.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	@Resource(name="upload_path")	// servlet-context.xml에 선언한 bean과 연결됨 
	String upload_path;
	
	@RequestMapping("/upload/input.do")
	public String input() {
		return "upload/input"; // views/upload/input.jsp로 이동
	}
	// 첨부파일이 MultipartFile에 저장됨 
	@RequestMapping("/upload/upload.do")
	public ModelAndView upload(MultipartFile file, ModelAndView mav) throws Exception {
		String savedName=file.getOriginalFilename(); //파일이름 
		savedName=uploadFile(savedName, file.getBytes());   // uuid를 붙인 파일 이름 
		mav.setViewName("upload/upload_result"); // upload_result.jsp 
		mav.addObject("saved_name", savedName); // 페이지에 전달할 변수명
		return mav;
	}
	
	public String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid=UUID.randomUUID(); // Universal Unique IDentifier 범용 고유 식별자
		String savedName=uid.toString()+"_"+originalName; // 파일 이름 중복을 방지하기 위한 코드 
		File target=new File(upload_path, savedName); // 파일참조변수 
		FileCopyUtils.copy(fileData, target); // 임시디렉토리에 저장된 첨부파일을 지정된 디렉토리(c:/upload)로 복사 
		return savedName;
	}
}
