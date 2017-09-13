package net.fnsco.core.utils.dto;

/**
 * 二维码生成数据类
 * @desc 
 * @author   sxf
 * @version  
 * @since    Ver 1.1
 * @Date	 2017年7月13日 下午2:23:57
 *
 */
public class QrDTO {
    private String filePath;
    private String fileName;
    private String content;
    private int    width;
    private int    height;
    private String format;

    /**
     * filePath
     *
     * @return  the filePath
     * @since   CodingExample Ver 1.0
    */

    public String getFilePath() {
        return filePath;
    }

    /**
     * filePath
     *
     * @param   filePath    the filePath to set
     * @since   CodingExample Ver 1.0
     */

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * fileName
     *
     * @return  the fileName
     * @since   CodingExample Ver 1.0
    */

    public String getFileName() {
        return fileName;
    }

    /**
     * fileName
     *
     * @param   fileName    the fileName to set
     * @since   CodingExample Ver 1.0
     */

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * content
     *
     * @return  the content
     * @since   CodingExample Ver 1.0
    */

    public String getContent() {
        return content;
    }

    /**
     * content
     *
     * @param   content    the content to set
     * @since   CodingExample Ver 1.0
     */

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * width
     *
     * @return  the width
     * @since   CodingExample Ver 1.0
    */

    public int getWidth() {
        return width;
    }

    /**
     * width
     *
     * @param   width    the width to set
     * @since   CodingExample Ver 1.0
     */

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * height
     *
     * @return  the height
     * @since   CodingExample Ver 1.0
    */

    public int getHeight() {
        return height;
    }

    /**
     * height
     *
     * @param   height    the height to set
     * @since   CodingExample Ver 1.0
     */

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * format
     *
     * @return  the format
     * @since   CodingExample Ver 1.0
    */

    public String getFormat() {
        return format;
    }

    /**
     * format
     *
     * @param   format    the format to set
     * @since   CodingExample Ver 1.0
     */

    public void setFormat(String format) {
        this.format = format;
    }

}
