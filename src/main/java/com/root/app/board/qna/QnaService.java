package com.root.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.root.app.board.BoardFileVO;
import com.root.app.board.BoardService;
import com.root.app.board.BoardVO;
import com.root.app.files.FileManager;
import com.root.app.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${menu.board.qna.name}")
	private String kind;
	
	@Value("${app.files.base}")
	private String path;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.make(qnaDAO.getTotalCount(pager));
		List<BoardVO> ar = qnaDAO.getList(pager);
		return ar;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		boardVO = qnaDAO.getDetail(boardVO);
		return boardVO;
	}

	@Override
	public int add(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception {
		int result = qnaDAO.add(boardVO);
		if(multipartFiles != null) {
			// 파일을 HDD에 저장
			for(MultipartFile f : multipartFiles) {
				if(f.isEmpty()) {
					continue;
				}
				String fileName = fileManager.fileSave(path.concat(kind), f);
				
				// 저장된 파일명을 DB에 저장
				BoardFileVO boardFileVO = new BoardFileVO();
				boardFileVO.setFileName(fileName);
				boardFileVO.setOldName(f.getOriginalFilename());
				boardFileVO.setBoardNum(boardVO.getBoardNum());
				
				qnaDAO.addFile(boardFileVO);
			}
			
			
			
		}
		
		
		return result;
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return qnaDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		return qnaDAO.update(boardVO);
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return qnaDAO.delete(boardVO);
	}

}
