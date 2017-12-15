package net.fnsco.trading.service.third.ticket.dto;

import net.fnsco.core.base.DTO;

public class TicketOrderSubmitDTO extends DTO {
    private String key;
    private String user_orderid;
    private String train_date;
    private String from_station_name;
    private String from_station_code;
    private String to_station_code;
    private String to_station_name;
    private String checi;
    //这里注意，必须是JSONArray格式，即即使只有一个乘客也是以"[{}]"形式存在
    PassengersDTO  passengers;

    /**
     * key
     *
     * @return  the key
     * @since   CodingExample Ver 1.0
    */

    public String getKey() {
        return key;
    }

    /**
     * key
     *
     * @param   key    the key to set
     * @since   CodingExample Ver 1.0
     */

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * user_orderid
     *
     * @return  the user_orderid
     * @since   CodingExample Ver 1.0
    */

    public String getUser_orderid() {
        return user_orderid;
    }

    /**
     * user_orderid
     *
     * @param   user_orderid    the user_orderid to set
     * @since   CodingExample Ver 1.0
     */

    public void setUser_orderid(String user_orderid) {
        this.user_orderid = user_orderid;
    }

    /**
     * train_date
     *
     * @return  the train_date
     * @since   CodingExample Ver 1.0
    */

    public String getTrain_date() {
        return train_date;
    }

    /**
     * train_date
     *
     * @param   train_date    the train_date to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrain_date(String train_date) {
        this.train_date = train_date;
    }

    /**
     * from_station_name
     *
     * @return  the from_station_name
     * @since   CodingExample Ver 1.0
    */

    public String getFrom_station_name() {
        return from_station_name;
    }

    /**
     * from_station_name
     *
     * @param   from_station_name    the from_station_name to set
     * @since   CodingExample Ver 1.0
     */

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    /**
     * from_station_code
     *
     * @return  the from_station_code
     * @since   CodingExample Ver 1.0
    */

    public String getFrom_station_code() {
        return from_station_code;
    }

    /**
     * from_station_code
     *
     * @param   from_station_code    the from_station_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setFrom_station_code(String from_station_code) {
        this.from_station_code = from_station_code;
    }

    /**
     * to_station_code
     *
     * @return  the to_station_code
     * @since   CodingExample Ver 1.0
    */

    public String getTo_station_code() {
        return to_station_code;
    }

    /**
     * to_station_code
     *
     * @param   to_station_code    the to_station_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setTo_station_code(String to_station_code) {
        this.to_station_code = to_station_code;
    }

    /**
     * to_station_name
     *
     * @return  the to_station_name
     * @since   CodingExample Ver 1.0
    */

    public String getTo_station_name() {
        return to_station_name;
    }

    /**
     * to_station_name
     *
     * @param   to_station_name    the to_station_name to set
     * @since   CodingExample Ver 1.0
     */

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    /**
     * checi
     *
     * @return  the checi
     * @since   CodingExample Ver 1.0
    */

    public String getCheci() {
        return checi;
    }

    /**
     * checi
     *
     * @param   checi    the checi to set
     * @since   CodingExample Ver 1.0
     */

    public void setCheci(String checi) {
        this.checi = checi;
    }

    /**
     * passengers
     *
     * @return  the passengers
     * @since   CodingExample Ver 1.0
    */

    public PassengersDTO getPassengers() {
        return passengers;
    }

    /**
     * passengers
     *
     * @param   passengers    the passengers to set
     * @since   CodingExample Ver 1.0
     */

    public void setPassengers(PassengersDTO passengers) {
        this.passengers = passengers;
    }

}
