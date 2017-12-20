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
    * 1硬座3硬卧4软卧
    * 9商务座特等座 8一等座 7二等座 6高级软卧  4软卧  5动卧  3硬卧  2软座 1 硬座  0无座  10其他
    * @author sxf
    * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
    */
    public enum SeatTypeEnum {
                              //状态 状态 0未执行1执行中2失败3成功
                              BUSINESS_CLASS("O",
                                             "商务座特等座"), FIRST_CLASS("1",
                                                                    "一等座"), SECOND_CLASS("2",
                                                                                         "二等座"), ADVANCED_SOFT("3",
                                                                                                               "高级软卧"), SOFT("4",
                                                                                                                             "软卧"), MOBILE_HORIZONTAL("5",
                                                                                                                                                      "动卧"), HARD_SLEEPER("3",
                                                                                                                                                                          "硬卧"), SOFT_SEAT("2",
                                                                                                                                                                                           "软座"), HARD_SEAT("1",
                                                                                                                                                                                                            "硬座"), NO_SEAT("0",
                                                                                                                                                                                                                           "无座"), OTHER("10",
                                                                                                                                                                                                                                        "其他");

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
                                //状态 状态 0未执行1执行中2失败3成功
        ADULT_TICKET("1", "成人票"),
                                CHILDREN_TICKET("2", "儿童票"), STUDENT_TICKET("3", "学生票"), REMNANT_MILITARY_TICKET("4", "残军票");

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
     * 
     * @author sxf
     * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
     */
    public enum OrderStateEnum {
                                //状态 状态 0未执行1执行中2失败3成功
                                INIT(0, "未执行"), PROCESSING(1, "执行中"), FAIL(2, "失败"), SUCCESS(3, "成功");

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