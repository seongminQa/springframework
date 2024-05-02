<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Insert title here</title>

    <!-- Bootstrap 5를 위한 외부 라이브러리 설정 -->
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <!-- jQuery -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

    <!-- css -->
    <link rel="stylesheet" href=""/>

    <!-- 사용자 정의 자바스크립트 -->
    <script></script>

    <!-- 내부 css 적용 (테스트용) -->
    <style>

    </style>
  </head>
	<body>
		<div class="d-flex flex-column vh-100"><!-- viewport height -->
		<%@include file="/WEB-INF/views/common/header.jsp"%>
		<div class="flex-grow-1 m-2">
			<div class="d-flex row">
				<div class="col-md-4">
					<%@include file="/WEB-INF/views/common/menu.jsp"%>				
				</div>
				
				<div class="col-md-8">
					<!-- #################################### -->
					<div class="card">
						<div class="card-header">로그인</div>
						<div class="card-body">
						
							<form method="post" action="${pageContext.request.contextPath}/login">
								<!-- 시큐리티의 위조 방지를 위한 토큰번호. -->
							   <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
							   <div class="form-group mb-2">
							       <label for="mid">Member ID</label>
							       <input type="text" class="form-control" id="mid" name="mid">
							   </div>
							   <div class="form-group mb-2">
							      <label for="mpassword">Member Password</label>
							      <input type="password" class="form-control" id="mpassword" name="mpassword">
							   </div>
							   <button type="submit" class="btn btn-info btn-sm mt-2">로그인</button>
							</form>   
				            
						</div>
					</div>
					<!-- #################################### -->
				</div>
			</div>
		</div> 
	</div>
	</body>
</html>