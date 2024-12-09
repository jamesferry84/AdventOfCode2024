package com.jrferry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DayNineProblem {

    List<String> intermediateInput = new ArrayList<>();

    public long partOne(String inputFileName) {
        List<Integer> output = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<String> stringInput = new ArrayList<>();
        stringInput = Utils.readFile(inputFileName);
        char[] inputCharacters = stringInput.get(0).toCharArray();
        int counter = 0;
        for (int i = 0, j = i + 1; j < inputCharacters.length; i += 2, j += 2) {
            int checkJ = j + 2;
            int lengthOfFile = Character.getNumericValue(inputCharacters[i]);
            int freeSpace = Character.getNumericValue(inputCharacters[j]);
            int id = counter;

            for (int k = 0; k < lengthOfFile; k++) {
                intermediateInput.add(String.valueOf(id));
            }
            for (int l = 0; l < freeSpace; l++) {
                intermediateInput.add(".");
            }
            counter++;

            if (checkJ == inputCharacters.length) {
                id += 1;
                int lengthOfLastEntry = Character.getNumericValue(inputCharacters[i + 2]);
                for (int k = 0; k < lengthOfLastEntry; k++) {
                    intermediateInput.add(String.valueOf(id));
                }
            }

        }
        System.out.println(intermediateInput);
        for (int x = 0, y = intermediateInput.size() - 1; x < intermediateInput.size(); x++) {

            if (intermediateInput.get(x).equals(".") && x <= y) {
                swap(x, y, intermediateInput);
                while (intermediateInput.get(y).equals(".")) {
                    y--;
                }
            }

        }
        System.out.println(intermediateInput);
        return calculateAnswer();
    }

    public long calculateAnswer() {
        long answer = 0L;
        List<Long> intList = intermediateInput.stream()
                .filter(str -> str.matches("\\d+"))
                .map(Long::parseLong) // Convert each Character to Integer
                .collect(Collectors.toList());
        for (int x = 0; x < intList.size(); x++) {
            long interAnswer = intList.get(x) * x;
            if (intList.get(x) >= 0) {
                answer += interAnswer;
            }

        }
        return answer;
    }

    public long partTwo(String inputFileName) {
        List<Integer> output = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<String> stringInput = new ArrayList<>();
        stringInput = Utils.readFile(inputFileName);
        char[] inputCharacters = stringInput.get(0).toCharArray();
        int counter = 0;
        for (int i = 0, j = i + 1; j < inputCharacters.length; i += 2, j += 2) {
            int checkJ = j + 2;
            int lengthOfFile = Character.getNumericValue(inputCharacters[i]);
            int freeSpace = Character.getNumericValue(inputCharacters[j]);
            int id = counter;

            for (int k = 0; k < lengthOfFile; k++) {
                intermediateInput.add(String.valueOf(id));
            }
            for (int l = 0; l < freeSpace; l++) {
                intermediateInput.add(".");
            }
            counter++;

            if (checkJ == inputCharacters.length) {
                id += 1;
                int lengthOfLastEntry = Character.getNumericValue(inputCharacters[i + 2]);
                for (int k = 0; k < lengthOfLastEntry; k++) {
                    intermediateInput.add(String.valueOf(id));
                }
            }
        }

        int targetNum = 0;
        int countOfTargetNum = 0;
        int freeSpace = 0;
        System.out.println(intermediateInput);

        for (int y = intermediateInput.size() - 1; y >= 0; y--) {
            if (intermediateInput.get(y).equals(".")) {
                continue;
            }
            else {
                //get nums of same number
                try {
                    targetNum = Integer.parseInt(intermediateInput.get(y));
                    countOfTargetNum = 0;
                    while (Integer.parseInt(intermediateInput.get(y)) == targetNum) {
                        countOfTargetNum++;
                        if (Integer.parseInt(intermediateInput.get(y-1)) == targetNum) {
                            y--;
                        } else {
                            break;
                        }

                    }
                } catch (Exception e) {
                    continue;
                }

            }

            for (int x = 0; x < y; x++) {
                if (intermediateInput.get(x).equals(".")) {
                    int tempX = x;
                    while (intermediateInput.get(x).equals(".")) {
                        freeSpace++;
                        x++;
                    }
                    if (freeSpace > countOfTargetNum) {
                        //swap
                        int tempNum = (y+countOfTargetNum) - 1;
                        for (int z = 0; z<countOfTargetNum; z++) {
                            swap(tempX, (tempNum), intermediateInput);
                            tempNum--;
                            tempX++;
                        }
                    }
                } else {
                    continue;
                }
            }
            System.out.println(intermediateInput);

        }


        return calculateAnswer();
    }


    public void swap(int x, int y, List<String> listToSwap) {
        String xTempValue = listToSwap.get(x);
        listToSwap.set(x, listToSwap.get(y));
        listToSwap.set(y, xTempValue);


    }
}
