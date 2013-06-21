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
<h1>
	<spring:message code="register.registered.title" />
</h1>

<!-- start page container 2 div-->
<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">

		<!-- start col-main -->

		<div id="col-main" class="resize" style="">
			<!-- start main content  -->
			<div class="main-content resize">

				<div class="action-container" style="display: none;"></div>


				<h3>Thank you for registering with OCMS.</h3>
				<p>Please <a href="/user">Login</a> with your email id and password to continue with this site</p>
				<br clear="all" />
				<div>

				
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


