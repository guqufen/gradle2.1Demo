package net.fnsco.order.service.sys.dao.helper;

import java.util.Date;

import net.fnsco.order.service.sys.entity.ImportErrorDO;

/**
 * @desc
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年9月14日 下午4:33:10
 */

public class ImportErrorMsgHelper {
    
    /**
     * createImportErrorDO:(创建一个ImportErrorDO实例)
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月25日 下午4:28:14
     * @return ImportErrorDO    DOM对象
     */
    public static ImportErrorDO createImportErrorDO(Date createTime,Date startCreateTime,Date endCreateTime,Integer createUserId,Integer rowNumber,Integer importType,
                                                    String importFileName,String errorMsg,String data){
        ImportErrorDO importErrorDO = new ImportErrorDO();
        importErrorDO.setCreateTime(createTime);
        importErrorDO.setCreateUserId(createUserId);
        importErrorDO.setEndCreateTime(endCreateTime);
        importErrorDO.setStartCreateTime(startCreateTime);
        importErrorDO.setImportFileName(importFileName);
        importErrorDO.setRowNumber(rowNumber);
        importErrorDO.setImportType(importType);
        importErrorDO.setErrorMsg(errorMsg);
        importErrorDO.setData(data);
        
        return importErrorDO;
    }
}
