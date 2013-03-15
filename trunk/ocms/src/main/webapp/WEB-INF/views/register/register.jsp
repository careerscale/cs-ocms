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
				alert('key and values are ' + key + '  ' + val);
			});
		});

	});
</script>

<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">

		<!-- start col-main -->

		<div id="col-main" class="resize" style="">
			<!-- start main content  -->
			<div class="main-content resize">

				<div class="action-container" style="display: none;"></div>


				<h1>Signup form</h1>
				<p></p>
				<br clear="all" />
				<div>

					<div id="error">
						<spring:hasBindErrors name="user">
							<font color="red"> <c:forEach items="${errors.allErrors}"
									var="error">
									<spring:message code="${error.code}"
										text="${error.defaultMessage}" />
									<br />
								</c:forEach>
							</font>
						</spring:hasBindErrors>
					</div>
					<form:form method="post" action="register" id="signUpForm"
						modelAttribute="user">
						<!-- <form id="signUpForm" type="actionForm" commandName="user" action="register"
								method="post">
 -->

						<div class="error" style="display: none;">
							<img src="resources/images/warning.gif" alt="Warning!" width="24"
								height="24" style="float: left; margin: -5px 10px 0px 0px;" />
							<span></span>.<br clear="all" />
						</div>


						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td class="label"><label for="emailId">Email Id:</label></td>
								<td class="field"><form:input path="emailId" type="email"
										class="required email" value="user@careerscale.in"
										tabindex="1" /></td>
							</tr>
							<tr>
								<td class="label"><label for="password">Password:</label></td>

								<td class="field"><form:input path="password"
										type="password" class="password" value="test123" tabindex="2"
										maxlength="40" /></td>
							</tr>
							<tr>
								<td class="label"><label for="confirmPassword">Retype
										Password:</label></td>
								<td class="field"><input id="confirmPassword"
									class="required" equalTo="#password" maxlength="40"
									name="password2" size="20" type="password" tabindex="3"
									value="test123" />
									<div class="formError"></div></td>
							</tr>
							<tr>
								<td class="label"><label for="firstName">First
										Name:</label></td>
								<td class="field"><form:input path="firstName" type="text"
										class="required" value="FirstName" tabindex="4" maxlength="40" />
								</td>
							</tr>
							<tr>
								<td class="label"><label for="lastName">Last Name:</label></td>
								<td class="field"><form:input path="lastName" type="text"
										class="required" value="lastName" tabindex="5" maxlength="40" />
								</td>
							</tr>
							<tr>
								<td class="label"><label for="dateOfBirth">Birth
										Date:</label></td>
								<td class="field"><form:input path="dateOfBirth"
										type="text" class="required date" tabindex="6" maxlength="15"
										value="1980-01-01" />
							</tr>

							<tr>
								<td></td>
								<td>
									<div class="buttonSubmit">
										<span></span> <input class="formButton" type="submit"
											value="Signup" style="width: 140px" tabindex="14" />
									</div>

								</td>

							</tr>
						</table>
						<br />
						<br />
					</form:form>
					<!-- </form> -->
					<br clear="all" />


				</div>



			</div>
			<!-- end main content  -->
			<br />
		</div>
		<!-- end col-main -->

		<!-- start left col -->
		<div id="col-left" class="nav-left-back empty resize"
			style="position: absolute; min-height: 450px;">
			<div class="col-left-header-tab" style="position: absolute;">Signup</div>
			<div class="nav-left"></div>


			<div class="left-nav-callout png"
				style="top: 15px; margin-bottom: 100px;">
				<img src="resources/images/left-nav-callout-long.png" class="png"
					alt="" />
				<h6>Sign Up Process</h6>
				<a
					style="background-image: url(resources/images/step1-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Provide
					your basic information.</a> <a
					style="background-image: url(resources/images/step2-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Chose
					the type of cases you are interested in.</a> <a
					style="background-image: url(resources/images/step3-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Complete
					registration, validate your email/phone to get updates.</a>
			</div>


			<br clear="all" />
		</div>
		<!-- end left col -->

	</div>
</div>
<!-- end page container 2 divs-->


