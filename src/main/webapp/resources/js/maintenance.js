$(document).ready(function() {
	
	$("#newempbtn").click(function() {
		console.log(";;;");
		loadEmployeeForm();
		$("#view_all_emp").hide();
		$("#emp_form").show();
	})
	
	
	$("#empclsbtn").click(function() {
		$("#emp_form").hide();
		$("#view_all_emp").show();
	});
	
	

	$("#employeebtn").click(function() {
		$("#client,#project,#leave_type,#roles_and_feature,#FAQ_,#question_").hide();
		loadEmployee(0);
		$("#employee").show();
		$("#newempbtn").click(function() {
			loadEmployeeForm();
			$("#view_all_emp").hide();
			$("#emp_form").show();
		})
		$("#empclsbtn").click(function() {
			$("#emp_form").hide();
			$("#view_all_emp").show();
		});
		
		
		
		
	});
	
	
	$('#emppage').on('click', '.pagebtn', function(){
		var x=$(this).text();
	    console.log(x);
	    loadEmployee(x);
	    
	    
	});
	
	/*$('select[name="role.roleID"]').on('change', function(){
		var t=$('select[name="role.roleID"]').find('option:selected').text();
		console.log(t);
		if(t==="tech supporter")
			$('#tc').show();
		else
			$('#tc').hide();
	   
	      
	});*/
	
	
	
	$("#clientbtn").click(function() {
		$("#employee,#project,#leave_type,#roles_and_feature,#FAQ_,#question_").hide();
		loadClient(0);
		$("#client").show();
		$("#newclientbtn").click(function() {
			$("#clientform")[0].reset();
			loadClientForm();
			$("#view_all_client").hide();
			$("#client_form").show();
		})
		$("#clientclsbtn").click(function() {
			$("#client_form").hide();
			$("#view_all_client").show();
		});

	});
	
	
	
	$('#clientpage').on('click', '.pagebtn', function(){
		var x=$(this).text();
	    console.log(x);
	    loadClient(x);
	    
	    
	});
	
	
	
	
	
	$("#projectbtn").click(function() {
		$("#employee,#client,#leave_type,#roles_and_feature,#FAQ_,#question_").hide();
		$("#project").show();
		loadproject(0);
		$("#newprojectbtn").click(function() {
			$("form.projectform")[0].reset();
			loadProjectForm();
			$(".input_fields_wrap").empty();
			$("#view_all_project").hide();
			$("#project_form").show();
		})
		$("#projectclsbtn").click(function() {
			$("#project_form").hide();
			$("#view_all_project").show();
		});

	});
	
	
	$('#projectpage').on('click', '.pagebtn', function(){
		var x=$(this).text();
	    console.log(x);
	    loadproject(x);
	    
	    
	});
	
	
	
	
	
	
	
	
	
	
	
	$("#rolefeatures").click(function() {
		$("#employee,#client,#project,#leave_type,#FAQ_,#question_").hide();
		loadRoleandFeatures();
		$("#roles_and_feature").show();
		$("#newrolebtn").click(function() {
			$("#roleform")[0].reset();
			$("#view_all_role_features").hide();
			$("#role_form").show();
		})
		$("#roleclsbtn").click(function() {
			$("#role_form").hide();
			$("#view_all_role_features").show();
		});
		
		
		
		

	});
	
	
	
	
	$("#faq").click(function() {
		$("#employee,#client,#project,#leave_type,#roles_and_feature,#question_").hide();
		loadFAQ();
		$("#FAQ_").show();
		$("#newfaqbtn").click(function() {
			$("form.faqform")[0].reset();
			$("#view_all_faq").hide();
			$("#faq_form").show();
		})
		$("#faqclsbtn").click(function() {
			$("#faq_form").hide();
			$("#view_all_faq").show();
		});
		
		

	});
	
	$("#emptype").click(function() {
		$("#employee,#client,#project,#leave_type,#FAQ_,#question_,#roles_and_feature").hide();
		loadTypes();
		$("#leave_type").show();
		$("#newtypebtn").click(function() {
			$("selecttype").attr('disabled', false);
			$("#typeform")[0].reset();
			$("#view_all_leave_type").hide();
			$("#type_form").show();
		})
		$("#typeclsbtn").click(function() {
			$("#type_form").hide();
			$("#view_all_leave_type").show();
		});
	
	
	
	

});
	
	
	$("#empserch").on("keyup", function(){
		
		console.log($(this).val());
		var username=$(this).val()
		if(username=="")
			loadEmployee(0);
		else{
			$.ajax({
				url : 'employeesearch/'+username,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					
					console.log(data)
					$("#emppage").empty();
					loadEmpTable(data);
					
				}
			});
		}

		
	});
	
	
	
	$("#empserchbtn").on("click", function(){
		
		console.log($(this).val());
		var username=$("#empserch").val()
		if(username=="")
			loadEmployee(0);
		else{
			$.ajax({
				url : 'employeesearch/'+username,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					
					console.log(data)
					$("#emppage").empty();
					loadEmpTable(data);
					
				}
			});
		}

		
	});
	
	
	$("#clientserch").on("keyup", function(){
			
			
			var username=$(this).val()
			console.log(username);
			if(username=="")
				loadClient(0);
			else{
				$.ajax({
					url : 'searchClient/'+username,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						
						console.log(data)
						$("#clientpage").empty();
						loadClientTable(data);
						
					}
				});
			}
	
			
		});
	
	
	$("#clientserchbtn").on("click", function(){
		
		
		var username=$("#clientserch").val()
		console.log(username);
		if(username=="")
			loadClient(0);
		else{
			$.ajax({
				url : 'searchClient/'+username,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					
					console.log(data)
					$("#clientpage").empty();
					loadClientTable(data);
					
				}
			});
		}

		
	});
	
	$("#projectSearch").on("keyup", function(){
		
		
		var username=$(this).val()
		console.log(username);
		if(username=="")
			loadproject(0);
		else{
			$.ajax({
				url : 'searchProject/'+username,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					
					console.log(data)
					$("#projectpage").empty();
					loadProjectTable(data);
					
				}
			});
		}

		
	});
	
$("#projectSearchbtn").on("click", function(){
		
		
		var username=$("#projectSearch").val()
		console.log(username);
		if(username=="")
			loadproject(0);
		else{
			$.ajax({
				url : 'searchProject/'+username,
				type : 'POST',
				dataType : 'json',
				success : function(data) {
					
					console.log(data)
					$("#projectpage").empty();
					loadProjectTable(data);
					
				}
			});
		}

		
	});
	
	
	

});


function loadEmployeeForm()
{
		$.ajax({
		url : 'getEmployeeForm',
		type : 'get',
		dataType : 'json',
		success : function(data) {

			console.log(data);
			$("#employeeform")[0].reset();
			$('#eusername').val(data[2]);
			$('#ejoineddate').attr('readonly', false);
			$('select[name="role.roleID"]').attr('disabled',false);
			$('#selectrole,#selecttype').empty();
			$.each(data[0], function(index) {
				$('select[name="role.roleID"]').append($('<option>', {
					value : data[0][index].tuples[0],
					text : data[0][index].tuples[1]
				}));
			});
			
			$.each(data[1], function(index) {
				$('select[name="type.typeID"]').append($('<option>', {
					value : data[1][index].tuples[0],
					text : data[1][index].tuples[1]
				}));
			});

		}

	});
	
	
}

function loadClientForm()
{
	console.log("hhhhhhh");
	$.ajax({
		url : 'getClientForm',
		type : 'get',
		dataType : 'json',
		success : function(data) {

			console.log(data);
			$('#cusername').val(data);
			
		}

	});
	
	
}

function loadProjectForm()
{
	
	$.ajax({
		url : 'getProjectForm',
		type : 'get',
		dataType : 'json',
		success : function(data) {

			console.log(data);
			$('#projectID').val(data);
			
		}

	});
	
	
}

