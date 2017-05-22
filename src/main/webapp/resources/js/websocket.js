var stompClient = null; 
        var y=0;
        
        function connect() {
            var socket = new SockJS('/version2/add');
			stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            notifications();
            stompClient.subscribe('/user/queue/qnotification', function(result){
            		showResult(JSON.parse(result.body));
            });
            stompClient.subscribe('/user/queue/notification', function(result){
        		console.log(JSON.parse(result.body));
        		loadNotification(JSON.parse(result.body));
            });
           
            });
        }
        
        function disconnect() {
            stompClient.disconnect();
            setConnected(false);
            console.log("Disconnected");
        }
        
        
      
        
        function showResult(message) {
        	y=y+message.no
            $('#calResponse').text(y);
        	
        
        
        }
        
        function notifications() {
        	$.ajax({
            	url:'notification',
            	type:'get',
            	dataType: 'json',
            	success: function(message) {
            		
            		if(message!=null){
            			y=y+message.no
                        $('#calResponse').text(y);
            		}
            	
            
            	}
            	});
        }
        
        
        
      $(document).ready(function(){
        $(window).bind('beforeunload', function(){
        	stompClient.disconnect();
            console.log("Disconnected");
        });
        
        
       
        
        
});