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
<link href="<c:url value="/resources/css/clockstyle.css" />"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
<script src="<c:url value="/resources/js/stomp.js" />"></script>
<script src="<c:url value="/resources/js/ajax.js" />"></script>
<script src="<c:url value="/resources/js/access.js" />"></script>
<script src="<c:url value="/resources/js/websocket.js" />"></script>
<script src="<c:url value="/resources/js/moment.js" />"></script>
<script src="<c:url value="/resources/js/clockscript.js" />"></script>

<script type="text/javascript">
	var contexturl = "<c:url value="/files/"/>";

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
				<div class="col-sm-8 profile">
					<div class="col-sm-12">
						<label>Your Profile</label>
					</div>
					<div class="col-sm-4">
						<span class=" profile-logo "><img id="profilepic"
							src="<c:url value="/files/${user.profilepicture}" />" /></span><br />
						<button data-toggle="modal" data-target="#imageModal" class="btnsubmit">update</button>
					</div>



					<div class="col-sm-8">
						<h5 id="editprofile">
							<b>User name: </b>${user.username}<button style="display: none;"
								id="editprofilebtn" data-toggle="modal"
								data-target="#editprofilemodal" class="btnsubmit">Edit</button>
						</h5>
						<c:if test="${user.role.roleName=='client'}">
							<h5>
								<b>Name: </b>${user.companyName}</h5>
						</c:if>

						<c:if test="${user.role.roleName!='client'}">
							<h5>
								<b>Name: </b>${user.first_name } ${user.last_name}
							</h5>
						</c:if>
						<h5>
							<b>Email: </b>${user.email}</h5>
						<h5>
							<b>Contact No: </b>${user.contact_no}</h5>
						<h5>
							<b>Address: </b>${user.address.no}, ${user.address.street}, ${user.address.town}, ${user.address.district}.</h5>

						<button data-toggle="modal" data-target="#changepasswordmodal" class="btnsubmit">change
							password</button>



					</div>

					<br />

					<div class="col-sm-8">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Status</th>
									<th>No of question</th>

								</tr>
							</thead>
							<tbody>
								<tr>
									<td>New</td>
									<td>${newq}</td>

								</tr>
								<tr>
									<td>Inprogress</td>
									<td>${inprogress}</td>

								</tr>
								<tr>
									<td>Close</td>
									<td>${close}</td>

								</tr>
								<tr>
									<td>Further Imp.</td>
									<td>${furtherimplements}</td>

								</tr>

							</tbody>
						</table>

					</div>



				</div>

				<div class="col-sm-4 ">

					<!--  
				<div id="clock-container" style="background: white; width: 340px;">
					<div id="clock" class="light">
						<div class="display">
							<div class="weekdays"></div>
							<div class="ampm"></div>
							<div class="digits"></div>
						</div>
					</div>
				</div>
				-->

				</div>
			</div>


		</div>
	</div>

	<div class="modal fade" id="imageModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="border-radius: 0px !important;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Profile picture</h4>
				</div>
				<div class="modal-body">

					<form id="imageupload">

						<img id="blah" src="#" alt="your image" width="auto"
							height="100px" /> <input type='file' id="imgInp" name="file"
							style="border: 0px" /> <input type="hidden" name="id"
							value="${ sessionScope.userId}"> <input type="submit"
							value="update" />
					</form>


				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="changepasswordmodal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="border-radius: 0px !important;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Change your password</h4>
				</div>
				<div class="modal-body">

					<form id="changepasswordform">

						<input type="hidden" name="id" value="${ sessionScope.userId}">
						<table>
							<tr>
								<td align="right"><label>Username :</label></td>
								<td><label>${sessionScope.username}</label></td>

							</tr>
							<tr>
								<td align="right"><label>Current Password : </label></td>
								<td><input name="currentpassword" type="password"
									placeholder=" current Password" /></td>
							</tr>
							<tr>
								<td align="right"><label>New Password : </label></td>
								<td><input name="newpassword" id="password" type="password"
									placeholder=" New Password" /></td>
							</tr>
							<tr>
								<td align="right"><label>Confirm Password : </label></td>
								<td><input type="password" id="confirm_password"
									placeholder=" confirm Password" /> <b><span id='message'></span></b>
								</td>
							</tr>
							<tr>
								<td></td>
								<td align="center"><input type="submit" class="btnA"
									value="submit"></td>
							</tr>
						</table>

						
					</form>


				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="editprofilemodal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content" style="border-radius: 0px !important;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Update Your profile</h4>
				</div>
				<div class="modal-body">
				 <div class="modal-body row">
				 <div class="col-sm-8">

					<form id="editprofileform">



						<input type="hidden" name="id" value="${ sessionScope.userId}">
						<input type="hidden" name="username" value="${ sessionScope.username}">
						
						<table>
							<tr>
								<td align="right" valign="top"><label>Contact No : </label>
								
								</td>
								
								<td><input type="text" name="contact_no" value="${user.contact_no}"><br />
								<p id="usercontact_no" class="error"></p></td>
							</tr>
							<tr>
								<td align="right" valign="top"><label>Email : </label></td>
								<td><input type="text" name="email" value="${user.email }" size="30"><br />
								<p id="useremail" class="error"></p></td>
							</tr>

							<tr>
								<td align="right" valign="top"><label>Address : </label></td>
								<td><input type="text" name="address.no" size="5"
									placeholder="No" value="${user.address.no}"/><br /> <input type="text"
									name="address.street" size="30" placeholder="Street" value="${user.address.street}"/><br />
									<input type="text" name="address.town" size="30"
									placeholder="Town" value="${user.address.town}"/><br /> <input type="text"
									name="address.district" size="30" placeholder="District" value="${user.address.district}"/></td>
							</tr>
							<tr>
								<td></td>
								<td align="right"><input type="submit" class="btnA"
									value="submit"></td>
							</tr>
							
						</table>
						
					</form>
					</div>

				</div>
				
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$("#editprofile").mouseover(function() {
				$("#editprofilebtn").show();

			})
			$("#editprofile").mouseout(function() {
				$("#editprofilebtn").hide();

			})

			$('#confirm_password').on('keyup', function() {
				if ($('#password').val() == $('#confirm_password').val()) {
					$('#message').html('Matching').css('color', 'green');
				} else
					$('#message').html('Not Matching').css('color', 'red');
			});

			

		});
	</script>




</body>
</html>
