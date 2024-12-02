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

    public static boolean recreateItemWithBadDataRemoved(String[] oldparts, int indexOfBadData) {
        List<String> backup = new ArrayList<>(Arrays.asList(oldparts));
        List<String> backupTwo = new ArrayList<>(Arrays.asList(oldparts));

        List<String> copy = new ArrayList<>(Arrays.asList(oldparts));
        copy.remove(indexOfBadData);
        String[] parts = copy.toArray(new String[0]);
        boolean isIncreasing = Integer.parseInt(parts[0]) < Integer.parseInt(parts[parts.length - 1]);

        int result = doWorkOnArray(parts, isIncreasing);
        try {
            if (result != -1) {

                backup.remove(indexOfBadData - 1);
                String[] partsTwo = backup.toArray(new String[0]);
                result = doWorkOnArray(partsTwo, isIncreasing);

                if (result != -1) {
                    backupTwo.remove(indexOfBadData - 1);
                    String[] partsThree = backupTwo.toArray(new String[0]);
                    result = doWorkOnArray(partsThree, isIncreasing);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
        }

        return false;

    }

    public static boolean checkItem(boolean isIncreasing, String[] parts) {
        int badIndex = doWorkOnArray(parts, isIncreasing);
        if (badIndex == -1)
            return true;
        return recreateItemWithBadDataRemoved(parts, badIndex);
         //return true;
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

    public static int doWorkOnArray(String[] parts, boolean isIncreasing) {
        for (int j = 0; j < parts.length - 1; j++) {
            int current = Integer.parseInt(parts[j]);
            int next = Integer.parseInt(parts[j + 1]);
            if (isIncreasing) {
                if (current > next) {
                    printArray(parts, j + 1, "is greater but levels go down");
                    return j+1;
                }
                if (next - current > maxDiff || next - current < minDiff) {
                    int diff = next - current;
                    printArray(parts, j + 1, "diff: " + diff);
                    return j+1;
                }
                if (j == parts.length - 2) {
                    safeReports++;
                }

            } else {
                if (current < next) {
                    printArray(parts, j + 1, "is less than but levels go up");
                    return j+1;
                }
                if (current - next > maxDiff || current - next < minDiff) {
                    int diff = current - next;
                    printArray(parts, j + 1, "diff: " + diff);
                    return j+1;
                }
                if (j == parts.length - 2) {
                    safeReports++;
                }
            }

        }
        return -1;
    }

}
