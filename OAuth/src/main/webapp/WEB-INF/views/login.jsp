<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form class="loginForm" method="post">
	<div class="loginDiv">
		<div>
			<p><label><b>ID</b></label> <input type="text" id="inputId" name="j_username" placeholder="ID" required maxlength="20" /></p>
			<p><label><b>PW</b></label> <input type="password" id="inputPw" name="j_password" placeholder="Password" required maxlength="20"/></p>
		</div>
		<div>
			<Button type="button" onclick="login()" id="loginBtn"><b>로그인</b></Button>
		</div>
	</div>
</form>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
function login(){
	var loginForm = $('.loginForm')[0];
	loginForm.action = "${pageContext.request.contextPath}/j_spring_security_check";
	loginForm.submit();
}
/* Login Exception */
if('${errorMessage}'!=''){
	alert('${errorMessage}');
}
</script>

</body>
</html>