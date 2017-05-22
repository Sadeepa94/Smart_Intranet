function loadQuestion(x){
	
	 $.ajax({
     	url:'loadQuestion/'+x,
     	type:'get',
     	dataType: 'json',
     	success: function(data) {
     		$('#questionpage').empty();
     		loadQuestionTable(data.source);
     		var page=paginate(data.pages,data.isFirstpage,data.isLastpage,data.current);
     		$("#questionpage").append(page);
     		
     	}
     	});
}



function loadQuestionTable(source)
{
	var content=""; 
	$('#questionpage').empty();
	$("#questionTable").empty();
	for(var id in source){
			
		if(source[id].tuples[3]===null)
			source[id].tuples[3]="not assign";
		
		if(source[id].tuples[8]==null)
			source[id].tuples[8]="not assign";
					
		var url="viewquestion/"+source[id].tuples[7];
			
		var rate=colorStars(source[id].tuples[5]);
		
		content += '<div class="col-sm-12 questiondiv"><span class="col-sm-2"><div>'+source[id].tuples[1]+'</div><div>'+rate+'</div></span><span class="col-sm-10 "><a href="'+url+'" class="questionurl"><div class="col-sm-12"><h4>'+source[id].tuples[0]+'</h4></div></a><div class="col-sm-12">'+source[id].tuples[6].slice(0,150)+'...</div><div class="col-sm-12"><span class="questiontabletc col-sm-2">'+source[id].tuples[3]+'</br>'+source[id].tuples[8]+'</span><span class="questiontableclient col-sm-5">asked : '+source[id].tuples[2]+'<br/>'+source[id].tuples[4]+'</span></div></span></div>';
			
		}
		
		$("#questionTable").append(content);




}



function loadQuestionforfilter(){
	
	 $.ajax({
    	url:'loadQuestionforfilter',
    	type:'get',
    	dataType: 'json',
    	success: function(data) {
    		var content="";
    		console.log(data);
    		$("#questionTable").empty();
    		$('#questionpage').empty();
    		for(var id in data){
		
    			var url="viewquestion/"+data[id].tuples[7];
    			content += '<tr><td><a href="'+url+'" class="questionurl filtered_url">'+data[id].tuples[0]+'</a></td></tr>';
    		}
    		$("#myTable").append(content);

    	}
    	});
}


function filterMyQuestions(){
	$.get("filterquestion?query=username&selected="+$('#username').text(), function(res, status){
		loadQuestionTable(JSON.parse(res));
	});
}

function filterQuestions(){
    
	console.log("came");
    var selected=$('#f1').val();
    var query='status';
    if($('#f2').prop('checked'))
    	selected=$('#f2').val();
    else if($('#f2').prop('checked'))
        selected=$('#f2').val();
    else if($('#f3').prop('checked'))
        selected=$('#f3').val();
    else if($('#f4').prop('checked'))
        selected=$('#f4').val();
    else if($('#f5').prop('checked')){
        selected=$('#f5').val();
        query='category';
    }
    else if($('#f6').prop('checked')){
        selected=$('#f6').val();
        query='category';
    }
    else if($('#f7').prop('checked')){
        selected=$('#f7').val();
        query='category';
    }
    
    console.log(selected+" "+query);
    $.get("filterquestion?query="+query+"&selected="+selected, function(res, status){
    	loadQuestionTable(JSON.parse(res));
    });
    
}






$(document).ready(function() {
	
	
		
	
	$('#myInput').on('keyup', function() {
		if($('#myInput').val()=='')
			$('#myTable').hide();
		else
			$('#myTable').show();
	});
	
	
	
	$('#questionpage').on('click', '.pagebtn', function(){
		var x=$(this).text();
    	loadQuestion(x);
    
    
	});
	
	
	
	$('#question-filter-list input[type="checkbox"]').on('change', function() {
        if($('input[type="checkbox"]').prop('checked')){
            $('input[type="checkbox"]').not(this).prop('checked', false);   
        }else{
        	$('input[type="checkbox"]').not(this).prop('checked', false);   
        }
        filterQuestions();
    });
	
	
	
	
	$('#myquestion').on('click', function(){
		filterMyQuestions();
    
	});
    
	
	$( "body" ).delegate( ".questionurl", "click", function() {
		
		var url=this.href;
		
		 $.ajax({
		     	url:url,
		     	type:'get',
		     	dataType: 'json',
		     	success: function(data) {
		     		console.log(data);
		     		$('#view_all_questions').hide();
		    		$('#viewquestion').show();
		     		
		     		
		     		$("#questiontopic").text(data[0].tuples[0]);
		     		$("#questiondescription").html(data[0].tuples[6]);
		     		$("#asked").text(data[0].tuples[4]);
		     		$("#clientusername").text(data[0].tuples[2]);
		     		if(data[0].tuples[3]==null)
		     			data[0].tuples[3]="not yet";
		     		$("#technicalsusername").text(data[0].tuples[3]);
		     		$("#status").text(data[0].tuples[1]);
		     		if(data[0].tuples[8]==null)
		     			data[0].tuples[8]="General";
		     		$("#systemid").text(data[0].tuples[8]);
		     		if(data[0].tuples[9]==null)
		     			data[0].tuples[9]="General";
		     		$("#moduleid").text(data[0].tuples[9]);
		     		
		     		
		     		if(data[0].tuples[5]==null)
		     			var rate="not yet";
		     		else
		     			var rate=colorStars(data[0].tuples[5]);
		     			
		     		$("#rating").html(rate);
		     		$("input[name='question.questionID'],input[name='questionID']").val(data[0].tuples[7]);
		     		$('#closequestionID').val(data[0].tuples[7]);
		     		
		     		if(data[0].tuples[10]==null)
		     			data[0].tuples[10]="not assign"
		     		$("#category").text(data[0].tuples[10]);
		     		
		     		
		    		$("#tcrating").val(0);
		     		
		     		var attachment="";
		     		
		     		for(var index in data[1]){
     					if(data[1][index].tuples[0]!=null)
     						attachment +='<div class="attachement"><a class="attachments" href="download?filename='+data[1][index].tuples[0]+'">'+data[1][index].tuples[1]+'</a></div>';
     				}
		     		$("#questionattachments").html(attachment);
		     		
		     		
		     		var replies="";
		     		
		     		for(var id in data[2]){
		     			
		     		
		     			
		     			if(data[2][id][0].tuples[3]==="client"){
		     				
		     				replies +='<div id="clientreply" class="col-sm-10 clientreply">'+data[2][id][0].tuples[1]+' ';
		     				
		     				replies +='<span class="questiontableclient col-sm-5">from : '+data[2][id][0].tuples[4]+'<br/>'+data[2][id][0].tuples[2]+'</span>';
		     				for(var index in data[2][id][1]){
		     					if(data[2][id][1][index].tuples[0]!=null)
		     						replies +='<div class="attachement"><a class="attachments" href="download?filename='+data[2][id][1][index].tuples[0]+'">'+data[2][id][1][index].tuples[1]+'</a></div>';
		     				}
		     				
		     				replies +='</div>'
		     				
		     			}
		     			
		     			else{
		     				
		     				replies +='<div id="compnanyreply" class="col-sm-10 companyreply"><p>'+data[2][id][0].tuples[1]+'</p>';
		     				
		     				replies +='<span class="questiontableclient col-sm-5">from : '+data[2][id][0].tuples[4]+'<br/>'+data[2][id][0].tuples[2]+'</span>';
		     				
		     				for(var index in data[2][id][1]){
		     					if(data[2][id][1][index].tuples[0]!=null)
		     						replies +='<div class="attachement"><a class="attachments" href="download?filename='+data[2][id][1][index].tuples[0]+'">'+data[2][id][1][index].tuples[1]+'</a></div>';
		     				}
		     				replies +='</div>'
		     				
		     				
		     			}
		     				
		     			}
		     			
		     			
		     		
		     			$("#reply").html(replies);
		     			
		     			var checkrole = $("#loginrole").text();
		   
		     			if(checkrole==="gateway controller")
		     				{	$("#tcselect").empty();
		     					$.each(data[4], function(index) {
		    						$('select[name="technicalSuporter.id"]').append($('<option>', {
		    							value : data[4][index].tuples[0],
		    							text : data[4][index].tuples[1]+'('+data[4][index].tuples[2]+')'
		    						}));
		    					});
		     				
		     				}
		     			
		     			if(checkrole!="client"){
		     			var employeelist="";
		     			$.each(data[3], function(index) {
    						
    						employeelist+='<li class="empchatlist" data-target="'+data[3][index].tuples[1]+'"><span class="chatpicture"><img src="'+contexturl+data[3][index].tuples[3]+'"  width="25px" height="auto"/></span>'+data[3][index].tuples[1]+'('+data[3][index].tuples[2]+')</li>'
    					});
		     			$('#emplist').html(employeelist);
		     			}
		     			if(data[0].tuples[1]!="close")
		     				$("#ratediv").show();
		     			
		     			if(data[0].tuples[1]=="close")
		     				$("#reopendiv").show();
		     			else
		     				$("#reopendiv").hide();
		     			
		     			
		     		
		     	}
		     	
		     	});
		
		  return false;
		});
	
	

	 /*$("form.furtherimpform").on('submit',function(){
	    	var x = $("form.furtherimpform").serialize();
	    	
	    	
	       $.ajax({
	        	url:'specialreply',
	        	type:'get',
	        	data:x,
	        	dataType: 'json',
	        	success: function(data) {
	        		$(".error").text("");
	        		console.log(data.status);
	        		if(data.status==="Fail"){
	        			alert("Error Occured");
	        			
	        		}
	        		else{
	        	
	        			alert("Sucessfully Submitted");
	        		}
	        			
	        	}
	        	});
	        return false;
	    });
	 
	 $("form.assignform").on('submit',function(){
	    	var x = $("form.assignform").serialize();
	    	
	    	
	       $.ajax({
	        	url:'assignquestion',
	        	type:'get',
	        	data:x,
	        	dataType: 'json',
	        	success: function(data) {
	        		
	        		console.log(data.status);
	        		if(data.status==="Fail"){
	        			alert("Error Occured");
	        		}
	        		else{
	        			
	        			alert("Sucessfully Assigned");
	        		}
	        			
	        	}
	        	});
	        return false;
	    });
	 
	 $("form.closeqform").on('submit',function(){
	    	var x = $("form.closeqform").serialize();
	    	
	    	
	       $.ajax({
	        	url:'specialreply',
	        	type:'get',
	        	data:x,
	        	dataType: 'json',
	        	success: function(data) {
	        		$(".error").text("");
	        		console.log(data.status);
	        		if(data.status==="Fail"){
	        			alert("Error Occured");
	        		}
	        		else{
	        			$("#employeeform")[0].reset();
	        			alert("Sucessfully Closed question");
	        		}
	        			
	        	}
	        	});
	        return false;
	    });*/
	$("#userlist").delegate(".empchatlist","click", function(){
		
		var username=$(this).data("target");
		console.log(username);
		$("#tousername").val(username);		 
		 
	 });
	 
	 
	 $("#viewquestionclsbtn").click(function(){
		 $('#view_all_questions').show();
 		$('#viewquestion').hide();
		 
	 });
	 
	 $("#notificationlist").delegate(".notificationclick","click", function(){
	
		var notificationID=$(this).data("target");
				 $.ajax({
	        	url:'notificationread/'+notificationID,
	        	type:'get',
	        	dataType: 'json',
	        	success: function(data) {
	        		
	        		console.log(data);
	        		$("#noofnotifications").text(data.length);
	        		
	        		var notifiList="";
	        		$.each(data, function(index) {
	        			notifiList += '<li role="presentation" class="notificationclick" data-target="'+data[index].tuples[1]+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+data[index].tuples[2]+'">'+data[index].tuples[0]+'</a></li>'
	    			});
	        		
	        		notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#">view all</a></li>';
	        		
	        		$("#notificationlist").html(notifiList);
	        		
	        		
	        			
	        	}
	        	});
		 
	 });
	 
	/* $(".notificationclick").click(function(){
			
			var notificationID=$(this).data("target");
					 $.ajax({
		        	url:'notificationread/'+notificationID,
		        	type:'get',
		        	dataType: 'json',
		        	success: function(data) {
		        		
		        		console.log(data);
		        		$("#noofnotifications").text(data.length);
		        		
		        		var notifiList="";
		        		$.each(data, function(index) {
		        			notifiList += '<li role="presentation" class="notificationclick" data-target="'+data[index].tuples[1]+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+data[index].tuples[2]+'">'+data[index].tuples[0]+'</a></li>'
		    			});
		        		notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#">view all</a></li>';	
		        		$("#notificationlist").html(notifiList);
		        		
		        		
		        			
		        	}
		        	});
			 
		 });*/
	 
	 
	 $("#notificationlist").delegate("#viewallnotifictions","click", function(){
			
		 var place=$(this).text();
			console.log(place);
					$.ajax({
		        	url:'getAllnotifications/'+place,
		        	type:'get',
		        	dataType: 'json',
		        	success: function(data) {
		        		
		        		console.log(data);
		        		
		        		
		        		

		        		var notifiList="";
		        		notifiList +='<li role="presentation" id="viewallunreadnotifictions" ><a role="menuitem" tabindex="-1" href="#"><b>unread notifications</b></a></li>';
		        		if(!data.isFirstpage)
		        			notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#"><b>prev</b></a></li>';	
		        		$.each(data.source, function(index) {
		        			notifiList += '<li role="presentation" class="" data-target="'+data.source[index].tuples[1]+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+data.source[index].tuples[2]+'">'+data.source[index].tuples[0]+'</a></li>'
		    			});
		        		if(!data.isLastpage)
		        			notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#"><b>more</b></a></li>';	
		        		$("#notificationlist").html(notifiList);
		        		$("#notificationlist").show();
		        			
		        	}
		        	});			 
		 });
	 
	 
	 $("#notificationlist").delegate("#viewallunreadnotifictions","click", function(){
		 
		 console.log("sadeepa");
			
		 $.ajax({
		        	url:'getallunreadnotifictions',
		        	type:'get',
		        	dataType: 'json',
		        	success: function(data) {
		        		
		        		console.log(data);
		        		
		        		
		        		$("#noofnotifications").text(data.length);
		        		
		        		var notifiList="";
		        		$.each(data, function(index) {
		        			notifiList += '<li role="presentation" class="notificationclick" data-target="'+data[index].tuples[1]+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+data[index].tuples[2]+'">'+data[index].tuples[0]+'</a></li>'
		    			});
		        		
		        		notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#">view all</a></li>';
		        		$("#notificationlist").html(notifiList);

		        		
		        			
		        	}
		        	});			 
		 });
		 
		 /*$("#viewallnotifictions").click(function(){
				
				var place=$(this).text();
				console.log(place);
						$.ajax({
			        	url:'getAllnotifications/'+place,
			        	type:'get',
			        	dataType: 'json',
			        	success: function(data) {
			        		
			        		console.log(data);
			        		
			        		
			        		var notifiList="";
			        		if(!data.isFirstpage)
			        			notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#" style="align:center">prev</a></li>';	
			        		$.each(data.source, function(index) {
			        			notifiList += '<li role="presentation" class="" data-target="'+data.source[index].tuples[1]+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+data.source[index].tuples[2]+'">'+data.source[index].tuples[0]+'</a></li>'
			    			});
			        		if(!data.isLastpage)
			        			notifiList +='<li role="presentation" id="viewallnotifictions" ><a role="menuitem" tabindex="-1" href="#" style="align:center">more</a></li>';	
			        		$("#notificationlist").html(notifiList);
			        		$("#notificationlist").show();
			        		
			        			
			        	}
			        	});
						
						
						
				 
			 });*/
		 
		 $("#menu1notifications").click(function(){	
			
				$("#notificationlist").toggle();
			});
		

	
	

	
})

function colorStars(rate){
	
	
	var rating='';
		
	for(var i=1; i<=rate; i++)
	{
		rating=rating.concat('<span class="glyphicon glyphicon-star" style="color:orange;padding:2px"></span>');
	}
	
	for(var j=rate+1;j<=5;j++)
	{
		rating=rating.concat('<span class="glyphicon glyphicon-star" style="color:#195e86;padding:2px"></span>');
	}
	
	return rating;
}

function myFunction(x){
	if(($("#tcrating").val()==1)&&(x==1))
	{
	
		$("#tcrating").val(0);
		$(".star").css("color", "gray");
	}
	else{
		$("#tcrating").val(x);
		$(".star").css("color", "gray");
		for(var i=1;i<=x;i++)
		{
			$("#star"+i).css("color", "gold");
		}
	}
}

function validateReply(){
	$("#descerror").text('');
	$("#fileerror").text('');
	console.log("valid reply");
	var fileList = document.getElementById("uploadfile").files;
	console.log(fileList);
	var description= $("#replyDesc").text();
	var fileresult=false;
	var desresult=false;
	
	if(description==""||description==null)
		{
			$("#descerror").text("Must be not empty");
			desresult=false
		}
	else
		desresult=true;
	
	
	
	if(fileList.length==0)
		fileresult=true;
	else{
	
	for(var i=0;i<fileList.length;i++){
		var filename=fileList[i].name;
		var ext=filename.split('.').pop();
		if(ext=="pdf"||ext=="png"||ext=="jpg"||ext=="jpeg"||ext=="doc"||ext=="docx"||ext=="txt")
			{
				fileresult=true;
			}
		else
			{
				$("#fileerror").text("Invalid file type "+ ext);
				fileresult= false;
				break;
			}
		
	}
	}
	return desresult&&fileresult;
	
	
	
	
}

function loadNotification(notification){
	
	var no=$("#noofnotifications").text();
	$("#noofnotifications").text(parseInt(no)+1);
	var notificationList=$("#notificationlist").html();
	var newnotificationList='<li role="presentation" class="notificationclick" data-target="'+notification.notificationId+'"><a role="menuitem" tabindex="-1" class="questionurl" href="viewquestion/'+notification.question.questionID+'">'+notification.message+'</a></li>'
	$("#notificationlist").html(newnotificationList+notificationList);
}

