package de.rwth.comsys.preserv.samrad;

/**
 * Created by Sam on 8/7/2014.
 *
 *
 * A random coordinate generator based on GeoMidpoint
 * http://www.geomidpoint.com/random/calculation.html
 */
public class RandomCoordsRect implements RandomCoords {

    private double northLimit;
    private double westLimit;
    private double southLimit;
    private double eastLimit;


    /**
     *
     * @param nLimit - Latitude of the upper left corner
     * @param wLimit - Longitude of the upper left corner
     * @param sLimit - Latitude of the bottom right corner
     * @param eLimit - Longitude of bottom right corner
     */
    public RandomCoordsRect(double nLimit, double wLimit,
                            double sLimit, double eLimit) {

        this.northLimit  = Math.toRadians(nLimit);
        this.westLimit   = Math.toRadians(wLimit);
        this.southLimit  = Math.toRadians(sLimit);
        this.eastLimit   = Math.toRadians(eLimit);
    }

    @Override
    public String getCoords() {

        // Random numbers between 0 and 1.0
        double rnd1, rnd2;
        rnd1 = Math.random();
        rnd2 = Math.random();

        // Compute a random latitude
        double resultLat = Math.asin(rnd1 *
                ( Math.sin(northLimit) - Math.sin(southLimit)) +
                Math.sin(southLimit));

        // Find the width of the rectangular region.
        double width = eastLimit - westLimit;

        if (width < 0) {
            width = width + 2* Math.PI;
        }

        // Compute the random longitude between westLimit and eastLimit
        double resultLng = westLimit + width* rnd2;

        if (resultLng < -Math.PI) {
            resultLng = resultLng + 2 * Math.PI;
        } else if (resultLng > Math.PI) {
            resultLng = resultLng - 2 * Math.PI;
        }

        return Math.toDegrees(resultLat) + ", " + Math.toDegrees(resultLng);
    }
}
