package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Guady on 6/27/2017.
 */
public class Org implements IOrg {

    private int orgId;
    private Optional<Integer> parentOrgId;
    private String orgName;
    private int totalNumFiles = 0;
    private int totalNumBytes = 0;
    private List<User> users = new ArrayList<>();
    private List<Org> childOrgs = new ArrayList<>();

    public Org(int orgId, String orgName)
    {
        this(orgId,orgName,Optional.empty());
    }
    public Org(int orgId, String orgName, Optional<Integer> parentOrgId)
    {
        this.orgId = orgId;
        this.orgName = orgName;
        this.parentOrgId = parentOrgId;
    }

    @Override
    public int getTotalNumUsers() {
        return users.size();
    }

    @Override
    public int getTotalNumFiles() {
        return this.totalNumFiles;
    }

    @Override
    public int getTotalNumBytes() {
        return this.totalNumBytes;
    }

    @Override
    public List<Org> getChildOrgs() {
       return this.childOrgs;
    }

    public void addUser(User user)
    {
        users.add(user);
        totalNumBytes +=user.getNumBytes();
        totalNumFiles +=user.getNumFiles();
    }

    public boolean removeUser(User user)
    {
        totalNumBytes -=user.getNumBytes();
        totalNumFiles -=user.getNumFiles();
        return users.remove(user);
    }

    public void addChildOrg(Org childOrg)
    {
        childOrgs.add(childOrg);
    }

    public void removeChildOrg(Org childOrg)
    {
        childOrgs.removeIf(org -> org.getOrgId() == childOrg.getOrgId());
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Optional<Integer> getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Optional<Integer> parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
