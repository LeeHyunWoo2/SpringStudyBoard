<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- jstl 코어 태그용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- jstl 포메팅 태그용 -->
<%@ include file="../includes/header.jsp" %>
<!-- list.jsp에서 /board/register 경로를 호출하면 get메서드가 실행, 폼 박스가 나옴 -->
<!-- 입력완료를 누르면 vo객체가 만들어져서 /board/register에 post 메서드가 실행 -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header"> 게시판 글 수정 </h1>
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Modify</div>
			<div class="panel-body">
				<!-- form 박스 만들고 submit 처리 -->
				<form role="form" action="/board/modify" method="post">
				
					<div class="form-group">
						<label>번호</label>
						<input class="form-control" name="bno" value='<c:out value="${ board.bno }"/>' readonly="readonly">
					</div>
				
					<div class="form-group">
						<label>제목</label>
						<input class="form-control"name="title" value='<c:out value="${ board.title }"/>'>
					</div> <!-- form-group end -->
					
					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="3" name="content"><c:out value="${ board.content}"/></textarea>
					</div>
					
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value='<c:out value="${ board.writer }"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>등록일</label>
						<input class="form-control" name="regdate" value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${ board.regdate }"/>' readonly="readonly">
					</div>
					<div class="form-group">
						<label>수정일</label>
						<input class="form-control" name="updateDate" value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${ board.updateDate }"/>' readonly="readonly">
					</div> 
					<button type='submit' data-oper='modify' class="btn btn-primary">수정</button>
					<button type='submit' data-oper='remove' class="btn btn-danger">삭제</button>
					<button type='submit' data-oper='list' class="btn btn-info">목록</button>
				</form>
			</div> <!-- panel-body end -->
		</div> <!-- panel panel-default end -->
	</div> <!-- .col-lg-12 end -->
</div> <!-- .row end -->

<script>
$(document).ready(function() {

	  var formObj = $("form"); // 상단 코드중에 form태그를 formObj 관여하겠다.
	  $('button').on("click", function(e){
		    
		    e.preventDefault(); // button 기본 동작 사용 안함. submit 안됨
		    
		    var operation = $(this).data("oper"); // data-oper='modify' 'remove' 'list'
		    
		    console.log(operation); // 개발자 도구 콘솔에 찍힘
		    
		    if(operation === 'remove'){ // data-oper='remove'
		      formObj.attr("action", "/board/remove"); // 삭제 컨트롤러를 요청
		    }else if(operation === 'list'){
		      //move to list
		      self.location="/board/list"; // data-oper='list'
		      return;
		    }
		    formObj.submit(); // data-oper='modify'
		  });
	});
	</script>


<%@ include file="../includes/footer.jsp" %>