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
<h1>
	<spring:message code="cases.addcase.title" />
</h1>
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
	$(document)
			.ready(
					function() {
						$.get("/master/states", function(data, status) {
							$.each(data, function(key, val) {

								//alert('key and values are ' + key + '  ' + val);
							});
						});

						$('select#countryId')
								.change(
										function() {

											$
													.get(
															"/master/states?countryId="
																	+ $(
																			'#countryId')
																			.val(),
															function(data,
																	status) {

																var html = '<option value="">Select your State</option>';

																jQuery(
																		'#dropdown')
																		.append(
																				html);
																$
																		.each(
																				data,
																				function(
																						id,
																						value) {
																					html += '<option value="'+value.id+'">'
																							+ value.name
																							+ '</option>';

																				}

																		);

																$(
																		'select#stateId')
																		.empty()
																		.append(
																				html);
															});
										});

						$('select#stateId')
								.change(
										function() {
											
											$
													.get(
															"/master/cities?stateId="
																	+ $(
																			'#stateId')
																			.val(),
															function(data,
																	status) {

																var html = '<option value="">Select your City</option>';

																jQuery(
																		'#dropdown')
																		.append(
																				html);
																$
																		.each(
																				data,
																				function(
																						key,
																						value) {

																					html += '<option value="'+value.id+'">'
																							+ value.name
																							+ '</option>';

																				}

																		);

																$(
																		'select#cityId')
																		.empty()
																		.append(
																				html);
															});
										});

					});
</script>

<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">

		<!-- start col-main -->


		<!-- start main content  -->
		<div class="main-content resize">


			<h1>Add Cases</h1>
			
			<br clear="all" />

			<div id="error">
				<spring:hasBindErrors name="caseDetails">
					<font color="red"> <c:forEach items="${errors.allErrors}"
							var="error">
							<spring:message code="${error.code}"
								text="${error.defaultMessage}" />
							<br />
						</c:forEach>
					</font>
				</spring:hasBindErrors>
			</div>
			<form:form method="post" action="addcase" id="signUpForm"
				modelAttribute="caseDetails">
				<div class="error" style="display: none;">
					<img src="resources/images/warning.gif" alt="Warning!" width="24"
						height="24" style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
						clear="all" />
				</div>


				<table width="90%">
					<tr>
						<td class="label"><label for="emailId">User Name:</label></td>
						<td class="field"><form:input path="personName" type="text"
								class="required" tabindex="1" /></td>
					</tr>
					<tr>
						<td class="label"><label for="emailId">Email Address</label></td>
						<td class="field"><form:input path="emailId" type="email"
								tabindex="2" /></td>
					</tr>
					<tr>
						<td class="label"><label for="caseDescription">Case
								Details(should be multi line):</label></td>

						<td class="field"><form:input path="caseDescription"
								type="textarea" class="required" value="" tabindex="2" /></td>
					</tr>
					<tr>
						<td class="label"><label for="firstName">Concerned
								Source:</label></td>
						<td class="field"><form:input path="caseSource" type="text"
								class="required" tabindex="4" maxlength="40" /></td>
					</tr>
					<tr>
						<td class="label"><label for="dateOfBirth">Birth
								Date:</label></td>
						<td class="field"><form:input path="dateOfBirth" type="text"
								class="required date" tabindex="6" maxlength="15"
								value="1980-01-01" />
					</tr>

					<tr>
						<td class="label"><label for="contact1">Contact
								Number 1 </label></td>
						<td class="field"><form:input path="contact1" type="text"
								class="required" tabindex="7" maxlength="40" /></td>
					</tr>

					<tr>
						<td class="label"><label for="contact2">Contact
								Number 2 </label></td>
						<td class="field"><form:input path="contact2" type="text"
								class="required" tabindex="8" maxlength="40" /></td>
					</tr>


					<tr>
						<td class="label"><label for="caseType"
							title='Please select all type of cases that you want to informed about'>Case
								Types </label></td>
						<td class="field"><form:select path="caseType"
								multiple="false">
								<form:options items="${caseDetails.caseMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select></td>
					</tr>

					<tr>
						<td class="label"><label for="helpType"
							title='Please select corresponding help type needed.'>Help
								Category Types </label></td>
						<td class="field"><form:select path="helpType"
								multiple="false">
								<form:options items="${caseDetails.helpMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select></td>
					</tr>

					<tr>
						<td class="label"><label for="countryId"
							title='Country Selection'>Country</label></td>
						<td class="field"><form:select path="countryId"
								multiple="false">
								<form:options items="${caseDetails.countryMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select></td>
					</tr>

					<tr>
						<td class="label"><label for="stateId"
							title='State Selection'>State</label></td>
						<td class="field"><form:select path="stateId"
								multiple="false">
							<form:options items="${caseDetails.stateMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select></td>
					</tr>


					<tr>
						<td class="label"><label for="cityId" title='City Selection'>City</label></td>
						<td class="field"><form:select path="cityId" multiple="false">
							<form:options items="${caseDetails.cityMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select></td>
					</tr>
					<tr>
						<td class="label"><label for="addressLine1">AddressLine1
						</label></td>
						<td class="field"><form:input path="addressLine1" type="text"
								class="required" tabindex="9" maxlength="40" /></td>
					</tr>
					<tr>
						<td class="label"><label for="addressLine2">AddressLine2
						</label></td>
						<td class="field"><form:input path="addressLine2" type="text"
								class="required" tabindex="10" maxlength="40" /></td>
					</tr>
					<tr>
						<td class="label"><label for="zipcode">ZipCode </label></td>
						<td class="field"><form:input path="zipcode" type="text"
								class="required" tabindex="11" maxlength="40" /></td>
					</tr>



					<tr>
						<td></td>
						<td>
							<div class="buttonSubmit">
								<span></span> <input class="formButton" type="submit"
									value="Register case" style="width: 140px" tabindex="14" />
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
		<!-- end main content  -->
		<br />

		<!-- end col-main -->

		<!-- start left col -->
		<div id="col-left" class="nav-left-back empty resize"
			style="position: absolute; min-height: 400px;">
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


