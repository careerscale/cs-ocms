
<body>
<div class="login1">
	<form action="/j_spring_security_check" method="POST">

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
				<td><p><div class="buttonSubmit1">
						<input class="formButton" type="submit" value="Signin"
							style="width: 120px" tabindex="3" /></p>
				  </div></td><td><span class="registerlink"><a href="/register"> Register</a></span></td>
				  
			</tr>

		</table>
	</form>
	</div>
</body>