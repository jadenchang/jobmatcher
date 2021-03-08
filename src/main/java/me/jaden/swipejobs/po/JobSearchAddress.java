package me.jaden.swipejobs.po;

import lombok.Data;
import me.jaden.swipejobs.geo.GeoHelper;

@Data
public class JobSearchAddress {
    public enum DistanceUnit{
        km, mile;
    }

    private Double maxJobDistance;
    private Double longitude;
    private Double latitude;
    private DistanceUnit unit;

    public boolean in(Double long1, Double lat1 ){
       return GeoHelper.withInZone(longitude, latitude, long1, lat1, maxJobDistance);
    }
}
