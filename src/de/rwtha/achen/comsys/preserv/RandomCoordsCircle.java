package de.rwtha.achen.comsys.preserv;

/**
 * Created by Sam on 8/7/2014.
 *
 *
 * A random coordinate generator based on GeoMidpoint
 * http://www.geomidpoint.com/random/calculation.html
 */
public class RandomCoordsCircle implements RandomCoords {

    // Earth radius in Kilometer
    private static final double EARTH_RADIUS_KM   = 6372.796924;
    private static final double EARTH_RADIUS_MILE = 3960.056052;
    private double centerLat;
    private double centerLng;
    private double radius;

    public RandomCoordsCircle(double centerLat, double centertLng, double radius) {

        // Convert degrees to radians
        this.centerLat  = Math.toRadians(centerLat);
        this.centerLng = Math.toRadians(centertLng);

        // Convert maximum distance to radians.
        this.radius     = radius / EARTH_RADIUS_KM;
    }

    public String getCoords() {

        // Random numbers between 0 and 1.0
        double rnd1, rnd2;
        rnd1 = Math.random();
        rnd2 = Math.random();

        // Compute a random distance from 0 to maxDist.
        double rndDist = Math.acos(rnd1 * (Math.cos(radius) - 1) + 1);

        // Compute a random bearing
        double rndBearing = 2* Math.PI * rnd2;

        // Calculate result
        double resultLat = Math.asin(Math.sin(centerLat)* Math.cos(rndDist) +
                Math.cos(centerLat)* Math.sin(rndDist)* Math.cos(rndBearing));
        double resultLng = centerLng + Math.atan2(Math.sin(rndBearing) * Math.sin(rndDist) *
                Math.cos(centerLat), Math.cos(rndDist)- Math.sin(centerLat) *
                Math.sin(resultLat));

        // If lon is less than -PI then:
        if (resultLng < -Math.PI) {
            resultLng = resultLng + 2 * Math.PI;
        } else if (resultLng > Math.PI) {
            // If lon is greater than PI then:
            resultLng = resultLng - 2 * Math.PI;
        };

        return Math.toDegrees(resultLat) + ", " + Math.toDegrees(resultLng);

    };
}
