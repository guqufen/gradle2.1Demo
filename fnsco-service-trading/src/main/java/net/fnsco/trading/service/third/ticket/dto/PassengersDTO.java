package net.fnsco.trading.service.third.ticket.dto;

import net.fnsco.core.base.DTO;

public class PassengersDTO extends DTO{
    private String passengerid;         //\":1,"
    private String passengersename;     //\":\"张三\","
    private String piaotype;            //\":\"1\","
    private String piaotypename;        //\":\"成人票\","
    private String passporttypeseid;    //\":\"1\","
    private String passporttypeseidname;//\":\"二代身份证\","
    private String passportseno;        //\":\"420205199207231234\","
    private String price;               //\":"+data.get("price")+","
    private String zwcode;              //\":\"O\","
    private String zwname;              //\":\"二等座\"}]";

    /**
     * passengerid
     *
     * @return  the passengerid
     * @since   CodingExample Ver 1.0
    */

    public String getPassengerid() {
        return passengerid;
    }

    /**
     * passengerid
     *
     * @param   passengerid    the passengerid to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengerid(String passengerid) {
        this.passengerid = passengerid;
    }

    /**
     * passengersename
     *
     * @return  the passengersename
     * @since   CodingExample Ver 1.0
    */

    public String getPassengersename() {
        return passengersename;
    }

    /**
     * passengersename
     *
     * @param   passengersename    the passengersename to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengersename(String passengersename) {
        this.passengersename = passengersename;
    }

    /**
     * piaotype
     *
     * @return  the piaotype
     * @since   CodingExample Ver 1.0
    */

    public String getPiaotype() {
        return piaotype;
    }

    /**
     * piaotype
     *
     * @param   piaotype    the piaotype to set
     * @since   CodingExample Ver 1.0
     */

    public void setPiaotype(String piaotype) {
        this.piaotype = piaotype;
    }

    /**
     * piaotypename
     *
     * @return  the piaotypename
     * @since   CodingExample Ver 1.0
    */

    public String getPiaotypename() {
        return piaotypename;
    }

    /**
     * piaotypename
     *
     * @param   piaotypename    the piaotypename to set
     * @since   CodingExample Ver 1.0
     */

    public void setPiaotypename(String piaotypename) {
        this.piaotypename = piaotypename;
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
     * passporttypeseidname
     *
     * @return  the passporttypeseidname
     * @since   CodingExample Ver 1.0
    */

    public String getPassporttypeseidname() {
        return passporttypeseidname;
    }

    /**
     * passporttypeseidname
     *
     * @param   passporttypeseidname    the passporttypeseidname to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassporttypeseidname(String passporttypeseidname) {
        this.passporttypeseidname = passporttypeseidname;
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

    /**
     * price
     *
     * @return  the price
     * @since   CodingExample Ver 1.0
    */

    public String getPrice() {
        return price;
    }

    /**
     * price
     *
     * @param   price    the price to set
     * @since   CodingExample Ver 1.0
     */

    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * zwcode
     *
     * @return  the zwcode
     * @since   CodingExample Ver 1.0
    */

    public String getZwcode() {
        return zwcode;
    }

    /**
     * zwcode
     *
     * @param   zwcode    the zwcode to set
     * @since   CodingExample Ver 1.0
     */

    public void setZwcode(String zwcode) {
        this.zwcode = zwcode;
    }

    /**
     * zwname
     *
     * @return  the zwname
     * @since   CodingExample Ver 1.0
    */

    public String getZwname() {
        return zwname;
    }

    /**
     * zwname
     *
     * @param   zwname    the zwname to set
     * @since   CodingExample Ver 1.0
     */

    public void setZwname(String zwname) {
        this.zwname = zwname;
    }

}
