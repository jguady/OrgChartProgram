import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Guady on 6/28/2017.
 */
public class OrgChartProgramProperties {

    public Properties readPropertiesFile(String fileName)
    {
        Properties props = new Properties();
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName)){

            if (stream != null) {
                props.load(stream);
            }
            else
            {
                throw new FileNotFoundException("Property file not found: "+fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }
}
