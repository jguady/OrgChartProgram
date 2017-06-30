import models.Org;
import models.OrgCollection;
import models.User;
import java.util.List;
import java.util.Properties;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgChartProgram {

    public static void main(String [] args) {

        try {
            OrgChartProgramProperties orgChartProps = new OrgChartProgramProperties();
            Properties appProperties = orgChartProps.readPropertiesFile("config.properties");

            String userDataFilePath = appProperties.getProperty("UserDataFilePath");
            String orgDataFilePath = appProperties.getProperty("OrgDataFilePath");
            boolean userDataHasHeaders = Boolean.parseBoolean(appProperties.getProperty("UserDataHeaders"));
            boolean orgDataHasHeaders = Boolean.parseBoolean(appProperties.getProperty("OrgDataHeaders"));

            if (userDataFilePath.trim().isEmpty())
                throw new UnsupportedOperationException("User Property File not found");
            if (orgDataFilePath.trim().isEmpty())
                throw new UnsupportedOperationException("Org Property File not found");

            CSVReaderUtil csvReader = new CSVReaderUtil();
            List<User> usersList = csvReader.readUserFile(userDataFilePath, userDataHasHeaders);
            List<Org> orgsList = csvReader.readOrgFile(orgDataFilePath, orgDataHasHeaders);

            //Add all orgs to the orgMap.
            orgsList.forEach((Org org) -> OrgCollection.getInstance().addOrg(org));

            //Establish parent child relationships in orgs.
            orgsList.forEach((Org org) ->
            {
                Integer parentId = org.getParentOrgId();
                if (parentId != null) {
                    Org parent = OrgCollection.getInstance().getOrg(parentId);
                    if (parent != null)
                        parent.addChildOrg(org);
                    else {
                        throw new UnsupportedOperationException("The parent of a child org must exist");
                    }
                }
            });
            //add all users to orgs.
            usersList.forEach((User user) ->
            {
                Org org = OrgCollection.getInstance().getOrg(user.getOrgId());
                if (org != null)
                    org.addUser(user);
                else {
                    throw new UnsupportedOperationException("A user must belong to an org that exists");
                }
            });
            //print the orgs
            OrgChartWriter.writeOrgTree("OrgTreeFile.txt", OrgCollection.getInstance().getAllRoots());
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }




}
