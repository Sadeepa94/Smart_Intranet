/**
 * 
 */

function paginate(pages,firstpage,lastpage,current){
	var cont='<ul class="pagination pagination-sm">';
	
	if(pages==1){
		return;
	}
	
	else if(pages<=10){
		if(!firstpage)
			cont+='<li class="pagebtn"><a href="#">prev</a></li>';
		
		for(var i=1;i<=pages;i++)
			{
				if(i==current)
					cont +='<li class="active pagebtn"><a href="#">'+i+'</a></li>';
				else
					cont +='<li class="pagebtn"><a href="#">'+i+'</a></li>';
			}
		
		if(!lastpage)
			cont+='<li class="pagebtn"><a href="#">next</a></li>';
		
		
	}
	
	else{
		
		if(!firstpage)
			cont+='<li class="pagebtn"><a href="#">prev</a></li>';
		
		for(var i=current;i<=current+4;i++)
			{
				if(i==current)
					cont +='<li class="active pagebtn"><a href="#" >'+i+'</a></li>';
				else
					cont +='<li class="pagebtn"><a href="#">'+i+'</a></li>';
			}
		
		for(var i=pages-5;i<pages;i++)
		{
				if(i<=current-4)
					continue;
				else
					cont +='<li class="pagebtn"><a href="#">'+i+'</a></li>';
			
		}
		
		if(!lastpage)
			cont+='<li class="pagebtn"><a href="#" class="cbtn">next</a></li>';
		
	}
	
	cont+='</ul>';
	return cont;

	
}