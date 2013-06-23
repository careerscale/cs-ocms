<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<h1>
	<spring:message code="oauth.oauthprofile.title" />
</h1>


<script type="text/javascript">
	$(document).ready(
			function() {
				var data =<%=request.getAttribute("oAuthResponse1")%>;

				$("#div-oauth-response-table").text("<table>");

				$.each(data, function(key, val) {
					alert('key and values are ' + key + '  ' + val);
					$("#div-oauth-response-table")
							.append(
									"<tr><td>" + key + "</td><td>" + val
											+ "</td></tr>");
				});

				$("#div-oauth-response-table").append("</table>");

			});
</script>


<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">
		<br clear="all" />
		<div id='div-oauth-response-table'></div>
		<!-- </form> -->
		<br clear="all" />


	</div>





	</p>

</div>
</div>