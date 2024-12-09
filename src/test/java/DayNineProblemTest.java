import com.jrferry.DayNineProblem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DayNineProblemTest {

    public static final String testInput = "src/test/resources/dayninetestinput.txt";
    public static final String input = "src/main/resources/daynineinput.txt";


    @Test
    public void dayNinePartOneTest() {
        int expected = 1928;
        DayNineProblem dayNineProblem = new DayNineProblem();

        long actual = dayNineProblem.partOne(testInput);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void dayNinePartTwoTest() {
        int expected = 2858;
        DayNineProblem dayNineProblem = new DayNineProblem();

        long actual = dayNineProblem.partTwo(testInput);

        Assertions.assertEquals(expected, actual);
// toolow 6607511583593
    }
}
