$('documnet').ready(function(){
	$('#overviewbtn').click(function(){
		
		$('#tsperformancediv,#statuswisediv,#categorieswisediv').hide();
		$('#overviewdiv').show();
		getovarview();
		
		
		
		
		
	});
	
	
	$('#tsperformancebtn').click(function(){
		
		$('#overviewdiv,#statuswisediv,#categorieswisediv').hide();
		$('#tsperformancediv').show();
		
		
		
		$.ajax({
	    	url:'getAllTechSupporters',
	    	type:'get',
	    	dataType: 'json',
	    	success: function(data) {
	    		
	    		console.log(data);
	    		$("#tsulist").empty();
	    		$.each(data, function(index) {
	    			
					var ts='<li class="tslistli" onclick="getTSperformance(\''+data[index].tuples[0]+ '\');"><b>'+data[index].tuples[1]+' ('+data[index].tuples[2]+')</b></li>';
	    			$("#tsulist").append(ts);
					
				});
	    		
	    		
	    		
	    		
	    		
	    		
	    	}
	    	});
		
		
		
		
		
	});
	
	
$('#statusWisebtn').click(function(){
		
		$('#overviewdiv,#tsperformancediv,#categorieswisediv').hide();
		$('#statuswisediv').show();
		
		
		
		$.ajax({
	    	url:'gatoverallstatus',
	    	type:'get',
	    	dataType: 'json',
	    	success: function(data) {
	    		
	    		console.log(data);
	    		var result=[];
	    		var label=[];
	    		
	    		$.each(data, function(index) {
	    			
					result.push(data[index].tuples[1]);
					label.push(data[index].tuples[0]);
					
				});
	    		drawPiechart(label,result);

	    	}
	    	});
		
		$("form.statusdateform").on('submit',function(){
	    	var x = $("form.statusdateform").serialize();
	    	console.log(x);
	    	 
	    	
	       $.ajax({
	        	url:'getoverallstatusByDate',
	        	type:'get',
	        	data:x,
	        	dataType: 'json',
	        	success: function(data) {
	        		var result=[];
		    		var label=[];
	        		
	        		$.each(data, function(index) {
	    			
					result.push(data[index].tuples[1]);
					label.push(data[index].tuples[0]);
					
					});
	    			drawPiechart(label,result);
	        		
	        		
	        		
	        			
	        	}
	        	});
	        return false;
	    });
		
		
		
		
		
	});



$('#categoriesWisebtn').click(function(){
	
	$('#overviewdiv,#tsperformancediv,#statuswisediv').hide();
	$('#categorieswisediv').show();
	$.ajax({
    	url:'getoverallcategories',
    	type:'get',
    	dataType: 'json',
    	success: function(data) {
    		
    		console.log(data);
    		var result=[];
    		var label=[];
    		
    		$.each(data, function(index) {
    			if(data[index].tuples[0]==null)
    				data[index].tuples[0]="not assign";
				result.push(data[index].tuples[1]);
				label.push(data[index].tuples[0]);
				
			});
    		drawPiechartforCategory(label,result);

    	}
    	});
	
	$("form.categoriesdateform").on('submit',function(){
    	var x = $("form.categoriesdateform").serialize();
    	console.log(x);
    	 
    	
       $.ajax({
        	url:'getoverallcategoriesByDate',
        	type:'get',
        	data:x,
        	dataType: 'json',
        	success: function(data) {
        		var result=[];
	    		var label=[];
        		
        		$.each(data, function(index) {
    			if(data[index].tuples[0]==null)
    				data[index].tuples[0]="not assign";
				result.push(data[index].tuples[1]);
				label.push(data[index].tuples[0]);
				
				});
        		drawPiechartforCategory(label,result);
        		
        		
        		
        			
        	}
        	});
        return false;
    });
	
	

    		
    		
    		
    		
    		
			
    		
    		
    		
    		
    	
    	
	
	
	
	
	
	});
			
	
	
	
	
})




function getovarview(){
	$.ajax({
    	url:'getoverview',
    	type:'get',
    	dataType: 'json',
    	success: function(data) {
    		
    		overview(data);
    		
    		
    		
    		
    	}
    	});
}

function overview(data){
	console.log(data);
	console.log(data[0].tuples[0]);
	console.log(data.length)
	var label=[];
	var datas=[];
	var month=["January","Februay","March","April","May","June","July","August","September","Octomber","November","December"]
	
	var startYear=data[0].tuples[0];
	
	
	var currentYear=startYear;
	var j=1;
	
	for(var i=0;i<data.length;i++){
		
		console.log(i);
		if(currentYear!=data[i].tuples[0]){
			for(var k=j;k<=12;k++)
			{
				label.push(currentYear+" "+month[k-1]);
				datas.push(0);
				j=1;
				
			}
			if(++currentYear!=data[i].tuples[0])
			{
				while(currentYear!=data[i].tuples[0]){
				for(var k=1;k<=12;k++)
				{
					label.push(currentYear+" "+month[k-1]);
					datas.push(0);
					
				
				}
				currentYear++;
				}
			}
			
			else{
				currentYear=data[i].tuples[0];
				j=1;
			}
			
		}
		//console.log(j);
		for(var k=j;k<=12;k++)
			{
				j++;
				
				label.push(currentYear+" "+month[k-1]);
				if(data[i].tuples[1]==k){
					datas.push(data[i].tuples[2]);
					break;
				}
				else
					datas.push(0);
				
			}
		
	}
	var data = {
    labels: label,
    datasets: [
        {
            label: "Questions Per Month",
            fill: false,
            lineTension:0.1,
            backgroundColor: "rgba(75,192,192,0.4)",
            borderColor: "rgba(75,192,192,1)",
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: "rgba(75,192,192,1)",
            pointBackgroundColor: "#fff",
            pointBorderWidth: 1,
            pointHoverRadius: 0,
            pointHoverBackgroundColor: "rgba(75,192,192,1)",
            pointHoverBorderColor: "rgba(220,220,220,1)",
            pointHoverBorderWidth: 2,
            pointRadius: 0,
            pointHitRadius: 10,
            data: datas,
            spanGaps: false,
        }
    ]
};
	var ctx = document.getElementById("overviewcanvas");
	var myLineChart = new Chart(ctx, {
    type: 'line',
    data: data
    

	
});
	console.log(label);
	console.log(datas);
	
	
}




function getTSperformance(x){
	console.log("vvvvvvv");
	
	$.ajax({
    	url:'getTechSupportPerform/'+x,
    	type:'get',
    	dataType: 'json',
    	success: function(data) {
    		
    		console.log(data);
    		$("#tsusername").html(data[0].username);
    		$("#tsname").html(data[0].first_name+" "+data[0].last_name);
    		$("#totalq").html(data[1]);
    		$("#closedq").html(data[2]);
    		$("#inpq").html(data[3]);
    		$("#futq").html(data[4]);
    		$("#spciiali").text("");
    		$("#spciiali").text(data[0].specifications);
    		if(data[5]<0)
    			$("#overallrating").html("no ratings");
    		else
    			$("#overallrating").html(data[5]+"%");
    		
    		
    		$("#profilepic").attr("src", contexturl+data[0].profilepicture+"?"+new Date().valueOf());
    		
				
			
    		
    		
    		
    		
    		
    		
    	}
    	});
	
}


function drawPiechart(label,result)
{
	
	var ctx1 = document.getElementById("statuschart");
	var myPieChart = new Chart(ctx1,{
	    type: 'pie',
	    data:{
	    labels: label,
	    datasets: [
	        {
	            data: result,
	            backgroundColor: [
	                "#FF6384",
	                "#36A2EB",
	                "#FFCE56"
	            ],
	            hoverBackgroundColor: [
	                "#FF6389",
	                "#36A2EE",
	                "#FFCE59"
	            ]
	        }]
	},

	    options: {
	        animation:{
	            animateScale:false,
				animateRotate:true
	        }
	    }

		
		
		
		
		
		
		
	});
}


function drawPiechartforCategory(label,result)
{
	
	var ctx1 = document.getElementById("categorychart");
	var myPieChart = new Chart(ctx1,{
	    type: 'pie',
	    data:{
	    labels: label,
	    datasets: [
	        {
	            data: result,
	            backgroundColor: [
	                "#FF6384",
	                "#36A2EB",
	                "#FFCE56"
	            ],
	            hoverBackgroundColor: [
	                "#FF6389",
	                "#36A2EE",
	                "#FFCE59"
	            ]
	        }]
	},

	    options: {
	        animation:{
	            animateScale:false,
				animateRotate:true
	        }
	    }

		
		
		
		
		
		
		
	});
}


	
	