package wzy.pojo;

import java.util.List;

/**
 * page是分页模型的对象
 * @param <T> 表示它可以对一个具体的JavaBean类进行分类
 */
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;
    // 当前页码
    private Integer pageNo;
    // 当前显示数
    private Integer pageSize = PAGE_SIZE;
    // 当前页数据
    private List<T> items;
    // 总页码
    private Integer pageTotal;
    // 总记录数
    private Integer pageTotalCount;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
//    public Page() {
//    }
//
//    public Page(Integer pageNo, Integer pageSize, List<T> items, Integer pageTotal, Integer pageTotalCount) {
//        this.pageNo = pageNo;
//        this.pageSize = pageSize;
//        this.items = items;
//        this.pageTotal = pageTotal;
//        this.pageTotalCount = pageTotalCount;
//    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo < 1){
            pageNo =1;
        }

        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }

        this.pageNo = pageNo;
    }


    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                '}';
    }
}
