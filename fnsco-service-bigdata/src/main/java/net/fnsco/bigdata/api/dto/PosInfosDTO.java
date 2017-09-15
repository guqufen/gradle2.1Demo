package net.fnsco.bigdata.api.dto;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月15日 上午10:29:29
 */

public class PosInfosDTO extends PosInfoDTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -4111046933284564504L;
    
    /**
     * 台码链接
     */
    private String taiCodeUrl;
    
    /**
     * taiCodeUrl
     *
     * @return  the taiCodeUrl
     * @since   CodingExample Ver 1.0
    */
    
    public String getTaiCodeUrl() {
        return taiCodeUrl;
    }

    /**
     * taiCodeUrl
     *
     * @param   taiCodeUrl    the taiCodeUrl to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setTaiCodeUrl(String taiCodeUrl) {
        this.taiCodeUrl = taiCodeUrl;
    }

}
