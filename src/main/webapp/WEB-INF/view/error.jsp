<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Help Desk</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

		<link rel='shortcut icon' type='image/x-icon' href="<c:url value="/resources/images/favicon.ico" />" />
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/customcss.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		
</head>
<body >

	<div style="display: block; padding: 10px">
		<img src="<c:url value="/resources/images/logo.png" />" width="120px" height="32px" style="display: block;" />
		
	</div>


	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-2" style="margin: 0; padding: 0;">
			</div>


			<div class="col-sm-8 text-left">
				<div
					style="margin-top: 20px; padding: 10px;">
					<h4>Something going wrong during processing your request, please check and try again later</h4>
					<h5>${error}</h5>
				</div>
				
			</div>
		



			<div class="col-sm-2">
			</div>


		</div>
		</div>
	


</body>
</html>
