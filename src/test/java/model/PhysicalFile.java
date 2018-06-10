package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PhysicalFile {

    private List<LogicalFile> logicalFileList;
    public List<LogicalFile> getLogicalFileList() {
        return logicalFileList;
    }

    public void setLogicalFileList(List<LogicalFile> logicalFileList) {
        this.logicalFileList = logicalFileList;
    }


    public String modelToJson() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
