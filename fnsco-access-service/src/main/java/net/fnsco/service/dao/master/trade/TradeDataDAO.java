package net.fnsco.service.dao.master.trade;

import net.fnsco.service.domain.trade.TradeData;

public interface TradeDataDAO {

    int deleteByPrimaryKey(String id);

    int insert(TradeData record);

    int insertSelective(TradeData record);

    TradeData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TradeData record);

    int updateByPrimaryKey(TradeData record);
}