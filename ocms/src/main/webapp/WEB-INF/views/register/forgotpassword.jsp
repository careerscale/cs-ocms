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
	<spring:message code="register.forgotpassword.title" />
</h1>

<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">

		<!-- start col-main -->


		<!-- start main content  -->
		<div class="main-content resize">

			<div class="action-container" style="display: none;"></div>

			<p>Please provide your registered email address and submit form.
				your password will be emailed to your registered email address.</p>
			<br clear="all" />

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
			<form:form method="post" action="forgotpassword" id="signUpForm"
				modelAttribute="user">

				<div class="error" style="display: none;">
					<img src="resources/images/warning.gif" alt="Warning!" width="24"
						height="24" style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
						clear="all" />
				</div>


				<div class="row">

					<label for="emailId">Email Id</label> <input type="text"
						name="emailId" type="email" class="required email" />
				</div>

				<div class="row">
					<input class="button small round" type="submit"
						value="Resend my password" />
				</div>
				<br />
			</form:form>
			<!-- </form> -->
			<br clear="all" />


		</div>
		<!-- end main content  -->
		<br />

		<!-- end col-main -->

	</div>
</div>
<!-- end page container 2 divs-->


