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

<script src="/resources/scripts/application/ajax.js">
	
</script>
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

		$("#anniversary").datepicker({
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
		$("#profileForm").validate();
	})
</script>

<div class="row full-width">



	<div class="row">
		<div class="large-4 columns">Horizontal nav. menu should go here</div>

		<div class="large-8 columns">


			<form:form method="post" action="profile" id="profileForm"
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
							<form:input path="emailId" type="text" class="required"
								tabindex="1" maxlength="40" placeholder="Inline Text Input"
								disabled="true" />

						</div>
					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="firstName" class="right inline">First Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="firstName" type="text" class="required"
								tabindex="1" maxlength="40" placeholder="Inline Text Input" />
						</div>
					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="lastName" class="right inline">Last Name:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="lastName" type="text" class="required"
								tabindex="2" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="dateOfBirth" class="right inline">Birth Date:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="dateOfBirth" type="text" class="required date"
								tabindex="3" maxlength="15" size="15" />
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
								class="right inline" tabindex="4">
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
							<form:select path="helpTypes" multiple="true" tabindex="5">
								<form:options items="${user.helpMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="bloodGroup" class="right inline">Blood Group:</label>
						</div>
						<div class="large-6 columns">
							<form:input path="bloodGroup" type="text" class="required"
								tabindex="6" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="anniversary" class="right inline">Anniversary:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:input path="anniversary" type="text" class="date"
								tabindex="7" maxlength="15" size="15" />
						</div>

					</div>


					<div class="row">
						<div class="large-6  columns">
							<label for="monthlyUpdates" class="right inline">Subscribe
								to Monthly updates:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:checkbox path="monthlyUpdates" class="required"
								tabindex="8" maxlength="15" size="15" />
						</div>

					</div>
					<div class="row">
						<div class="large-6  columns">
							<label for="regularUpdates" class="right inline">Subscribe
								to Regular updates:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:checkbox path="regularUpdates" class="required"
								tabindex="9" maxlength="15" size="15" />
						</div>

					</div>
					<div class="row">
						<div class="large-6  columns">
							<label for="specialUpdates" class="right inline">Subscribe
								to special updates:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:checkbox path="specialUpdates" class="required"
								tabindex="10" maxlength="15" size="15" />
						</div>

					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="mobileNumber1" class="right inline">Mobile
								number:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:input path="mobileNumber1" type="text" tabindex="11"
								maxlength="15" size="15" />
						</div>

					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="mobileNumber2" class="right inline">Additional
								Mobile number:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:input path="mobileNumber2" type="text" tabindex="12"
								maxlength="15" size="15" />
						</div>

					</div>

					<div class="row">
						<div class="large-6  columns">
							<label for="landlineNumber" class="right inline">Landline
								number:</label>
						</div>
						<div class="large-6 columns">
							<div class="formError"></div>
							<form:input path="landlineNumber" type="text" tabindex="13"
								maxlength="15" size="15" />
						</div>

					</div>

					<div class="row">
						<div class="large-6 columns">
							<label for="countryId" title='Country Selection'
								class="right inline">Country</label>
						</div>
						<div class="large-6 columns">
							<form:select path="countryId" multiple="false">
								<form:options items="${user.countryMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br />

					<div class="row">
						<div class="large-6 columns">
							<label for="stateId" title='State Selection' class="right inline">State</label>
						</div>
						<div class="large-6 columns">
							<form:select path="stateId" multiple="false">
								<form:options items="${user.stateMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br />

					<div class="row">
						<div class="large-6 columns">
							<label for="cityId" title='City Selection' class="right inline">City</label>
						</div>
						<div class="large-6 columns">
							<form:select path="cityId" multiple="false">
								<form:options items="${user.cityMasterTypes}" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<br />

					<div class="row">
						<div class="large-6 columns">
							<label for="addressLine1" class="right inline">AddressLine1
							</label>
						</div>
						<div class="large-6 columns">
							<form:input path="addressLine1" type="text" class="required"
								tabindex="9" maxlength="40" />
						</div>
					</div>


					<div class="row">
						<div class="large-6 columns">
							<label for="addressLine2" class="right inline">AddressLine2
							</label>
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



					<div class="row">
						<div class="large-6  columns"></div>
						<div class="large-6 columns"></div>
					</div>

					<div class="row">

						<div class="large-6 columns"></div>
						<div class="large-6 columns">
							<input class="button round" type="submit" value="Update"
								class="right inline" tabindex="9" /> <input
								class="button round" type="reset" value="Reset"
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