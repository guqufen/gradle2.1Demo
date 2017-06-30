package net.fnsco.service.modules.merchant;

import java.util.HashMap;
import java.util.Map;

import net.fnsco.api.merchant.AppUserService;
import net.fnsco.core.base.BaseService;

public class test{

    public static void main(String[] args) {

        // TODO Auto-generated method stub
       Map<String,Integer> LoginTimeMap=new HashMap<>();//存放登录次数的
        int LoginTimes=0;
//        LoginTimeMap.put("mobile",LoginTimes++);
        
        System.out.println(++LoginTimes);
    }

}
