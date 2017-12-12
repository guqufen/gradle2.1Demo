package net.fnsco.trading.service.third.ticket.demo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import net.fnsco.core.utils.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
 
public class TicketsDemo {
    public static final String  KEY="yourKey";
    /*
     * 试想一下，日常生活中，我们想订票（我们这里以一切顺利的情况看，以方便做示例），
     * 要先看看从你的出发地到目的地有没有直达的票， 如果有，我们就看看我们的出发日期有
     * 没有余票，很顺利，有，那么来定吧（还有个前提是你有钱）！！！
     * 假设你在苏州，女朋友在无锡，你正好周末有空要去看她，好，查一下余票 对应接口地址：
     * http://www.juhe.cn/docs/api/id/173/aid/580
     * 看了文档发现需要如下参数：
     *dtype            string  否   返回的格式，json或xml，默认json
    * key             string  是   您申请到的APPKEY
    * train_date      string  是   发车日期，如：2015-07-01（务必按照此格式）
    * from_station    string  是   出发站简码，如：BJP
    * to_station      string  是   到达站简码，如：SHH
    *
    * key我们就不说了，这是我们的钥匙,聚合平台的所有接口都需要这个参数
    * dtype我们以默认json为准(本例所有接口返回格式都以默认为准)
    * train_date，我们就选择这个周六的2015-08-22
    * from_station,嗯？简码？什么鬼？抬头猛然发现第一个接口“站点简码查询”
    * 点进去看。。。好吧，看来要先查一下苏州和无锡的简码了，来吧！
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        /* 1.*获取苏州和无锡的简码**/
        String szCode=getCode("苏州");
        String wxCode=getCode("无锡");
        String train_date="2015-08-22";
        /**获取了两个城市的简码之后现在终于可以查询余票了，查询余票会返回很多信息，包括
         *路过的车辆的信息，以及一些票价座位剩余数量等很多信息，我们这里以一个D636的
         *车次为例,这里返回了一个map类型，map中含有座位剩余数量、车次和对应的座位价
         *格,这个几个参数在提交订单的时候需要用到
         */
        /* 2.*查询余票**/
        Map data=getTicketsAvailable(train_date, szCode, wxCode);
        if(data!=null){
            //判断余票数量是否大于0，如果有就执行步骤3
            String edz_num=(String) data.get("edz_num");
            if(edz_num!=null){
                int num=Integer.parseInt(edz_num);
                if(num>0){
        /* 3.*提交订单**/  
                    //这些参数都是提交订单所必须的，以map封装这些参数，
                    //同时，pureNetUtil中的post方法简单封装了post请求的方法
                    Map map=new HashMap();
                    map.put("key", KEY);
                    map.put("user_orderid", "1");
                    map.put("train_date", train_date);
                    map.put("from_station_name", "苏州");
                    map.put("from_station_code",szCode);
                    map.put("to_station_code",wxCode);
                    map.put("to_station_name","无锡");
                    map.put("checi",data.get("train_code"));
                    //这里注意，必须是JSONArray格式，即即使只有一个乘客也是以"[{}]"形式存在
                    String passengers="[{\"passengerid\":1,"
                            + "\"passengersename\":\"张三\","
                            + "\"piaotype\":\"1\","
                            + "\"piaotypename\":\"成人票\","
                            + "\"passporttypeseid\":\"1\","
                            + "\"passporttypeseidname\":\"二代身份证\","
                            + "\"passportseno\":\"420205199207231234\","
                            + "\"price\":"+data.get("price")+","
                            + "\"zwcode\":\"O\","
                            + "\"zwname\":\"二等座\"}]";
                    map.put("passengers", passengers);
                    String orderid=postIndent(map);
                    if(orderid!=null){
        /* 4.*请求出票**/      
                    /**此处省略*/
                    }
                }
            }
        }
    }
     
     
    /**
     * 获取站点名称的简码,这个接口可以看出stationName是一个必须参数
     * 对应接口地址：http://www.juhe.cn/docs/api/id/173/aid/579
     * @param stationName 站点名称
     * @return
     */
    public static String getCode(String stationName){
        if (stationName==null) {
            return null;
        }
        String url="http://op.juhe.cn/trainTickets/cityCode?stationName="+stationName+"&key="+KEY;
        //获取返回数据
        String data=HttpUtils.get(url);
        if(data==null){
            return null;
        }
        JSONObject obj=JSONObject.fromObject(data);
        String error_code=obj.getString("error_code");
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if("0".equals(error_code)){
            //如果返回成功则获取result字段
            String result=obj.getString("result");
            if(result!=null){
                obj=JSONObject.fromObject(result);
                //如果获取result字段成功获取最终想要的结果-->code,
                //即站点代码，其余状况表示不成功，则返回null
                String code=obj.getString("code");
                return code;
            }
        }
        return null;
    }
     
    /*
     * 查询余票，所需参数如下
     * 对应接口地址：http://www.juhe.cn/docs/api/id/173/aid/580
      
       dtype           string  否   返回的格式，json或xml，默认json
       key             string  是   您申请到的APPKEY
       train_date      string  是   发车日期，如：2015-07-01（务必按照此格式）
       from_station    string  是   出发站简码，如：BJP
       to_station      string  是   到达站简码，如：SHH
     */
    public static  Map getTicketsAvailable(String train_date,String from_station,String to_station){
        String url="http://op.juhe.cn/trainTickets/ticketsAvailable?key="+KEY+"&train_date="+train_date

+"&from_station="+from_station+"&to_station="+to_station;
        String data=HttpUtils.get(url);
        if(data==null){
            return null;
        }
        JSONObject obj=JSONObject.fromObject(data);
        String error_code=obj.getString("error_code");
        System.out.println(error_code);
        //判断是否调用成功，只有error_code=0的时候表示返回成功
        if("0".equals(error_code)){
            String result=obj.getString("result");
            if(result!=null){
                obj=JSONObject.fromObject(result);
                String list=obj.getString("list");
                if (list!=null) {
                    JSONArray arr=JSONArray.fromObject(list);
                    System.out.println(arr.size());
                    for(int i=0;i<arr.size();i++){
                        if(arr.get(i).toString().contains("D636")){
                            //{"rwx_price":0,"end_station_name":"成都东",
                            //"swz_price":0,"swz_num":"--","to_station_name":"无锡",
                            //"ydz_num":"8","yz_num":"--","rw_num":"--","arrive_days":"0",
                            //"rz_num":"--","access_byidcard":"1","yz_price":0,
                            //"ywz_price":0,"sale_date_time":"1600","from_station_code":"SZH",
                            //"rz_price":0,"gjrw_num":"--","to_station_code":"WXH",
                            //"ydz_price":15.5,"wz_price":12.5,"tdz_price":0,
                            //"run_time":"00:17","yw_num":"--","edz_price":12.5,
                            //"qtxb_price":0,"can_buy_now":"Y","yw_price":0,"rw_price":0,
                            //"train_type":"D","note":"","train_no":"5l0000D63600",
                            //"train_code":"D636","from_station_name":"苏州","run_time_minute":"17",
                            //"ywx_price":0,"arrive_time":"07:00","start_station_name":"上海虹桥",
                            //"start_time":"06:43","wz_num":"0","edz_num":"175","qtxb_num":"--",
                            //"train_start_date":"20150822","gjrw_price":0,"tdz_num":"--"}
                            obj=JSONObject.fromObject(arr.get(i).toString());
                            Map map=new HashMap<>();
                            //所选的座位的价格，这里选的是二等座
                            map.put("price", obj.getString("edz_price"));
                            map.put("train_code",obj.getString("train_code"));
                            //二等座的剩余数量
                            map.put("edz_num", obj.getString("edz_num"));
                            return map;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }
    /*
       dtype                string    否   返回的格式，json或xml，默认json
       key                 string  是   您申请到的APPKEY
       user_orderid        string  是   您自定义的订单号，如：12345678，不要超过50个字符
       train_date          string  是   乘车日期，如：2015-07-01
       from_station_name   string  是   出发站名字，如：苏州
       from_station_code   string  是   出发站简码，如：SZH
       to_station_code     string  是   到达站简码，如：SHH
       to_station_name     string  是   到达站名字，如：上海
       checi               string  是   车次，如：G7027，请注意：出发站、到达站信息必须属实，例如

G101车次不经过北京（经过北京南），出发站信息中不能填北京
       passengers          string  是   乘车人信息，json格式的字符串，如：
                                        [{"passengerid":1123,
                                            "passengersename":"张三",
                                            "piaotype":"1",
                                            "piaotypename":"成人票",
                                            "passporttypeseid":"1",
                                            "passporttypeseidname":"二代身份证",
                                            "passportseno":"420205199207231234",
                                            "price":"763.5",
                                            "zwcode":"M",
                                            "zwname":"一等座",
                                            "insurance": {"name": "张三",
                                            "mobile": "13888888888", 
                                            "gender": "M",
                                            "birth": "1987-05-04",
                                            "city": "苏州",
                                            "idcard": "332522198705040011"
                                        }}]
 
        各个字段的解释：
              "passengerid":乘客的顺序号，如：1123，当有多个乘客时，用来唯一标识每个乘客；请自定义此参数，但要

在100000000以内。
              "passengersename":乘车人姓名
              "piaotype":1 :成人票,2 :儿童票,4 :残军票
              "piaotypename":票种名称
              "passporttypeseid": 1:二代身份证,2:一代身份证,C:港澳通行证,G:台湾通 行证,B:护照
              "passporttypeseidname":证件类型名称
              "passportseno":乘客证件号码
              "price": 票价，即当前乘客选择的座位的价格
              "zwcode":座位编码。 9:商务座,P:特等座,M:一等座,O（大写字母O，不是数字0）:二等座,6:高级软卧, 4:

软卧,3:硬卧,2:软座,1:硬座。注意:当最低座位无票时,购买选该座位, 买下的就是无座(即无座的编码就是该车次的最低席别的编码)；另外

,当最低席别的票卖完了的时候才可以卖无座的票。
              "zwname":座位名称
              "insurance":选填，如需要免费出行险，请提供此信息，其中：
            "name":需要保险的乘客的姓名
            "mobile":手机号码，保单信息将发送至此手机号
            "gender":性别，M男F女    
            "birth":出生日期，注意格式
            "city":所在城市
            "idcard":身份证号       
     */
    /**
     * 提交订单，对应接口地址：http://www.juhe.cn/docs/api/id/173/aid/581
     * 这个方法需要传的参数很多，不用慌，一个一个来
     * @param map
     * @throws UnsupportedEncodingException 
     */
    public static String postIndent(Map map) throws UnsupportedEncodingException{
        String url="http://op.juhe.cn/trainTickets/submit";
        String data=HttpUtils.post(url, map);
        if(data!=null){
            JSONObject obj=JSONObject.fromObject(data);
            String error_code=obj.getString("error_code");
            if("0".equals(error_code)) {
                String result=obj.getString("result");
                if(result!=null){
                    obj=JSONObject.fromObject(result);
                    return obj.getString("orderid");
                }
            }
        }
        return null;
    }
 
}