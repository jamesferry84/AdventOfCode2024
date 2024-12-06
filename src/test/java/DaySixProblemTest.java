import com.jrferry.DaySixProblem;
import org.junit.jupiter.api.Test;

public class DaySixProblemTest {

    @Test
    public void testVisitsWithTestInputPartOne() {
        String inputFile = "src/test/resources/daysixtestinput.txt";

        DaySixProblem problem = new DaySixProblem();
        problem.partOne(inputFile);
    }

    @Test
    public void testVisitsWithTestInputPartTwo() {
        String inputFile = "src/main/resources/daysixinput.txt";

        DaySixProblem problem = new DaySixProblem();
        problem.partTwo(inputFile);
    }
}
