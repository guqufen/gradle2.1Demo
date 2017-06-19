package net.fnsco.core.base;

import java.util.List;

public class ResultPageDTO<T> extends DTO {
    public ResultPageDTO() {

    }

    public ResultPageDTO(int total, List<T> list) {
        this.list = list;
        this.total = total;
    }

    private static final long serialVersionUID = -7387542509934814087L;
    private List<T>           list;
    private int               total;
    private int               currentPage;

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