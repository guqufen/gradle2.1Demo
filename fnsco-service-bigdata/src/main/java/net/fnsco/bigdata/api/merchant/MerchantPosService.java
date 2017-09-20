package net.fnsco.bigdata.api.merchant;

import java.util.List;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.core.base.ResultDTO;

/**
 * @desc POS相关业务接口
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午9:28:06
 */
public interface MerchantPosService {
    
    int deleteByPrimaryKey(Integer id);

    int insert(MerchantPos record);

    int insertSelective(MerchantPos record);

    MerchantPos selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantPos record);

    int updateByPrimaryKey(MerchantPos record);
    
    /**
     * savePosInfo:(这里用一句话描述这个方法的作用)保存POS机信息
     * @param record
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 上午11:43:17
     * @return ResultDTO<String>    DOM对象
     */
    ResultDTO<String> savePosInfo(List<WebMerchantPosDTO> record);
    /**
     * deleteByPosId:(这里用一句话描述这个方法的作用)根据id删除关联终端等信息
     * @param posId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:14:30
     * @return boolean    DOM对象
     */
    boolean deleteByPosId(Integer posId);
    
    /**
     * queryWebByInnerCode:(这里用一句话描述这个方法的作用)
     * @param innerCode
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月18日 下午5:45:19
     * @return List<MerchantBank>    DOM对象
     */
    List<MerchantBank> queryWebByInnerCode(String innerCode);

	Integer insertPos(MerchantPos record);
	/**
	 * 
	 * selectBySnCode:(根据sncode查询pos信息列表)
	 *
	 * @param snCode
	 * @return   List<MerchantPos>    返回Result对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	List<MerchantPos> selectBySnCode(String snCode);
}
