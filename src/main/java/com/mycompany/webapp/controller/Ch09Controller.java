package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2

/*
 *  수업시간
 */

public class Ch09Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileupload(Ch09Dto dto) throws IllegalStateException, IOException {
		log.info("실행");
		log.info("title: " +dto.getTitle());
		log.info("desc: " + dto.getDesc());
		
		log.info("file originalname: " +dto.getAttach().getOriginalFilename());
		log.info("file contenttype: " + dto.getAttach().getContentType());
		log.info("file size: "+dto.getAttach().getSize()); //지금 다운로드 받으려는 파일의 사이즈를 제공할 수 있다.
		
		//파일의 순수 데이터(받은 파일을 DB에 저장할 때 둘 중에 하나 사용)
		//byte[] bytes = attach.getBytes();
		//InputStream is = attach.getInputStream();
		
		//받은 파일을 서버 파일 시스템에 저장할 때
//		String saveFilename = new Date().getTime()+"-"+dto.getAttach().getOriginalFilename();
//		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
//		dto.getAttach().transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileuploadAjax(String title, String desc, MultipartFile attach) throws IllegalStateException, IOException {
		log.info("실행");
		log.info("title: " +title);
		log.info("desc: " + desc);
		
		log.info("file originalname: " +attach.getOriginalFilename());
		log.info("file contenttype: " + attach.getContentType());
		log.info("file size: "+attach.getSize()); //지금 다운로드 받으려는 파일의 사이즈를 제공할 수 있다.
		
		//받은 파일을 서버 파일 시스템에 저장할 때
		String saveFilename = new Date().getTime()+"-"+attach.getOriginalFilename();
		File file = new File("C:/Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFilename", saveFilename);
		String json = jsonObject.toString();
		
		
		
		return json; 
	}
	
	@RequestMapping("/filedownload")
	//fileNo는 db에서 조건 검색할 때 필요한 매개변수로 지금은 사용하지 않는다.
	public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
		//DB에서 가져올 정보
		String contentType ="image/jepg";	//다운로드 하려는 파일의 타입
		String originalFilename = "유럽풍경.jpeg"; //다운로드 되는 파일 이름
		String saveFilename ="photo1";	//저장하려는 파일
		
		//응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType); //어떤 데이터 타입인지 브라우저에게 알려주어야 한다.
		//response.setHeader("Content-Type", contentType); 이렇게 사용해도 됨
		
		//다운로드할 파일명을 헤더에 추가
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) { //윈도우 10이하 msie Trident i11
			//IE 브라우저일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		}else {
			//크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		
		//파일 데이터를 응답 본문에 싣기
		File file = new File("C:/Temp/uploadfiles"+saveFilename);
		if(file.exists()) {
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
}
