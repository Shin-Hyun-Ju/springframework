package com.mycompany.webapp.dao.mybatis;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Ch14Member;

@Repository //클래스로 만들 때는 객체로 관리해주어야 하기 때문에 Repository를 붙여주어야 한다.
public class Ch14MemberDao {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(Ch14Member member) {
		int rows = sqlSessionTemplate.insert("com.mycompany.webapp.dao.mybatis.Ch14MemberDao.insert",member);
		return rows;
	}
	public Ch14Member selectByMid(String mid) {
		Ch14Member ch14Member = sqlSessionTemplate.selectOne("com.mycompany.webapp.dao.mybatis.Ch14MemberDao.selectByMid",mid);
		return ch14Member;
	}
}
