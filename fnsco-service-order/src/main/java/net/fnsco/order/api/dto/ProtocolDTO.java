package net.fnsco.order.api.dto;

import io.swagger.annotations.ApiModelProperty;

public class ProtocolDTO {
	@ApiModelProperty(value="协议号",example="1")
    private int protocol;

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}
