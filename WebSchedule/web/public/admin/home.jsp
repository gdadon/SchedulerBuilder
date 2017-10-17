<!DOCTYPE html>
<html>

<head>
	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

	<title>Schedule | Home</title>

	<link rel='stylesheet' type='text/css' href='../../css/style.css' />
	<!--[if IE]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	<script type="text/javascript" src="../../js/submitBtn.js"></script>
</head>

<body>
<div id="page-wrap">
	<header>
        <jsp:include page="header.jsp"></jsp:include>
	</header>

	<section id="main-content">
		<div id="guts">
			<h2>Home</h2>
            <p id="homeBase">There are ${pendingDemands} pending requests for your approval</p>
		</div>
	</section>
</div>
</body>

</html>