import com.sun.javaws.exceptions.InvalidArgumentException;

import models.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgChartProgram {

    public static void main(String [] args) throws InvalidArgumentException {

        OrgChartProgramProperties orgChartProps = new OrgChartProgramProperties();
        Properties appProperties = orgChartProps.readPropertiesFile("config.properties");

        String userDataFilePath = appProperties.getProperty("UserDataFilePath");
        String orgDataFilePath = appProperties.getProperty("OrgDataFilePath");
        String userDataHasHeaders = appProperties.getProperty("UserDataHeaders");
        String orgDataHasHeaders = appProperties.getProperty("OrgDataHeaders");

        if(userDataFilePath.trim().isEmpty())
            throw new InvalidArgumentException(new String[]{"User Property File not found"});
        if(orgDataFilePath.trim().isEmpty())
            throw new InvalidArgumentException(new String[]{"Org Property File not found"});

        CSVReaderUtil csvReader = new CSVReaderUtil();
        List<User> usersList = csvReader.readUserFile(userDataFilePath);

        try {
            List<String> lines = Files.readAllLines(Paths.get("/resources/UserData.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
