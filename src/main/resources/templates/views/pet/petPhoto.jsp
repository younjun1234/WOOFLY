<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>petPhoto</title>
<link rel="icon" href="${ contextPath }/resources/image/woofly.png">
<style>
	.active {
		color: black !important;
		font-weight: 700 !important;
	}
</style>
</head>
<body>
	<!-- top navigation bar -->
	<!-- 마이 페이지에 공통으로 들어가는 navigation bar -->
	<jsp:include page="/WEB-INF/views/common/top.jsp"></jsp:include>
	<!-- 메인 네비게이션 바 아래 있는 모든 요소들 -->
	<main>
		<div class="container d-flex justify-content-between mx-5">
			<jsp:include page="/WEB-INF/views/common/myNav.jsp"></jsp:include>
			<!-- 아래만 수정해주세요 -->
			<!-- 아래만 수정해주세요 -->
			<!-- 아래만 수정해주세요 -->
			<!-- 아래만 수정해주세요 -->
			<!-- 아래만 수정해주세요 -->
			<!-- 아래만 수정해주세요 -->
			<div class="col-10">
				<div class="border-bottom border-3 border-black">
					<h3>마이펫 사진첩</h3>
				</div>	
			</div>
		</div>
	</main>
</body>
</html>