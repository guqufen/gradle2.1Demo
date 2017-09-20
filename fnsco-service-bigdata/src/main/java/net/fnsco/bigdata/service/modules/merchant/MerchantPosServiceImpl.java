package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.api.dto.WebMerchantPosDTO;
import net.fnsco.bigdata.api.dto.WebMerchantTerminalDTO;
import net.fnsco.bigdata.api.merchant.MerchantPosService;
import net.fnsco.bigdata.service.dao.master.MerchantBankDao;
import net.fnsco.bigdata.service.dao.master.MerchantChannelDao;
import net.fnsco.bigdata.service.dao.master.MerchantPosDao;
import net.fnsco.bigdata.service.dao.master.MerchantTerminalDao;
import net.fnsco.bigdata.service.domain.MerchantBank;
import net.fnsco.bigdata.service.domain.MerchantChannel;
import net.fnsco.bigdata.service.domain.MerchantPos;
import net.fnsco.bigdata.service.domain.MerchantTerminal;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;

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
    @Autowired
    private MerchantBankDao merchantBankDao;
    
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
    public Integer insertPos(MerchantPos record) {
        
        // TODO Auto-generated method stub
        int i=merchantPosDao.insert(record);
        if(i!=1) {
        	return 0;
        }
        return record.getId();
        
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
     * @see net.fnsco.bigdata.api.merchant.MerchantPosService#savePosInfo(java.util.List)
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
                    updatePosInfoToDB(webMerchantPosDTO.getPosInfos(),merChannel.getId());
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
    @Transactional
    private void updatePosInfoToDB(List<WebMerchantTerminalDTO> posInfos,Integer channelId){
        for (WebMerchantTerminalDTO webMerchantTerminalDTO : posInfos) {
            MerchantPos merchantPos = webMerchantTerminalDTO.getMerchantPos();
            if(null != merchantPos){
                merchantPos.setChannelId(channelId);
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
    @Transactional
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
     * @see net.fnsco.bigdata.api.merchant.MerchantPosService#deleteByPosId(java.lang.Integer)
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
    
    /**
     * (non-Javadoc)获取所有银行卡信息
     * @see net.fnsco.bigdata.api.merchant.MerchantPosService#queryWebByInnerCode(java.lang.String)
     * @author tangliang
     * @date 2017年8月18日 下午5:45:51
     */
    @Override
    public List<MerchantBank> queryWebByInnerCode(String innerCode) {
        return merchantBankDao.queryWebByInnerCode(innerCode);
    }
    @Override
    public List<MerchantPos> selectBySnCode(String snCode){
        return merchantPosDao.selectBySnCode(snCode);
    }
}
