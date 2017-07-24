package net.fnsco.core.code;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.fnsco.core.utils.FileUtils;

public class AutoCreateCode {

    private Logger                     logger          = Logger.getLogger(this.getClass());

    // 分隔符
    private String                     separator       = FileUtils.separator;

    // 项目根目录
    private String                     baseDir         = System.getProperty("user.dir") + "\\src\\main\\java\\";

    // 数据库类型和java类型映射
    private static Map<String, String> typeMap         = new HashMap<>();

    // 是否创建action代码
    private boolean                    isCreateAction  = true;

    // 是否创建biz代码
    private boolean                    isCreateBiz     = true;

    // 是否创建service代码
    private boolean                    isCreateService = true;

    // 是否覆盖已有代码
    private boolean                    override        = false;

    private DriverManagerDataSource    ds;

    public AutoCreateCode(String host, String dbName, String user, String pwd) throws ClassNotFoundException, SQLException {
        ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://" + host + ":3306/" + dbName + "?characterEncoding=utf-8");
        ds.setUsername(user);
        ds.setPassword(pwd);
    }

    static {
        typeMap.put("int", "Integer");
        typeMap.put("varchar", "String");
        typeMap.put("char", "String");
        typeMap.put("bigint", "Long");
        typeMap.put("text", "String");
        typeMap.put("tinyint", "Integer");
        typeMap.put("datetime", "Date");
        typeMap.put("decimal", "BigDecimal");
        typeMap.put("timestamp", "Date");
    }

    /**
     * 根据数据表创建DAO,DO
     * 
     * @param basePackage
     * @param module
     * @param tableName
     * @param override
     *            true表示覆盖原先的;false表示不覆盖,如果文件已存在则直接结束
     */
    public void init(String basePackage, String module, String tableName) throws IOException, SQLException {
        if (StringUtils.isBlank(basePackage) || StringUtils.isBlank(tableName)) {
            logger.error("包路径或者表名为空");
            return;
        }

        JdbcTemplate j = this.getJdbcTemplate();
        ResultSet columnSet = j.getDataSource().getConnection().getMetaData().getColumns(null, "%", tableName, "%");
        Connection conn = j.getDataSource().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
        String tableComment = "";
        if (rs != null && rs.next()) {
            String createDDL = rs.getString(2);
            tableComment = parse(createDDL);
        }
        rs.close();
        List<Field> list = new LinkedList<>();
        while (columnSet.next()) {
            list.add(new Field(columnSet.getString(4), columnSet.getString(6), columnSet.getString("REMARKS")));
        }
        if (list.size() > 0) {
            this.createEntityFile(basePackage, module, tableName, list, override);
            this.createDaoFile(basePackage, module, tableName, list, override);
            if (this.isCreateService) {
                this.createServiceFile(basePackage, module, tableName, list, override);
            }
            if (this.isCreateAction) {
                this.createActionFile(basePackage, module, tableName, list, override, tableComment);
            }
            if (this.isCreateBiz) {
                this.createBizFile(basePackage, module, tableName, list, override);
            }
        }

    }

    public static String parse(String all) {
        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    // 获取spring jdbctemplate
    private JdbcTemplate getJdbcTemplate() {
        JdbcTemplate t = new JdbcTemplate();
        t.setDataSource(ds);
        return t;
    }

    // 数据库表名转化成类名
    private String tableNameTransfer(String tableName, boolean upperCaseFirst) {
        if (StringUtils.isBlank(tableName)) {
            return null;
        }
        tableName = tableName.replaceFirst("[a-zA-Z0-9]+?_", "");
        char[] arr = tableName.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean upNext = false;
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (i == 0) {
                if (upperCaseFirst) {
                    sb.append(String.valueOf(c).toUpperCase());
                } else {
                    sb.append(String.valueOf(c).toLowerCase());

                }
            } else {
                if (c == '_') {
                    upNext = true;
                } else {
                    if (upNext == false) {
                        sb.append(String.valueOf(c));
                    } else {
                        sb.append(String.valueOf(c).toUpperCase());
                        upNext = false;
                    }
                }
            }
        }
        return sb.toString();
    }

    // 生成数据对象类(DO)文件
    private void createEntityFile(String basePackage, String module, String tableName, List<Field> list, boolean override) throws IOException {

        // 数据对象(DO)
        String entiryPackage = basePackage + ".service";
        String entityDir = baseDir + StringUtils.replace(entiryPackage, ".", "\\");
        if (StringUtils.isNotBlank(module)) {
            entityDir = entityDir + "\\" + module + "\\entity";
            entiryPackage = entiryPackage + "." + module + ".entity";
        }
        FileUtils.createAllFolder(entityDir);

        String entityName = this.tableNameTransfer(tableName, true) + "DO";
        String entityFilePath = entityDir + "\\" + entityName + ".java";

        if (override) {
            FileUtils.delFile(entityFilePath);
        } else {
            if (FileUtils.ifExist(entityFilePath)) {
                logger.warn(entityFilePath + "已存在,结束");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(entiryPackage).append(";").append(this.separator);
        sb.append(this.separator);

        // 判断是否有datetime类型
        boolean haveDateField = false;
        for (Field f : list) {
            if (StringUtils.containsIgnoreCase("datetime", f.getType())) {
                haveDateField = true;
                break;
            }
        }
        if (haveDateField) {
            sb.append("import java.util.Date;").append(this.separator);
        }
        sb.append(this.separator);

        sb.append("public class ").append(entityName).append(" {").append(this.separator).append(this.separator);
        for (Field f : list) {
            sb.append("    /**").append(this.separator);
            sb.append("     * ").append(f.getComment()).append(this.separator);
            sb.append("     */").append(this.separator);
            sb.append("    private ").append(this.typeTransfer(f.getType())).append(" ").append(f.getName()).append(";").append(this.separator).append(this.separator);
        }
        sb.append(this.separator).append(this.separator);
        for (Field f : list) {
            sb.append(this.method(f));
        }
        sb.append(this.separator);
        sb.append(this.separator);
        sb.append(this.toString(list));
        sb.append("}");
        FileUtils.writeToEnd(entityFilePath, sb.toString());
    }

    // 转化mysql字段类型为java数据类型
    private String typeTransfer(String mysqlType) {
        return typeMap.get(mysqlType.toLowerCase());
    }

    // 设置get/set方法
    private String method(Field f) {
        StringBuilder sb = new StringBuilder();
        sb.append("    public ").append(this.typeTransfer(f.getType())).append(" get");
        char[] arr = f.getName().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(String.valueOf(arr[i]).toUpperCase());
            } else {
                sb.append(arr[i]);
            }
        }
        sb.append("() {").append(this.separator);
        sb.append("        return ").append(f.getName()).append(";").append(this.separator);
        sb.append("    }");
        sb.append(this.separator);
        sb.append(this.separator);
        sb.append("    public ").append("void set");
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(String.valueOf(arr[i]).toUpperCase());
            } else {
                sb.append(arr[i]);
            }
        }
        sb.append("(").append(this.typeTransfer(f.getType())).append(" ").append(f.getName()).append(") {").append(this.separator);
        sb.append("        this.").append(f.getName()).append(" = ").append(f.getName()).append(";").append(this.separator);
        sb.append("    }");
        sb.append(this.separator);
        sb.append(this.separator);
        return sb.toString();
    }

    private String getMethodName(Field f) {
        StringBuilder sb = new StringBuilder();
        char[] arr = f.getName().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                sb.append(String.valueOf(arr[i]).toUpperCase());
            } else {
                sb.append(arr[i]);
            }
        }
        return "get" + sb.toString();
    }

    // toString方法
    private String toString(List<Field> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("    @Override").append(this.separator);
        sb.append("    public String toString() {").append(this.separator);
        sb.append("        return \"[");
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            sb.append(f.getName()).append("=\"").append("+ ").append(f.getName()).append(" + \"");
            if (i != list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\";").append(this.separator);
        sb.append("    }").append(this.separator);

        return sb.toString();
    }

    private boolean getUpperColumn(List<Field> list, StringBuffer sb) {
        sb.reverse();
        boolean result = false;
        sb.append("    @Results({");
        int j=0;
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            if (!f.getColumn().equals(f.getName())) {
                result = true;
                if(j>0){
                    sb.append(",");
                }
                sb.append("@Result( column = \""+f.column+"\",property = \""+f.name+"\")");
                j++;
            }
        }
        sb.append(" })");
        return result;
    }
    private void createDaoFile(String basePackage, String module, String tableName, List<Field> list, boolean override) {
        String daoPackage = basePackage + ".service";
        String providerPackage = basePackage + ".service";
        String doPackage = basePackage + ".service";
        String entityPackage = basePackage + ".service";

        String daoDir = baseDir + StringUtils.replace(daoPackage, ".", "\\");
        String providerDir = baseDir + StringUtils.replace(providerPackage, ".", "\\");
        if (StringUtils.isNotBlank(module)) {
            daoDir = daoDir + "\\" + module + "\\dao";
            providerDir = providerDir + "\\" + module + "\\dao\\helper";

            providerPackage = providerPackage + "." + module + ".dao.helper";
            daoPackage = daoPackage + "." + module + ".dao";
            doPackage = doPackage + "." + module + ".dao.helper";

            entityPackage = entityPackage + "." + module + ".entity";
        }
        FileUtils.createAllFolder(daoDir);
        FileUtils.createAllFolder(providerDir);

        String daoName = this.tableNameTransfer(tableName, true) + "DAO";
        String providerName = this.tableNameTransfer(tableName, true) + "Provider";
        String doName = this.tableNameTransfer(tableName, true) + "DO";
        String doVariable = this.tableNameTransfer(tableName, false);

        String daoFilePath = daoDir + "\\" + daoName + ".java";
        String providerFilePath = providerDir + "\\" + providerName + ".java";

        if (override) {
            FileUtils.delFile(daoFilePath);
            FileUtils.delFile(providerFilePath);
        } else {
            if (FileUtils.ifExist(daoFilePath)) {
                logger.warn(daoFilePath + "已存在,结束");
                return;
            }
            if (FileUtils.ifExist(providerFilePath)) {
                logger.warn(providerFilePath + "已存在,结束");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(daoPackage).append(";").append(this.separator);
        sb.append(this.separator);

        sb.append("import org.apache.ibatis.annotations.Insert;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Param;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Select;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Options;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Delete;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Result;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.Results;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.SelectProvider;").append(this.separator);
        sb.append("import org.apache.ibatis.annotations.UpdateProvider;").append(this.separator);
        sb.append("import " + entityPackage + ".").append(doName).append(";").append(this.separator);
        sb.append("import " + providerPackage + ".").append(providerName).append(";").append(this.separator);
        sb.append(this.separator);

        sb.append("import java.util.List;;").append(this.separator);

        sb.append(this.separator);

        sb.append("public interface ").append(daoName).append(" {").append(this.separator).append(this.separator);

        // getById
        sb.append("    @Select(\"SELECT * FROM " + tableName + " WHERE id = #{id}\")").append(this.separator);
        sb.append("    public " + doName + " getById(@Param(\"id\") int id);").append(this.separator);
        sb.append(this.separator);
        
        StringBuffer sb1 = new StringBuffer();
        boolean flag = getUpperColumn(list,sb1);
        // insert
        if(flag){
            sb.append(sb1.toString());
            sb.append(this.separator);
        }
        sb.append("    @Insert(\"INSERT into " + tableName + "(");
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            sb.append(f.getColumn());
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") VALUES (");
        for (int i = 0; i < list.size(); i++) {
            Field f = list.get(i);
            sb.append("#{" + f.getName() + "}");
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")\")");
        sb.append(this.separator);
        sb.append("    @Options(useGeneratedKeys = true, keyProperty = \"id\")").append(this.separator);
        sb.append("    public void insert(" + doName + " " + doVariable + ");");
        sb.append(this.separator);
        sb.append(this.separator);

        // DELETE
        sb.append("    @Delete(\"DELETE FROM " + tableName + " WHERE id = #{id}\")").append(this.separator);
        sb.append("    public int deleteById(@Param(\"id\") int id);").append(this.separator);
        sb.append(this.separator);

        // UPDATE
        sb.append("    @UpdateProvider(type = " + providerName + ".class, method = \"update\")").append(this.separator);
        sb.append("    public int update(@Param(\"" + doVariable + "\") " + this.tableNameTransfer(tableName, true) + "DO " + " " + this.tableNameTransfer(tableName, false) + ");")
            .append(this.separator);
        sb.append(this.separator);

        if(flag){
            sb.append(sb1.toString());
            sb.append(this.separator);
        }
        // PAGE
        sb.append("    @SelectProvider(type = " + providerName + ".class, method = \"pageList\")").append(this.separator);
        sb.append("    public List<" + doName + "> pageList(@Param(\"" + doVariable + "\") " + doName + " " + this.tableNameTransfer(tableName, false)
                  + ", @Param(\"pageNum\") Integer pageNum, @Param(\"pageSize\") Integer pageSize);")
            .append(this.separator);
        sb.append(this.separator);

        sb.append("    @SelectProvider(type = " + providerName + ".class, method = \"pageListCount\")").append(this.separator);
        sb.append("    public Integer pageListCount(@Param(\"" + doVariable + "\") " + doName + " " + this.tableNameTransfer(tableName, false) + ");").append(this.separator);
        sb.append(this.separator);

        sb.append("}");

        FileUtils.writeToEnd(daoFilePath, sb.toString());
        // provider

        StringBuilder sb2 = new StringBuilder();
        sb2.append("package ").append(providerPackage).append(";").append(this.separator);
        sb2.append(this.separator);

        sb2.append("import org.apache.ibatis.jdbc.SQL;").append(this.separator);

        sb2.append("import java.util.Map;").append(this.separator);
        sb2.append(this.separator);
        sb2.append("import org.slf4j.Logger;").append(this.separator);
        sb2.append("import org.slf4j.LoggerFactory;").append(this.separator);
        sb2.append("import org.apache.commons.lang3.StringUtils;").append(this.separator);
        sb2.append(this.separator);

        sb2.append("import " + entityPackage + "." + doName + ";").append(this.separator);

        sb2.append("public class " + providerName + " {").append(this.separator);
        sb2.append(this.separator);
        sb2.append("    private Logger logger = LoggerFactory.getLogger(this.getClass());").append(this.separator);
        sb2.append(this.separator);
        sb2.append("    private static final String TABLE_NAME = \"" + tableName + "\";").append(this.separator);
        sb2.append(this.separator);
        sb2.append("    public String update(Map<String, Object> params) {").append(this.separator);
        sb2.append("        " + doName + " " + doVariable + " = (" + doName + ") params.get(\"" + doVariable + "\");").append(this.separator);
        sb2.append("        return new SQL() {{").append(this.separator);
        sb2.append("        UPDATE(TABLE_NAME);").append(this.separator);
        for (Field f : list) {
            if (StringUtils.equals(f.getName(), "id")) {
                continue;
            }
            if (StringUtils.equals(this.typeTransfer(f.getType()), "String")) {
                sb2.append("        if (StringUtils.isNotBlank(" + doVariable + "." + this.getMethodName(f) + "())){").append(this.separator);
            } else {
                sb2.append("        if (" + doVariable + "." + this.getMethodName(f) + "() != null) {").append(this.separator);
            }
            sb2.append("            SET(\"" + f.getColumn() + "=#{" + doVariable + "." + f.getName() + "}\");").append(this.separator);
            sb2.append("        }").append(this.separator);
        }
        sb2.append("        WHERE(\"id = #{" + doVariable + ".id}\");").append(this.separator);
        sb2.append("        }}.toString();").append(this.separator);
        sb2.append("    }").append(this.separator);
        sb2.append(this.separator);

        sb2.append("    public String pageList(Map<String, Object> params) {").append(this.separator);
        sb2.append("        " + doName + " " + doVariable + " = (" + doName + ") params.get(\"" + doVariable + "\");").append(this.separator);
        sb2.append("        Integer pageNum = (Integer) params.get(\"pageNum\");").append(this.separator);
        sb2.append("        Integer pageSize = (Integer) params.get(\"pageSize\");").append(this.separator);
        sb2.append("        if (pageNum == null || pageNum == 0) {").append(this.separator);
        sb2.append("            pageNum = 1;").append(this.separator);
        sb2.append("        }").append(this.separator);
        sb2.append("        if (pageSize == null || pageSize == 0) {").append(this.separator);
        sb2.append("            pageSize = 20;").append(this.separator);
        sb2.append("        }").append(this.separator);
        sb2.append("        int start = (pageNum - 1) * pageSize;").append(this.separator);
        sb2.append("        int limit = pageSize;").append(this.separator);
        sb2.append("        return new SQL() {{").append(this.separator);
        sb2.append("        SELECT(\"*\");").append(this.separator);
        sb2.append("        FROM(TABLE_NAME);").append(this.separator);
        for (Field f : list) {
            if (StringUtils.equals(this.typeTransfer(f.getType()), "String")) {
                sb2.append("        if (StringUtils.isNotBlank(" + doVariable + "." + this.getMethodName(f) + "())){").append(this.separator);
            } else {
                sb2.append("        if (" + doVariable + "." + this.getMethodName(f) + "() != null) {").append(this.separator);
            }
            // sb2.append(" if (" + doVariable + "." + this.getMethodName(f) + "() != null) {").append(this.separator);
            sb2.append("            WHERE(\"" + f.getColumn() + "=#{" + doVariable + "." + f.getName() + "}\");").append(this.separator);
            sb2.append("        }").append(this.separator);
        }
        sb2.append("        ORDER_BY(\"id desc limit \" + start + \", \" + limit );").append(this.separator);
        sb2.append("        }}.toString();").append(this.separator);
        sb2.append("    }").append(this.separator);
        sb2.append(this.separator);
        sb2.append("    public String pageListCount(Map<String, Object> params) {").append(this.separator);
        sb2.append("        " + doName + " " + doVariable + " = (" + doName + ") params.get(\"" + doVariable + "\");").append(this.separator);
        sb2.append("        return new SQL() {{").append(this.separator);
        sb2.append("        SELECT(\"count(1)\");").append(this.separator);
        sb2.append("        FROM(TABLE_NAME);").append(this.separator);
        for (Field f : list) {
            if (StringUtils.equals(this.typeTransfer(f.getType()), "String")) {
                sb2.append("        if (StringUtils.isNotBlank(" + doVariable + "." + this.getMethodName(f) + "())){").append(this.separator);
            } else {
                sb2.append("        if (" + doVariable + "." + this.getMethodName(f) + "() != null) {").append(this.separator);
            }
            sb2.append("            WHERE(\"" + f.getColumn() + "=#{" + doVariable + "." + f.getName() + "}\");").append(this.separator);
            sb2.append("        }").append(this.separator);
        }
        sb2.append("        }}.toString();").append(this.separator);
        sb2.append("    }").append(this.separator);
        sb2.append("}").append(this.separator);
        sb2.append(this.separator);
        FileUtils.writeToEnd(providerFilePath, sb2.toString());
    }

    // 生成service
    private void createServiceFile(String basePackage, String module, String tableName, List<Field> list, boolean override) throws IOException {

        // 数据对象(DO)
        String servicePackage = basePackage + ".service";
        String serviceDir = baseDir + StringUtils.replace(servicePackage, ".", "\\");
        String daoPackage = basePackage + ".service";
        String doPackage = basePackage + ".service";
        if (StringUtils.isNotBlank(module)) {
            daoPackage = daoPackage + "." + module + "." + "dao";
            doPackage = doPackage + "." + module + "." + "entity";
            serviceDir = serviceDir + "\\" + module;
            servicePackage = servicePackage + "." + module;
        }
        FileUtils.createAllFolder(serviceDir);

        String serviceName = this.tableNameTransfer(tableName, true) + "Service";
        String serviceFilePath = serviceDir + "\\" + serviceName + ".java";
        String daoName = this.tableNameTransfer(tableName, true) + "DAO";
        String doName = this.tableNameTransfer(tableName, true) + "DO";
        String doVariable = this.tableNameTransfer(tableName, false);
        String daoVariable = this.tableNameTransfer(tableName, false) + "DAO";

        if (override) {
            FileUtils.delFile(serviceFilePath);
        } else {
            if (FileUtils.ifExist(serviceFilePath)) {
                logger.warn(serviceFilePath + "已存在,结束");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(servicePackage).append(";").append(this.separator);
        sb.append(this.separator);

        sb.append("import java.util.List;").append(this.separator);
        sb.append("import java.util.Map;").append(this.separator);
        sb.append("import org.slf4j.Logger;").append(this.separator);
        sb.append("import org.slf4j.LoggerFactory;").append(this.separator);
        sb.append("import org.springframework.beans.factory.annotation.Autowired;").append(this.separator);
        sb.append("import org.springframework.stereotype.Service;").append(this.separator);
        sb.append("import " + daoPackage + "." + daoName + ";").append(this.separator);
        sb.append("import " + doPackage + "." + doName + ";").append(this.separator);

        sb.append("import net.fnsco.core.base.BaseService;").append(this.separator);
        sb.append("import net.fnsco.core.base.ResultDTO;").append(this.separator);
        sb.append("import net.fnsco.core.base.ResultPageDTO;").append(this.separator);

        sb.append(this.separator);

        sb.append("@Service").append(this.separator);
        sb.append("public class ").append(serviceName).append(" extends BaseService {").append(this.separator).append(this.separator);
        sb.append(" private Logger logger = LoggerFactory.getLogger(this.getClass());");
        sb.append(this.separator);
        sb.append(" @Autowired").append(this.separator);
        sb.append(" private " + daoName + " " + daoVariable + ";").append(this.separator);
        sb.append(this.separator);

        sb.append(" // 分页").append(this.separator);
        sb.append(" public ResultPageDTO<" + doName + "> page(" + doName + " " + doVariable + ", Integer pageNum, Integer pageSize) {").append(this.separator);
        sb.append("     logger.info(\"开始分页查询" + serviceName + ".page, " + doVariable + "=\" + " + doVariable + ".toString());").append(this.separator);
        //sb.append("     ResultPageDTO<" + doName + "> pager = new ResultPageDTO<>();").append(this.separator);
        sb.append("     List<" + doName + "> pageList = this." + daoVariable + ".pageList(" + doVariable + ", pageNum, pageSize);").append(this.separator);
        sb.append("     Integer count = this." + daoVariable + ".pageListCount(" + doVariable + ");").append(this.separator);
        sb.append("   ResultPageDTO<" + doName + "> pager =  new ResultPageDTO<" + doName + ">(count,pageList);").append(this.separator);
        //sb.append("     pager.setList(pageList);").append(this.separator);
        sb.append("     return pager;").append(this.separator);
        sb.append(" }").append(this.separator);

        sb.append(this.separator);
        sb.append(" // 添加").append(this.separator);
        sb.append(" public " + doName + " doAdd (" + doName + " " + doVariable + ",int loginUserId) {").append(this.separator);
        sb.append("     logger.info(\"开始添加" + serviceName + ".add," + doVariable + "=\" + " + doVariable + ".toString());").append(this.separator);
        //sb.append("     ResultDTO<Integer> result = new ResultDTO<>();").append(this.separator);
        //sb.append(this.separator);
        //sb.append(this.separator);
        //sb.append("     //" + doVariable + ".setGmtCreate(new Date());").append(this.separator);
        //sb.append("     //" + doVariable + ".setGmtModified(" + doVariable + ".getGmtCreate());")
        //    .append(this.separator);
        sb.append("     this." + daoVariable + ".insert(" + doVariable + ");").append(this.separator);
        //sb.append(
        //    "     if (" + doVariable + ".getId() == null || " + doVariable + ".getId() == 0) {")
        //    .append(this.separator);
        //sb.append("         return result.setError(\"添加失败\");").append(this.separator);
        //sb.append("     }").append(this.separator);
        sb.append("     return " + doVariable + ";").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 修改").append(this.separator);
        sb.append(" public Integer doUpdate (" + doName + " " + doVariable + ",Integer loginUserId) {").append(this.separator);
        sb.append("     logger.info(\"开始修改" + serviceName + ".update," + doVariable + "=\" + " + doVariable + ".toString());").append(this.separator);
        //sb.append("     ResultDTO<Integer> result = new ResultDTO<>();").append(this.separator);
        //sb.append(this.separator);
        //sb.append(this.separator);
        sb.append("     int rows=this." + daoVariable + ".update(" + doVariable + ");").append(this.separator);

        sb.append("     return rows;").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 删除").append(this.separator);
        sb.append(" public Integer doDelete (" + doName + " " + doVariable + ",Integer loginUserId) {").append(this.separator);
        sb.append("     logger.info(\"开始删除" + serviceName + ".delete," + doVariable + "=\" + " + doVariable + ".toString());").append(this.separator);
        //sb.append("     ResultDTO<Integer> result = new ResultDTO<>();").append(this.separator);
        //sb.append(this.separator);
        //sb.append(this.separator);
        sb.append("     int rows=this." + daoVariable + ".deleteById(" + doVariable + ".getId());").append(this.separator);

        sb.append("     return rows;").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 查询").append(this.separator);
        sb.append(" public " + doName + " doQueryById (Integer id) {").append(this.separator);

        sb.append("     " + doName + " obj = this." + daoVariable + ".getById(id);").append(this.separator);

        sb.append("     return obj;").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append("}");
        FileUtils.writeToEnd(serviceFilePath, sb.toString());
    }

    // 生成action
    private void createActionFile(String basePackage, String module, String tableName, List<Field> list, boolean override, String tableComment) throws IOException {

        String actionPackage = basePackage + ".web";
        String actionDir = baseDir + StringUtils.replace(actionPackage, ".", "\\");
        String servicePackage = basePackage + ".service";
        String doPackage = basePackage + ".service";
        String entityPackage = basePackage + ".service";
        if (StringUtils.isNotBlank(module)) {
            actionDir = actionDir + "\\" + module;
            actionPackage = actionPackage + "." + module;
            servicePackage = servicePackage + "." + module;
            doPackage = doPackage + "." + module;
            entityPackage = entityPackage + "." + module + ".entity";
        }
        FileUtils.createAllFolder(actionDir);

        String actionName = this.tableNameTransfer(tableName, true) + "Controller";
        String actionFilePath = actionDir + "\\" + actionName + ".java";
        String serviceName = this.tableNameTransfer(tableName, true) + "Service";
        String serviceVariable = this.tableNameTransfer(tableName, false) + "Service";
        String doName = this.tableNameTransfer(tableName, true) + "DO";
        String doVariable = this.tableNameTransfer(tableName, false);

        if (override) {
            FileUtils.delFile(actionFilePath);
        } else {
            if (FileUtils.ifExist(actionFilePath)) {
                logger.warn(actionFilePath + "已存在,结束");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(actionPackage).append(";").append(this.separator);
        sb.append(this.separator);

        //sb.append("import java.util.List;").append(this.separator);
        sb.append("import java.util.Map;").append(this.separator);
        //sb.append("import java.util.HashMap;").append(this.separator);
        sb.append("import org.apache.commons.lang3.math.NumberUtils;").append(this.separator);

        //sb.append("import javax.servlet.http.HttpServletRequest;").append(this.separator);
        sb.append("import org.springframework.beans.factory.annotation.Autowired;").append(this.separator);
        sb.append("import org.springframework.stereotype.Controller;").append(this.separator);
        //sb.append("import org.springframework.ui.Model;").append(this.separator);
        sb.append("import org.springframework.web.bind.annotation.RequestMapping;").append(this.separator);
        sb.append("import org.springframework.web.bind.annotation.ResponseBody;").append(this.separator);
        sb.append("import org.springframework.web.bind.annotation.RequestMethod;").append(this.separator);
        sb.append("import org.springframework.web.bind.annotation.RequestParam;").append(this.separator);
        sb.append("import org.springframework.web.bind.annotation.RequestBody;").append(this.separator);
        sb.append("import " + servicePackage + "." + serviceName + ";").append(this.separator);
        sb.append("import " + entityPackage + "." + doName + ";").append(this.separator);

        sb.append("import net.fnsco.core.base.ResultPageDTO;").append(this.separator);
        sb.append("import net.fnsco.core.base.ResultDTO;").append(this.separator);
        sb.append("import net.fnsco.core.base.BaseController;").append(this.separator);
        sb.append("import io.swagger.annotations.Api;").append(this.separator);
        sb.append("import io.swagger.annotations.ApiOperation;").append(this.separator);

        sb.append(this.separator);

        sb.append("@Controller").append(this.separator);
        sb.append("@RequestMapping(value =\"/" + doVariable + "\", method = RequestMethod.POST)").append(this.separator);
        sb.append("@Api(value = \"/" + doVariable + "\", tags = {\"" + tableComment + "\"})").append(this.separator);
        sb.append("public class ").append(actionName).append(" extends BaseController {").append(this.separator);
        sb.append(this.separator);
        sb.append(" @Autowired").append(this.separator);
        sb.append(" private " + serviceName + " " + serviceVariable + ";").append(this.separator);
        sb.append(this.separator);
        sb.append(this.separator);
        sb.append(" // 列表页").append(this.separator);
        sb.append(" @RequestMapping(\"/list\")").append(this.separator);
        sb.append(" public String list() {").append(this.separator);
        sb.append("     return \"\";").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 分页").append(this.separator);
        sb.append(" @ApiOperation(value = \"分页查询\", notes = \"分页查询\")").append(this.separator);
        sb.append(" @ResponseBody").append(this.separator);
        sb.append(" @RequestMapping(value = \"query\", method = RequestMethod.GET)").append(this.separator);
        sb.append(" public ResultDTO page(@RequestBody " + doName + " " + doVariable + ") {").append(this.separator);
        sb.append("     logger.info(\"开始分页查询" + actionName + ".page, " + doVariable + "=\" + " + doVariable + ".toString());").append(this.separator);
        sb.append("     Map<String, Integer> params = super.copyParamsToInteger(new String[] { \"page\", \"rows\" });").append(this.separator);
        sb.append("     Integer page = params.get(\"page\");").append(this.separator);
        sb.append("     Integer rows = params.get(\"rows\");").append(this.separator);
        sb.append("     ResultPageDTO<" + doName + "> pager = this." + serviceVariable + ".page(" + doVariable + ", page,rows);").append(this.separator);
        sb.append("     return success(pager);").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 添加").append(this.separator);
        sb.append(" @ApiOperation(value = \"新增保存\", notes = \"新增保存\")").append(this.separator);
        sb.append(" @ResponseBody").append(this.separator);
        sb.append(" @RequestMapping(value = \"doAdd\")").append(this.separator);
        sb.append(" public ResultDTO doAdd (@RequestBody " + doName + " " + doVariable + ") {").append(this.separator);
        sb.append("    " + doName + "   resultDO = this." + serviceVariable + ".doAdd(" + doVariable + ",super.getUserId());").append(this.separator);
        sb.append("    return success(resultDO);").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 修改").append(this.separator);
        sb.append(" @ApiOperation(value = \"修改保存\", notes = \"修改保存\")").append(this.separator);
        sb.append(" @ResponseBody").append(this.separator);
        sb.append(" @RequestMapping(value = \"doUpdate\")").append(this.separator);
        sb.append(" public ResultDTO doUpdate (@RequestBody " + doName + " " + doVariable + ") {").append(this.separator);
        sb.append("     Integer result = this." + serviceVariable + ".doUpdate(" + doVariable + ",getUserId());").append(this.separator);
        sb.append("     return success(result);").append(this.separator);
        sb.append(" }").append(this.separator);
        sb.append(this.separator);
        sb.append(" // 删除").append(this.separator);
        sb.append(" @ApiOperation(value = \"删除\", notes = \"删除\")").append(this.separator);
        sb.append(" @ResponseBody").append(this.separator);
        sb.append(" @RequestMapping(value = \"doDelete\")").append(this.separator);
        sb.append(" public ResultDTO doDelete(@RequestBody " + doName + " " + doVariable + ") {").append(this.separator);
        sb.append("     Integer result = this." + serviceVariable + ".doDelete(" + doVariable + ",getUserId());").append(this.separator);
        sb.append("     return success(result);").append(this.separator);
        sb.append(" }").append(this.separator);

        sb.append(" // 详情").append(this.separator);
        sb.append(" @ApiOperation(value = \"查询详情\", notes = \"查询详情\")").append(this.separator);
        sb.append(" @ResponseBody").append(this.separator);
        sb.append(" @RequestMapping(value = \"detail\")").append(this.separator);
        sb.append(" public ResultDTO detail(@RequestParam String id) {").append(this.separator);
        //sb.append("     String id = request.getParameter(\"id\");").append(this.separator);
        sb.append("     " + doName + " result = this." + serviceVariable + ".doQueryById(NumberUtils.toInt(id));").append(this.separator);
        //        sb.append("     if (result.isSuccess()) {").append(this.separator);
        //        sb.append("         model.addAttribute(\"" + doVariable + "\", result.getData());")
        //            .append(this.separator);
        //        sb.append("     }").append(this.separator);
        sb.append("     return success(result);").append(this.separator);
        sb.append(" }").append(this.separator);

        sb.append("}");
        FileUtils.writeToEnd(actionFilePath, sb.toString());
    }

    // 生成Biz
    private void createBizFile(String basePackage, String module, String tableName, List<Field> list, boolean override) throws IOException {

        // 数据对象(DO)
        String bizPackage = basePackage + ".service";
        String bizDir = baseDir + StringUtils.replace(bizPackage, ".", "\\");
        if (StringUtils.isNotBlank(module)) {
            bizDir = bizDir + "\\" + module;
            bizPackage = bizPackage + "." + module;
        }
        FileUtils.createAllFolder(bizDir);

        String bizName = this.tableNameTransfer(tableName, true) + "Biz";
        String bizFilePath = bizDir + "\\" + bizName + ".java";

        if (override) {
            FileUtils.delFile(bizFilePath);
        } else {
            if (FileUtils.ifExist(bizFilePath)) {
                logger.warn(bizFilePath + "已存在,结束");
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(bizPackage).append(";").append(this.separator);
        sb.append(this.separator);

        sb.append("public class ").append(bizName).append(" {").append(this.separator).append(this.separator);
        sb.append("}");
        FileUtils.writeToEnd(bizFilePath, sb.toString());
    }

    private class Field {
        private String name;
        private String type;
        private String comment;
        private String column;
        public Field(String name, String type, String comment) {
            super();
            this.column = name;
            this.name = StrChange(name);
            this.type = type;
            this.comment = comment;
        }

        private String StrChange(String name) {
            StringBuffer result = new StringBuffer();
            name = name.toLowerCase();
            if (name.indexOf("_") > 0) {
                String[] names = name.split("_");
                for (int i = 0; i < names.length; i++) {
                    if (i == 0) {
                        result.append(names[i]);
                    } else {
                        result.append(toUpperFiretWord(names[i]));
                    }
                }

            } else {
                result.append(name);
            }
            return result.toString();
        }

        private String toUpperFiretWord(String name) {
            StringBuilder sb = new StringBuilder();
            char[] arr = name.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (i == 0) {
                    sb.append(String.valueOf(arr[i]).toUpperCase());
                } else {
                    sb.append(arr[i]);
                }
            }
            return sb.toString();
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getComment() {
            return comment;
        }

        /**
         * column
         *
         * @return  the column
         * @since   CodingExample Ver 1.0
        */
        
        public String getColumn() {
            return column;
        }

        /**
         * column
         *
         * @param   column    the column to set
         * @since   CodingExample Ver 1.0
         */
        
        public void setColumn(String column) {
            this.column = column;
        }

        /**
         * name
         *
         * @param   name    the name to set
         * @since   CodingExample Ver 1.0
         */
        
        public void setName(String name) {
            this.name = name;
        }

        /**
         * type
         *
         * @param   type    the type to set
         * @since   CodingExample Ver 1.0
         */
        
        public void setType(String type) {
            this.type = type;
        }

        /**
         * comment
         *
         * @param   comment    the comment to set
         * @since   CodingExample Ver 1.0
         */
        
        public void setComment(String comment) {
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "Field [name=" + name + ", type=" + type + ", comment=" + comment + "]";
        }
    }

    public void setCreateAction(boolean isCreateAction) {
        this.isCreateAction = isCreateAction;
    }

    public void setCreateBiz(boolean isCreateBiz) {
        this.isCreateBiz = isCreateBiz;
    }

    public void setCreateService(boolean isCreateService) {
        this.isCreateService = isCreateService;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
