package net.fnsco.trading.service.third.ticket.dto;

import net.fnsco.core.base.DTO;

public class Tickets extends DTO {
    private String ticket_no;
    private String passengername;
    private String passporttypeseid;
    private String passportseno;

    /**
     * ticket_no
     *
     * @return  the ticket_no
     * @since   CodingExample Ver 1.0
    */

    public String getTicket_no() {
        return ticket_no;
    }

    /**
     * ticket_no
     *
     * @param   ticket_no    the ticket_no to set
     * @since   CodingExample Ver 1.0
     */

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    /**
     * passengername
     *
     * @return  the passengername
     * @since   CodingExample Ver 1.0
    */

    public String getPassengername() {
        return passengername;
    }

    /**
     * passengername
     *
     * @param   passengername    the passengername to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengername(String passengername) {
        this.passengername = passengername;
    }

    /**
     * passporttypeseid
     *
     * @return  the passporttypeseid
     * @since   CodingExample Ver 1.0
    */

    public String getPassporttypeseid() {
        return passporttypeseid;
    }

    /**
     * passporttypeseid
     *
     * @param   passporttypeseid    the passporttypeseid to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassporttypeseid(String passporttypeseid) {
        this.passporttypeseid = passporttypeseid;
    }

    /**
     * passportseno
     *
     * @return  the passportseno
     * @since   CodingExample Ver 1.0
    */

    public String getPassportseno() {
        return passportseno;
    }

    /**
     * passportseno
     *
     * @param   passportseno    the passportseno to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassportseno(String passportseno) {
        this.passportseno = passportseno;
    }

}
