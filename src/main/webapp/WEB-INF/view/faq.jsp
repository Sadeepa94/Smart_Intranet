<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Help Desk</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link rel='shortcut icon' type='image/x-icon' href="<c:url value="/resources/images/favicon.ico" />" />
<link href="<c:url value="/resources/css/customcss.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
<script src="<c:url value="/resources/js/stomp.js" />"></script>
<script src="<c:url value="/resources/js/ajax.js" />"></script>
<script src="<c:url value="/resources/js/websocket.js" />"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
	function onLoadfunction() {
		connect();
		

	}
</script>





</head>
<body onload="onLoadfunction()">



	<jsp:include page="./nav/topnav.jsp" />




	<div class="container-fluid">
		<div class="row content">


			<jsp:include page="./nav/sidenav.jsp" />


			<div class="col-sm-10 text-left">

				<div style="padding: 20px">
					<h4>Frequently Asked Question</h4>
					<br />
					<ul>
						<c:forEach items="${faqlist}" var="faq">
							<li>
								<div>
									<h5>
										<b>${faq.questionDescription}</b>
									</h5>
								</div>

								<div style="padding-left: 50px">
									<p>${faq.answerDescription}
									<p />
								</div> <br />
							</li>
						</c:forEach>
					</ul>


				</div>

			</div>


		</div>

	</div>
</body>
</html>
