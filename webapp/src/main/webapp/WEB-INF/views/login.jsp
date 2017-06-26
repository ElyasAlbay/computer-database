<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="label.title" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="utf-8">
	<link href="resources/css/login.css" rel="stylesheet" type="text/css"
		media="screen" />
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Login Form</h1>

	<div id="login-box">

		<h2>Login with Username and Password</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form id='loginForm' name='loginForm'
			action="<c:url value='login' />" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='user' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='pass' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>