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
		
		<script>
			$(document).ready(function(){
    			$('#confirm_password').on('keyup', function () {
    			if ($('#password').val() == $('#confirm_password').val()) {
        			$('#message').html('Matching').css('color', 'green');
    			} else 
        			$('#message').html('Not Matching').css('color', 'red');
				});
			});
		</script>
		
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
						
				<h4 style="color:#666666">Welcome to MI-Synergy</h4>
        		<p>Please enter your new password</p>		
				<form action="<c:url value="/forgetpassword/resetpassword" />" method="post">
        		
        		<br />
        		<input name="username"  type="hidden" value="${username}"/>
        		
        		<table>
        		<tr>
        			<td align="right"><label>Username :</label></td> 
        			<td><label>${username}</label></td>
        			
        			
        		</tr>
        		<tr>
        			<td align="right"><label>Email :</label></td> 
        			<td><input name="email" id="email" type="text" placeholder=" Email"/></td>
        			
        			
        		</tr>
        		
        		<tr>
        			<td align="right">
        				<label>New Password : </label>
        			</td>
        			<td>
    					<input name="newpassword" id="password" type="password" placeholder=" New Password"/>
    				</td>
    			</tr>
				<tr>
					<td align="right">
						<label>Confirm Password : </label>
					</td>
    				<td>
    					<input type="password"  id="confirm_password" placeholder=" confirm Password"/> <b><span id='message'></span></b>
    				</td>
    			</tr>
    			<tr>
					<td >
						<p style="color:red">${message}</p>
					</td>
    				<td align="center">
    					<input type="submit" class="btnA" value="submit">
    				</td>
    			</tr>
    			</table>
    			
    	</form>
												
						</div>
					
			
				</div>
				
				</div>


			</div>
	


</body>
</html>
