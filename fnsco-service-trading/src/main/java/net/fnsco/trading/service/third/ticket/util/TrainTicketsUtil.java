package net.fnsco.trading.service.third.ticket.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import net.fnsco.core.utils.HttpUtils;
import net.sf.json.JSONArray;
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
        long timer = System.currentTimeMillis();
        String url = "http://op.juhe.cn/trainTickets/ticketsAvailable?key=" + KEY + "&train_date=" + train_date

                     + "&from_station=" + from_station + "&to_station=" + to_station;
        String data = HttpUtils.get(url);
        if (data == null) {
            logger.error("调用火车票查询余票接口返回空");
            return null;
        }
        logger.error("调用火车票查询余票接口内容" + url + data);
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
        logger.error("调用火车票查询余票接口耗时" + (System.currentTimeMillis() - timer));
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
    public static JSONObject postIndent(Map<String, String> paramesMap) throws UnsupportedEncodingException {
        paramesMap.put("key", KEY);
        Map<String, String> paramesMapTemp = Maps.newHashMap();
        for (Entry<String, String> entry : paramesMap.entrySet()) {
            String key = entry.getKey();
            String value = "";
            Object temp = entry.getValue();
            if (temp instanceof String) {
                value = (String) temp;
            } else if (temp instanceof JSONArray) {
                value = ((JSONArray) temp).toString();
            } else {
                value = String.valueOf(temp);
            }
            paramesMapTemp.put(key, value);
        }
        String url = "http://op.juhe.cn/trainTickets/submit";
        logger.error("调用火车票（提交订单开始）" + JSON.toJSONString(paramesMapTemp));
        String data = HttpUtils.post(url, paramesMapTemp);
        logger.error("调用火车票（提交订单返回）" + data);
        if (data != null) {
            return JSONObject.fromObject(data);
        }
        return null;
    }

    /**
     * 订单状态查询，对应接口地址：http://op.juhe.cn/trainTickets/orderStatus?key=您申请到的appkey&orderid=1433243990111
     * {"reason":"查询订单状态成功","result":{"orderid":"JH151390556151688","user_orderid":"T1222091991883349","msg":"在12306未获取到车次[K825]车票预订查询结果。","orderamount":null,"status":"1","passengers":[{"zwcode":"1","passportseno":"","passporttypeseid":"1","passporttypeseidname":"二代身份证","price":"11","passengersename":"宋先飞","zwname":"硬座","passengerid":"154376","piaotype":"1","piaotypename":"成人票"}],"checi":"K825","ordernumber":null,"submit_time":"2017-12-22 09:19:21","deal_time":"2017-12-22 09:19:49","cancel_time":null,"pay_time":null,"finished_time":null,"refund_time":null,"juhe_refund_time":null,"start_time":null,"arrive_time":null,"runtime":null,"train_date":"2017-12-30","from_station_name":"杭州东","from_station_code":"HGH","to_station_name":"海宁","to_station_code":"HNH","refund_money":null},"error_code":0}
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static JSONObject getOrderStatus(String orderNo) {
        String url = "http://op.juhe.cn/trainTickets/orderStatus?key=" + KEY + "&orderid=" + orderNo;
        logger.error("调用火车票（订单状态查询）" + orderNo);
        String data = HttpUtils.get(url);
        logger.error("调用火车票（订单状态查询）" + data);
        if (data != null) {
            return JSONObject.fromObject(data);
        }
        return null;
        //调用火车票（订单状态查询）{"reason":"查询订单状态成功","result":{"orderid":"JH151390696648081","user_orderid":"T1222094223734791","msg":"线上退票成功","orderamount":"11.00","status":"7",
        //        "passengers":[{"zwcode":"1","passportseno":"433023198209112817",
        //            "passporttypeseid":"1","passporttypeseidname":"二代身份证",
        //            "price":"11.0","passengersename":"宋先飞","zwname":"硬座",
        //            "passengerid":"154377","piaotype":"1","piaotypename":"成人票",
        //            "ticket_no":"E6606813191130007","cxin":"13车厢,007座","reason":0,
        //            "refundTimeline":[{"time":"2017-12-22 10:37:23","msg":"线上申请退票"}
        //            ,{"time":"2017-12-22 10:37:36","msg":"线上退票成功",
        //                "detail":{"returnsuccess":true,"returnmoney":"9","returnfailid":"",
        //                "returnfailmsg":"","returntype":"1","ticket_no":"E6606813191130007",
        //                "passengername":"宋先飞","passporttypeseid":"1","passportseno":"433023198209112817"}}]
        //                        ,"returntickets":{"returnsuccess":true,"returnmoney":"9",
        //            "returntime":"2017-12-22 10:37:35","returnfailid":"","returnfailmsg":"","returntype":"1"}}],
        //        "checi":"K528","ordernumber":"E660681319","submit_time":"2017-12-22 09:42:46",
        //        "deal_time":"2017-12-22 09:43:05","cancel_time":null,"pay_time":"2017-12-22 10:01:48",
        //        "finished_time":"2017-12-22 10:02:04","refund_time":"2017-12-22 10:37:23",
        //        "juhe_refund_time":"2017-12-22 10:37:36","start_time":"2017-12-30 02:26:00",
        //        "arrive_time":"2017-12-30 03:02:00","runtime":"00:36","train_date":"2017-12-30",
        //        "from_station_name":"杭州东","from_station_code":"HGH","to_station_name":"海宁",
        //        "to_station_code":"HNH","refund_money":"9.00"},"error_code":0}
    }

    /**
     * 请求出票支付订单，对应接口地址：http://op.juhe.cn/trainTickets/pay?key=您申请到的appkey&orderid=1433231423482
     * 
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static JSONObject pay(String orderNo) {
        String url = "http://op.juhe.cn/trainTickets/pay?key=" + KEY + "&orderid=" + orderNo;
        logger.error("调用火车票（请求出票支付订单）" + orderNo);
        String data = HttpUtils.get(url);
        logger.error("调用火车票（请求出票支付订单）" + data);
        if (data != null) {
            return JSONObject.fromObject(data);
        }
        return null;
    }

    /**
     * 在线退票，对应接口地址：http://op.juhe.cn/trainTickets/refund?key=您申请的APPKEY&orderid=1440491862863&tickets=[{"ticket_no":"E1190475871081234","passengername":"李四","passporttypeseid":"1","passportseno":"370827195104212345"}]
     * 这个方法需要传的参数很多，不用慌，一个一个来
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static JSONObject refund(Map<String, String> paramesMap) throws UnsupportedEncodingException {
        paramesMap.put("key", KEY);
        Map<String, String> paramesMapTemp = Maps.newHashMap();
        for (Entry<String, String> entry : paramesMap.entrySet()) {
            String key = entry.getKey();
            String value = "";
            Object temp = entry.getValue();
            if (temp instanceof String) {
                value = (String) temp;
            } else if (temp instanceof JSONArray) {
                value = ((JSONArray) temp).toString();
            } else {
                value = String.valueOf(temp);
            }
            paramesMapTemp.put(key, value);
        }
        String url = "http://op.juhe.cn/trainTickets/refund";
        logger.error("调用火车票（在线退票）" + JSON.toJSONString(paramesMapTemp));
        String data = HttpUtils.post(url, paramesMapTemp);
        logger.error("调用火车票（在线退票）" + data);
        if (data != null) {
            return JSONObject.fromObject(data);
        }
        return null;
    }

    /**
     * 取消支付订单，对应接口地址：http://op.juhe.cn/trainTickets/cancel?orderid=1440040885864&key=您申请的appkey
     * 
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static JSONObject cancel(String orderNo) {
        String url = "http://op.juhe.cn/trainTickets/cancel?key=" + KEY + "&orderid=" + orderNo;
        logger.error("调用火车票（取消支付订单）" + orderNo);
        String data = HttpUtils.get(url);
        logger.error("调用火车票（取消支付订单）" + data);
        if (data != null) {
            return JSONObject.fromObject(data);
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
