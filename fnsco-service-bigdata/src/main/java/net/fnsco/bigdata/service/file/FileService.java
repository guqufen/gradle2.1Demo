package net.fnsco.bigdata.service.file;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.core.utils.OssLoaclUtil;

/**
 * @desc 
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年12月27日 下午2:34:41
 */
@Service
public class FileService extends BaseService {
	
	@Autowired
	private Environment env;
	
	/**
	 * getFileByteArray:(获取文件流的字节码数组)
	 *
	 * @param  @param fileRelativePath
	 * @param  @return    设定文件
	 * @return byte[]    DOM对象
	 * @author tangliang
	 * @date   2017年12月27日 下午2:40:51
	 */
	public byte[] getFileByteArray(String fileRelativePath) {
		
		if(Strings.isNullOrEmpty(fileRelativePath)) {
			return null;
		}
		
		//获取本地绝对路径
		String line = System.getProperty("file.separator");
		String absolutePath = this.env.getProperty("fileUpload.url")+line+fileRelativePath;
		
		/**
		 * 本地存在文件的情况下，取本地文件,本地不存在文件时，先去OSS下载本地，再输出给页面
		 */
		if(!FileUtils.ifExist(absolutePath)) {
			OssLoaclUtil.getFileToLocal(OssLoaclUtil.getHeadBucketName(), fileRelativePath, absolutePath);
		}
		
		File image = new File(absolutePath);
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(image);
			int length = inputStream.available();
			byte data[] = new byte[length];
			inputStream.read(data);
			return data;
		} catch (Exception e) {
			logger.error("获取文件流出错!",e);
		}
		return null;
	}
}
