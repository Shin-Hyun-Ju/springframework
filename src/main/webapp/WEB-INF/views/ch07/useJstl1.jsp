<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 원래 html태그가 아니라 외부 라이브러리가 제공해 주는 libray 쓰겠다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%-- c붙여서 쓰기 --%>

<%-- 응답 바디에 들어가는 내용이 어떤 타입인지 contentType으로 명시 --%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="card m-2">
	<div class="card-header">배열 반복 처리</div>
	<div class="card-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No</th>
					<th>Language</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${langs}" var="lang" varStatus="status">
				<!-- ${lang} 배열 -->
				<!-- lang 배열 원소 하나하나를 지칭하는 변수 -->
				<!-- status for문이 몇번 반복하는지에 대한 상태변수 -->
					<tr>
						<td>${status.count}</td>
						<td>${lang}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>