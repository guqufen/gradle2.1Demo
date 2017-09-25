package net.fnsco.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;

public class ReadExcel {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 总行数
	private int totalRows = 0;
	// 总条数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;

	// 构造方法
	public ReadExcel() {
	}

	// 获取总行数
	public int getTotalRows() {
		return totalRows;
	}

	// 获取总列数
	public int getTotalCells() {
		return totalCells;
	}

	// 获取错误信息
	public String getErrorInfo() {
		return errorMsg;
	}
	
	/**
     * 下载模板
     *
     * @param filePath
     * @param response
     * @throws IOException
     */
    public static void downTemplate(String filePath, String fileName, HttpServletResponse response) throws IOException {
        // 设置response的编码方式
        response.setContentType("application/x-msdownload");
        // 写明要下载的文件的大小
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "iso8859-1"));
        FileInputStream in = null;
        OutputStream out = null;
        
        try {
        	in = new FileInputStream(filePath);
        	out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
        } finally {
        	if (in!=null) {
        		// 关闭文件输入流
        		in.close();
        	}
        	if (out!=null) {
        		// 关闭输出流
        		out.close();
        	}
        }
    }
	
	/**
	 * 验证EXCEL文件
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null || !(StringUtil.isExcel2003(filePath) || StringUtil.isExcel2007(filePath))) {
			errorMsg = "文件名不是excel格式";
			return false;
		}
		return true;
	}

	/**
	 * 读EXCEL文件，获取客户信息集合
	 * 
	 * @param fielName
	 * @return
	 */
	public List<Object[]> getExcelInfo(String fileName, MultipartFile Mfile){

		// 把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
		// CommonsMultipartFile cf= (CommonsMultipartFile)Mfile;
		/*
		 * File file = new File("D:\\import"); //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		 * if (!file.exists()) file.mkdirs(); //新建一个文件 File file1 = new
		 * File("D:\\import" + new Date().getTime() + ".xls"); //将上传的文件写入新建的文件中 try {
		 * cf.getFileItem().write(file1); } catch (Exception e) { e.printStackTrace(); }
		 */

		// 初始化客户信息的集合
		List<Object[]> customerList = new ArrayList<Object[]>();
		// 初始化输入流
		InputStream is = null;
		try {
			// 验证文件名是否合格
			if (!validateExcel(fileName)) {
				return null;
			}
			// 根据文件名判断文件是2003版本还是2007版本
			boolean isExcel2003 = true;
			if (StringUtil.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			// 根据新建的文件实例化输入流
			// is = new FileInputStream(file1);\
			is = Mfile.getInputStream();
			// 根据excel里面的内容读取客户信息
			customerList = getExcelInfo(is, isExcel2003);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return customerList;
	}

	/**
	 * 根据excel里面的内容读取客户信息
	 * 
	 * @param is
	 *            输入流
	 * @param isExcel2003
	 *            excel是2003还是2007版本
	 * @return
	 * @throws IOException
	 */
	public List<Object[]> getExcelInfo(InputStream is, boolean isExcel2003) {
		List<Object[]> customerList = null;
		try {
			/** 根据版本选择创建Workbook的方式 */
			Workbook wb = null;
			// 当excel是2003时
			if (isExcel2003) {
				wb = new HSSFWorkbook(is);
			} else {// 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			// 读取Excel里面客户的信息
			customerList = readExcelValue(wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	/**
	 * 读取Excel里面客户的信息
	 * 
	 * @param wb
	 * @return
	 */
	private List<Object[]> readExcelValue(Workbook wb) {
		Sheet sheet = wb.getSheetAt(0);
        // 解析公式结果
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        List<Object[]> list = Lists.newArrayList();
        int minRowIx = sheet.getFirstRowNum() + 2;
        int maxRowIx = sheet.getLastRowNum();
        Row row1 = sheet.getRow(minRowIx);
        Row row2 = sheet.getRow(sheet.getFirstRowNum());
        short minColIx = row1.getFirstCellNum();
        short maxColIx = (short)0;
        if (maxColIx == 0) {
            maxColIx = row2.getLastCellNum();
        } else {
            maxColIx = (short) (maxColIx + minColIx);
        }

        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            // StringBuilder sb = new StringBuilder();
            Object[] ojbs = new Object[maxColIx+1];
            int i = 0;
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                Cell cell = row.getCell(Integer.valueOf(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    if (colIx != maxColIx) {
                        // sb.append(SEPARATOR + "null");
                        ojbs[i] = null;
                    }
                    i++;
                    continue;
                }
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {

                    case Cell.CELL_TYPE_BOOLEAN:
                        // sb.append(SEPARATOR + cellValue.getBooleanValue());
                        ojbs[i] = cellValue.getBooleanValue();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                        if (DateUtil.isCellDateFormatted(cell)) {
                            // sb.append(SEPARATOR + cell.getDateCellValue());
                            ojbs[i] = cell.getDateCellValue();
                        } else {
                            Double doubleVal = cellValue.getNumberValue();
                            Object inputValue = null;
                            long longVal = Math.round(cell.getNumericCellValue());
                            if (Double.parseDouble(longVal + ".0") == doubleVal)
                                inputValue = longVal;
                            else
                                inputValue = doubleVal;
                            // sb.append(SEPARATOR +inputValue);
                            ojbs[i] = inputValue;
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        // sb.append(SEPARATOR + cellValue.getStringValue());
                        ojbs[i] = cellValue.getStringValue();
                        break;
                    case Cell.CELL_TYPE_FORMULA://公式类型
                        ojbs[i] = cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
                
                i++;
            }
            logger.info("正在处理数据"+rowIx);
            list.add(ojbs);
        }
        return list;
	}
}