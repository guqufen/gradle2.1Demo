package net.fnsco.trading.service.third.ticket.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fnsco.core.utils.HttpUtils;
import net.sf.json.JSONObject;

public class TrainTicketsUtil {
    public static final String KEY    = "89ecc72825546e4116e2963848c890c4";
    public static Logger       logger = LoggerFactory.getLogger(TrainTicketsUtil.class);

    /*
     * 查询余票，所需参数如下
     * 对应接口地址：http://www.juhe.cn/docs/api/id/173/aid/580
      
       dtype           string  否   返回的格式，json或xml，默认json
       key             string  是   您申请到的APPKEY
       train_date      string  是   发车日期，如：2015-07-01（务必按照此格式）
       from_station    string  是   出发站简码，如：BJP
       to_station      string  是   到达站简码，如：SHH
     */
    public static String getTicketsAvailable(String train_date, String from_station, String to_station) {
        String url = "http://op.juhe.cn/trainTickets/ticketsAvailable?key=" + KEY + "&train_date=" + train_date

                     + "&from_station=" + from_station + "&to_station=" + to_station;
        String data = HttpUtils.get(url);
        if (data == null) {
            logger.error("调用火车票接口http://op.juhe.cn/trainTickets/ticketsAvailable返回空");
            return null;
        }
        //{"reason":"成功的返回","result":{"list":[{"rwx_price":0,"end_station_name":"北京南","swz_price":1701,"swz_num":"181","to_station_name":"北京南","ydz_num":"108","yz_num":"--","rw_num":"--","arrive_days":"0","rz_num":"--","access_byidcard":"1","yz_price":0,"ywz_price":0,"sale_date_time":"1130","dw_price":0,"from_station_code":"HGH","rz_price":0,"gjrw_num":"--","to_station_code":"VNP","ydz_price":907,"wz_price":0,"tdz_price":0,"run_time":"05:55","dwx_price":0,"yw_num":"--","distance":0,"edz_price":538.5,"qtxb_price":0,"can_buy_now":"Y","yw_price":0,"train_type":"G","rw_price":0,"train_code":"G34","train_no":"5600000G3472","from_station_name":"杭州东","run_time_minute":"355","ywx_price":0,"dw_num":"--","gjrws_price":0,"arrive_time":"13:05","start_station_name":"杭州东","start_time":"07:10","wz_num":"--","edz_num":"190","qtxb_num":"--","train_start_date":"20171226","gjrw_price":0,"tdz_num":"--"},
        JSONObject obj = JSONObject.fromObject(data);
        String error_code = obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if ("0".equals(error_code)) {
            logger.error("调用火车票接口http://op.juhe.cn/trainTickets/ticketsAvailable返回错误信息" + error_code);
            return null;
        }
        String result = obj.getString("result");
        String resultList = "";
        if (result != null) {
            obj = JSONObject.fromObject(result);
            resultList = obj.getString("list");
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
        }
        return resultList;
    }
}
