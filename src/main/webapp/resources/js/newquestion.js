

function loadSytems(){
	var username=$.trim($("#username").text());
	loadSystemsByusername(username);
	
	
}

function loadSystemsByusername(username){
	 $.ajax({
	    	url:'loadSystems/'+username,
	    	type:'get',
	    	dataType: 'json',
	    	success: function(data) {
	    		console.log(data);
	    		$('#systemsselect').empty();
	    		$('#moduleselect').empty();
	    		
	    		
	    		
	    		$('select[name="project.projectID"]').append($('<option>', {
					value :"General",
					text : "General"
				}));
	    		
	    		$('select[name="module.moduleID"]').append($('<option>', {
					value :"General",
					text : "General"
				}));
	    		
				$.each(data, function(index) {
					$('select[name="project.projectID"]').append($('<option>', {
						value : data[index].projectID,
						text : data[index].projectName
					}));
				});
				
				
				
				
	    		
	    		$('select[name="project.projectID"]').on('click', function(){
	    			var t=$('select[name="project.projectID"]').find('option:selected').val();
	    			$.each(data, function(index) {
	    				if(data[index].projectID===t){
	    					$('select[name="module.moduleID"]').empty();
	    								
	    					$('select[name="module.moduleID"]').append($('<option>', {
	    						value :"General",
	    						text : "General"
	    					}));
	    		    		
	    					$.each(data[index].modules, function(i) {
	    						$('select[name="module.moduleID"]').append($('<option>', {
	    							value : data[index].modules[i].moduleID,
	    							text : data[index].modules[i].moduleName
	    						}));
	    					});
						
						
	    				}
					});
	    		      
	    		});


	    		
	    	}
	    });
	
	
}


$(document).ready(function(){
	$("#searchprojectbycusername").click(function(){
		
		var username=$('#clientusername').val();
		loadSystemsByusername(username);
		
		
		
		return false;
	})
	
	
})


