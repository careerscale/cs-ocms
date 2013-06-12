<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" media="screen" href="/resources/css/my.css" />

<style>
#letterbox {
	background-color: white;
	border: 0px solid #D4D4D4;
	margin: 10px auto 10px 170px;
	padding: 1px 1px 10px;
	width: 110%;
}

#header-background {
	visibility: hidden;
}

.line-grey-tier {
	visibility: hidden;
}
</style>
<div id="page">


	<div class="home" id="content_container"
		style="padding-bottom: 505px; padding-left: 200px;">
		<!-- content_container start -->
		<div id="content">
			<div class="top_info">
				<div class="bh_breadcrumb"></div>
				<!-- !/bh_breadcrumb -->
				<div class="tabs"></div>
				<!-- tabs -->
			</div>
			<!-- !/top_info -->
			<div class="layout_wide" id="content_core">
				<div class="node" id="node-225">




					<div class="content clear-block">
						<div id="node-edit-link-225" class="node-edit-link"
							style="display: none;"></div>
						<div class="headerRep">
							<img src="/resources/images/ocms_tag.png"
								alt="OCMS - Online Portal for Charity"></img>
						</div>
						<div class="topSection">
							<div class="messageBox">
								<div id="slides">
									<ul class="pagination">
										<li class=""><a href="#0">1</a></li>
										<li class=""><a href="#1">2</a></li>
										<li class="current"><a href="#2">3</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div id="home_content">
							<div id="home_left_col">
								<a href="/resources/docs/OCMS.pptx"> <img
									src="/resources/images/OCMShighlevelflow.jpg" />
								</a>

							</div>
							<div id="signup-box" class="box-border signupForm">
								<div class="box">
									<div class="inframe">

										<div class="header">Login</div>
										<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
											<form method="POST" id="signup" class="searchboxbg"
												action="/j_spring_security_check">


												<div class="formBox">
													<div class="formCaption">Enter userId</div>
													<div class="formTxt">
														<input id="username" name="j_username" class="formtxtbox"
															type="text" tabindex="1" value="hari@harinath.in" />

													</div>
												</div>
												<div class="formBox">
													<div class="formCaption">Enter password</div>
													<div class="formTxt">
														<input id="password" class="formtxtbox" name="j_password"
															type="password" tabindex="2" value="test123" />
													</div>
												</div>
												<div class="formBox">
													<input type="submit" value="Login" class="btn_getstarted" />
												</div>
												<div class="formBox">
													<a href="/forgotpassword">Forgot password? </a>
												</div>

												<div class="formBox">Sign in using your account with</div>
												<div class="formBox">
													<div id="sign_in_opts">
														<div class="box_gray2">
															<a href=/login-facebook><div id="fb_btn"
																	class="ya-btn ui-btn-d">
																	<div class="btn-icon">
																		<div class="btn-icon-img"></div>
																	</div>
																	<div class="btn-div"></div>
																	<div class="btn-label">Facebook</div>
																</div> </a> <a href=/login-google><div id="gm_btn"
																	class="ya-btn ui-btn-e">
																	<div class="btn-icon">
																		<div class="btn-icon-img"></div>
																	</div>
																	<div class="btn-div"></div>
																	<div class="btn-label">Google</div>
																</div> </a> <a href=/login-linkedin>
																<div id="ln_btn" class="ya-btn ui-btn-l">
																	<div class="btn-icon">
																		<div class="btn-icon-img"></div>
																	</div>
																	<div class="btn-div"></div>
																	<div class="btn-label">LinkedIn</div>
																</div>
															</a> <a href=/login-twitter>
																<div id="tw_btn" class="ya-btn ui-btn-f">
																	<div class="btn-icon">
																		<div class="btn-icon-img"></div>
																	</div>
																	<div class="btn-div"></div>
																	<div class="btn-label">Twitter</div>
																</div>
															</a>
														</div>
														<div class="formBox">
															<!-- END box_gray -->
															<input type="button" value="Register"
																class="btn_register" onClick="location.href='/register'" />
														</div>
													</div>
												</div>
										</sec:authorize>
										<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
											<div class="formBox">
												Hello
												<sec:authentication property="principal.username" />
												<a href="logout">Logout</a>
											</div>
										</sec:authorize>


										</form>
									</div>
								</div>
							</div>

							<p></p>
						</div>
					</div>

					<div class="clear-block"></div>


				</div>
			</div>
			<!-- !/content_core -->
		</div>
		<!-- content -->

		<!-- content_container end -->
		<div id="footer_gap"></div>
	</div>





	<div id="header">
		<!--start header-->
		<div id="header_content">
			<div id="logo_block">
				<a href="/"><img alt="" src="/resources/images/ocms_logo.png"></img></a>
			</div>

			<div id="header_nav_block">
				<div class="home_menu" id="main_menu">
					<div class="clear-block block block-menu"
						id="block-menu-menu-reachmainmenu">


						<div class="content">
							<ul class="menu">
								<li class="leaf first active-trail"><a class="active"
									title="" href="/">Home</a></li>
								<li class="leaf"><a title=""
									href="/resources/docs/OCMS.pptx">How it works</a></li>
								<li class="leaf"><a title="" href="/about">About us</a></li>
								<li class="leaf"><a title="Resources"
									href="http://code.google.com/p/cs-ocms">Resources</a></li>
								<li class="leaf"><a title="" href="http://tmad.org">TMAD</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- !/main_menu -->
			</div>
		</div>
		<!--end header-->
	</div>




</div>