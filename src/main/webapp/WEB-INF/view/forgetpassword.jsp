<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Help Desk</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=0.5">


		<link rel='shortcut icon' type='image/x-icon' href="<c:url value="/resources/images/favicon.ico" />" />
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/customcss.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		
</head>
<body style="min-width:970px">
	<div class="container-fluid">
	
		<div class="row">
			<div class="col-lg-12 col-xs-12" style="display: block; padding: 10px;">
				<img src="<c:url value="/resources/images/logo.png" />" width="120px" height="32px" style="display: block;" />
			</div>
		</div>


		<div class="row">
			
			<div class="col-lg-12 col-xs-12">
			
						<div class="row" style="margin:10%;margin-left:15%;margin-right:15%">
						
						<p>To reset your password, submit your username below. If we can find you in the database, 
						an email will be sent to your email address, with instructions how to get access again.<p>
						
						
						<form action="reset" method="get">
						<input type="text" name="username">
						<input type="submit" value="reset" class="btnsubmit">
						<p style="color:red;">${message}</p>
						
						
						</form>
						
						</div>
					
			
				</div>
				
				</div>


			</div>
	


</body>
</html>
