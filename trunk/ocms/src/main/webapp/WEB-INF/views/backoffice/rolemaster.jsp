<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<h1><spring:message code="view.backoffice.rolemaster.title" /></h1>

<link rel="shortcut icon" type="image/ico"
	href="http://www.datatables.net/media/images/favicon.ico">
<link rel="alternate" type="application/rss+xml" title="RSS 2.0"
	href="http://www.datatables.net/rss.xml">

<style type="text/css" media="screen">
@import "/resources/media/css/demo_page.css";

@import "/resources/media/css/demo_table.css";

@import "http://www.datatables.net/media/css/site_jui.ccss";

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


<script type="text/javascript" src="/resources/media/js/complete.js"></script>
<script src="/resources/media/js/jquery-1.4.4.min.js"
	type="text/javascript"></script>
<script src="/resources/media/js/jquery.dataTables.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="/resources/media/js/jquery.dataTables.editable.js"></script>
<script src="/resources/media/js/jquery.jeditable.js"
	type="text/javascript"></script>
<script src="/resources/media/js/jquery-ui.js" type="text/javascript"></script>
<script src="/resources/media/js/jquery.validate.js"
	type="text/javascript"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var id = -1;//simulation of id
		$('#example').dataTable({
			bJQueryUI : true,

			"sPaginationType" : "full_numbers"
		}).makeEditable({
			sUpdateURL : function(value, settings) {
				//alert(value);

				//alert(settings);
				return (value); //Simulation of server-side response using a callback function
			},
			sUpdateURL : "updateRoleType",
			sAddURL : "addRoleType",
			sDeleteURL : "delRoleType",
			sAddHttpMethod : "GET",
			sDeleteHttpMethod : "GET",
			oAddNewRowButtonOptions : {
				label : "Add...",
				icons : {
					primary : 'ui-icon-plus'
				}
			},
			oDeleteRowButtonOptions : {
				label : "Remove",
				icons : {
					primary : 'ui-icon-trash'
				}
			},

			oAddNewRowFormOptions : {
				title : 'Add a new Role',
				show : "blind",
				hide : "explode",
				modal : true
			},
			sAddDeleteToolbarSelector : ".dataTables_length"

		});

	});
</script>

</head>

<div id="index" class="grid_2_3">
	<div id="fw_container">

		<div id="fw_header">


			<div id="fw_content">

				<h3>Case Types</h3>
				<div class="full_width">
					<form id="formAddNewRow" action="#" title="Add a new browser"
						style="width: 400px; min-width: 300px"-->
						<!--form:form method="post" action="#" id="caseTypeForm"
						modelAttribute="casetype"-->

						<label for="name">Role Type</label><br /> <input type="text"
							name="name" id="name" class="required" rel="0" /> <br /> <label
							for="description">Description</label><br /> <input type="text"
							name="description" id="browser" rel="1" />
						<!-- input type="hidden" name="platform" rel="2" /-->
						<br />
					</form>

					<table cellpadding="0" cellspacing="0" border="0" class="display"
						id="example">
						<thead>
							<tr>
								<th>Role Type</th>
								<th>Description</th>

							</tr>
						</thead>
						<tfoot>
							<tr>

								<th>Role Type</th>
								<th>Description</th>

							</tr>

						</tfoot>
						<tbody id="tbody1">

							<c:forEach items="${botypeList}" var="rolemaster">
								<tr id='${rolemaster.id}'>
									<td>${rolemaster.name}</td>
									<td>${rolemaster.description}</td>

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