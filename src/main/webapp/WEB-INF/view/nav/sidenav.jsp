<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


	
	

	




	
		
		
		<div class="col-sm-2" style="padding:0px;height:100%">
		<div class="nav-side-menu" style="height:100%; border:0px;width:100%;">


								
            <ul class="nav navbar-nav side-nav" >
            	<li style="text-size:small;padding:15px;">Login as: <label id="loginrole">${sessionScope.role}</label></li>
            	<c:forEach items="${features}" var="feature">
            	
            	<c:if test="${feature=='maintenance'}">
                <li >
                    <a href="${fn:contains(pageContext.request.requestURI,'maintenance')?'#':'maintenance.html'}" data-toggle="collapse" data-target="#submenu-1"><span class="glyphicon glyphicon-triangle-right"></span>   Maintenance</a>
                    <c:if test="${fn:contains(pageContext.request.requestURI,'maintenance')}">
                    <ul id="submenu-1" class="collapsed">
                        <li id="employeebtn"><a href="#">Employee</a></li>
                        <li id="clientbtn"><a href="#">Client</a></li>
                        <li id="projectbtn"><a href="#">Project</a></li>
                        <li id="rolefeatures"><a href="#">Roles and Features</a></li>
                        <li id="emptype"><a href="#">Employee Type</a></li>
						<li id="faq"><a href="#">FAQ</a></li>
                     </ul>  
                     </c:if> 
						
                  
                    
                </li>
                </c:if>
                
                
                <c:if test="${feature=='question'}">
                <li>
                    <a href="<c:url value="question.html"/>"><span class="glyphicon glyphicon-triangle-right"></span>  Questions<span id="calResponse" class="quetionnotification"></span> </a>
                    
                </li>
                </c:if>
                
                
                <c:if test="${feature=='ask question'}">
                 <li>
                    <a href="<c:url value="askquestion.html"/>"><span class="glyphicon glyphicon-triangle-right"></span>  New Question  </a>
                    
                </li>
                </c:if>
                
                <c:if test="${feature=='dashboards'}">
                <li>
                    <a href="<c:url value="dashboard.html"/>"><span class="glyphicon glyphicon-triangle-right"></span>  Dashboards  </a>
                    
                </li>
                </c:if>
                
                <c:if test="${feature=='details'}">
                <li>
                    <a href="<c:url value="details.html"/>"><span class="glyphicon glyphicon-triangle-right"></span>  Details  </a>
                    
                </li>
                </c:if>
                
                </c:forEach>
               
               
            </ul>


			
		</div>	
		</div>




		



