// 全局变量a和b，分别获取用户框和密码框的value值
var a = document.getElementsByTagName("input")[0];
var b = document.getElementsByTagName("input")[1];
var c = document.getElementsByTagName("input")[2];
//用户框失去焦点后验证value值
a.onblur = function() {
    if (!a.value) { //用户框value值为空
        if(document.getElementById("remind_1_1"))
            {
                document.getElementById("remind_1_1").innerHTML = "请输入用户名！";
            }else
            {
                document.getElementById("remind_1_2").innerHTML = "请输入用户名！";
            }
        document.getElementById("change_margin_1").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_2").style.marginTop = 2 + "px";
    } else { //密码框value值不为空
        var reg = /^[A-Za-z][A-Za-z1-9]{2,11}$/;                      //用户名3-12位，字母开头，字母和数字的任意组合
        isok= reg.test(a.value);
        if (!isok) {
            if(document.getElementById("remind_1_1"))
            {
                document.getElementById("remind_1_1").innerHTML = "";
                document.getElementById("remind_1_1").innerHTML = "用户名应为字母开头 + 数字/字母/下划线）";  //注册界面用户名提醒
            }else
            {
                document.getElementById("remind_1_2").innerHTML = "";
                document.getElementById("remind_1_2").innerHTML = "用户名格式输入错误！";                      //登录界面用户名提醒
            }
            document.getElementById("change_margin_1").style.marginBottom = 1 + "px";
            document.getElementById("change_margin_2").style.marginTop = 2 + "px";
        }
       
    }
}

//密码框失去焦点后验证value值
b.onblur = function() {
    if (!b.value) { //密码框value值为空
        if(document.getElementById("remind_2_1"))
            {
                document.getElementById("remind_2_1").innerHTML = "请输入密码！";
            }else
            {
                document.getElementById("remind_2_2").innerHTML = "请输入密码！";
            }
        document.getElementById("change_margin_2").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_3").style.marginTop = 2 + "px";
    } else { //密码框value值不为空
       
        var reg = /^[a-zA-Z0-9]{6,12}$/;                                 //密码6-12位，字母和数字的任意组合
        isok= reg.test(b.value);
        if (!isok) {
            if(document.getElementById("remind_2_1"))
            {
                document.getElementById("remind_2_1").innerHTML = "";
                document.getElementById("remind_2_1").innerHTML = "密码应为6-12位字母数字的任意组合！";  //注册界面密码提醒
            }else
            {
                document.getElementById("remind_2_2").innerHTML = "";
                document.getElementById("remind_2_2").innerHTML = "密码输入错误！";                      //登录界面密码提醒
            }
            document.getElementById("change_margin_2").style.marginBottom = 1 + "px";
            document.getElementById("change_margin_3").style.marginTop = 2 + "px";
        }
    }
}

//邮箱框失去焦点后验证value值
c.onblur = function() {
    if (!c.value) { //邮箱框value值为空
        document.getElementById("remind_3").innerHTML = "请输入邮箱！";
        document.getElementById("change_margin_3").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_4").style.marginTop = 2 + "px";
    } else { //邮箱框value值不为空
        document.getElementById("remind_3").innerHTML = "";
        //document.getElementById("change_margin_3").style.marginBottom = 19 + "px";
        //document.getElementById("change_margin_4").style.marginTop = 19 + "px";
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        isok= reg.test(c.value);
        if (!isok) {
            document.getElementById("remind_3").innerHTML = "邮箱格式不正确！";
            document.getElementById("change_margin_3").style.marginBottom = 1 + "px";
            document.getElementById("change_margin_4").style.marginTop = 2 + "px";
        }

    }
}
//用户框获得焦点的隐藏提醒
a.onfocus = function() {
    if(document.getElementById("remind_1_1"))
            {
                 document.getElementById("remind_1_1").innerHTML = "";
            }else
            {
                document.getElementById("remind_1_2").innerHTML = "";
            }
    document.getElementById("change_margin_1").style.marginBottom = 19 + "px";
}

//密码框获得焦点的隐藏提醒
b.onfocus = function() {
     if(document.getElementById("remind_2_1"))
            {
                 document.getElementById("remind_2_1").innerHTML = "";
            }else
            {
                document.getElementById("remind_2_2").innerHTML = "";
            }
    document.getElementById("change_margin_2").style.marginBottom = 19 + "px";
    document.getElementById("change_margin_3").style.marginTop = 19 + "px";
}

//邮箱框获得焦点的隐藏提醒
c.onfocus = function() {
    document.getElementById("remind_3").innerHTML = "";
    document.getElementById("change_margin_3").style.marginBottom = 19 + "px";
    document.getElementById("change_margin_4").style.marginTop = 19 + "px";
}

//若输入框为空，阻止表单的提交
/*function submitTest() {
    if (!a.value && !b) { //用户框value值和密码框value值都为空
        document.getElementById("remind_1").innerHTML = "请输入用户名！";
        document.getElementById("change_margin_1").style.marginBottom = 1 + "px";
        document.getElementById("remind_2").innerHTML = "请输入密码！";
        document.getElementById("change_margin_2").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_3").style.marginTop = 2 + "px";
        return false; //只有返回true表单才会提交
    } else if (!a) { //用户框value值为空
        document.getElementById("remind_1").innerHTML = "请输入用户名！";
        document.getElementById("change_margin_1").style.marginBottom = 1 + "px";
        return false;
    } else if (!b) { //密码框value值为空
        document.getElementById("remind_2").innerHTML = "请输入密码！";
        document.getElementById("change_margin_2").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_3").style.marginTop = 2 + "px";
        return false;
    } else if (!b) { //邮箱框value值为空
        document.getElementById("remind_3").innerHTML = "请输入邮箱！";
        document.getElementById("change_margin_3").style.marginBottom = 1 + "px";
        document.getElementById("change_margin_4").style.marginTop = 2 + "px";
        return false;
    }
}
*/