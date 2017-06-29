import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Guady on 6/27/2017.
 */
public class CSVReader {

    private static final char DEFAULT_DELIMITER = ',';
    private static final char DEFAULT_ENCLOSING_CHARACTER='"';


    public static void readFile(Reader reader)
    {

        try (Scanner scanner = new Scanner(reader)){
            while(scanner.hasNext())
                List<String> line = parseLine(scanner.nextLine());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }



    public static List<String> parseLine(String line)
    {
        List<String> result = new ArrayList<>();

        if(line == null || line.isEmpty())
            return result;

        StringBuilder currentValue = new StringBuilder();

        boolean enclosedValue = false;

        char[] charsLine = line.toCharArray();

        for(char c: charsLine)
        {
            //is it an quoted value?
             //if so is the current character a double quote
              // if so set enclosed to false
              //continue reading without adding to value
            // if its not enclosed
              // is the value a comma?
              // if not add the value to the current value



            if(c==DEFAULT_ENCLOSING_CHARACTER) {
                if(enclosedValue) // endquote
                    enclosedValue = false;
                enclosedValue = true;

            }
            else
            {
                if(enclosedValue)
                {
                    currentValue.append(c);
                }
                else {
                    if (c == DEFAULT_DELIMITER || c =='\n') {
                        result.add(currentValue.toString());
                        currentValue = new StringBuilder();
                    }
                    currentValue.append(c);
                }
            }

        }

        return result;
    }

}
