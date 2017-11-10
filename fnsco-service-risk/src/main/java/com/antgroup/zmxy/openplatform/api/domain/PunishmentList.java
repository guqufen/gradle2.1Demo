package com.antgroup.zmxy.openplatform.api.domain;

import java.util.List;

import com.antgroup.zmxy.openplatform.api.ZhimaObject;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiField;
import com.antgroup.zmxy.openplatform.api.internal.mapping.ApiListField;

/**
 * 处罚信息列表
 *
 * @author auto create
 * @since 1.0, 2017-03-21 23:22:51
 */
public class PunishmentList extends ZhimaObject {

	private static final long serialVersionUID = 8656599995893129112L;

	/** 
	 * null
	 */
	@ApiListField("punishment_list")
	@ApiField("punishment")
	private List<Punishment> punishmentList;

	public void setPunishmentList(List<Punishment> punishmentList) {
		this.punishmentList = punishmentList;
	}
	public List<Punishment> getPunishmentList( ) {
		return this.punishmentList;
	}

}
