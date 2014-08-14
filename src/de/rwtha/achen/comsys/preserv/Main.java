package de.rwtha.achen.comsys.preserv;

import java.lang.*;
import java.io.*;

class Main {

    public static void main (String[] args) throws java.lang.Exception
    {

        RandomCoordsCircle rndCoords=  new RandomCoordsCircle(50.778429606229615, 6.070034466683865, 4);
        RandomCoordsRect rndCoordsRect = new RandomCoordsRect(50.781500, 6.041382, 50.774625, 6.084642);

        StringBuilder r = new StringBuilder();
        BufferedWriter writer = null;

//        for (int i = 0; i < 300000; i++) {
//
//            r.append(rndCoords.getCoord());
//            r.append(System.getProperty("line.separator"));
//        };

        for (int i = 0; i < 50000; i++) {

            r.append(rndCoordsRect.getCoords());
            r.append(System.getProperty("line.separator"));
        };

//        System.out.println(r.toString());


        try {

            // Create a temporary file
            File logFile = new File("coords.txt");

            // Full path where the file is written to
            System.out.println(logFile.getCanonicalPath());

            // Append to file
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(r.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

}
