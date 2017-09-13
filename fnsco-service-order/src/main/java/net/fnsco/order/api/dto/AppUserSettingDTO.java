package net.fnsco.order.api.dto;

import net.fnsco.core.base.DTO;

/**
 * @desc  消息设置DTO
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月12日 下午3:04:53
 */

public class AppUserSettingDTO extends DTO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -8653585941499730933L;
    
    /**
     * 通知类型0:周报1:台码2:其他
     */
    private String noticeType;
    
    /**
     * 通知是否开启0:停用1:开启
     */
    private String openStatus;

    /**
     * noticeType
     *
     * @return  the noticeType
     * @since   CodingExample Ver 1.0
    */
    
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * noticeType
     *
     * @param   noticeType    the noticeType to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * openStatus
     *
     * @return  the openStatus
     * @since   CodingExample Ver 1.0
    */
    
    public String getOpenStatus() {
        return openStatus;
    }

    /**
     * openStatus
     *
     * @param   openStatus    the openStatus to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }
    
}
