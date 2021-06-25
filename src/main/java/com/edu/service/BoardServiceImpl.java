package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.IF_BoardDAO;
import com.edu.dao.IF_ReplyDAO;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 DAO메서드를 호출하는 기능을 함.
 * @author 유지윤
 *
 */
@Service //@애노테이션을 붙이면 스프링 빈으로 등록이 됨.
public class BoardServiceImpl implements IF_BoardService {
	@Inject
	private IF_BoardDAO boardDAO;
	@Inject
	private IF_ReplyDAO replyDAO;
	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// TODO 첨부파일 List형으로 조회 DAO호출
		return boardDAO.readAttach(bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// TODO 페이징 처리시 PageVO의 totalCount변수
		return boardDAO.countBoard(pageVO);
	}
	@Transactional //All or NotAll
	@Override
	public void deleteBoard(int bno) throws Exception {
		// TODO 게시물 삭제 할때, 3개의 메서드가 실행(댓글+첨부파일삭제 ->게시물이 삭제됨)
		// 트랜잭션이 필요한 순간, 작업순서1. 첨부파일 삭제. 2.게시물 삭제 에러. 삭제 안됨.
		// 위와 같은 상황을 방지하는 목적의 @Transantional 애노테이션.
		//댓글 삭제도 *나중에 추가.
		//특이사항: 첨부파일DB만 삭제해서 해결 + 실제 업로드된 파일을 삭제가 필요. *나중에 추가
		boardDAO.deleteAttachAll(bno);
		//댓글 DAO에서 deleteReplyAll실행
		replyDAO.deleteReplyAll(bno);
		boardDAO.deleteBoard(bno);
	}
	@Transactional //All or NotAll
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// 첨부파일 있으면 updateAttach -> 게시물 업데이트 updateBoard
		boardDAO.updateBoard(boardVO);
		//첨부파일DB처리(아래)
		int bno = boardVO.getBno();
		String[] save_file_names = boardVO.getSave_file_names();
		String[] real_file_names = boardVO.getReal_file_names();
		if(save_file_names == null) {return;}//조건이 맞지 않으면 빠져나감. 이후 실행안함.
		AttachVO attachVO = new AttachVO();
		int index = 0;
		String real_file_name = "";
		for(String save_file_name:save_file_names) {
			if(save_file_name != null) {//컨트롤러에서 null이 들어갈 수 있는 로직이라서 추가
				real_file_name = real_file_names[index];
				attachVO.setBno(bno);
				attachVO.setSave_file_name(save_file_name);
				attachVO.setReal_file_name(real_file_name);
				boardDAO.updateAttach(attachVO);
			}
		index = index + 1;//index++;
		}
	}
	@Transactional //All or NotAll
	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// TODO 게시물 상세보기시 실행순서 readBoard + updateViewCount 2개의 메서드가 필요
		boardDAO.updateViewCount(bno);
		BoardVO boardVO = boardDAO.readBoard(bno);
		return boardVO;
	}

	@Transactional //All or NotAll
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// TODO 부모 게시물 insertBoard -> [자식] 첨부파일 있으면  insertAttach 실행
		//게시물 등록+반환값으로 bno 추가
		int bno = boardDAO.insertBoard(boardVO);
		//첨부파일 등록: 1개 이상일때 가정해서 처리
		String[] save_file_names=boardVO.getSave_file_names();//폴더에 저장용 파일명들
		String[] real_file_names=boardVO.getReal_file_names();//UI용 배열 파일명들
		if(save_file_names == null) {return;}//리턴이 발생되면, 이후 실행 안됨.
		//첨부파일이 null이 아닐때 아래가 진행됨.
		int index = 0;
		String real_file_name = "";//UI용 1개 파일명
		AttachVO attachVO = new AttachVO();
		for(String save_file_name:save_file_names) {//첨부파일 갯수 만큼 반복진행.
			if(save_file_name != null) {
				real_file_name = real_file_names[index];	
				attachVO.setBno(bno);
				attachVO.setReal_file_name(real_file_name);
				attachVO.setSave_file_name(save_file_name);
				boardDAO.insertAttach(attachVO);
			}
			index++;//index = index + 1;
		}
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// TODO DAO 1개 호출하면 됨.
		return boardDAO.selectBoard(pageVO);
	}

}
