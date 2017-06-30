import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Guady on 6/28/2017.
 */
@RunWith(Parameterized.class)
public class CSVReaderUtilTest {

    @Parameters
    public static Collection<Object[]> data() {
        List<String> expected1 = new ArrayList<>();
        expected1.add("Dude");
        expected1.add("Stop");

        List<String> expected2 = new ArrayList<>();
        expected2.add("1");
        expected2.add("2");
        expected2.add("3");

        List<String> expected3 = new ArrayList<>();
        expected3.add(" 1");
        expected3.add(" 2 ");
        expected3.add("3 ");

        List<String> expected4= new ArrayList<>();
        expected4.add("Miss\"iss\"ippi");
        expected4.add("Oh,io");


        return Arrays.asList(new Object[][]
        {
                {"\"Dude\",\"Stop\"",expected1},
                {"1,2,3",expected2},
                {" 1, 2 ,3 ",expected3},
                {"\"Miss\"\"iss\"\"ippi\",\"Oh,io\"",expected4}
        });
    }

    public CSVReaderUtilTest(String parseInput,List<String> expected)
    {
        this.parseInput = parseInput;
        this.expected = expected;
    }

    public String parseInput;


    public List<String> expected;

    @Test
    public void parseLine() throws Exception
    {
        String input = parseInput;
        List<String> actual = CSVReaderUtil.parseLine(input);
        List<String> expectedStrings = expected;
        for(String str:expectedStrings)
        {
            if(!str.equals(actual.remove(0)))
                fail();
        }


    }

}