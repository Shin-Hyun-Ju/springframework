package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service //관리 객체로 만듦, controller에서 필드로 사용
public class Ch14MemberService {
	//중첩
	//ch14memberService를 사용할 때만 사욯하기 때문에 enum을 class 안에 선언
	//인스턴스 클래스 이런게 아니기 때문에 그냥 인식 시키면 된다.
	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED
	}
	
	public enum LoginResult{
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FAIL
	}
	
	
	
	@Resource
	private Ch14MemberDao memberDao;
	
	public JoinResult join(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if (dbMember == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			member.setMpassword(passwordEncoder.encode(member.getMpassword()));
			int result = memberDao.insert(member);
			return JoinResult.SUCCESS;
		} else {
			return JoinResult.DUPLICATED;
		}

	}

	public LoginResult login(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if(dbMember == null) {
			return LoginResult.FAIL_MID;
		}else {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if(passwordEncoder.matches(member.getMpassword(), dbMember.getMpassword())) {
				return LoginResult.SUCCESS;
			}else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}
	}
}
