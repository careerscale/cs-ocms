<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<h1><s:message code="view.index.title" /></h1>

<div id="page-container" class="resize">

	<div id="page-content-inner" class="resize">
<p>
	Click <a href='<s:url value="user"></s:url>'>here</a> to navigate to the restricted area. Username: <strong>user</strong>, password: <strong>demo</strong>.
	Click <a href='<s:url value="default/index"></s:url>'>here</a> to navigate to default area
</p>

</div>
</div>