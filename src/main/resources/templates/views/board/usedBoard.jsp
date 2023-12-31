<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WOOFLY</title>
<link rel="icon" href="${ contextPath }/resources/image/woofly.png">
<style>
	.card {
		cursor: pointer;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/topAdd.jsp"></jsp:include>
	<div class="container">
		<div class="container d-flex px-0 mb-3 border-bottom border-black border-3 justify-content-between align-items-center">
			<h1>중고게시판</h1>
			<a role="button" href="${ contextPath }/board/used/write" class="btn btn-dark btn-sm h-50">글 작성</a>
		</div>
		<div>
			<a class="btn btn-outline-secondary btn-sm px-3 active" href="${ contextPath }/used">중고거래게시판</a>
	 		<a class="btn btn-outline-secondary btn-sm px-3" href="#">중고거래후기</a>
		</div>
		<!-- 검색 -->
		<form>
			<div class="row mt-3">
			  	<label for="search-box" class="form-label">게시글 검색</label>
			    <input type="text" class="form-control" id="search-box" placeholder="검색">
			</div>
		</form>
		
		<!-- 게시글 -->
		<div class="row gap-5 justify-content-center mt-3">
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/5efb/fd48acde4c9b97aa39e55704c0c100cc95fc22024dc80dc1a6e14801cdfd.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/0863/8360e74209bb76ccb87369df804895c456907e9b27ad2970e0c8cfa49814.png" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail8.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/4e1a/b08b56f9e4a8995cd3f9298a1cacbd28bf09b57198aaec82f91a1f703126.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
		</div>
		
		<!-- 게시글 -->
		<div class="row gap-5 justify-content-center mt-3">
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/5efb/fd48acde4c9b97aa39e55704c0c100cc95fc22024dc80dc1a6e14801cdfd.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/0863/8360e74209bb76ccb87369df804895c456907e9b27ad2970e0c8cfa49814.png" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail8.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/4e1a/b08b56f9e4a8995cd3f9298a1cacbd28bf09b57198aaec82f91a1f703126.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
		</div>
		
		<!-- 게시글 -->
		<div class="row gap-5 justify-content-center mt-3">
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail9.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/5efb/fd48acde4c9b97aa39e55704c0c100cc95fc22024dc80dc1a6e14801cdfd.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/0863/8360e74209bb76ccb87369df804895c456907e9b27ad2970e0c8cfa49814.png" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
			<div class="card" style="width: 30%;">
				<img src="https://thumbnail8.coupangcdn.com/thumbnails/remote/230x230ex/image/vendor_inventory/4e1a/b08b56f9e4a8995cd3f9298a1cacbd28bf09b57198aaec82f91a1f703126.jpg" class="card-img-top" alt="...">
			  	<div class="card-body d-flex justify-content-between">
			  		<div class="d-flex align-items-center">
				    	<img class="float-start me-4" src="resources/image/dog1.jpg" alt="" style="height: 50px; width: 50px; border-radius: 50px;">
						<div class="header-text-container pt-3">
							<h6>글제목</h6>
							<p>작성자</p>
						</div>
			  		</div>
					<div class="d-flex align-items-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16" role="button">
							<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"/>
						</svg>
						<div class="ms-3">53</div>
					</div>
			  	</div>
			</div>
		</div>
		
	</div>
	
	
	<script>
		// 해당 페이지 nav bar 강조 넣기
		// 페이지 별로 id만 수정 하면 됨 eg.) topAdd1, topAdd2, ...
		const boardNav = document.getElementById("topAdd6");
		$(boardNav).addClass('active');
		
		// 카드에 클릭 이벤트 넣어주는 부분
		const cards = document.getElementsByClassName("card");
		for (const card of cards) {
			card.addEventListener('click', () => {
				window.location.href = "${ contextPath }/board/used/detail"
			})
		}
	</script>
</body>
</html>