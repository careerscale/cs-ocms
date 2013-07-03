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
	<spring:message code="${result}" />
</h1>
<div class="row full-width">

	<div class="row">
		<p>
			Click <a href='<spring:url value="/"></spring:url>'>here</a> to navigate to
			home page. Click <a href='<spring:url value="/logout"></spring:url>'>here</a>
			to logout.
		</p>

	</div>

	<br clear="all" />


</div>
<!-- end main content  -->
<br />


