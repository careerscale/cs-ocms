<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<h1>
	<s:message code="register.login.title" />
</h1>
<tiles:putAttribute name="pageTitle" value="${register.login.title}" />

<p>login with registered account</p>



<form method="POST" id="login" action="j_spring_security_check">
	<div class="row">

		<label for="j_username">Email Id</label> <input type="text"
			value="hari@harinath.in" name="j_username" />
	</div>

	<div class="row">
		<label for="j_password">Enter password</label> <input type="password"
			value="test123" name="j_password" />
	</div>

	<div class="row">
		<input type="submit" class="button round" value="Login" /> <a
			href="/forgotpassword" class="button">Forgot password? </a>
			No account yet?<a href="/register"
			class="button round">Register</a>
	</div>

	<div class="row">
		<label>Login with</label>
	</div>
	<div class="row">
		<a href="/login-facebook" class="button round">Facebook</a> <a
			href="/login-google" class="button round">Google</a>
			<a href="/login-linkedin" class="button round">LinkedIn</a> <a
			href="/login-twitter" class="button round">Twitter</a>
	</div>

</form>

   <!-- 
	<div class="socialconnect">
		<h5 class="margin-15">Use your social network</h5>
		<div id="social-connect">
			<div class="logo">
				<a href="/login-facebook"><img
					src="resources/images/facebook.png" width="78" height="50"
					alt="facebook" /></a> <a href="/login-google"><img
					src="resources/images/google..jpg" width="78" height="50"
					alt="google" /></a> <a href="/login-twitter"><img
					src="resources/images/twitter.png" width="78" height="50"
					alt="google" /></a> <a href="/login-linkedin"><img
					src="resources/images/linkedin.jpg" width="78" height="50"
					alt="google" /></a>
			</div>

		</div>
	</div>
    -->


</body>