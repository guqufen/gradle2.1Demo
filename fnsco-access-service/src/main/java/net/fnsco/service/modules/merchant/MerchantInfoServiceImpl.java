/**
 * 
 */
package net.fnsco.service.modules.merchant;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.fnsco.api.merchant.MerchantInfoService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.service.dao.master.MerchantFileDao;
import net.fnsco.service.domain.MerchantFile;

/**@desc 文件上传实现类
 * @author tangliang
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {
	
	@Autowired
	private MerchantFileDao merchantFileInfoDao;
	
	/**
	 * @todo 录入文件信息入库
	 * @author tangliang
	 * @date 2017年6月21日 上午10:17:44
	 * @see net.fnsco.api.merchant.MerchantInfoService#doAddToDB(net.fnsco.service.domain.MerchantFile, int)
	 */
	@Override
	public ResultDTO<Integer> doAddToDB(MerchantFile fileInfo, int loginUserId) {
		
		ResultDTO<Integer> result = new ResultDTO<>();
		
		if(StringUtils.isEmpty(fileInfo.getInnerCode()) || StringUtils.isEmpty(fileInfo.getFileType()))
		{
			result.setError();
			return result.setError("添加失败");
		}	
		
		MerchantFile fileInfo_con = new MerchantFile();
		fileInfo_con.setInnerCode(fileInfo.getInnerCode());
		fileInfo_con.setFileType(fileInfo.getFileType());
		
		List<MerchantFile> files = merchantFileInfoDao.queryByCondition(fileInfo_con);
		int res_db = 0;
		if(!files.isEmpty())
		{
			files.get(0).setFilePath(fileInfo.getFilePath());
			files.get(0).setFileName(fileInfo.getFileName());
			files.get(0).setInnerCode(fileInfo.getInnerCode());
			res_db =merchantFileInfoDao.updateByPrimaryKeySelective(files.get(0));
		}	
		else
		{
			res_db = merchantFileInfoDao.insertSelective(fileInfo);
		}	
		
		
		if (res_db != 1) {
			result.setError();
			return result.setError("添加失败");
		}
		return result.setSuccess("添加成功");
	}

}
