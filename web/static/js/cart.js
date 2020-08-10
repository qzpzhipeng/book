//页面加载完成
$(function () {
    //给添加购物车绑定点击事件
    $("button.addToCart").click(function () {
        //获取加入对应商品的id
        var bookId = $(this).attr("bookId");
        // 在事件响应的 function 函数 中， 有一个 this 对象， 这个 this 对象， 是当前正在响应事件的 dom 对象
        //  @type {jQuery}
        location.href = "http://localhost:8080/mybook/cartServlet?action=addItem&id=" + bookId;
    })

    //给购车删除商品绑定点击事件
    $("a.deleteItem").click(function () {
        return confirm("你确定要删除【"+$(this).parent().parent().find("td:first").text()+"】吗？");
    })
    // 给清空购物车绑定单击事件
    $("#clearCart").click(function () {
        return confirm("你确定要清空购物车吗?");
    })

    //给购物车中的商品数量绑定单击事件
    $("input.updateCount").change(function () {
        //获取修改对应商品的id
        var bookId = $(this).attr("bookId");
        //获取修改对应的商品的名称
        var name = $(this).parent().parent().find("td:first").text();
        //获取修改对应的商品的count
        var count = this.value;
        if(confirm("你确定要将【 " + name + "】 商品修改数量为： " + count + " 吗?") ){
            location.href="http://localhost:8080/mybook/cartServlet?action=updateCount&id=" + bookId+"&count="+ count;
        }else {
            // defaultValue 属性是表单项 Dom 对象的属性。 它表示默认的 value 属性值。
            this.value = this.defaultValue;
        }
    })
})