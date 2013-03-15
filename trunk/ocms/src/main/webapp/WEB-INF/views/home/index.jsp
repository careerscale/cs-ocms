<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<h1><s:message code="view.index.title" /></h1>

<div id="page-container" class="resize">

	<div id="page-content-inner" class="resize">
<p>
  <h2> TODO for home page</h2>
    <ul>
    <li> Latest approved cases list </li>
    <li>Latest successfully closed cases </li>
    <li>Latest user participated cases </li>
    <li> Calendar, if applicable </li>
    <li> Make the UI pleasing one </li>
    </ul>
	Click <a href='<s:url value="user"></s:url>'>here</a> to navigate to the restricted area. Username: <strong>user</strong>, password: <strong>demo</strong>.
	Click <a href='<s:url value="default/index"></s:url>'>here</a> to navigate to default area
	Google login demo <a href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&state=/profile&redirect_uri=http://localhost:9090/oauth&response_type=token&client_id=179271485873.apps.googleusercontent.com">Google Login</a>
</p>

</div>
</div>