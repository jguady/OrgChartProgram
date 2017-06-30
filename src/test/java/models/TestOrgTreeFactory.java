package models;

import java.util.*;

/**
 * Created by Guady on 6/28/2017.
 */
public class TestOrgTreeFactory {
    private static TestOrgTreeFactory ourInstance = new TestOrgTreeFactory();

    public static TestOrgTreeFactory get() {
        return ourInstance;
    }

    public List<Org> createTree(String type) {
        List<Org> result = Collections.emptyList();
        if (type.toUpperCase().equals("TWOCHILDTREE")) {
            result = buildTwoChildTree();
        }
        else if (type.toUpperCase().equals("ONECHILDPERNODETREE")) {
            result = buildOneChildPerNodeTree();
        }
        else if (type.toUpperCase().equals("TWOROOTTREE")) {
            result = buildDoubleRootNodeTree();
        }
        else if (type.toUpperCase().equals("ALLROOTTREE")) {
            result = buildAllRootTree();
        }
        else if (type.toUpperCase().equals("ORDERTESTTREE")) {
            result = buildComplexOrderTree();
        }
        else if (type.toUpperCase().equals("COMPLEXCYCLETREE")) {
            result = buildComplexCycleTree();
        }
        return result;
    }

    List<Org> buildTwoChildTree() {
        Org root = new Org(1, "Root", null);
        Org child1 = new Org(2, "Child", 1);
        Org child2 = new Org(3, "Child", 1);
        root.addChildOrg(child1);
        root.addChildOrg(child2);

        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        result.add(child2);
        return result;
    }

    List<Org> buildOneChildPerNodeTree() {
        Org root = new Org(1, "Root", null);
        Org child1 = new Org(2, "Child", 1);
        Org child2 = new Org(3, "Child", 2);

        root.addChildOrg(child1);
        child1.addChildOrg(child2);
        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        result.add(child2);
        return result;
    }

    List<Org> buildDoubleRootNodeTree() {
        Org root = new Org(1, "Root", null);
        Org child1 = new Org(2, "Child", 1);

        root.addChildOrg(child1);

        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        return result;
    }

    List<Org> buildAllRootTree() {
        Org root = new Org(1, "Root", null);

        List<Org> result = new ArrayList<>();
        result.add(root);
        return result;
    }

    List<Org> buildComplexOrderTree() {
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 2);
        Org orgD = new Org(4, "Child", 1);
        Org orgE = new Org(5, "Child", 2);
        // A-> B, D
        orgA.addChildOrg(orgB);
        orgA.addChildOrg(orgD);
        // B-> C, E
        orgB.addChildOrg(orgC);
        orgB.addChildOrg(orgE);

        List<Org> result = new ArrayList<>();
        //Order should be
        // A D B E C
        result.add(orgA);
        result.add(orgD);
        result.add(orgB);
        result.add(orgE);
        result.add(orgC);

        return result;
    }

    List<Org> buildComplexCycleTree() {
        Org orgA = new Org(1, "Root", null);
        Org orgB = new Org(2, "Child", 1);
        Org orgC = new Org(3, "Child", 2);
        Org orgD = new Org(4, "Child", 1);
        Org orgE = new Org(5, "Child", 2);
        Org orgF = new Org(4, "Child", 1);
        Org orgJ = new Org(5, "Child", 2);
        // A-> B, D
        orgA.addChildOrg(orgB);
        orgA.addChildOrg(orgD);
        // B-> C, E
        orgB.addChildOrg(orgC);
        orgB.addChildOrg(orgE);
        // E-> F
        orgE.addChildOrg(orgF);
        // F-> E
        orgF.addChildOrg(orgE);

        List<Org> result = new ArrayList<>();
        //Order should be
        // A D B E C
        result.add(orgA);
        result.add(orgD);
        result.add(orgB);
        result.add(orgE);
        result.add(orgF);
        result.add(orgC);


        return result;
    }


}
