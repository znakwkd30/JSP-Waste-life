<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<title>SNS</title>
		<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
		<link rel="stylesheet" type="text/css" href="css/uikit/uikit.min.css">
		<script src="js/uikit.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/new.css"/>
		<script src="js/uikit-icons.min.js"></script>
		<script src="js/jquery.js"></script>
	</head>
	<body>
		<c:if test="${empty user}">
			<c:redirect url="login.jsp"/>
		</c:if>
		<jsp:include page="head.jsp" flush="flase"/>
		<div class="uk-align-center uk-width-1-2">
		<h2 class="uk-text-primary fonted">팔로우</h2>
		<form id="newform" action="follow" method="post" name="newform" onsubmit="return follow();">
			<ul class="uk-list uk-list-striped">   
			     <li class="uk-margin-small-bottom">
			     <script src="js/jquery.datetimepicker.full.min.js"></script>
			     <script>			     
			     
			     	function popup(){
		    	    	window.open('searchmember','searchmember','width=400, height=500');
		    	    }
			     	function follow(){
			     		if(confirm("팔로우 하시겠습니까?") == true){
			     			alert("팔로우했습니다.");
							return true;
			     		}
			     		else{
			     			alert("팔로우 하지 않았습니다.");
			     			return false;
			     		}
			     	}
			     </script>
			     	<div class="uk-margin">
				    	<label class="uk-form-label" for="members">Follow (클릭)</label>
				        	<div class="uk-form-controls">
				           		<input class="uk-input" id="members" type="text" name="members" onclick="popup();" value="${user.id};">
				        	</div>
				   	</div>
				</li>
				<li>
					<input type="submit" value="Follow" onClick="" class="uk-button uk-button-primary">
				</li>	
			</ul>
		</form>   
		</div>
	</body>
</html>