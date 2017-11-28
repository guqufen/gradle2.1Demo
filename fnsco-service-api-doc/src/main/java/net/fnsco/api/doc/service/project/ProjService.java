package net.fnsco.api.doc.service.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.api.doc.service.project.dao.ProjDAO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultPageDTO;

/**
 * @desc 新增项目service
 * @author hjt
 *
 */
@Service
public class ProjService extends BaseService {
	@Autowired
	private ProjDAO projDAO;
	
	public ResultPageDTO<ProjDO> queryProj(ProjDO projDO, Integer currentPageNum, Integer pageSize){
		logger.info("开始分页查询TradeDataService.page, tradeData=" + projDO.toString());
        List<ProjDO> datas = projDAO.pageList(projDO, currentPageNum, pageSize);
        Integer total = projDAO.pageListCount(projDO);
        ResultPageDTO<ProjDO> result = new ResultPageDTO<ProjDO>(total, datas);
        result.setCurrentPage(currentPageNum);
        return result;
	}

	public void add(ProjDO projDO) {
		projDAO.insert(projDO);
	}

	public boolean exportJson(String filePath,String name, String jsonParams) {
		if (Strings.isNullOrEmpty(jsonParams)) {
			return false;
		}
		return createFile(filePath,name, jsonParams);
	}

	/**
	 * 创建文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param filecontent
	 *            文件内容
	 * @return 是否创建成功，成功则返回true
	 */
	public boolean createFile(String filePath,String name, String filecontent) {
		Boolean bool = false;
		String path = filePath + "jsonTxt/";
		String filenameTemp = path + name + ".txt";// 文件路径+名称+文件类型
		File file = new File(filenameTemp);
		try {
			// 如果文件不存在，则创建新的文件
			if (!file.exists()) {
				file.createNewFile();
				bool = true;
				logger.error("success create file,the file is " + filenameTemp);
				// 创建文件成功后，写入内容到文件里
				writeFileContent(filenameTemp, filecontent);
			}
		} catch (Exception e) {
			logger.error("创建"+ name +".txt失败 ");
			e.printStackTrace();
		}

		return bool;
	}

	/**
	 * 向文件中写入内容
	 * 
	 * @param filepath
	 *            文件路径与名称
	 * @param newstr
	 *            写入的内容
	 * @return
	 * @throws IOException
	 */
	public boolean writeFileContent(String filepath, String newstr) throws IOException {
		Boolean bool = true;
		{
			try {
				// 创建文件对象
				File fileText = new File(filepath);
				// 向文件写入对象写入信息
				FileWriter fileWriter = new FileWriter(fileText);
				// 写文件
				fileWriter.write(newstr);
				// 关闭
				fileWriter.close();
			} catch (IOException e) {
				logger.error("导入txt失败 ");
				e.printStackTrace();
			}
			return bool;
		}
	}
	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 *//*
		 * public boolean delFile(String fileName){ Boolean bool = false; filenameTemp =
		 * path+fileName+".txt"; File file = new File(filenameTemp); try {
		 * if(file.exists()){ file.delete(); bool = true; } } catch (Exception e) { //
		 * TODO: handle exception } return bool; }
		 */
}
