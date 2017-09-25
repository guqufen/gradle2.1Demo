package net.fnsco.order.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.core.base.BaseService;
import net.fnsco.order.service.sys.dao.ImportErrorDAO;
import net.fnsco.order.service.sys.entity.ImportErrorDO;

/**
 * @desc excel上传实现类
 * @author hjt
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class TradeDataImportService extends BaseService {
    @Autowired
    private ImportErrorDAO importErrorDAO;
    public void saveImportError(ImportErrorDO importError){
        importErrorDAO.insert(importError);
    }
}
