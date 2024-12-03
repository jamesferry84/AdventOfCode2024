package com.jrferry;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThreeProblem {

    static long total = 0L;
    static String regex = "mul\\((\\d+),(\\d+)\\)";
    static String doRegex = "do\\(\\)";
    static String dontRegex = "don't\\(\\)";
    static String fullRegex = "do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)";

    public static void main(String[] args) {
        List<String> allLines = Utils.readFile("src/main/resources/daythreeinput.txt");
        Pattern pattern = Pattern.compile(regex);
        Pattern fullPAttern = Pattern.compile(fullRegex);
        boolean doSum = true;
        for (String line : allLines) {
            Matcher matcher = fullPAttern.matcher(line);

            while (matcher.find()) {
                String match = matcher.group();
                if (match.equals("don't()")) {
                    doSum = false;
                }
                if (match.equals("do()")) {
                    doSum = true;
                }
                if (doSum && match.startsWith("mul")) {
                    Matcher multiplyMatcher = pattern.matcher(match);
                    if (multiplyMatcher.find()) {
                        long x = Long.parseLong(multiplyMatcher.group(1));
                        long y = Long.parseLong(multiplyMatcher.group(2));
                        long multiple = x * y;
                        total += multiple;
                    }
                }
            }
        }
        System.out.println("Grand total = " + total);
    }
}
