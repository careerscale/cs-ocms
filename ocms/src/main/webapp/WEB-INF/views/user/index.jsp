<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>


<h1>

	<c:set var="displayName">
		<security:authentication property="principal.displayName" />
	</c:set>


	<s:message code="user.index.title" arguments="${displayName}" />
</h1>
<security:authorize access="hasAnyRole('MODERATOR','MANAGER', 'ADMIN')">
	<div class="row">
	Tabular data for all the cases that are pending for approval are to be listed.
	</div>

</security:authorize>

<div class="row">
Tabular data for all the cases that have logged in user activity, are to be listed.
</div>

<div class="row">
Tabular data for all the cases that are pending  are to be listed.
</div>

<div class="row">
	<p>
		Click <a href='<s:url value="/"></s:url>'>here</a> to navigate to home
		page. Click <a href='<s:url value="/logout"></s:url>'>here</a> to
		logout.
	</p>

</div>


