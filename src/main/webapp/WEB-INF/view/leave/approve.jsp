<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
   <head >
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Leave Approval -Leave management</title>
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

   </head>
   <body>
      <jsp:include page="../nav/topnav.jsp" />
      <div id="wrapper">
         <jsp:include page="../includes/leave/sidebar.jsp"/>
      <div id="page-wrapper">
         <div class="row">
            <div class="col-lg-12">
               <h1 class="page-header">Leave Approval</h1>
            </div>
            <!-- /.col-lg-12 -->
         </div>
		  <div class="row">
			<div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> All Leave Requests
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="">
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Cover up Person</th>
                                                    <th>Type</th>
                                                    <th>Start Date</th>
                                                    <th>End Date</th>
                                                    <th>Start Time</th>
                                                    <th>End Time</th>
                                                    <th>Status</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${leaveRequests}" var="leave">
                                                <tr>
                                                    <td>${leave.id}</td>
                                                    <td>${leave.coverupPerson.first_name} (${leave.coverupPerson.username})</td>
                                                    <td>
                                                        ${leave.leaveType}
                                                    </td>
                                                    <td>${leave.startDate}</td>
                                                    <td>${leave.endDate}</td>
                                                    <td>${leave.startTime}</td>
                                                    <td>${leave.endTime}</td>
                                                    <td>
                                                        <span class="label ${leave.leaveStatus=='APPROVED'?'label-success':'label-danger'}">${leave.leaveStatus}</span>
                                                    </td>
                                                    <td>
                                                        <form method="post" action="${pageContext.request.contextPath}/leave/${leave.leaveStatus=='APPROVED'?'unapprove':'approve'}/${leave.id}">
                                                            <input class="btn-sm btn-link" type="submit" value="${leave.leaveStatus=='APPROVED'?'Unapprove':'Approve'}"/>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- /.table-responsive -->
                                </div>
                                <!-- /.col-lg-4 (nested) -->
                                <div class="col-lg-8">
                                    <div id="morris-bar-chart">
									
									</div>
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
			
		  </div>
      </div>
      </div>

      <!-- jQuery -->

          <script src="${pageContext.request.contextPath}/resources/leaves/vendor/jquery/jquery.min.js"  ></script>
      <!-- Bootstrap Core JavaScript -->

          <script src="${pageContext.request.contextPath}/resources/leaves/vendor/bootstrap/js/bootstrap.min.js"  ></script>
      <!-- Metis Menu Plugin JavaScript -->

          <script src="${pageContext.request.contextPath}/resources/leaves/vendor/metisMenu/metisMenu.min.js"  ></script>
      <!-- Morris Charts JavaScript -->

          <script src="${pageContext.request.contextPath}/resources/leaves/vendor/raphael/raphael.min.js"  ></script>

          <script src="${pageContext.request.contextPath}/resources/leaves/vendor/morrisjs/morris.min.js"  ></script>

          <script src="${pageContext.request.contextPath}/resources/leaves/data/morris-data.js"  ></script>
      <!-- Custom Theme JavaScript -->

          <script src="${pageContext.request.contextPath}/resources/leaves/dist/js/sb-admin-2.js"  ></script>

   </body>
</html>