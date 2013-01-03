<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="title" content="Subscription Signup | Marketo" />
<meta name="robots" content="index, follow" />
<meta name="description" content="Marketo Search Marketing application" />
<meta name="keywords" content="Marketo, Search Marketing" />
<meta name="language" content="en" />
<title>Register @OCMS | Developed by CareerScale</title>

<link rel="shortcut icon" href="/favicon.ico" />

<script src="resources/scripts/jquery/jquery-1.8.3.min.js"
	type="text/javascript"></script>
<script src="resources/scripts/jquery/jquery.validate.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="resources/scripts/jquery/jquery.maskedinput.js"></script>
<script type="text/javascript"
	src="resources/scripts/application/register.js"></script>

<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/stylesheet.css" />
</head>
</head>
<body>

</body>
</html>





<body>
	<!--[if lte IE 6]>
  <link rel="stylesheet" type="text/css" media="all" href="ie6.css" />
<![endif]-->

	<!-- start page wrapper -->
	<div id="letterbox">

		<!-- start header container -->
		<div id="header-background">
			<div class="nav-global-container">
				<div class="login">
					<a href="#"><span></span>Login</a>
				</div>
				<div class="logo">
					<a href="#"><img src="resources/images/logo_careerscale.gif"
						width="168" height="73" alt="CareerScale" /></a>

				</div>

				<div class="nav-global">
					<ul>
						<li><a href="#" class="nav-g01"><span></span>Home</a></li>
						<li><a href="#" class="nav-g02"><span></span>Cases</a></li>
						<li><a href="#" class="nav-g04"><span></span>Support</a></li>
						<li><a href="#" class="nav-g05"><span></span>About OCMS</a></li>
						<li><a href="#" class="nav-g06"><span></span>Tell a
								Friend</a></li>
					</ul>
				</div>
				OCMS - Online Case Management System, The application connnecting
				the DOTS in the society!
			</div>
		</div>
		<!-- end header container -->
		<div class="line-grey-tier"></div>

		<!-- start page container 2 div-->
		<div id="page-container" class="resize">
			<div id="page-content-inner" class="resize">

				<!-- start col-main -->

				<div id="col-main" class="resize" style="">



					<!-- start main content  -->
					<div class="main-content resize">

						<div class="action-container" style="display: none;"></div>


						<h1>Signup form</h1>
						<p></p>
						<br clear="all" />
						<div>
							<form id="signUpForm" type="actionForm" commandName="user" action="register"
								method="post">


								<div class="error" style="display: none;">
									<img src="resources/images/warning.gif" alt="Warning!"
										width="24" height="24"
										style="float: left; margin: -5px 10px 0px 0px;" /> <span></span>.<br
										clear="all" />
								</div>


								<table cellpadding="0" cellspacing="0" border="0">
									<tr>
										<td class="label"><label for="emailId">Email Id:</label></td>
										<td class="field"><input id="emailId"
											class="required email" maxlength="40" name="emailId"
											size="20" type="text" value="user@careerscale.in"
											tabindex="1" value="" /></td>
									</tr>
									<tr>
										<td class="label"><label for="password">Password:</label></td>
										<td class="field"><input id="password" class="required"
											maxlength="40" name="password" style="width: 163px"
											type="password" tabindex="2" value="test123" /></td>
									</tr>
									<tr>
										<td class="label"><label for="confirmPassword">Retype
												Password:</label></td>
										<td class="field"><input id="confirmPassword"
											class="required" equalTo="#password" maxlength="40"
											name="password2" size="20" type="password" tabindex="3"
											value="test123" />
											<div class="formError"></div></td>
									</tr>


									<tr>
										<td />
										<td />
									</tr>
									<tr>
										<td class="label"><label for="firstName">First
												Name:</label></td>
										<td class="field"><input id="firstName" class="required"
											maxlength="40" name="firstName" size="20" type="text"
											tabindex="4" value="User" /></td>
									</tr>
									<tr>
										<td class="label"><label for="lastName">Last
												Name:</label></td>
										<td class="field"><input id="lastName" class="required"
											maxlength="40" name="lastName" size="20" type="text"
											tabindex="5" value="lastname" /></td>
									</tr>
									<tr>
										<td class="label"><label for="dateOfBirth">Birth
												Date:</label></td>
										<td class="field"><input maxlength="40"
											class="required date" name="dateOfBirth" size="20"
											type="text" tabindex="6" value="01/01/1980" /></td>
									</tr>

									<tr>
										<td></td>
										<td>
											<div class="buttonSubmit">
												<span></span> <input class="formButton" type="submit"
													value="Next" style="width: 140px" tabindex="14" />
											</div>

										</td>

									</tr>
								</table>
								<br /> <br />
							</form>
							<br clear="all" />


						</div>



					</div>
					<!-- end main content  -->
					<br />
				</div>
				<!-- end col-main -->

				<!-- start left col -->
				<div id="col-left" class="nav-left-back empty resize"
					style="position: absolute; min-height: 450px;">
					<div class="col-left-header-tab" style="position: absolute;">Signup</div>
					<div class="nav-left"></div>


					<div class="left-nav-callout png"
						style="top: 15px; margin-bottom: 100px;">
						<img src="resources/images/left-nav-callout-long.png" class="png"
							alt="" />
						<h6>Sign Up Process</h6>
						<a
							style="background-image: url(resources/images/step1-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Provide
							your basic information.</a> <a
							style="background-image: url(resources/images/step2-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Chose
							the type of cases you are interested in.</a> <a
							style="background-image: url(resources/images/step3-24.gif); font-weight: normal; text-decoration: none; cursor: default;">Complete
							registration, validate your email/phone to get updates.</a>
					</div>

					<div class="footerAddress">
						<b>CareerScale IT Consulting LLP.</b><br />
						2-1-7/2/88,Venkateswara Enclave.Upperpally<br /> Hyderabad, AP,
						500048 INDIA<br />
					</div>
					<br clear="all" />
				</div>
				<!-- end left col -->

			</div>
		</div>
		<!-- end page container 2 divs-->

		<div id="footer-container" align="center">
			<div class="footer">
				<ul>
					<li><a href="..">Home</a></li>
					<li class="line-off"><a href="step2.htm">Second step</a></li>
				</ul>
			</div>
		</div>



		<!-- end page wrapper -->
	</div>

</body>
</html>
