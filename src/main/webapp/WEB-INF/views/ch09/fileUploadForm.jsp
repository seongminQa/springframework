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
			// 방법 1
			/* $(function(){  // 콜백 함수  // body부분의 태그들이 모두 로드된 후 실행된다.
				$("#btnAjax").click( () => {  // 화살표 함수 사용
					console.log("btnAjax 클릭됨");
				});
			}); */
			
			// 방법 2
			/* $(function(){
				$("#btnAjax").on("click", () => {
					console.log("btnAjax 클릭됨");
				});
			}); */
			// 방법 3
			/* $(function(){
				document.querySelector("#btnAjax").addEventListener("click", () => {
					console.log("btnAjax 클릭됨");
				});
			}); */
			
			$(function () {
	           $("#btnAjax").click(() => {
	              var formData = new FormData();
	              formData.append("title", $("#title").val());
	              formData.append("desc", $("#desc").val());
	              formData.append("attach", $("#attach")[0].files[0]);
	              
	              $.ajax({
	                 url: "fileUploadAjax",
	                 method: "post",
	                 data: formData,
	                 cache: false,
	                 processData: false,
	                 contentType: false,
	                 success: function(data) {
	                    console.log(data);
	                    // window.alert("파일 업로드 성공"); // 브라우저마다 모양도 틀리다.. 가급적이면 쓰지 말자.
	                    const modal = new bootstrap.Modal('#fileUploadModal');
	                    modal.show();
	                 }
	              });
	           });
	        });


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
						<div class="card-header">파일 업로드 폼</div>
						<div class="card-body">
							<!-- p80 학습 -->
							<form method="post" enctype="multipart/form-data" action="fileUpload">
							   <div class="form-group">
							      <label for="title">File Title</label> 
							      <input type="text" class="form-control" id="title" name="title" placeholder="제목">
							   </div>
							   <div class="mt-2 form-group">
							      <label for="desc">File Description</label> 
							      <input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
							   </div>
							   <div class="mt-2 form-group">
							       <label for="attach">첨부파일</label>
							       <input type="file" multiple="multiple" class="form-control-file" id="attach" name="attach">
							   </div>
							     
							   <!-- 방법1 -->
							   <button class="mt-2 btn btn-info btn-sm">Form 파일 업로드</button>
							   <!-- 방법2 // 폼 밑의 버튼은 submit 효과가 있다. 하지만 type="button"을 해주면 일반 버튼이 된다. -->
							   <!-- <button type="button" onclick="fileUpload()">AJAX 파일 업로드</button> -->
							   <button id="btnAjax" type="button" class="mt-2 btn btn-info btn-sm">AJAX 파일 업로드</button>
							</form>
							
						</div>
					</div>
					<!-- #################################### -->
				</div>
			</div>
		</div> 
	</div>
	<!-- The Modal -->
	<div class="modal" id="fileUploadModal">
	  <div class="modal-dialog modal-dialog-centered modal-sm">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">알림</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        	파일 업로드 성공
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger btn-sm" data-bs-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>
</body>
</html>