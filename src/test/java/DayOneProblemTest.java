import com.jrferry.Utils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class DayOneProblemTest {

    @Test
    public void testDayOneSolutions() {
        System.out.println("testing works");

        //"3   4"
        //"4   3"
        //"2   5"
        //"1   3"
        //"3   9"
        //"3   3"
       List<String> allLocations = Utils.readFile("src/main/resources/dayoneinput.txt");
//        allLocations.add("3   4");
//        allLocations.add("4   3");
//        allLocations.add("2   5");
//        allLocations.add("1   3");
//        allLocations.add("3   9");
//        allLocations.add("3   3");
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
                long key = listTwo.get(i);
                long numTimes = similarityMap.get(listTwo.get(i));
                long value = similarityMap.get(listTwo.get(i));
                long updatedTimes = value + 1;
                similarityMap.put(listTwo.get(i), updatedTimes);
            }
        }

        int total = 0;

        for (Map.Entry<Long,Long> entry : similarityMap.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            similarityMap.put(key, key*value);
        }
        Map<Long,Long> timesFoundInListOne = new HashMap<>();
        for (int i = 0; i < listOne.size(); i++) {
            if (timesFoundInListOne.containsKey(listOne.get(i))) {
                long value = timesFoundInListOne.get(listOne.get(i));
                timesFoundInListOne.put(listOne.get(i), value + 1);
            } else {
                timesFoundInListOne.put(listOne.get(i), 1L);
            }
        }
        List<Long> totalNum = new ArrayList<>();

        for (Map.Entry<Long,Long> entry : similarityMap.entrySet()) {
            if (timesFoundInListOne.containsKey(entry.getKey())) {
                long value = timesFoundInListOne.get(entry.getKey());
                long timesFoundTotal = value * entry.getValue();
                totalNum.add(timesFoundTotal);
            }
        }

        long t = 0;
        for (int i = 0; i < totalNum.size(); i++) {
            t += totalNum.get(i);
        }

        System.out.println("total is: " + t);
    }
}
