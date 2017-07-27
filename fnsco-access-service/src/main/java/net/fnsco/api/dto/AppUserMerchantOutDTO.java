package net.fnsco.api.dto;

import java.util.Date;
import java.util.List;

import net.fnsco.core.base.DTO;

public class AppUserMerchantOutDTO extends DTO{
    private String innerCode;
    private String merName;
    private List<BandListDTO> bandListDTO;
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
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
    
}
