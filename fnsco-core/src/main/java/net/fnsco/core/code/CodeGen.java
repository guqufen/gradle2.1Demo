package net.fnsco.core.code;

public class CodeGen {

    public static void main(String[] args) throws Exception {

        AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "fnsco_big_data", "root", "123456");
        auto.setCreateBiz(false);
        auto.setCreateAction(false);
        auto.setCreateService(true);
        auto.setOverride(true);
        
        //auto.init("net.fnsco.auth", "sys", "sys_user");
        //auto.init("net.fnsco.auth", "sys", "sys_user_role");
        //auto.init("net.fnsco.auth", "sys", "sys_role_menu");
        //auto.init("net.fnsco.auth", "sys", "sys_role_dept");
        //auto.init("net.fnsco.auth", "sys", "sys_menu");
        //auto.init("net.fnsco.risk", "report", "risk_report_info");
        //auto.init("net.fnsco.risk", "report", "risk_report_repayment_history");
        auto.init("net.fnsco.order", "act", "act_apply_user");
        //auto.init("net.fnsco.risk", "trade", "t_trade_data");
    }
}
