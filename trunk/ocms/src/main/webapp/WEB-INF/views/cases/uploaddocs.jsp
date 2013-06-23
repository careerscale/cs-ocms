<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>




<div class="row full-width">
	<h3>
		<spring:message code="cases.registered.title" />
	</h3>

	<div class="row">

		<h3>
			We need certain documents to be added as per the OCMS policy. This
			helps for faster and easier processing. id: ${entry.id} <br /> name:
			${entry.name} <br /> mandatory: ${entry.mandatory} <br />
			supportedFormat: ${entry.supportedFormat} <br /> maxSize:
			${entry.maxSize} <br />

		</h3>


	</div>

	<div class="row">

		<form:form method="post" action="/cases/upload"
			modelAttribute="caseArtifacts" enctype="multipart/form-data">
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
						<%
							String pathId = "caseDocuments[" + i + "].file";
						%>
						<td class="label"><label for=<%=pathId%>>${entry.name}</label></td>
						<td class="field"><form:input path="<%=pathId%>" type="file" /></td>
					</tr>

					<%
						i++;
					%>
				</c:forEach>
			</table>
			<input type="submit" value="Upload" />
		</form:form>
		<br clear="all" />

	</div>

</div>
