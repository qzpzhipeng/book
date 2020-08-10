/*用户注册页面*/
//页面加载完成
$(function () {
    //给注册页面添加用户名判断是否存在
    $("#username").blur(function () {
        var username = this.value;
        $.getJSON("http://localhost:8080/mybook/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
            console.log(data);
            if(data.existsUsername){
                $("span.errorMsg").text("用户名已存在！");
            }else {
                $("span.errorMsg").text("用户名可用！");
            }
        })
    })
    //给注册按钮绑击点击事件
    $("#sub_btn").click(function () {

        //验证用户名： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
        //1.获取用户输入的用户名
        var usernameText = $("#username").val();
        //2 创建正则表达式对象
        var usernamePatt = /^\w{5,12}$/;
        // 验证用户名： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
        if(!usernamePatt.test(usernameText)){
            $("span.errorMsg").text("用户名不合法");
            return false;
        }

        //验证密码： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
        //1.获取用户输入的用户密码
        var passwordText = $("#password").val();
        //2 创建正则表达式对象
        var passwordPatt = /^\w{5,12}$/;
        // 验证用户密码： 必须由字母， 数字下划线组成， 并且长度为 5 到 12 位
        if(!passwordPatt.test(passwordText)){
            $("span.errorMsg").text("用户密码不合法");
            return false;
        }

        // 验证确认密码： 和密码相同
        //1.获取用户输入的用户确认密码
        var repwdText = $("#repwd").val();
        //2.和密码相比较
        if(passwordText!=repwdText){
            $("span.errorMsg").text("用户密码和确认密码不一致");
            return false;
        }

        //验证邮箱：xxxxx@xxx.com
        //1.获取用户输入的邮箱
        var emailText = $("#email").val();
        //2 创建正则表达式对象
        var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        //3.验证邮箱是否合法
        if(!emailPatt.test(emailText)){
            $("span.errorMsg").text("用户的邮箱不合法");
            return false;
        }

        //验证码： 现在只需要验证用户已输入。 因为还没讲到服务器。 验证码生成
        //1.获取用户输入的验证码
        var codeText = $("#code").val();
        //为了防止用户加入空格，我们需要去掉空格
        /*alert("去空格前： ["+codeText+"]")*/
        codeText = $.trim(codeText);
        /*alert("去空格后： ["+codeText+"]")*/
        //2.判断是否为空
        if(codeText==null ||codeText==""){
            $("span.errorMsg").text("用户输入的验证码不能为空");
            return false;
        }
    })
})