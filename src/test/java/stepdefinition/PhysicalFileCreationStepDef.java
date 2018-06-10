package stepdefinition;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import model.*;
import net.thucydides.core.annotations.Steps;
import steps.NachaModelBuilder;

import java.util.List;

public class PhysicalFileCreationStepDef {

    @Steps
    NachaModelBuilder nachaModelBuilder;


    @Given("^(\\d+) logical files for physical file \"([^\"]*)\"$")
    public void logicalFilesForPhysicalFile(int logicalFileSize, String fileName) {
        nachaModelBuilder.initializeLogicalFiles(logicalFileSize, fileName);
    }

    @And("^file header for logical file (\\d+)$")
    public void fileHeaderForLogicalFile(int logicalFileNumber, List<FileHeader> fileHeaderList) {
        nachaModelBuilder.setupFileHeader(logicalFileNumber, fileHeaderList);
    }

    @And("^(\\d+) batch header for logical file (\\d+)$")
    public void batchHeaderForLogicalFile(int batchNumberSize, int logicalFileNumber, List<BatchHeader> batchHeaderList) {
        nachaModelBuilder.setupBatchHeader(batchNumberSize, logicalFileNumber, batchHeaderList);
    }

    @And("^(\\d+) entry record for batch (\\d+) in logical file (\\d+)$")
    public void entryRecordForLogicalFile(int instructionSize, int batchNumber, int logicalFileNumber, List<EntryRecord> entryRecordList) {
        nachaModelBuilder.setupEntryRecord(instructionSize, batchNumber, logicalFileNumber, entryRecordList);
    }

    @And("^additional record for batch (\\d+) in logical file (\\d+)$")
    public void additionalRecordForBatchInLogicalFile(int batchNumber, int logicalFileNumber, List<AdditionalRecord> additionalRecordList) {
        nachaModelBuilder.setupAdditionalRecord(batchNumber, logicalFileNumber, additionalRecordList);
    }

    @And("^batch control for logical file (\\d+)$")
    public void batchControlForLogicalFile(int logicalFileNumber, List<BatchControl> batchControlList) {
        nachaModelBuilder.setupBatchControl(logicalFileNumber, batchControlList);
    }

    @And("^file control for logical file (\\d+)$")
    public void fileControlForLogicalFile(int logicalFileNumber, List<FileControl> fileControlList) {
        nachaModelBuilder.setupFileControl(logicalFileNumber, fileControlList);
    }


    @And("^physical model of NACHA-M file is ready$")
    public void physicalModelOfNACHAMFileIsReady() {
        nachaModelBuilder.setupPhysicalFile();

        System.out.println(nachaModelBuilder.modelToJson());
    }
}
