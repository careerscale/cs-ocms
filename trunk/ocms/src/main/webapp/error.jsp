<%@ page language="java" pageEncoding="UTF-8"
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





<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pl" xml:lang="pl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="title" content="Register @OCMS | CareerScale " />
<meta name="robots" content="index, follow" />
<meta name="description"
	content="Online Case Management System application" />
<meta name="keywords" content="OCMS, TMAD" />
<meta name="language" content="en" />
<title>Register @OCMS | Developed by CareerScale</title>

<link rel="shortcut icon" href="/favicon.ico" />

<script src="resources/scripts/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script src="resources/scripts/jquery/jquery.validate.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="resources/scripts/jquery/jquery.maskedinput.js"></script>
<script type="text/javascript"
	src="resources/scripts/application/register.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/stylesheet.css" />

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="datePattern">
	<fmt:message key="date.format" />
</c:set>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<!-- start page wrapper -->
	<div id="letterbox">

		Something went wrong. Don't worry we are working n it. Please bear with us for some time.
		<!-- end page wrapper -->
	</div>
</body>
</html>
