package com.jrferry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwoProblem {

    static int safeReports = 0;
    static int minDiff = 1;
    static int maxDiff = 3;

    public static void main(String[] args) {
        // List<String> allLocations = Utils.readFile("src/main/resources/dayTwoBadInput.txt");
        List<String> allLocations = Utils.readFile("src/main/resources/daytwoinput.txt");


        for (int i = 0; i < allLocations.size(); i++) {
            String[] parts = allLocations.get(i).split(" ");
            boolean isIncreasing = Integer.parseInt(parts[0]) < Integer.parseInt(parts[parts.length - 1]);
            checkItem(isIncreasing, parts);
        }


        System.out.println("safe reports = " + safeReports);
    }

    public static boolean recreateItemWithBadData(String[] oldparts, int indexOfBadData) {

        List<String> copy = new ArrayList<>(Arrays.asList(oldparts));
        copy.remove(indexOfBadData);
        String[] parts = copy.toArray(new String[0]);
        boolean isIncreasing = Integer.parseInt(parts[0]) < Integer.parseInt(parts[parts.length - 1]);

        doWorkOnArray(parts, isIncreasing);
        return false;

    }

    public static boolean checkItem(boolean isIncreasing, String[] parts) {

        int badIndex = -1;

        doWorkOnArray(parts, isIncreasing);
        if (badIndex == -1) {
            return false;
        }
        return recreateItemWithBadData(parts, badIndex);
        // return true;
    }

    public static void printArray(String[] array, int index, String reason) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                output.append(" "); // Add a space between elements
            }
            output.append(array[i]);
        }
        System.out.println(output + " bad index: " + index + " reason: " + reason);
    }

    public static void doWorkOnArray(String[] parts, boolean isIncreasing) {
        for (int j = 0; j < parts.length - 1; j++) {
            int current = Integer.parseInt(parts[j]);
            int next = Integer.parseInt(parts[j + 1]);
            if (isIncreasing) {
                if (current > next) {
                    printArray(parts, j + 1, "is greater but levels go down");
                    break;
                }
                if (next - current > maxDiff || next - current < minDiff) {
                    int diff = next - current;
                    printArray(parts, j + 1, "diff: " + diff);
                    break;
                }
                if (j == parts.length - 2) {
                    safeReports++;
                }

            } else {
                if (current < next) {
                    printArray(parts, j + 1, "is less than but levels go up");
                    break;
                }
                if (current - next > maxDiff || current - next < minDiff) {
                    int diff = current - next;
                    printArray(parts, j + 1, "diff: " + diff);
                    break;
                }
                if (j == parts.length - 2) {
                    safeReports++;
                }
            }

        }
    }

}
