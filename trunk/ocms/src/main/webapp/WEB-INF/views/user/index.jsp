<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<script src="/resources/media/js/jquery.dataTables.min.js"
	type="text/javascript"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var id = -1;//simulation of id
		$('#example').dataTable({
			bJQueryUI : true,

			"sPaginationType" : "full_numbers"
		});

	});
	
	$(document).ready(function() {
		var id = -1;//simulation of id
		$('#exampleInterested').dataTable({
			bJQueryUI : true,

			"sPaginationType" : "full_numbers"
		});

	});
</script>
<link rel="shortcut icon" type="image/ico"
	href="http://www.datatables.net/media/images/favicon.ico">
<link rel="alternate" type="application/rss+xml" title="RSS 2.0"
	href="http://www.datatables.net/rss.xml">

<style type="text/css" media="screen">
@import "/resources/media/css/demo_page.css";

@import "/resources/media/css/demo_table.css";

@import "css/datatable/site_jui.ccss";

@import "/resources/media/css/demo_table_jui.css";

@import "/resources/media/css/themes/base/jquery-ui.css";

@import
	"/resources/media/css/themes/smoothness/jquery-ui-1.7.2.custom.css";
/*
			 * Override styles needed due to the mix of three different CSS sources! For proper examples
			 * please see the themes example in the 'Examples' section of this site
			 */
.dataTables_info {
	padding-top: 0;
}

.dataTables_paginate {
	padding-top: 0;
}

.css_right {
	float: right;
}

#example_wrapper .fg-toolbar {
	font-size: 0.8em
}

#theme_links span {
	float: left;
	padding: 2px 10px;
}
</style>



<h1>

	<c:set var="displayName">
		<security:authentication property="principal.displayName" />
	</c:set>


	<s:message code="user.index.title" arguments="${displayName}" />
</h1>
<security:authorize access="hasAnyRole('MODERATOR','MANAGER', 'ADMIN')">
	<div class="row">
	
	</div>

</security:authorize>

<div id="fw_container">

		<div id="fw_header">
<div id="fw_content">
				<div class="full_width">
				<h4>My Cases</h4>
					<table cellpadding="0" cellspacing="0" border="0" class="display"
						id="example">
						<thead>
							<tr>
								<th>Person Name</th>
								<th>Description</th>
								<th>Contact Number</th>
								<th>Source</th>
								<th>Case Status</th>
								<th>Case Type</th>
								<th>Help category</th>
								<th>Created On</th>
								<th>Updated On</th>
								<th>Created By</th>
								<th>Updated By</th>
								<th>Action</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Person Name</th>
								<th>Description</th>
								<th>Contact Number</th>
								<th>Source</th>
								<th>Case Status</th>
								<th>Case Type</th>
								<th>Help category</th>
								<th>Created On</th>
								<th>Updated On</th>
								<th>Created By</th>
								<th>Updated By</th>
								<th>Action</th>
							</tr>
						</tfoot>
						<tbody id="tbody1">
							<c:forEach items="${cases.myCases}" var="mycase">
								<tr id='${mycase.id}'>
									<td>${mycase.personName}</td>
									<td>${mycase.caseDescription}</td>
									<td>${mycase.contact1}</td>
									<td>${mycase.caseSource}</td>
									<td>${mycase.caseStatusString}</td>
									<td>${mycase.caseTypeString}</td>
									<td>${mycase.helpCategoryString}</td>
									<td>${mycase.createdDate}</td>
									<td>${mycase.updatedDate}</td>
									<td>${mycase.createdBy}</td>
									<td>${mycase.updatedBy}</td>
									<td><a href="#">Approve</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
	
	</div>
				<div class="full_width">
				<h4>Cases which may interest you</h4>
					<table cellpadding="0" cellspacing="0" border="0" class="display"
						id="exampleInterested">
						<thead>
							<tr>
								<th>Person Name</th>
								<th>Description</th>
								<th>Contact Number</th>
								<th>Source</th>
								<th>Case Status</th>
								<th>Case Type</th>
								<th>Help category</th>
								<th>Created On</th>
								<th>Updated On</th>
								<th>Created By</th>
								<th>Updated By</th>
								<th>Add</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Person Name</th>
								<th>Description</th>
								<th>Contact Number</th>
								<th>Source</th>
								<th>Case Status</th>
								<th>Case Type</th>
								<th>Help category</th>
								<th>Created On</th>
								<th>Updated On</th>
								<th>Created By</th>
								<th>Updated By</th>
								<th>Add</th>
							</tr>
						</tfoot>
						<tbody id="tbody1">
							<c:forEach items="${cases.interestedCases}" var="mycase">
								<tr id='${mycase.id}'>
									<td>${mycase.personName}</td>
									<td>${mycase.caseDescription}</td>
									<td>${mycase.contact1}</td>
									<td>${mycase.caseSource}</td>
									<td>${mycase.caseStatusString}</td>
									<td>${mycase.caseTypeString}</td>
									<td>${mycase.helpCategoryString}</td>
									<td>${mycase.createdDate}</td>
									<td>${mycase.updatedDate}</td>
									<td>${mycase.createdBy}</td>
									<td>${mycase.updatedBy}</td>
									<td><a href="#">+Add</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="css_clear"></div>

			<div class="css_spacing"></div>

			<div class="css_clear"></div>

		</div>
	</div>

<div class="row">
	<p>
		Click <a href='<s:url value="/"></s:url>'>here</a> to navigate to home
		page. Click <a href='<s:url value="/logout"></s:url>'>here</a> to
		logout.
	</p>

</div>


