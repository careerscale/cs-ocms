<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="large-3 columns">
		<h1>
			<img src="/resources/images/ocms_logo.png">
		</h1>
	</div>
	<div class="large-9 columns">
		<ul class="right button-group">
			<li><a href="/about" class="button">About Us</a></li>
			<li><a href="/addcase" class="button">Add Case</a></li>
			<li><a href="/backoffice" class="button">Back Office</a></li>
			<li><a href="http://careerscale.in" class="button">About
					Careerscale</a></li>
		</ul>
	</div>
</div>

<!-- End Header and Nav -->

<!-- First Band (Slider) -->

<div class="row">
	<div class="large-1 columns">&nbsp;</div>
	<div class="large-10 columns">
		<div class="orbit-container orbit-stack-on-small">
			<ul data-orbit="" class="orbit-slides-container"
				style="margin-left: -200%; width: 300%; height: 350px;">
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image4.png" class="inline"></li>
				<!--  images should be of 1000*400 -->
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image1.png" class="inline"></li>
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image4.png" class="inline"></li>
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image1.png" class="inline"></li>
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image4.png" class="inline"></li>
				<li data-orbit-slide="" class="inline"><img
					src="/resources/images/home/image1.png" class="inline"></li>
			</ul>

			<!--a href="http://foundation.zurb.com/page-templates4/orbithome.html#" class="orbit-prev">Prev <span></span></a><a href="http://foundation.zurb.com/page-templates4/orbithome.html#" class="orbit-next">Next <span></span></a-->

			<div class="orbit-slide-number">
				<span>4</span> of <span>4</span>
			</div>

			<div class="orbit-timer paused">
				<span></span>
				<div class="orbit-progress" style="width: 0%;"></div>
			</div>
		</div>

		<!--ol class="orbit-bullets">
        <li data-orbit-slide-number="1" class=""></li>
        <li data-orbit-slide-number="2" class=""></li>
        <li data-orbit-slide-number="3" class=""></li>
        <li data-orbit-slide-number="4" class="active"></li>
      </ol-->
		<!-- <div id="slider">
      
    </div> -->

	</div>

	<div class="large-1 columns">&nbsp;</div>
</div>

<!-- Three-up Content Blocks -->

<div class="row">
	<div class="large-4 columns">
		<img src="/resources/images/home/connecting_dots.png">
		<!-- 400x300 image -->
		<h4>Reaching the needy</h4>
		<p>There are so many people needing help(poor students unable to
			pay fee, poor people needing medical help etc. OCMS aims to connect
			the needy with donors and organizations so as needy gets benefited in
			time.</p>
	</div>

	<div class="large-4 columns">
		<img src="/resources/images/home/saas.png">
		<h4>SAAS model for NGOs</h4>
		<p>A single application that helps manage all your NGO activities
			in a single place with super duper multi tenancy built in. OCMS is
			conceived and developed by key members of a leading NGO from India.</p>
	</div>

	<div class="large-4 columns">
		<img src="/resources/images/home/single_place_for_ngo.jpg">
		<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			<h3>Login to OCMS</h3>
			<div class="row">Login</div>
			<form method="POST" id="signup" class="searchboxbg"
				action="j_spring_security_check">
				<div class="row">

					<label for="j_username">Email Id</label> <input type="text"
						value="hari@harinath.in" name="j_username" />
				</div>

				<div class="row">
					<label for="j_password">Enter password</label> <input
						type="password" value="test123" name="j_password" />
				</div>

				<div class="row">
					<input type="submit" class="button round" value="Login" /> <a
						href="/forgotpassword" class="button">Forgot password? </a>
				</div>
				<div class="row">
					<label>No account yet?</label> <a href="/register">Register</a>
				</div>
				<div class="row">
					<label>Login with</label>
				</div>
				<div class="row">
					<a href="/login-facebook">Facebook</a> <a href="/login-google">Google</a>
				</div>

				<div class="row">
					<a href="/login-linkedin">LinkedIn</a> <a href="/login-twitter">Twitter</a>
				</div>
			</form>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<br />
			<br />
				Hello <sec:authentication property="principal.displayName" />
				Welcome to OCMS, use the top navigation to access your account and manage the cases.
				<br />
			<a href="logout">Logout</a>
		</sec:authorize>

	</div>

</div>



<!-- Call to Action Panel -->
<div class="row">
	<div class="large-12 columns">

		<div class="panel">
			<h4>Get in touch!</h4>

			<div class="row">
				<div class="large-9 columns">
					<p>We'd love to hear from you</p>
				</div>
				<div class="large-3 columns">
					<a href="/aboutus" class="radius button right">Contact Us</a>
				</div>
			</div>
		</div>

	</div>
</div>