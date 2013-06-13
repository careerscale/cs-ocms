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
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="title" content="Register @OCMS | CareerScale " />
<meta name="robots" content="index, follow" />
<meta name="description"
	content="Online Case Management System application" />
<meta name="keywords" content="OCMS, TMAD" />
<meta name="language" content="en" />

<title><tiles:getAsString name="pageTitle" defaultValue="OCMS | The Online Case Management System by Careerscale" /></title>

<link rel="shortcut icon" href="/favicon.ico" />

<script src="/resources/scripts/jquery/jquery-1.8.3.min.js"></script>
<script src="/resources/scripts/jquery/jquery.validate.min.js"></script>
<script src="/resources/scripts/jquery/jquery-ui-1.8.17.custom.min.js"></script>

<script src="/resources/scripts/jquery/jquery.maskedinput.js"></script>
<script src="/resources/scripts/application/register.js"></script>
<script src="/resources/scripts/application/register.js"></script>
<script src="/resources/scripts/jquery/nav.js"></script>

<!-- 

/* iPhone, etc portrait ---- */
@media only screen and (min-width: 320px)

/* iPhone, etc landscape ---- */
@media only screen and (min-width: 480px)


/* iPad, tablets etc portrait ---- */
@media only screen and (min-width: 768px)

/* Laptops, Desktops, etc ---- */
@media only screen and (min-width: 1024px)


 -->

<link rel="stylesheet" media="screen" href="/resources/css/stylesheet.css" />
<link rel="stylesheet" media="screen" href="/resources/css/styles1.css" />
<link rel="stylesheet" media="only screen and (min-width: 768px)" href="/resources/css/medium/stylesheet.css" />
<link rel="stylesheet" media="only screen and (min-width: 320px)" href="/resources/css/small/stylesheet.css" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="datePattern">
	<fmt:message key="date.format" />
</c:set>

<link href="<c:url value="/resources/css/stylesheet.css"/>" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/resources/css/redmond/jquery-ui-1.8.16.custom.css" />
	
	
</head>
<body>
	<!-- start page wrapper -->
	<div id="letterbox">

		<tiles:insertAttribute name="header" defaultValue="" />
		<!-- Page content -->
		<tiles:insertAttribute name="body" defaultValue="" />
		<!-- End of page content -->
		<tiles:insertAttribute name="footer" defaultValue="" />

		<!-- end page wrapper -->
	</div>
</body>
</html>
