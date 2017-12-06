package net.fnsco.web.controller.e789.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月6日 下午1:37:37
 */

public class TradeDataVO extends VO {
	
	@ApiModelProperty(value="当前页码",example="当前页码")
	private Integer currentPageNum;
	@ApiModelProperty(value="页码大小",example="页码大小")
	private Integer pageSize;
	@ApiModelProperty(value="总页码",example="总页码")
	private Integer totalPage;
	
	private List<TradeDataListVO>  datas;
	/**
	 * currentPageNum
	 *
	 * @return  the currentPageNum
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getCurrentPageNum() {
		return currentPageNum;
	}
	/**
	 * currentPageNum
	 *
	 * @param   currentPageNum    the currentPageNum to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	/**
	 * pageSize
	 *
	 * @return  the pageSize
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * pageSize
	 *
	 * @param   pageSize    the pageSize to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * totalPage
	 *
	 * @return  the totalPage
	 * @since   CodingExample Ver 1.0
	*/
	
	public Integer getTotalPage() {
		return totalPage;
	}
	/**
	 * totalPage
	 *
	 * @param   totalPage    the totalPage to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * datas
	 *
	 * @return  the datas
	 * @since   CodingExample Ver 1.0
	*/
	
	public List<TradeDataListVO> getDatas() {
		return datas;
	}
	/**
	 * datas
	 *
	 * @param   datas    the datas to set
	 * @since   CodingExample Ver 1.0
	 */
	
	public void setDatas(List<TradeDataListVO> datas) {
		this.datas = datas;
	}
	
}
