package net.fnsco.core.utils;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

 

public class CreateFileUtils {

	 protected static Logger              logger = LoggerFactory.getLogger(CreateFileUtils.class.getClass());

    public static Map<String ,String> filePath(MultipartFile file, String fileName,Environment env) {
        String line = System.getProperty("file.separator");// 文件分割符
        // 保存文件的路径
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 数据库存的路径
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String stry = env.getProperty("fileUpload.url") + line + year;// +"\\"+month+"\\";
        File yearPath = new File(stry);
        // 如果文件夹不存在则创建
        if (!yearPath.exists()) {
            logger.info("年份目录不存在");
            yearPath.mkdirs();
        } else {
            logger.info("年份目录已存在");
        }

        String strm = env.getProperty("fileUpload.url") + line + year + line + month + line;
        File monthPath = new File(strm);
        if (!monthPath.exists()) {
            logger.info("月份目录不存在");
            monthPath.mkdirs();
        } else {
            logger.info("月份目录已存在");
        }
        
        String yearMonthPath = year + line + month + line;
        String newFileName =System.currentTimeMillis() + "." + prefix;
        String fileKey = year + "/" + month + "/" + newFileName;
        String filepath = yearMonthPath + newFileName;
        String fileURL = env.getProperty("fileUpload.url") + line + filepath;
        Map<String ,String> map = new HashMap<String ,String>();
        map.put("fileKey", fileKey);
        map.put("fileURL", fileURL);
        return map;
    }
}
