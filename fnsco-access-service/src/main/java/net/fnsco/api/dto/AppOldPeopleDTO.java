package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class AppOldPeopleDTO extends DTO{
    private List<AppOldListDTO> list;
    public List<AppOldListDTO> getList() {
        return list;
    }
    public void setList(List<AppOldListDTO> list) {
        this.list = list;
    }
}
