<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1>
	<spring:message code="view.backoffice.casetype.title" />
</h1>


<script type="text/javascript">
	$(document).ready(function() {

		//bind the output of ajax call to drop down or so...
		$('#myButton').click(function(){
			
			  $.get("/master/casesubtype",function(data,status){
				    $.each(data, function(key, val) {
					    alert('key and values are ' + key + '  ' +val);						
						});
				  });
			});


	});
</script>

<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">
		<br clear="all" />
		<div>

			<div id="error">
				<spring:hasBindErrors name="botype">
					<font color="red"> <c:forEach items="${errors.allErrors}"
							var="error">
							<spring:message code="${error.code}"
								text="${error.defaultMessage}" />
							<br />
						</c:forEach>
					</font>
				</spring:hasBindErrors>
			</div>
			<form:form commandName="caseType" method="post"
				action="/backoffice/casetype" id="signUpForm"
				modelAttribute="botype">

				<div class="error" style="display: none;">
					<img src="resources/images/warning.gif" alt="Warning!" width="24"
						height="24" style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
						clear="all" />
				</div>


				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td class="label"><label for="name">Case Type Name:</label></td>
						<td class="field"><form:input path="name" type="text"
								class="required" value="" tabindex="1" /></td>
					</tr>
					<tr>
						<td class="label"><label for="description">Description:</label></td>

						<td class="field"><form:input path="description"
								type="textarea" class="required" value="" tabindex="2"
								maxlength="40" /></td>
					</tr>

					<tr>
						<td><input type="button" id='myButton' value="Ajax call" /></td>
						<td>
							<div class="buttonSubmit">
								<span></span> <input class="formButton" type="submit"
									value="Add Case Type" style="width: 140px" tabindex="3" />
							</div>

						</td>

					</tr>
					
							<tr>
						<td></td>
						<td>
						

						</td>

					</tr>
					
					
				</table>
				<br />
				<br />
			</form:form>
			
			
			<!-- </form> -->
			<br clear="all" />

	
		</div>





		</p>

	</div>
</div>