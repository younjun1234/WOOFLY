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
	<div class="container mt-3 mb-5">
		<div>
			<a class="btn btn-outline-secondary btn-sm px-3 active" href="${ contextPath }/used">중고거래게시판</a>
	 		<a class="btn btn-outline-secondary btn-sm px-3" href="#">중고거래후기</a>
		</div>
		<div class="header-container d-flex mt-5 justify-content-between">
			<div class="d-flex">
				<img class="float-start me-4" src="${ contextPath }/resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
				<div class="header-text-container">
					<h6>작성자</h6>
					<p>2023.10.16</p>
				</div>
			</div>
			<div class="dropdown mt-1">
				<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-three-dots dropdown-toggle" data-bs-toggle="dropdown" role="button" viewBox="0 0 16 16">
				  	<path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3m5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3m5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3"/>
				</svg>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="${ contextPath }/board/used/edit">수정</a></li>
				    <li><a class="dropdown-item" href="#">삭제</a></li>
				    <li><button class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modal1">신고</button></li>
				</ul>
			</div>
		</div>
		<div id="carousel" class="carousel slide mx-auto mt-5 mb-5" style="height: 500px; width: 500px;">
		  	<div class="carousel-indicators">
			    <button type="button" data-bs-target="#carousel" data-bs-slide-to="0" class="active"></button>
			    <button type="button" data-bs-target="#carousel" data-bs-slide-to="1"></button>
			    <button type="button" data-bs-target="#carousel" data-bs-slide-to="2"></button>
		  	</div>
  			<div class="carousel-inner bg-black">
			    <div class="carousel-item active">
			      	<img src="${ contextPath }/resources/image/dog1.jpg" style="height:500px; width: 500px;">
			    </div>
    			<div class="carousel-item">
      				<img src="https://kream-phinf.pstatic.net/MjAyMzExMzBfMTkx/MDAxNzAxMzMyNzYyNTc3.UHki92T8iGvkrLOpGO-RyCUSz1GFLPxm5poyOYtH-0Ug._ZBLSk6mGHvXOQx89jYhwoiqFfyMlOwytVj6BAl-PLUg.JPEG/a_38c80c7c422c4aacb5249a7f04c5ce11.jpg" class="d-block h-100">
    			</div>
    			<div class="carousel-item">
      				<img src="" class="d-block h-100">
    			</div>
  			</div>
		  	<button class="carousel-control-prev" type="button" data-bs-target="#carousel" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon"></span>
		  	</button>
		  	<button class="carousel-control-next" type="button" data-bs-target="#carousel" data-bs-slide="next">
			    <span class="carousel-control-next-icon"></span>
		  	</button>
		</div>
		
		<div class="d-flex justify-content-start">
			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
				<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
			</svg>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16" role="button">
			  	<path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894m-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
			</svg>
		</div>
		
		<div class="d-flex align-content-start flex-column">
			<p>좋아요 81개</p>
			<h3>강아지 유모차 팔아요</h3>
			<p>거의 사용하지 않았던 제품입니다! 때문에 네고는 없어요ㅠㅠ</p>
			<br>
			<p>댓글 1개</p>
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
				
		<div class="comment-container d-flex mt-3 pt-3 justify-content-center text-center border-top border-black">
			<div class="col-1" style="line-height: 40px;">
				<img class="float-start me-1" src="${ contextPath }/resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
				콩
			</div>
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
			
	</div>
</body>
</html>