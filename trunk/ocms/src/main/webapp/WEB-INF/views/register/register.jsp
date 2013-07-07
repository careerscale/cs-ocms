<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!--[if lte IE 6]>
  <link rel="stylesheet" type="text/css" media="all" href="ie6.css" />
<![endif]-->
<!-- start page container 2 div-->
<h3>
	<spring:message code="register.register.title" />
</h3>


<script src="/resources/scripts/application/datepicker.js">
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$.get("/master/casetypes", function(data, status) {
			$.each(data, function(key, val) {
				//alert('key and values are ' + key + '  ' + val);
			});
		});

	});
</script>

<script>
	$(document).ready(function() {
		registerDatePicker('#dateOfBirth');
		$("#registerForm").validate();
	})
</script>

<div class="row full-width">


	<br clear="all" />



	<div class="row">
		<div class="large-2 columns">Horizontal nav. menu should go here</div>

		<div class="large-10 columns">

			<form:form method="post" action="register" id="registerForm"
				modelAttribute="user">

				<fieldset>
					<div class="row">

						<div class="small-1  columns"></div>
						<div class="large-11  columns">

							<spring:hasBindErrors name="user">

								<font color="red"> <c:forEach items="${errors.allErrors}"
										var="error">
										<img src="resources/images/warning.gif" alt="Warning!"
											width="24" height="24"
											style="float: left; margin: -5px 10px 0px 0px;" />
										<spring:message code="${error.code}"
											text="${error.defaultMessage}" />
										<br />

									</c:forEach>
								</font>

							</spring:hasBindErrors>

						</div>

					</div>

					<br />

					<div class="row">
						<div class="large-6  columns">
							<label for="emailId" class="right inline">Email Id:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="emailId" type="email" class="required email"
								placeholder="Inline Text Input" 
								tabindex="1" />
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
								tabindex="3"  />
						</div>

					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="firstName" class="right inline">First Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="firstName" type="text" class="required"
								 tabindex="4" maxlength="40"
								placeholder="Inline Text Input" />
						</div>
					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="lastName" class="right inline">Last Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="lastName" type="text" class="required"
								tabindex="5" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="dateOfBirth" class="right inline">Birth Date:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="dateOfBirth" type="text" class="required date"
								tabindex="6" maxlength="15" size="15" />
						</div>
					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="caseTypes"
								title='Please select all type of cases that you want to informed about'
								class="right inline">Case Types </label>
						</div>
						<div class="large-6 columns">
							<form:select path="caseTypes" multiple="true"
								class="right inline" tabindex="7">
								<form:options items="${user.caseMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="large-6  columns">
							<label for="helpTypes"
								title='Please select how many ways you want to help others'
								class="right inline">Help Category Types </label>
						</div>

						<div class="large-6 columns">
							<form:select path="helpTypes" multiple="true" tabindex="8">
								<form:options items="${user.helpMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="large-6  columns"></div>
						<div class="large-6 columns"></div>
					</div>

					<div class="row">

						<div class="large-6 columns"></div>
						<div class="large-6 columns">
							<input class="button small round" type="submit" value="Signup"
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
			<!-- </form> -->
			<br clear="all" />


		</div>
		<!-- end form content  -->
		<br />


	</div>
</div>
<!-- end page container 2 divs-->


