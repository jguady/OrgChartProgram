package models;

import java.util.List;

/**
 * Created by Guady on 6/27/2017.
 */
public class User {
    private int userId;
    private int orgId;
    private int numFiles;
    private int numBytes;

    public User() {}

    public User(List<String> data)
    {
        //Map Fields
        this.userId = Integer.parseInt(data.get(0));
        this.orgId = Integer.parseInt(data.get(1));
        this.numFiles = Integer.parseInt(data.get(2));
        this.numBytes = Integer.parseInt(data.get(3));
    }


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
}
