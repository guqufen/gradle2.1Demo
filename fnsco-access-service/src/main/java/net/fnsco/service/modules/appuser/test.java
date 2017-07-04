package net.fnsco.service.modules.appuser;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class test {

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        //客户端版本号
        String[] versionArr = {"1","2","3"};
//        int [] versionNum=new int[versionArr.length];
//        for(int i=0;i<versionNum.length;i++){
//            versionNum[i]=Integer.parseInt(versionArr[i]);
//        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<versionArr.length;i++){
          sb.append(versionArr[i]);        //append String并不拥有该方法，所以借助StringBuffer
         };
       
         int val=Integer.valueOf(sb.toString());
         int big=333;
         if(big<val){
             System.out.println(val);
         }
         
//        String str = new String(versionArr);
        
    }

}
