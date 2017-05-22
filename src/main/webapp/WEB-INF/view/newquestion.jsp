<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<script src="<c:url value="/resources/js/newquestion.js" />"></script>
		<script src="<c:url value="/resources/js/sockjs-0.3.4.js" />"></script>
		<script src="<c:url value="/resources/js/stomp.js" />"></script>
		<script src="<c:url value="/resources/js/ajax.js" />"></script>
		<script src="<c:url value="/resources/js/tinymce/js/tinymce/tinymce.min.js" />"></script>
		
		<script>tinymce.init({
  		selector: 'textarea',
  		height: 250,
  		menubar: false,
  		plugins: [
    				' lists ',
    				
    				'insertdatetime media table contextmenu paste code'
  				],
  		toolbar: 'undo redo | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent ',
  		content_css: '//www.tinymce.com/css/codepen.min.css'
  	  		});
  		</script>
   		<script src="<c:url value="/resources/js/websocket.js" />"></script>
		<script type="text/javascript">
			function onLoadfunction()
			{
				connect();
				loadSytems();
				

			}

      </script>
      
      
      <style>
      .error{
       		color:red;
       }
       
      </style>



      
      
</head>
<body onload="onLoadfunction()">



	<jsp:include page="./nav/topnav.jsp"/>




	<div class="container-fluid">
		<div class="row content" >
		
		
			 <jsp:include page="./nav/sidenav.jsp"/>




			<div class="col-sm-10 text-left">
				<div class="col-sm-10 form">
				<form:form id="askquestionform" method="POST" enctype="multipart/form-data" action="askquestion.html" commandName="question">
				
				
				<div class="col-sm-12" >
					<c:if test="${sessionScope.role!='client'}">
					<label>Client Username</label><br />
					</c:if>
					<input type="${sessionScope.role!='client'?'text':'hidden'}"  name="client.username" class="forminput" style="width:20%;" value="${sessionScope.role!='client'?'':sessionScope.username}" id="clientusername"/>
					<c:if test="${sessionScope.role!='client'}">
					<button id="searchprojectbycusername">search</button>
					</c:if>
				</div>
				
				
				<div class="col-sm-12" >
					<label>Topic</label><br />
					<form:input type="text" path="topic" class="forminput" style="width:100%;"/><br />
					<form:errors path="topic" cssClass="error"/>
				</div>
				
				
				<span class="col-sm-6">
					<label>System</label><br />
					<form:select path="project.projectID" form="askquestionform" id="systemsselect"></form:select>
				</span>
				
				
				<span class="col-sm-6">			
					<label>Module</label><br />
					<form:select path="module.moduleID" form="askquestionform" id="moduleselect"> </form:select>
				</span>
				<br />
				<div class="col-sm-12">
				<form:textarea form="askquestionform" path="questionDescription"></form:textarea>
				<form:errors path="questionDescription" cssClass="error"/>
				</div >
				<div class="col-sm-12">
				<label>Attachments</label><br />
				<form:input type="file" multiple="multiple" path="uploadings" class="attachmentinput"/>
				<form:errors path="uploadings" cssClass="error"/>
				</div>
				
				<div class="col-sm-12">
				<input type="submit" class="btnqsubmit" value="POST Question"/>
				</div>
				</form:form>
				
				<div>
				<span style="color:green;">${message}</span>
			</div>
			
			
		</div>	
	</div>
	
	</div>	
	</div>



</body>
</html>
