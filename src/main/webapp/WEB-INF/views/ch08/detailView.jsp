<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
		<!-- Bootstrap 5를 위한 외부 라이브러리 설정 -->
		<!-- Latest compiled and minified CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<!-- Latest compiled JavaScript -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
		
		<!-- jQuery -->
		<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
		
		<!-- 사용자 정의 자바스크립트 -->
		<script>
			function addCartItem() {
				var pno = ${product.pno};  // ??? 서버에 값을 넣어주는 것. // 이해하기 // 서버에서 값을 집어넣어주는 코드
				var amount = $("#amount").val();  // 클라이언트에서 실행하는 것. // 위의 코드와 실행위치가 다르다. // 공부
				document.location.href = "addCartItem?pno=" + pno + "&amount=" + amount; // 값이 2개 넘어감. // 폼 태그 사용하지 않고 넘기는 방식
				
			}
		</script>
</head>
<body>

	<div class="d-flex flex-column "><!-- viewport height -->
		<%@include file="/WEB-INF/views/common/header.jsp"%>
		<div class="flex-grow-1 m-2">
			<div class="d-flex row">
				<div class="col-md-4">
					<%@include file="/WEB-INF/views/common/menu.jsp"%>				
				</div>
				
				<div class="col-md-8">
					<!-- #################################### -->
					<div class="card">
						<div class="card-header">상품 상세 정보</div>
						<div class="card-body">
							<p>상품 번호: ${product.pno}</p>
							<p>상품 이름: ${product.pname}</p>
							<p>상품 가격: ${product.pprice}</p>
							<hr/>
							<div>
								수량: <input type="number" id="amount" value="1"/>
							</div>
							<button onclick="addCartItem()" class="mt-3 btn btn-info btn-sm">장바구니 넣기</button>
						</div>
					</div>
					<!-- #################################### -->
				</div>
			</div>
		</div> 
	</div>
</body>
</html>