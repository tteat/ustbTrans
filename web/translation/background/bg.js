//function getCookies(cookiename){
 //   var value = document.cookie.match(new RegExp("(^| )" + cookiename + "=([^;]*)(;|$)"));
//    return null != value ? decodeURIComponent(value[2]) : null;
//}
function call(){
	var flag2=0;
$.ajax({ 'url':'http://localhost:8080/check',
    'data':{"username":"ljw","password":"123456",}, 
    'success':function(data){
        switch(data.type){ 
            case 0:flag2 = 0;
            case 1:
                   flag2 = data.username;
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
return flag2;

}

function logout(cookie){
	var flag3 = 0;
	$.ajax({ 
	                'url':'http://localhost:8080/logout',
	                'data':{"username":cookie}, 
	                'success':function(data){
	                    switch(data.type){ 
	                        case 0: break; 
	                        case 1: flag3 = 1;
	                                
	                                break;
	                            
	                }}, 
	                'error':function (XMLHttpRequest, textStatus, errorThrown) {
	                    alert(textStatus);
	                    alert(XMLHttpRequest.status);
	                    alert(XMLHttpRequest.readyState);
	                    alert(XMLHttpRequest.responseText);},
	                    'type':'post',
	                    'dataType':'json', 
	                    'async': false
	                });
	return flag3;
	}

	function callwordbook(cookie){
		var flag4 = 0;
	$.ajax({ 
	                'url':'http://localhost:8080/wordbook',
	                'data':{"username":cookie}, 
	                'success':function(data){
	                    switch(data.type){ 
	                        case 0: break; 
	                        case 1: flag4 = data.wordlist; break;
	                        }
	                }, 
	                'error':function (XMLHttpRequest, textStatus, errorThrown) {
	                    alert(textStatus);
	                    alert(XMLHttpRequest.status);
	                    alert(XMLHttpRequest.readyState);
	                    alert(XMLHttpRequest.responseText);},
	                    'type':'get',
	                    'dataType':'json', 
	                    'async': false
	                });
	return flag4;
	}

	function delwordbook(cookie,word){
	$.ajax({ 
	                'url':'http://localhost:8080/del_wordbook',
	                'data':{"username":cookie,"word":word}, 
	                'success':function(data){
	                    switch(data.type){ 
	                        case 0:break; 
	                        case 1:break;
	                    }
	                }, 
	                'error':function (XMLHttpRequest, textStatus, errorThrown) {
	                    alert(textStatus);
	                    alert(XMLHttpRequest.status);
	                    alert(XMLHttpRequest.readyState);
	                    alert(XMLHttpRequest.responseText);},
	                    'type':'get',
	                    'dataType':'json', 
	                    'async': false
	                });
	}


	function callapi(word){
	    var trans = "";
	    $.ajax({ 
	        'url':'http://fanyi.youdao.com/openapi.do', 
	        'data':{"keyfrom":"fadabvaa","key":522071532,"type":"data","doctype":"json","version":"1.1","q":word}, 
	        'success':function(data){
	 
	             if(data == null)
	             {
	                trans = "查询失败！";
	             }
	             else
	             {
	                var content = data.basic.explains;
	                for(j = 0;j<content.length;j++)
	                {
	                    trans = trans + content[j];
	                }
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
	    return trans;
	}
