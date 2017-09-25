package net.fnsco.order.service.sys.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.order.service.sys.entity.ImportErrorDO;
import net.fnsco.order.service.sys.dao.helper.ImportErrorProvider;

import java.util.List;;

public interface ImportErrorDAO {

    @Results({@Result( column = "import_file_name",property = "importFileName"),@Result( column = "error_msg",property = "errorMsg"),@Result( column = "create_time",property = "createTime"),@Result( column = "create_user_Id",property = "createUserId"),@Result( column = "row_number",property = "rowNumber") })
    @Select("SELECT * FROM sys_import_error WHERE id = #{id}")
    public ImportErrorDO getById(@Param("id") int id);

    @Insert("INSERT into sys_import_error(id,import_file_name,data,error_msg,create_time,create_user_Id,row_number,import_type) VALUES (#{id},#{importFileName},#{data},#{errorMsg},#{createTime},#{createUserId},#{rowNumber},#{importType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(ImportErrorDO importError);

    @Delete("DELETE FROM sys_import_error WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = ImportErrorProvider.class, method = "update")
    public int update(@Param("importError") ImportErrorDO  importError);

    @Results({@Result( column = "import_file_name",property = "importFileName"),@Result( column = "error_msg",property = "errorMsg"),@Result( column = "create_time",property = "createTime"),@Result( column = "create_user_Id",property = "createUserId"),@Result( column = "row_number",property = "rowNumber") })
    @SelectProvider(type = ImportErrorProvider.class, method = "pageList")
    public List<ImportErrorDO> pageList(@Param("importError") ImportErrorDO importError, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = ImportErrorProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("importError") ImportErrorDO importError);
    
    /**
     * selectByCondition:(条件查询)
     * @param importError
     * @return    设定文件
     * @author    tangliang
     * @date      2017年9月25日 下午4:13:00
     * @return List<ImportErrorDO>    DOM对象
     */
    @Results({@Result( column = "import_file_name",property = "importFileName"),@Result( column = "error_msg",property = "errorMsg"),@Result( column = "create_time",property = "createTime"),@Result( column = "create_user_Id",property = "createUserId"),@Result( column = "row_number",property = "rowNumber"),@Result( column = "import_type",property = "importType")  })
    @SelectProvider(type = ImportErrorProvider.class, method = "selectByCondition")
    public List<ImportErrorDO> selectByCondition(@Param("importError") ImportErrorDO importError);
}