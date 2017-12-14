package net.fnsco.trading.service.third.ticket.dto;

import java.util.List;

public class SiteResultDTO {
    private String        error_code;
    private String        reason;
    private List<SiteDTO> result;

    /**
     * error_code
     *
     * @return  the error_code
     * @since   CodingExample Ver 1.0
    */

    public String getError_code() {
        return error_code;
    }

    /**
     * error_code
     *
     * @param   error_code    the error_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    /**
     * reason
     *
     * @return  the reason
     * @since   CodingExample Ver 1.0
    */

    public String getReason() {
        return reason;
    }

    /**
     * reason
     *
     * @param   reason    the reason to set
     * @since   CodingExample Ver 1.0
     */

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * result
     *
     * @return  the result
     * @since   CodingExample Ver 1.0
    */

    public List<SiteDTO> getResult() {
        return result;
    }

    /**
     * result
     *
     * @param   result    the result to set
     * @since   CodingExample Ver 1.0
     */

    public void setResult(List<SiteDTO> result) {
        this.result = result;
    }

}
