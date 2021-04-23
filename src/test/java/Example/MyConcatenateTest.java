package Example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyConcatenateTest {

    @Test
    public void testConcatenate()  {
        String concatenated = MyConcatenator.concatanate("one", "two", "three", "four");
        Assert.assertEquals("one,two,three,four", concatenated);
    }
}