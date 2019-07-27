package com.jojand.hamreg.logsum;

public class LogSum {
    public static double logSum(double a, double b) {
        a = a == 0 ? 0 : Math.pow(2, a-1);
        b = b == 0 ? 0 : Math.pow(2, b-1);
        double res = a + b;
        res = res == 0 ? 0 : Math.round(1 + Math.log(res)/Math.log(2));
        return res;
    }
}
