package com.bipinet.pointsdistance.main;

import com.bipinet.pointsdistance.service.DataReaderService;
import com.bipinet.pointsdistance.service.DataReaderServiceImpl;
import com.bipinet.pointsdistance.service.DistanceType;
import com.bipinet.pointsdistance.utils.UserInputHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.bipinet.pointsdistance.utils.UserInputHandler.getDistanceType;


public class MainApp {

    private static final String DEFAULT_FILENAME = "points";
    private static final int NUMBER_OF_CLOSEST_POINTS = 10;
    private static final int NUMBER_OF_FURTHEST_POINTS = 20;
    public static final int NUMBER_OF_POINTS = 20000000;
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = NUMBER_OF_POINTS / 2;
    private static final String NUMBER_OF_POINTS_MESSAGE = "\nNumber of points for distance calculation must be >0, <=" + MAX_NUMBER;
    private static final String DISTANCE_TYPE_MESSAGE = "\nAvailable distance types: " + DistanceType.ALL().toString();

    public static void main(String[] args) {
        DistanceType inputDistanceType;
        int numberOfPointsForDistanceCalculation;
        if (areInputArgumentsValid(args)) {
            inputDistanceType = getDistanceType(args[0]);
            numberOfPointsForDistanceCalculation = Integer.parseInt(args[1]);
        } else {
            inputDistanceType = getDistanceTypeFromUserInput();
            numberOfPointsForDistanceCalculation = getNumberOfPointsForDistanceCalculation(inputDistanceType);
        }
        getUserInputAndFindPoints(inputDistanceType, numberOfPointsForDistanceCalculation);
    }

    private static boolean areInputArgumentsValid(String[] args) {
        if (args.length == 2){
            if (UserInputHandler.isDistanceTypeInvalid(args[0])) {
                System.err.print("Invalid distance type: " + args[0] + DISTANCE_TYPE_MESSAGE);
                System.exit(1);
            }
            if (!UserInputHandler.isNumberValid(args[1], MIN_NUMBER, MAX_NUMBER)) {
                System.err.print("Invalid number of points: " + args[1] + NUMBER_OF_POINTS_MESSAGE);
                System.exit(1);
            }
            return true;
        } else {
            return false;
        }
    }

    private static short[] loadInputPoints() {
        short[] inputPoints = new short[0];
        //Read in the points form the file.
        try {
            // Determine where the input file is; assuming it's in the same directory as the jar
            File jarFile = null;
            try {
                jarFile = new File(MainApp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                System.err.println(String.format("File %s not found in the jar directory.", DEFAULT_FILENAME));
            }
            assert jarFile != null;
            final String inputFilePath = jarFile.getParent() + File.separator + DEFAULT_FILENAME;
            final DataReaderService dataReaderService = new DataReaderServiceImpl();
            inputPoints = dataReaderService.readPoints(inputFilePath, NUMBER_OF_POINTS);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(String.format("Error occurred when reading file %s.", DEFAULT_FILENAME));

        }
        return inputPoints;
    }

    private static void getUserInputAndFindPoints(DistanceType inputDistanceType, int numberOfPointsForDistanceCalculation) {
        if (inputDistanceType != null) {

            //Record start time.
            final long statTime = System.currentTimeMillis();

            final short[] inputPoints = loadInputPoints();

            //Find and print points depending on the input.
            inputDistanceType.findPoints(numberOfPointsForDistanceCalculation, NUMBER_OF_POINTS, inputPoints);

            //Record end time.
            final long endTime = System.currentTimeMillis();

            System.out.println();
            System.out.println(String.format("Finding %s %s points took %d milliseconds.",
                    inputDistanceType.toString().toLowerCase(), numberOfPointsForDistanceCalculation, endTime - statTime));
        } else {
            System.err.println("inputDistanceType cannot be null.");
        }
    }

    private static int getNumberOfPointsForDistanceCalculation(DistanceType inputDistanceType) {
        final int defaultNumberOfPointsForDistanceCalculation =
                inputDistanceType.equals(DistanceType.CLOSEST) ? NUMBER_OF_CLOSEST_POINTS : NUMBER_OF_FURTHEST_POINTS;
        return UserInputHandler.getUserInput(
                NUMBER_OF_POINTS_MESSAGE +
                        "\nDefault number of points: " + defaultNumberOfPointsForDistanceCalculation +
                        "\nEnter number of points:",
                defaultNumberOfPointsForDistanceCalculation,
                0,
                NUMBER_OF_POINTS/2);
    }

    private static DistanceType getDistanceTypeFromUserInput() {
        return UserInputHandler.getUserInput(
                DISTANCE_TYPE_MESSAGE +
                        "\n" + DistanceType.printAllInfo() +
                        "\nEnter distance type:");
    }
}
