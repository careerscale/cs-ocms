<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<h1>
    <s:message code="cases.index.title" />   
</h1>
 <tiles:putAttribute name="pageTitle" value="${cases.index.title}" />

<div id="page-container" class="resize">

	<div id="page-content-inner" class="resize">
		<h3>
			Hello
			<security:authentication property="principal.username" />
			!
		</h3>
		
		<h1>Work is in progress... Please visit us again</h1>
		
		<p>
			Click <a href='<s:url value="/"></s:url>'>here</a> to navigate to
			home page. Click <a href='<s:url value="/logout"></s:url>'>here</a>
			to logout.
		</p>

	</div>
</div>

