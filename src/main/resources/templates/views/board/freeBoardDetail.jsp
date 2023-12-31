<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WOOFLY</title>
<link rel="icon" href="${ contextPath }/resources/image/woofly.png">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/topAdd.jsp"></jsp:include>
	<div class="container mx-5 mt-5 mb-5">
	
		<!-- 게시글 영역 -->
		<div class="container d-flex px-0 border-bottom border-black border-3 justify-content-between align-items-center">
			<h1>자유게시판</h1>
		</div>
		<div class="container">
			<!-- 사진 작성자 작성일자 드롭다운 -->
			<div class="header-container d-flex mt-3 justify-content-between">
				<div class="d-flex">
					<img class="float-start me-4" src="${ contextPath }/resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
					<div class="header-text-container">
						<h6>작성자</h6>
						<p>2023.10.16</p>
					</div>
				</div>
				<div class="dropdown mt-1">
					<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-three-dots-vertical dropdown-toggle" data-bs-toggle="dropdown" role="button" viewBox="0 0 16 16">
					  	<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
					</svg>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${ contextPath }/board/free/edit">수정</a></li>
					    <li><a class="dropdown-item" href="#">삭제</a></li>
					    <li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal1">신고</button></li>
					</ul>
				</div>
			</div>
			<!-- 제목 -->
			<div class="row">
				<h1 class="ps-0">반려견 출입 가능 카페 알려드려요~</h1>
			</div>
			<!-- 내용 -->
			<div class="row mt-3" style="min-height: 500px;">
				종로역 1번 출구에 생겼더라구요~!
			</div>
			<!-- 좋아요 -->
			<div class="row border-bottom border-black border-3">
				<div class="d-flex justify-content-center">
					<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
						<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
					</svg>
					<h4 class="ms-3">53</h4>
				</div>
			</div>
		</div>
		
		<!-- 댓글 작성 영역 -->
		
		<div class="mt-3">
			<div class="row g-3 justify-content-center">
				<div class="col-10">
					<input class="form-control" type="text" placeholder="댓글 작성하기">
				</div>
				<div class="col-auto">
					<button class="btn btn-dark">댓글 작성하기</button>
				</div>
			</div>
		</div>
		
		<!-- 댓글 조회 영역 -->
		
		<div class="comment-container d-flex mt-3 justify-content-center text-center border-top border-black">
			<div class="col-1" style="line-height: 40px;">꼬미</div>
			<div class="col-8" style="line-height: 40px;">생각보다 음료 종류도 많네요</div>
			<div class="col-1" style="line-height: 40px;">작성일자</div>
			<div class="col-2 d-flex align-items-center justify-content-end">
				<div class="mx-2">53</div>
				<div>
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
						<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
					</svg>
				</div>
				<div class="dropdown ms-2 me-5">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-three-dots-vertical dropdown-toggle" data-bs-toggle="dropdown" role="button" viewBox="0 0 16 16">
					  	<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
					</svg>
					<ul class="dropdown-menu">
    				    <li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal1">신고</button></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="comment-container d-flex mt-3 justify-content-center text-center border-top border-black">
			<div class="col-1" style="line-height: 40px;">뽀또</div>
			<div class="col-8" style="line-height: 40px;">주말 동안 가보기 좋은 곳이네요</div>
			<div class="col-1" style="line-height: 40px;">작성일자</div>
			<div class="col-2 d-flex align-items-center justify-content-end">
				<div class="mx-2">53</div>
				<div>
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
						<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
					</svg>
				</div>
				<div class="dropdown ms-2 me-5">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-three-dots-vertical dropdown-toggle" data-bs-toggle="dropdown" role="button" viewBox="0 0 16 16">
					  	<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
					</svg>
					<ul class="dropdown-menu">
				    	<li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal1">신고</button></li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="comment-container d-flex mt-3 justify-content-center text-center border-top border-black">
			<div class="col-1" style="line-height: 40px;">콩</div>
			<div class="col-8" style="line-height: 40px;">오 안 가본 곳이네요~  한번 가봐야겠어요</div>
			<div class="col-1" style="line-height: 40px;">작성일자</div>
			<div class="col-2 d-flex align-items-center justify-content-end">
				<div class="mx-2">53</div>
				<div>
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
						<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
					</svg>
				</div>
				<div class="dropdown ms-2 me-5">
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-three-dots-vertical dropdown-toggle" data-bs-toggle="dropdown" role="button" viewBox="0 0 16 16">
					  	<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
					</svg>
					<ul class="dropdown-menu">
				    	<li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal1">신고</button></li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 댓글 페이지네이션 -->
		<nav class="border-top border-black pt-5 mt-3">
		  	<ul class="pagination justify-content-center">
		    	<li class="page-item">
			      	<a class="page-link" href="#">
			        	<span aria-hidden="true">&laquo;</span>
			      	</a>
		    	</li>
			    <li class="page-item"><a class="page-link active" href="#">1</a></li>
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
	<!-- 신고 영역 -->
	<form>
		<div class="modal fade" id="modal1" tabindex="-1">
		  	<div class="modal-dialog modal-dialog-centered">
		    	<div class="modal-content">
		      		<div class="modal-header">
		        		<h1 class="modal-title fs-5" id="modalToggleLabel1">신고</h1>
		        		<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      		</div>
		      		<div class="modal-body">
			      		<select class="form-select mb-3">
						  	<option disabled>신고 사유</option>
						  	<option value="1">불법게시글</option>
						  	<option value="2">음란물</option>
						  	<option value="3">기타</option>
						</select>
		        		<textarea class="form-control" rows="5" style="resize: none;"></textarea>
		    		</div>
		    		<div class="modal-footer">
		        		<button class="btn btn-primary" data-bs-toggle="modal">제출</button>
		      		</div>
		    	</div>
		  	</div>
		</div>
	</form>

	
	
	<script>
		// 해당 페이지 nav bar 강조 넣기
		// 페이지 별로 id만 수정 하면 됨 eg.) topAdd1, topAdd2, ...
		const boardNav = document.getElementById("topAdd3");
		$(boardNav).addClass('active');
	</script>
	
</body>
</html>