<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 원래 html태그가 아니라 외부 라이브러리가 제공해 주는 libray 쓰겠다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 응답 바디에 들어가는 내용이 어떤 타입인지 contentType으로 명시 --%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">ModelAndView 또는 Model을 이용한 데이터 전달</div>
	<div class="card-body">
		<p>no: ${board.no}</p>
		<p>title: ${board.title}</p>
		<p>content: ${board.content}</p>
		<p>writer: ${board.writer}</p>
		<p>date: <fmt:formatDate value="${board.date}" pattern="yyyy.MM.dd"/></p>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>