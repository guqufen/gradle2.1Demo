/**
 * 
 */
package net.fnsco.service.modules.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.merchant.MerchantService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.MerchantFileDao;
import net.fnsco.service.domain.MerchantFile;

/**@desc 商户相关操作
 * @author sxfei
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantFileDao merchantFileInfoDao;

    /**
     * @todo 录入文件信息入库
     * @author tangliang
     * @date 2017年6月21日 上午10:17:44
     * @see net.fnsco.api.merchant.MerchantInfoService#doAddToDB(net.fnsco.service.domain.MerchantFile, int)
     */
    public ResultDTO<Integer> doAddToDB(MerchantFile fileInfo, int loginUserId) {

        ResultDTO<Integer> result = new ResultDTO<>();

        int res_db = merchantFileInfoDao.insertSelective(fileInfo);

        if (res_db != 1) {
            result.setError();
            return result.setError("添加失败");
        }
        return result.setSuccess("添加成功");
    }

}
