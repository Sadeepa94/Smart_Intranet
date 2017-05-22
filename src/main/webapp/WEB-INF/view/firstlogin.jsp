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
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link href="<c:url value="/resources/css/customcss.css" />" rel="stylesheet" />
		<link href="<c:url value="/resources/css/form.css" />" rel="stylesheet" />
		<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
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
<body >
	
	
	<jsp:include page="./nav/topnav.jsp"/>
	



	<div class="container-fluid">
		<div class="row content" >
		
		
			
			<jsp:include page="./nav/sidenav.jsp"/>










			<div class="col-sm-10 text-left">
			</div>
			
			
		</div>	
	</div>


<div class="modal  fade in" id="myModal" role="dialog" Style="pasition:absolute;top:100px;display:block">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
         <div style="display: block; padding: 10px">
			<img src="<c:url value="/resources/images/logo.png" />" width="120px" height="32px" style="display: block;" />
		</div>
        </div>
        <div class="modal-body">
        <form action="passwordchange" method="post">
        		<h4 style="color:#666666">Welcome to MI-Synergy</h4>
        		<p>This is your first login. Please change default password for further works.</p>
        		<br />
        		<input type="hidden" name="id" value="${userId}" />
        		<p style="color:red">${message}</p>
        		<table>
        		<tr>
        			<td align="right"><label>Username :</label></td> 
        			<td><label>${username}</label></td>
        			
        		</tr>
        		<tr>
        			<td align="right">
        				<label>New Password : </label>
        			</td>
        			<td>
    					<input name="password" id="password" type="password" placeholder=" New Password"/>
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
  
  <div class="modal-backdrop fade in"></div>

</body>
</html>



