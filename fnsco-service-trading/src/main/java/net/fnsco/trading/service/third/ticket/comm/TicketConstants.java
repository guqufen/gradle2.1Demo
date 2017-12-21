package net.fnsco.trading.service.third.ticket.comm;

import net.fnsco.core.constants.CoreConstants;

public class TicketConstants extends CoreConstants {
    /**
     * 证件类型
     *  1">二代身份证C 港澳通行证G">台湾通行证B">护照
     * @author sxf
     * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
     */
    public enum CardTypeEnum {
                              //状态 状态 1二代身份证
                              ID_CARD("1", "二代身份证"), HONG_KONG_MACAO("C", "港澳通行证"), TAIWAN("G", "台湾通行证"), PASSPORT("B", "护照");

        private String code;
        private String name;

        private CardTypeEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTypeName(String code) {
            for (CardTypeEnum taskType : values()) {
                if (taskType.getCode().equals(code)) {
                    return taskType.getName();
                }
            }
            return "";
        }

        /**
         * Getter method for property <tt>code</tt>.
         * 
         * @return property value of code
         */
        public String getCode() {
            return code;
        }

        /**
         * Getter method for property <tt>name</tt>.
         * 
         * @return property value of name
         */
        public String getName() {
            return name;
        }

    }

    /**
    * 座位类型表
    F:动卧(新增),
    9:商务座,
    P:特等座,
    M:一等座,
    O（大写字母O，不是数字0）:二等座,
    6:高级软卧, 
    4:软卧,
    3:硬卧,
    2:软座,
    1:硬座。
    * @author sxf
    * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
    */
    public enum SeatTypeEnum {
                              //状态 状态 0未执行1执行中2失败3成功
                              BUSINESS("9",
                                       "商务座"), SPECIAL("P",
                                                       "特等座"), FIRST_CLASS("M",
                                                                           "一等座"), SECOND_CLASS("O",
                                                                                                "二等座"), ADVANCED_SOFT("6",
                                                                                                                      "高级软卧"), SOFT("4",
                                                                                                                                    "软卧"), MOBILE_SLEEPER("F",
                                                                                                                                                          "动卧"), HARD_SLEEPER("3",
                                                                                                                                                                              "硬卧"), SOFT_SEAT("2",
                                                                                                                                                                                               "软座"), HARD_SEAT("1",
                                                                                                                                                                                                                "硬座"), NO_SEAT("1",
                                                                                                                                                                                                                               "无座"), OTHER_SEAT("1",
                                                                                                                                                                                                                                                 "其它席别");
        ;

        private String code;
        private String name;

        private SeatTypeEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTypeName(String code) {
            for (SeatTypeEnum taskType : values()) {
                if (taskType.getCode().equals(code)) {
                    return taskType.getName();
                }
            }
            return "";
        }

        /**
         * Getter method for property <tt>code</tt>.
         * 
         * @return property value of code
         */
        public String getCode() {
            return code;
        }

        /**
         * Getter method for property <tt>name</tt>.
         * 
         * @return property value of name
         */
        public String getName() {
            return name;
        }

    }

    /**
    * 火车票类型
    *1 成人票2 儿童票3学生票4残军票 
            
            
    
    * @author sxf
    * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
    */
    public enum TicketTypeEnum {
                                //状态 1 成人票2 儿童票3学生票4残军票 
                                ADULT_TICKET("1", "成人票"), CHILDREN_TICKET("2", "儿童票"), STUDENT_TICKET("3", "学生票"), REMNANT_MILITARY_TICKET("4", "残军票");

        private String code;
        private String name;

        private TicketTypeEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTypeName(String code) {
            for (TicketTypeEnum taskType : values()) {
                if (taskType.getCode().equals(code)) {
                    return taskType.getName();
                }
            }
            return "";
        }

        /**
         * Getter method for property <tt>code</tt>.
         * 
         * @return property value of code
         */
        public String getCode() {
            return code;
        }

        /**
         * Getter method for property <tt>name</tt>.
         * 
         * @return property value of name
         */
        public String getName() {
            return name;
        }

    }

    /**
     * 订单状态表
     * 状态 0未占座1占座中2已占座3支付完成4取消订单
     * @author sxf
     * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
     */
    public enum OrderStateEnum {
                                //状态 状态 0未执行1执行中2失败3成功
                                INIT(0, "未占座"), PROCESSING(1, "占座中"), SIT_DOWN(2, "已占座"), FAIL(3, "占座失败"), SUCCESS(4, "支付完成"), CANCEL(5, "取消订单");

        private Integer code;
        private String  name;

        private OrderStateEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static String getTypeName(Integer code) {
            for (OrderStateEnum taskType : values()) {
                if (taskType.getCode() == code) {
                    return taskType.getName();
                }
            }
            return "";
        }

        /**
         * Getter method for property <tt>code</tt>.
         * 
         * @return property value of code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * Getter method for property <tt>name</tt>.
         * 
         * @return property value of name
         */
        public String getName() {
            return name;
        }

    }
}