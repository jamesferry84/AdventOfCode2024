package com.jrferry;

import java.util.*;

public class DayOneProblem {

    public static void main(String[] args) {
        //Day one class
        //sum is: 1320851
        //total is: 2533421
        System.out.println("Day one class");

        List<String> allLocations = Utils.readFile("src/main/resources/dayoneinput.txt");
        List<Long> listOne = new ArrayList<>();
        List<Long> listTwo = new ArrayList<>();
        List<Long> differences = new ArrayList<>();

        Map<Long,Long> similarityMap = new HashMap<>();

        allLocations.forEach(location -> {
            String[] locationParts = location.split("   ");
            String leftPart = locationParts[0].trim();
            String rightPart = locationParts[1].trim();
            listOne.add(Long.parseLong(leftPart));
            listTwo.add(Long.parseLong(rightPart));
            if (!similarityMap.containsKey(Integer.parseInt(leftPart))) {
                similarityMap.put(Long.parseLong(leftPart), 0L);
            }
        });

        listOne.sort(Comparator.naturalOrder());
        listTwo.sort(Comparator.naturalOrder());

        for(int i = 0; i < listOne.size(); i++) {
            if (listOne.get(i) > listTwo.get(i)) {
                differences.add(listOne.get(i) - listTwo.get(i));
            } else {
                differences.add(listTwo.get(i) - listOne.get(i));
            }
        }

        int sum = 0;
        for (int i = 0; i < differences.size(); i++) {
            sum += differences.get(i);
        }

        System.out.println("sum is: " + sum);

        for (int i = 0; i < listOne.size(); i++) {
            if (similarityMap.containsKey(listTwo.get(i)) == true) {
                long numTimes = similarityMap.get(listOne.get(i));
                long value = similarityMap.get(listOne.get(i));
                long updatedTimes = value + 1;
                similarityMap.put(listTwo.get(i), updatedTimes);
            }
        }

        int total = 0;

        for (Map.Entry<Long,Long> entry : similarityMap.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            total += (key * value);
        }

        System.out.println("total is: " + total);
    }

    public static int getTotal (Map<Integer, Integer> p) {
        return p.values().stream().mapToInt(Integer::intValue).sum();
    }
}
