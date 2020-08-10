package com.mage.pojo;

import java.util.List;

/**
 * @author qzp
 * @create 2020-04-16 7:14
 * @Description: Page 是分页的模型对象
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private Integer pageNo;
    //每页显示的数量
    private Integer pageSize =PAGE_SIZE;
    //总页数
    private Integer pageTotal;
    //每页的实际页数据
    private List<T> items;
    //总记录数
    private Integer pageTotalCount;
    //分页的地址
    private String url;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPageNo(Integer pageNo) {
        /*设置数据边界有效检查*/
        if(pageNo<1){
            this.pageNo=1;
        }else if(pageNo>pageTotal){
            this.pageNo=pageTotal;
        }else {
            this.pageNo = pageNo;
        }
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", items=" + items +
                ", pageTotalCount=" + pageTotalCount +
                ", url='" + url + '\'' +
                '}';
    }
}
