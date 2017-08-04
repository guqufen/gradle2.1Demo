package net.fnsco.order.controller.app.jo;

import net.fnsco.core.base.JO;

/**
 * @desc 返回消息数量
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月13日 下午5:14:27
 */

public class PushMsgInfoJO extends JO {

    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since Ver 1.1
     */
    
    private static final long serialVersionUID = -5943479685742314595L;
    /**
     * 未读消息条数
     */
    private Integer unReadCount;
    
    /**
     * 最后一次阅读时间
     */
    private String lastReadTimeStr;

    /**
     * unReadCount
     *
     * @return  the unReadCount
     * @since   CodingExample Ver 1.0
    */
    
    public Integer getUnReadCount() {
        return unReadCount;
    }

    /**
     * unReadCount
     *
     * @param   unReadCount    the unReadCount to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    /**
     * lastReadTimeStr
     *
     * @return  the lastReadTimeStr
     * @since   CodingExample Ver 1.0
    */
    
    public String getLastReadTimeStr() {
        return lastReadTimeStr;
    }

    /**
     * lastReadTimeStr
     *
     * @param   lastReadTimeStr    the lastReadTimeStr to set
     * @since   CodingExample Ver 1.0
     */
    
    public void setLastReadTimeStr(String lastReadTimeStr) {
        this.lastReadTimeStr = lastReadTimeStr;
    }
    
}
