package me.jaden.swipejobs.geo;

public class GeoHelper {

    private static final double EARTH_RADIUS = 6378.137;

    /**
     * calculate the accurate distance between to locations.
     * @return physical distance (KM)
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2)
    {
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * test if the two location are in the same zone by given distance threshold.
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @param threshold, unit:KM
     * @return
     */
    public static boolean withInZone(double lng1, double lat1, double lng2, double lat2, double threshold){
        double distance = getDistance(lng1, lat1, lng2, lat2);
        return (distance<threshold)?true:false;
    }
}
