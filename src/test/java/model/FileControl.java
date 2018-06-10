package model;

public class FileControl {

    {
        this.sessionType = "4";
    }

    private String recordType = "9";
    private String sessionType = "2";

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }
}
