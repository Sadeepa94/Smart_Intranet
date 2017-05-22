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
		<script src="<c:url value="/resources/js/maintenance.js" />"></script>
		<script src="<c:url value="/resources/js/ajax.js" />"></script>
		<script src="<c:url value="/resources/js/load.js" />"></script>
		<script src="<c:url value="/resources/js/paginate.js" />"></script>
		<script src="<c:url value="/resources/js/stomp.js" />"></script>
		<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
		<script src="<c:url value="/resources/js/module.js" />"></script>
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
		<div class="row content"  >
		
		
			<jsp:include page="./nav/sidenav.jsp" />











			<div class="col-sm-10 ">
			
			
			
			
				<div id="employee" style="display:block" >
					<div id="view_all_emp" style="padding:25px;width:95%;" >
					<h4><b>Employees details</b></h4>
					<button class="btnN" id="newempbtn"><span class="glyphicon glyphicon-plus"></span> <b>Create New</b></button>
					
					<div style="display:inline;float:right;">
					
						<input type="text" placeholder=" Username" id="empserch" />
						<button class="btnA" id="empserchbtn"><span class="glyphicon glyphicon-search"></span></button>
					
					</div>
					
					 <table class="table table-bordered table-condensed " style="margin-top:20px" >
						<thead>
							<tr>
								<th>#</th>
								<th>User name</th>
								<th>First name</th>
								<th>Role</th>
								<th>Contact-No</th>
								<th>Email</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody id="employeeTable">
						</tbody>
					</table>
					<div id="emppage"></div>
					</div>
					
					
					
					<div id="emp_form" class="form" style="display:none">
					
						<h4><b>Employee Form</b></h4>
						<span class="closebtn" id="empclsbtn">&times;</span> 
						<br />
						
						
						<form action="newEmployee" method=post class="employeeform" id="employeeform">
						
							<table>
								
								<tr>
									<td ><label>Employee ID</label><br />
										<input type="text" name="username" size="20" class="forminput" id="eusername" readonly/><br />
										<p id="userusername" class="error"></p> 
									</td>
									<td ><label>Enable</label><br />
										<input type="radio" name="enable" value="true" /> enable
										<input type="radio" name="enable" value="false" /> disable
									</td>
									
								</tr>
								
								<tr>
									
									<td ><label>Employee Name</label><br />
										<input type="text" name="first_name"  size="25" placeholder=" first" class="forminput" id="efirstname"/>
										<br /><p id="userfirst_name" class="error"></p>
									</td>
									<td valign="bottom">
										<input type="text" name="last_name"  size="25"  placeholder=" last" class="forminput" id="elastname"/>
										<br /><p id="userlast_name" class="error"></p>
									</td>
								</tr>
			
								
			
								<tr>
									<td><label>Role</label><br />
										<select name="role.roleID" form="employeeform" id="selectrole" >
										 	
										</select> 
									</td>
									
									<td><label>Type</label><br />
										<select name="type.typeID" form="employeeform" id="selecttype" >
										 	
										</select> 
										
									</td>
								</tr>
			
								
			
			
								<tr>
									<td >
										<label>E-mail</label><br />
										<input type="text" name="email" size="40" class="forminput" id="eemail"/>
										<br /><p id="useremail" class="error"></p>
									</td>
									<td><label>Joined Date</label><br />
										<input type="date" name="joinedDate" class="forminput" id="ejoineddate"/>
										
										 
									</td>
								</tr>
			
								
			
								<tr>
									<td valign="top" >
										<label>Address</label><br />
										<input type="text" name="address.no" size="5" placeholder="No" class="forminput" id="eaddressno"/><br />
										<input type="text" name="address.street" size="30" placeholder="Street" class="forminput" id="eaddressstreet"/><br />
										<input type="text" name="address.town" size="30" placeholder="Town" class="forminput" id="eaddresstown"/><br />
										<input type="text" name="address.district" size="30" placeholder="District" class="forminput" id="eaddressdistrict"/>
										<br /><p id="useraddress" class="error"></p>
									</td>	
									<td valign="top">
										<label>Contact-No</label><br />
										<input type="text" name="contact_no" size="15"  class="forminput" id="econtact_no"/>
										<br /><p id="usercontact_no" class="error"></p>
									</td>
										
									
								
									
								</tr>
			
								<tr>
									<td><input type="submit" value="Save" class="btnA" id="saveEmp"/>
									</td>
								</tr>
			
							
							</table>
						
						</form>
					
					</div>
					
					
	
				
				</div>
				
				
				
				
				
				
				
				
				
				
				
					<div id="client" style="display:none">
					
						<div id="view_all_client" style="padding:25px;width:95%;" >
							<h4><b>Client Details</b></h4>
							<button class="btnN" id="newclientbtn"><span class="glyphicon glyphicon-plus"></span> <b>Create New</b></button>
					
							<div style="display:inline;float:right;">
							
								<input type="text" placeholder=" Username" id="clientserch"/>
							<button class="btnA" id="clientserchbtn"><span class="glyphicon glyphicon-search"></span></button>
						
						</div>
					
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
										<th></th>
									</tr>
								</thead>
								<tbody id="clientTable">
						
									
							
							</tbody>
						</table>
						
						<div id="clientpage"></div>
						
					</div>
				
					
					<div id="client_form" class="form" style="display:none">
					
						<h4><b>Client Form</b></h4>
						
						<span class="closebtn" id="clientclsbtn">&times;</span> 
						<br />
						<form class="clientform" id="clientform">
						
							<table>
								
								<tr>
									<td ><label>Client ID</label><br />
									<input type="text" name="username" size="10" class="forminput" id="cusername" readonly/>  
									</td>
									<td ><label>Enable</label><br />
										<input type="radio" name="enable" value="true" /> enable
										<input type="radio" name="enable" value="false" /> disable
									</td>
								</tr>
								<tr>
									<td><label>Client name</label><br />
										<input type="text" name="companyName"  size="30" placeholder=" company name" class="forminput" id="ccompanyname"/>
									</td>
									
									<td><label>Responsible person name</label><br />
										<input type="text" name="responsible_person"  size="30" placeholder=" person name" class="forminput" id="cpname"/>
									</td>
								</tr>
								<tr>
									<td >
										<label>E-mail</label><br />
										<input type="text" name="email" size="40" class="forminput" id="cemail" />
										<br /><p id="clientemail" class="error "></p>
									</td>
								</tr>
								
								<tr>
									<td valign="top" >
										<label>Address</label><br />
										<input type="text" name="address.no" size="5" placeholder="No" class="forminput" id="caddressno"/><br />
										<input type="text" name="address.street" size="30" placeholder="Street" class="forminput" id="caddressstreet"/><br />
										<input type="text" name="address.town" size="30" placeholder="Town" class="forminput" id="caddresstown"/><br />
										<input type="text" name="address.district" size="30" placeholder="District" class="forminput" id="caddressdistrict"/>
									</td>	
									<td valign="top">
										<label>Contact-No</label><br />
										<input type="text" name="contact_no" size="10" class="forminput" id="ccontactno"/>
										<br /><p id="clientcontact_no" class="error"></p>
									</td>
								</tr>
			
								<tr>
									<td><input type="submit" value="Save" class="btnA" /></td>
									
								</tr>
			
							
							</table>
						
						</form>
					</div>
					
					
				
				</div>
				
				
				
				
				
				<div id="project"  style="display:none">
					  	
					<div id="view_all_project" style="padding:25px;width:95%;">
						<h4><b>Projects</b></h4>
						<button class="btnN" id="newprojectbtn"><span class="glyphicon glyphicon-plus"></span> <b>Create New</b></button>
						
						<div style="display:inline;float:right;">
					
						<input type="text" placeholder=" client username" id="projectSearch"/>
						<button class="btnA" id="projectSearchbtn"><span class="glyphicon glyphicon-search"></span></button>
					
					</div>
							
					 		<table class="table table-bordered table-condensed " style="margin-top:20px">
								<thead>
									<tr>
										<th>#</th>
										<th>Project ID</th>
										<th>Project Name</th>
										<th>User Name</th>
										<th>Client Name</th>
										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody id="projecttable">
						
									
							
							</tbody>
							
						</table>
					<div id="projectpage"></div>
					</div>
					
					
					<div id="project_form" class="form col-sm-12" style="display:none;width:95%">
					
					<h4><b>Project Form</b></h4>
						
						<span class="closebtn" id="projectclsbtn">&times;</span> 
						<br />
					
						<form id="projectform" method="post" class="projectform">
						
						<div class="col-sm-6">
							<table>
								
								<tr>
									<td ><label>Project ID</label><br />
									<input type="text" name="projectID" size="10" id="projectID" readonly />  
								</tr>
								<tr>
									<td><label>Project name</label><br />
										<input type="text" name="projectName"  size="30" placeholder=" company name"/>
									</td>
									
									<td><label>Client username</label><br />
										<input type="text" name="client.username"  size="30" placeholder=" person name"/>
									</td>
								</tr>
								<tr>
									<td >
										<label>Delivery date</label><br />
										<input type="date" name="deliveryDate" size="40"/>
									</td>
								</tr>
								<tr >
									<td colspan="2"><label>Description</label><br />
										<textarea rows="4" cols="70" name="description" form="projectform" placeholder="Enter text here..."></textarea>
									</td>
								</tr>
								<tr>
									<td><br /><input type="submit" value="Save" class="btnA" />
									</td>
								</tr>
							</table>
							
							</div>
							
							<div class="col-sm-6">
							
								
								<br/>
								<div id=modules>
								<label>Modules</label>
								
								<div class="input_fields_wrap" id="input_fields_wrap">
								</div>
								</div>
									
									<button class="add_field_button btnB">Add</button>
							</div>
						
						</form>
					
					</div>
					
				
				</div>
				
				
				
				
				<div id="roles_and_feature" style="display:none;" >
					<div id="view_all_role_features" class="col-sm-12" style="padding:25px;width:95%;">
					
						<div class="col-sm-12">
							<h4><b>Role Details</b></h4>
							<button class="btnN" id="newrolebtn"><span class="glyphicon glyphicon-plus"></span> <b>New Role</b></button>
					 		<table class="table table-bordered table-condensed " style="margin-top:20px" >
								<thead>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>Description</th>
										<th>Features</th>
										<th>Edit</th>
										
									</tr>
								</thead>
								<tbody id="roleTable">
						
									
							
							</tbody>
						</table>
					</div>
						
					</div>	
						
					
					<div class="form col-sm-8" id="role_form" style="display:none;">
						<h4><b>Role Form</b></h4>
						
						<span class="closebtn" id="roleclsbtn">&times;</span> 
						<br />
						<form id="roleform" class="roleform">
							<label>Role Name </label><br />
							
							<select name="roleName" form="roleform" id="slectfeatures" >								
								<option value="gateway controller">Gateway Controller</option>
								<option value="tech supporter">Tech Supporter</option>
								<option value="system admin">System Admin</option>
								<option value="normal employee">Normal Employee</option>
								<option value="leave supervisor">Leave Supervisor</option>
								<option value="admin">Admin</option>
								<option value="client">client</option>
								
							</select> 
							<br />
							<label>Features </label><br />
							<select name="features" form="roleform" id="slectfeatures" multiple>								
								<option value="ask question">Ask question</option>
								<option value="question">Question</option>
								<option value="maintenance">Maintenance</option>
								<option value="dashboards">Dash boards</option>
								<option value="details">Details</option>
								
							</select> 
							<br />
							<label>Description</label><br />
							<textarea rows="3" cols="80" name="roleDescription" form="roleform" id="roleDescription"></textarea>
							<br />
							<input type="submit" value="Save" class="btnA" />
							
						</form>
					</div>
						
				
					
					</div>
					
					
					<div id="leave_type" style="display:none;" >
					<div id="view_all_leave_type" class="col-sm-12" style="padding:25px;width:95%;">
					
						<div class="col-sm-12">
							<h4><b>EmployeeType Details</b></h4>
							<button class="btnN" id="newtypebtn"><span class="glyphicon glyphicon-plus"></span> <b>New Type</b></button>
					 		<table class="table table-bordered table-condensed " style="margin-top:20px" >
								<thead>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>No of AL</th>
										<th>No of CL</th>
										<th>No of SickL</th>
										<th>No of SL</th>
										<th>Edit</th>
										
									</tr>
								</thead>
								<tbody id="typeTable">
						
									
							
							</tbody>
						</table>
					</div>
						
					</div>	
						
					
					<div class="form col-sm-8" id="type_form" style="display:none;">
						<h4><b>Employee Type Form</b></h4>
						
						<span class="closebtn" id="typeclsbtn">&times;</span> 
						<br />
						<form id="typeform" class="typeform">
							<label>type Name </label><br />
							<select name="typeName" form="typeform" id="selecttypename">								
								<option value="PERMANENT">Permanent</option>
								<option value="PROBATION">Probation</option>
								<option value="TEMPORARY">Temporary</option>
								<option value="CONTRACT">Contract</option>
								<option value="OTHER">Other</option>
								
							</select> 
							
							
							<table style="width:50%">
							<tr>
							<td><label>Max ShortLeaves </label></td>
							<td align="left"><input type="text" name="maxShortLeaves" id="maxShortLeaves" size="3"/></td>
							</tr>
							<tr>
							<td><label>Max AnnualLeaves </label></td>
							<td align="left"><input type="text" name="maxAnnualLeaves" id="maxAnnualLeaves" size="3"/></td>
							</tr>
							<tr>
							<td><label>Max CasualLeaves </label></td>
							<td align="left"><input type="text" name="maxCasualLeaves"  id="maxCasualLeaves" size="3"/></td>
							</tr>
							<tr>
							<td><label>Max SickLeaves </label></td>
							<td align="left"> <input type="text" name="maxSickLeaves" id="maxSickLeaves"  size="3"/></td>
							</tr>
							</table>
							<br />
							<label>Description</label><br />
							<textarea rows="3" cols="80" name="description" form="typeform" id="typeDescription"></textarea>
							<br />
							<input type="submit" value="Save" class="btnA" />
							
						</form>
					</div>
						
						
						
						
					
					</div>
					
					
					
				
					<div id="FAQ_" style="display:none" >
						
						<div id="view_all_faq" style="padding:25px;width:95%;" >
							<h4><b>FAQ</b></h4>
							<button class="btnN" id="newfaqbtn"><span class="glyphicon glyphicon-plus"></span> <b>Create New</b></button>
					
							
					
					 		<table class="table table-bordered table-condensed " style="margin-top:20px">
								<thead>
									<tr>
										<th>#</th>
										<th>Question</th>
										<th></th>
										<th></th>
										
									</tr>
								</thead>
								<tbody id="faqTable">
						
									
							
							</tbody>
						</table>
						
						
					</div>
				
					
					<div id="faq_form" class="form" style="display:none" >
					
						<h4><b>Create new FAQ</b></h4>
						
						<span class="closebtn" id="faqclsbtn">&times;</span> 
						<br />
						<form id="faqform" class="faqform" >
							<input type="hidden" name=faqID id="faqID" value="0" />
							<table>
								
								
								<tr >
									<td colspan="2"><label>Question</label><br />
										<textarea rows="4" cols="70" id="faqquestionDescription" name="questionDescription" form="faqform" placeholder="Enter text here..." style="width:100%;"></textarea>
									</td>
								</tr>
								<tr >
									<td colspan="2"><label>Answer</label><br />
										<textarea rows="4" cols="70" id="faqanswerDescription" name="answerDescription" form="faqform" placeholder="Enter text here..." style="width:100%;"></textarea>
									</td>
								</tr>
								
			
								<tr>
									<td><input type="submit" value="Save" class="btnA" /></td>
									
								</tr>
			
							
							</table>
						
						</form>
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



