package net.fnsco.api.dto;

import java.util.List;

public class AppUserMerchantDTO {
    private String merName;
    private List<BandListDTO> bandListDTO;
    public List<BandListDTO> getBandListDTO() {
        return bandListDTO;
    }
    public void setBandListDTO(List<BandListDTO> bandListDTO) {
        this.bandListDTO = bandListDTO;
    }
    public String getMerName() {
        return merName;
    }
    public void setMerName(String merName) {
        this.merName = merName;
    }
    
}
