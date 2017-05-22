<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<script src="<c:url value="/resources/js/loadquestion.js" />"></script>
<script src="<c:url value="/resources/js/paginate.js" />"></script>
<script
	src="<c:url value="/resources/js/tinymce/js/tinymce/tinymce.min.js" />"></script>

<script>
	tinymce
			.init({
				selector : 'textarea',
				height : 250,
				menubar : false,
				plugins : [ ' lists ',

				'insertdatetime media table contextmenu paste code' ],
				toolbar : 'undo redo | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent ',
				content_css : '//www.tinymce.com/css/codepen.min.css'
			});
</script>
<script src="<c:url value="/resources/js/websocket.js" />"></script>

<script type="text/javascript">
var contexturl = "<c:url value="/files/"/>";
	function onLoadfunction() {
		connect();
		loadQuestion(0);
		

	}
</script>





</head>
<body onload="onLoadfunction()">




	<jsp:include page="./nav/topnav.jsp" />



	<div class="container-fluid">
		<div class="row content">


			<jsp:include page="./nav/sidenav.jsp" />











			<div class="col-sm-10 text-left">
				<div id="view_all_questions">
				<div class="dropdown" style="margin:10px;">
    				<button class="btn btn-default dropdown-toggle" type="button" id="menu1notifications" data-toggle="dropdown"><span id="noofnotifications" class="quetionnotification"  style="margin-left:10px">${fn:length(notifications)}</span> Notifications</button>
    					<ul class="dropdown-menu" role="menu" aria-labelledby="menu1notifications" id="notificationlist">
    						
    					<c:forEach items="${notifications}" var="notification">
      						<li role="presentation" class="notificationclick" data-target="${notification.get(1)}"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/${notification.get(2)}">${notification.get(0)}</a></li>
      					</c:forEach>
      						
      						<li role="presentation" class="" id="viewallnotifictions"><a role="menuitem" tabindex="-1" href="#">view all</a></li>	
    					</ul>
  				</div>
					<div class="col-sm-10 text-left" id="view_all_questions" >
						<div style="padding: 25px; width: 95%;">




							<div class="col-sm-12 questiondivmain" style="margin-top: 20px"
								id="questionTable"></div>
							<div id="questionpage"></div>

						</div>




					</div>

					<div class="col-sm-2"style="margin=0px;padding:0px;">

						<br /> <br /> <br />
						<div id="question-filter-list" class="formq" >
							<table>
								<tr>
									<td>Filter by Status :</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" id="f1"
										value="new"> <label> New</label></td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" id="f2"
										value="inprogress"> <label> Inprogress</label></td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" id="f3"
										value="close"> <label>close</label></td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" id="f4"
										value="further implementation"> <label>
											Futher Implements</label></td>
								</tr>
								<tr>
									<td><br>Filter by Category:</td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" value="mobile"
										id="f5"> <label for="dfn" class="styled-label">
											Mobile</label></td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" value="web"
										id="f6"> <label for="dln" class="styled-label">
											Web</label></td>
								</tr>
								<tr>
									<td><input type="checkbox" name="pname" value="desktop"
										id="f7"> <label for="ad" class="styled-label">
											Desktop</label></td>
								</tr>
								<c:if test="${sessionScope.role=='tech supporter' }">
								<tr>
									<td><button id="myquestion" class="btnqsubmit">My
											Question</button></td>
								</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>

					<jsp:include page="viewquestion.jsp" />
				
			</div>

		</div>
	</div>




</body>
</html>
