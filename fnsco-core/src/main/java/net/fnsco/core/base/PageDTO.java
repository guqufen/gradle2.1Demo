/**
 * 
 */
package net.fnsco.core.base;

/**@desc 分页器
 * @author tangliang
 * @date 2017年6月22日 上午11:59:05
 */
public class PageDTO<T> extends DTO {
    /**
     * 无参构造
     */
    public PageDTO() {
    }

    /**
     * 有参构造
     * @param currentPageNum
     * @param perPageSize
     * @param condition
     */
    public PageDTO(int currentPageNum, int perPageSize, T condition) {
        if (currentPageNum < 1) {
            this.currentPageNum = 1;
        } else {
            this.currentPageNum = currentPageNum;
        }
        this.perPageSize = perPageSize;
        this.condition = condition;
    }

    public PageDTO(int currentPageNum, int perPageSize, int totalPageNum, int totalCount, T condition) {
        if (currentPageNum < 1) {
            this.currentPageNum = 1;
        } else {
            this.currentPageNum = currentPageNum;
        }
        this.perPageSize = perPageSize;
        this.condition = condition;
    }

    /**
     * 
     */
    private static final long serialVersionUID = 580785904907206672L;

    /**
     * 当前第几页(默认第一页)
     */
    private int               currentPageNum   = 1;

    /**
     * 总页数 
     */
    private int               totalPageNum;

    /**
     * 总记录数 
     */
    private int               totalCount;

    /**
     * 每页显示的记录条数(默认10条)  
     */
    private int               perPageSize      = 10;

    /**
     * mysql查询起始行数
     */
    private int               startRow;

    /**
     * 条件查询
     */
    private T                 condition;

    public int getStartRow() {
        setStartRow((this.currentPageNum - 1) * perPageSize);
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        if (currentPageNum < 1) {
            this.currentPageNum = 1;
        } else {
            this.currentPageNum = currentPageNum;
        }
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPerPageSize() {
        return perPageSize;
    }

    public void setPerPageSize(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

}
