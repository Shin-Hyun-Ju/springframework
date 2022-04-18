<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>Document</title>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js" ></script> <!-- slim들어가 있으면 ajax 포함 x -->
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" ></script>
	    <link rel="stylesheet" href="/webapp/resources/css/app.css"/>
	</head>
	<body>
		<div class="d-flex flex-column vh-100"> <!-- viewport + height -->
			<nav class="navbar navbar-dark bg-dark font-weight-bold"> <!-- 디폴트로 justify-content-between(양끝정렬) 되어있음 -->
				<a class="navbar-brand" href="#">
    				<img src="${pageContext.request.contextPath}/resources/images/logo-spring.png" width="30" height="30"> Spring
    				<%-- <img src="<%=request.getContextPath()%>/resources/images/logo-spring.png" width="30" height="30">Spring		 --%>	
  				</a>
				<div>
					<c:if test="${sessionMid == null}">
						<a href="${pageContext.request.contextPath}/ch08/login" class="btn btn-success btn-sm">로그인</a>
					</c:if>
					<c:if test="${sessionMid != null}">
						<b class="mr-2" style="color:white">User ID: ${sessionMid}</b>
						<a href="${pageContext.request.contextPath}/ch08/logout" class="btn btn-success btn-sm">로그아웃</a>
					</c:if>
				</div>
			</nav>
			<div class="container-fluid flex-grow-1">	<!-- viewport ê½ ì±ìì¤ë¤. -->
				<div class="row h-100"> <!-- h-100 ë¬´ì¨ìë¯¸? -->
					<div class="col-md-4 bg-dark p-3">
						<div class="h-100 d-flex flex-column">
							<div class="flex-grow-1" style="height: 0px; overflow-y: auto; overflow-x: hidden;">
								<%@ include file="/WEB-INF/views/common/menu.jsp" %>
							</div>
						</div>
					</div>
					<div class="col-md-8 p-3">
						<div class="h-100 d-flex flex-column">
							<div class="flex-grow-1 pr-3" style="height: 0px; overflow-y: auto; overflow-x: hidden;">

