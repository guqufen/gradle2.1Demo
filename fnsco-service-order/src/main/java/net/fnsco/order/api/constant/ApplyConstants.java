package net.fnsco.order.api.constant;



public class ApplyConstants {
	public static enum ApplyType {
		BUY_CAR_TYPE("01", "买车申请"), LOAN_APPLY_TYPE("02", "贷款申请"), INSURANCE_APPLY_TYPE("03", "保险申请"), MANAGE_APPLY_TYPE("04", "理财申请");

		private String code;
		private String name;

		private ApplyType(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
