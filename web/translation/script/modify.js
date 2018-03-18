$('#modify').click(function(){
	var cookie;
	if($('#newpwd1').val()==0||$('#newpwd2').val()==0||$('#oldpwd').val() == 0)
		{
		alert("信息不能为空！");
		}
	else{
	if(($('#newpwd1').val() == $('#newpwd2').val()))
		{

	    	$.ajax({ 'url':'http://localhost:8080/ustbTrans/check', 
	    	    'data':{"username":"ljw","password":"123456",}, 
	    	    'success':function(data){
	    	        switch(data.type){ 
	    	            case 0:cookie = 0;
	    	            case 1:
	    	                   cookie = data.username;
	    	                  // alert(flag2);
	          
	    	        } 
	    	        
	    	    }, 
	    	    'error':function (XMLHttpRequest, textStatus, errorThrown) {
	    	     alert(textStatus);
	    	     alert(XMLHttpRequest.status);
	    	     alert(XMLHttpRequest.readyState);
	    	     alert(XMLHttpRequest.responseText);},
	    	     'type':'post',
	    	     'dataType':'json', 
	    	     'async': false
	    	 });
	
	        $.ajax({ 'url':'http://localhost:8080/ustbTrans/change_PWD', 
	            'data':{"username":cookie,"password":$('#newpwd1').val(),"oldpassword":$('#oldpwd').val()}, 
	            'success':function(data){
	                switch(data.type){ 
	                    case 0:alert('修改失败！');break; 
	                    case 1:
	                           alert('修改成功！');
	                           window.close();
	                           break;
	                } 
	            }, 
	            'error':function (XMLHttpRequest, textStatus, errorThrown) {
	             alert(textStatus);
	             alert(XMLHttpRequest.status);
	             alert(XMLHttpRequest.readyState);
	             alert(XMLHttpRequest.responseText);},
	             'type':'post',
	             'dataType':'json', 
	         });
	    }
	    else
	    {
	        alert("两次密码设置不同！");
	    }
	}
})
    	
    	
