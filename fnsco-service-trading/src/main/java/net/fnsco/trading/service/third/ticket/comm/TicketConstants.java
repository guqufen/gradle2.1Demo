package net.fnsco.trading.service.third.ticket.comm;

import net.fnsco.core.constants.CoreConstants;

public class TicketConstants extends CoreConstants {
    /**
     * 证件类型
     * 
     * @author sxf
     * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
     */
    public enum CardTypeEnum {
                              //状态 状态 0未执行1执行中2失败3成功
                              INIT("0", "未执行"), PROCESSING("1", "执行中"), FAIL("2", "失败"), SUCCESS("3", "成功");

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
    * 
    * @author sxf
    * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
    */
    public enum SeatTypeEnum {
                              //状态 状态 0未执行1执行中2失败3成功
                              INIT("0", "未执行"), PROCESSING("1", "执行中"), FAIL("2", "失败"), SUCCESS("3", "成功");

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
    * 
    * @author sxf
    * @version $Id: WithdrawPrepStateEnum.java, v 0.1 2017年3月21日 下午6:01:34 sxf Exp $
    */
    public enum TicketTypeEnum {
                                //状态 状态 0未执行1执行中2失败3成功
                                INIT("0", "未执行"), PROCESSING("1", "执行中"), FAIL("2", "失败"), SUCCESS("3", "成功");

        private String code;
        private String  name;

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