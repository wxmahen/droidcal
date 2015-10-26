package iit.mahen.calculator.util;

import java.text.DecimalFormat;

public class DoubleUtils {

    public static double getFormatted(double d) {
        DecimalFormat df = new DecimalFormat("#.000000000000000");
        String s = df.format(d);
        double d2 = Double.parseDouble(s);
        return d2;
    }
}
