package com.root.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional//(rollbackFor = Exception.class)
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Test
	@Rollback(true)
	void test() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setUserName("user2");
		qnaVO.setBoardTitle("title2");
		qnaVO.setBoardContents("contents2");
		qnaDAO.add(qnaVO);
		qnaVO.setBoardRef(qnaVO.getBoardNum());
		qnaDAO.refUpdate(qnaVO);
	}

}
