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

<script>
	$(document).ready(function() {
		$("#addCaseForm").validate();
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
		<div class="large-4 columns">If needed left side navigation
			here..</div>

		<div class="large-8 columns">
			<form:form method="post" action="addcase" id="addCaseForm"
				modelAttribute="caseDetails">
				<div class="error" style="display: none;">
					<img src="resources/images/warning.gif" alt="Warning!" width="24"
						height="24" style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
						clear="all" />
				</div>
				<fieldset>

					<div class="row">
						<div class="large-6  columns">
							<label for="emailId" class="right inline">User Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="personName" type="text" class="required"
								tabindex="1" />
						</div>
					</div>

					<div class="row">
						<div class="large-6 columns">
							<label for="emailId" class="right inline">Email Address</label>
						</div>
						<div class="large-6 columns">
							<form:input path="emailId" type="email" tabindex="2" />
						</div>
					</div>

					<div class="row">
						<div class="large-6 columns">
							<label for="caseDescription" class="right inline">Case Details</label>
						</div>
						<div class="large-6 columns">
							<form:textarea path="caseDescription" class="required"
								type="text" tabindex="3" rows="5" />
						</div>

					</div>

					<div class="row">
						<div class="large-6 columns">
							<label for="firstName" class="right inline">Concerned Source:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="caseSource" type="text" class="required"
								tabindex="4" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6 columns">
							<label for="dateOfBirth" class="right inline">Birth Date:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="dateOfBirth" type="text" class="required date"
								tabindex="6" maxlength="15" size="15" value="1980-01-01" />
						</div>
					</div>
					<div class="row full-width"></div>
					<div class="row">
						<div class="large-6 columns">
							<label for="contact1" class="right inline">Contact Number 1 </label>
						</div>
						<div class="large-6 columns">
							<form:input path="contact1" type="text" class="required"
								tabindex="7" maxlength="40" />
						</div>
					</div>
					<div class="row">
						<div class="large-6 columns">
							<label for="contact2" class="right inline">Contact Number 2 </label>
						</div>
						<div class="large-6 columns">
							<form:input path="contact2" type="text" class="required"
								tabindex="8" maxlength="40" />
						</div>
					</div>

					<div class="row">
						<div class="large-6 columns">
							<label for="caseType"
								title='Please select all type of cases that you want to informed about' class="right inline">Case
								Types </label>

						</div>
						<div class="large-6 columns">
							<form:select path="caseType" multiple="false">
								<form:options items="${caseDetails.caseMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br/>

					<div class="row">
						<div class="large-6 columns">
							<label for="helpType"
								title='Please select corresponding help type needed.' class="right inline">Help
								Category Types </label>
						</div>
						<div class="large-6 columns">
							<form:select path="helpType" multiple="false">
								<form:options items="${caseDetails.helpMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br/>
					<div class="row">
						<div class="large-6 columns">
							<label for="countryId" title='Country Selection' class="right inline" >Country</label>
						</div>
						<div class="large-6 columns">
							<form:select path="countryId" multiple="false">
								<form:options items="${caseDetails.countryMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br/>

					<div class="row">
						<div class="large-6 columns">
							<label for="stateId" title='State Selection' class="right inline">State</label>
						</div>
						<div class="large-6 columns">
							<form:select path="stateId" multiple="false">
								<form:options items="${caseDetails.stateMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select>
						</div>
					</div>
				<br/>

					<div class="row">
						<div class="large-6 columns">
							<label for="cityId" title='City Selection' class="right inline">City</label>
						</div>
						<div class="large-6 columns">
							<form:select path="cityId" multiple="false">
								<form:options items="${caseDetails.cityMasterTypes}"
									itemValue="id" itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br/>

					<div class="row">
						<div class="large-6 columns">
							<label for="addressLine1" class="right inline">AddressLine1 </label>
						</div>
						<div class="large-6 columns">
							<form:input path="addressLine1" type="text" class="required"
								tabindex="9" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6 columns">
							<label for="addressLine2" class="right inline">AddressLine2 </label>
						</div>
						<div class="large-6 columns">
							<form:input path="addressLine2" type="text" class="required"
								tabindex="10" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6 columns">
							<label for="zipcode" class="right inline">ZipCode </label>
						</div>
						<div class="large-6 columns">
							<form:input path="zipcode" type="text" class="required"
								tabindex="11" maxlength="40" />
						</div>
					</div>


					<div class="row" class="right inline" >
					
							<div  class="large-6 columns" >
							</div>
						<div  class="large-6 columns">
							<div class="right inline" >
								<input class="right inline button round" type="reset" value="Reset form"
									style="width: 140px" tabindex="14"  />
							
								<input class="button round inline right" type="submit" value="Register case"
									style="width: 140px" tabindex="14"  />
							</div>
						</div>
					</div>

				</fieldset>

			</form:form>
		</div>
		<!-- </form> -->
		<br clear="all" />


	</div>

</div>

<!-- end main content  -->
<br />
<div id="col-left" class="nav-left-back empty resize"></div>




