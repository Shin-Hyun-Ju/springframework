<%@ page contentType="text/html; charset=UTF-8" %>

<!-- 단순하게 소스 복사 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %> <!-- header.jsp에 tag.library가 들어가 있으면 여기에서도 사용할 수 있다. -->

<div class="card m-2">
	<div class="card-header">
		DTO 객체(Command Object)와 폼 연결
	</div>
	<div class="card-body">
		<form:form modelAttribute="member"> <!-- Dto인 member가 들어오게 된다. -->
		  <div class="form-group">
		    <label for="mid">ID</label>
		    <form:input class="form-control" path="mid"/> <!-- <input id="mid" name="mid" class="form-control" type="text" value="spring"> -->
		  </div>
		  <div class="form-group">
		    <label for="mname">Name</label>
		    <form:input class="form-control" path="mname"/>
		  </div>
		  <div class="form-group">
		    <label for="mpassword">Password</label>
		    <form:password class="form-control" path="mpassword"/>
		  </div>
		   <form:hidden class="form-control" path="mnation"/>
		  <button class="btn btn-primary">Submit</button>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>