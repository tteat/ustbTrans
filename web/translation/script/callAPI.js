function callAPI(word){
    $.ajax({ 
        'url':'http://fanyi.youdao.com/openapi.do', 
        'data':{"keyfrom":"fadabvaa","key":522071532,"type":"data","doctype":"json","version":"1.1","q":word}, 
        'success':function(data){
            var f = document.getElementById("head_API");
            var nominal = document.getElementsByClassName('nominal');
            var API = document.getElementsByClassName("span_API");

            
            $(".nominal").empty();
            $(".span_API").empty();
              
           /* var childs = f.childNodes;
            if(childs)
            {
                for(var i = childs.length - 1; i >= 0; i--) 
                { 
     
                     f.removeChild(childs[i]); 
                 }
             }
             */
             if(data == null)
             {
                f.innerText = "查询失败！";
             }
             else
             {

            var content = data.basic.explains;
            if(content.length>3)
            {
                content.length = 3;
            }
                    for(var i = 0;i < content.length;i++)
                    {
                        var strs= new Array();  
                        strs = content[i].split("."); 
                        
                        nominal[i].innerText = strs[0]+'.';
                       
                        var strs1 = new Array();
                        strs1 = strs[1].split("；");
                        if(strs1.length>2){
                            strs1.length = 2;
                        }
                        for(j = 0;j<strs1.length;j++)
                        {
                            
                            API[i*2+j].innerText = strs1[j]+'；';
                            
                        }

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
     });
}

function add_cus(word,trans){
        $.ajax({ 
                'url':'http://localhost:8080/add_cus',
                'data':{"trans":trans,"word":word}, 
                'success':function(data){
                    switch(data.type){ 
                        case 0: 
                                return false;
                                break; 
                        case 1: return true;
                        break;        
                    } 
                }, 
                'error':function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(textStatus);
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(XMLHttpRequest.responseText);},
                    'type':'get',
                    'dataType':'json', 
                });
}

function mark(word,trans,num){
        $.ajax({ 
                'url':'http://localhost:8080/mark',
                'data':{"trans":trans,"word":word,"score":num}, 
                'success':function(data){
                    switch(data.type){ 
                        case 0: 
                                break; 
                        case 1: return data.score;
                        break;        
                    } 
                }, 
                'error':function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(textStatus);
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(XMLHttpRequest.responseText);},
                    'type':'get',
                    'dataType':'json', 
                });
}
