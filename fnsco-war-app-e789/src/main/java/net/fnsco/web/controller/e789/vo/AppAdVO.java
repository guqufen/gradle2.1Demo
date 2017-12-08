package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;
import net.fnsco.order.service.ad.entity.AdDO;
import net.fnsco.order.service.ad.entity.AdDTO;

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
	private List<AdDTO> adList;
	@ApiModelProperty(value = "资讯数组", name = "newsList", example = "")
	private List<AdDTO> newsList;
	/**
	 * @return the adList
	 */
	public List<AdDTO> getAdList() {
		return adList;
	}
	/**
	 * @param adList the adList to set
	 */
	public void setAdList(List<AdDTO> adList) {
		this.adList = adList;
	}
	/**
	 * @return the newsList
	 */
	public List<AdDTO> getNewsList() {
		return newsList;
	}
	/**
	 * @param newsList the newsList to set
	 */
	public void setNewsList(List<AdDTO> newsList) {
		this.newsList = newsList;
	}
	
	
	
	

}
