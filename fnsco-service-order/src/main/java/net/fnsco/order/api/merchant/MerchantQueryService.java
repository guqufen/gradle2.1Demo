package net.fnsco.order.api.merchant;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.fnsco.order.service.domain.MerchantTerminal;

public interface MerchantQueryService {
    List<MerchantTerminal> selectTerminalByIdList(@Param("innerIdList") List<Integer> innerIdList);
}