package com.mycompany.webapp.dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class Ch14Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private String bhitcount;
	//MultipartFile을 가지고 3개 필드 셋팅
	private String battachoname;
	private String battachsname;
	private String battachtype;
	//클라이언트가 파일을 보낼 때, 서버에서는 MultipartFile로 받아야 한다.
	//boardWriteForm 에서 type="file"에서 얻음
	private MultipartFile battach;
}
