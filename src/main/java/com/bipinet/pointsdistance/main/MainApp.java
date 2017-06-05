package com.bipinet.pointsdistance.main;

import com.bipinet.pointsdistance.domain.DistanceCalculationParameters;
import com.bipinet.pointsdistance.domain.PointXY;
import com.bipinet.pointsdistance.service.DataReaderService;
import com.bipinet.pointsdistance.service.DataReaderServiceImpl;
import com.bipinet.pointsdistance.service.DistanceType;
import com.bipinet.pointsdistance.utils.UserInputHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class MainApp {

    public static final String DEFAULT_FILENAME = "points";
    public static final PointXY POINT_FOR_CLOSEST = new PointXY((short) -200, (short) 300);
    public static final PointXY POINT_FOR_FURTHEST = new PointXY((short) 1000, (short) 25);
    public static final int NUMBER_OF_CLOSEST_POINTS = 10;
    public static final int NUMBER_OF_FURTHEST_POINTS = 20;
    public static final int NUMBER_OF_POINTS = 20000000;

    public static void main(String[] args) {
        //Get user input
        DistanceType inputDistanceType = UserInputHandler.getUserInput("Available distance types: " +
                DistanceType.ALL().toString() +
                "\n Enter distance type:");

        if (inputDistanceType != null) {
            //Record start time.
            long statTime = System.currentTimeMillis();

            short[] inputPoints = new short[0];
            //Read in the points form the file.
            try {
                // Determine where the input file is; assuming it's in the same directory as the jar
                File jarFile = null;
                try {
                    jarFile = new File(MainApp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                    System.out.println(String.format("File %s not found in the jar directory.", DEFAULT_FILENAME));
                }
                String inputFilePath = jarFile.getParent() + File.separator + DEFAULT_FILENAME;
                DataReaderService dataReaderService = new DataReaderServiceImpl();
                inputPoints = dataReaderService.readPoints(inputFilePath, NUMBER_OF_POINTS);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(String.format("Error occurred when reading file %s.", DEFAULT_FILENAME));

            }

            //Find and print points depending on the input.
            inputDistanceType.findPoints(
                    new DistanceCalculationParameters(
                            POINT_FOR_CLOSEST,
                            POINT_FOR_FURTHEST,
                            NUMBER_OF_CLOSEST_POINTS,
                            NUMBER_OF_FURTHEST_POINTS,
                            NUMBER_OF_POINTS),
                    inputPoints);

            //Record end time.
            long endTime = System.currentTimeMillis();

            System.out.println();
            System.out.println(String.format("Finding %s points took %d milliseconds.",
                    inputDistanceType.toString().toLowerCase(), endTime - statTime));
        } else {
            System.out.println("An unknown error occurred when parsing user input.");
        }


    }
}
