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
        } else if (type.toUpperCase().equals("ONECHILDPERNODETREE")) {
            result = buildOneChildPerNodeTree();
        } else if (type.toUpperCase().equals("TWOROOTTREE")) {
            result = buildDoubleRootNodeTree();
        } else if (type.toUpperCase().equals("ALLROOTTREE")) {
            result = buildAllRootTree();
        }
        return result;
    }

    List<Org> buildTwoChildTree() {
        Org root = new Org(1, "Root", Optional.empty());
        Org child1 = new Org(2, "Child", Optional.of(1));
        Org child2 = new Org(3, "Child", Optional.of(1));
        root.addChildOrg(child1);
        root.addChildOrg(child2);

        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        result.add(child2);
        return result;
    }

    List<Org> buildOneChildPerNodeTree() {
        Org root = new Org(1, "Root", Optional.empty());
        Org child1 = new Org(2, "Child", Optional.of(1));
        Org child2 = new Org(3, "Child", Optional.of(2));

        root.addChildOrg(child1);
        child1.addChildOrg(child2);
        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        result.add(child2);
        return result;
    }

    List<Org> buildDoubleRootNodeTree() {
        Org root = new Org(1, "Root", Optional.empty());
        Org child1 = new Org(2, "Child", Optional.of(1));
        Org child2 = new Org(3, "Root", Optional.empty());

        root.addChildOrg(child1);

        List<Org> result = new ArrayList<>();
        result.add(root);
        result.add(child1);
        //result.add(child2);
        return result;
    }

    List<Org> buildAllRootTree() {
        Org root = new Org(1, "Root", Optional.empty());
        Org root1 = new Org(2, "Root", Optional.empty());
        Org root2 = new Org(3, "Root", Optional.empty());

        List<Org> result = new ArrayList<>();
        result.add(root);
//        result.add(root1);
//        result.add(root2);
        return result;
    }


}
