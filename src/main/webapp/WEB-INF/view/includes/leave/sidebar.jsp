<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amila
  Date: 3/11/17
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            
            <li>
                <a href="${pageContext.request.contextPath}/leave/"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/leave/apply"><i class="fa fa-dashboard fa-fw"></i> Apply For a Leave</a>
            </li>
           
    

            <c:if test="${sessionScope.get('role')=='leave supervisor'}">
            <li>
                <a href="${pageContext.request.contextPath}/leave/approve"><i class="fa fa-dashboard fa-fw"></i> Approve Leaves</a>
            </li>
            </c:if>


        </ul>
        <!-- /.nav-second-level -->
        
        
    </div>
    <!-- /.sidebar-collapse -->
</div>