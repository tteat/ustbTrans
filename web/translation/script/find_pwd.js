$('#findpwd').click(function(){
	if($('#mailbox').val() != null)
		{
    $.ajax({ 'url':'/ustbTrans/find_PWD', 
        'data':{"mailbox":$('#mailbox').val()}, 
        'success':function(data){
            switch(data.type){ 
                case 0:alert('未找到邮箱！');break; 
                case 1:
                       alert('邮件已成功发至您邮箱！');
                       window.location.href='login.html';
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
		alert("邮箱不能为空！");
		}
});