<body>
	<form action="/j_spring_security_check" method="POST">

		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="label"><label for="username">Email Id</label></td>
				<td class="field"><input id="username" name="j_username"
					class="required email" type="text" tabindex="1" /></td>
			</tr>
			<tr>
				<td class="label"><label for="password">Password:</label></td>

				<td class="field"><input id="password" class="required"
					name="j_password" type="password" tabindex="2" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login" tabindex="3" /></td>
				<td><a href="/register"> Register</a></td>
			</tr>
		</table>
	</form>
</body>