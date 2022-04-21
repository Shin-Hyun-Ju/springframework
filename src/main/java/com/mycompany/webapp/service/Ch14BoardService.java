package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Pager;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch14BoardService {
	
	@Resource
	private Ch14BoardDao boardDao; //mybatis에 자동으로 객체 생성되게 조치 해놨음.

	public int getTotalBoardNum() {
		return boardDao.count();
	}

	public List<Ch14Board> getBoards(Pager pager) {
		// TODO Auto-generated method stub
		return boardDao.selectByPage(pager);
	}

	public void writeBoard(Ch14Board board) {
		boardDao.insert(board); //리턴되는 숫자는 게시물의 개수!!
		log.info("저장된 게시물 번호: "+board.getBno());
	}

	public Ch14Board getBoard(int bno) {
		return boardDao.selectByBno(bno);
	}

	public void updateBoard(Ch14Board board) {
		// TODO Auto-generated method stub
		boardDao.update(board);
	}

	public void removeBoard(int bno) {
		// TODO Auto-generated method stub
		boardDao.deleteByBno(bno);
	}
	
	
}

