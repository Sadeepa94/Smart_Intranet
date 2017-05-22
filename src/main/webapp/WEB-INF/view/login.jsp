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
						
						<div class="row">
							<div class="col-lg-12 col-xs-12" style="background-color:#195e86;padding:10px;color: white;">
								<h3>Login</h3>
							</div>
						</div>
						
						
						<div class="row" >
							<div  class="col-lg-12 col-xs-12"  style="background-color:#999999; ">
							
								<div class="row">
									<div class="col-lg-6 col-xs-6"  style="padding:25px;border-right:1px solid white;margin-top:10px;margin-bottom:10px;">
										<form action="<c:url value="/login" />" method="post">
									
		
									<table>
										<tr>
											<td align="right" ><label>Username </label></td>
											<td class="col-lg-6 col-xs-12"> <input type="text" name="username" /></td>
										</tr>
										<tr>
											<td align="right"><label style="padding-right:5px">Password </label></td>
											<td class="col-lg-6 col-xs-12"> <input type="password" name="password" /></td>
										</tr>
			
										<tr>
											<td><b style="color:red">${error}</b></td>
											
											<td align="right"><input type="submit" value="Login"
												class="btnA" style="align: right;" /></td>
										</tr>
			
									</table>
								</form>
									</div>
									<div class="col-lg-6 col-xs-6" style="padding-top:40px;">
										<label>Please login using username and password</label><br />
										<label><a href="forgetpassword/page">Forget password</a></label>
									</div>
								</div>
							</div>
						</div>
						
						</div>
					
			
				</div>
				
				</div>


			</div>
	


</body>
</html>
