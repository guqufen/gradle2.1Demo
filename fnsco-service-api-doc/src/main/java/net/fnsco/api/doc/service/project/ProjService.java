package net.fnsco.api.doc.service.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.api.doc.service.project.dao.ProjDAO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
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
        return result;
	}
	public boolean modifProj(String path,ProjDO projDO) {
		boolean bool = false; 
		int i=projDAO.update(projDO);
		if(i<1) {
			return bool;
		}
		long idl = projDO.getId();
		int id=(int)idl;
		ProjDO proj = projDAO.getById(id);
		String filePath = path+proj.getUrl();
		clearInfoForFile(filePath);
		try {
			bool = writeFileContent(filePath, projDO.getJsonStr());
		} catch (IOException e) {
			logger.error("导入json失败 ");
			e.printStackTrace();
		}
		return bool;
	}
	
	public ProjDO queryById(String path,Integer id) {
		ProjDO projDO = projDAO.getById(id);
		String filePath = path+projDO.getUrl();
		File file = new File(filePath);
		projDO.setJsonStr(readFileString(file));
		return projDO;
	}

	public int add(ProjDO projDO) {
		return projDAO.insert(projDO);
	}
	
	public int update(ProjDO projDO) {
		return projDAO.update(projDO);
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
		String filenameTemp = path + name + ".json";// 文件路径+名称+文件类型
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
			logger.error("创建"+ name +".json失败 ");
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
				logger.error("导入json失败 ");
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
	
	/**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public String readFileString(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
 // 清空已有的文件内容，以便下次重新写入新的内容
    public void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
