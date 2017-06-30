package net.fnsco.api.merchant;

import org.apache.commons.lang3.StringUtils;

public class test {

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        String[] newVersionArr =StringUtils.split("2.2.2", ".");
        String[] versionArr =StringUtils.split("3.2.2", ".");
        if(Integer.valueOf(newVersionArr[0]) < Integer.valueOf(versionArr[0])){
            System.out.println(11);
            
        }
    }

}
