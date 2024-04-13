<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			function handleBtn1() {
				// 추가 실습
				var mid = $("#mid").val();
				var mpassword = $("#mpassword").val();
				
				console.log("handleBtn1() 실행");
				console.log(mid);
				console.log(mpassword);
				
				$.ajax({
					url: "postMethodAjax",
					type: "post",
					// data: "mid=summer&mpassword=67890",
					// data: "mid=" + mid + "&mpassword=" + mpassword,  // 변수를 이용하여 사용할 경우
					// data: {mid:"summer", mpassword:67890},
					// data: {mid: mid, mpassword: mpassword}, // 변수의 이름을 이용하여 사용할 경우
					// 속성의 이름과 변수의 이름이 같을 경우 이렇게 사용 가능하다.
					// 위의 코드 중 편한 것 사용... 
					data: { mid, mpassword },  
					success: function(data) {
						// $("#ajaxResponseInclude").html(data);
						
						//data: {"result":"success"}
						//data: {"result":"fali", "reason":"wrongMid"}
						//data: {"result":"fali", "reason":"wrongMpassword"}
						// 자동으로 JSON => JavaScript Object 변환
						console.log(data);
						
						if(data.result === "success"){
							$("#ajaxResponseInclude").html("로그인 성공");
						} else {
							if(data.reason === "wrongMid") {
								$("#ajaxResponseInclude").html("아이디가 존재하지 않습니다.");
							} else {
								$("#ajaxResponseInclude").html("비밀번호가 틀립니다.");
							}
						}
						
					}
				});
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
						<div class="card-header">modelAndView 타입 리턴</div>
						<div class="card-body">
							<p>장번호: ${ chNum }</p>
							<p>로그인: ${ login }</p>
							<p>사용자: ${ userName }</p>
						</div>
					</div>
					<!-- #################################### -->
				</div>
			</div>
		</div> 
	</div>
</body>
</html>