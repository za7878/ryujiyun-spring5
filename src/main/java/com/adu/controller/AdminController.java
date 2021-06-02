package com.adu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 이 클래스는 Admin관리자단을 접근하는 클래스
 * 변수 Object를 만들어서 jsp로 전송 <-> jsp 값을 받아서 Object로 처리
 * @author 유지윤
 * 
 */
@Controller
public class AdminController {
	//URL요청 경로는 @RequestMapping 반드시 절대경로로 표시
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String admin(Model model) throws Exception { //에러발생시 Exception 클래스의 정보를 스프링으로 보내게 됨.
		//아래 상대경로에서 /WEB-INF/views/폴더가 루트(생략 prefix접두어)이다.
		//아래 상대경로 home.jsp에서 jsp (생략suffix접미어)이다.
		return "admin/home"; //리턴(반환) 경로 = 접근경로는 반드시 상대겨로로 표시
	}
}
