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
	<spring:message code="cases.registered.title" />
</h1>

<!-- start page container 2 div-->
<div id="page-container" class="resize">
	<div id="page-content-inner" class="resize">

		<!-- start col-main -->

		<div id="col-main" class="resize" style="">
			<!-- start main content  -->
			<div class="main-content resize">

				<div class="action-container" style="display: none;"></div>


				<h3>Case is added to OCMS.</h3>

				<div>
					<h3>
						We need certain documents to be added as per the OCMS policy. This
						helps for faster and easier processing. id: ${entry.id} <br />
						name: ${entry.name} <br /> mandatory: ${entry.mandatory} <br />
						supportedFormat: ${entry.supportedFormat} <br /> maxSize:
						${entry.maxSize} <br />

					</h3>
				

				</div>

                
				<form:form method="post" action="/cases/upload"
					modelAttribute="caseArtifacts"
					enctype="multipart/form-data">
					<form:input path="caseId" type="hidden" value="${caseId}" />
					<form:input path="caseTypeId" type="hidden" value="${caseTypeId}" />
					<%
						int i = 0;
					%>
					<table width="90%">
					<c:forEach var="entry" items="${docTypeList}">
						<form:input path="caseId" type="hidden"
							value="${caseDocuments[i].documentTypeId}" />

						<tr>
						   <% String pathId="caseDocuments["+ i+ "].file"; %>
							<td class="label"><label for=<%=pathId %>>${entry.name}</label></td>
							<td class="field"><form:input
									path="<%=pathId %>" type="file" /></td>
						</tr>

						<%
							i++;
						%>
					</c:forEach>
					</table>
					<input type="submit" value="Upload" />
				</form:form>
				<br clear="all" />
				<div>


					<br clear="all" />


				</div>



			</div>
			<!-- end main content  -->
			<br />
		</div>
		<!-- end col-main -->
	</div>
	<!-- end page container 2 divs-->
</div>

