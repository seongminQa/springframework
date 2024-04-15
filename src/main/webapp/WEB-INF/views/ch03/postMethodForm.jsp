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
						<div class="card-header">postMethodForm</div>
						<div class="card-body">
						
							<form method="post" action="receiveParamData">
							   <div class="input-group">
							      <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
							      <input type="text" name="param1" class="form-control" value="문자열">
							   </div>
							   <div class="input-group">
							      <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
							      <input type="text" name="param2" class="form-control" value="5" >
							   </div>
							   <div class="input-group">
							      <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
							      <input type="text" name="param3" class="form-control" value="3.14">
							   </div>
							   <div class="input-group">
							      <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
							      <div class="btn-group btn-group-toggle" data-toggle="buttons">
							         <label class="btn btn-secondary active">
							           <input type="radio" name="param4" checked value="true"> true
							         </label>
							         <label class="btn btn-secondary">
							           <input type="radio" name="param4" value="false"> false
							         </label>
							      </div>
							   </div>
							   <div class="input-group">
							      <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
							      <input type="date" name="param5" class="form-control" value="2030-12-05">
							   </div>
							   <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
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