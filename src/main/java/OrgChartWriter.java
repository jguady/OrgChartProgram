import models.Org;
import models.OrgCollection;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgChartWriter
{
    private static final char DEFAULT_DELIMITER = ',';
    private static final char DEFAULT_INDENT_CHARACTER = '\t';

    public static void writeOrgTree(String fileName)
    {
        String path = File.pathSeparator+"resources"+File.pathSeparator+fileName;
        File file = new File(path);

        List<Org> orgs = OrgCollection.getInstance().getAllRoots();
        List<Integer> visited = new ArrayList<>();
        StringBuilder indents = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for(Org org: orgs)
        {
            //result = org.printOrgTree(Writer writer);
            visited.add(org.getOrgId());
        }

    }

}
