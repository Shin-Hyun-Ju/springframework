<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 응답 바디에 들어가는 내용이 어떤 타입인지 contentType으로 명시 --%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="card m-2">
	<div class="card-header">JSP 템플릿 동작 이해(~.jsp -> ~.java ->~.class)</div>
	<div class="card-body">
		index.jsp
		<hr />
		오늘 날짜:
		<%=request.getAttribute("strDate")%>
		<!-- setAttribute로 줬던 값을 getAttribute로 가져온다. -->
		오늘 날짜: ${strDate}
		<%-- Expression Language(EL) 변수 이름에 대한 키만 부여하면 된다.  --%>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">객체 저장 범위</div>
	<div class="card-body">
		<p>request 범위 객체 값: <%=request.getAttribute("requestScopeValue") %></p> <%-- request 범위에서 requestScopeValue를 찾아와라 --%>
		<p>session 범위 객체 값: <%=session.getAttribute("sessionScopeValue") %></p>
		<p>application 범위 객체 값: <%=application.getAttribute("applicationScopeValue") %></p>
		<hr>
		<%-- 찾는 순서: request 범위에서 먼저 찾고 session범위 -> application 범위 순으로 찾음. --%>
		<p>
			request 범위 객체 값: ${requestScopeValue}<br>
			member's name: ${member.name} <br> <%-- member.getName() 의 뜻! --%>
			member's job: ${member.job}<br>
			member's age: ${member.age}<br>
			member's city: ${member.city.name}<br>
		</p>
		
		<hr>

		<p> 
			session 범위 객체 값: ${sessionScopeValue}
			member2's name: ${member2.name} <br> <%-- member.getName() 의 뜻! --%>
			member2's job: ${member2.job}<br>
			member2's age: ${member2.age}<br>
			member2's city: ${member2.city.name}<br>
		</p>
		<p>
			application 범위 객체 값: ${applicationScopeValue} <br>
			방문 카운팅: ${counter}
		</p>
		<hr/>
		<a href="requestScopeSave" class="btn btn-info btn-sm mr-2">request 범위에 객체 저장</a>
		<a href="sessionScopeSave" class="btn btn-info btn-sm mr-2">session 범위에 객체 저장</a>
		<a href="applicationScopeSave" class="btn btn-info btn-sm mr-2">application 범위에 객체 저장</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">JSTL(Java Standard Tag Library)</div>
	<div class="card-body">
		<a href="useJstl1" class="btn btn-info btn-sm mr-2">JSTL 사용하기</a>
		<a href="useJstl2" class="btn btn-info btn-sm mr-2">JSTL 사용하기</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">ModelAndView로 객체 전달</div>
	<div class="card-body">
		<a href="modelAndViewReturn" class="btn btn-info btn-sm mr-2">ModelAndView 리턴</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">Model 매개변수로 객체 전달</div>
	<div class="card-body">
		<a href="modelArgument" class="btn btn-info btn-sm mr-2">Model 매개변수로 객체 전달</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">@ModelAttribute로 객체 전달</div>
	<div class="card-body">
		<a href="modelAttribute?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">@ModelAttribute로 객체 전달</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">Command(DTO) 객체로 전달</div>
	<div class="card-body">
		<a href="commandOject?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2">Command(DTO) 객체로 전달</a>
	</div>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>