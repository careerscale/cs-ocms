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
			<p><b><i>Connecting the needy and the donors</i></b></p>
		</div>
	</div>
	<div class="row">
		<div class="row">
			<div class="large-12 columns">
				<nav class="top-bar">

					<section class="top-bar-section">
						<!-- Left Nav Section -->
						<ul class="left">
							<li class="active"><a href="/">Home</a></li>
							<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
								<li><a href="/about">About</a></li>
								<li class="has-dropdown"><a href="/user">Cases</a>
									<ul class="dropdown">
										<li><a href="/addcase">Report new case</a></li>
										<li><a href="/user">View Cases</a></li>
									</ul></li>
							</sec:authorize>
							<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
								<li class="has-dropdown"><a href="/profile">My Account</a>
									<ul class="dropdown">
										<li><a href="/profile">Edit Profile</a></li>
										<li><a href="/password">Change Password</a></li>
										<li><a href="/cases/receipts">My downloads</a></li>
									</ul></li>

							</sec:authorize>


							<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
								<li class="has-dropdown"><a href="/backoffice">Admin</a>

									<ul class="dropdown">
										<li><a href="/backoffice/casetype">Case Type
												Management</a></li>
										<li><a href="/backoffice/helptype">Help Type
												Management</a></li>
										<li><a href="/backoffice/rolemaster">Role Management</a></li>
									</ul></li>

							</sec:authorize>
							<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
								<li><a href="/logout">Logout</a></li>
							</sec:authorize>
							<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
								<li><a href="/register">Register</a></li>
								<li><a href="/login">Login</a></li>
							</sec:authorize>
						</ul>
					</section>
				</nav>
			</div>

		</div>
		<!-- end header container -->