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
		<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
		<script src="<c:url value="/resources/js/stomp.js" />"></script>
		<script src="<c:url value="/resources/js/paginate.js" />"></script>
		<script src="<c:url value="/resources/js/maintenance.js" />"></script>
		<script src="<c:url value="/resources/js/load.js" />"></script>
		<script src="<c:url value="/resources/js/tinymce/js/tinymce/tinymce.min.js" />"></script>
		
		
		
    	<script src="<c:url value="/resources/js/websocket.js" />"></script>
     	
		<script type="text/javascript">
		var contexturl = "<c:url value="/files/"/>";
			function onLoadfunction()
			{
				connect();
				loadEmployee(0);
				

			}

      	</script>
</head>
<body onload="onLoadfunction()">

	
	

	<jsp:include page="./nav/topnav.jsp" />



	<div class="container-fluid">
		<div class="row content">


			<jsp:include page="./nav/sidenav.jsp" />
	










			<div class="col-sm-10 text-left ccc">
			
			<div class="col-sm-12" id="" style="display:block;">
						<span ><button class="dashboardbtn" id="employeebtn">Employees</button></span>
						<span ><button class="dashboardbtn" id="clientbtn">Clients</button></span>
						<span ><button class="dashboardbtn" id="projectbtn">Projects</button></span>
						
						
						
			</div>
			
			
			
			<div id="employee" style="display:block" >
					<div id="view_all_emp" style="padding:25px;width:95%;" >
					<h4><b>Employees details</b></h4>
					
					
					<div style="display:inline;float:right;">
						<input type="text" placeholder=" Username" id="empserch" />
						<button class="btnA" id="empserchbtn"><span class="glyphicon glyphicon-search"></span></button>
					
					</div>
					
					 <table class="table table-bordered table-condensed etable" style="margin-top:20px" >
						<thead>
							<tr>
								<th>#</th>
								<th>User name</th>
								<th>First name</th>
								<th>Role</th>
								<th>Contact-No</th>
								<th>Email</th>
								
								<th></th>
							</tr>
						</thead>
						<tbody id="employeeTable">
						</tbody>
					</table>
					<div id="emppage"></div>
					</div>
					</div>
					
					
					
					
					
					<div id="client" style="display:none">
					
						<div id="view_all_client" style="padding:25px;width:95%;" >
						<div style="display:inline;float:right;">
							
								<input type="text" placeholder=" Username" id="clientserch"/>
							<button class="btnA" id="clientserchbtn"><span class="glyphicon glyphicon-search"></span></button>
						
						</div>
							<h4><b>Client Details</b></h4>
							
					
							
					
					 		<table class="table table-bordered table-condensed " style="margin-top:20px">
								<thead>
									<tr>
										<th>#</th>
										<th>User Name</th>
										<th>Client Name</th>
										<th>Name</th>
										<th>Contact-No</th>
										<th>Email</th>
										
										<th></th>
									</tr>
								</thead>
								<tbody id="clientTable">
						
									
							
							</tbody>
						</table>
						
						<div id="clientpage"></div>
						
					</div>
					</div>
					
					
					<div id="project"  style="display:none">
					  	
					<div id="view_all_project" style="padding:25px;width:95%;">
											<div style="display:inline;float:right;">
					
						<input type="text" placeholder=" client username" id="projectSearch"/>
						<button class="btnA" id="projectSearchbtn"><span class="glyphicon glyphicon-search"></span></button>
					
					</div>
					
						<h4><b>Projects</b></h4>
						
					
							
					 		<table class="table table-bordered table-condensed " style="margin-top:20px">
								<thead>
									<tr>
										<th>#</th>
										<th>Project ID</th>
										<th>Project Name</th>
										<th>User Name</th>
										<th>Client Name</th>
										
										<th></th>
									</tr>
								</thead>
								<tbody id="projecttable">
						
									
							
							</tbody>
							
						</table>
					<div id="projectpage"></div>
					</div>
					</div>
			
					
	
	
	
	</div>
	</div>
	</div>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog" >
      <div class="modal-content"  style="border-radius: 0px !important;">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Details of Employee</h4>
        </div>
        <div class="modal-body">
         <table id="viewEmployee">
       
         	
         </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
  
  
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog" >
      <div class="modal-content"  style="border-radius: 0px !important;">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Details of Employee</h4>
        </div>
        <div class="modal-body">
         <table id="viewclient">
         	
         	
         	
         	
         </table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
	
 <div class="modal fade" id="projectModal" role="dialog">
    <div class="modal-dialog modal-lg" >
      <div class="modal-content"  style="border-radius: 0px !important;">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Details of Project</h4>
        </div>
        <div class="modal-body">
        
       <div class="modal-body row">
  			<div class="col-md-6">
    			<table id="viewproject">
    			</table>
  			</div>
  			<div class="col-md-6" id="viewmodule">
    			<!-- Your second column here -->
  			</div>
		</div>
         
         
         
         
         
        </div>
        
        
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>


</body>
</html>
