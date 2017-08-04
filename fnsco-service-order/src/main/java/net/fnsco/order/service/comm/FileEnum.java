package net.fnsco.order.service.comm;

import java.util.Map;
import java.util.TreeMap;

public class FileEnum {

	//商户类型
	public enum FileTypeEnum {

		INIT_OTHERS("init_others", "其他"), 
		BUSINESS_LICENSE("business_license","营业执照"), 
		TAX_REGIST_PHOTO("tax_regist_photo", "税务登记证照片"), 
		LEGALPERSON_IDCARD_PHOTO("legalperson_idcard_photo","法人身份证照片"),
		STORE_PHOTO("store_photo", "门店照片"), 
		HOUSE_RENT_CONTRACT_PHOTO("house_rent_contract_photo","房屋租赁合同照片"),
		ORG_CODE_PHOTO("org_code_photo", "组织机构代码证照片"), 
		BANK_DEBIT_CARD_PHOTO("bank_debit_card_photo","银行卡、开户许可证照片"),
		ENTERCOUNT_PERSON_IDCARD_PHOTO("entercount_person_idcard_photo", "入账人身份证照片"), 
		DEAL_PHOTO("deal_photo","签约协议照片"),
		MERCINFO_REGIST_PHOTO("mercinfo_regist_photo", "商户信息登记表照片");

		private final String code;
		private final String desc;

		FileTypeEnum(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		public Map<String, String> getMap() {
			Map<String, String> map = new TreeMap<>();
			for (FileTypeEnum mType : FileTypeEnum.values()) {
				map.put(mType.code, mType.desc);
			}
			return map;
		}

		public String getDescByCode(String code) {
			for (FileTypeEnum eType : FileTypeEnum.values()) {
				if (eType.code == code) {
					return eType.desc;
				}
			}
			return "";
		}

	}

	

}
