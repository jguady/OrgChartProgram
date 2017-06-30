import mappers.Mapper;
import mappers.OrgMapper;
import mappers.UserMapper;
import models.Org;
import models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Guady on 6/27/2017.
 */
public class CSVReaderUtil {

    private static final char DEFAULT_DELIMITER = ',';
    private static final char DEFAULT_ENCLOSING_CHARACTER = '"';

    public List<User> readUserFile(String filePath, boolean hasHeaders)
    {
        File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
        return readFile(file, new UserMapper(), hasHeaders);
    }
    public List<Org> readOrgFile(String filePath, boolean hasHeaders)
    {
        File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
        return readFile(file, new OrgMapper(), hasHeaders);
    }

    private static <T> List<T> readFile(File file, Mapper<T> mapper, boolean hasHeaders) {

        List<T> results = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            if(hasHeaders) {
                if (scanner.hasNext()) {
                    headers = parseLine(scanner.nextLine());
                }
            }
            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());
                T object = hasHeaders ? mapper.map(headers,line) : mapper.map(line);
                results.add(object);
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
            //ignore \r
            //stop on \n


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
