import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class TestNgAdditionTest {

    @DataProvider(name="additionTestCases")
    public Object[][] getAdditionTestCases() {
        return new Object[][] {
                {1, 2, 3},
                {4, 5, 9}
        };
    }

    @Test(dataProvider="additionTestCases")
    public void testAddition(int a, int b, int result) {
        assertEquals(a + b, result);
    }
}
