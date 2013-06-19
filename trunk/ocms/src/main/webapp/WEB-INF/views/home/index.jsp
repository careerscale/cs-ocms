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
			<li><a
				href="/about"
				class="button">About Us</a></li>
			<li><a
				href="/addcase"
				class="button">Add Case</a></li>
			<li><a
				href="/backoffice"
				class="button">Back Office</a></li>
			<li><a
				href="http://careerscale.in"
				class="button">About Careerscale</a></li>
		</ul>
	</div>
</div>

<!-- End Header and Nav -->

<!-- First Band (Slider) -->

<div class="row">
	<div class="large-12 columns">
		<div class="orbit-container orbit-stack-on-small">
			<ul data-orbit="" class="orbit-slides-container"
				style="margin-left: -400%; width: 600%; height: 388px;">
				<li data-orbit-slide="" style="width: 16.666666666666668%;"><img
					src="/resources/images/home/image4"></li>
					<!--  images should be of 1000*400 -->
				<li class="" style="width: 16.666666666666668%;"><img
					src="/resources/images/home/image1"></li>
				<li style="width: 16.666666666666668%;" class=""><img
					src="/resources/images/home/image2"></li>
				<li style="width: 16.666666666666668%;" class=""><img
					src="/resources/images/home/image3"></li>
				<li style="width: 16.666666666666668%;" class="active"><img
					src="/resources/images/home/image4"></li>
				<li data-orbit-slide="" style="width: 16.666666666666668%;"><img
					src="/resources/images/home/image1"></li>
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

		<hr>
	</div>
</div>

<!-- Three-up Content Blocks -->

<div class="row">
	<div class="large-4 columns">
		<img src="/resources/images/home/another_image"> <!-- 400x300 image -->
		<h4>Reaching the needy</h4>
		<p>The essence of OCMS is to reach the genuine needy (yes our process pays high attention for verification process). There are so many people in disperate condition needing help, bright but poor students unable to pay fee, poor people unable to pay for their medical expenses etc. We are not gods but tryig our level best to connect the needy with the kind hearted people and organizations and making sure we provide help in time.</p>
	</div>

	<div class="large-4 columns">
		<img src="/resources/images/home/another_image">
		<h4>This is a content section.</h4>
		<p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation
			eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore
			aliqua non est magna in labore pig pork biltong. Eiusmod swine spare
			ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.</p>
	</div>

	<div class="large-4 columns">
		<img src="/resources/images/home/another_image">
		<sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
			<h3>Login to OCMS</h3>
			<div class="row">Login</div>
			<form method="POST" id="signup" class="searchboxbg"
				action="j_spring_security_check">
				<div class="row">

					<label for="j_username">Email Id</label> <input type="text"
						value="hari@harinath.in" name="j_username"  />
				</div>

				<div class="row">
					<label for="j_password">Enter password</label> <input type="password"
						value="test123" name="j_password" />
				</div>

				<div class="row">
					<input type="submit" value="Login" />
				</div>
				</form>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				Hello <sec:authentication property="principal.displayName" />
			<a href="logout">Logout</a>
		</sec:authorize>
	
	</div>
	<p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation
		eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore
		aliqua non est magna in labore pig pork biltong. Eiusmod swine spare
		ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.</p>
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
					<a
						href="/aboutus"
						class="radius button right">Contact Us</a>
				</div>
			</div>
		</div>

	</div>
</div>