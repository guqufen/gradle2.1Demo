package net.fnsco.order.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class AppOldPeopleDTO extends DTO{
    private List<AppOldListDTO> list;
    private List<AppOldListDTO> clerk;
    public List<AppOldListDTO> getClerk() {
        return clerk;
    }
    public void setClerk(List<AppOldListDTO> clerk) {
        this.clerk = clerk;
    }
    public List<AppOldListDTO> getList() {
        return list;
    }
    public void setList(List<AppOldListDTO> list) {
        this.list = list;
    }
}
