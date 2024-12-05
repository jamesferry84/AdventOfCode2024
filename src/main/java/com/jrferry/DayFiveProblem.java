package com.jrferry;

import java.util.*;

public class DayFiveProblem {

    static int total = 0;
    static boolean valid = true;

    public static void main(String[] args) {
        List<String> allLines = Utils.readFile("src/main/resources/dayfiveinput.txt");
        Map<Integer, List<Integer>> orderRules = new HashMap<>();
        List<List<Integer>> locations = new ArrayList<>();
        List<List<Integer>> incorrectOrderings = new ArrayList<>();

        allLines.forEach(line -> {
            if (line.contains("|")) {
                String[] tokens = line.split("\\|");
                if (orderRules.containsKey(Integer.parseInt(tokens[0]))) {
                    orderRules.get(Integer.parseInt(tokens[0])).add(Integer.parseInt(tokens[1]));
                    sortTheValues(orderRules.get(Integer.parseInt(tokens[0])));
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(Integer.parseInt(tokens[1]));
                    orderRules.put(Integer.parseInt(tokens[0]), newList);
                }

            }
            if (line.contains(",")) {
                List<Integer> lineLocation = new ArrayList<>();
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    lineLocation.add(Integer.parseInt(token));
                }
                locations.add(lineLocation);
            }
        });

        locations.forEach(puzzlelist -> {
            for (int i = puzzlelist.size() - 1; i > 0; i--) {

                if (orderRules.containsKey(puzzlelist.get(i))) {
                    int key = puzzlelist.get(i);
                    List<Integer> order = orderRules.get(puzzlelist.get(i));  //contains ordering rules
                    //check a value has not come before the number
                    // 11
                    try {
                        for (int j = i - 1; j >= 0; j--) {
                            int numToCheck = puzzlelist.get(j);
                            for (int k = 0; k < order.size(); k++) {
                                int orderValue = order.get(k);
                                if (orderValue == numToCheck) {
                                   // System.out.println("braks the rules: " + numToCheck + " in index " + j);
                                    incorrectOrderings.add(puzzlelist);
                                    valid = false; // number comes before
                                    break;
                                }
                                valid = true;
                            }
                            if (!valid) {
                                break;
                            }
                        }
                    } catch (Exception e) {

                    }

                } else {
                    //do nothing for now
                }

                if (!valid) {
                    break;
                }

            }
            if (valid) {
                total += puzzlelist.get(puzzlelist.size() / 2);
            }
        });

        System.out.println("total = " + total);

        incorrectOrderings.forEach(list -> {
            System.out.println(list);
        });

    }

    public static void sortTheValues(List<Integer> listToSort) {
        listToSort.sort(Comparator.naturalOrder());

    }
}
