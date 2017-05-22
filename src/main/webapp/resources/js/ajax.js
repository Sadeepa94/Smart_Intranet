$(document).ready(function(){
    $("form.employeeform").on('submit',function(){
    	$('select[name="role.roleID"]').attr('disabled', false);
    	var x = $("form.employeeform").serialize();
    	console.log(x);
    	 
    	
       $.ajax({
        	url:'createEmployee',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		$(".error").text("");
        		
        		if(data.success===false){
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		}
        		else{
        			$("#employeeform")[0].reset();
        			alert(data.status);
        		}
        			
        	}
        	});
        return false;
    });
    
    
   
    
    
    $("form.roleform").on('submit',function(){
    	$('select[name="roleName"]').attr('disabled', false);
    	var x = $("form.roleform").serialize();
    	console.log(x);
    	
       $.ajax({
        	url:'createrole',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		
        		if(data.success){
        			
        			$("#roleform")[0].reset();
        			alert(data.status);
        			
        		}
        		else{
        			alert(data.status);
        		}
        		
        		loadRoleandFeatures();
        	}
        	});
        return false;
    });
    
    
    
    $("form.projectform").on('submit',function(){
    	var x = $("form.projectform").serialize();
    	console.log(x);
    	
       $.ajax({
        	url:'createProject',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		if(data.success==true){
        			$("form.projectform")[0].reset();
        			$("#input_fields_wrap").empty();
        			alert(data.status);
        		}
        		else
        			{
        			
        			$(".error").text("");
        			console.log(data);
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		}
        	}
        });
       return false;
    });
    
    
    
   
    
    
    $("form.typeform").on('submit',function(){
    	$("selecttype").attr('disabled', false);
    	var x = $("form.typeform").serialize();
    	
    	
       $.ajax({
        	url:'createType',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		if(data.success){
        			alert(data.status);
        			$("#typeform")[0].reset();
        			
        		}
        		else{
        			$(".error").text("");
        			console.log(data);
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		
        		     }
        		
        		loadTypes();
        		}
        	});
        return false;
    });
    
    
    $("form.clientform").on('submit',function(){
    	var x = $("form.clientform").serialize();
    	console.log(x);
    	
       $.ajax({
        	url:'createClient',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		console.log(data);
        		$(".error").text("");
        		
        		if(data.success===false){
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		}
        		else{
        			$("#clientform")[0].reset();
        			alert(data.status);
        		}
        			
        	}
        	});
        return false;
    });
    
    $("form.faqform").on('submit',function(){
    	var x = $("form.faqform").serialize();
    	console.log(x);
    	
       $.ajax({
        	url:'savefaq',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		console.log(data)
        		
        		if(data.status==="fail"){
        			alert("Error Occured");
        		}
        		else{
        			$("#faqform")[0].reset();
        			alert("Sucessfully Submitted");
        		}
        			
        	}
        	});
        return false;
    });
    
    
    $("form#imageupload").on('submit',function(){
    	    	
       $.ajax({
        	url:'imageupload',
        	type:'POST',
        	data:new FormData(document.getElementById("imageupload")),
        	enctype: 'multipart/form-data',
        	processData: false,
            contentType: false,
        	dataType: 'json',
        	success: function(data) {
        		
        		
        		//console.log(data);
        		//var src=$("#profilepic").attr("src");
        		//var newsrc=src.replace(data[0],data[1]);
        		//$("#profilepic").attr("src",newsrc)
        		console.log(contexturl);
        		$("#profilepic").attr("src", contexturl+data+"?"+new Date().valueOf());
        		$("#imageupload")[0].reset();
        		$('#imageModal').modal('hide');

        			
        	}
        	});
        return false;
    });
    
    
    $("#imgInp").change(function(){
    	        readURL(this);
    });
    
    
    $("form#editprofileform").on('submit',function(){
    	
    	var x = $("form#editprofileform").serialize();
        $.ajax({
         	url:'editprofile',
         	type:'POST',
         	data:x,
            dataType: 'json',
         	success: function(data) {
         		
         		$(".error").text("");
        		
        		if(data.success){
        			$("#editprofileform")[0].reset();
        			alert(data.status);
        			$('#editprofilemodal').modal('hide');
        			
        			 
        		}
        		else{
        			
        			
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		}         		
         			
         	}
         	});
         return false;
     });
    
    
 $("form#changepasswordform").on('submit',function(){
    	
    	var x = $("form#changepasswordform").serialize();
    	console.log("xxxnnnxxxx");
        $.ajax({
         	url:'changepassword',
         	type:'POST',
         	data:x,
            dataType: 'json',
         	success: function(data) {
         		$(".error").text("");
        		
        		if(data.success){
        			$("#changepasswordform")[0].reset();
        			alert(data.status);
        			$('#changepasswordmodal').modal('hide');
        		}
        		else{
        			
        			
        			for(var id in data.errors)
        				$("#"+id).text(""+data.errors[id]);
        			alert(data.status);
        		}       
         		
         			
         	}
         	});
         return false;
     });
    
    
    $("form#imageupload").on('submit',function(){
    	
        $.ajax({
         	url:'imageupload',
         	type:'POST',
         	data:new FormData(document.getElementById("imageupload")),
         	enctype: 'multipart/form-data',
         	processData: false,
             contentType: false,
         	dataType: 'json',
         	success: function(data) {
         		
         		
         		console.log(data);
         		//var src=$("#profilepic").attr("src");
         		//var newsrc=src.replace(data[0],data[1]);
         		//$("#profilepic").attr("src",newsrc)
         		console.log(contexturl);
         		$("#profilepic").attr("src", contexturl+data+"?"+new Date().valueOf());
         		$('#imageModal').modal('hide');

         			
         	}
         	});
         return false;
     });

    
   
});


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function (e) {
            $('#blah').attr('src', e.target.result);
        }
        
        reader.readAsDataURL(input.files[0]);
    }
}






