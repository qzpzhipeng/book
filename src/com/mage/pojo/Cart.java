package com.mage.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qzp
 * @create 2020-04-19 7:43
 * @Description: 购物车对象
 */
public class Cart {
    /*为了节约资源，将两个变量改成局部变量*/
    //购物车中所有物品的总数量
//    private Integer totalCount;
    //购物车中所有物品的总金额
//    private BigDecimal totalPrice;
    //建议换成Map
    //private List<CartItem> list = new ArrayList<>();
    /**
     * @Description: 将购物车的上品项放入集合中
     *              key:代表商品项CartItem的id
     *              value:代表CartItem
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    private Map<Integer,CartItem> items = new LinkedHashMap<>();
    /**
     * @Description:  购物车添加商品
     * @Param: [cartItem]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     */
    public void addItem(CartItem cartItem){
        //1.判读购物车集合中是否有相同的物品
        CartItem item = items.get(cartItem.getId());
        if(item ==null){
            //购物车之前没有添加过此商品
            items.put(cartItem.getId(),cartItem);
        }else {
            //购物车之前已添加过此商品
            //相同的物品的数量累加
            item.setCount(item.getCount()+1);
            //相同的物品的总金额累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /**
     * @Description: 删除购物车对应的id的商品
     * @Param: [id] ：商品对应的id
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    public void deleteItem(Integer id){
        //删除购物车对应的id的商品
        items.remove(id);
    }
    /**
     * @Description: 清空购物车中所有的商品
     * @Param: []
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    public void clearItem(){
        //清空购物车中所有的商品
        items.clear();
    }
    /**
     * @Description: 修改购物车中对应的id的商品的信息
     * @Param: [id ：对应商品的id  count:对应商品的数量]
     * @return: void
     * @Author: qzp
     * @Date: 2020/4/19
     **/
    public void updateItem(Integer id,Integer count){
        //1.先查看购物车中是否有此商品。
        CartItem item = items.get(id);
        //2. 如果有， 修改商品数量， 更新总金额
        if(item !=null){
            //设置购物车此商品的数量
            item.setCount(count);
            //设置购物车此商品的数量的总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice= totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getitems() {
        return items;
    }

    public void setitems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
