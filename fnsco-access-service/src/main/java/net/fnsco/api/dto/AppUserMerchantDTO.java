package net.fnsco.api.dto;

import java.util.List;

import net.fnsco.core.base.DTO;

public class AppUserMerchantDTO extends DTO{
    private String roleId;
    private String innerCode;
    private Integer id;
    private List<BandListDTO> bandListDTO;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getInnerCode() {
        return innerCode;
    }
    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode;
    }
    public List<BandListDTO> getBandListDTO() {
        return bandListDTO;
    }
    public void setBandListDTO(List<BandListDTO> bandListDTO) {
        this.bandListDTO = bandListDTO;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
}
