<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


		
		<div class="col-sm-12 text-left" id="viewquestion"
					style="display: none">
					<span class="closebtn" id="viewquestionclsbtn">&times;</span> 

					<div class="col-sm-12 questiontopic">
						<h3 id="questiontopic"></h3>
					</div>


					<div class="col-sm-9  questionout">

						<div class="col-sm-12 questiondescription">
							<div id="questiondescription" class="col-sm-12"></div>
							<div id="questionattachments" class="col-sm-12"></div>
						</div>

						<div id="reply"></div>





						<div class="col-sm-12 formq">
							<form id="replyform" method="POST" enctype="multipart/form-data" action="reply.html" onsubmit="return validateReply(); ">
								<input type="hidden" value="${sessionScope.userId }"
									name="user.id" /> <input type="hidden"
									name="question.questionID" /> <label>Your reply</label>
								<textarea form="replyform" name="replyDescription" id="replyDesc">
								
				
						</textarea>
						<p style="color:red" id="descerror"></p>



								<label>Attachments</label><br /> <input type="file" multiple="multiple" name="uploadings" class="attachmentinput" id="uploadfile"/>
								<p style="color:red" id="fileerror"></p>
								<br /> 
								
								<input type="submit" value="send Reply" class="btnqsubmit" style="float: left" />
								<c:if test="${sessionScope.role!='client'}">
								
								<input type="submit" formaction="privatereply.html" value="post" style="float: right" class="btnqsubmit" />
								<input type="text" name="targetUsername" placeholder=" To" style="float: right" id="tousername" readonly>	
								</c:if>
							</form>
						</div>


					</div>

					<div class="col-sm-3">
						<h5>
							<b>asked :</b><span id="asked"></span>
						</h5>
						<h5>
							<b>client :</b><span id="clientusername"></span>
						</h5>
						<h5>
							<b>system :</b><span id="systemid"></span>
						</h5>
						<h5>
							<b>module :</b><span id="moduleid"></span>
						</h5>
						<h5>
							<b>status :</b><span id="status"></span>
						</h5>
						<h5>
							<b>assign to :</b><span id="technicalsusername"></span>
						</h5>
						<h5>
							<b>rating :</b><span id="rating"></span>
						</h5>
						<h5>
							<b>category :</b><span id="category"></span>
						</h5>
						

						<c:if test="${sessionScope.role=='gateway controller'}">
							<div class="col-sm-12 formq" id="gcspecialfeatures">
								<form id="assignform" class="assignform" method="get" action="assignquestion">
									<input type="hidden" name="questionID" /> <input type="hidden"
										name="status" value="inprogress" /> <label>Category</label><br />
									<select name="category" form="assignform">
										<option value="mobile">Mobile</option>
										<option value="web">Web</option>
										<option value="desktop">Desktop</option>
									</select> <label>Technical supporter</label><br /> <select
										name="technicalSuporter.id" form="assignform"  id="tcselect">
									</select> <br /> <input type="submit" class="btnqsubmit" value="Assign" />

								</form>

								<div></div>
							</div>

							<div class="col-sm-12 formq">
								<form id="closeqform" class="closeqform" method="get" action="specialreply">
									<input type="hidden" value="${sessionScope.userId }"
										name="user.id" /> <input type="hidden"
										name="question.questionID" /> <input type="hidden"
										value="You have not any response to our reply, Therefore we are close your question"
										name="replyDescription" /> <input type="hidden"
										name="question.status" value="close" /> <input type="submit"
										value="Close" class="btnqsubmit" style="float: left" />

								</form>
							</div>


							<div class="col-sm-12 formq">
								<form id="furtherimpform" class="furtherimpform" method="get" action="specialreply">
									<input type="hidden" value="${sessionScope.userId }"
										name="user.id" /> <input type="hidden"
										name="question.questionID" /> <input type="hidden"
										value="This is not current function of the system"
										name="replyDescription" /> <input type="hidden"
										name="question.status" value="further implementation" /> <input
										type="submit" value="Further Implementation"
										class="btnqsubmit" style="float: left" />

								</form>
								<div></div>
							</div>
							
							
						</c:if>
						<c:if test="${sessionScope.role!='client'}">
							<div class="col-sm-12 formq userlist" id="userlist">
								
								
									<ul id="emplist" style="list-style-type: none;margin-left:0px;padding:0px;">
									
									
									
								</ul>
								
							</div>
						
						</c:if>
						
						
						<c:if test="${sessionScope.role=='client'}">
							<br/>
							<div id="ratediv" style="display:none">
							<label>If you satisfy with our service close and rate our Tech Supporter</label>
							<form action="rateandclose" method="get">
							<div>
								<input type="hidden" name="questionID" id="closequestionID" />
								<input type="hidden" name="rating" id="tcrating" /></input>
									<span class="glyphicon glyphicon-star star" id="star1" onclick="myFunction(1);"></span>
									<span class="glyphicon glyphicon-star star" id="star2" onclick="myFunction(2);"> </span>
									<span class="glyphicon glyphicon-star star" id="star3" onclick="myFunction(3);"> </span>
									<span class="glyphicon glyphicon-star star" id="star4" onclick="myFunction(4);"> </span>
									<span class="glyphicon glyphicon-star star" id="star5" onclick="myFunction(5);"> </span>
							</div>
	
							<input type="submit"  class="btnqsubmit" id="rateandclose" value="close">
							</form>
							</div>
						</c:if>
						
						
						<c:if test="${sessionScope.role=='client'}">
							<br/>
							<div id="reopendiv" style="display:none">
							
							<form action="reopen" method="get">
							<div>
								<input type="hidden" name="questionID" id="reopenquestionID" />
								
							</div>
	
							<input type="submit" class="btnqsubmit" id="reopen" value="Reopen">
							</form>
							</div>
						</c:if>
						
					</div>


				</div>



		



