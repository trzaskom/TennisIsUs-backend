package com.trzaskom.utils;

import java.math.BigDecimal;

/**
 * Created by miki on 2019-03-19.
 */
public class GeolocationUtils {

    public static double distanceBetweenTwoUsers(double lat1, double lon1, double lat2,
                                  double lon2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        return distance;
    }

    private static BigDecimal toRadians(BigDecimal angdeg) {
        return angdeg.divide(new BigDecimal("180"), 7, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(Math.PI));
    }
}
