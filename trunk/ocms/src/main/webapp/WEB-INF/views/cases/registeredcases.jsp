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


				<h3>Case is successfully registered with OCMS.</h3>
				<p>
					Please note that we verify each case reported on OCMS and then
					publish to the community for assistance. It would take few days
					time. <br /> If you really think it is urgent case, please speak to
					one of our moderators at xxxxx or xxxxx. # <br /> <a href="/user">continue</a>
					using OCMS for other activities. Thank you for using our services.
				</p>
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

