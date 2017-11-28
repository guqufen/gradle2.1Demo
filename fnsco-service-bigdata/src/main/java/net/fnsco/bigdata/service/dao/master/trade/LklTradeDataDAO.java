package net.fnsco.bigdata.service.dao.master.trade;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.fnsco.bigdata.service.domain.trade.TradeDataLkl;
import net.fnsco.core.base.PageDTO;

/**
 * 
 * @desc
 * @author   tangliang
 * @version  
 * @since    Ver 1.1
 * @Date	 2017 2017年6月28日 下午3:42:26
 *
 */
public interface LklTradeDataDAO {

    int deleteByPrimaryKey(String id);

    int insert(TradeDataLkl record);

    int insertSelective(TradeDataLkl record);

    TradeDataLkl selectByPrimaryKey(String id);

    TradeDataLkl selectByMd5(String md5);

    int updateByPrimaryKeySelective(TradeDataLkl record);

    int updateByPrimaryKey(TradeDataLkl record);

    /**
     * 条件分页查询
     */
    List<TradeDataLkl> queryPageList(PageDTO<TradeDataLkl> pages);
    
    List<TradeDataLkl> queryByAllCondition(TradeDataLkl record);
    
    /**
     * 条件查询总数
     * @param pages
     * @return
     */
    int queryTotalByCondition(TradeDataLkl record);

    
    /**
     * 条件查询金额
     * @param pages
     * @return
     */
    String queryTotalAmount(TradeDataLkl record);
    /**
     * queryByCondition:(这里用一句话描述这个方法的作用)
     *
     * @param record
     * @return    设定文件
     * @return List<TradeDataLkl>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    List<TradeDataLkl> queryTempByCondition(TradeDataLkl record);

    /**
     * 
     * selectByIRT:(根据内部商务号终端号参数考)
     *
     * @param innerCode
     * @param referNo
     * @param termId
     * @return   TradeDataLkl    返回Result对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    TradeDataLkl selectByIRT(TradeDataLkl record);
    
    /**
     * 通过渠道终端号商户号和订单号查找交易
     * @param record
     * @return
     */
    TradeDataLkl selectByCMT(TradeDataLkl record);
    
    int selectCountByIRT(TradeDataLkl record);
    
    Map querySumByCondition(TradeDataLkl record);

	String queryByCertifyId(@Param("certifyId") String certifyId,@Param("cardTotalLength") String cardTotalLength);
}