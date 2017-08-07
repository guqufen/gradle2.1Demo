package net.fnsco.core.code;

public class CodeGen {

    public static void main(String[] args) throws Exception {

        AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "sosoapi", "root", "123456");
        auto.setCreateBiz(false);
        auto.setCreateAction(false);
        auto.setCreateService(false);
        auto.setOverride(true);
        
        auto.init("net.fnsco.api.doc", "user", "t_user_basic");
        auto.init("net.fnsco.api.doc", "user", "t_user_cube");
        auto.init("net.fnsco.api.doc", "user", "t_user_detail");
        auto.init("net.fnsco.api.doc", "user", "t_user_ext");
        auto.init("net.fnsco.api.doc", "user", "t_user_login");
        auto.init("net.fnsco.api.doc", "user", "t_user_msg");
        auto.init("net.fnsco.api.doc", "user", "t_user_token");
        auto.init("net.fnsco.api.doc", "other", "t_api_doc");
        auto.init("net.fnsco.api.doc", "other", "t_inter");
        auto.init("net.fnsco.api.doc", "other", "t_inter_param");
        auto.init("net.fnsco.api.doc", "other", "t_inter_resp");
        auto.init("net.fnsco.api.doc", "other", "t_module");
        auto.init("net.fnsco.api.doc", "project", "t_proj");
        auto.init("net.fnsco.api.doc", "project", "t_proj_log");
        auto.init("net.fnsco.api.doc", "project", "t_proj_mem");
        auto.init("net.fnsco.api.doc", "project", "t_proj_privilege");
        auto.init("net.fnsco.api.doc", "project", "t_proj_role");
        auto.init("net.fnsco.api.doc", "project", "t_proj_role_privilege");
        auto.init("net.fnsco.api.doc", "other", "t_resp_schema");
        auto.init("net.fnsco.api.doc", "other", "t_suggest");
    }
}
