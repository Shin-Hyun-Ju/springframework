package com.mycompany.webapp.view;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Ch12FileListView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.info("실행");
		
		
		// 파일의 총 개수 및 파일 이름 목록 얻기
		String fileDir = "C:/Temp/uploadFiles";
		File file = new File(fileDir); //저장 경로에 대한 파일 객체 생성
		String[] fileList = file.list(); //파일 내용 저장할 String list
		int totalFileNum = fileList.length; //파일 개수
		
		//JSON 응답을 생성하고 보내기
		response.setContentType("application/json; charset=UTF-8"); 
		
		//{"totalFileNum":20, "fileList":["a.jpg","b.jpg", ...]};
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("totalFileNum", totalFileNum);
		JSONArray jsonArray = new JSONArray();
		for(String fileName : fileList) {
			jsonArray.put(fileName);
		}
		jsonObject.put("fileList", jsonArray); //JSONArray이라는게 있었다니 ㅠㅠ 
		String json = jsonObject.toString();
		
		//문자열을 응답에 실어 보내기
		//응답 본문에 JSON문자열 싣기
		PrintWriter pw = response.getWriter(); //입력 본문에 들어가는 출력 스트림
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
}
