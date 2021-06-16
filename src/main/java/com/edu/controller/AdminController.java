package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.service.IF_BoardTypeService;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin관리자단을 접근하는 컨트롤러 클래스 <- 디스패처 서블렛(게이트웨이) 기능을 함.
 * 디스패처 서블렛 클래스는 톰캣이 실행될 때 제일 먼저 실행되는 클래스. 그래서, 게이트웨이 라고 함.
 * 변수 Object를 만들어서 jsp로 전송 <-> jsp 폼값을 받아서 Object로 처리
 * 디스패처 서블릿 실행될 때, 컨트롤러의 Request매퍼경로를 다 등록함.
 * @author 유지윤
 *
 */
@Controller
public class AdminController {
	//컨트롤러수정하면 자동로딩(auto컴파일)
	//디버그용 로그객체 생성(아래)
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	//이 메서드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
	@Inject
	private IF_MemberService memberService;
	@Inject
	private IF_BoardTypeService boardTypeService;
	
	//jsp에서 게시판 생성관리에 Get/Post 접근할 때 URL을 bbs_type으로 지정함.
	//왜 board_type 하지 않고, bbs_type하는 이유는 왼쪽 메뉴 고정시키는 로직에서 경로 board와 겹치지 않도록
	@RequestMapping(value="/admin/bbs_type/bbs_type_list", method=RequestMethod.GET)
	public String selectBoardTypeList(Model model) throws Exception {//목록폼1
		model.addAttribute("listBoardTypeVO",boardTypeService.selectBoardType());
		return "admin/bbs_type/bbs_type_list";//상대경로일 때 views폴더가 최상위 root(최상위)
	}
	//bbs_type_list.jsp에서 게시판생성 버틍르 클릭했을 때 이동하는 폼 경로
	@RequestMapping(value="/admin/bbs_type/bbs_type_insert", method=RequestMethod.GET)
	public String insertBoardTypeForm() throws Exception{//입력폼1
		return "admin/bbs_type/bbs_type_insert";//.jsp생략
	}
	//bbs_type_insert.jsp의 입력폼에서 전송된 값을 boardTypeVO 자동으로 담겨서{구현} 단, 자동으로 바인딩 되려면 폼 name과, VO 멤버변수명 동일해야 함.
	@RequestMapping(value="admin/bbs_type/bbs_type_insert", method=RequestMethod.POST)
	public String insertBoardType(BoardTypeVO boardTypeVO) throws Exception {//입력처리1
		boardTypeService.insertBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_list";//리다이렉트는(뒤로가기 데이터 사라짐) 절대경로 forward:이동이 가능(뒤로가기 데이터가 살아있음)
		//쇼핑몰에서 결제화면을 처리 후 뒤로가기를 누르면, 리다이렉트는 데이터가 사라지기 때문에 재결제 불가
		//forward로 결제화면을 처리 후 뒤로가기를 누르면, 재결제가 발생됨. 이러면 안되기 때문에 사용안함. 
	}
	//게시판 생성관리는 이 기능은 사용자단에서 UI르 사용할 일이 없기 때문에, Read, Update를 1개로 사용
	@RequestMapping(value="/admin/bbs_type/bbs_type_update", method=RequestMethod.GET)
	public String updateBoardTypeForm(@RequestParam("board_type")String board_type, Model model) throws Exception {//수정폼1
		model.addAttribute("boardTypeVO", boardTypeService.readBoardType(board_type));
		//서식model.add~("jsp변수로 담아서 view화면으로 보냄.","서비스에서 쿼리 실행한 데이터객체");
		return "admin/bbs_type/bbs_type_update";//.jsp생략
	}
	@RequestMapping(value="admin/bbs_type/bbs_type_update", method=RequestMethod.POST)
	public String updateBoardType(BoardTypeVO boardTypeVO) throws Exception {//수정처리1
		boardTypeService.updateBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_update?board_type="+boardTypeVO.getBoard_type();//수정한 이후 수정폼을 GET방식으로 이동
	}
	@RequestMapping(value="admin/bbs_type/bbs_type_delete", method=RequestMethod.POST)
	public String deleteBoardType(@RequestParam("board_type")String board_type) throws Exception {//삭제처리1
		boardTypeService.deleteBoardType(board_type);//삭제서비스 호출(실행) 끝
		return "redirect:/admin/bbs_type/bbs_type_list";//.jsp생략
	}
	//아래 경로는 회원신규등록 폼을 호출하는 URL 쿼리스트링으로 보낸 것을 받을 때는 GET방식으로 받음.
	@RequestMapping(value="/admin/member/member_insert_form", method=RequestMethod.GET)
	public String insertMemberForm(@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		
		return "admin/member/member_insert";//.jsp는 생략
	}
	//아래 경로는 회원신규등록을 처리하는 서비스를 ㅗ출하는 URL
	@RequestMapping(value="admin/member/member_insert", method=RequestMethod.POST)
	public String insertMember(PageVO pageVO, MemberVO memberVO) throws Exception{
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = memberVO.getUser_pw();//원시 패스워드값
		String encPassword = passwordEncoder.encode(rawPassword);
		memberService.insertMember(memberVO);
		return "redirect:/admin/member/member_list";//.jsp는 생략.
	}
	//아래 경로는 수정처리를 호출=DB를 변경처리함.
	@RequestMapping(value="/admin/member/member_update", method=RequestMethod.POST)
	public String updateMember(MemberVO memberVO,PageVO pageVO) throws Exception {
		//update 서비스만 처리하면 끝
		//업데이트 쿼리 서비스 호출하기 전 스프링시큐리티 암호화 적용함.
		String rawPassword = memberVO.getUser_pw();
		if(!rawPassword.isEmpty()) {//수정폼에서 암호 입력값이 비어있지 않을 때만 아래 로직 실행.
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encPassword = passwordEncoder.encode(rawPassword);
		memberVO.setUser_pw(encPassword);
		}
		memberService.updateMember(memberVO);//반환값이 없음.
		//redurect로 페이지를 이동하면, model로 담아서 보낼 수 없음. 쿼리스트링(URL?)으로 보냄.
		String queryString = "user_id="+memberVO.getUser_id()+"&page="+pageVO.getPage()
		+"&search_type="+pageVO.getSearch_type()
		+"&search_keyword="+pageVO.getSearch_keyword();
		return "redirect:/admin/member/member_update_form?"+queryString;//
	}
	//아래 경로는 수정폼을 호출=화면에 출력만=렌더링만
	@RequestMapping(value="/admin/member/member_update_form"
			, method=RequestMethod.GET)
	public String updateMemberForm(MemberVO memberVO, Model model,@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		//이 메서드는 수정폼에 pageVO, memberVO 2개의 데이터객체를 jsp로 보낸다.
		//사용자1명의 레코드를 가져오는 멤버서비스(쿼리)를 실행(아래)
		MemberVO memberView = memberService.readMember(memberVO.getUser_id());
		//사용자1명의 레코드를 model에 담아서 + @ModelAttribute에 담아서 jsp로 보낸다.
		model.addAttribute("memberVO",memberView);
		return "admin/member/member_update";//상대경로
	}
	@RequestMapping(value="/admin/member/member_delete", method=RequestMethod.POST)
	public String deleteMember(MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO는 클래스형 변수: String user_id 스트링형 변수 같은 방식
		String user_id = memberVO.getUser_id();
		//이 메서드는 회원 상세보기 페이지에서 삭제 버튼을 클릭 시, 전송 받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(user_id); //삭제쿼리가 실행됨.
		//return "/admin/member/member_list";//삭제 후 이동할 jsp경로지정.
		//위 방식대로 하면, 새로고침 시 /admin/member/member_delete 계속 실행됨. -사용자단에서 실습
		//게시판 테러 상황을 방지하기 위해서, 쿼리를 작업 후 이동할 때는 redirect(다시접속)라는 명령을 사용함.
		return "redirect:/admin/member/member_list"; //단, redirect는 절대경로를 사용.
	}
	@RequestMapping(value="/admin/member/member_view", method=RequestMethod.GET)
	public String viewMemberForm(Model model, @RequestParam("user_id")String user_id, @ModelAttribute("pageVO")PageVO pageVO) throws Exception{
		//페이진입 시 받은 클래스변수값 PageVO pageVO
		/*
		 * 이 메서드는 리스트페이지에서 상세보기로 이동할때 보여주는 1개 레코드값을 보여주는 구현을 합니다.
		 * JUnit에서 테스트했던 readMember 방식을 이용.
		 * 다른점은 JUnit에서는 식별자 ID를 강제로 지정했지만, 이 메서드에서는 @RequsetParam인터페이스를 이용해서 식별자값을 받음.
		 */
		//위 출력값 memberVO 1개의 레코드를 model를 이용해서 member_view.jsp 보냅니다.(아래)
		model.addAttribute("memberVO", memberService.readMember(user_id));
		//model.addAllAttribute("pageVO",pageVO);
		//아래 페이지 반환 시|(렌더링) @ModelAttribute("PageVO")에 의해서 pageVO.page변수값으로 jsp보냄
		return "admin/member/member_view";//상태경로 폴더파일위치
	}
	@RequestMapping(value="/admin/member/member_list", method=RequestMethod.GET)
	public String selectMember(@ModelAttribute("pageVO")PageVO pageVO,Model model) throws Exception {
		/*
		이 메서드는 2개 객체 생성하는 로직이 필요. 결과를 JSP로 보내는 기능을 수행
		1객체: memberList객체를 생성해서 model을 통해서 jsp로 전송 
		2객체: pageVO객체(prev,next,startPage,endPage)를 생성해서 model을 통해서 jsp로 전송
		2번객체부터 로직이 필요 -> memberList구하는 쿼리변수가 만들어지기 때문에 이것부터구현
		*/
		if(pageVO.getPage() == null) {//jsp에서 클릭값이 없을때만 초기값 입력
			pageVO.setPage(1);//초기값 1페이지 입력
		}
		//학습포인트: calcPage()로직(이해) < 변수(객체)값의 이동확인(코딩사용)
		pageVO.setQueryPerPageNum(5);//memberList객체+endPage구할때 필요
		pageVO.setPerPageNum(5);//startPage구할때-UI하단 페이지번호개수
		//위 2개의 변수값을 이용해서 아래 setTotalCount메서드에서 calcPage()호출됨
		pageVO.setTotalCount(memberService.countMember(pageVO));
		//calcPage 실행되면, prev, next변수 값이 입력됩니다.
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		//위 setPerPageNum 20이면 next가 false(비활성화), 5이면 next가 true(활성화)
		logger.info("디버그" + pageVO.toString());
		//컨트롤러에서 jsp로 자료를 Model에 담아서 보내게 됩니다.
		model.addAttribute("listMember", listMember);
		//model.addAttribute("pageVO", pageVO);//나중에 @ModelAttribute로 대체
		return "admin/member/member_list";//jsp파일 상대경로
	}
	//URL요청 경로는 @RequestMapping 반드시 절대경로로 표시
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model) throws Exception {//에러발생시 Exception클래스의 정보를 스프링으로 보내게 됩니다.		
		//아래 상대경로에서 /WEB-INF/views/폴더가 루트(생략prefix접두어) 입니다.
		//아래 상대경로 home.jsp에서 .jsp (생략suffix접미어) 입니다.
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
}