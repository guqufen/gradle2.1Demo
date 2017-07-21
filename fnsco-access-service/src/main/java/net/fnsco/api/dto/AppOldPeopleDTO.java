package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class AppOldPeopleDTO extends DTO{
    private List<AppOldListDTO> list;
    private List<AppOldListDTO> clerkList;
    public List<AppOldListDTO> getClerkList() {
        return clerkList;
    }
    public void setClerkList(List<AppOldListDTO> clerkList) {
        this.clerkList = clerkList;
    }
    public List<AppOldListDTO> getList() {
        return list;
    }
    public void setList(List<AppOldListDTO> list) {
        this.list = list;
    }
}
