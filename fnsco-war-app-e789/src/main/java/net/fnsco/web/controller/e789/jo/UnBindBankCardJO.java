package net.fnsco.web.controller.e789.jo;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.JO;

public class UnBindBankCardJO extends JO {
//    @ApiModelProperty(value = "内部商户号", name = "innerCode", example = "092916342476171")
//    private String innerCode; 
    @ApiModelProperty(value = "银行卡id", name = "bankID", example = "2")
    private String bankID;
    @ApiModelProperty(value = "用户id", name = "userId", example = "3")
    private String userId;



//    public String getInnerCode() {
//        return innerCode;
//    }
//
//    /**
//     * innerCode
//     *
//     * @param   innerCode    the innerCode to set
//     * @since   CodingExample Ver 1.0
//     */
//
//    public void setInnerCode(String innerCode) {
//        this.innerCode = innerCode;
//    }

    
}
