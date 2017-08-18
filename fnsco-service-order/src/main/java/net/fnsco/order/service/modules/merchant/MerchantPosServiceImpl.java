package net.fnsco.order.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.order.api.dto.WebMerchantPosDTO;
import net.fnsco.order.api.dto.WebMerchantTerminalDTO;
import net.fnsco.order.api.merchant.MerchantPosService;
import net.fnsco.order.service.dao.master.MerchantChannelDao;
import net.fnsco.order.service.dao.master.MerchantPosDao;
import net.fnsco.order.service.dao.master.MerchantTerminalDao;
import net.fnsco.order.service.domain.MerchantChannel;
import net.fnsco.order.service.domain.MerchantPos;
import net.fnsco.order.service.domain.MerchantTerminal;

/**
 * @desc POS相关业务实现类
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月17日 上午9:28:55
 */
@Service
public class MerchantPosServiceImpl extends BaseService implements MerchantPosService {
    
    @Autowired
    private MerchantPosDao merchantPosDao;
    @Autowired
    private MerchantChannelDao merchantChannelDao;
    @Autowired
    private MerchantTerminalDao merchantTerminalDao;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.deleteByPrimaryKey(id);
        
    }

    @Override
    public int insert(MerchantPos record) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.insert(record);
        
    }

    @Override
    public int insertSelective(MerchantPos record) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.insertSelective(record);
        
    }

    @Override
    public MerchantPos selectByPrimaryKey(Integer id) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.selectByPrimaryKey(id);
        
    }

    @Override
    public int updateByPrimaryKeySelective(MerchantPos record) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.updateByPrimaryKeySelective(record);
        
    }

    @Override
    public int updateByPrimaryKey(MerchantPos record) {
        
        // TODO Auto-generated method stub
        return merchantPosDao.updateByPrimaryKey(record);
        
    }
    
    /**
     * (non-Javadoc)保存POS信息业务
     * @see net.fnsco.order.api.merchant.MerchantPosService#savePosInfo(java.util.List)
     * @author tangliang
     * @date 2017年8月17日 上午11:43:59
     */
    @Transactional
    @Override
    public ResultDTO<String> savePosInfo(List<WebMerchantPosDTO> record) {
        
        for (WebMerchantPosDTO webMerchantPosDTO : record) {
            MerchantChannel  merChannel = webMerchantPosDTO.getMerChannel();
            if(null != merChannel){
                if(null != merChannel.getId()){
                    merchantChannelDao.updateByPrimaryKeySelective(merChannel);
                    updatePosInfoToDB(webMerchantPosDTO.getPosInfos());
                }else{
                    merchantChannelDao.insertSelective(merChannel);
                    if(null != merChannel.getId()){
                        addPosInfoToDB(webMerchantPosDTO.getPosInfos(),merChannel.getId());
                    }
                }
            }
        }
        return ResultDTO.successForSave(null);
        
    }
    /**
     * updatePosInfoToDB:(这里用一句话描述这个方法的作用)更新操作
     * @param posInfos
     * @param channelId    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午2:25:34
     * @return void    DOM对象
     */
    private void updatePosInfoToDB(List<WebMerchantTerminalDTO> posInfos){
        for (WebMerchantTerminalDTO webMerchantTerminalDTO : posInfos) {
            MerchantPos merchantPos = webMerchantTerminalDTO.getMerchantPos();
            if(null != merchantPos){
                if(null != merchantPos.getId()){
                    this.updateByPrimaryKeySelective(merchantPos);
                }else{
                    this.insertSelective(merchantPos);
                }
                List<MerchantTerminal>  terminals = webMerchantTerminalDTO.getTerminals();
                for (MerchantTerminal merchantTerminal : terminals) {
                    merchantTerminal.setPosId(merchantPos.getId());
                    if(merchantTerminal.getId() != null){
                        merchantTerminalDao.updateByPrimaryKeySelective(merchantTerminal);
                    }else{
                        merchantTerminalDao.insertSelective(merchantTerminal);
                    }
                }
            }
        }
    }
    
    /**
     * addPosInfoToDB:(这里用一句话描述这个方法的作用)
     * @param posInfos    设定文件
     * @author    tangliang
     * @date      2017年8月17日 下午1:31:21
     * @return void    DOM对象
     */
    private void addPosInfoToDB(List<WebMerchantTerminalDTO> posInfos,Integer channelId){
        for (WebMerchantTerminalDTO webMerchantTerminalDTO : posInfos) {
            MerchantPos merchantPos = webMerchantTerminalDTO.getMerchantPos();
            if(null != merchantPos){
                merchantPos.setChannelId(channelId);
                insertSelective(merchantPos);
                if(null != merchantPos.getId()){
                    List<MerchantTerminal>  terminals = webMerchantTerminalDTO.getTerminals();
                    for (MerchantTerminal merchantTerminal : terminals) {
                        merchantTerminal.setPosId(merchantPos.getId());
                    }
                    merchantTerminalDao.insertBatch(terminals);
                }
            }
        }
    }
    /**
     * (non-Javadoc)删除关联数据
     * @see net.fnsco.order.api.merchant.MerchantPosService#deleteByPosId(java.lang.Integer)
     * @author tangliang
     * @date 2017年8月17日 下午2:15:07
     */
    @Transactional
    @Override
    public boolean deleteByPosId(Integer posId) {
        
        int i = merchantTerminalDao.deleteByPosId(posId);
        int j = deleteByPrimaryKey(posId);
        if(i+j == 0){
            return false;
        }
        return true;
    }
}
