<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!-- start header container -->
<div id="header-background">
	<div class="nav-global-container">
		<div class="login">
			<a href="#"><span></span> 
			<sec:authorize
					ifAnyGranted="ROLE_ANONYMOUS"> 	<a href="login">Login</a>
			
			</sec:authorize>
			<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				Hello <sec:authentication property="principal.username" /> <a href="logout" >Logout</a>				
			</sec:authorize>
			
			</a>
		</div>
		<div class="logo">
			<a href="#"><img src="resources/images/logo_careerscale.gif"
				width="168" height="73" alt="CareerScale" /></a>

		</div>

		<div class="nav-global">
			<ul>
				<li><a href="/" class="nav-g01"><span></span>Home</a></li>
				<li><a href="/cases" class="nav-g02"><span></span>Cases</a></li>
				<li><a href="/support" class="nav-g04"><span></span>Support</a></li>
				<li><a href="/about" class="nav-g05"><span></span>About OCMS</a></li>				
				<li><a href="/backoffice" class="nav-g06"><span></span>Back Office</a></li>
			</ul>
		</div>
		<strong>OCMS - Online Case Management System, Connecting the DOTS in the society!</strong>
	</div>
</div>
<!-- end header container -->
<div class="line-grey-tier"></div>
