package net.fnsco.fnscocore;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import net.fnsco.core.utils.DateUtils;

public class Test {
    public static void main(String[] args) {
        String timeStamp = "20170919";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String timeStampEnd = "";
        try {
            timeStampEnd = DateUtils.getDateStrYYYYMMDD(sf.parse(timeStamp),1);
        } catch (ParseException e) {
             
        }
        System.out.println(timeStampEnd);
        DecimalFormat df = new DecimalFormat("#0.00");
        double a= 123.123/100;
        System.out.println(df.format(a));
        System.out.println(df.format(123));
        System.out.println(df.format(3));
        System.out.println(df.format(0.003));
        System.out.println(df.format(3.00));
    }
}
