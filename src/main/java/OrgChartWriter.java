import models.Org;
import models.OrgCollection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Stack;

/**
 * Created by Guady on 6/27/2017.
 */
public class OrgChartWriter {

    public static void writeOrgTree(String fileName, List<Org> roots) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("OrgChartText.txt"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Org root : roots) {
                Stack<Integer> stack = new Stack<>();
                int indentLevel = 0;

                writer.write(root.toString());
                writer.newLine();

                List<Org> tree = OrgCollection.getInstance().getOrgTree(root.getOrgId(), false);

                for (Org node : tree) {
                    int index = stack.search(node.getParentOrgId());
                    if (index == -1) {
                        // parent not in stack (aka depth increased)
                        stack.add(node.getParentOrgId());
                        indentLevel++;
                    }
                    else if(index>1){
                        // depth decreased
                        //index is 1 based so we subtract 1 to adjust
                        indentLevel = indentLevel-(index-1);
                        for (; index > 1; index--) {
                            stack.pop();
                        }
                    }

                    writer.write(getIndentLevel(indentLevel).append(node.toString()).toString());
                    writer.newLine();
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    private static StringBuilder getIndentLevel(int indents)
    {
        StringBuilder tabs = new StringBuilder();
        for(;indents>0;indents--)
        {
            tabs.append('\t');
        }
        return tabs;
    }

}


