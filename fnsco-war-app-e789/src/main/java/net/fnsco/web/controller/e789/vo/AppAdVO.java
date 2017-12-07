package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;
import net.fnsco.order.service.ad.entity.AdDO;

/**
 * @desc 
 * @author   
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 
 */

public class AppAdVO extends VO {
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 6314011364816553608L;
	
	
	@ApiModelProperty(value = "广告数组", name = "adList", example = "")
	private List<AdDO> adList;
	@ApiModelProperty(value = "资讯数组", name = "newsList", example = "")
	private List<AdDO> newsList;
	/**
	 * @return the adList
	 */
	public List<AdDO> getAdList() {
		return adList;
	}
	/**
	 * @param adList the adList to set
	 */
	public void setAdList(List<AdDO> adList) {
		this.adList = adList;
	}
	/**
	 * @return the newsList
	 */
	public List<AdDO> getNewsList() {
		return newsList;
	}
	/**
	 * @param newsList the newsList to set
	 */
	public void setNewsList(List<AdDO> newsList) {
		this.newsList = newsList;
	}
	
	
	
	

}
