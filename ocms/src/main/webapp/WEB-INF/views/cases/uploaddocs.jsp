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

		<h3>We need certain documents to be added as per the OCMS policy.
			This helps for faster and easier processing.</h3>


	</div>

	<div class="row">

		<form:form method="post" action="/cases/upload"
			modelAttribute="caseArtifacts" enctype="multipart/form-data">
			<form:input path="caseId" type="hidden" value="${caseId}" />
			<form:input path="caseTypeId" type="hidden" value="${caseTypeId}" />
			<%
				int i = 0;
			%>

			<c:forEach var="entry" items="${docTypeList}">
				<form:input path="caseId" type="hidden"
					value="${caseDocuments[i].documentTypeId}" />
				<%
					String pathId = "caseDocuments[" + i + "].file";
				%>
				<div class="row">

					<label for=<%=pathId%>>${entry.name}</label>
					<form:input path="<%=pathId%>" type="file" />
				</div>
				<%
					i++;
				%>
			</c:forEach>

			<input type="submit" value="Upload" />
		</form:form>
		<br clear="all" />

	</div>

</div>
