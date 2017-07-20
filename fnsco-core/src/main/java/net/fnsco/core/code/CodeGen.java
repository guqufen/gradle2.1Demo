package net.fnsco.core.code;

public class CodeGen {

    public static void main(String[] args) throws Exception {

        AutoCreateCode auto = new AutoCreateCode("114.55.54.76", "hb-withhold-platform", "root", "root");
        //auto.setCreateBiz(true);
        //auto.setCreateAction(true);
        //auto.setCreateService(true);
        auto.setOverride(true);
        auto.init("net.fnsco.withhold.server", "sys", "sys_user");
        auto.init("net.fnsco.withhold.server", "withhold", "w_withhold_info");
        auto.init("net.fnsco.withhold.server", "withhold", "w_withhold_log");
    }
}
