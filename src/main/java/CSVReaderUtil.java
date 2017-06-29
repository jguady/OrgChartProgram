import mappers.Mapper;
import mappers.OrgMapper;
import mappers.UserMapper;
import models.Org;
import models.Registerable;
import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Guady on 6/27/2017.
 */
public class CSVReaderUtil {

    private static final char DEFAULT_DELIMITER = ',';
    private static final char DEFAULT_ENCLOSING_CHARACTER = '"';

    public List<User> readUserFile(String filePath)
    {
        File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
        return readFile(file, new UserMapper());
    }
    public List<Org> readOrgFile(String filePath)
    {
        File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
        return readFile(new File(filePath), new OrgMapper());
    }

    private static <T> List<T> readFile(File file, Mapper<T> mapper) {

        List<T> results = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                results.add(mapper.map(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }


    public static List<String> parseLine(String line) {
        List<String> result = new ArrayList<>();

        if (line == null || line.isEmpty())
            return result;

        StringBuilder currentValue = new StringBuilder();

        boolean enclosedValue = false;
        boolean secondEnclosingCharacter=  false;

        char[] charsLine = line.toCharArray();

        for (char c : charsLine) {
            //is it an quoted value?
            //if so is the current character a double quote
            // if so set enclosed to false
            //continue reading without adding to value
            // if its not enclosed
            // is the value a comma?
            // if not add the value to the current value


            if (c == DEFAULT_ENCLOSING_CHARACTER) {
                if (enclosedValue) {// endquote
                    enclosedValue = false;
                    secondEnclosingCharacter=true;
                }
                else
                {
                    if(secondEnclosingCharacter) {
                        currentValue.append(c);
                        secondEnclosingCharacter=false;
                    }
                    enclosedValue = true;
                }
            }
            else {
                if (enclosedValue) {

                    currentValue.append(c);
                }
                else {
                    if (c == DEFAULT_DELIMITER) {
                        result.add(currentValue.toString());
                        currentValue = new StringBuilder();
                        secondEnclosingCharacter = false;
                    }
                    else if (c == '\r') {
                        continue;
                    }
                    else if (c == '\n') {
                        break;
                    }
                    else {
                        currentValue.append(c);
                    }
                }
            }

        }
        result.add(currentValue.toString());

        return result;
    }

}
