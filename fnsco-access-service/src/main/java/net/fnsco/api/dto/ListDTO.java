package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class ListDTO extends DTO{
    private List<AppPeopleDTO> appPeopleDTOList;

    public List<AppPeopleDTO> getAppPeopleDTOList() {
        return appPeopleDTOList;
    }

    public void setAppPeopleDTOList(List<AppPeopleDTO> appPeopleDTOList) {
        this.appPeopleDTOList = appPeopleDTOList;
    }
}
