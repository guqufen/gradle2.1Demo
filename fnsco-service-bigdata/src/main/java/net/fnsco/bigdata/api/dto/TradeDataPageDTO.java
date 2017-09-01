package net.fnsco.bigdata.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class TradeDataPageDTO<T> extends DTO {
    public TradeDataPageDTO() {

    }

    public TradeDataPageDTO(int total, List<T> list) {
        this.list = list;
        this.total = total;
    }

    private static final long serialVersionUID = -7387542509934814087L;
    private List<T>           list;
    private int               total;
    private int               currentPage;
    private int               merTotal;
    private String            count;                                   //交易总笔数
    private String            amtTot;                                  //交易总金额

    /**
     * count
     *
     * @return  the count
     * @since   CodingExample Ver 1.0
    */

    public String getCount() {
        return count;
    }

    /**
     * count
     *
     * @param   count    the count to set
     * @since   CodingExample Ver 1.0
     */

    public void setCount(String count) {
        this.count = count;
    }

    public String getAmtTot() {
        return amtTot;
    }

    public void setAmtTot(String amtTot) {
        this.amtTot = amtTot;
    }

    /**
     * merTotal
     *
     * @return  the merTotal
     * @since   CodingExample Ver 1.0
    */

    public int getMerTotal() {
        return merTotal;
    }

    /**
     * merTotal
     *
     * @param   merTotal    the merTotal to set
     * @since   CodingExample Ver 1.0
     */

    public void setMerTotal(int merTotal) {
        this.merTotal = merTotal;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}