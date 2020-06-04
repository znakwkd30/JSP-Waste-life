<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html class="uk-background-muted">

<head>
	<meta charset="UTF-8">
	<title>SNS</title>
	<link rel="stylesheet" href="css/home.css" />
	<script src="js/uikit.min.js"></script>
	<script src="js/uikit-icons.min.js"></script>
	<script src="js/home.js"></script>
</head>

<body>
	<c:if test="${empty user}">
		<c:redirect url="login.jsp" />
	</c:if>
	<jsp:include page="head.jsp" flush="flase" />

	<div class="uk-container uk-container-expand uk-margin-top uk-animation-fade">
		<h2 class="uk-margin-bottom fonted">${user.name}님, 환영합니다!</h2>
		<hr />
		<div class="uk-card uk-card-default uk-card-body uk-card-hover uk-margin-top">
			<div uk-sticky="animation: uk-animation-slide-top; sel-target: .uk-navbar-container; cls-active: uk-navbar-sticky; cls-inactive: uk-navbar-transparent uk-dark; top: 200">
				<nav class="uk-background-default uk-navbar-container uk-margin" uk-navbar>
					<div class="uk-navbar-left">
						<h3 class="fonted">타임라인</h3>
					</div>
					<div class="uk-navbar-right">
						<div class="uk-margin-right">
							<form class="uk-search uk-search-default">
								<a href="" class="uk-search-icon-flip" uk-search-icon></a>
								<input class="uk-search-input" type="search" placeholder="Search...">
							</form>
						</div>
						<div class="uk-margin-right">
							<form action="new" method="get" class="addbtn">
								<input type="submit" class="uk-input addbtn uk-button-primary" value="글쓰기">
							</form>
						</div>
						<form action="follow" method="get" class="addbtn">
								<input type="submit" class="uk-input addbtn uk-button-primary" value="Follow">
						</form>
					</div>
				</nav>
			</div>
			<c:if test="${empty projectlist}">
				타임라인이 없습니다.
			</c:if>
			<c:if test="${not empty projectlist}">
				<div class="uk-grid-medium uk-flex-wrap uk-flex-wrap-around uk-child-width-1-3" uk-grid>
					<c:forEach var="p" items="${projectlist}">
						<div class="uk-width-1-3">
							<div class="uk-card uk-card-default">
								<div class="uk-card-header">
									<div class="uk-grid-small uk-flex-middle" uk-grid>
										<div class="uk-width-expand">
											<h2 class="uk-card-title uk-margin-remove-bottom fonted">${p.name}</h2>
											<p>${p.subject}</p>
											<a href="heart?timelineId=${p.id}" onclick="document.getElementById('regform').submit();" uk-icon="icon: heart; ratio: 2" ></a><span class="uk-text-middle fonted">${p.heartCnt}</span>
										</div>
									</div>
								</div>
								<div class="uk-card-footer">
									<a href="select?projectid=${p.id}" class="uk-button uk-button-text">타임라인 진입 <span uk-icon="arrow-right"></span></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
		</div>
	</div>
</body>

</html>
