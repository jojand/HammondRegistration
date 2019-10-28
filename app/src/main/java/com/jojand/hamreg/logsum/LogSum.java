package com.jojand.hamreg.logsum;

import java.util.ArrayList;
import java.util.Arrays;

public class LogSum {
    private static final int REGISTER_COUNT = 9;
    private static final int MAX_REGISTER_VAL = 8;
    private static final int MAX_LINEAR_VALUE = 180;

    public LogSum() {
        data = new ArrayList<>();
    }

    public ArrayList<Double[]> getData() {
        return data;
    }

    public void setData(ArrayList<Double[]> data) {
        this.data = data;
    }

    private ArrayList<Double[]> data;

    public static double logSum(double a, double b) {
        a = a == 0 ? 0 : Math.pow(2, a-1);
        b = b == 0 ? 0 : Math.pow(2, b-1);
        double res = a + b;
        res = res == 0 ? 0 : Math.round(1 + Math.log(res)/Math.log(2));
        return res;
    }

    private static double toLinearPower(double x) {
        return x == 0 ? 0 : Math.pow(2, x-1);
    }

    private static double toLogScale(double x) {
        return x == 0 ? 0 : Math.round(1 + Math.log(x)/Math.log(2));
    }

    private static Double[] normalise(Double[] x) {
        Double[] sorted = x.clone();

        Arrays.sort(sorted);

        double maxVal = sorted[sorted.length-1];

        if (maxVal > MAX_LINEAR_VALUE) {
            for (int i=0; i<x.length; i++) {
                x[i] = (x[i]/maxVal)*MAX_LINEAR_VALUE;
            }
        }
        return x;
    }

    public void parseText(String input) {
        String[] lines = input.split("\n");
        for(String line: lines) {
            Double dataLine[] = new Double[REGISTER_COUNT];

            for (int i=0; i<REGISTER_COUNT; i++) {
                double n;
                try {
                    if (line.length() > i) {
                        if (Double.parseDouble(String.valueOf(line.charAt(i))) > MAX_REGISTER_VAL) {
                            n = MAX_REGISTER_VAL;
                        } else {
                            n = Double.parseDouble(String.valueOf(line.charAt(i)));
                        }
                    } else {
                        n = 0;
                    }
                } catch (Exception e) {
                    n = 0;
                }
                dataLine[i] = n;
            }
            data.add(dataLine);
        }
    }

    public String getText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Double[] line: data) {
            for (Double number: line) {
                stringBuilder.append(number.intValue());
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public Double[] getResultSum() {
        Double result[] = new Double[REGISTER_COUNT];

        for (int i=0; i<REGISTER_COUNT; i++) {
            double linearSum = 0;
            for (Double[] line: data) {
                linearSum += toLinearPower(line[i]);
                result[i] = linearSum;
            }
        }

        result = normalise(result);

        for (int i=0; i<REGISTER_COUNT; i++) {
            result[i] = toLogScale(result[i]);
        }

        return result;
    }
}
