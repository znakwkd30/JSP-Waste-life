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

        <!-- 기능 구역 -->
        	<c:if test="${not empty projectlist}">
				<div class="uk-grid-medium uk-flex-wrap uk-flex-wrap-around uk-child-width-1-3" uk-grid>
					<c:forEach var="member" items="${memberlist}">
						<div class="uk-width-1-3">
							<div class="uk-card uk-card-default">
								<div class="uk-card-header">
									<div class="uk-grid-small uk-flex-middle" uk-grid>
										<div class="uk-width-expand">
											<p>내가 팔로우 한 사람</p>
											<h2 class="uk-card-title uk-margin-remove-bottom fonted"><span uk-icon="icon: user; ratio: 2"></span>${member.friendId}</h2>
											<script>
											function unfollow(){
									     		if(confirm("언팔로우 하시겠습니까?") == true){
									     			alert("언팔로우했습니다.");
									     			return true;
									     		}
									     		else{
									     			alert("언팔로우 하지 않았습니다.");
									     			return false;
									     		}
									     	}
											</script>
											<form action="friend" method="post" onsubmit="return unfollow();">
												<input class="uk-button uk-button-primary" class="uk-margin-left" type="submit" value="UNFOLLOW" onClick="">
												<input type="hidden" value="${member.friendId}" name="id">
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
</body>

</html>