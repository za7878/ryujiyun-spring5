package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	@Test
	public void oldQuerTest() throws Exception {
		//스프링빈을 사용하지 않을 때 예전방식: 코딩테스트에서는 스프링설정을 안쓰고, 직접 DB 아이디/암호 입력.
		Connection connection = null;
		connection = DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1521/XE");
		
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
