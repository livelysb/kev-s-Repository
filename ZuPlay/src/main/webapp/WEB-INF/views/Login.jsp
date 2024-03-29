<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="icon" href="resources/img/favicon.ico"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>play STOCK - Zuplay</title>

<c:url value="/resources" var="url" />

<!-- Bootstrap Core CSS -->
<link href="${url}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="${url}/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic'
	rel='stylesheet' type='text/css'>

<!-- Plugin CSS -->
<link href="${url}/vendor/magnific-popup/magnific-popup.css"
	rel="stylesheet">

<!-- Theme CSS -->
<link href="${url}/css/creative.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top">

	<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top">Zuplay</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a class="page-scroll" href="#about">Services</a></li>
					<li><a class="page-scroll" href="#services">About</a></li>
					<li><a class="page-scroll" href="#contact">Contact</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<header>
		<div class="header-content">
			<div class="header-content-inner">
				<h1 id="homeHeading">주식을 플레이하라 !</h1>
				<hr>
				<p>
					1%대 초저금리 시대 - 예금으로 돈버는 시대는 끝났다.<br> 게임으로 쉽게 배우는 주식
				</p>
				<p></p>
				<div id="naver_id_login"></div>
			</div>
		</div>
	</header>
	<section class="bg-primary" id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<h2 class="section-heading">실제 주식정보를 가져온 모의주식 게임</h2>
					<hr class="light">
					<p class="text-faded">
						실시간 주식정보를 통해 실제 주식거래를 하는 것 같은 느낌을 Up!<br> 870여개의 kospi 기업정보
						등재!
					</p>
					<a href="#page-top"
						class="page-scroll btn btn-default btn-xl sr-button">Game
						Start</a>
				</div>
			</div>
		</div>
	</section>

	<section id="services">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Team Introduce</h2>
					<hr class="primary">
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row no-gutter popup-gallery">
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/1.jpg" class="portfolio-box"
						style="text-align: center"> <img
						src="${url}/img/portfolio/thumbnails/1.jpg"  width="100%"   class="img-responsive"
						alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">Leader</div>
								<div class="project-name">Lee Seok-bum</div>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/2.jpg" class="portfolio-box"
						style="text-align: center"> <img
						src="${url}/img/portfolio/thumbnails/2.jpg"  width="100%"  class="img-responsive"
						alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">Java part Manager
								</div>
								<div class="project-name">Kim Kyung-won</div>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/3.png" class="portfolio-box">
						<img src="${url}/img/portfolio/thumbnails/3.png"  width="100%"  
						class="img-responsive" alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">Design Manager</div>
								<div class="project-name">Moon Jung-ha</div>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/4.jpg" class="portfolio-box">
						<img src="${url}/img/portfolio/thumbnails/4.jpg"  width="100%"   
						class="img-responsive" alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">UI Manager</div>
								<div class="project-name">Kim Min-su</div>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/5.jpg" class="portfolio-box">
						<img src="${url}/img/portfolio/thumbnails/5.jpg"  width="100%"
						class="img-responsive" alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">UI Manager</div>
								<div class="project-name">Park Hyo-seung</div>
							</div>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-sm-6">
					<a href="${url}/img/portfolio/fullsize/6.jpg" class="portfolio-box">
						<img src="${url}/img/portfolio/thumbnails/6.jpg"  width="100%"  
						class="img-responsive" alt="">
						<div class="portfolio-box-caption">
							<div class="portfolio-box-caption-content">
								<div class="project-category text-faded">Team ZUPLAY</div>
								<div class="project-name"></div>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</section>

	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 text-center">
					<h2 class="section-heading">Contact Us</h2>
					<hr class="primary">
					<p>문의사항은 아래 방법으로 연락 부탁드립니다.</p>
				</div>
				<div class="col-lg-4 col-lg-offset-2 text-center">
					<i class="fa fa-phone fa-3x sr-contact"></i>
					<p>010-2073-3908</p>
				</div>
				<div class="col-lg-4 text-center">
					<i class="fa fa-envelope-o fa-3x sr-contact"></i>
					<p>
						<a href="mailto:your-email@your-domain.com">zuplay.naver.com</a>
					</p>
				</div>
			</div>
		</div>
	</section>

	<!-- jQuery -->
	<script src="${url}/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${url}/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="${url}/vendor/scrollreveal/scrollreveal.min.js"></script>
	<script src="${url}/vendor/magnific-popup/jquery.magnific-popup.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="${url}/js/creative.js"></script>
	<script src="resources/js/jquery-2.2.4.min.js"></script>
	<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>

	<!-- 네이버아디디로로그인 초기화 Script -->
	<script type="text/javascript">
		$(function() {
			$("body").attr("style", "height:" + (screen.height - 100) + "px");
		})

		var naver_id_login = new naver_id_login("MEu9lHVoIBXQU0fULcr6",
				"http://127.0.0.1:8000/zuplay/LoginInfo");
		var state = naver_id_login.getUniqState(); 
		naver_id_login.setButton("white", 3, 40);
		naver_id_login.setDomain(".service.com");
		naver_id_login.setState(state);
		//naver_id_login.setPopup();
		naver_id_login.init_naver_id_login();
	</script>

</body>

</html>
