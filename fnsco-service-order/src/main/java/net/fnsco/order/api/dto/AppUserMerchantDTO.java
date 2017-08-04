package net.fnsco.order.api.dto;

import java.util.Date;
import java.util.List;

import net.fnsco.core.base.DTO;

public class AppUserMerchantDTO extends DTO{
    List<AppUserMerchantDTO> array;
    private String roleId;
    private String innerCode;
    private Integer id;
    private Integer appUserId;
    private String merName;
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
    }
    private List<BandListDTO> bandListDTO;
    private Date modefyTime;
    public Date getModefyTime() {
        return modefyTime;
    }
    public void setModefyTime(Date modefyTime) {
        this.modefyTime = modefyTime;
    }
    public List<AppUserMerchantDTO> getArray() {
        return array;
    }
    public void setArray(List<AppUserMerchantDTO> array) {
        this.array = array;
    }
    public Integer getAppUserId() {
        return appUserId;
    }
    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }
   
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
