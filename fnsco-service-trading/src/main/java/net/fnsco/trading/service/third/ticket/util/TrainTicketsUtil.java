package net.fnsco.trading.service.third.ticket.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

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
            logger.error("调用火车票查询余票接口返回空");
            return null;
        }
        //{"reason":"成功的返回","result":{"list":[{"rwx_price":0,"end_station_name":"北京南","swz_price":1701,"swz_num":"181","to_station_name":"北京南","ydz_num":"108","yz_num":"--","rw_num":"--","arrive_days":"0","rz_num":"--","access_byidcard":"1","yz_price":0,"ywz_price":0,"sale_date_time":"1130","dw_price":0,"from_station_code":"HGH","rz_price":0,"gjrw_num":"--","to_station_code":"VNP","ydz_price":907,"wz_price":0,"tdz_price":0,"run_time":"05:55","dwx_price":0,"yw_num":"--","distance":0,"edz_price":538.5,"qtxb_price":0,"can_buy_now":"Y","yw_price":0,"train_type":"G","rw_price":0,"train_code":"G34","train_no":"5600000G3472","from_station_name":"杭州东","run_time_minute":"355","ywx_price":0,"dw_num":"--","gjrws_price":0,"arrive_time":"13:05","start_station_name":"杭州东","start_time":"07:10","wz_num":"--","edz_num":"190","qtxb_num":"--","train_start_date":"20171226","gjrw_price":0,"tdz_num":"--"},
        JSONObject obj = JSONObject.fromObject(data);
        String error_code = obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if (!"0".equals(error_code)) {
            logger.error("调用火车票接口查询余票返回错误信息" + obj.toString());
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
        //        "gjrw_num": "--", /*高级软卧余票数量*/
        //        "gjrws_price":0, /* 高级软卧（上）票价*/
        //       "gjrw_price": 0, /*高级软卧票价*/
        //       "qtxb_num": "--", /*其他席别余票数量*/
        //       "qtxb_price": 0, /*其他席别对应的票价*/
        //       "rw_num": "--", /*软卧余票数量*/
        //       "rw_price": 0, /*软卧（上）票价*/
        //       "rwx_price":10, /*软卧(下)票价*/
        //       "rz_num": "--", /*软座的余票数量*/
        //       "rz_price": 0, /*软座的票价*/   
        //       "swz_num": "15", /*商务座余票数量*/    
        //       "swz_price": 1748, /*商务座票价*/
        //       "tdz_num": "--", /*特等座的余票数量*/
        //       "tdz_price": 0,  /*特等座票价*/
        //       "wz_num": "--", /*无座的余票数量*/       
        //       "wz_price": 0, /*无座票价*/
        //       "dw_num":"8",/*动卧的余票数量*/
        //       "dw_price":"",/*动卧(上)票价[12306新增]*/
        //       "dwx_price":"",/*动卧(下)票价[12306新增]*/
        //      "yw_num": "--", /*硬卧的余票数量*/
        //      "yw_price": 0, /*硬卧(上)票价（因为硬卧上中下铺价格不同，这个价格一般是均价），
        //   请务必阅读 http://code.juhe.cn/docs/201 中第15条*/     
        //     " ywz_price":90,/*硬卧(中)票价*/        
        //      "ywx_price":86.5,/*硬卧(下)票价*/        
        //       "yz_num": "--", /*硬座的余票数量*/
        //       "yz_price": 0, /*硬座票价*/   
        //       "edz_num": "900", /*二等座的余票数量*/
        //       "edz_price": 553, /*二等座票价*/ 
        //       "ydz_num": "54", /*一等座余票数量*/
        //       "ydz_price": 933, /*一等座票价*/
    }

    /**
     * 提交订单，对应接口地址：http://www.juhe.cn/docs/api/id/173/aid/581
     * 这个方法需要传的参数很多，不用慌，一个一个来
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static String postIndent(Map map) throws UnsupportedEncodingException {
        map.put("key", KEY);
        String url = "http://op.juhe.cn/trainTickets/submit";
        logger.error("调用火车票（提交订单开始）" + JSON.toJSONString(map));
        String data = HttpUtils.post(url, map);
        logger.error("调用火车票（提交订单返回）" + data);
        if (data != null) {
            JSONObject obj = JSONObject.fromObject(data);
            String error_code = obj.getString("error_code");
            if ("0".equals(error_code)) {
                String result = obj.getString("result");
                if (result != null) {
                    obj = JSONObject.fromObject(result);
                    return obj.getString("orderid");
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String jsgsCont = "asdasdf【1】asdf21sdf阿斯蒂芬";
        //Pattern pattern = Pattern.compile("【\\d+】");
        jsgsCont.replaceAll("【1】", "");
        Pattern pattern = Pattern.compile("【1】");
        Matcher matcher = pattern.matcher(jsgsCont);
        while (matcher.find()) {
            String s = matcher.group();
            System.out.println(s);
        }
    }
}
