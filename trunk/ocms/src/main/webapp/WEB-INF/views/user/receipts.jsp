<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	
<script src="/resources/media/js/responsive-tables.js"  type="text/javascript"></script>
<link rel="stylesheet" href="/resources/media/css/themes/smoothness/responsive-tables.css">
<script src="/resources/media/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var id = -1;//simulation of id
		$('#mycases').dataTable({
			bJQueryUI : true,

			"sPaginationType" : "full_numbers"
		});
	});
</script>
<link rel="shortcut icon" type="image/ico" href="http://www.datatables.net/media/images/favicon.ico">
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="http://www.datatables.net/rss.xml">

<style type="text/css" media="screen">
@import "/resources/media/css/demo_page.css";

@import "/resources/media/css/demo_table.css";

@import "css/datatable/site_jui.ccss";

@import "/resources/media/css/demo_table_jui.css";

@import "/resources/media/css/themes/base/jquery-ui.css";

@import
	"/resources/media/css/themes/smoothness/jquery-ui-1.7.2.custom.css";
	
@import "/resources/media/css/themes/smoothness/responsive-tables.css"
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

.dataTables_wrapper {
     width: 113.8%;
     
     /*93.8%*/
}

 
 #mycases{
 width:100%;
 }
 
 #approvedcases{
 width:100%;
 }
 
 #approvedcases_wrapper{
 width:105%;
 }
 
 #mycases_wrapper{
 width:105%;
 } 
 
 #interestedcases{
 width:100%;
 }
 
 .dataTables_wrapper{
  width:100%;
 }
 
 fg-toolbar{
   width:100%;
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
				<div class="twelve columns">
				<h4>Cases to be acted Upon ( where I am an Approver )</h4>
					<table cellpadding="0" cellspacing="0" border="0" 
						id="mycases"  class="responsive">
						<thead>
							<tr>
								<th nowrap>Amount</th>
								<th nowrap>Purpose</th>
								<th nowrap>Donation Status</th>
								<th nowrap>Promised Date</th>
								<th nowrap>Confirmed Date</th>
								<th nowrap>Case Id</th>
								<th nowrap>Download Receipt</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th nowrap>Amount</th>
								<th nowrap>Purpose</th>
								<th nowrap>Donation Status</th>
								<th nowrap>Promised Date</th>
								<th nowrap>Confirmed Date</th>
								<th nowrap>Case Id</th>
								<th nowrap>Download Receipt</th>
							</tr>
						</tfoot>
						<tbody id="tbody1">
							<c:forEach items="${caseDetails.funds}" var="fund">
								<tr id='${fund.id}'>
									<td>${fund.amount}</td>
									<td>${fund.purpose}</td>
									<td>${fund.fundStatus}</td>
									<td>${fund.promisedDate}</td>
									<td>${fund.confirmedDate}</td>
									<td><a href="/cases/"+${fund.caseId}>${fund.caseId}</a></td>
									<td>
										<c:if test="${(fund.fundStatus eq 'CONFIRMED')}">
											<a href='/user/downloads/receipt/${fund.id}' target="_blank">Receipt</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row"></div>
	
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


