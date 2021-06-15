package com.edu.util;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

/**
 * 이 클래스는 이 프로젝트에서 공통으로 사용하는 유틸리티 기능을 모아놓은 클래스.
 * @author 유지윤
 * 컨트롤러 기능 @Controller (jsp와 바인딩이 필요할 때는 필수 애노테이션.)
 * 컴포넌트 @Component는 MVC가 아닌 기능들을 모아놓은 스프링빈 메서드 명시, 여기서는 jsp와 바인딩이 필요해서 사용 안함.
 */
@Controller
public class CommonUtil {
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	//멤버변수생성(아래)
	@Inject
	private IF_MemberService memberService;//스프링빈을 주입 받아서 (DI)객체준비
	
	//RestAPI 서버 맛보기 ID중복체크(제대로 만들면 @RestController 사용)
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody //반환받은 값의 헤더값을 제외하고, 내용(body)만 반환하겠다는 명시.
	public String id_check(@RequestParam("user_id")String user_id) throws Exception {
		//중복아이디를 체크로지(아래)
		String memberCnt = "1";//중복ID가 있을 때, 기본값1
		if(!user_id.isEmpty()) {//user_id가 공백이 아니라면,
		MemberVO memberVO = memberService.readMember(user_id);
		logger.info("디버그: " + memberVO); //user_id를 공백을 전송해도 null이기 때문에 조건 추가 필요.
		if(memberVO == null) {//중복아이디가 존재하지 않으면, {}안 내용 실행.
			memberCnt = "0";
			}
		}
		memberService.readMember(user_id);
		return memberCnt; //0.jsp 이렇게 작동하지 않음. 이유는 @ResponseBody 때문이고, RestAPI는 값만 반환
	}
}
