$(document).ready(function() {
    var max_fields      = 10; 
    var wrapper         = $(".input_fields_wrap"); 
    var add_button      = $(".add_field_button"); 
    
    
	var x=0;
    $(add_button).click(function(e){ 
        e.preventDefault();
        x = getNoOftables(); 
        console.log(x+"+++++++++++++++++++++++++");
		var y='<table><tr><td><label>module ID</label><br /><input type="text" name="modules['+x+'].moduleID" size="20"/></td>	<td ><label>module Name</label><br /><input type="text" name="modules['+x+'].moduleName" size="20"/></td>	</tr><tr ><td colspan="2"><label>Description</label><br /><textarea rows="3" cols="50" name="modules['+x+'].description" form="projectform" placeholder="Enter text here..."></textarea></td></tr></table>';
		if(x < max_fields){ 
            $(wrapper).append('<div><hr style="border-color:#195e86;"></hr>'+y+'<button class="remove_field btnA">Remove</button></div>'); 
           // x++;
        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ 
        e.preventDefault(); 
		$(this).parent('div').remove(); 
		x--;
    })
});


function getNoOftables(){
	var tables=0;
	tables=$('#modules').find('table').length;
	return tables;
}