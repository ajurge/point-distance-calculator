package com.bipinet.pointsdistance.utils;

import com.bipinet.pointsdistance.service.DistanceType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UserInputHandler {

    /**
     * Get and parse user input from {@link #System.in}.
     *
     * @param prompt string with usage instructions.
     * @return {@link DistanceType} that defined the operation which will be performed.
     */
    public static DistanceType getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = is.readLine();
            if ((inputLine.length() == 0) || (!DistanceType.ALL().contains(inputLine.toUpperCase()))) {
                System.out.println("Entered distance type is not valid.");
                return getUserInput(prompt);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println("Processing data... ");
        return DistanceType.valueOf(inputLine.toUpperCase());
    }
}
