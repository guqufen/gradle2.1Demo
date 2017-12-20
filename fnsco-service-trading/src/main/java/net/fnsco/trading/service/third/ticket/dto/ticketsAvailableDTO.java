package net.fnsco.trading.service.third.ticket.dto;

import net.fnsco.core.base.DTO;

//"sale_date_time": "1230", /*车票开售时间*/
//"can_buy_now": "Y",   /*当前是否可以接受预定*/
// "arrive_days": "0",  /*列车从出发站到达目的站的运行天数 0:当日到达1,: 次日
//   到达,2:三日到达,3:四日到达,依此类推*/
//"train_start_date": "20150711", /*列车从始发站出发的日期*/
//"train_code": "G101", /*车次*/
//"access_byidcard": "1", /*是否可凭二代身份证直接进出站*/
//"train_no": "240000G1010A", /*列车号*/   
//"train_type": "G", /*列车类型*/
//"from_station_code": "VNP", /*出发车站简码*/
//"from_station_name": "北京南", /*出发车站名*/
//"to_station_code": "AOH", /*到达车站简码*/
//"to_station_name": "上海虹桥", /*到达车站名*/
//"start_station_name": "北京南", /*始发站名*/
//"end_station_name": "上海虹桥", /*终到站名*/
//"start_time": "07:00", /*当前站出发时刻*/
//"arrive_time": "12:37", /*到达时刻*/
//"run_time": "05:37", /*历时（出发站到目的站）*/
//"run_time_minute": "337", /*历时分钟合计*/
//"gjrw_num": "--", /*高级软卧余票数量*/
//"gjrws_price":0, /* 高级软卧（上）票价*/
//"gjrw_price": 0, /*高级软卧票价*/
//"qtxb_num": "--", /*其他席别余票数量*/
//"qtxb_price": 0, /*其他席别对应的票价*/
//"rw_num": "--", /*软卧余票数量*/
//"rw_price": 0, /*软卧（上）票价*/
//"rwx_price":10, /*软卧(下)票价*/
//"rz_num": "--", /*软座的余票数量*/
//"rz_price": 0, /*软座的票价*/   
//"swz_num": "15", /*商务座余票数量*/    
//"swz_price": 1748, /*商务座票价*/
//"tdz_num": "--", /*特等座的余票数量*/
//"tdz_price": 0,  /*特等座票价*/
//"wz_num": "--", /*无座的余票数量*/       
//"wz_price": 0, /*无座票价*/
//"dw_num":"8",/*动卧的余票数量*/
//"dw_price":"",/*动卧(上)票价[12306新增]*/
//"dwx_price":"",/*动卧(下)票价[12306新增]*/
//"yw_num": "--", /*硬卧的余票数量*/
//"yw_price": 0, /*硬卧(上)票价（因为硬卧上中下铺价格不同，这个价格一般是均价），
//请务必阅读 http://code.juhe.cn/docs/201 中第15条*/     
//" ywz_price":90,/*硬卧(中)票价*/        
//"ywx_price":86.5,/*硬卧(下)票价*/        
//"yz_num": "--", /*硬座的余票数量*/
//"yz_price": 0, /*硬座票价*/   
//"edz_num": "900", /*二等座的余票数量*/
//"edz_price": 553, /*二等座票价*/ 
//"ydz_num": "54", /*一等座余票数量*/
//"ydz_price": 933, /*一等座票价*/
//"distance":0, /*里程数*/
//{"rwx_price":0,"end_station_name":"北京南","swz_price":1701,"swz_num":"11",
//"to_station_name":"北京南","ydz_num":"150","yz_num":"--","rw_num":"--",
//"arrive_days":"0","rz_num":"--","access_byidcard":"1","yz_price":0,
//"ywz_price":0,"sale_date_time":"1130","dw_price":0,"from_station_code":"HGH",
//"rz_price":0,"gjrw_num":"--","to_station_code":"VNP","ydz_price":907,
//"wz_price":0,"tdz_price":0,"run_time":"05:52","dwx_price":0,"yw_num":"--",
//"distance":0,"edz_price":538.5,"qtxb_price":0,"can_buy_now":"Y",
//"yw_price":0,"train_type":"G","rw_price":0,"train_code":"G58",
//"train_no":"5e00000G5832","from_station_name":"杭州东",
//"run_time_minute":"352","ywx_price":0,"dw_num":"--","gjrws_price":0,
//"arrive_time":"14:44","start_station_name":"宁波","start_time":"08:52",
//"wz_num":"--","edz_num":"130","qtxb_num":"--",
//"train_start_date":"20171226","gjrw_price":0,"tdz_num":"--"}
public class ticketsAvailableDTO extends DTO {
    private String rwx_price;
    private String end_station_name;
    private String swz_price;
    private String swz_num;
    private String to_station_name;
    private String ydz_num;
    private String yz_num;
    private String rw_num;
    private String arrive_days;
    private String rz_num;
    private String access_byidcard;
    private String yz_price;
    private String ywz_price;
    private String sale_date_time;
    private String dw_price;
    private String from_station_code;
    private String rz_price;
    private String gjrw_num;
    private String to_station_code;
    private String ydz_price;
    private String wz_price;
    private String tdz_price;
    private String run_time;
    private String dwx_price;
    private String yw_num;
    private String distance;
    private String edz_price;
    private String qtxb_price;
    private String can_buy_now;
    private String yw_price;
    private String train_type;
    private String rw_price;
    private String train_code;
    private String train_no;
    private String from_station_name;
    private String run_time_minute;
    private String ywx_price;
    private String dw_num;
    private String gjrws_price;
    private String arrive_time;
    private String start_station_name;
    private String start_time;
    private String wz_num;
    private String edz_num;
    private String qtxb_num;
    private String train_start_date;
    private String gjrw_price;
    private String tdz_num;

    /**
     * rwx_price
     *
     * @return  the rwx_price
     * @since   CodingExample Ver 1.0
    */

    public String getRwx_price() {
        return rwx_price;
    }

    /**
     * rwx_price
     *
     * @param   rwx_price    the rwx_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setRwx_price(String rwx_price) {
        this.rwx_price = rwx_price;
    }

    /**
     * end_station_name
     *
     * @return  the end_station_name
     * @since   CodingExample Ver 1.0
    */

    public String getEnd_station_name() {
        return end_station_name;
    }

    /**
     * end_station_name
     *
     * @param   end_station_name    the end_station_name to set
     * @since   CodingExample Ver 1.0
     */

    public void setEnd_station_name(String end_station_name) {
        this.end_station_name = end_station_name;
    }

    /**
     * swz_price
     *
     * @return  the swz_price
     * @since   CodingExample Ver 1.0
    */

    public String getSwz_price() {
        return swz_price;
    }

    /**
     * swz_price
     *
     * @param   swz_price    the swz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setSwz_price(String swz_price) {
        this.swz_price = swz_price;
    }

    /**
     * swz_num
     *
     * @return  the swz_num
     * @since   CodingExample Ver 1.0
    */

    public String getSwz_num() {
        return swz_num;
    }

    /**
     * swz_num
     *
     * @param   swz_num    the swz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
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
     * ydz_num
     *
     * @return  the ydz_num
     * @since   CodingExample Ver 1.0
    */

    public String getYdz_num() {
        return ydz_num;
    }

    /**
     * ydz_num
     *
     * @param   ydz_num    the ydz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setYdz_num(String ydz_num) {
        this.ydz_num = ydz_num;
    }

    /**
     * yz_num
     *
     * @return  the yz_num
     * @since   CodingExample Ver 1.0
    */

    public String getYz_num() {
        return yz_num;
    }

    /**
     * yz_num
     *
     * @param   yz_num    the yz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    /**
     * rw_num
     *
     * @return  the rw_num
     * @since   CodingExample Ver 1.0
    */

    public String getRw_num() {
        return rw_num;
    }

    /**
     * rw_num
     *
     * @param   rw_num    the rw_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    /**
     * arrive_days
     *
     * @return  the arrive_days
     * @since   CodingExample Ver 1.0
    */

    public String getArrive_days() {
        return arrive_days;
    }

    /**
     * arrive_days
     *
     * @param   arrive_days    the arrive_days to set
     * @since   CodingExample Ver 1.0
     */

    public void setArrive_days(String arrive_days) {
        this.arrive_days = arrive_days;
    }

    /**
     * rz_num
     *
     * @return  the rz_num
     * @since   CodingExample Ver 1.0
    */

    public String getRz_num() {
        return rz_num;
    }

    /**
     * rz_num
     *
     * @param   rz_num    the rz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    /**
     * access_byidcard
     *
     * @return  the access_byidcard
     * @since   CodingExample Ver 1.0
    */

    public String getAccess_byidcard() {
        return access_byidcard;
    }

    /**
     * access_byidcard
     *
     * @param   access_byidcard    the access_byidcard to set
     * @since   CodingExample Ver 1.0
     */

    public void setAccess_byidcard(String access_byidcard) {
        this.access_byidcard = access_byidcard;
    }

    /**
     * yz_price
     *
     * @return  the yz_price
     * @since   CodingExample Ver 1.0
    */

    public String getYz_price() {
        return yz_price;
    }

    /**
     * yz_price
     *
     * @param   yz_price    the yz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setYz_price(String yz_price) {
        this.yz_price = yz_price;
    }

    /**
     * ywz_price
     *
     * @return  the ywz_price
     * @since   CodingExample Ver 1.0
    */

    public String getYwz_price() {
        return ywz_price;
    }

    /**
     * ywz_price
     *
     * @param   ywz_price    the ywz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setYwz_price(String ywz_price) {
        this.ywz_price = ywz_price;
    }

    /**
     * sale_date_time
     *
     * @return  the sale_date_time
     * @since   CodingExample Ver 1.0
    */

    public String getSale_date_time() {
        return sale_date_time;
    }

    /**
     * sale_date_time
     *
     * @param   sale_date_time    the sale_date_time to set
     * @since   CodingExample Ver 1.0
     */

    public void setSale_date_time(String sale_date_time) {
        this.sale_date_time = sale_date_time;
    }

    /**
     * dw_price
     *
     * @return  the dw_price
     * @since   CodingExample Ver 1.0
    */

    public String getDw_price() {
        return dw_price;
    }

    /**
     * dw_price
     *
     * @param   dw_price    the dw_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setDw_price(String dw_price) {
        this.dw_price = dw_price;
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
     * rz_price
     *
     * @return  the rz_price
     * @since   CodingExample Ver 1.0
    */

    public String getRz_price() {
        return rz_price;
    }

    /**
     * rz_price
     *
     * @param   rz_price    the rz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setRz_price(String rz_price) {
        this.rz_price = rz_price;
    }

    /**
     * gjrw_num
     *
     * @return  the gjrw_num
     * @since   CodingExample Ver 1.0
    */

    public String getGjrw_num() {
        return gjrw_num;
    }

    /**
     * gjrw_num
     *
     * @param   gjrw_num    the gjrw_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setGjrw_num(String gjrw_num) {
        this.gjrw_num = gjrw_num;
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
     * ydz_price
     *
     * @return  the ydz_price
     * @since   CodingExample Ver 1.0
    */

    public String getYdz_price() {
        return ydz_price;
    }

    /**
     * ydz_price
     *
     * @param   ydz_price    the ydz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setYdz_price(String ydz_price) {
        this.ydz_price = ydz_price;
    }

    /**
     * wz_price
     *
     * @return  the wz_price
     * @since   CodingExample Ver 1.0
    */

    public String getWz_price() {
        return wz_price;
    }

    /**
     * wz_price
     *
     * @param   wz_price    the wz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setWz_price(String wz_price) {
        this.wz_price = wz_price;
    }

    /**
     * tdz_price
     *
     * @return  the tdz_price
     * @since   CodingExample Ver 1.0
    */

    public String getTdz_price() {
        return tdz_price;
    }

    /**
     * tdz_price
     *
     * @param   tdz_price    the tdz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setTdz_price(String tdz_price) {
        this.tdz_price = tdz_price;
    }

    /**
     * run_time
     *
     * @return  the run_time
     * @since   CodingExample Ver 1.0
    */

    public String getRun_time() {
        return run_time;
    }

    /**
     * run_time
     *
     * @param   run_time    the run_time to set
     * @since   CodingExample Ver 1.0
     */

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }

    /**
     * dwx_price
     *
     * @return  the dwx_price
     * @since   CodingExample Ver 1.0
    */

    public String getDwx_price() {
        return dwx_price;
    }

    /**
     * dwx_price
     *
     * @param   dwx_price    the dwx_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setDwx_price(String dwx_price) {
        this.dwx_price = dwx_price;
    }

    /**
     * yw_num
     *
     * @return  the yw_num
     * @since   CodingExample Ver 1.0
    */

    public String getYw_num() {
        return yw_num;
    }

    /**
     * yw_num
     *
     * @param   yw_num    the yw_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    /**
     * distance
     *
     * @return  the distance
     * @since   CodingExample Ver 1.0
    */

    public String getDistance() {
        return distance;
    }

    /**
     * distance
     *
     * @param   distance    the distance to set
     * @since   CodingExample Ver 1.0
     */

    public void setDistance(String distance) {
        this.distance = distance;
    }

    /**
     * edz_price
     *
     * @return  the edz_price
     * @since   CodingExample Ver 1.0
    */

    public String getEdz_price() {
        return edz_price;
    }

    /**
     * edz_price
     *
     * @param   edz_price    the edz_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setEdz_price(String edz_price) {
        this.edz_price = edz_price;
    }

    /**
     * qtxb_price
     *
     * @return  the qtxb_price
     * @since   CodingExample Ver 1.0
    */

    public String getQtxb_price() {
        return qtxb_price;
    }

    /**
     * qtxb_price
     *
     * @param   qtxb_price    the qtxb_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setQtxb_price(String qtxb_price) {
        this.qtxb_price = qtxb_price;
    }

    /**
     * can_buy_now
     *
     * @return  the can_buy_now
     * @since   CodingExample Ver 1.0
    */

    public String getCan_buy_now() {
        return can_buy_now;
    }

    /**
     * can_buy_now
     *
     * @param   can_buy_now    the can_buy_now to set
     * @since   CodingExample Ver 1.0
     */

    public void setCan_buy_now(String can_buy_now) {
        this.can_buy_now = can_buy_now;
    }

    /**
     * yw_price
     *
     * @return  the yw_price
     * @since   CodingExample Ver 1.0
    */

    public String getYw_price() {
        return yw_price;
    }

    /**
     * yw_price
     *
     * @param   yw_price    the yw_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setYw_price(String yw_price) {
        this.yw_price = yw_price;
    }

    /**
     * train_type
     *
     * @return  the train_type
     * @since   CodingExample Ver 1.0
    */

    public String getTrain_type() {
        return train_type;
    }

    /**
     * train_type
     *
     * @param   train_type    the train_type to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    /**
     * rw_price
     *
     * @return  the rw_price
     * @since   CodingExample Ver 1.0
    */

    public String getRw_price() {
        return rw_price;
    }

    /**
     * rw_price
     *
     * @param   rw_price    the rw_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setRw_price(String rw_price) {
        this.rw_price = rw_price;
    }

    /**
     * train_code
     *
     * @return  the train_code
     * @since   CodingExample Ver 1.0
    */

    public String getTrain_code() {
        return train_code;
    }

    /**
     * train_code
     *
     * @param   train_code    the train_code to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrain_code(String train_code) {
        this.train_code = train_code;
    }

    /**
     * train_no
     *
     * @return  the train_no
     * @since   CodingExample Ver 1.0
    */

    public String getTrain_no() {
        return train_no;
    }

    /**
     * train_no
     *
     * @param   train_no    the train_no to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
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
     * run_time_minute
     *
     * @return  the run_time_minute
     * @since   CodingExample Ver 1.0
    */

    public String getRun_time_minute() {
        return run_time_minute;
    }

    /**
     * run_time_minute
     *
     * @param   run_time_minute    the run_time_minute to set
     * @since   CodingExample Ver 1.0
     */

    public void setRun_time_minute(String run_time_minute) {
        this.run_time_minute = run_time_minute;
    }

    /**
     * ywx_price
     *
     * @return  the ywx_price
     * @since   CodingExample Ver 1.0
    */

    public String getYwx_price() {
        return ywx_price;
    }

    /**
     * ywx_price
     *
     * @param   ywx_price    the ywx_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setYwx_price(String ywx_price) {
        this.ywx_price = ywx_price;
    }

    /**
     * dw_num
     *
     * @return  the dw_num
     * @since   CodingExample Ver 1.0
    */

    public String getDw_num() {
        return dw_num;
    }

    /**
     * dw_num
     *
     * @param   dw_num    the dw_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setDw_num(String dw_num) {
        this.dw_num = dw_num;
    }

    /**
     * gjrws_price
     *
     * @return  the gjrws_price
     * @since   CodingExample Ver 1.0
    */

    public String getGjrws_price() {
        return gjrws_price;
    }

    /**
     * gjrws_price
     *
     * @param   gjrws_price    the gjrws_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setGjrws_price(String gjrws_price) {
        this.gjrws_price = gjrws_price;
    }

    /**
     * arrive_time
     *
     * @return  the arrive_time
     * @since   CodingExample Ver 1.0
    */

    public String getArrive_time() {
        return arrive_time;
    }

    /**
     * arrive_time
     *
     * @param   arrive_time    the arrive_time to set
     * @since   CodingExample Ver 1.0
     */

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    /**
     * start_station_name
     *
     * @return  the start_station_name
     * @since   CodingExample Ver 1.0
    */

    public String getStart_station_name() {
        return start_station_name;
    }

    /**
     * start_station_name
     *
     * @param   start_station_name    the start_station_name to set
     * @since   CodingExample Ver 1.0
     */

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    /**
     * start_time
     *
     * @return  the start_time
     * @since   CodingExample Ver 1.0
    */

    public String getStart_time() {
        return start_time;
    }

    /**
     * start_time
     *
     * @param   start_time    the start_time to set
     * @since   CodingExample Ver 1.0
     */

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    /**
     * wz_num
     *
     * @return  the wz_num
     * @since   CodingExample Ver 1.0
    */

    public String getWz_num() {
        return wz_num;
    }

    /**
     * wz_num
     *
     * @param   wz_num    the wz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    /**
     * edz_num
     *
     * @return  the edz_num
     * @since   CodingExample Ver 1.0
    */

    public String getEdz_num() {
        return edz_num;
    }

    /**
     * edz_num
     *
     * @param   edz_num    the edz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setEdz_num(String edz_num) {
        this.edz_num = edz_num;
    }

    /**
     * qtxb_num
     *
     * @return  the qtxb_num
     * @since   CodingExample Ver 1.0
    */

    public String getQtxb_num() {
        return qtxb_num;
    }

    /**
     * qtxb_num
     *
     * @param   qtxb_num    the qtxb_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setQtxb_num(String qtxb_num) {
        this.qtxb_num = qtxb_num;
    }

    /**
     * train_start_date
     *
     * @return  the train_start_date
     * @since   CodingExample Ver 1.0
    */

    public String getTrain_start_date() {
        return train_start_date;
    }

    /**
     * train_start_date
     *
     * @param   train_start_date    the train_start_date to set
     * @since   CodingExample Ver 1.0
     */

    public void setTrain_start_date(String train_start_date) {
        this.train_start_date = train_start_date;
    }

    /**
     * gjrw_price
     *
     * @return  the gjrw_price
     * @since   CodingExample Ver 1.0
    */

    public String getGjrw_price() {
        return gjrw_price;
    }

    /**
     * gjrw_price
     *
     * @param   gjrw_price    the gjrw_price to set
     * @since   CodingExample Ver 1.0
     */

    public void setGjrw_price(String gjrw_price) {
        this.gjrw_price = gjrw_price;
    }

    /**
     * tdz_num
     *
     * @return  the tdz_num
     * @since   CodingExample Ver 1.0
    */

    public String getTdz_num() {
        return tdz_num;
    }

    /**
     * tdz_num
     *
     * @param   tdz_num    the tdz_num to set
     * @since   CodingExample Ver 1.0
     */

    public void setTdz_num(String tdz_num) {
        this.tdz_num = tdz_num;
    }

}
