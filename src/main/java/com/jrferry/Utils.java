package com.jrferry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Utils INSTANCE;

    public Utils() {}

    public static Utils getInstance() {
        if (INSTANCE == null) {
            return new Utils();
        }
        return INSTANCE;
    }

    public static List<String> readFile(String filename) {
        List<String> tempList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
            String line = br.readLine();
            while (line != null) {
                tempList.add(line);
                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return tempList;
    }

}
