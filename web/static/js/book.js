/*页面加载完成*/
$(function () {
    //给book_manager.jsp页面中的删除操作添加确认信息
    $("a.deleteclass").click(function () {
        // 给删除的 a 标签绑定单击事件， 用于删除的确认提示操作
        /**
         * confirm 是确认提示框函数
         * 参数是它的提示内容
         * 它有两个按钮， 一个确认， 一个是取消。
         * 返回 true 表示点击了， 确认， 返回 false 表示点击取消。
         */
       return  confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"吗？");
    });

    //跳到指定页面
    $("#searchPageBtn").click(function () {
        var pageNo = $("#pn_input").val();
        pageNo = parseInt(pageNo);
        /*alert(typeof pageNo)*/
        // javaScript 语言中提供了一个 location 地址栏对象
        // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
        // href 属性可读， 可写
        //location.href="https://www.baidu.com";
        var href = window.location.href;
        var basePath = href.split("&")[0];
        //获取总页数
        var total = document.getElementById('value').value;
        total = parseInt(total);
        /*alert(typeof total)*/
        if(pageNo > total){
            location.href=basePath+"&pageNo="+total;
        }else if(pageNo<1){
            location.href=basePath+"&pageNo=1";
        }else {
            location.href=basePath+"&pageNo="+pageNo;
        }
    })
})
