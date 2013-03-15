<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1>
	<spring:message code="view.backoffice.casetype.title" />
</h1>


<script type="text/javascript">

$(document).ready(function() {
	var data = <%=request.getAttribute("oAuthResponse1")%>;

$.each(data, function(key, val) {
    alert('key and values are ' + key + '  ' +val);      
 });
 });

 </script>


<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">
		<br clear="all" />
		<div>

			<form:form method="post"
				action="/backoffice/casetype" id="signUpForm">

				

				<table cellpadding="0" cellspacing="0" border="0">
									
				</table>
				<br />
				<br />
			</form:form>
			
			
			<!-- </form> -->
			<br clear="all" />

	
		</div>





		</p>

	</div>
</div>