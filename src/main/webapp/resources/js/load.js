function loadEmployee(x) {
	
			$.ajax({
				url : 'loadEmployee/' + x,
				type : 'get',
				 async: false,

				dataType : 'json',
				success : function(data) {
					
					console.log(data);
					$("#emppage").empty();
					loadEmpTable(data.source);
					var page = paginate(data.pages, data.isFirstpage,
							data.isLastpage, data.current);
					$("#emppage").append(page);
					
				}
			});
}

function loadRoleandFeatures() {
	$.ajax({
		url : 'loadRoles',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			var contentrole = "";
			console.log(data);
			$("#roleTable").empty();
			for ( var id in data) {

				contentrole += '<tr><td class="col-sm-1">'
						+ (parseInt(id) + parseInt(1))
						+ '</td><td class="col-sm-2">' + data[id].roleName
						+ '</td><td class="col-sm-3">'
						+ data[id].roleDescription
						+ '</td><td class="col-sm-4">' + data[id].features
						+ '</td><td class="col-sm-1"><button class="btnB" onclick="editRole(\''
						+ data[id].roleID
						+ '\');">Edit</button></td></tr>'

			}

			$("#roleTable").append(contentrole);

		}
	});

}

function loadTypes() {
	
			$.ajax({
				url : 'loadTypes',
				type : 'get',
				dataType : 'json',
				success : function(data) {
					console.log(data);
					var contenttype = "";
					
					$("#typeTable").empty();
					
					for ( var id in data) {

						contenttype += '<tr><td class="col-sm-1">'
								+ (parseInt(id) + parseInt(1))
								+ '</td><td class="col-sm-3">'
								+ data[id].typeName
								+ '</td><td class="col-sm-1">'
								+ data[id].maxAnnualLeaves
								+ '</td><td class="col-sm-1">'
								+ data[id].maxCasualLeaves
								+ '</td><td class="col-sm-1">'
								+ data[id].maxSickLeaves
								+ '</td><td class="col-sm-1">'
								+ data[id].maxShortLeaves
								+ '</td><td class="col-sm-1"><button class="btnB" onclick="editType(\''
								+ data[id].typeID
								+ '\');">Edit</button> </td></tr>'

					}

					
					
					$("#typeTable").append(contenttype);
					
				}
			});
}



function loadFAQ() {
	
	$.ajax({
		url : 'loadfaq',
		type : 'get',
		dataType : 'json',
		success : function(data) {
			console.log(data);
			var contentfaaq = "";
			
			$("#faqTable").empty();
			
			for ( var id in data) {

				contentfaaq += '<tr><td class="col-sm-1">'
						+ (parseInt(id) + parseInt(1))
						+ '</td><td class="col-sm-9">'
						+ data[id].questionDescription
						+ '</td><td class="col-sm-1"><button class="btnB" onclick="editFAQ(\''+ data[id].faqID+'\');">Edit</button> </td>'
						+ '<td class="col-sm-1"><button class="btnA"  onclick="deleteFAQ(\''+ data[id].faqID+'\');">Delete</button> </td></tr>'
						

			}

			
			
			$("#faqTable").append(contentfaaq);
			
		}
	});
}

function loadClient(x) {
	
			$.ajax({
				url : 'loadClient/' + x,
				type : 'get',
				dataType : 'json',
				success : function(data) {
					
					
					loadClientTable(data.source)
					var page = paginate(data.pages, data.isFirstpage,
							data.isLastpage, data.current);
					$("#clientpage").append(page);
				}
			});
}

function loadproject(x) {
	
	$.ajax({
		url : 'loadprojects/'+x,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			
			
			$("#projectpage").empty();
			loadProjectTable(data.source)		
			var page = paginate(data.pages, data.isFirstpage,
								data.isLastpage, data.current);
			$("#projectpage").html(page);
		}
	});
}



function editEmployee(x) {
	$.ajax({
		url : 'getEmployeeForm',
		type : 'get',
		dataType : 'json',
		success : function(data) {

			console.log(data);
			$("#employeeform")[0].reset();
			$('select[name="role.roleID"]').empty();
			$('select[name="type.typeID"]').empty();
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

			$.ajax({

				url : 'getEmployee/' + x,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					
					$("#view_all_emp").hide();
					$("#emp_form").show();
					console.log(data);
					$("#eusername").val(data.username);
					$("#efirstname").val(data.first_name);
					$("#elastname").val(data.last_name);
					$("#eemail").val(data.email);
					$("#ejoineddate").val(data.joinedDate);
						$('#ejoineddate').attr('readonly', true);
					$("#eaddressno").val(data.address.no);
					$("#eaddressstreet").val(data.address.street);
					$("#eaddresstown").val(data.address.town);
					$("#eaddressdistrict").val(data.address.district);
					$("#econtact_no").val(data.contact_no);
					$('select[name="role.roleID"]').val(data.role.roleID);
					$('select[name="type.typeID"]').val(data.type.typeID);
					$('input:radio[name="enable"]').filter('[value='+data.enable+']').attr('checked', true);
						/*$('select[name="role.roleID"]').attr('disabled', true);
					if(data.role.roleName=="tech supporter"){
						$("#tc").show();
						$.each(data.specifications, function(i) {
					$("input:checkbox[value="+data.specifications[i]+"]").attr("checked", true);
						});
						
					}
					
					else
						$("#tc").hide();*/
						
					//$('input:radio[name="enable"]').filter('[value='+data.enable+']').attr('checked', true);

				}

			});

		}

	});

}



function viewEmployee(x) {
	console.log(x);
	$.ajax({
		url : 'getEmployee/'+x,
		type : 'post',
		dataType : 'json',
		success : function(data) {

			console.log(data);
			var inner = '<tr><td><img src="'+contexturl+data.profilepicture+'" width="100px" height="auto" style="display: block;" /></td><td>'
					+ data.username + "</td></tr>";
			inner = inner.concat("<tr><td><label>Full Name</label></td><td>"
					+ data.first_name + " " + data.last_name + "</td></tr>");
			inner = inner.concat("<tr><td><label>Role</label></td><td>"
					+ data.role.roleName + "</td></tr>");
			inner = inner.concat("<tr><td><label>Employee Type</label></td><td>"
					+ data.type.typeName + "</td></tr>");
			inner = inner.concat("<tr><td><label>Joined Date</label></td><td>"
					+ data.joinedDate + "</td></tr>");
			inner = inner.concat("<tr><td><label>Email</label></td><td>"
					+ data.email + "</td></tr>");
			inner = inner.concat("<tr><td><label>Contact No</label></td><td>"
					+ data.contact_no + "</td></tr>");
			inner = inner.concat("<tr><td><label>Address</label></td><td>"
					+ data.address.no + " " + data.address.street + " "
					+ data.address.town + " " + data.address.district
					+ "</td></tr>");
			inner = inner.concat("<tr><td><label>Remaining annual leave</label></td><td>"
					+ data.remainingAnnualLeaves + "</td></tr>");
			inner = inner.concat("<tr><td><label>Remaining casual leave</label></td><td>"
					+ data.remainingCasualLeaves + "</td></tr>");
			inner = inner.concat("<tr><td><label>Remaining short leave</label></td><td>"
					+ data.remainingShortLeaves + "</td></tr>");
			inner = inner.concat("<tr><td><label>Remaining sick leave</label></td><td>"
					+ data.remainingSickLeaves + "</td></tr>");
			$("#viewEmployee").html(inner);
		}

	});

}

function viewclient(x) {
	console.log(x);
	
			$.ajax({
				url : 'getclient/' + x,
				type : 'post',
				dataType : 'json',
				success : function(data) {

					console.log(data);
					var inner = "<tr><td><label>User Name</label></td><td>"
							+ data.username + "</td></tr>";
					inner = inner
							.concat("<tr><td><label>Company Name</label></td><td>"
									+ data.companyName + "</td></tr>");
					inner = inner.concat("<tr><td><label>Role</label></td><td>"
							+ data.role.roleName + "</td></tr>");
					inner = inner
							.concat("<tr><td><label>Responsible Person</label></td><td>"
									+ data.responsible_person + "</td></tr>");
					inner = inner
							.concat("<tr><td><label>Email</label></td><td>"
									+ data.email + "</td></tr>");
					inner = inner
							.concat("<tr><td><label>Contact No</label></td><td>"
									+ data.contact_no + "</td></tr>");
					inner = inner
							.concat("<tr><td><label>Address</label></td><td>"
									+ data.address.no + " "
									+ data.address.street + " "
									+ data.address.town + " "
									+ data.address.district + "</td></tr>");
					$("#viewclient").html(inner);
				}

			});

}

function editclient(x){
	
	$.ajax({
		url : 'getclient/' + x,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			
			$("#view_all_client").hide();
			$("#client_form").show();
			$("#cusername").val(data.username);
			$("#ccompanyname").val(data.companyName);
			$("#cpname").val(data.responsible_person);
			$("#cemail").val(data.email);
			$("#caddressno").val(data.address.no);
			$("#caddressstreet").val(data.address.street);
			$("#caddresstown").val(data.address.town);
			$("#caddressdistrict").val(data.address.district);
			$("#ccontactno").val(data.contact_no);
			$('input:radio[name="enable"]').filter('[value='+data.enable+']').attr('checked', true);
			
			
			
		}
	
	});
	

}

function editproject(x){
	
	$.ajax({
		url : 'getproject/'+x,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			$("#view_all_project").hide();
			$("#project_form").show();
			$(".input_fields_wrap").empty();
			$('[name="projectID"]').val(data.projectID);
			$('[name="projectName"]').val(data.projectName);
			$('[name="client.username"]').val(data.client.username);
			$('[name="deliveryDate"]').val(data.deliveryDate);
			$('[name="description"]').val(data.description);
			
			
			var y="";
			
			 $.each(data.modules, function(i) {
				y+='<div><hr style="border-color:#195e86;"></hr>';
				y+='<table><tr><td><label>module ID</label><br />';
				y+='<input type="text" name="modules['+i+'].moduleID" size="20" value="'+data.modules[i].moduleID+'"/></td>';
				y+='<td ><label>module Name</label><br />';
				y+='<input type="text" name="modules['+i+'].moduleName" size="20" value="'+data.modules[i].moduleName+'"/></td>';
				y+='</tr><tr ><td colspan="2"><label>Description</label><br />';
				y+='<textarea rows="3" cols="50" name="modules['+i+'].description" form="projectform" >'+data.modules[i].description+'</textarea></td>';
				y+='</tr></table></div>';
				
				});
			 
			 $(".input_fields_wrap").html(y);
			
			
		}
	
	});
}

function viewproject(x){
	
	$.ajax({
		url : 'getproject/'+x,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			var inner = "<tr><td><label>ProjectID</label></td><td>"+ data.projectID+ "</td></tr>";
			inner = inner.concat("<tr><td><label>Project Name</label></td><td>"+data.projectName+ "</td></tr>");
			inner = inner.concat("<tr><td><label>Client Name</label></td><td>"+ data.client.companyName + "</td></tr>");
		    inner = inner.concat("<tr><td><label>Responsible Person</label></td><td>"+ data.client.responsible_person + "</td></tr>");
		    inner = inner.concat("<tr><td><label>DeliveryDate</label></td><td>"+data.deliveryDate+"</td></tr>");
		    inner = inner.concat("<tr><td><label>Description</label></td><td>"+ data.description+ "</td></tr>");
		    
		    var module="";
		    $.each(data.modules, function(index) {
		    
		    	module=module.concat("<h5><u><b>Module "+(parseInt(index) + parseInt(1))+"</b></u><h5>");
		    	module = module.concat("<table><tr><td><label>Module ID</label></td><td>"+data.modules[index].moduleID+ "</td></tr>");
		    	module = module.concat("<table><tr><td><label>Module Name</label></td><td>"+data.modules[index].moduleName+ "</td></tr>");
		    	module = module.concat("<table><tr><td><label>Description</label></td><td>"+data.modules[index].description+ "</td></tr></table>");
		    });
		   
		    $("#viewproject").html(inner);
		    $("#viewmodule").html(module);
	
			
			
		}
	
	});
}


function editRole(x){
	
	$.ajax({
		url : 'getRole/'+x,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			$("#view_all_role_features").hide();
			$("#role_form").show();
			
			$('select[name="roleName"]').val(data.roleName);
			$('select[name="roleName"]').attr('disabled', true);
			$("#roleDescription").val(data.roleDescription);
			
		}
	
	});
}


function editType(x){
	console.log("xxx"+x);
	
	$.ajax({
		url : 'getType/'+x,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			$("#view_all_leave_type").hide();
			$("#type_form").show();
			$("#selecttypename").val(data.typeName);
			$("#selecttypename").attr('disabled', true);
			$("#maxShortLeaves").val(data.maxShortLeaves);
			$("#maxAnnualLeaves").val(data.maxAnnualLeaves);
			$("#maxCasualLeaves").val(data.maxCasualLeaves);
			$("#maxSickLeaves").val(data.maxSickLeaves);
			$("#typeDescription").val(data.description);
			
			
			
			
		}
	
	});
}

function deleteFAQ(faqID){
	
	$.ajax({
		url : 'deletefaq/'+faqID,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			console.log(data);
			loadFAQ();
			
			
			
			
		}
	});

	
}

function editFAQ(faqID){
	
	$("#view_all_faq").hide();
	$("#faq_form").show();
	
	$.ajax({
		url : 'getfaq/'+faqID,
		type : 'get',
		dataType : 'json',
		success : function(data) {
			
			console.log(data.faqID);
			$("#faqID").val(data.faqID);
			$("#faqquestionDescription").val(data.questionDescription);
			$("#faqanswerDescription").val(data.answerDescription);
			
			
			
			
			
			
		}
	});

	
}

function hideedit(){
	var pathname = window.location.pathname;
	if(pathname.includes("details"))
		return "style=display:none";
	else
		return "";
		
	
}

function loadEmpTable(source){
	var content = "";
	$("#employeeTable").empty();
	var edit=hideedit();
	for ( var id in source) {
		
		
		content += '<tr><td class="col-sm-1">'
				+ (parseInt(id) + parseInt(1))
				+ '</td><td class="col-sm-1">'
				+ source[id].tuples[0]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[2]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[3]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[4]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[1]
				+ '</td><td class="col-sm-1" '+edit+' ><button class="btnB" onclick="editEmployee(\''
				+ source[id].tuples[0]
				+ '\');">Edit</button> </td><td class="col-sm-1"><button class="btnA" data-toggle="modal" data-target="#myModal"  onclick="viewEmployee(\''
				+ source[id].tuples[0]
				+ '\');">View</button></td></tr>'
		
	}
	
	
	$("#employeeTable").html(content);
	
}


function loadClientTable(source){
	
	$("#clientpage").empty();
	var content = "";
	$("#clientTable").empty();
	var edit=hideedit();
	for ( var id in source) {
		console.log(id);
		content += '<tr><td class="col-sm-1">'
				+ (parseInt(id) + parseInt(1))
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[0]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[4]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[3]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[2]
				+ '</td><td class="col-sm-2">'
				+ source[id].tuples[1]
				+ '</td><td class="col-sm-0.75" '+edit+'><button class="btnB" onclick="editclient(\''
				+ source[id].tuples[0]
				+ '\');">Edit</button> </td><td class="col-sm-0.75"><button class="btnA" onclick="viewclient(\''
				+ source[id].tuples[0]
				+ '\');" data-toggle="modal" data-target="#myModal1">View</button></td></tr>'
	
	}

	
	$("#clientTable").append(content);

}


function loadProjectTable(source)
{
	var content = "";
	$("#projecttable").empty();
	var edit=hideedit();

	for ( var id in source) {
		content=content.concat('<tr><td class="col-sm-1">'+(parseInt(id)+1)+'</td>');
		content=content.concat('<td class="col-sm-3">'+source[id].tuples[0]+'</td>');
		content=content.concat('<td class="col-sm-3">'+source[id].tuples[1]+'</td>');
		content=content.concat('<td class="col-sm-3">'+source[id].tuples[2]+'</td>');
		content=content.concat('<td class="col-sm-3">'+source[id].tuples[3]+'</td>');
		content=content.concat('<td class="col-sm-1" '+edit+'><button class="btnB" onclick="editproject(\''+source[id].tuples[0]+'\');">Edit</button> </td>');
		content=content.concat('<td class="col-sm-1"><button class="btnA" data-toggle="modal" data-target="#projectModal" onclick="viewproject(\''+source[id].tuples[0]+'\');">View</button></td><tr>');
	}

	$("#projecttable").html(content);


}

