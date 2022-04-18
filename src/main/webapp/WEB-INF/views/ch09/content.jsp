<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Form 태그를 이용한 FileUpload
			</div>
			<div class="card-body">
				<form method="post" enctype="multipart/form-data" action="fileupload">
					<div class="form-group">
						<label for="title">File Title</label> 
						<input type="text" class="form-control" id="title" name="title" placeholder="제목">
					</div>
					<div class="form-group">
						<label for="desc">File Description</label> 
						<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
					</div>
					<div class="form-group">
					    <label for="attach">Example file input</label>
					    <input type="file" class="form-control-file" id="attach" name="attach" multiple="multiple"> <!-- multiple="multiple"을 사용하면 여러개의 파일을 선택할 수 있다. -->
				  	</div>
				  	<button class="btn btn-info btn-sm">Form 파일 업로드</button>
				  	<a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a><!-- 함수실행 -->
				</form>
			</div>
			<script>
				function fileupload() { //ajax로 multipart 얻어내는 방법
					//입력된 정보를 얻기
					const title = $("#title").val();
					const desc = $("#desc").val();
					const attach = document.querySelector("#attach").files[0]; //엘리먼트 객체로 사용해야 한다.!!($("attach")[0] 이렇게 사용할 수도 있다. jquery속에 있는 배열의 element 사용) 그냥 jquery객체로 사용하면 안됨. 
					//files[0] 은 여러개의 사진 중 첫번째 사진을 선택할 때 사용하는데, 사진이 하나일 때에도 files[0]을 사용해야 한다.
					
					
					//Multipart/form-data
					const formData = new FormData();
					formData.append("title", title);
					formData.append("desc", desc);
					formData.append("attach", attach);
					
					//Ajax로 서버로 전송
					$.ajax({
						url: "fileuploadAjax",
						method: "post",
						data: formData,
						//3개의 설정 중 하나라도 빠지면 실행x,
						//processData 쿼리 스트링 형태로 만들지 말아라. => title=xxx&dexc=yyy& 식으로 만들지 마라.
						cache: false,		//파일이 포함되어 있으니, 브라우저 메모리에 저장하지 마라.
						processData: false, //title=xxx&dexc=yyy& 식으로 만들지 마라.
						contentType: false  //파트마다 Content-Type이 포함되기 때문에 따로 헤더에 Content-Type에 추가하지 마라 
					}).done((data) => {
						console.log(data);
						if(data.result === "success") {
							window.alert("파일 전송이 성공됨");
						}
					});
				}
			</script>
		</div>
	
		<div class="card">
			<div class="card-header">
				File Downlaod
			</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1"class="btn btn-info btn-sm" onclick="filedownload(1)">파일 다운로드</a> <!-- 클릭이 먼저 실행되고 href가 실행 됨. -->
				<hr/>
				<img id="downloadImg" width="200px"/>
				<img width="200px"/>
			</div>
			<script>
				function filedownload(fileNo){
					$("downloadImg").attr("src", "filedownload?fileNo=" + fileNo);
				}
			</script>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>