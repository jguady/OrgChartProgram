package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Guady on 6/27/2017.
 */
public class Org implements IOrg {

    private int orgId;
    private Integer parentOrgId;
    private String name;
    private List<User> users = new ArrayList<>();
    private List<Org> childOrgs = new ArrayList<>();

    public Org() {
    }

    public Org(int orgId, String name) {
        this(orgId, name, null);
    }

    public Org(int orgId, String name, Integer parentOrgId) {
        this.orgId = orgId;
        this.name = name;
        this.parentOrgId = parentOrgId;
    }

    @Override
    public int getTotalNumUsers() {
        int size = users.size();
        size += childOrgs.stream().collect(Collectors.summingInt((Org::getTotalNumUsers)));
        return size;
    }


    @Override
    public int getTotalNumFiles() {
        int files = users.stream().collect(Collectors.summingInt(User::getNumFiles));
        files += childOrgs.stream().collect(Collectors.summingInt((Org::getTotalNumFiles)));
        return files;
    }

    @Override
    public int getTotalNumBytes() {
        int bytes = users.stream().collect(Collectors.summingInt(User::getNumBytes));
        bytes += childOrgs.stream().collect(Collectors.summingInt((Org::getTotalNumBytes)));
        return bytes;
    }

    @Override
    public List<Org> getChildOrgs() {
        return this.childOrgs;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean removeUser(User user) {
        return users.remove(user);
    }

    public void addChildOrg(Org childOrg) throws UnsupportedOperationException {
        if(this.getOrgId() == childOrg.getOrgId())
            throw new UnsupportedOperationException("Self Referenced Org Detected :"+ this.orgId);
        long matches = childOrg.getChildOrgs().stream().filter(child -> child.getOrgId() == this.orgId).count();
        if(matches>0)
            throw new UnsupportedOperationException("Org Cycle Detected :"+ this.orgId + " child: " + childOrg.getOrgId());


        childOrgs.add(childOrg);
    }

    public void removeChildOrg(Org childOrg) {
        throw new UnsupportedOperationException("Method not yet implemented");
        //TODO: Implement RemoveChildOrg
//        childOrgs.removeIf(org -> org.getOrgId() == childOrg.getOrgId());
//        childOrg.setParentOrgId(null);
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Integer getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Integer parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(orgId);
        str.append(',');
        str.append(getTotalNumUsers());
        str.append(',');
        str.append(getTotalNumFiles());
        str.append(',');
        str.append(getTotalNumBytes());

        return str.toString();
    }
}
