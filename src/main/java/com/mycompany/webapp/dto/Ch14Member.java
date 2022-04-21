package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class Ch14Member {
	private String mid;
	private String mname;
	private String mpassword;
	private boolean menabled; //1 true , 0 false
	private String mrole;
	private String memail;
	
	//oracle에는 true와 false 를 데이터 타입으로 받지 않기 때문에 1과 0으로 받는다.
}
