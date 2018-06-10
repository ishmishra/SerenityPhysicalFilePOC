/*
package stepdefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class PhysicalFileCreationStepDefBackup {

    List<LogicalFile> logicalFiles=new ArrayList<>();
    List<Batch> batches;
    List<Instruction> instructions;

    @Given("^(\\d+) logical files for physical file \"([^\"]*)\"$")
    public void logicalFilesForPhysicalFile(int logicalFileSize, String fileName) {

        //Extract the number of logical files we are going to create and the name of the file
        //Put a logic to create a file of given name

        //iterate to initialize the the list of logical files
        for (int i = 0; i < logicalFileSize; i++) {
            logicalFiles.add(new LogicalFile());
        }


    }

    @And("^file header for logical file (\\d+)$")
    public void fileHeaderForLogicalFile(int logicalFileNumber, List<FileHeader> fileHeaderList) {

        //create list of batches for this specific logical file
        batches=new ArrayList<>();

        logicalFileNumber=logicalFileNumber-1;
        //logicalFileNumber- represents the logical file to which this file header belongs to

        logicalFiles.get(logicalFileNumber).setFileHeader(fileHeaderList.get(0));
    }

    @And("^(\\d+) batch header for logical file (\\d+)$")
    public void batchHeaderForLogicalFile(int batchNumberSize, int logicalFileNumber, List<BatchHeader> batchHeaderList) {
        logicalFileNumber=logicalFileNumber-1;
        //iterate to initialize the the list of batch
        for (int i = 0; i < batchNumberSize; i++) {
            batches.add(new Batch());
        }

        //iterate to initialize the the list of batch with batch header
        for (int i = 0; i < batchNumberSize; i++) {
            batches.get(i).setBatchHeader(batchHeaderList.get(i));
        }

        //Set value of batches and continue
        logicalFiles.get(logicalFileNumber).setBatches(batches);



    }

    @And("^(\\d+) entry record for batch (\\d+) in logical file (\\d+)$")
    public void entryRecordForLogicalFile(int instructionSize, int batchNumber, int logicalFileNumber, List<EntryRecord> entryRecordList) {

        instructions=new ArrayList<>();
        logicalFileNumber=logicalFileNumber-1;
        batchNumber=batchNumber-1;
        //iterate to initialize the the list of batch
        for (int i = 0; i < instructionSize; i++) {
            instructions.add(new Instruction());
        }

        //iterate to initialize the the list of instruction with entry record
        for (int i = 0; i < instructionSize; i++) {
            instructions.get(i).setEntryRecord(entryRecordList.get(i));
        }

        logicalFiles
                .get(logicalFileNumber)
                .getBatches()
                .get(batchNumber)
                .setInstructionList(instructions);

    }

    @And("^additional record for batch (\\d+) in logical file (\\d+)$")
    public void additionalRecordForBatchInLogicalFile(int batchNumber, int logicalFileNumber, List<AdditionalRecord> additionalRecordList)  {
        logicalFileNumber=logicalFileNumber-1;
        batchNumber=batchNumber-1;
        //iterate to initialize the the list of instruction with entry record
        for (int i = 0; i < additionalRecordList.size(); i++) {
            logicalFiles
                    .get(logicalFileNumber)
                    .getBatches()
                    .get(batchNumber)
                    .getInstructionList().get(i)
                    .setAdditionalRecord(additionalRecordList.get(i));
        }


    }

    @And("^batch control for logical file (\\d+)$")
    public void batchControlForLogicalFile(int logicalFileNumber, List<BatchControl> batchControlList) {

        logicalFileNumber=logicalFileNumber-1;
        for (int i = 0; i < batchControlList.size(); i++) {
            logicalFiles
                    .get(logicalFileNumber)
                    .getBatches()
                    .get(i)
                    .setBatchControl(batchControlList.get(i));
        }

    }

    @And("^file control for logical file (\\d+)$")
    public void fileControlForLogicalFile(int logicalFileNumber, List<FileControl> fileControlList) {

        logicalFileNumber=logicalFileNumber-1;
        logicalFiles
                .get(logicalFileNumber)
                .setFileControl(fileControlList.get(0));

    }

    @When("^file to json conversion service is called$")
    public void fileToJsonConversionServiceIsCalled(){

        PhysicalFile physicalFile = new PhysicalFile();
        physicalFile.setLogicalFileList(logicalFiles);

        System.out.println(physicalFile);
    }
}
*/
