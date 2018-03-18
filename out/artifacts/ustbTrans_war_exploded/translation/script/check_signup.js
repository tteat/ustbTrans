$('#signup').click(function(){
	if($('#user').val()&&$('#password').val()&&$('#mailbox').val() != null)
		{
    $.ajax({ 'url':'http://localhost:8080/sign_up',
        'data':{"username":$('#user').val(),"password":$('#password').val(),"mailbox":$('#mailbox').val()}, 
        'success':function(data){
            switch(data.type){ 
                case 0:alert('邮箱或用户名已存在！');break; 
                case 1:
                       alert('注册成功！');
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
		alert("信息不能为空！");
		}
});