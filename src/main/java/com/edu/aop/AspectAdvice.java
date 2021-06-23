package com.edu.aop;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 AOP기능 중 @Aspect 과 @ControllerAdvice로 구현됨.
 * @author 유지윤
 *
 */
@Component
@Aspect
@ControllerAdvice
public class AspectAdvice {
	private Logger logger = LoggerFactory.getLogger(AspectAdvice.class);
	@Inject
	private IF_BoardTypeService boardTypeService;
	
	//나중에 게시물관리 기능 만들 때 @Aspect로 AOP기능 추가 = 목적: board_type값을 항상 가져가도록 처리
	//세션? 서버-PC 구조상에서 클라이언트가 서버에 접속할 때 [서버에 발생되는 정보를 세션이라고 함.(서버에 저장됨)]
	//쿠키? 서버-PC 구조상에서 클라이언트가 서버에 접속할 때 클라이언트에 발생되는 정보를 쿠키라고 함.(PC에 저장됨)
	//옛날에는 쿠키로 로그인 체크를 했음. -> 보안상 PC에 로그인정보가 저장되기 때문에 위험(인터넷 광고에만 사용) -> 세션만 사용
	//Aspect로 AOP를 구현할 때는 포인트 컷(Advice참견이 실행될 위치)이 필요함.
	//@Around=@Before+After = @Around(포인트컷 전+후.*(...)모든메서드)
	//@Around는 콜백함수 매개변수로 조인포인트객체(포인트컷에서 입출력값)를 필수로 받음.
	@Around("execution(* com.edu.controller.*Controller.*(..))")
	public Object sessionManger(ProceedingJoinPoint pjp) throws Throwable{
		//board_type변수값을 세션에 저장하려고 함. 클라이언트별 세션이 발생됨.
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		
		//일반적인 컨트롤러에서는 매개변수 HttpServletRequest를 사용 가능 함. 위처럼 복잡하게 구하지 않음.
		//컨트롤러 클래스에서 매개변수로 받을 값 (board_type) > pageVO, boardVO
		PageVO pageVO = null;
		String board_type = null;//jsp에서 전송되는 값을 세션변수값이 임시로 저장,목적은 세션변수 발생조건으로 사용.
		String search_keyword = null; //한글검색시 IE에서 400에러 발생 때문에 추가
		//조인포인트 리스트의 객체들의 매서드의 Arguments매개변수를 뽑아냄.
		for(Object object:pjp.getArgs()) {
			if(object instanceof PageVO) {//AOP실행 메서드 중 매개변수 PageVO pageVO객체판단
				//결과는 게시판, 멤버서비스에 PageVO사용하는 서비스는 적용됨. 게시판생성관리에는 적용X
				pageVO = (PageVO) object;
				board_type = pageVO.getBoard_type();
				search_keyword = pageVO.getSearch_keyword();
				}
			if(object instanceof BoardVO) {
			}
		}
		if(request != null) {//jsp에서 Get,Post 있을 때, 
			//세션값을 PageVO.board_type 값으로 저장 로직(아래)
			HttpSession session = request.getSession();//PC가 스프링프로젝트 접근 시, 세션객체 생성.
			//검색폼이 있는 jsp에서 발생됨. 결과는 검색폼이 없는 입력/수정/삭제에서는 실행안됨.
			if(search_keyword != null) {//검색어가 발생하면 최초로 세션을 만듬.
				session.setAttribute("session_search_keyword", search_keyword);
			}
			if(session.getAttribute("session_search_keyword") != null) {
				//세션값이 있다면, 실행
				search_keyword = (String) session.getAttribute("session_search_keyword");
				if(pageVO != null) {//겟,셋 중에 Set할때 pageVO널이면 에러발생 하기 때문에 추가한 코드
					pageVO.setSearch_keyword(search_keyword);
					
				}
			}
			if(board_type != null) {//최초로 세션변수가 발생.
				session.setAttribute("session_board_type", board_type);
			}
			if(session.getAttribute("session_board_type") != null) {
				board_type = (String) session.getAttribute("session_board_type");
				if(pageVO != null) {//Set은 pageVO가 null아닐 경우만 실행되도록
				pageVO.setBoard_type(board_type);//목표달성 : 여기서 항상 값을 가져가도록 구현 됨.
				}
			}	
			logger.info("디버그19:"+(String) session.getAttribute("session_board_type"));
		}
		//Aspect > 포인트컷 > 조인포인트(메서드) > 매개변수로 구현
		Object result = pjp.proceed(); //여기서 조인포인트가 실행됨
		return result;
	}
	
	//이 메서드는 컨트롤러의 메서드가 실행 전에 값을 생성해서 model객체에 담아서 jsp로 자료를 전송.
	//위 @컨트롤러 어드바이스를 이용해서 컨트롤러의 모든 메서드가 실행되기 전에 호출만 되면 아래 메서드가 자동실행.(콜백 함수)
	@ModelAttribute("listBoardTypeVO")
	public List<BoardTypeVO> listBoardTypeVO() throws Exception {
		
		return boardTypeService.selectBoardType();
	}
}
