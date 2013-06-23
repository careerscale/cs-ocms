<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



<!DOCTYPE html>
<!--[if IE 8]> <html class="no-js lt-ie9" lang="en" > <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Welcome to OCMS | The Online Case Management System</title>



<meta name="title" content="Register @OCMS | CareerScale " />
<meta name="robots" content="index, follow" />
<meta name="description"
	content="Online Case Management System application" />
<meta name="keywords" content="OCMS, TMAD" />
<meta name="language" content="en" />

<title><tiles:getAsString name="pageTitle"
		defaultValue="OCMS | The Online Case Management System by Careerscale" /></title>

<link rel="shortcut icon" href="/favicon.ico" />

<script src="/resources/scripts/jquery/jquery-1.8.3.min.js"></script>
<script src="/resources/scripts/jquery/jquery.validate.min.js"></script>
<script src="/resources/scripts/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<script src="/resources/scripts/jquery/jquery.maskedinput.js"></script>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="datePattern">
	<fmt:message key="date.format" />
</c:set>

<link rel="stylesheet" type="text/css"
	href="/resources/css/redmond/jquery-ui-1.8.16.custom.css" />

<!-- <link rel="stylesheet" href="/foundation/css/foundation.css" />
<link rel="stylesheet" href="/foundation/css/normalize.css" />
  -->


 
 <!--   
 <link rel="stylesheet" href="http://foundation.zurb.com/page-templates4/css/normalize.css">
 <link rel="stylesheet" href="http://foundation.zurb.com/page-templates4/css/foundation.css">

-->

<link rel="stylesheet" href="/foundation/css/foundation.css" />
<link rel="stylesheet" href="/foundation/css/normalize.css" />
<script src="/foundation/js/custom.modernizr.js"></script>

</head>

<body>
	<!-- start page wrapper -->
	<div class="row">

		<tiles:insertAttribute name="header" defaultValue="" />
		<!-- Page content -->
		<tiles:insertAttribute name="body" defaultValue="" />
		<!-- End of page content -->
		<tiles:insertAttribute name="footer" defaultValue="" />

		<!-- end page wrapper -->
	</div>


	<script>
		document.write('<script src='
				+ ('__proto__' in {} ? '/foundation/js/vendor/zepto'
						: '/foundation/js/vendor/jquery') + '.js><\/script>')
	</script>

	<script src="/foundation/js/foundation.min.js"></script>

	<script src="/foundation/js/foundation/foundation.forms.js"></script>


	<script src="/foundation/js/foundation/foundation.js"></script>


	<script src="/foundation/js/foundation/foundation.orbit.js"></script>




	<!-- 
  
    
  <script src="/foundation/js/foundation/foundation.dropdown.js"></script>
  
  <script src="/foundation/js/foundation/foundation.placeholder.js"></script>
  
  <script src="/foundation/js/foundation/foundation.alerts.js"></script>
  
  <script src="/foundation/js/foundation/foundation.magellan.js"></script>
  
  <script src="/foundation/js/foundation/foundation.reveal.js"></script>
  
  <script src="/foundation/js/foundation/foundation.tooltips.js"></script>
  
  <script src="/foundation/js/foundation/foundation.clearing.js"></script>
  
  <script src="/foundation/js/foundation/foundation.cookie.js"></script>
  
  <script src="/foundation/js/foundation/foundation.joyride.js"></script>
  
  <script src="/foundation/js/foundation/foundation.section.js"></script>
  
  <script src="/foundation/js/foundation/foundation.topbar.js"></script>
  
 -->

	<script>
		$(document).ready(function() {
			$(document).foundation();
		});
		// $("#navigation").load("nav.html"); // Load the contents of nav.html
	</script>

</body>
</html>
