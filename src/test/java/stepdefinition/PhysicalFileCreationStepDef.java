package stepdefinition;


import cucumber.api.DataTable;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import model.*;
import net.thucydides.core.annotations.Steps;
import steps.NachaModelBuilder;

import java.util.List;
import java.util.Map;

public class PhysicalFileCreationStepDef {

    @Steps
    NachaModelBuilder nachaModelBuilder;


    @Given("^(\\d+) logical files for physical file \"([^\"]*)\"$")
    public void logicalFilesForPhysicalFile(int logicalFileSize, String fileName) {
        nachaModelBuilder.initializeLogicalFiles(logicalFileSize, fileName);
    }

    @And("^file header for logical file (\\d+)$")
    public void fileHeaderForLogicalFile(int logicalFileNumber, DataTable dataTable) {
        List<Map<String,String>> fileHeaderMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupFileHeader(logicalFileNumber, fileHeaderMapList);
    }

    @And("^(\\d+) batch header for logical file (\\d+)$")
    public void batchHeaderForLogicalFile(int batchNumberSize, int logicalFileNumber,DataTable dataTable) {
        List<Map<String,String>> batchHeaderMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupBatchHeader(batchNumberSize, logicalFileNumber, batchHeaderMapList);
    }

    @And("^(\\d+) entry record for batch (\\d+) in logical file (\\d+)$")
    public void entryRecordForLogicalFile(int instructionSize, int batchNumber, int logicalFileNumber, DataTable dataTable) {
        List<Map<String,String>> entryRecordMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupEntryRecord(instructionSize, batchNumber, logicalFileNumber, entryRecordMapList);
    }

    @And("^additional record for batch (\\d+) in logical file (\\d+)$")
    public void additionalRecordForBatchInLogicalFile(int batchNumber, int logicalFileNumber,  DataTable dataTable) {
        List<Map<String,String>> additionalRecordMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupAdditionalRecord(batchNumber, logicalFileNumber, additionalRecordMapList);
    }

    @And("^batch control for logical file (\\d+)$")
    public void batchControlForLogicalFile(int logicalFileNumber, DataTable dataTable) {
        List<Map<String,String>> batchControlMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupBatchControl(logicalFileNumber, batchControlMapList);
    }

    @And("^file control for logical file (\\d+)$")
    public void fileControlForLogicalFile(int logicalFileNumber, DataTable dataTable) {
        List<Map<String,String>> fileControlMapList=dataTable.asMaps(String.class,String.class);
        nachaModelBuilder.setupFileControl(logicalFileNumber, fileControlMapList);
    }


    @And("^physical model of NACHA-M file is ready$")
    public void physicalModelOfNACHAMFileIsReady() {
        nachaModelBuilder.setupPhysicalFile();

        System.out.println(nachaModelBuilder.modelToJson());
    }
}
