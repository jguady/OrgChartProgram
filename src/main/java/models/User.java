package models;

import java.util.List;

/**
 * Created by Guady on 6/27/2017.
 */
public class User implements Registerable {
    private int userId;
    private int orgId;
    private int numFiles;
    private int numBytes;
    private OrgCollection orgCollection;

    public User() {

    }

    public User(int userId, int orgId, int numFiles, int numBytes, OrgCollection orgCollection) {
        this.userId = userId;
        this.orgId = orgId;
        this.numFiles = numFiles;
        this.numBytes = numBytes;
        this.orgCollection = orgCollection;
    }

    //    public User(List<String> data)
//    {
//        //Map Fields
//        this.userId = Integer.parseInt(data.get(0));
//        this.orgId = Integer.parseInt(data.get(1));
//        this.numFiles = Integer.parseInt(data.get(2));
//        this.numBytes = Integer.parseInt(data.get(3));
//    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public void setNumFiles(int numFiles) {
        this.numFiles = numFiles;
    }

    public int getNumBytes() {
        return numBytes;
    }

    public void setNumBytes(int numBytes) {
        this.numBytes = numBytes;
    }

    public OrgCollection getOrgCollection() {
        return orgCollection;
    }

    public void setOrgCollection(OrgCollection orgCollection) {
        this.orgCollection = orgCollection;
    }

    @Override
    public void register() {

    }
}
