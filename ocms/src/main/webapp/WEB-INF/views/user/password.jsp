<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	$(document).ready(function() {
		$("#passwordForm").validate();
	})
</script>
<h1>

	<core:set var="displayName">
		<security:authentication property="principal.displayName" />
	</core:set>


	<spring:message code="user.index.title" arguments="${displayName}" />
</h1>


<div class="row">
	<div class="large-2 columns">Horizontal nav. menu should go here</div>

	<div class="large-10 columns">

		<form:form method="post" action="password" id="passwordForm"
			modelAttribute="user">

			<fieldset>
				<div class="row">

					<div class="small-1  columns"></div>
					<div class="large-11  columns">

						<spring:hasBindErrors name="user">

							<font color="red"> <core:forEach items="${errors.allErrors}"
									var="error">
									<img src="resources/images/warning.gif" alt="Warning!"
										width="24" height="24"
										style="float: left; margin: -5px 10px 0px 0px;" />
									<spring:message code="${error.code}"
										text="${error.defaultMessage}" />
									<br />

								</core:forEach>
							</font>

						</spring:hasBindErrors>

					</div>

				</div>

				<br />

				<div class="row">
					<div class="large-6  columns">
						<label for="previousPassword" class="right inline">Current
							Password:</label>
					</div>
					<div class="large-6 columns">
						<form:input path="previousPassword" type="password"
							class="required password" tabindex="1"
							maxlength="40" />
					</div>
				</div>


				<div class="row">
					<div class="large-6  columns">
						<label for="password" class="right inline">Password:</label>
					</div>
					<div class="large-6 columns">
						<form:input path="password" type="password"
							class="required password"  tabindex="2"
							maxlength="40" />
					</div>
				</div>


				<div class="row">
					<div class="large-6  columns">
						<label for="confirmPassword" class="right inline">Retype
							Password:</label>
					</div>
					<div class="large-6 columns">
						<div class="formError"></div>
						<input id="confirmPassword" class="required" equalTo="#password"
							maxlength="40" name="password2" size="20" type="password"
							tabindex="3" />
					</div>

				</div>
				
					<div class="row">

						<div class="large-6 columns"></div>
						<div class="large-6 columns">
							<input class="button small round" type="submit" value="Update"
								class="right inline" tabindex="9" /> <input
								class="button small round" type="reset" value="Reset"
								class="right inline" tabindex="10" />
						</div>
					</div>
					<div class="row">
						<div class="large-6  columns"></div>
						<div class="large-6 columns"></div>
					</div>

			</fieldset>
		</form:form>
		
		</div>
		</div>
	