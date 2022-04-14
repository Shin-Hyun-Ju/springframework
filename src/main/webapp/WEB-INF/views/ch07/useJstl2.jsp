<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%-- 응답 바디에 들어가는 내용이 어떤 타입인지 contentType으로 명시 --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div class="card m-2">
	<div class="card-header">JSTL을 이용해서 List 반복 처리</div>
	<div class="card-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Content</th>
					<th>Writer</th>
					<th>Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardlist}" var="board" varStatus="status">
					<tr>
					<th>${board.no}</th>
					<th>${board.title}</th>
					<th>${board.content}</th>
					<th>${board.writer}</th>
					<th><fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd"/></th>
				</tr>
				
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>