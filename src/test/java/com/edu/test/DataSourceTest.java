package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트 하는 클래스 이다.
 * 과장(이사,팀장) JUnit CRUD까지 만들어서 일반사원에게 공개 + 회원관리
 * @author 유지윤
 *
 */
//Runwith인터페이스 현재클래스가 Junit실행클래스라고 명시.
@RunWith(SpringJUnit4ClassRunner.class)
//경로에서 **(모든 폴더), *(모든 파일명)을 명시.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourceTest {
	//디버그용 로그 객체생성
	private Logger logger = Logger.getLogger(DataSourceTest.class);
	//dataSource 객체는 데이터베이수객체를 pool로 저장해서 사용할 때 DataSource 클래스를 사용(아래)
	@Inject //인젝트는 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 만들었고...
	DataSource dataSource;//Inject로 객체를 만들면 객체의 메모리 관리를 스프링이 대신해 줌.
	//Inject 자바8부터 지원, 그럼 이전 자바 7에서 @Autowired로 객체를 만들었음.
	
	//스프링 코딩 시작 순서
	// M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set메서드)생성.
	//보통 ValueObject클래스는 DB테이블과 1:1로 매칭이 됨.
	//그래서, 1. MemberVO.java VO클래스를 생성.(필수)
	//2. DB(마이바티스)쿼리를 만듬.(VO사용됨.) - 내일 부터 시작.
	
	@Test
	public void selectMember() throws Exception {
		//회원관리 테이블에서 더미로 입력한 100개의 레코드를 출력 메서드 테스트->회원관리목록이 출력
		
	}
	
	@Test
	public void oldQuerTest() throws Exception {
		//스프링빈을 사용하지 않을 때 예전방식: 코딩테스트에서는 스프링설정을 안쓰고, 직접 DB 아이디/암호 입력.
		Connection connection = null;
		connection = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521/XE","XE2","apmsetup");
		logger.debug("데이터베이스 직접접근이 성공되었습니다");
		//직접 뭐리를 날린다. 날리기 전, 쿼리문자 객체생성 statement
		Statement stmt = connection.createStatement();
		//위 쿼리문장 객체를 만드는 이유? 보안(SQL인젝션 공격을 방지)
		//stmt객체가 없으면, 개발자가 SQL인젝션 방지코딩을 넣어야 함.
		//Insert쿼리문장만듬(아래)
		/*
		 * for(int cnt=0; cnt<100; cnt++) {
		 * stmt.executeQuery("insert into dept02 values("+cnt+",'디자인부','경기도')"); }
		 */
		//테이블에 입력되어 있는 레코드셋을 select 쿼리 stmt문장으로 가져옴(아래)
		ResultSet rs = stmt.executeQuery("select * from dept order by deptno");
		//위에서 저장된 rs객체를 반복문으로 출력(아래)
		while(rs.next())
			//rs객체의 레코드가 없을때 까지 반복
			logger.debug(rs.getString("deptno")+" "+rs.getString("dname")+
			" "+rs.getString("loc"));
		
		stmt = null;//메모리 반환
		rs = null;//메모리 반환
		connection = null; //메모리 초기화
	}
	@Test
	public void dbConnectionTest() {
			//데이터베이스 커넥션 테스트 : 설정은 root-context의 빈을 이용.
			try {
				Connection connection = dataSource.getConnection();
				logger.debug("데이터 베이스 접속이 성공 하였습니다. DB종류는 "+ connection.getMetaData().getDatabaseProductName());
			} catch (SQLException e) {
				logger.debug("데이터 베이스 접속이 실패 하였습니다.");
				//e.printStackTrace();
			}
			
	}
	@Test
public void junitTest () {
		//로거는 장점>조건에 따라서 출력을 조정할 수 있음.
	logger.debug("Junit 태스트 시작 입니다.");
}
}
