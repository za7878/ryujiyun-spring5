<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>

 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${boardVO.board_type} 상세보기</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">${boardVO.board_type}</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 콘텐츠 내용 -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">보기</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <!-- 첨부파일을 전송할 때 enctype=필수 없으면, 첨부파일이 전송X -->
          <form name="form_view" method="get" action="/admin/board/board_update_form" enctype="multipart/form-data">
            <div class="card-body">
              <div class="form-group">
                <label for="exampleInputEmail1">제목</label>
                <br>
                ${boardVO.title}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">내용</label>
               <br>
               ${boardVO.content}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">작성자</label>
               <br>
               ${boardVO.writer}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">조회수</label>
               <br>
               ${boardVO.view_count}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">작성일</label>
               <br>
              <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${boardVO.reg_date}"/> 
              </div>
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
              	<c:forEach begin="0" end="1" var="idx">
              	<c:if test="${boardVO.save_file_names[idx] != null }">
              	 <div class="input-group">
                  <div class="custom-file">
                   <!-- 첨부파일을 URL로 직접접근 하지 못하기 때문에 컨트롤러로만 접근가능(다운로드전용 메서드생성)  -->
                   <a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}" >
                   ${boardVO.real_file_names[idx]}
                   </a>
                   <!-- jstl에서 변수사용하기 fn.split('데이터','분할기준값') 목적: 확장자를 이용해서 이미지 미리보기를 할 건지 결정img태그 사용
                   		String[] fileNameArray = String.split('변수값','분할기준값');
                   		-->
                   <c:set var="fileNameArray" value="${fn:split(boardVO.save_file_names[idx],'.')}" />
 					<!-- 그림판.얼굴.jpg = 3개 배열, 그림판.jpg = 2개 배열-->                 
                   <c:set var="extName" value="${fileNameArray[fn:length(fileNameArray)-1]}" />
                   <!-- 그림판.얼굴.jpg 파일을 위 extNname = fineNameArray[2] = jpg -->
                   <!-- 자바언어로는 switch ~ case문 ~default -->
                   <!-- containsIgnoreCase('찾을값의문장','비교기준값' -->
                   <c:choose>
                   		<c:when test="${fn:containsIgnoreCase(checkImgArray,extName)}">
                   		<img src="/imge_preview?save_file_name=${boardVO.save_file_names[idx]}" style="width:100%;">
                   		</c:when>
                   		<c:otherwise>
                   		<!-- 아무 의미 없이 개발연습용으로  -->
                   		<c:out value="${checkImgArray}" /> 이미지가 아님.
                   		</c:otherwise>
                   </c:choose>
                  </div>
                </div>
              	</c:if>
              	</c:forEach>
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-danger" id="btn_delete">삭제</button>
              <button type="button" class="btn btn-default" id="btn_list">목록</button>
            </div>
            <input name="page" value="${pageVO.page}" type="hidden">
           <%--  <input name="search_type" value="${pageVO.search_type}" type="hidden"> --%>
            <input name="search_keword" value="${pageVO.search_keyword}" type="hidden">
            <input name="bno" value="${boardVO.bno}" type="hidden">
          </form>
        </div>
   
        <!-- 댓글입력폼 -->
        <div class="col-md-12">
          <div class="card-default">
            <div class="card-header">
              <h3 class="card-title">댓글 쓰기</h3>
            </div>
            <div class="card-body p-0">
              <div class="bs-stepper linear">
              <div class="bs-stepper-header" role="tablist">
                <div class="line"></div>
              </div>
              <div class="bs-stepper-content">
                <!-- your steps content here -->
                <div id="logins-part" class="content active dstepper-block" role="tabpanel" aria-labelledby="logins-part-trigger">
                <div class="form-group">
                  <label for="replyer">작성자</label>
                  <input type="text" class="form-control" id="replyer" placeholder="작성자를 입력하세요">
                </div>
                <div class="form-group">
                  <label for="reply_text">댓글내용</label>
                  <input type="text" class="form-control" id="reply_text" placeholder="댓글내용을 입력하세요">
                </div>
                </div>
                <div id="information-part" class="content" role="tabpanel" aria-labelledby="information-part-trigger">
                <button type="button" class="btn btn-warning" id="btn_reply_write">댓글등록</button>
                </div>
              </div>
              </div>
            </div>
            <!-- /.card-body -->
            <div class="card-footer">
              아래 댓글리스트 버튼을 클릭하시면 댓글 목록이 출력이 됩니다.
            </div>
          </div>
          </div>
        <!-- //댓글입력폼 -->
        <!-- 댓글 타임라인 -->
        <div class="col-md-12">
          <!-- The time line -->
          <div class="timeline">
          <!-- timeline time label -->
          <div class="time-label">
            <span class="bg-red" data-toggle="collapse" href="#collapseReply" role="button" id="btn_reply_list">
                  댓글리스트
                  [<span>1</span>]
                </span>
          </div>
             <!-- 콜랩스 시작 -->
             <div class="collapse timeline" id="collapseReply">
          <!-- time-label 이후 after요소로 템플릿결과가 여기에 출력됨. -->
          <!-- /.timeline-label -->
          <!-- timeline item -->
          <!-- 댓글리스트를 자바스크립트의 빵틀(템플릿)을 만듭니다.  -->
          <!-- 고전append함수를 사용하지 않고, handlebars라는 확장프로그램(아래) 임포트 -->
          <!-- 장점은 기존 퍼블리셔가 만든태그를 그대로 사용가능 -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
          <script id="template" type="text/x-handlebars-template">
          {{#each .}}
          <div class="div_template" data-rno="{{rno}}">
            <i class="fas fa-envelope bg-blue"></i>
            <div class="timeline-item">
            <h3 class="timeline-header">{{replyer}}</h3>
            <div class="timeline-body">{{reply_text}}</div>
            <div class="timeline-footer">
              <a class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modal-reply">수정</a>
            </div>
            </div>
          </div>
          {{/each}}
          </script>
          
          <!-- 페이징 처리 -->
          <div class="row">
            <ul class="pagination" style="margin: 0 auto;">
              <!-- <li class="paginate_button page-item previous disabled" id="example2_previous">
                <a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
              </li>
              <li class="paginate_button page-item active">
                <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">1</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0" class="page-link">2</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0" class="page-link">3</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0" class="page-link">4</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0" class="page-link">5</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0" class="page-link">6</a>
              </li>
              <li class="paginate_button page-item next" id="example2_next">
                <a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
              </li> -->
            </ul>
          </div>
          <!-- //페이징 처리 -->
          </div>
            <!-- //콜랩스 끝 -->
             </div>
          <!-- END timeline item -->
        <!-- //댓글 타임라인 -->
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>
<script>
$(document).ready(function(){
	var form_view = $("form[name='form_view']");
	$("#btn_list").click(function(){
		//여기서는 함수내 변수
		form_view.attr("action","/admin/board/board_list");
		form_view.submit();
	});
	$("#btn_delete").click(function(){
		if(confirm('정말로 삭제 하시겠습니까?')) {//Yes를 클릭하면 아래내용 실행
			form_view.attr("action","/admin/board/board_delete");
			form_view.attr("method", "post");
			form_view.submit();
		}
	});
});
</script>