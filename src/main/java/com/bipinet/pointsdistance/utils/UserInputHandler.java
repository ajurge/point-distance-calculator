package com.bipinet.pointsdistance.utils;

import com.bipinet.pointsdistance.service.DistanceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UserInputHandler {

    /**
     * Get and parse user input from {@link System#in}.
     *
     * @param prompt string with usage instructions.
     * @return {@link DistanceType} that defined the operation which will be performed.
     */
    public static DistanceType getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        final BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = is.readLine();
            if (isDistanceTypeInvalid(inputLine)) {
                System.out.println("Entered distance type is not valid.");
                return getUserInput(prompt);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return getDistanceType(inputLine);
    }

    public static DistanceType getDistanceType(String inputLine) {
        return DistanceType.valueOf(inputLine.toUpperCase());
    }

    public static boolean isDistanceTypeInvalid(String inputLine) {
        return (inputLine.length() == 0) || (!DistanceType.ALL().contains(inputLine.toUpperCase()));
    }

    public static boolean isNumberValid(String number, int minNumber, int maxNumber) {
        try {
            final int inputNumber = Integer.parseInt(number);
            return isNumberInRange(inputNumber, minNumber, maxNumber);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * * Get and parse user input from {@link System#in}.
     *
     * @param prompt string with usage instructions.
     * @param defaultNumber default number.
     * @param minNumber min valid number
     * @param maxNumber max valid number
     * @return integer number.
     */
    public static int getUserInput(String prompt, int defaultNumber, int minNumber, int maxNumber) {
        int inputInteger = defaultNumber;
        String inputLine;
        System.out.println(prompt + " ");
        final BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = is.readLine();
            inputInteger = Integer.parseInt(inputLine);
        } catch (NumberFormatException e) {
            System.out.println("Entered number of points for distance calculation is not valid.");
            return getUserInput(prompt, defaultNumber, minNumber, maxNumber);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return isNumberInRange(inputInteger, minNumber, maxNumber) ? inputInteger : getUserInput(prompt, defaultNumber, minNumber, maxNumber);
    }

    private static boolean isNumberInRange(int inputInteger, int minNumber, int maxNumber) {
        return inputInteger > minNumber && inputInteger <= maxNumber;
    }
}
