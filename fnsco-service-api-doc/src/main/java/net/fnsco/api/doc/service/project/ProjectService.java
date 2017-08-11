package net.fnsco.api.doc.service.project;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;

import net.fnsco.api.doc.service.inter.dao.InterDAO;
import net.fnsco.api.doc.service.inter.dao.InterParamDAO;
import net.fnsco.api.doc.service.inter.dao.InterRespDAO;
import net.fnsco.api.doc.service.inter.dao.ModuleDAO;
import net.fnsco.api.doc.service.inter.entity.InterDO;
import net.fnsco.api.doc.service.inter.entity.InterParamDO;
import net.fnsco.api.doc.service.inter.entity.InterRespDO;
import net.fnsco.api.doc.service.inter.entity.ModuleDO;
import net.fnsco.api.doc.service.other.dao.ApiDocDAO;
import net.fnsco.api.doc.service.other.entity.ApiDocDO;
import net.fnsco.api.doc.service.project.dao.ProjDAO;
import net.fnsco.api.doc.service.project.entity.ProjDO;
import net.fnsco.core.base.BaseService;

/**
 * @desc 项目管理service
 * @author   tangliang
 * @version  0.0.1-SNAPSHOT
 * @since    Ver 1.1
 * @Date	 2017年8月10日 上午10:23:59
 */
@Service
public class ProjectService extends BaseService{
    
    @Autowired
    private ProjDAO projDAO;
    @Autowired
    private ModuleDAO moduleDAO;
    @Autowired
    private ApiDocDAO apiDocDAO;
    @Autowired
    private InterDAO interDAO;
    @Autowired
    private InterParamDAO interParamDAO;
    @Autowired
    private InterRespDAO interRespDAO;
    
    /**
     * exportJson:(这里用一句话描述这个方法的作用) 导入JSON 项目接口信息
     * @param jsonParams    设定文件
     * @author    tangliang
     * @date      2017年8月10日 上午10:25:43
     * @return void    DOM对象
     */
    @Transactional
    public void exportJson(String jsonParams){
        if(Strings.isNullOrEmpty(jsonParams)){
            return;
        }
        /**
         * 对特殊字符需要处理
         */
        jsonParams = jsonParams.replaceAll("\\$", "");
        JSONObject jsonParam = JSONObject.parseObject(jsonParams);
        JSONObject info = jsonParam.getJSONObject("info");
        Long proId = installProJDO(info);
        Long docId = installApiDocDO(jsonParam,proId);
        JSONArray tags = jsonParam.getJSONArray("tags");
        installModuleDO(tags,docId);
        //组装接口信息
        installInterDO(jsonParam.getJSONObject("paths"),jsonParam.getJSONObject("definitions"));
    }
    /**
     * installProJDO:(这里用一句话描述这个方法的作用)组装项目信息对象
     * @param jsonParams
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月10日 上午10:37:25
     * @return ProjDO    DOM对象
     */
    private Long installProJDO(JSONObject info){
        String  title =  (String) info.get("title");
        ProjDO data = new ProjDO();
        data.setName(title);
        data.setCreateDate(new Date());
        data.setStatus("0");//初始化状态
        data.setUserId(1l);
        data.setModifyDate(new Date());
        data.setDescription(title);
        projDAO.insert(data);
        return data.getId();
    }
    /**
     * installApiDocDO:(这里用一句话描述这个方法的作用)初始化文档信息
     * @param jsonParams
     * @param proId
     * @return    设定文件
     * @author    tangliang
     * @date      2017年8月10日 上午11:52:54
     * @return Long    DOM对象
     */
    private Long installApiDocDO(JSONObject jsonParams,Long projId){
        String host = jsonParams.getString("host");
        String basePath = jsonParams.getString("basePath");
        JSONObject info = jsonParams.getJSONObject("info");
        ApiDocDO data = new ApiDocDO();
        data.setBasePath(basePath);
        data.setHost(host);
        data.setCreateDate(new Date());
        data.setProjId(projId);
        data.setVersion(info.getString("version"));
        data.setOpen(1);
        apiDocDAO.insert(data);
        return data.getId();
    }
    /**
     * installModuleDO:(这里用一句话描述这个方法的作用)组装模块名称信息对象
     * @param tags    设定文件
     * @author    tangliang
     * @date      2017年8月10日 上午11:33:47
     * @return void    DOM对象
     */
    private void installModuleDO(JSONArray tags,Long docId){
        for(int i =0;i<tags.size(); i++){
            JSONObject object = tags.getJSONObject(i);
            String name  = object.getString("name");
            String description = object.getString("description");
            ModuleDO model = new ModuleDO();
            model.setDescription(description);
            model.setCreateDate(new Date());
            model.setName(name);
            model.setDocId(docId);
            model.setSortWeight(0);
            moduleDAO.insert(model);
        }
    }
    /**
     * installInterDO:(这里用一句话描述这个方法的作用)组装接口模块对象信息
     * @param paths    设定文件
     * @author    tangliang
     * @date      2017年8月10日 下午1:20:12
     * @return void    DOM对象
     */
    private void installInterDO(JSONObject paths,JSONObject definitions){
        Set<String> urls  =  paths.keySet();
        Iterator<String> iterator = urls.iterator();
        while (iterator.hasNext()) {
            String key  = iterator.next();
            getInterInfo(key,paths.getJSONObject(key),definitions);
        }
    }
    /**
     * getInterInfo:(这里用一句话描述这个方法的作用)初始化接口信息
     * @param path
     * @param interInfo    设定文件
     * @author    tangliang
     * @date      2017年8月10日 下午2:40:27
     * @return void    DOM对象
     */
    private void getInterInfo(String path ,JSONObject interInfo,JSONObject definitions){
        Set<String> methods  =  interInfo.keySet();
        Iterator<String> iterator = methods.iterator();
        while (iterator.hasNext()) {
            String key  = iterator.next();
            JSONObject value =  interInfo.getJSONObject(key);
            String consumes = value.getString("consumes");
            String operationId = value.getString("operationId");
            InterDO interDO = new InterDO();
            interDO.setPath(path);
            interDO.setConsume(consumes);
            interDO.setMethod(key);
            interDO.setSummary(value.getString("summary"));
            interDO.setDescription(value.getString("description"));
            interDO.setName(operationId);
            interDO.setProduce(value.getString("produces"));
            interDO.setSortWeight(0);
            //获取模块信息
            JSONArray tags = value.getJSONArray("tags");
            String moduleName = tags.get(0).toString();
            ModuleDO module = moduleDAO.getByName(moduleName);
            interDO.setDocId(module.getDocId());
            interDO.setModuleId(module.getId());
            interDO.setCreateDate(new Date());
            interDAO.insert(interDO);
            installParam(interDO.getId(),interDO.getDocId(),value.getJSONArray("parameters"));
            //增加返回参数
            installResponse(interDO.getId(),interDO.getDocId(),value.getJSONObject("responses"),definitions);
        }
    }
    /**
     * installParam:(这里用一句话描述这个方法的作用)初始化参数信息
     * @param interId
     * @param docId
     * @param parameters    设定文件
     * @author    tangliang
     * @date      2017年8月10日 下午2:40:40
     * @return void    DOM对象
     */
    private void installParam(Long interId ,Long docId,JSONArray parameters){
        for(int i =0;i<parameters.size();i++){
            JSONObject json = parameters.getJSONObject(i);
            InterParamDO interParamDO = new InterParamDO();
            interParamDO.setDocId(docId);
            interParamDO.setInterId(interId);
            interParamDO.setName(json.getString("name"));
            interParamDO.setDefValue("");
            interParamDO.setDescription(json.getString("description"));
            interParamDO.setType(json.getString("type"));
            interParamDO.setRequired(json.getBoolean("required")?0:1);
            interParamDO.setPosition(json.getString("in"));
            interParamDO.setFormat(json.getString("format"));
            interParamDO.setRefSchemaId(1l);
            interParamDAO.insert(interParamDO);
            
        }
    }
    /**
     * installResponse:(这里用一句话描述这个方法的作用)初始化返回值参数
     * @param interId
     * @param docId
     * @param responses    设定文件
     * @author    tangliang
     * @date      2017年8月10日 下午5:30:11
     * @return void    DOM对象
     */
    private void installResponse(Long interId ,Long docId,JSONObject responses,JSONObject definitions){
        Set<String> methods  =  responses.keySet();
        Iterator<String> iterator = methods.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            //响应码有很多种、需要分开处理数据结构 200表示成功
            if("200".equals(key)){
                JSONObject scheJson = responses.getJSONObject(key);
                JSONObject schema = scheJson.getJSONObject("schema");
                //返回值有多种数据结构，需要分开处理
                
                String ref = schema.getString("ref");
                String schemaName = ref.substring(ref.length()-4);
                InterRespDO interRespDO = new InterRespDO();
                interRespDO.setDocId(docId);
                interRespDO.setInterId(interId);
                interRespDO.setDescription(scheJson.getString("description"));
                JSONObject custSchema = definitions.getJSONObject(schemaName);
                interRespDO.setType(custSchema.getString("type"));
                interRespDO.setCustSchema(custSchema.getString("properties"));
                interRespDO.setSortWeight(0);
                interRespDAO.insert(interRespDO);
            }
            
        }
    }
}
