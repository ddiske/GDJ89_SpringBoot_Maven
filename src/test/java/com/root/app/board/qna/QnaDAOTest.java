package com.root.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Test
	void test() throws Exception {
		QnaVO qnaVO = new QnaVO();
		qnaVO.setUserName("user1");
		qnaVO.setBoardTitle("title1");
		qnaVO.setBoardContents("contents1");
		qnaDAO.add(qnaVO);
		qnaVO.setBoardRef(qnaVO.getBoardNum());
		qnaDAO.refUpdate(qnaVO);
	}

}
