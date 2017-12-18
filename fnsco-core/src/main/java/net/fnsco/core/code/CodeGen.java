package net.fnsco.core.code;

public class CodeGen {

    public static void main(String[] args) throws Exception {

        //AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "fnsco_big_data", "root", "123456");
        //AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "hb-withhold-platform", "root", "123456");
        AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "fnsco_e789_data", "root", "123456");
        //AutoCreateCode auto = new AutoCreateCode("192.168.1.110", "zrts_car_finance", "root", "123456");
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
        //auto.init("net.fnsco.order", "trade", "t_trade_data_lkl");
        //auto.init("net.fnsco.risk", "zhimainfo", "rist_data_access_third");
        //auto.init("net.fnsco.risk", "trade", "t_trade_data");
        //auto.init("net.fnsco.withhold", "sys", "sys_bank_trade_limit");
        //auto.init("net.fnsco.risk", "trade", "risk_trade_pay_bill");
        //auto.init("net.fnsco.risk", "trade", "risk_trade_recharge_bill");
        //auto.init("net.fnsco.risk", "sys", "risk_user_sub_account");
        //auto.init("net.fnsco.order", "sys", "sys_sequence");
        //        auto.init("net.fnsco.order", "trade", "t_email");
        //        auto.init("net.fnsco.trading", "order", "r_trade_order_date_temp");
        //        auto.init("net.fnsco.bigdata", "customer", "car_customer");
        // auto.init("net.fnsco.bigdata", "merchant", "u_app_user_merchant");
        //        auto.init("net.fnsco.trading", "appUser", "u_app_user");
        //        auto.init("net.fnsco.bigdata", "withdraw", "t_trade_withdraw_red");
        // auto.init("net.fnsco.car", "config", "car_config");
        //auto.init("net.fnsco.trading", "third.ticket", "thr_ticket_site");
        auto.init("net.fnsco.trading", "third.ticket", "thr_ticket_order");
        auto.init("net.fnsco.trading", "third.ticket", "thr_ticket_order_passenger");
        //auto.init("net.fnsco.trading", "order", "r_trade_order_by_pay_medium");

        //auto.init("net.fnsco.trading", "third.ticket", "thr_ticket_contact");
        //auto.init("net.fnsco.trading", "order", "r_trade_order_by_pay_medium");
        auto.init("net.fnsco.trading", "appUserMerchantEntity", "u_app_user_merchant_entity");
    }
}
