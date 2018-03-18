window.onload = function () {
    var hh = document.getElementsByTagName("tr");
    var aa;
    var vo_no;
    var l2;
    var count = 0;
    var Word_list = [];
    var a = document.getElementById('login');
    var cookie = null;
    // var bgPage = chrome.extension.getBackgroundPage();
    // cookie = bgPage.call();
    // if (cookie && a.innerText == "登录") {
    //     a.innerText = "修改密码";
    //     var logout = document.createElement("div");
    //     var reforeNode = document.getElementById("wordbook");
    //     logout.id = "logout";
    //     logout.className = "item";
    //     logout.innerHTML = "登出";
    //     document.body.insertBefore(logout, reforeNode.nextSibling);
    // }


    //登录成功后改变popup的内容


    $('#login').on('click', function (event) {


        if (a.innerText == "登录") {

            window.open("http://localhost:8080/translation/html/login.html");

        }
        else {
            window.open("http://localhost:8080/translation/html/modify.html");
        }
    });

    var b = document.getElementById('logout');

    b.onclick = function () {
        var flag = bgPage.logout(cookie);
        if (flag == 1) {
            a.innerText == "登录";
            b.style.display = "none";

        }
    }


    function addRow(word, trans, i) {
        var tableObj = document.getElementById('tb_wordbook');
        var rowNums = tableObj.rows.length;
        //var rowObj = tableObj.insertRow(rowNums);

        tableObj.rows[i].cells[0].innerHTML = i;

        // var cellObj1 = rowObj.insertCell(1);
        tableObj.rows[i].cells[1].innerHTML = word;

        //var cellObj2 = rowObj.insertCell(2);
        tableObj.rows[i].cells[2].innerHTML = trans;

        //var cellObj3 = rowObj.insertCell(3);
        //cellObj3.innerHTML = '<a>delete</a>';
        //cellObj3.firstChild.href = "javascript:;";
        tableObj.rows[i].cells[3].firstChild.innerHTML = "delete";

    }


    jQuery(document).ready(function ($) {
        //打开窗口
        $('#wordbook').on('click', function (event) {


            if (cookie)										//这里要改成if(cookie)
            {
                var flag1 = bgPage.callwordbook(cookie);
                if (flag1 == 0) {
                    //$('#tb_wordbook').innerHTML = "查询单词本失败";
                    var f = document.getElementById('tb_wordbook').lastElementChild;
                    var childs = f.childNodes;
                    if (childs.length > 1) {
                        for (var i = childs.length - 1; i > 0; i--) {

                            f.removeChild(childs[i]);
                        }
                    }
                }
                else {
                    var f = document.getElementById('tb_wordbook').lastElementChild;

                    /*   if(childs.length > 1)
                     {
                     for(var i = childs.length - 1; i > 0; i--)
                     {

                     f.removeChild(childs[i]);
                     }
                     }
                     */
                    var Wordlist = flag1;
                    Word_list = flag1;
                    var ll = Wordlist.length;
                    l2 = Wordlist.length;
                    vo_no = Math.ceil(Wordlist.length / 10);
                    ;
                    if (Wordlist.length > 10) {
                        ll = 10
                    }

                    for (var i = 0; i < ll; i++) {
                        Word = Wordlist[i];
                        Trans = bgPage.callapi(Wordlist[i]);
                        addRow(Word, Trans, i + 1);
                    }
                    count++;
                    if (ll < 10) {

                        for (var k = ll + 1; k < 11; k++) {
                            hh[k].style.display = "none";
                        }
                    }

                }
                aa = document.getElementsByClassName("del_row");
                event.preventDefault();
                document.body.style.height = 600 + 'px';
                document.body.style.width = 650 + 'px';
                $('.cd-popup3').addClass('is-visible3');

            }
            else {
                window.open("http://localhost:8080/ustbTrans/translation/html/login.html");
            }
        });
        //关闭窗口
        $('.cd-popup3').on('click', function (event) {
            if ($(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup3')) {
                event.preventDefault();
                $(this).removeClass('is-visible3');
            }
        });
        //ESC关闭
        $(document).keyup(function (event) {
            if (event.which == '27') {
                $('.cd-popup3').removeClass('is-visible3');
            }
        });
    });

//删除单词
    var tableObj = document.getElementById('tb_wordbook');
    $('.del_row').on('click', function (event) {


        var rowIndex = this.parentNode.parentNode.rowIndex;//获得行下标
        var vocabu = this.parentNode.parentNode.cells[1].innerText;
        var vvono = this.parentNode.parentNode.cells[0].innerText;
        //tableObj.deleteRow(rowIndex);//删除当前行
        for (var j = rowIndex; j < 11; j++) {

            tableObj.getElementsByTagName('tr')[j].getElementsByTagName('td')[0].firstChild.innerHTML = j;
            //console.log(tableObj.rows[j].cells[0]);
            tableObj.getElementsByTagName('tr')[j].getElementsByTagName('td')[1].firstChild.innerHTML = Word_list[j];
            tableObj.getElementsByTagName('tr')[j].getElementsByTagName('td')[2].firstChild.innerHTML = bgPage.callapi(Word_list[j]);
            tableObj.getElementsByTagName('tr')[j].getElementsByTagName('td')[3].firstChild.innerHTML = "delete";
        }
        //window.location.reload();
        //parent.location.reload()
        //document.body.style.height = 600+'px';
        //document.body.style.width = 650+'px';
        //$('.cd-popup3').removeClass('is-visible3');
        //$('.cd-popup3').addClass('is-visible3');

        //告诉数据库删除了单词
        bgPage.delwordbook(cookie, vocabu);
        Word_list = bgPage.callwordbook(cookie);

    })

    $('#cd-popup-next').on('click', function (event) {
        for (var k = 1; k < 11; k++) {
            hh[k].style.display = "table-row";
        }

        if (l2 - 10 * count >= 10) {
            for (var i = 0; i < 10; i++) {
                Word = Word_list[10 * count + i];
                Trans = bgPage.callapi(Word_list[10 * count + i]);
                addRow(Word, Trans, 10 * count + i + 1);
            }
        }
        else {
            for (var i = 0; i < l2 - 10 * count; i++) {
                Word = Word_list[10 * count + i];
                Trans = bgPage.callapi(Word_list[10 * count + i]);
                addRow(Word, Trans, i + 1);
            }
        }
        if ((Word_list.length - count * 10) < 10) {

            for (var k = Word_list.length - count * 10 + 1; k < 11; k++) {
                hh[k].style.display = "none";
            }
        }
        if (count < vo_no - 1) {
            count++;
        }
        else {
            count = 0;
        }


    })
}

