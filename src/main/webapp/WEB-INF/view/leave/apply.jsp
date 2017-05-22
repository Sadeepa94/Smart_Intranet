<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Leaves - Smart Intranet</title>

    <!-- Bootstrap Core CSS -->


    <link href="${pageContext.request.contextPath}/resources/leaves/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
    <!-- MetisMenu CSS -->

    <link href="${pageContext.request.contextPath}/resources/leaves/vendor/metisMenu/metisMenu.min.css" rel="stylesheet" >
    <!-- Custom CSS -->

    <link href="${pageContext.request.contextPath}/resources/leaves/dist/css/sb-admin-2.css" rel="stylesheet" >

    

    <link href="${pageContext.request.contextPath}/resources/leaves/vendor/morrisjs/morris.css" rel="stylesheet" >
    <!-- Custom Fonts -->

    <link href="${pageContext.request.contextPath}/resources/leaves/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" >
    <link href="<c:url value="/resources/css/customcss.css" />" rel="stylesheet" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>

    <script src="${pageContext.request.contextPath}/resources/leaves/https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" rel="stylesheet" ></script>

    <script src="${pageContext.request.contextPath}/resources/leaves/https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" rel="stylesheet" ></script>
    <![endif]-->

    <script type="text/javascript">
        function clearField() {
            if(document.getElementById) {
                document.chatform.reset();
            }
        }
    </script>

</head>

<body>
    <jsp:include page="../nav/topnav.jsp" />
    <div id="wrapper">

        <%--side bar--%>
        <jsp:include page="../includes/leave/sidebar.jsp"/>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="page-header" style="color:black">Apply Leave</h2>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
				<div class="row">
					<div class="col-md-6">
					<div class="panel panel-default">
                        <div class="panel-body">
                           <form:form id="leaveform" method="post"
                                      modelAttribute="leave"
                                      action="${pageContext.request.contextPath}/leave/apply">
						   
				
							<div class="form-group">
								<label>Leave Type</label>
								<form:select cssClass="form-control" path="leaveType" items="${leaveTypes}"/>
							</div>
                               <div class="form-group">
                                   <label>Cover up person</label>
                                   <form:select cssClass="form-control" path="coverupPerson.id" items="${employeeList}" itemLabel="username" itemValue="id"/>
                               </div>

                               <div class="row">
							<div class="form-group col-md-6">
								<label>Start Date</label>
								<input name="startDate" class="form-control" type="date">
							</div>
                               <div class="form-group col-md-6">
                                   <label>End Date</label>
                                   <input name="endDate" class="form-control" type="date">
                               </div>

                               </div>

                               <div class="row">
                               <div class="form-group col-md-6">
                                   <label>start time</label>
                                   <input name="startTime"class="form-control" type="time">
                               </div>
                               <div class="form-group col-md-6">
                                   <label>End Time</label>
                                   <input  name="endTime" class="form-control" type="time">
                               </div>
                               </div>
						
							<button type="submit" class="btn btn-success" >Submit</button>
							<button type="reset" class="btn btn-default" >Clear</button>

							
						   </form:form>
                        </div>
						
                    </div>
					</div>
					<div class="col-lg-4">
					</div>
					
					
					
				</div>
            </div>
            <!-- /.container-fluid -->
        </div>


    </div>
	    
    <!-- /#wrapper -->

    <!-- jQuery -->


    <script src="${pageContext.request.contextPath}/resources/leaves/vendor/jquery/jquery.min.js"  ></script>
    <!-- Bootstrap Core JavaScript -->

    <script src="${pageContext.request.contextPath}/resources/leaves/vendor/bootstrap/js/bootstrap.min.js"  ></script>
    <!-- Metis Menu Plugin JavaScript -->

    <script src="${pageContext.request.contextPath}/resources/leaves/vendor/metisMenu/metisMenu.min.js" ></script>
    <!-- Morris Charts JavaScript -->

    <script src="${pageContext.request.contextPath}/resources/leaves/vendor/raphael/raphael.min.js" ></script>

    <script src="${pageContext.request.contextPath}/resources/leaves/vendor/morrisjs/morris.min.js"></script>

    <script src="${pageContext.request.contextPath}/resources/leaves/data/morris-data.js" ></script>
    <!-- Custom Theme JavaScript -->

    <script src="${pageContext.request.contextPath}/resources/leaves/dist/js/sb-admin-2.js" ></script>


</body>



</html>
