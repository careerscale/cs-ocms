<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<h1>
    <s:message code="user.index.title" />
</h1>


<div id="page-container" class="resize">

	<div id="page-content-inner" class="resize">
<h3>Hello <security:authentication property="principal.displayName" />!</h3>
	<p>
	Click <a href='<s:url value="/"></s:url>'>here</a> to navigate to home page.	
	Click <a href='<s:url value="/logout"></s:url>'>here</a> to logout.	
</p>

</div>
</div>

