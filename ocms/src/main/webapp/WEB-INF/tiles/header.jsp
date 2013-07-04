<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<body>
	<div class="row right">

		<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			<a href="login">Login</a>
			<label>No account yet?</label>
			<a href="/register">Register</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				Hello <sec:authentication property="principal.displayName" />
			<a href="logout">Logout</a>
		</sec:authorize>
		</a>

	</div>
	<div class="row">
		<div class="large-3 columns">
			<a href=""><img src="/resources/images/ocms_logo.png" /></a>
		</div>
		<div class="large-8 columns">
			<h2>Online Case Management System</h2>
			<p>Connecting the needy and the donors</p>
		</div>
	</div>
	<div class="row">
		<div class="large-12 columns">
			<nav class="top-bar">
				<ul class="title-area">
					<!-- Title Area -->
					<li class="name">
						<h1>
							<a href="#"> Menu</a>
						</h1>
					</li>
					<!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone -->
					<li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
				</ul>

				<section class="top-bar-section">
					<!-- Left Nav Section -->
					<ul class="left">
						<li class="divider"></li>
						<li class="active"><a href="/">Home</a></li>
						<li class="divider"></li>
						<li><a href="/about">About us</a></li>
						<li class="divider"></li>
						<li class="has-dropdown"><a href="/cases">Cases</a>
							<ul class="dropdown">
								<li><label>Case Management</label></li>
								<li><a href="/addcase">Report new case</a></li>
								<li><a href="/viewcases">View Cases</a></li>
								<li><a href="/searchcases">Search</a></li>
							</ul></li>
						<li class="divider"></li>
					</ul>

					<!-- Right Nav Section -->
					<ul class="right">
						<li class="divider hide-for-small"></li>




						<li class="divider"></li>
						<li class="has-dropdown"><a href="/user">Profile</a>
							<ul class="dropdown">
								<li><a href="/profile">Profile</a></li>
								<li><a href="/password">Change Password</a></li>
							</ul></li>
						<li class="divider"></li>




						<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
							<li><a href="/backoffice">Backoffice</a></li>
						</sec:authorize>
						<ul class="dropdown">
							<li><a href="/backoffice/casetype">Case Type Management</a></li>
							<li><a href="/backoffice/helptype">Help Type Management</a></li>
							<li><a href="/backoffice/rolemaster">Role Management</a></li>
						</ul>
						<li class="divider"></li>

						<li class="divider"></li>
						<li class="has-form">
							<form>
								<div class="row collapse">
									<div class="small-8 columns">
										<input type="text">
									</div>
									<div class="small-4 columns">
										<a href="#" class="alert button round">Search</a>
									</div>
								</div>
							</form>
						</li>
						<li class="divider show-for-small"></li>
						
					</ul>
				</section>
			</nav>
		</div>

	</div>
	<!-- end header container -->