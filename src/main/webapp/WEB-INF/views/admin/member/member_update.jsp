<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원정보 수정</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">관리자관리</li>
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
            <h3 class="card-title">수정</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <!-- 첨부파일을 전송할때 enctype=필수 없으면, 첨부파일이 전송X -->
          <form name="form_write" action="/admin/member/member_update" method="post" enctype="multipart/form-data">
            <div class="card-body">
              
              <div class="form-group">
                <label for="user_id">사용자ID</label>
                <input readonly value="${memberVO.user_id}" name="user_id" type="text" class="form-control" id="user_id" placeholder="회원ID를 입력해 주세요" required>
              </div>
              <div class="form-group">
                <label for="user_pw">암호</label>
                <!-- 암호는 기존값이 필요없음. 이유는 값이 있으면 업데이트 진행, 없으면 업데이트에서 제외됨. -->
                <input value="" name="user_pw" type="password" class="form-control" id="user_pw" placeholder="암호를 입력해 주세요">
              </div>
              <div class="form-group">
                <label for="user_name">사용자이름</label>
                <input value="${memberVO.user_name}" name="user_name" type="text" id="user_name" class="form-control" placeholder="이름을 입력해주세요." required>
              </div>
              <div class="form-group">
                <label for="email">이메일</label>
                <input value="${memberVO.email}" name="email" type="email" class="form-control" id="email" placeholder="이메일을 입력해 주세요" required>
              </div>
              <div class="form-group">
                <label for="point">포인트</label>
                <input value="${memberVO.point}" name="point" type="number" class="form-control" id="point" placeholder="포인트를 입력해 주세요" required>
              </div>
              <div class="form-group">
                <label for="enabled">로그인여부</label>
                <select name="enabled" id="enabled" class="form-control">
                  <option value="1" ${memberVO.enabled==true?'selected':'' }">허용</option>
                  <option value="0" ${memberVO.enabled==false?'selected':'' }">금지</option>
                </select>
              </div>
              <div class="form-group">
                <label for="levels">권한부여</label>
                <select name="levels" id="levels" class="form-control">
                  <option value="ROLE_USER" ${memberVO.levels=='ROLE_USER'?'selected':''}>사용자</option>
                  <option value="ROLE_ADMIN" ${memberVO.levels=='ROLE_ADMIN'?'selected':''}>관리자</option>
                </select>
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-info" id="btn_prev">이전</button>
              <button type="button" class="btn btn-default" id="btn_list">목록</button>
            </div>
            <input name="page" type="hidden" value="${pageVO.page}">
            <input name="search_type" type="hidden" value="${pageVO.search_type}">
          <%--   <input name="search_keyword" type="hidden" value="${pageVo.seaarch_keyword}"> --%>
          </form>
        </div>
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>
<!-- 관리자단은 jQuery코어가 하단 footer에 있기 때문에 여기에 위치합니다. -->
<script>
$(document).ready(function(){
	var form_update = $("form[name='form_write']");
	$("#btn_prev").click(function(){
		form_update.attr("action","/admin/member/member_view");
		form_update.attr("method","get");
		form_update.submit();
	});
	$("#btn_list").click(function(){
		/* 폼을 get방식을 전송시 암호와 같은 정보가 URL쿼리 스트링에 노출 되어서 주석처리
		form_update.attr("action","/admin/member/member_list");
		form_update.attr("method","get");
		form_update.submit();*/
		var queryString = 'page=${pageVO.page}&search_type=${pageVO.search_type}';
		location.replace('/admin/member/member_list?'+queryString);

	});
});
</script>