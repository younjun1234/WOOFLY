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
			<h3>게시글 작성하기</h3>
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
			</div>
			
			<form>
				<div class="mb-3">
					<label for="title" class="form-label">제목</label>
				  	<input type="text" class="form-control" id="title" placeholder="">
				</div>
				<div class="mb-3">
				  	<label for="content" class="form-label">내용</label>
				  	<textarea class="form-control" id="content" rows="20" style="resize: none;"></textarea>
				</div>
				<div class="mb-5">
				  	<label for="file" class="form-label">첨부파일</label>
				  	<input class="form-control form-control-sm" id="file" type="file">
				</div>
				<div class="row border-top border-black pt-3">
					<button class="btn btn-dark">등록</button>
				</div>
			</form>
		</div>
		
		
	</div>
	
	<script>
		// 해당 페이지 nav bar 강조 넣기
		// 페이지 별로 id만 수정 하면 됨 eg.) topAdd1, topAdd2, ...
		const boardNav = document.getElementById("topAdd3");
		$(boardNav).addClass('active');
	</script>
	
</body>
</html>