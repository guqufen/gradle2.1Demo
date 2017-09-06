package net.fnsco.fnscocore;

import java.text.DecimalFormat;

public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#0.00");
        double a= 123.123/100;
        System.out.println(df.format(a));
        System.out.println(df.format(123));
        System.out.println(df.format(3));
        System.out.println(df.format(0.003));
        System.out.println(df.format(3.00));
    }
}
