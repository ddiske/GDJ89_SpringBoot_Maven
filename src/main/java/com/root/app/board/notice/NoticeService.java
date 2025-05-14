package com.root.app.board.notice;

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
import com.root.app.files.FileVO;
import com.root.app.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${menu.board.notice.name}")
	private String kind;
	
	@Value("${app.files.base}")
	private String path;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.make(noticeDAO.getTotalCount(pager));
		List<BoardVO> ar = noticeDAO.getList(pager);
		return ar;
	}

	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		boardVO = noticeDAO.getDetail(boardVO);
		return boardVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int add(BoardVO boardVO, MultipartFile [] multipartFiles) throws Exception {
		int result = noticeDAO.add(boardVO);
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
				
				result = noticeDAO.addFile(boardFileVO);
				
			}
			
			
			
		}
		
		
		return result;
	}

	@Override
	public BoardFileVO getFileDetail(BoardFileVO boardFileVO) throws Exception {
		return noticeDAO.getFileDetail(boardFileVO);
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		return noticeDAO.update(boardVO);
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		
		boardVO = noticeDAO.getDetail(boardVO);
		
		for(FileVO f : boardVO.getBoardFileVOs()) {
			String fileName = f.getFileName();
			fileManager.fileDelete(path.concat(kind), fileName);
		}
		
		noticeDAO.deleteFiles(boardVO);
		
		return noticeDAO.delete(boardVO);
	}

}
