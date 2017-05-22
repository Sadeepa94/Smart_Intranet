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
<script src="<c:url value="/resources/js/dashboard.js" />"></script>




<script src="<c:url value="/resources/js/test.js" />"></script>
<script src="<c:url value="/resources/js/Chart.js" />"></script>
<script
	src="<c:url value="/resources/js/tinymce/js/tinymce/tinymce.min.js" />"></script>


<script src="<c:url value="/resources/js/websocket.js" />"></script>

<script type="text/javascript">
var contexturl="<c:url value="/files/"/>";
	function onLoadfunction() {
		
		connect();
		getovarview();
		
		

	}
</script>





</head>
<body onload="onLoadfunction()">




	<jsp:include page="./nav/topnav.jsp" />



	<div class="container-fluid">
		<div class="row content">


			<jsp:include page="./nav/sidenav.jsp" />











			<div class="col-sm-10">
				<div class="col-sm-12">
					<div class="col-sm-12" id="" style="display:block;">
						<span ><button class="dashboardbtn" id="overviewbtn">Overview</button></span>
						<span ><button class="dashboardbtn" id="tsperformancebtn">TS Performance</button></span>
						<span ><button class="dashboardbtn" id="statusWisebtn">StatusWise</button></span>
						<span ><button class="dashboardbtn" id="categoriesWisebtn">CategoriesWise</button></span>
						
						
					</div>
					
					<div class="col-sm-12" id="overviewdiv" style="display:block;">
							
							<canvas id="overviewcanvas" width="80" height="40"></canvas>
							
	
					</div>
					
					
					<div class="col-sm-12" id="tsperformancediv" style="display:none;">
							
							<div class="col-sm-8 tsview ">
								<div class="col-sm-4">
				 <span class=" profile-logo "><img id="profilepic" src="<c:url value="/files/default.png" />"  /></span><br />
				
				 </div>
				  
				 
				 
                 <div class="col-sm-8"> 
                 	<h5><b>User name: </b><span id="tsusername"></span></h5>   
                 	<h5><b>Name: </b><span id="tsname"></span></h5>
                 	<h5><b>Total Questions: </b><span id="totalq"></span></h5>
                 	<h5><b>closed Question: </b><span id="closedq"></span></h5>
                 	<h5><b>In progress Question: </b><span id="inpq"></span></h5>
                 	<h5><b>Further Implementation Question: </b><span id="futq"></span></h5>
                 	<h5><b>rating: </b><span id="overallrating"></span></h5>
                 	
                 	
                 	
                 	                    
                        
             	</div>
							</div>
							
							<div class="col-sm-3 tclist" >
							
								<ul id="tsulist" style="list-style-type: none;margin-left:0px;padding:0px;">
								</ul>
								
							
							</div>
							
					</div>
					
					
					<div class="col-sm-12" id="statuswisediv" style="display:none;">
						<div>
						<label>Status wise view</label><br />
							<form class="statusdateform">
								<label>From</label>
								<input type="date" name="fromdate">
								<label>To</label>
								<input type="date" name="todate">
								<input type="submit" value="search">
							</form>
						</div>
							
							<div class="col-sm-8">
							<canvas id="statuschart" width="80" height="40"></canvas>
							</div>
	
					</div>
					
					
					<div class="col-sm-12" id="categorieswisediv" style="display:none;">
						<div>
							<label>Categories wise view</label><br />
							<form class="categoriesdateform">
								<label>From</label>
								<input type="date" name="fromdate">
								<label>To</label>
								<input type="date" name="todate">
								<input type="submit" value="search">
							</form>
						</div>
							
							<div class="col-sm-8">
							<canvas id="categorychart" width="80" height="40"></canvas>
							</div>
	
					</div>
					
					
					
					
					
				</div>
				
			</div>

		</div>
	</div>




</body>
</html>
