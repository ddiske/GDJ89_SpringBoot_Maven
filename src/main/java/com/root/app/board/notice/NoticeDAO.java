package com.root.app.board.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.root.app.board.BoardDAO;
import com.root.app.board.BoardVO;

@Mapper
public interface NoticeDAO extends BoardDAO {
	
	public int addAll(List<BoardVO> ar) throws Exception;

}
