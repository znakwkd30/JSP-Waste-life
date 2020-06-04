<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="uk-background-muted">

<head>
	<title>SNS</title>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="css/main.css" />
    <script src="js/uikit.min.js"></script>
    <script src="js/uikit-icons.min.js"></script>
    <script src="js/jquery.js"></script>
</head>

<body>
	<c:if test="${empty user}">
		<c:redirect url="login.jsp" />
	</c:if>
	<jsp:include page="head.jsp" flush="flase" />
    <div class="uk-container uk-container-expand uk-margin-top uk-animation-fade">
        <ul class="uk-breadcrumb">
            <li><a href=""></a>Timeline</li>
            <li><span>${project.name}</span></li>
        </ul>
        <div class="uk-grid-medium uk-flex-wrap uk-flex-wrap-around uk-child-width-expand@s uk-grid-match" uk-grid>
            <div class="uk-width-1-3">
                <div class="uk-card uk-card-primary uk-card-body uk-card-hover">
                	<h1 class="uk-text-large fonted"><span uk-icon="icon: facebook; ratio: 2"></span> <span class="uk-text-middle fonted">제목</span></h1>
                    <h2 class="uk-text-large fonted">${project.name}</h2>
                </div>
            </div>
            <div class="fl-left uk-margin-left uk-height-2-3 uk-margin-remove-left">
                <div class="uk-card uk-card-default uk-card-body uk-height-2-3 uk-card-hover">
                	<div class="uk-card uk-card-default uk-card-body uk-card-hover">
                    <h3 class="uk-card-title uk-card-large"> <span uk-icon="icon: comments; ratio: 2"></span> <span class="uk-text-middle fonted">내용</span></h3>
       					
                    <h1 class="uk-text-large fonted">${project.subject}</h1>
                	</div>
                </div>
            </div>
        </div>

        <div class="uk-grid-medium uk-flex-wrap uk-flex-wrap-around uk-child-width-expand@s uk-margin-top" uk-grid>
            <div class="uk-width-1-3">
                <div class="uk-card uk-card-default uk-card-body uk-card-hover">
                    <h3 class="uk-card-title uk-card-large"> <span uk-icon="icon: users; ratio: 2"></span> <span class="uk-text-middle fonted">태그된 멤버</span></h3>
                    <hr />
                    <c:if test="${empty projectmember}">
                        	멤버가 없습니다.
                    </c:if>
                    <c:if test="${not empty projectmember}">
                        <table class="uk-table uk-table-hover uk-table uk-table-divider">
                            <tbody>
                                <c:forEach var="pm" items="${projectmember}">
                                    <tr>
                                        <td>
                                            ${pm.name}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
            <a href="twitter" class="uk-width-1-3 no-line">
                <div class="uk-card uk-card-default uk-card-body uk-card-hover">
                    <h3 class="uk-card-title uk-card-large"> <span uk-icon="icon: comments; ratio: 2"></span> <span class="uk-text-middle fonted">댓글</span> <span class="uk-align-right uk-text-middle" uk-icon="icon: plus"></span></h3>
                    <hr />
                    <ul class="uk-list uk-list-divider">
                        <c:forEach var="tl" items="${timeline}">
                            <li>${tl.memberId} : ${tl.comment}<br />${tl.timestamp}</li>
                        </c:forEach>
                    </ul>
                </div>
            </a>
            <a href="file" class="uk-width-1-3 no-line">
                <div class="uk-card uk-card-default uk-card-body uk-card-hover">
                    <h3 class="uk-card-title uk-card-small uk-text-middle"> <span uk-icon="icon: file-text; ratio: 2"></span> <span class="uk-text-middle fonted">파일</span> <span class="uk-align-right uk-text-middle" uk-icon="icon: plus"></span></h3>
                    <hr />
                    <c:if test="${not empty files}">
                        <table class="uk-table uk-table-hover uk-table uk-table-divider">
                            <tbody>
                                <c:forEach var="f" items="${files}">
                                    <tr>
                                        <td>
                                            ${f.originalFileName}<br />(${f.timestamp})
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </a>
        </div>
    </div>
</body>

</html>