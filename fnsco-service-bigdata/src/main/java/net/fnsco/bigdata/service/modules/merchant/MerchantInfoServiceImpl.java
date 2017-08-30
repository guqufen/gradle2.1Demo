/**
 * 
 */
package net.fnsco.bigdata.service.modules.merchant;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fnsco.bigdata.api.merchant.MerchantInfoService;
import net.fnsco.bigdata.service.dao.master.MerchantFileDao;
import net.fnsco.bigdata.service.dao.master.MerchantFileTempDao;
import net.fnsco.bigdata.service.domain.MerchantFileTemp;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.core.utils.OssUtil;

/**@desc 文件上传实现类
 * @author tangliang
 * @date 2017年6月21日 上午10:15:40
 */
@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {
	
	@Autowired
	private MerchantFileTempDao merchantFileInfoDao;
	
	@Autowired
	private MerchantFileDao merChantFileDao;
	
//	@Autowired
//	private Environment env;
	
	/**
	 * @todo 录入文件信息入库
	 * @author tangliang
	 * @date 2017年6月21日 上午10:17:44
	 * @see net.fnsco.bigdata.api.merchant.MerchantInfoService#doAddToDB(net.fnsco.bigdata.service.domain.MerchantFile, int)
	 */
	@Override
	public ResultDTO<Integer> doAddToDB(MerchantFileTemp fileInfo, int loginUserId) {
		
		ResultDTO<Integer> result = new ResultDTO<>();
		
		if(StringUtils.isEmpty(fileInfo.getInnerCode()) || StringUtils.isEmpty(fileInfo.getFileType()))
		{
			return result.fail();
		}	
		
		MerchantFileTemp fileInfo_con = new MerchantFileTemp();
		fileInfo_con.setInnerCode(fileInfo.getInnerCode());
		fileInfo_con.setFileType(fileInfo.getFileType());
		
		List<MerchantFileTemp> files = merchantFileInfoDao.queryByCondition(fileInfo_con);
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
			return result.fail();
		}
		List<MerchantFileTemp> fileTemp = merchantFileInfoDao.queryByCondition(fileInfo_con);
		return result.success(fileTemp.get(0).getId());
	}
	
	/**
	 * @todo 删除文件数据
	 * @author tangliang
	 * @date 2017年6月27日 下午5:12:40
	 * @see net.fnsco.bigdata.api.merchant.MerchantInfoService#deleteFromDB(java.lang.Integer)
	 */
	@Transactional
	@Override
	public boolean deleteFromDB(Integer id,String url) {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(url))
		{
			String fileKey = url.substring(url.lastIndexOf("^")+1);
			String nativeFile = url.substring(0, url.indexOf("^"));
			if(FileUtils.ifExist(nativeFile)){
			    FileUtils.delFile(nativeFile);
			}
			/**
			 * 删除OSS上图片
			 * 
			 */
			OssUtil.deleteFile(OssUtil.getHeadBucketName(), fileKey);
		}	
		int res = merChantFileDao.deleteByPrimaryKey(id);
		int re =  merchantFileInfoDao.deleteByPrimaryKey(id);
		
		if(re != 0 || res !=0)
		{
			return true;
		}	
		return false;
	}
}
