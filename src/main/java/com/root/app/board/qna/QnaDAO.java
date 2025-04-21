package com.root.app.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.root.app.board.BoardDAO;
import com.root.app.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {
	
	public int addAll(List<BoardVO> ar) throws Exception;
	
	public int refUpdate(QnaVO qnaVO) throws Exception;

}
