<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WOOFLY</title>
<link rel="icon" href="${ contextPath }/resources/image/woofly.png">
<style>
	.board-list {
		cursor: pointer;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/topAdd.jsp"></jsp:include>
	<div class="container mx-5 mt-5">
		<div class="container d-flex px-0 border-bottom border-black border-3 justify-content-between align-items-center">
			<h1>자유게시판</h1>
			<a role="button" href="${ contextPath }/board/free/write" class="btn btn-dark btn-sm h-50">글 작성</a>
		</div>
		
		<form>
			<div class="row mt-3">
			  	<label for="search-box" class="form-label">게시글 검색</label>
			    <input type="text" class="form-control" id="search-box" placeholder="검색">
			</div>
		</form>
		
		<div class="container board-list">
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
			<div class="row mt-3 border-top border-secondary text-center board-list" style="height: 80px;">
				<div class="col-2" style="line-height: 80px;">닉네임</div>
				<div class="col-8" style="line-height: 80px;">게시글 제목</div>
				<div class="col-2" style="line-height: 80px;">작성일자</div>
			</div>
		</div>
		
		<nav class="border-top border-black pt-5">
		  	<ul class="pagination justify-content-center">
		    	<li class="page-item">
			      	<a class="page-link" href="#">
			        	<span aria-hidden="true">&laquo;</span>
			      	</a>
		    	</li>
			    <li class="page-item"><a class="page-link" href="#">1</a></li>
			    <li class="page-item"><a class="page-link" href="#">2</a></li>
			    <li class="page-item"><a class="page-link" href="#">3</a></li>
			    <li class="page-item"><a class="page-link" href="#">4</a></li>
			    <li class="page-item"><a class="page-link" href="#">5</a></li>
		    	<li class="page-item">	
			      	<a class="page-link" href="#" aria-label="Next">
				    	<span aria-hidden="true">&raquo;</span>
			      	</a>
		    	</li>
		  	</ul>
		</nav>
	</div>
	
	<script>
		
		// 해당 페이지 nav bar 강조 넣기
		// 페이지 별로 id만 수정 하면 됨 eg.) topAdd1, topAdd2, ...
		const boardNav = document.getElementById("topAdd3");
		$(boardNav).addClass('active');
		
		// 각 게시물 별로 클릭 이벤트 넣는 부분
		const boardList = document.getElementsByClassName("board-list");
		for (const board of boardList) {
			board.addEventListener('click', () => {
				// 이 부분에서 boardId 가져와야함
				// 추후에 넣어주세요
				// 틀 단계에서는 무작위로 boardDetail로 넘어갑니다
				window.location.href = '${ contextPath }/board/free/detail';
				
			})
			
		}
		
		
	</script>
</body>
</html>