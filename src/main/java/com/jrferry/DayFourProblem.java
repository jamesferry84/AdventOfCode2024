package com.jrferry;

import java.util.List;

public class DayFourProblem {

    //593 too low

    private static String XMAS = "XMAS";
    private static int totalTimes = 0;
    private static int rowLength;
    private static int columnLength;

    public static void main(String[] args) {
        List<String> allLines = Utils.readFile("src/main/resources/dayfourinput.txt");
        rowLength = allLines.get(0).length();
        columnLength = allLines.size();
        char[][] xmasChars = new char[rowLength][columnLength];
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < rowLength; x++) {
                xmasChars[y][x] = allLines.get(y).charAt(x);
            }
        }

//        upToDown(xmasChars);
//        downAndUp(xmasChars);
//        leftAndRight(xmasChars);
//        rightToLeft(xmasChars);
//        diagonalDownRight(xmasChars);
//        diagonalUpLeft(xmasChars);
//        diagonalUpRight(xmasChars);
//        diagonalDownLeft(xmasChars);

        leftAndRight(xmasChars);


        System.out.println("totalTImes = " + totalTimes);
    }

    public static void upToDown(char[][] chars) {
        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < columnLength; y++) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y + 1][x] == 'M') {
                            if (chars[y + 2][x] == 'A') {
                                if (chars[y + 3][x] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void downAndUp(char[][] chars) {
        for (int x = 0; x < rowLength; x++) {
            for (int y = columnLength - 1; y >= 0; y--) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y - 1][x] == 'M') {
                            if (chars[y - 2][x] == 'A') {
                                if (chars[y - 3][x] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void leftAndRight(char[][] chars) {
        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < columnLength; y++) {

                try {
//                    if (chars[y][x] == 'X') {
//                        if (chars[y][x + 1] == 'M') {
//                            if (chars[y][x + 2] == 'A') {
//                                if (chars[y][x + 3] == 'S') {
//                                    totalTimes++;
//                                }
//                            }
//                        }
//                    }
                    if (chars[y][x] == 'M' && chars[y][x + 2] == 'M') {
                        if (chars[y + 1][x + 1] == 'A') {
                            if (chars[y + 2][x] == 'S' && chars[y + 2][x + 2] == 'S') {
                                totalTimes++;
                            }
                        }
                    }

                    if (chars[y][x] == 'S' && chars[y][x + 2] == 'M') {
                        if (chars[y + 1][x + 1] == 'A') {
                            if (chars[y + 2][x] == 'S' && chars[y + 2][x + 2] == 'M') {
                                totalTimes++;
                            }
                        }
                    }

                    if (chars[y][x] == 'M' && chars[y][x + 2] == 'S') {
                        if (chars[y + 1][x + 1] == 'A') {
                            if (chars[y + 2][x] == 'M' && chars[y + 2][x + 2] == 'S') {
                                totalTimes++;
                            }
                        }
                    }

                    if (chars[y][x] == 'S' && chars[y][x + 2] == 'S') {
                        if (chars[y + 1][x + 1] == 'A') {
                            if (chars[y + 2][x] == 'M' && chars[y + 2][x + 2] == 'M') {
                                totalTimes++;
                            }
                        }
                    }
//                    if ((chars[y][x] == 'M' || chars[y][x] == 'S') && (chars[y][x + 2] == 'M' || chars[y][x + 2] == 'S')) {
//                        if (chars[y + 1][x + 1] == 'A') {
//                            if (chars[y][x] == 'M') {
//                                if (chars[y + 2][x + 2] == 'S') {
//
//                                }
//                            } else if  (chars[y][x] == 'S') {
//                                if (chars[y + 2][x + 2] == 'M') {
//
//                                }
//                            }
//                        }
//                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void rightToLeft(char[][] chars) {
        for (int x = rowLength - 1; x >= 0; x--) {
            for (int y = 0; y < columnLength; y++) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y][x - 1] == 'M') {
                            if (chars[y][x - 2] == 'A') {
                                if (chars[y][x - 3] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void diagonalDownRight(char[][] chars) {
        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < columnLength; y++) {

                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y + 1][x + 1] == 'M') {
                            if (chars[y + 2][x + 2] == 'A') {
                                if (chars[y + 3][x + 3] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void diagonalDownLeft(char[][] chars) {
        for (int x = rowLength - 1; x >= 0; x--) {
            for (int y = 0; y < columnLength; y++) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y + 1][x - 1] == 'M') {
                            if (chars[y + 2][x - 2] == 'A') {
                                if (chars[y + 3][x - 3] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void diagonalUpLeft(char[][] chars) {
        for (int x = rowLength - 1; x >= 0; x--) {
            for (int y = columnLength - 1; y >= 0; y--) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y - 1][x - 1] == 'M') {
                            if (chars[y - 2][x - 2] == 'A') {
                                if (chars[y - 3][x - 3] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }

    public static void diagonalUpRight(char[][] chars) {
        for (int x = 0; x < rowLength; x++) {
            for (int y = columnLength - 1; y >= 0; y--) {
                try {
                    if (chars[y][x] == 'X') {
                        if (chars[y - 1][x + 1] == 'M') {
                            if (chars[y - 2][x + 2] == 'A') {
                                if (chars[y - 3][x + 3] == 'S') {
                                    totalTimes++;
                                }
                            }
                        }
                    }
                } catch (IndexOutOfBoundsException e) {

                }
            }
        }
    }


}
