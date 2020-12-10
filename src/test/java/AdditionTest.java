import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class AdditionTest {

    @DataProvider(name="additionInputs")
    public Object[][] provideAdditionInputs() {
        return new Object[][]{
                {1, 2, 3},
                {4, 5, 9},
                {0, 0, 0},
                {-1, -1, -2},
        };
    }

    public int add(int a, int b) {
        return a+b;
    }

    @Test(dataProvider = "additionInputs")
    public void addTwoNumbers(int a, int b, int expectedResult)
    {
        assertEquals(expectedResult, add(a, b));
    }

    public boolean isSubstring(String a, String b) {
        return a.contains(b);
    }

    @DataProvider(name="substringData")
    public Object[][] subStringData() {
        return new Object[][]{
                {"Happy","app",true},
                {"dishee","df",false},
                {"dishee", "dish", true},
        };
    }

    @Test(dataProvider = "substringData")
    public void verifySubstring(String a, String b, boolean expected)
    {
        assertEquals(expected, isSubstring(a, b));
    }
}
