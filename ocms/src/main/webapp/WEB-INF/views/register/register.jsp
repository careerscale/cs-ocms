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
<script type="text/javascript">
	$(document).ready(function() {
		$("#dateOfBirth").datepicker({
			showOn : "button",
			buttonImage : "resources/images/ico_calendar.gif",
			buttonImageOnly : true,
			appendText : ' yyyy-mm-dd ',
			buttonText : 'Date selector 1',
			align : "middle",
			changeMonth : true,
			changeYear : true,
			defaultDate : null,
			showOn : 'both',
			alt : 'Date Selector 1',
			cursor : 'pointer',
			dateFormat : 'yy-mm-dd',
			beforeShow : function(input, inst) {
				$('ui-widget-header').css({
					"color" : 'red',
					"width" : "100%"
				});
				$('#ui-datepicker-div').css({
					"z-index" : "2"
				});

			}

		});

	});
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
		$("#registerForm").validate();
	})
</script>

<div class="row full-width">


	<br clear="all" />

	<div id="error">
		<spring:hasBindErrors name="caseDetails">
			<font color="red"> <c:forEach items="${errors.allErrors}"
					var="error">
					<spring:message code="${error.code}" text="${error.defaultMessage}" />
					<br />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
	</div>

	<div class="row">
		<div class="large-6 columns">Horizontal nav. menu should go here</div>

		<div class="large-8 columns">
			<form:form method="post" action="register" id="registerForm"
				modelAttribute="user">

				<fieldset>
					<div class="row full-width">
						<div class="error" style="display: none;">
							<img src="resources/images/warning.gif" alt="Warning!" width="24"
								height="24" style="float: left; margin: -5px 10px 0px 0px;" />
							<span></span>.<br clear="all" />
						</div>
					</div>

					<div class="row">
						<div class="large-2  columns">
							<label for="emailId">Email Id:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="emailId" type="email" class="required email"
								value="user@careerscale.in" tabindex="1" />
						</div>
					</div>

					<div class="row">
						<div class="large-2  columns">
							<label for="password">Password:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="password" type="password" class="password"
								value="test123" tabindex="2" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-2  columns">
							<label for="confirmPassword">Retype Password:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<input id="confirmPassword" class="required" equalTo="#password"
								maxlength="40" name="password2" size="20" type="password"
								tabindex="3" value="test123" />
						</div>

					</div>


					<div class="row">
						<div class="large-2  columns">
							<label for="firstName">First Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="firstName" type="text" class="required"
								value="FirstName" tabindex="4" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-2  columns">
							<label for="lastName">Last Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="lastName" type="text" class="required"
								value="lastName" tabindex="5" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-2  columns">
							<label for="dateOfBirth">Birth Date:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="dateOfBirth" type="text" class="required date"
								tabindex="6" maxlength="15" size="15" value="1980-01-01" />
						</div>
					</div>

					<div class="row">
						<div class="large-2  columns">
							<label for="caseTypes"
								title='Please select all type of cases that you want to informed about'>Case
								Types </label>
						</div>
						<div class="large-6 columns">
							<form:select path="caseTypes" multiple="true">
								<form:options items="${user.caseMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="large-2  columns">
							<label for="helpTypes"
								title='Please select how many ways you want to help others'>Help
								Category Types </label>
						</div>
					
						<div class="large-6 columns">
							<form:select path="helpTypes" multiple="true">
								<form:options items="${user.helpMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
				
					<div class="row">
						<div class="large-2  columns"></div>
						<div class="large-6 columns">
							<div class="buttonSubmit">
								<span></span> <input class="formButton" type="submit"
									value="Signup" style="width: 140px" tabindex="14" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="large-2  columns"></div>
						<div class="large-6 columns"></div>
					</div>



				</fieldset>


			</form:form>
			<!-- </form> -->
			<br clear="all" />


		</div>
		<!-- end main content  -->
		<br />

		<!-- end col-main -->

		<div id="col-left"></div>

	</div>
</div>
<!-- end page container 2 divs-->


