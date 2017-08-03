package net.fnsco.controller.app.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;

import io.swagger.annotations.ApiOperation;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.TradeReportDTO;
import net.fnsco.api.dto.TradeTurnoverDTO;
import net.fnsco.api.dto.ConsumptionDTO;
import net.fnsco.api.dto.WeeklyDTO;
import net.fnsco.api.dto.WeeklyHisDateDTO;
import net.fnsco.api.trade.TradeReportService;
import net.fnsco.core.base.BaseController;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc 交易统计APP接口类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年7月27日 上午10:36:32
 */
@RestController
@RequestMapping(value = "/app/tradeReport", method = RequestMethod.POST)
public class TradeReportController extends BaseController{
    
    @Autowired
    private TradeReportService tradeReportService;
    /**
     * queryTurnoverAndWeekly:(这里用一句话描述这个方法的作用)APP首页查询周期营业额和周报数据
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return ResultDTO<TradeReportOutlineJO>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/queryTurnoverAndWeekly")
    @ApiOperation(value = "查询APP首页营业额和周报混总数据")
    public ResultDTO<TradeTurnoverDTO> queryTurnoverAndWeekly(@RequestBody TradeReportDTO tradeReportDTO){
        if(null == tradeReportDTO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        TradeTurnoverDTO datas = tradeReportService.queryTurnovers(tradeReportDTO);
        return ResultDTO.success(datas);
    }
    
    /**
     * queryWeeklyByInnerCode:(这里用一句话描述这个方法的作用)查询某商家一段时间内的周报数据
     *
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/queryWeeklyByInnerCode")
    @ApiOperation(value = "查询商家某时间段的周报数据")
    public ResultDTO<WeeklyDTO> queryWeeklyByInnerCode(@RequestBody TradeReportDTO tradeReportDTO){
        if(Strings.isNullOrEmpty(tradeReportDTO.getInnerCode()) && null  == tradeReportDTO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_INNER_CODE_NULL);
        }
        WeeklyDTO datas = tradeReportService.queryWeeklyByInnerCode(tradeReportDTO);
        return ResultDTO.success(datas);
    }
    
    /**
     * queryWeeklyHisDate:(这里用一句话描述这个方法的作用)查询周报时间
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/queryWeeklyHisDate")
    @ApiOperation(value = "查询周报历史时间段")
    public ResultDTO<WeeklyHisDateDTO> queryWeeklyHisDate(@RequestBody TradeReportDTO tradeReportDTO){
        if(null == tradeReportDTO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
        }
        if(null == tradeReportDTO.getPageNum() || tradeReportDTO.getPageNum()<1){
            return ResultDTO.fail(ApiConstant.E_PAGE_NUM_NULL);
        }
        WeeklyHisDateDTO datas = tradeReportService.queryWeeklyHisDate(tradeReportDTO);
        return ResultDTO.success(datas);
    }
    
    /**
     * queryTrandPeak:(这里用一句话描述这个方法的作用)查询消费模式
     *
     * @param tradeReportDTO
     * @return    设定文件
     * @return ResultDTO<Object>    DOM对象
     * @throws 
     * @since  CodingExample　Ver 1.1
     */
    @RequestMapping("/queryConsumption")
    @ApiOperation(value = "H5查询消费模式")
    public ResultDTO<ConsumptionDTO>  queryConsumption(@RequestBody TradeReportDTO tradeReportDTO){
        if(Strings.isNullOrEmpty(tradeReportDTO.getInnerCode()) && null  == tradeReportDTO.getUserId()){
            return ResultDTO.fail(ApiConstant.E_INNER_CODE_NULL);
        }
        ConsumptionDTO data = tradeReportService.queryConsumption(tradeReportDTO);
        return ResultDTO.success(data);
    }
}
