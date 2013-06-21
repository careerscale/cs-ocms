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


			<h3>We found your password</h3>
			<p>Your password has been emailed to your registered email
				address.</p>
			<br clear="all" />
			<!-- end main content  -->
			<br />

			<!-- end col-main -->

		</div>
	</div>
	<!-- end page container 2 divs-->
</div>