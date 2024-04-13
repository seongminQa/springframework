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
		
		<!--  -->
		<script type="text/javascript">
			function handleBtn1() {
				console.log("handleBtn1() 실행");
				// 상대경로 
				window.location.href = "getMethod?chNum=ch02&bkind=qAndA&bno=200";
				// ?chNum=ch02&bkind=qAndA&bno=200는 쿼리 스트링
			}
			function handleBtn2() {
				   console.log("handleBtn2() 실행");
				   $.ajax({
				      url: "getMethodAjax",
				      //method: "get", // method 대신에  type: "get" 을 사용해도 된다.
				      type: "get",
				      //data: "bkind=notice&bno=300",
				      data: {bkind:"notice", bno:300}, // 이렇게 사용하는 경우가 더 많다.
				      success: function(data) {
				    	  console.log(data);
				         $("#ajaxResponseInclude").html(data);
				      }
				   });
			}
			function handleBtn3() {
				   console.log("handleBtn3() 실행"); // 1번
				   
				   $.get("getMethodAjax", {bkind:"notice", bno:300}, function(data) {  // 2번 or 4번
				         $("#ajaxResponseInclude").html(data);
				      });
				   
				   console.log("~~~~");  // 3번
				   // 비동기 처리 방식이기 때문에 1번->3번->2번 순으로 실행될 수도 있고, 1번->2번->3번 순으로 응답 처리될 수도 있다.
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
						<div class="card-header">getMethod</div>
						<div class="card-body">
							<form class="m-2" method="get" action="getMethod">
							   <input type="hidden" name="chNum" value="ch02"/>
							   <div class="form-group mb-2">
							       <label for="bkind">bkind</label>
							       <input type="text" class="form-control" id="bkind" name="bkind" value="free">
							   </div>
							   
							   <div class="form-group mb-2">
							       <label for="bno">bno</label>
							       <input type="text" class="form-control" id="bno" name="bno" value="1">
							   </div>
							   <!-- 제출 버튼: 양식의 데이터를 서버로 보내겠다. -->
							   <!-- <input type="submit" value="GET방식" class="btn btn-info btn-sm"/>
							   <button type="submit" class="btn btn-info btn-sm">GET방식</button> -->
							   <input type="image" 
							   src="${pageContext.request.contextPath}/resources/image/submit_button.png"
							   width="100" height="30"/>
							</form>
							
							<hr/>
							
							<button onclick="handleBtn1()" type="button" class="btn btn-success btn-sm">JavaScript로 요청</button>
							<button onclick="handleBtn2()" type="button" class="btn btn-success btn-sm">AJAX로 요청</button>
							<div id="ajaxResponseInclude" class="mt-2">
								
							</div>
						</div>
					</div>
					<!-- #################################### -->
				</div>
			</div>
		</div> 
	</div>
</body>
</html>