package net.fnsco.car.comm;

import java.util.Map;

import com.google.common.collect.Maps;

import ch.qos.logback.core.CoreConstants;

public class CarServiceConstant{
    public static Map<String, String> anErrorMap = Maps.newHashMap();
    static {
        //anErrorMap.put("0000", "接受通知成功（异步交易时才会出现）");
        anErrorMap.put("0001", "参数错误");
        anErrorMap.put("0002", "签名错误");
        //anErrorMap.put("1001", "交易成功");
        anErrorMap.put("1002", "交易失败");
        anErrorMap.put("1003", "已退款");
        //anErrorMap.put("1111", "交易进行中");
        anErrorMap.put("2000", "无效商户");
        anErrorMap.put("2001", "重复交易");
        anErrorMap.put("2002", "查无此交易");
        anErrorMap.put("2003", "交易金额超限");
        anErrorMap.put("2004", "原交易不存在或状态不正确");
        anErrorMap.put("2005", "与原交易信息不符");
        anErrorMap.put("2006", "已超过最大查询次数或操作过于频繁");
        anErrorMap.put("2007", "风控受限");
        anErrorMap.put("2008", "交易不在受理时间范围内");
        anErrorMap.put("2009", "扣款成功但交易超过指定支付时间");
        anErrorMap.put("2010", "订单金额不匹配");
        anErrorMap.put("2011", "订单币种不匹配");
        anErrorMap.put("2012", "卡信息或银行预留手机号有误");
        anErrorMap.put("2013", "密码错误次数超限");
        anErrorMap.put("2014", "密码验证失败");
        anErrorMap.put("2015", "短信验证码错误");
        anErrorMap.put("2016", "CVN验证失败");
        anErrorMap.put("2017", "身份证号有误");
        anErrorMap.put("2018", "短信验证码发送次数超限");
        anErrorMap.put("2019", "短信验证码发送频率过高");
        anErrorMap.put("2020", "短信验证码验证错误次数超限");
        anErrorMap.put("2021", "短信校验码已过期");
        anErrorMap.put("2022", "姓名有误");
        anErrorMap.put("2023", "银行预留手机号有误");
        anErrorMap.put("2024", "银行卡无效或状态有误");
        anErrorMap.put("2025", "余额不足");
        anErrorMap.put("2026", "请持卡人与发卡银行联系");
        anErrorMap.put("9999", "系统繁忙");
    }

    //状态0扣款中1扣款失败2扣款成功9补收
    public static enum AnPayResultEnum {
                                        AN_PAY_SUCC("1001", "成功"), AN_PAY_FAIL("1002", "失败"), AN_PAYING("1111", "进行中");
        private String code;
        private String name;

        private AnPayResultEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * @return the code
         */
        public String getCode() {
            return code;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        public static String getNameByCode(String code) {
            for (PayStatusEnum eopen : PayStatusEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }

    //状态0扣款中1扣款失败2扣款成功9补收
    public static enum PayStatusEnum {
                                      PAYING(0, "扣款中"), PAY_RE_FAIL(8, "扣款失败有再扣款"), PAY_SUCC(2, "扣款成功"), PAY_FAIL(1, "扣款失败"), PAY_AGAIN(9, "补收");
        private Integer code;
        private String  name;

        private PayStatusEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        /**
         * @return the code
         */
        public Integer getCode() {
            return code;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        public static String getNameByCode(Integer code) {
            for (PayStatusEnum eopen : PayStatusEnum.values()) {
                if (eopen.code.equals(code)) {
                    return eopen.name;
                }
            }
            return "";
        }
    }
    
    public static enum ApplyType {
		BUY_CAR_TYPE("01", "买车申请"), LOAN_APPLY_TYPE("02", "贷款申请"), INSURANCE_APPLY_TYPE("03", "保险申请"), MANAGE_APPLY_TYPE("04", "理财申请");

		private String type;
		private String name;

		private ApplyType(String type, String name) {
			this.type = type;
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}
		
		
		 public static String getNameByType(String type) {
	            for (ApplyType eopen : ApplyType.values()) {
	                if (eopen.type.equals(type)) {
	                    return eopen.name;
	                }
	            }
	            return "";
	        }
	}
}
