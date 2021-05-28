#### 20210531(월) 작업예정.
- 4장 패키지와 예외처리 실습.
- 스프링프로젝트 ERD제작 후, 게시판 UI디자인 적용.

#### 20210528(금)작업내용.
- 추상클래스 실습은 오늘, 인터페이스 실습은 스프링에 엄청 하게 됨.
- extends관계클래스에서 this. (현재클래스), super(부모클래스)
- 다형성? 메서드 @오버라이드(상속), 메서드 오버로드(동일함수명의 파라미터만 갯수와, 종류가 틀린 메서드) 구현됨.
- 오버라이드 : 상속받아서 재정의 메서드 @오버라이드 = 다형성구현함.
- 오라클11g ExpressEditon 설치예정 OracleXE112_win64.zip
SQL디벨리퍼를 다운받아서 압축푼다. - ERD제작할 예정.
- StructuredQuery Language: 구조화된 질의 언어 (오라클 서버) -> 답변
- QueryString : URL에서 전송하는 값(서버에 질의문)을 문장으로 저장한 내용. [?]로 시작
- CommendLineInterface : SQL*Plus 터미널화면으로 SQL쿼리 실행X
- GrapicUserInter: SQL디벨리퍼 윈도우화면 에디엍에서 SQL쿼리 실행O
- SQL Developer 프로그램으로 ERD 다이어그램 그림으로 프로젝트 구조 제작예정.
- EntityRelationDiagram: 테이블관계도형(아래)
- 프로젝트 진행: 발주(클라이언트) -> 수주(개발사) -> 디자인UI(Client-Dev) -> ERD(이사,팀장) -> 코딩시작(UI보면서+ERD소스에 프로그램 입히기)
-ERD에서 SQL쿼리가 생성 -> 물리테이블을 생성.
 첨부파일(자식)->게시판테이블(부모)<-댓글테이블(자식)-
- 자바앱에서는 객체를 생성 후 사용이 끝나면, 메모리에서 삭제하는 명령이 필수.
- 객체를 메모리에서 삭제: Object = null; Object.close();
- 스프링에서는 내장된 가비지컬렉터(쓰레기수집가)가 자동실행. 사용하지 않는 객체를 자동으로 지워줌.
- 위와 같이 개발자가 하던 메모리관리를 스프링이 대신 = IoC(Inversion Of Control)제어의 역전. 스프링 특징3 가지 중 1개(IoC, AOP, DI)

####20210527(목) 작업내용.
- 3장 객체와 클래스 부터 시작.
- 스프링에서는 클래스 extends(상속)보다는 인터페이스 implements를 더 많이 사용함.
- abstract클래스(추상클래스): 구현내용이 없이, 메서드명만 있는 클래스.
- 메서드명만 있기(구현내용이 없기) 때문에, *객체로 만들 수 없는 클래스
- 부모로서 자식에게 상속만(메서드이름만)해서 재사용만 가능하게 됨.
- 객체로 만들 수 없다? 실행가능한 클래스로 사용 못하지만, 프로그램을 구조화 시킬 때 필요.
-Step1 aaa = new Step1();//이렇게 생성자로 객체를 만들지 못함.
- final클래스: 부모가 될 수 없는 클래스(상속해서 재사용이 불가능한 클래스)
- 접근제어자: public(패키지위치에 상관없이 접근-제일 많이 사용),
- public 클래스 안에서 멤버변수는 private을 제일 많이 사용(private보안성 갖춤)
- public 클래스 안에서 멤버메서드는 public을 많이 사용.(외부 다른 클래스의 메서드를 실행가능)대신, 변수가 private이기 때문에, 직접 수정 안됨, 실행만 가능.
- 인스턴스 (클라우드 주로사용)=오브젝트(자바)=객체=실행중인클래스
- 클래스 자료형(int, long, String처럼)는 멤버변수, 멤버메서드, 서브클래스 등등 포함할 수 있음. = C언어 구조체 자료형
- 이클립스에서 커밋, 푸쉬가 안될 때 커밋 팝업창 하단에 Force 항목을 체크 후, 진행.

####20210526(수) 작업
- 붕어빵 : 붕어빵틀(클래스) -> 붕어빵 구워져서 나오면 오브젝트(객체)
- 객체 = 오브젝트 = 인스턴스 = 실행가능한 클래스
- OOP:자바를 객체지향(클래스를 실행 가능하게 하는) 프로그래밍.
- 객체(Object) 와 클래스? 클래스형 자료를 실행 가능하게 만든 것을 오브젝트라(객체)라고 함.
- 추상화(Abstract): 오프라인 업무 -> 대표 업무만 뽑아낸 클래스 = 추상클래스
- 클래스는 멤버변수 + 멤버메서드(실행) + 서브클래스
- 변하지 않는 변수=상수=변수명을 대문자(원주율)PI=3.141569...
- 보통 상수변수는 클래스의 제일 상단 위치함.
- 기본형(정수자료형-소문자시작): byte<short<int<long 10L(롱타입변수)
- 기본형(실수자료형-소수점,소문자시작):float<double , 123.4f(실수형숫자)
- 기본형(문자형-'a'단따옴표 값을 입력해야 에러x): char, 1개 문자만 입력.
- 문자형에서 유니코드 \u숫자 임.
- 기본형(불린형-참,거짓):boolean, (0|1)
-참조형(대문자로 시작): 참조형 변수의 대표적인 변수 클래스. 클래스변수(저장된 상태) -> 실행가능 하게 되었을 때, 인스턴스 변수.(메모리로드된 상태)
- String(문자열 클래스 변수): 대문자로 시작.
- 인스턴스라는 말보다는 오브젝트라는 말을 더 많이 사용함.
- 상수변수를 명시적으로 만들 때: final int MAX = 100;
- for-each 반복문전까지 실습


####20210525(화) 작업
- 스프링 MVC프로젝트 : ModelviewController? 약자 MVC구조(웹프로그램구조)
- src/text/java 폴더 만들어짐 : 테스트 작업은 이 폴더에서 하라는 약속.
- src/main/java 폴더가 진짜 프로그램 작업을 하는 폴더.
- javac HelloWorld.jacv -encodeing UTF-8 (한글 내용도 컨파일 됨)
- 위 java컴파일러로 실행한 결과 -> HelloWorld.class 실행파일이 생성됨.
- 주의점), 클래스파일은 실행 패키지의 루트(최상위)에서 실행해야 함.
- kr.or.test패키지 root폴더 src/test/java 폴더에 실행을 해야 함.
- java kr.or.test.HelloWorld 클래스를 실행하게 됨.
- 이클립스 나오기 전, 20~25년전 javac 컴파일에서 class프로그램을 만듬.
- 지금은 터미널에서 javac 사용하지 않고, 이클립스에서 바로 실행.
- javac? 자바앱 컴파일러 -> 클래스 실행파일을 만듬. class (바이트코드)(자바환경JVN실행)
- 메이븐 이란? Maven : 웹프로그램 컴파일러 -> 웹프로그램(앱) 실행파일을 만듬.war(톰캣에서 실행)
- 메이븐이 컴파일 할 때, 자바소스만 컴파일 하는 것이 아니고, 외부 라이브러리도 가져와서 바인딩(묶어줌)하게 됨. = 패키징 이라고 함. = war(와르)파일로 패키징 됨.
- 메이븐이 관리하는 외부 라이브러리 파일(lib) 목록을 저장하는 파일 pom.xml 임.
- pox.xml 에서 자바버전을 1.6 -> 1.8로 변경 후, 메이븐을 업데이트 함.
-외부 라이브러리 파일을 참조하는 방식을 영어로 = dependency 디펜던시
- jar파일이란? javaARchive = jar 자바클래스를 패키징한 파일.
 
####20210525
- 자바 기초 실습
- 자바 .java 클래스파일을  컴파일해서  .class파일 실행하는 구조.
- 파이썬/자바스크립트는 인터프리터 방식을 실행.
- 자바스크립트는 함수구조의 프로그래밍 = Function(함수)
- 자바는 객체지향 프로그래밍(Object Oriented Program)
- 오브젝트 (객체) = 실행가능한 Class(클래스)
- 아스키,유니코드(UnicodeTypeFormat-8)
- 아스키코드 IOT에서 데이터 전송시 필수로 확인해야 함. 0,1을 전송 -> 48,49값을 받음.
- IoT프로그램시 하드웨어 값을 주고 받을 때 if(var1 == 48) {구현내용}<br>
- 공유기가 하위로 다질 수 있는 사설IP는 공유기 자신의 IP + 255개 (여유분) = 256개 인터넷이 가능.
- 영어 인코딩은 아스키코드로 다 표현 가능.
- 단, 한글 인코딩, 중국어/일본어 인코 딩등등은 아스키 코드로 다 표현 못함. 유니코드 등장. UTF-8
- Hex(16진수),Dec (10진수),Cher(문자) = 127개 = 아스키문자의 크기(컴문자-사람문자 1:1매칭)
- Oct(8진수), bit(2진수)
- 아스키코드 7비트코드(127글자)->ANSI코드 -> 8비트(256글자) -> UniCode(655536글자)-UTF-8
- UTF8mb4(이모지 포함:이모티콘+이미지).
- 자바언어를 한다는 것은 컴파일 후 실행이 된다. -> 실습예정
- 자바스크립트는(파이썬)그냥 실행해서 프로그램을 만들어 짐. -> 실습예정.