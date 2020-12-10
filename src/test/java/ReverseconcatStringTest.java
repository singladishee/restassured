import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class ReverseconcatStringTest {
    @DataProvider(name="concatStrings")
    public Object[][] provideConcatStrings() {
        return new Object[][]{
                {"hello", "world", "ollehdlrow"},
                {"Aarav", "bansal", "Aaravbansal"},

        };
    }

    @Test(dataProvider = "concatStrings")
    public void reverseString(String S1 , String S2 , String expectedResult)
    {
        ReverseConcatString obj=new ReverseConcatString();
        assertEquals(expectedResult, obj.reverseConcatinateString(S1, S2));
    }
}


