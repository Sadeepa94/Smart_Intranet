<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	

	<div style="display: block; padding: 10px">
		<img src="<c:url value="/resources/images/logo.png" />" width="120px" height="32px" style="display: block;" />
	</div>

	<nav class="navbar navbar-inverse row"
		style="margin: 0; padding: 0; background-color: #999999">

		<div class="col-sm-2"></div>
		

		<div class="col-sm-10" style="margin: 0; padding-left: 0;">

			<div class="container-fluid" style="margin: 0; padding: 0;">
				<div class="navbar-header" style="margin: 0; padding: 0;">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

				</div>
				

				<div class="collapse navbar-collapse" id="myNavbar"
					style="margin: 0; padding: 0; border: 0px;">
					<ul class="nav navbar-nav navbar-left"style="margin: 0; padding: 0; border: 0px;">
						<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'home')}">active</c:if>"><a href="<c:url value="/home.html" />">Home</a></li>
						<c:if test="${sessionScope.role!='client'}">
						<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'leave')}">active</c:if>"><a href="<c:url value="/leave.html" />">Leave</a></li>
						</c:if>
						<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'project')}">active</c:if>"><a href="<c:url value="/project.html" />">Projects</a></li>
						<li class="<c:if test="${fn:contains(pageContext.request.requestURI,'faq')}">active</c:if>"><a href="<c:url value="/faq.html" />">FAQ</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li id="username"><a href="#"><span class="glyphicon glyphicon-user"></span>${sessionScope.username}</a></li>
						<li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-out"></span></a></li>

					</ul>
				</div>
			</div>
		</div>
	</nav>




	