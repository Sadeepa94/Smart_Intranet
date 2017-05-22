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
		<script src="<c:url value="/resources/js/loadquestion.js" />"></script>
		<script src="<c:url value="/resources/js/paginate.js" />"></script>
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
				loadQuestionforfilter();

			}

      	</script>
</head>
<body onload="onLoadfunction()">

	
	

	<jsp:include page="./nav/topnav.jsp" />



	<div class="container-fluid">
		<div class="row content">


			<jsp:include page="./nav/sidenav.jsp" />
	










			<div class="col-sm-10 text-left">
			
				<div class="col-sm-10 text-left" id="view_all_questions" >
				<div style="padding:20px">
				<h4><b>Enter your question Topic</b></h4>
				<h5>In here you can find previously asked smiler questions</h5>
				<h5>If you don't want this process click skip button and ask new question</h5>
				<input type="text" id="myInput" onkeyup="myFunction()" placeholder="question" title="Type in a question"><a href="skip.html"><button class="btnqsubmit">Skip And Ask</button> </a>                        

					<div id="table1" style="width:100%; height:300px; overflow: auto; display:none">
					<table id="myTable">
  					
  						
    					
  					
  					</table>
			
			
					</div>
					</div>
			</div>
		
		
			<div class="col-sm-12 text-left" id="viewquestion" style="display:none">
			
				<div class="col-sm-12 questiontopic" >
					<h3 id="questiontopic"></h3>
				</div>
				
				
				<div class="col-sm-9  questionout" >
				
					<div class="col-sm-12 questiondescription" > 
					<div id="questiondescription" class="col-sm-12"></div>
					<div id="questionattachments" class="col-sm-12"></div>	
					</div>
				
				<div id="reply">
					
				</div>
				
				
				
				
				
				<div class="col-sm-12 formq">
					<form id="replyform" method="POST" enctype="multipart/form-data" action="reply.html">
						<input type="hidden" value="${sessionScope.userId }" name="user.id"/>
						<input type="hidden"  name="question.QuestionID"/>
	
						<label>Your reply</label>
						<textarea form="replyform" name="replyDescription">
				
						</textarea>
				
						
				
						<label>Attachments</label><br />
						<input type="file" multiple="multiple" name="uploadings"/>
						
						
						
				
						<input type="submit" class="btnqsubmit"  value="Submit Reply"/>  
				
					</form>
				</div>
				
			
			</div>
			
			<div class="col-sm-3">
				<h5>asked  :<span id="asked"></span> </h5>
				<h5>client :<span id="clientusername"></span> </h5>
				<h5>system :<span id="systemid"></span></h5>
				<h5>module :<span id="moduleid"></span></h5>
				<h5>status :<span id="status"></span></h5>
				<h5>assign to :<span id="technicalsusername"></span></h5>
				<h5>rating :<span id="rating"></span></h5>
			</div>	
			
			
	
	
	</div>	
	
	
	
	
	</div>
	</div>
	</div>
	<script>
function myFunction(){

  var x= document.getElementById('table1');
   if (x.style.display == 'none') {
        x.style.display = 'block';
    } 

  var input, filter, table1,table2, tr, td, i;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}


</script>



</body>
</html>
