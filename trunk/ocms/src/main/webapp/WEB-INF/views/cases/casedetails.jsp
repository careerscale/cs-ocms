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
<!-- start page container 2 div-->
<h1>
	<spring:message code="cases.casedetails.title" />
</h1>
<script src="/resources/media/js/responsive-tables.js"  type="text/javascript"></script>
<link rel="stylesheet" href="/resources/media/css/themes/smoothness/responsive-tables.css" type="text/css">



<script src="/resources/media/js/jquery.dataTables.min.js" type="text/javascript"></script>


<!--  script src="http://code.jquery.com/jquery-1.9.1.js"></script-->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script src="/resources/media/js/jquery.mousewheel-3.0.6.pack.js"  type="text/javascript"></script>
<script src="/resources/media/js/jquery.fancybox.js?v=2.1.5"  type="text/javascript"></script>
<link rel="stylesheet" href="/resources/media/css/themes/smoothness/jquery.fancybox.css?v=2.1.5" media="screen"  type="text/css">




<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"  type="text/css" >
<link rel="stylesheet" href="/resources/demos/style.css" >


<script src="/resources/media/js/jquery.popupoverlay.js" type="text/javascript"></script>



 <style>
  /* IE has layout issues when sorting (see #5413) */
  .group { zoom: 1 }
 
  #resizable { width: 400px; height: 200px; background: silver; }
  
  .ui-accordion .ui-accordion-header .ui-icon {
    	left: -2px !important;
    }
    
    
    
    #feedback { font-size: 1.4em; }
  #selectable .ui-selecting { background: #FECA40; }
  #selectable .ui-selected { background: #F39814; color: white; }
  #selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  #selectable li { margin: 3px; padding: 0.4em; font-size: 1.4em; height: 18px; }
  
  
  
  </style>
  
  
  <style>
.postbit, .postbitlegacy, .eventbit {
    border: 1px solid #727453;
    clear: both;
    color: #3E3E3E;
    display: block;
    float: left;
    margin-bottom: 10px;
    position: relative;
    width: 100%;
}
li {
    list-style: none outside none;
}

.postbit .postdetails_noavatar .posthead, .postbit .postdetails_noavatar .userinfo .contact {
    margin-left: 0;
}

.postbit .posthead, .postbitlegacy .posthead, .eventbit .eventhead {
    background: none repeat-x scroll 0 0 #727453;
    border: 1px solid #727453;
    clear: both;
    color: #FFFFFF;
    display: block;
    float: left;
    font: 12px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    margin: -1px -1px 0;
    padding: 4px 0;
    width: 100%;
}

.postbit .posthead .postdate.old, .postbitlegacy .posthead .postdate.old {
    background: url("images/statusicon/post_old.png") no-repeat scroll left center transparent;
}
.postbit .posthead .postdate, .postbitlegacy .posthead .postdate {
    clear: right;
    display: block;
    float: left;
    font: 12px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    margin-left: 10px;
    padding-left: 15px;
    width: 49%;
}

.postbit .posthead .postdate, .postbitlegacy .posthead .postdate {
    font: 12px Tahoma,Calibri,Verdana,Geneva,sans-serif;
}

.postbit .postdate .time {
    color: #FFFFFF;
    margin-left: 10px;
}

.postbit .userinfo, .postbit .userinfo_noavatar {
    background: none repeat scroll 0 0 #F2F6F8;
    border-bottom: 1px solid #727453;
    clear: both;
    float: left;
    height: auto !important;
    padding: 0.5em 0;
    width: 100%;
}

.postbit .postdetails_noavatar .posthead, .postbit .postdetails_noavatar .userinfo .contact {
    margin-left: 0;
}
.postbit .userinfo .contact, .userinfo .userinfo_extra, .postbit .userinfo_noavatar .contact {
    display: inline-block;
    height: auto !important;
}

.postbit .userinfo .contact, .postbit .userinfo_noavatar .contact {
    clear: right;
    /*display: block;*/
    float: left;
    position: relative;
    width: 50%;
}

.postbit .postuseravatarlink {
    clear: right;
    display: block;
    float: left;
    margin-left: 15px;
    position: relative;
    text-align: center;
    top: 0;
}
body a {
    color: #0033CC;
    text-decoration: none;
}

.postbit .postuseravatarlink img {
}
fieldset, img {
    border: 0 none;
}


.postbit .postuseravatarlink {
    text-align: center;
}

.postbit .postbody {
    background: none repeat scroll 0 0 #FAFAFA;
    clear: both;
    color: #333333;
}

.postbit .postrow {
    font: 13px Verdana,Arial,Tahoma,Calibri,Geneva,sans-serif;
    padding: 5px 10px 3em;
}

.postrow {
    overflow: auto;
}

.postbit .posttitle, .postbitlegacy .title {
    border-bottom: 1px solid #C8C8C8;
    margin-bottom: 5px;
    padding-bottom: 5px;
}
.postbit .posttitle {
    display: block;
    font: bold 14px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    /*margin: 0;
    padding: 10px;*/
}

.postcontent {
    word-wrap: break-word;
}
blockquote {
    overflow: hidden;
}

.postbody img.inlineimg {
    vertical-align: bottom;
}
fieldset, img {
    border: 0 none;
}

.postbit .postfoot, .postbitlegacy .postfoot, .eventbit .eventfoot {
    background: none repeat scroll 0 0 transparent;
    clear: both;
    display: block;
    float: left;
    font: bold 11px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    position: relative;
    right: 0;
    width: 100%;
}

.floatcontainer:after, .formcontrols .blockrow:after, dl.stats:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}
.postbit .postfoot .textcontrols, .postbitlegacy .postfoot .textcontrols, .eventbit .eventfoot .eventcontrols {
    background: none repeat scroll 0 0 #727453;
    display: block;
    font: bold 11px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    margin-right: 10px;
    padding: 6px 0 4px;
    width: 100%;
}


.postbitlegacy .postfoot .postcontrols, .postbit .postfoot .postcontrols {
    float: right;
    padding-right: 10px;
    text-align: right;
    width: 50%;
}

.postbitlegacy .postfoot .textcontrols a.newreply, .postbit .postfoot .textcontrols a.newreply {
    background: url("images/buttons/quote_40b.png") no-repeat scroll left center #727453;
    border: 0 solid #A8A8A8;
    padding: 0 0 0 20px;
}
.postbitlegacy .postfoot .textcontrols a, .postbit .postfoot .textcontrols a, .eventbit .eventfoot .eventcontrols a, .postbitlegacy .postfoot .textcontrols span.mobile, .postbit .postfoot .textcontrols span.mobile {
    background: none repeat scroll 0 0 #727453;
    border: 0 solid #A8A8A8;
    border-radius: 0 0 0 0;
    color: #FFFFFF;
    display: inline-block;
    font: bold 11px Tahoma,Calibri,Verdana,Geneva,sans-serif;
    margin-left: 5px;
    padding: 0 0 0 23px;
}
.textcontrols a, a.textcontrol {
    background: none repeat-x scroll left top #5B5D43;
    border: 1px solid #A8A8A8;
    border-radius: 0 0 0 0;
    color: #FFFFFF;
    font: bold 11px/1.23 Tahoma,Calibri,Verdana,Geneva,sans-serif;
    padding: 3px 5px;
    position: relative;
    text-decoration: none;
}


</style>
  
  
<style type="text/css" media="screen">
@import "/resources/media/css/demo_page.css";

@import "/resources/media/css/demo_table.css";

@import "css/datatable/site_jui.ccss";

@import "/resources/media/css/demo_table_jui.css";

@import "/resources/media/css/themes/base/jquery-ui.css";

@import
	"/resources/media/css/themes/smoothness/jquery-ui-1.7.2.custom.css";
	
@import "/resources/media/css/themes/smoothness/responsive-tables.css"

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
     width: 87%;
 }
 
 
 .greenStatus
{
color:green;
}
.yellowStatus
{
color:yellow;
}

#selectable li {
    font-size: 0.7em;
    height: 128px;
    margin: 3px;
    padding: 0.4em;
}

#selectable {
    width: 100%;
}

.ui-dialog{
 top: -70em !important;
  left: 30em !important;
}

.ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable
{
top: -70em !important;
  left: 30em !important;
}

/* Add these styles once per website */
  .popup_background {
    z-index: 2000; /* any number */
  }
  .popup_wrapper {
    z-index: 2001; /* any number + 1 */
  }
  /* Add inline-block support for IE7 */
  .popup_align,
  .popup_content {
    *display: inline;
    *zoom: 1;
  }
  
  .well {
    background-color: #F5F5F5;
    border: 1px solid #E3E3E3;
    border-radius: 4px 4px 4px 4px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05) inset;
    margin-bottom: 20px;
    min-height: 20px;
    padding: 19px;
}

.ui-accordion-content
{
height:auto !important;
}
</style>


<script type="text/javascript">

$(function() {
    $('#my_modal').popup();
    
    $('#newreply_modal').popup();
    
  });
  
  
$(document).ready(function() {
	$(".fancybox").fancybox();
});

function openPopup(id)
{
	$("#parentDiscussionId").val(id);
}
$(function() {
    $( "#dialogReply" ).dialog({
    	 height: 140,
         modal: true
    });
  });

$(function() {
	
	$( "#dialog-form" ).dialog({
	      autoOpen: false,
	      height: 180,
	      width: 450,
	     top:500,
	     
	      modal: true,
	      buttons: {
	    	  
	      }
	});
	
	
	$(function() {
		
		$( "#dialog-add" ).dialog({
		      autoOpen: false,
		      height: 270,
		      width: 450,
		     top:500,
		     
		      modal: true,
		      buttons: {
		    	  
		      }
		});
	});
	
		/*$( ".newreply-open" ).button().click(function() {
		  $( "#newreply" ).dialog( "open" );
		});*/
		
		/* $( "#create-comments" ).button().click(function() {
			  $( "#dialog-add" ).dialog( "open" );
			}); */
});

$(function() {
    $( "#selectable" ).selectable();
  });
  
  
$(function() {
	
	$(document).ready(function() {
		var id = -1;//simulation of id
		$('#mycases').dataTable({
			bJQueryUI : true,

			"sPaginationType" : "full_numbers"
		});
	});
	
	
    $( "#accordion" )
      .accordion({
        header: "> div > h3"
      })
      .accordion({
    	  collapsible: true
      })
      .sortable({
        axis: "y",
        handle: "h3",
        stop: function( event, ui ) {
          // IE doesn't register the blur when sorting
          // so trigger focusout handlers to remove .ui-state-focus
          ui.item.children( "h3" ).triggerHandler( "focusout" );
        }
      });
  });
  
$(document).ready(function() {
    $("#resizable").resizable();
  });
</script>
<div>


  

<div id="newreply_modal" class="well" title="Add Comments">
   <form:form method="post" action="caseDiscussion" id="addCaseForm2"
				modelAttribute="caseDetails">
  <div id="submitComments">
  <fieldset >
    <label for="comments">Comments</label>
	<form:textarea path="comments" rows="10" cols="30"   id="resizable111" />
	<br>
	<input class="button small round inline right" type="submit" name="status" value="Submit"  />
	 <form:hidden path="parentDiscussionId" id="parentDiscussionId" />
	 <form:hidden path="caseId" value="${caseDetails.id}" />
  </fieldset>
  </div>
</form:form>
 </div>
 
 
  <div id="my_modal" class="well" title="Add Comments">
   <form:form method="post" action="caseDiscussion" id="addCaseForm1"
				modelAttribute="caseDetails">
  <div id="submitComment1">
  <fieldset >
  	<label for="subject">Subject</label>
    <input type="text" name="subject" id="subject123" class="text ui-widget-content ui-corner-all" />
    
    <label for="comments">Comments</label>
	<form:textarea path="comments" rows="10" cols="30"   id="resizable222" />
	<br>
	<input class="button small round inline right" type="submit" name="status" value="Submit"  />
	<form:hidden path="caseId" value="${caseDetails.id}" />
	
	
  </fieldset>
  </div>
</form:form>
 </div>
 
   
<div class="row full-width">
			</div>
			<div class="large-1 columns"></div>
			<div class="large-5 columns"><h4>Case Status</h4></div>
			<div class="large-6 columns"><h4 style="color:green;">${caseDetails.caseStatusString}</h4>
			</div>
	<br clear="all" />
	<br clear="all" />
	<form:form method="post" action="caseAction" id="addCaseForm"
				modelAttribute="caseDetails">
<div class="large-1 columns"></div>

<div id="accordion" class="large-11 columns" >
  <div class="group">
   <h3><b>Actions</b></h3>
    <div>
      <p>Approve, Reject or Ask Clarifications for the case</p>
      <div class="row">
						<div class="large-9 columns">
							
							<form:textarea path="reason" rows="5" cols="30"   id="resizable" />
							
							<!-- <textarea name="test" id="resizable"></textarea> -->
						</div>
						<div class="large-3 columns">
							<form:hidden path="caseId" value="${caseDetails.id}" />
						</div>
					</div>
					
					<br clear="all" />
			  <div class="row">	
			  		</div>
					
							<div class="left inline">
								
								
								<div class="large-3 columns">
											<input class="button small round inline right" type="submit" name="status" value="Approve Case" style="width: 120px" tabindex="14" />
								</div>
								<div class="large-3 columns">
									 <input class="button small round inline right" type="submit" value="Reject Case" style="width: 120px" tabindex="14" />
								</div>
								<div class="large-4 columns">
									<input class="button small round inline right" type="submit" value="Ask For More Info" style="width: 150px" tabindex="14" />
								</div>
								<div class="large-2 columns"></div>
								
							</div>
						
      
    </div>
  </div>
  <div class="group">
    <h3><b>Case Details</b></h3>
     <div>
      
     <div class="large-6 columns">
     
		     <div class="large-6 columns">
		     <label>Person Name:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.personName}
		     </div>
	
	</div>
	<div class="large-6 columns">
	 		<div class="large-6 columns">
		    <label>Contact:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.contact1}
		     </div>
	</div>
	<br clear="all" />
	<br clear="all" />
	 <div class="large-6 columns">
			<div class="large-6 columns">
		    <label>Source:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.caseSource}
		     </div>
	</div>
	<div class="large-6 columns">
			<div class="large-6 columns">
		    <label>Case Status:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.caseStatusString}
		     </div>
	</div>
	<br clear="all" />
	<br clear="all" />
	 <div class="large-6 columns">
			<div class="large-6 columns">
		    <label>Case Type:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.caseTypeString}
		     </div>
	</div>
	<div class="large-6 columns">
			<div class="large-6 columns">
		   <label> Help Category:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.helpCategoryString}
		     </div>
	</div>
	<br clear="all" />
	<br clear="all" />
	 <div class="large-6 columns">
			<div class="large-6 columns">
		    <label>Created By:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.createdBy}
		     </div>
	</div>
	<div class="large-6 columns">
		<div class="large-6 columns">
		    <label>Updated By:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.updatedBy}
		     </div>
	</div>
	<br clear="all" />
	<br clear="all" />
	 <div class="large-6 columns">
		<div class="large-6 columns">
		    <label>Created On:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.createdDate}
		     </div>
	</div>
	<div class="large-6 columns">
		<div class="large-6 columns">
		    <label>Updated On:</label>
		     </div>
		      <div class="large-6 columns">
		     ${caseDetails.updatedDate}
		     </div>
	</div>
	<br clear="all" />
	<br clear="all" />
	<div class="large-12 columns">
			<div class="large-3 columns">
		    <label>Case Description:</label>
		     </div>
		      <div class="large-9 columns">
		     ${caseDetails.caseDescription}
		     </div>
	</div>
	
	<br clear="all" />
	<br clear="all" />
	 <div class="large-12 columns">
	<c:forEach var="entry" items="${caseArtifacts.caseDocuments}">
					
						<div class="row">
							<div class="large-6 columns">
							<a  class="fancybox"  href="../1_b.jpg" data-fancybox-group="gallery" title="">
							<img src="/cases/documents/${entry.id}">
								</img></a>
						
							</div>
							<div class="large-6 columns">
							<div id="img_modal" class="well">
								<!--  img src="/cases/documents/${entry.id}">csd
								</img-->
							</div>
						   
							</div>
						</div>
						</c:forEach>
	
	</div>
	
	
  </div>
  </div>
  <div class="group">
    <h3><b>Approval History</b></h3>
    <div>
    <div id="fw_container">

		<div id="fw_header">
<div id="fw_content">

      <div class="twelve columns">
				<h4>Approval History</h4>
					<table cellpadding="0" cellspacing="0" border="0" 
						id="mycases"  class="responsive">
						<thead>
							<tr>
								<th nowrap>Case Action</th>
								<th nowrap>Approver Name</th>
								<th nowrap>Comments</th>
								<th nowrap>Action Date</th>
								<th nowrap>Approver Email</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th nowrap>Case Action</th>
								<th nowrap>Approver Name</th>
								<th nowrap>Comments</th>
								<th nowrap>Action Date</th>
								<th nowrap>Approver Email</th>
							</tr>
						</tfoot>
						<tbody id="tbody1">
							<c:forEach items="${caseDetails.caseHistoryList}" var="caseHistory">
								<tr id='${caseHistory.caseId}'>
									<td>${caseHistory.status}</td>
									<td>${caseHistory.userName}</td>
									<td>${caseHistory.reason}</td><!-- <td></td>-->
									<td></td> 
									
									<%--  <td>${caseHistory.actionDate}</td> --%>
									<td>${caseHistory.approverContactEmail}</td> 
									
									
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				</div>
				</div></div>
    </div>
  </div>
  <div class="group">
    <h3><b>Discussion</b></h3>
    <div>
    <div class="large-2 columns">
    </div>
    	 <div class="large-2 columns">
   		<a class="my_modal_open" id="create-comments">+ Add</a>
   		</div>
   		<div class="large-8 columns">
   		</div>
   		
    	<br /><br />
    	 <div class="large-12 columns">
    	<ol id="sele11ctable">
    		<c:if test="${caseDetails.caseDiscussions.size() > 0}">
    				<c:forEach var="caseDiscuss" items="${caseDetails.caseDiscussions}">
    			 <li class="postbit postbitim postcontainer old">
    			 <div class="postdetails_noavatar">
    				<div class="posthead">
                       <span class="postdate old">
                         <span class="date">${caseDiscuss.createdOn} ,&nbsp;<span class="time">07:46 AM</span></span>
                       </span>
        			</div>
        			<div class="userinfo">
						<div class="contact">
							<a href="" class="postuseravatarlink">${caseDiscuss.createdBy}</a>
						</div>
					</div>
			 	</div>
			 	<div class="postbody">
			 		<div class="postrow">
						<h2 class="posttitle icon">${caseDiscuss.subject}</h2> 
						
						  <c:if test="${caseDiscuss.parentDiscussionId !=null}"> 
						  <h2 class="posttitle icon">  &nbsp; &nbsp; &nbsp; In Reply to  : &nbsp; ${caseDiscuss.parentSubject} &nbsp; &nbsp; By  &nbsp; ${caseDiscuss.parentCreatedBy}</h2> 
						  </c:if>
						<div class="content">
							<div id="post_message_2122861">
							<blockquote class="postcontent restore">${caseDiscuss.comments}</blockquote>
							</div>
						</div>
					</div>
				</div>
		
				<div class="postfoot">
					<div class="textcontrols floatcontainer">
						<span class="postcontrols">
							<img alt="" src="struct%20usage%20and%20setup_files/progress.gif"  style="display:none">
							<a title="Reply With Quote" rel="nofollow" href="#"  class="newreply_modal_open" onClick="openPopup('${caseDiscuss.id}')" >Reply With Quote
							 </a>
						</span>
					</div>
				</div>
				
				
    			 </li>
  					
  					</c:forEach>
    		</c:if>
    	</ol>
 
  </div>
     </div>
  </div>
   <div class="group">
    <h3><b>Financials</b></h3>
    <div>
      <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. </p><p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. </p>
    </div>
  </div>
  
  
</div>

</form:form>

  
  
<!-- <div id="dialogReply" title="Basic modal dialog">
  <p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p>
</div>
 -->

<!-- end main content  -->
<br />
<div id="col-left" class="nav-left-back empty resize"></div>

</div>




