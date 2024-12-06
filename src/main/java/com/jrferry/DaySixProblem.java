package com.jrferry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaySixProblem {

    private static int totalVisits = 0;
    private static int totalDistinctVists = 0;
    private static int obstaclesThatMakeLoops = 0;
    private static int rowLength;
    private static int columnLength;

    private static boolean facingUp = true;
    private static boolean facingRight = false;
    private static boolean facingDown = false;
    private static boolean facingLeft = false;
    private static boolean isLoop = false;
    private static int startingPositionX = 0;
    private static int currentPositionX = 0;
    private static int startingPositionY = 0;
    private static int currentPositionY = 0;
    char[][] maze;
    char[][] visitedPositions;
    Map<Integer, String> visitWithDirection;
    List<String> allLines;

    public void startup(String inputFile) {
        allLines = Utils.readFile(inputFile);

        visitWithDirection = new HashMap<>();

        rowLength = allLines.get(0).length();
        columnLength = allLines.size();
        maze = new char[rowLength][columnLength];
        visitedPositions = new char[rowLength][columnLength];
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < rowLength; x++) {
                maze[y][x] = allLines.get(y).charAt(x);
                visitedPositions[y][x] = allLines.get(y).charAt(x);
                if (maze[y][x] != '#' && maze[y][x] != '.') {
                    startingPositionX = x;
                    startingPositionY = y;
                    currentPositionX = startingPositionX;
                    currentPositionY = startingPositionY;
                }
            }
        }
    }

    public void resetMaze() {
        maze = new char[rowLength][columnLength];
        visitedPositions = new char[rowLength][columnLength];
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < rowLength; x++) {
                maze[y][x] = allLines.get(y).charAt(x);
                visitedPositions[y][x] = allLines.get(y).charAt(x);
                if (maze[y][x] != '#' && maze[y][x] != '.') {
                    startingPositionX = x;
                    startingPositionY = y;
                    currentPositionX = startingPositionX;
                    currentPositionY = startingPositionY;
                }
            }
        }
    }

    public void resetMaze(int newY, int newX) {
        maze = new char[rowLength][columnLength];
        visitedPositions = new char[rowLength][columnLength];
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < rowLength; x++) {
                maze[y][x] = allLines.get(y).charAt(x);
                visitedPositions[y][x] = allLines.get(y).charAt(x);
                if (newY == y && newX == x) {
                    visitedPositions[y][x] = '#';
                }

                if (maze[y][x] != '#' && maze[y][x] != '.') {
                    startingPositionX = x;
                    startingPositionY = y;
                    currentPositionX = startingPositionX;
                    currentPositionY = startingPositionY;
                }
            }
        }
    }


//    public static void main(String[] args) {
//        partOne();
//    }

    public void visited(int xy, String direction) {
        if (visitWithDirection.containsKey(xy)) {
            if (visitWithDirection.get(xy).equals(direction)) {
                printOutMap();
                obstaclesThatMakeLoops++;
                System.out.println("loop at: " + xy + "with direction: " + direction + "total: " + obstaclesThatMakeLoops);
                visitWithDirection = new HashMap<>();
                isLoop = true;
            }
        } else {
            visitWithDirection.put(xy, direction);
        }

    }

    public void draw() {
        if (facingUp) {
            visitedPositions[currentPositionY][currentPositionX] = '^';
        }
        if (facingDown) {
            visitedPositions[currentPositionY][currentPositionX] = 'v';
        }

        if (facingLeft) {
            visitedPositions[currentPositionY][currentPositionX] = '<';
        }

        if (facingRight) {
            visitedPositions[currentPositionY][currentPositionX] = '>';
        }
        int x = 0;


    }

    public void printOutMap() {
        for (int y = 0; y < columnLength; y++) {
            for (int x = 0; x < rowLength; x++) {
                System.out.print(visitedPositions[y][x]);
            }
            System.out.print("        ");
            for (int x = 0; x < rowLength; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println();
        }
    }

    public void partTwo(String inputFile) {
        //part 2

        //pseudo code
        /*
        Place obstacle at (0,0) skip starting spot
        do the loop check if visited with same direction if so add
        place bstacle at 0,1.....
         */
        startup(inputFile);
        for (int x = 0; x < rowLength; x++) {
            for (int y = 0; y < columnLength; y++) {
                //place obstacle
                isLoop = false;

                currentPositionX = startingPositionX;
                currentPositionY = startingPositionY;
                facingUp = true;
                facingDown = false;
                facingLeft = false;
                facingRight = false;
                resetMaze(y,x);
                if (startingPositionY == y && startingPositionX == x) {
                    continue;
                } else {
                    maze[y][x] = '#';
                }


                while (currentPositionX >= 0 && currentPositionX < rowLength && currentPositionY >= 0 && currentPositionY < columnLength && isLoop == false) {
                    boolean hadTurn = false;
                    try {

                        if (facingUp && !hadTurn) {
                            int xy = Integer.parseInt(Integer.toString(currentPositionX) + Integer.toString(currentPositionY));

                            hadTurn = true;
                            if (maze[currentPositionY - 1][currentPositionX] == '#') {
                                visited(xy, "up");
                                draw();
                                turnRight();
                            } else {
                                draw();
                                currentPositionY--;
                                totalVisits++;
                            }
                        }
                        if (facingRight && !hadTurn) {
                            int xy = Integer.parseInt(Integer.toString(currentPositionX) + Integer.toString(currentPositionY));

                            hadTurn = true;
                            if (maze[currentPositionY][currentPositionX + 1] == '#') {
                                draw();
                                visited(xy, "right");
                                turnRight();
                            } else {
                                draw();
                                currentPositionX++;
                                totalVisits++;
                            }
                        }
                        if (facingDown && !hadTurn) {
                            int xy = Integer.parseInt(Integer.toString(currentPositionX) + Integer.toString(currentPositionY));
                            hadTurn = true;
                            if (maze[currentPositionY + 1][currentPositionX] == '#') {
                                draw();
                                visited(xy, "down");
                                turnRight();
                            } else {
                                draw();
                                currentPositionY++;
                                totalVisits++;
                            }
                        }
                        if (facingLeft && !hadTurn) {

                            int xy = Integer.parseInt(Integer.toString(currentPositionX) + Integer.toString(currentPositionY));

                            hadTurn = true;
                            if (maze[currentPositionY][currentPositionX - 1] == '#') {
                                draw();
                                visited(xy, "left");
                                turnRight();
                            } else {
                                draw();
                                currentPositionX--;
                                totalVisits++;
                            }
                        }

                        hadTurn = false;
                    } catch (Exception e) {
                        totalVisits++;
                        totalDistinctVists++;
                        System.out.println("Index out of bounds");
                        isLoop = true;
                        visitWithDirection = new HashMap<>();
                        break;
                    }


                    char visitedResult = visitedPositions[currentPositionY][currentPositionX];

                    if (visitedResult == '.') {
                        totalDistinctVists++;
                        visitedPositions[currentPositionY][currentPositionX] = 'X';
                    } else if (visitedResult == 'X') {
                        visitedPositions[currentPositionY][currentPositionX] = 'O';
                    }

                }
            }
        }

    }

    public void partOne(String inputFile) {
        startup(inputFile);
        while (currentPositionX >= 0 && currentPositionX < rowLength && currentPositionY >= 0 && currentPositionY < columnLength) {
            boolean hadTurn = false;
            try {
                if (facingUp && !hadTurn) {
                    hadTurn = true;
                    if (maze[currentPositionY - 1][currentPositionX] == '#') {
                        int nextY = currentPositionY - 1;
                        System.out.println(" # at currentPosition[" + currentPositionX + "][" + nextY + "]");
                        turnRight();
                    } else {
                        currentPositionY--;
                        totalVisits++;
                    }
                }
                if (facingRight && !hadTurn) {
                    hadTurn = true;
                    if (maze[currentPositionY][currentPositionX + 1] == '#') {
                        turnRight();
                    } else {
                        currentPositionX++;
                        totalVisits++;
                    }
                }
                if (facingDown && !hadTurn) {
                    hadTurn = true;
                    if (maze[currentPositionY + 1][currentPositionX] == '#') {
                        turnRight();
                    } else {
                        currentPositionY++;
                        totalVisits++;
                    }
                }
                if (facingLeft && !hadTurn) {
                    hadTurn = true;
                    if (maze[currentPositionY][currentPositionX - 1] == '#') {
                        turnRight();
                    } else {
                        currentPositionX--;
                        totalVisits++;
                    }
                }

                hadTurn = false;
            } catch (Exception e) {
                totalVisits++;
                totalDistinctVists++;
                System.out.println("Index out of bounds");
                return;
            }


            char visitedResult = visitedPositions[currentPositionY][currentPositionX];
            if (visitedResult == '.') {
                totalDistinctVists++;
                visitedPositions[currentPositionY][currentPositionX] = 'X';
            } else if (visitedResult == 'X') {
                visitedPositions[currentPositionY][currentPositionX] = 'O';
            }

            System.out.println("current position x: " + currentPositionX + ", current position y: " + currentPositionY + ": totalVisits: " + totalVisits + "totalDistinctVists: " + totalDistinctVists);
        }
    }

    public static void turnRight() {
        //System.out.println("hit # turning right");
        if (facingUp) {
            facingUp = false;
            facingRight = true;
           // System.out.println("facing right");
            return;
        }
        if (facingRight) {
            facingDown = true;
            facingRight = false;
           // System.out.println("facing down");
            return;
        }
        if (facingDown) {
            facingLeft = true;
            facingDown = false;
          //  System.out.println("facing left");
            return;
        }
        if (facingLeft) {
          //  System.out.println("facing up");
            facingLeft = false;
            facingUp = true;
            return;
        }
    }
}
