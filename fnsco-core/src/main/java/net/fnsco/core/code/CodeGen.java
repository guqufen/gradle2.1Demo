package net.fnsco.core.code;

public class CodeGen {

    public static void main(String[] args) throws Exception {

        AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "fnsco_big_data", "root", "123456");
        auto.setCreateBiz(false);
        auto.setCreateAction(false);
        auto.setCreateService(false);
        auto.setOverride(true);
        
        //auto.init("net.fnsco.auth", "sys", "sys_user");
        //auto.init("net.fnsco.auth", "sys", "sys_user_role");
        //auto.init("net.fnsco.auth", "sys", "sys_role_menu");
        //auto.init("net.fnsco.auth", "sys", "sys_role_dept");
        //auto.init("net.fnsco.auth", "sys", "sys_menu");
        //auto.init("net.fnsco.risk", "report", "risk_report_info");
        //auto.init("net.fnsco.risk", "report", "risk_report_repayment_history");
        //auto.init("net.fnsco.risk", "sys", "risk_web_user");
        auto.init("net.fnsco.risk", "sys", "sys_industry");
    }
}
