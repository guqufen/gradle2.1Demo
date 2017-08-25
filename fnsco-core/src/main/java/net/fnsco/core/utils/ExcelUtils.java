package net.fnsco.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtils {
	
	/**
	 * 生成Excel 单行表头
	 * @param columnNum 列数
	 * @param itemMarks 列头名称 对应 Map中的Key
	 * @param itemParaps 列头名称
	 * @param list 数据
	 * @return
	 */
	public static HSSFWorkbook getInputStreamNoTitle(int columnNum, String[] itemMarks, String[] itemParaps, List list,String title){
        HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
        HSSFSheet sheet = workbook.createSheet();                       // 创建工作表  
//        sheet.setDefaultColumnWidth ((short)20);                        // 设置工作表列宽  
//        sheet.setDefaultRowHeight((short)500);                           // 设置工作表行高  
        sheet.autoSizeColumn((short)30);
        

        
        //sheet样式定义
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);		//获取列头样式对象  
        HSSFCellStyle style = getStyle(workbook);                  		//单元格样式对象  
 
        
        
        // 产生表格标题行  
        HSSFRow rowm = sheet.createRow(0);  
        HSSFCell cellTiltle = rowm.createCell(0); 
//        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (itemMarks.length-1)));    
        cellTiltle.setCellStyle(columnTopStyle);  
//        cellTiltle.setCellValue(title); 
 
        
        
        
        //设置列头  
        HSSFRow row1 = sheet.createRow((short)0);               		// 在索引0的位置创建行(最顶端的行)  
        HSSFCell cell1 = null;                                  		// 在索引0的位置创建单元格(左上端)  
        // 将列头设置到sheet的单元格中  
        for(int n=0;n<columnNum;n++){  
            cell1 = row1.createCell((short)(n));                		//创建列头对应个数的单元格  
            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);       		//设置列头单元格的数据类型  
            try {
				cell1.setCellValue(new String(itemParaps[n].getBytes("utf-8"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}                       									//设置列头单元格的值  
            cell1.setCellStyle(columnTopStyle);                 		//设置列头单元格样式  
        }  
        //将查询出的数据设置到sheet对应的单元格中   
        if(list != null){
	        for(int i=0;i<list.size();i++){  
	        	Map map = (Map)list.get(i);									//遍历每个对象 
	            //创建行(从下面的i+1要注意,第0行是列头,因此创建新行要从下一行开始)  
	            HSSFRow row = sheet.createRow(i+1);                         //创建所需的行数  
	            for(short j=0;j<columnNum;j++){  
	            	
	            	HSSFCell cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            	
	            	
	            	
	                if(map.get(itemMarks[j])!=null){
	                	//
	                	if(map.get(itemMarks[j]) instanceof Integer&&(Integer)map.get(itemMarks[j])==0){
		            		
	                		cell.setCellValue("");
	                	}else{	                		
	                		cell.setCellValue(map.get(itemMarks[j]).toString());        //设置单元格的值
	                	}
		            	
	                }else{
	                	cell.setCellValue("");
	                }
	                cell.setCellStyle(style);                                   //设置单元格样式  
	            }     
	        }
        }
     //   ByteArrayOutputStream baos = new ByteArrayOutputStream();
       // ByteArrayInputStream  is = null;
        
       
       /* try {
			workbook.write(baos);
			byte[] content = baos.toByteArray(); 
			is = new ByteArrayInputStream(content,0,content.length);
	        baos.flush();        // 缓冲  
			baos.close(); // 关闭流         
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
        return workbook;
	}
	
	
	/**
	 * 生成Excel
	 * @param columnNum 列数
	 * @param itemMarks 列头名称 对应 Map中的Key
	 * @param itemParaps 列头名称
	 * @param list 数据
	 * @return
	 */
	public static HSSFWorkbook getInputStream(int columnNum, String[] itemMarks, String[] itemParaps, List list,String title){
        HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
        HSSFSheet sheet = workbook.createSheet();                       // 创建工作表  
//        sheet.setDefaultColumnWidth ((short)20);                        // 设置工作表列宽  
//        sheet.setDefaultRowHeight((short)500);                           // 设置工作表行高  
        sheet.autoSizeColumn((short)30);
        

        
        //sheet样式定义
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);		//获取列头样式对象  
        HSSFCellStyle style = getStyle(workbook);                  		//单元格样式对象  
 
        
        
        // 产生表格标题行  
        HSSFRow rowm = sheet.createRow(0);  
        HSSFCell cellTiltle = rowm.createCell(0); 
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (itemMarks.length-1)));    
        cellTiltle.setCellStyle(columnTopStyle);  
        cellTiltle.setCellValue(title); 
 
        
        
        
        //设置列头  
        HSSFRow row1 = sheet.createRow((short)2);               		// 在索引0的位置创建行(最顶端的行)  
        HSSFCell cell1 = null;                                  		// 在索引0的位置创建单元格(左上端)  
        // 将列头设置到sheet的单元格中  
        for(int n=0;n<columnNum;n++){  
            cell1 = row1.createCell((short)(n));                		//创建列头对应个数的单元格  
            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);       		//设置列头单元格的数据类型  
            try {
				cell1.setCellValue(new String(itemParaps[n].getBytes("utf-8"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}                       									//设置列头单元格的值  
            cell1.setCellStyle(columnTopStyle);                 		//设置列头单元格样式  
        }  
        //将查询出的数据设置到sheet对应的单元格中   
        if(list != null){
	        for(int i=0;i<list.size();i++){  
	        	Map map = (Map)list.get(i);									//遍历每个对象 
	            //创建行(从下面的i+1要注意,第0行是列头,因此创建新行要从下一行开始)  
	            HSSFRow row = sheet.createRow(i+3);                         //创建所需的行数  
	            for(short j=0;j<columnNum;j++){  
	            	
	            	HSSFCell cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            	
	            	
	            	
	                if(map.get(itemMarks[j])!=null){
	                	//
	                	if(map.get(itemMarks[j]) instanceof Integer&&(Integer)map.get(itemMarks[j])==0){
		            		
	                		cell.setCellValue("");
	                	}else{	                		
	                		cell.setCellValue(map.get(itemMarks[j]).toString());        //设置单元格的值
	                	}
		            	
	                }else{
	                	cell.setCellValue("");
	                }
	                cell.setCellStyle(style);                                   //设置单元格样式  
	            }     
	        }
        }
     //   ByteArrayOutputStream baos = new ByteArrayOutputStream();
       // ByteArrayInputStream  is = null;
        
       
       /* try {
			workbook.write(baos);
			byte[] content = baos.toByteArray(); 
			is = new ByteArrayInputStream(content,0,content.length);
	        baos.flush();        // 缓冲  
			baos.close(); // 关闭流         
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
        return workbook;
	}
	
	
	
	
	
	
	
	/**
	 * 生成双公示Excel（注意与上方法有点不同）
	 * @param columnNum 列数
	 * @param itemMarks 列头名称 对应 Map中的Key
	 * @param itemParaps 列头名称
	 * @param list 数据
	 * @return
	 */
	public static HSSFWorkbook getInputStreamSGS(int columnNum, String[] itemMarks, String[] itemParaps, List list,String title){
        HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
        HSSFSheet sheet = workbook.createSheet();                       // 创建工作表  
        sheet.setDefaultColumnWidth ((short)20);                        // 设置工作表列宽  
        sheet.setDefaultRowHeight((short)10);                           // 设置工作表行高  


        
        //sheet样式定义
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);		//获取列头样式对象  
        HSSFCellStyle style = getStyle(workbook);                  		//单元格样式对象  
 
        
        
        // 产生表格标题行  
        HSSFRow rowm = sheet.createRow(0);  
        HSSFCell cellTiltle = rowm.createCell(0); 
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (itemParaps.length-1)));    
        cellTiltle.setCellStyle(columnTopStyle);  
        cellTiltle.setCellValue(title); 
 
        
        
        
        //设置列头  
        HSSFRow row1 = sheet.createRow((short)2);               		// 在索引0的位置创建行(最顶端的行)  
        HSSFCell cell1 = null;                                  		// 在索引0的位置创建单元格(左上端)  
        // 将列头设置到sheet的单元格中  
        for(int n=0;n<columnNum;n++){  
            cell1 = row1.createCell((short)(n));                		//创建列头对应个数的单元格  
            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);       		//设置列头单元格的数据类型  
            try {
				cell1.setCellValue(new String(itemParaps[n].getBytes("utf-8"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}                       									//设置列头单元格的值  
            cell1.setCellStyle(columnTopStyle);                 		//设置列头单元格样式  
        }  
        //将查询出的数据设置到sheet对应的单元格中   
        if(list != null){
	        for(int i=0;i<list.size();i++){  
	        	Map map = (Map)list.get(i);									//遍历每个对象 
	            //创建行(从下面的i+1要注意,第0行是列头,因此创建新行要从下一行开始)  
	            HSSFRow row = sheet.createRow(i+3);                         //创建所需的行数  
	            
	            //项目名称
	            HSSFCell cell = row.createCell(0,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            //cell.setCellValue(map.get(itemMarks[1])+"车牌号为："+map.get(itemMarks[0])+"车辆申请载运大件"+map.get(itemMarks[2])+"行驶河南省普通公路行政许可案");        //设置单元格的值
	            cell.setCellValue("在省、自治区范围内跨设区的市进行超限运输");        //设置单元格的值
	            cell.setCellStyle(style);      
	            //设定依据
	            HSSFCell cell2 = row.createCell(1,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            cell2.setCellValue("《公路安全保护条例》第三十六条第二款");        //设置单元格的值
	            cell2.setCellStyle(style);  
	            //行政许可结果
	            DecimalFormat dft = new DecimalFormat("0000000");
	            String passportCard=dft.format(new BigDecimal(map.get(itemMarks[11])+"") );
	            
	            HSSFCell cell3 = row.createCell(2,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            cell3.setCellValue("准予"+map.get(itemMarks[1])+""+map.get(itemMarks[0])+"车辆行驶"+map.get(itemMarks[12])+"路线,发放《河南省普通公路超限运输通行证》通行证编号："+passportCard);        //设置单元格的值
	            
	            cell3.setCellStyle(style);     
	            //许可决定书文号
	            DecimalFormat df = new DecimalFormat("0000");
	            String wen=df.format(map.get(itemMarks[4]));
	            HSSFCell cell4 = row.createCell(3,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            cell4.setCellValue("["+map.get(itemMarks[3])+"]"+wen+"号");        //设置单元格的值
	            cell4.setCellStyle(style);  
	            //许可时限
	            SimpleDateFormat sdfFolderDir = new SimpleDateFormat("yyyy-MM-dd");
	            String startTime   = sdfFolderDir.format(new Date((Long)((Map)(map.get(itemMarks[6]))).get("time")));
	            String endTime     = sdfFolderDir.format(new Date((Long)((Map)(map.get(itemMarks[7]))).get("time")));
	           
	            HSSFCell cell5 = row.createCell(4,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            cell5.setCellValue(startTime+"至"+endTime);        //设置单元格的值
	            cell5.setCellStyle(style);     
	            //审批部门
	            HSSFCell cell6 = row.createCell(5,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	            cell6.setCellValue("河南省交通运输厅公路管理局");        //设置单元格的值
	            cell6.setCellStyle(style);  
/*	            for(short j=0;j<columnNum;j++){  
	                HSSFCell cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	                if(map.get(itemMarks[j])!=null){
	                	cell.setCellValue(map.get(itemMarks[j]).toString());        //设置单元格的值
	                }else{
	                	cell.setCellValue("");
	                }
	                cell.setCellStyle(style);                                   //设置单元格样式  
	            } */    
	        }
        }
     //   ByteArrayOutputStream baos = new ByteArrayOutputStream();
       // ByteArrayInputStream  is = null;
        
       
       /* try {
			workbook.write(baos);
			byte[] content = baos.toByteArray(); 
			is = new ByteArrayInputStream(content,0,content.length);
	        baos.flush();        // 缓冲  
			baos.close(); // 关闭流         
		} catch (IOException e) {
			e.printStackTrace();
		}*/
        
        return workbook;
	}
	
	
	/**
	 * 生成Excel
	 * @param columnNum 列数
	 * @param itemMarks 列头名称 对应 Map中的Key
	 * @param itemParaps 列头名称
	 * @param list 数据
	 * @return
	 */
	public static InputStream getInputStream_test(int columnNum, String[] itemMarks, String[] itemParaps, List list){
        HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
        HSSFSheet sheet = workbook.createSheet();                       // 创建工作表  
        sheet.setDefaultColumnWidth ((short)20);                        // 设置工作表列宽  
        sheet.setDefaultRowHeight((short)10);                           // 设置工作表行高  
        //sheet样式定义
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);		//获取列头样式对象  
        HSSFCellStyle style = getStyle(workbook);                  		//单元格样式对象  
        //设置列头  
        HSSFRow row1 = sheet.createRow((short)0);               		// 在索引0的位置创建行(最顶端的行)  
        HSSFCell cell1 = null;                                  		// 在索引0的位置创建单元格(左上端)  
        // 将列头设置到sheet的单元格中  
        for(int n=0;n<columnNum;n++){  
            cell1 = row1.createCell((short)(n));                		//创建列头对应个数的单元格  
            cell1.setCellType(HSSFCell.CELL_TYPE_STRING);       		//设置列头单元格的数据类型  
            try {
				cell1.setCellValue(new String(itemParaps[n].getBytes("utf-8"),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}                       									//设置列头单元格的值  
            cell1.setCellStyle(columnTopStyle);                 		//设置列头单元格样式  
        }  
        //将查询出的数据设置到sheet对应的单元格中   
        if(list != null){
	        for(int i=0;i<list.size();i++){  
	        	Map map = (Map)list.get(i);									//遍历每个对象 
	            //创建行(从下面的i+1要注意,第0行是列头,因此创建新行要从下一行开始)  
	            HSSFRow row = sheet.createRow(i+1);                         //创建所需的行数  
	            for(short j=0;j<columnNum;j++){  
	                HSSFCell cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);//设置单元格的数据类型 
	                if(map.get(itemMarks[j])!=null){
	                	cell.setCellValue(map.get(itemMarks[j]).toString());        //设置单元格的值
	                }else{
	                	cell.setCellValue("");
	                }
	                cell.setCellStyle(style);                                   //设置单元格样式  
	            }     
	        }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream  is = null;
        try {
			workbook.write(baos);
			byte[] content = baos.toByteArray(); 
			is = new ByteArrayInputStream(content,0,content.length);
	        baos.flush();        // 缓冲  
			baos.close(); // 关闭流         
		} catch (IOException e) {
			e.printStackTrace();
		}
        return is;
	}
	
	/*  
	 * 列头单元格样式 
	 */      
	public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {  
	      // 设置字体  
	      HSSFFont font = workbook.createFont();  
	      //设置字体大小  
	      font.setFontHeightInPoints((short)11);  
	      //字体加粗  
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	      //设置字体名字   
	      font.setFontName("Courier New");  
	      //设置样式;   
	      HSSFCellStyle style = workbook.createCellStyle();  
	      //设置底边框;   
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	      //设置底边框颜色;    
	      style.setBottomBorderColor(HSSFColor.BLACK.index);  
	      //设置左边框;     
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	      //设置左边框颜色;   
	      style.setLeftBorderColor(HSSFColor.BLACK.index);  
	      //设置右边框;   
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	      //设置右边框颜色;   
	      style.setRightBorderColor(HSSFColor.BLACK.index);  
	      //设置顶边框;   
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	      //设置顶边框颜色;    
	      style.setTopBorderColor(HSSFColor.BLACK.index);
	      //设置单元格背景颜色
	      //style.setFillBackgroundColor(HSSFColor.BLUE.index);
	      
	      style.setFillForegroundColor(HSSFColor.TAN.index);
	      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      
	      //在样式用应用设置的字体;    
	      style.setFont(font);  
	      //设置自动换行;   
	      style.setWrapText(false);  
	      //设置水平对齐的样式为居中对齐;    
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	      //设置垂直对齐的样式为居中对齐;   
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	      return style;  
	}  
  
	/*   
	 * 列数据信息单元格样式 
	 */    
	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
	      // 设置字体  
	      HSSFFont font = workbook.createFont();  
	      //设置字体大小  
	      //font.setFontHeightInPoints((short)10);  
	      //字体加粗  
	      //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	      //设置字体名字   
	      font.setFontName("Courier New");  
	      //设置样式;   
	      HSSFCellStyle style = workbook.createCellStyle();  
	      //设置底边框;   
	      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	      //设置底边框颜色;    
	      style.setBottomBorderColor(HSSFColor.BLACK.index);  
	      //设置左边框;     
	      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	      //设置左边框颜色;   
	      style.setLeftBorderColor(HSSFColor.BLACK.index);  
	      //设置右边框;   
	      style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	      //设置右边框颜色;   
	      style.setRightBorderColor(HSSFColor.BLACK.index);  
	      //设置顶边框;   
	      style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	      //设置顶边框颜色;    
	      style.setTopBorderColor(HSSFColor.BLACK.index);  
	      //在样式用应用设置的字体;    
	      style.setFont(font);  
	      //设置自动换行;   
	      style.setWrapText(false);  
	      //设置水平对齐的样式为居中对齐;    
	      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	      //设置垂直对齐的样式为居中对齐;   
	      style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
	      return style;  
	}
}
