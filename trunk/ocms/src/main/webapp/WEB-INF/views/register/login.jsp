<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<h1>
	<s:message code="register.login.title" />
</h1>
<tiles:putAttribute name="pageTitle" value="${register.login.title}" />
<div id="page-content-inner1" class="resize">
<div id="login-box">
<div id="login-intro">
<h3>joining OCMS is free and easy! being a member you can help in many ways.</h3>
</div>

	<form id="login-register" action="/j_spring_security_check" method="POST">
	<fieldset>
	<h5>login with registered account</h5>
	            <div id="login-form" >
					<table align="center" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="label"><label for="username">Email Id:</label></td>
						<td class="field"><input id="username" name="j_username"
							class="required email" type="text" tabindex="1" /></td>
					</tr>
					<tr>
						<td class="label"><label for="password">Password:</label></td>

						<td class="field"><input id="password" class="required"
							name="j_password" type="password" tabindex="2" /></td>

					</tr>
					<tr>
						<td><div class="buttonSubmit">
								<input class="formButton" type="submit" value="Signin"
									style="width: 120px" tabindex="3" />
							</div></td>
						<td><span class="registerlink"><a href="/register">
									Register</a></span></td>
								

					</tr>
					</table>
					</div>
	
	</fieldset>
	<div class="socialconnect">
	<h5 class="margin-15">Use your social network</h5>
	<div id ="social-connect">
	<div class="logo">
			<a href="/login-facebook"><img src="resources/images/facebook.png"
				width="78" height="50" alt="facebook" /></a>
		
		 <a href="/login-google"><img src="resources/images/google..jpg"
				width="78" height="50" alt="google" /></a>
				
				<a href="/login-twitter"><img src="resources/images/twitter.png"
				width="78" height="50" alt="google" /></a>
				
				<a href="/login-linkedin"><img src="resources/images/linkedin.jpg"
				width="78" height="50" alt="google" /></a>
		</div>
		
	</div>
	</div>
	</form>
	</div>

</div>


</body>