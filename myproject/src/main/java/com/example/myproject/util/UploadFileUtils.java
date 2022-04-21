package com.example.myproject.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	// 자료형... 파라미터들을 배열로 저장
	//makeDir( c:/upload, /2022, /2022/04 , /2022/04/22 )
	static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists()) { // 디렉토리가 존재하면
			return; 
		}
		for (String path : paths) {
			// c:/upload/2022
			// c:/upload/2022/04
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) { //존재하지 않으면
				dirPath.mkdir(); //디렉토리 생성
			}
		}
	}

	static String calcPath(String upload_path) {
		// c:/upload/2022/04/14/파일이름
		
		
		Calendar cal = Calendar.getInstance(); //캘린더 객체 생성 
		String year = "/" + cal.get(Calendar.YEAR); //   /2022
		// 월은 +1을 해야 함, 7월=>07월 2자리수로 설정
		//  /2022/04
		String month = year + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		//  /2022/04/22
		String path = month + "/" + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		// makeDir( c:/upload, /2022, /2022/04 , /2022/04/22 )
		makeDir(upload_path, year, month, path);
		return path;
	}

	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID(); // uuid 생성 
		// 파일이름이 중복되지 않도록 uuid를 붙임 
		String filename = uid.toString() + "_" + originalName;
		String path = calcPath(uploadPath);
		File target = new File(uploadPath + path, filename);
		FileCopyUtils.copy(fileData, target); //지정된 디렉토리에 파일 복사 
		String str = uploadPath + path + "/" + filename;
		return str;
	}
}
