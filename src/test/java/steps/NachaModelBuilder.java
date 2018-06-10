package steps;

import model.*;
import net.thucydides.core.annotations.Step;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NachaModelBuilder {

    private PhysicalFile physicalFile = new PhysicalFile();
    private List<LogicalFile> logicalFiles = new ArrayList<>();
    private List<Batch> batches;
    private List<Instruction> instructions;

    @Step
    public void initializeLogicalFiles(int logicalFileSize, String fileName) {
        //Extract the number of logical files we are going to create and the name of the file
        //Put a logic to create a file of given name

        //iterate to initialize the the list of logical files
        for (int i = 0; i < logicalFileSize; i++) {
            logicalFiles.add(new LogicalFile());
        }
    }

    @Step
    public void setupFileHeader(int logicalFileNumber, List<FileHeader> fileHeaderList) {
        //create list of batches for this specific logical file
        batches = new ArrayList<>();

        logicalFileNumber = logicalFileNumber - 1;
        //logicalFileNumber- represents the logical file to which this file header belongs to

        logicalFiles.get(logicalFileNumber).setFileHeader(fileHeaderList.get(0));
    }
    @Step
    public void setupBatchHeader(int batchNumberSize, int logicalFileNumber, List<BatchHeader> batchHeaderList) {
        logicalFileNumber = logicalFileNumber - 1;
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
    @Step
    public void setupEntryRecord(int instructionSize, int batchNumber, int logicalFileNumber, List<EntryRecord> entryRecordList) {

        //Initialize an instructions list for this logical file and a specific batch number
        instructions = new ArrayList<>();

        logicalFileNumber = logicalFileNumber - 1;
        batchNumber = batchNumber - 1;
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
    @Step
    public void setupAdditionalRecord(int batchNumber, int logicalFileNumber, List<AdditionalRecord> additionalRecordList) {

        logicalFileNumber = logicalFileNumber - 1;
        batchNumber = batchNumber - 1;
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
    @Step
    public void setupBatchControl(int logicalFileNumber, List<BatchControl> batchControlList) {

        logicalFileNumber = logicalFileNumber - 1;
        for (int i = 0; i < batchControlList.size(); i++) {
            logicalFiles
                    .get(logicalFileNumber)
                    .getBatches()
                    .get(i)
                    .setBatchControl(batchControlList.get(i));
        }
    }
    @Step
    public void setupFileControl(int logicalFileNumber, List<FileControl> fileControlList) {
        logicalFileNumber = logicalFileNumber - 1;
        logicalFiles
                .get(logicalFileNumber)
                .setFileControl(fileControlList.get(0));
    }
    @Step
    public void setupPhysicalFile() {
        physicalFile.setLogicalFileList(logicalFiles);
    }
    @Step
    public String modelToJson() {
        return physicalFile.modelToJson();
    }

    @Step
    private void setupFileHeaderNullValues(FileHeader fileHeader){

        for (Field field: FileHeader.class.getDeclaredFields()){

            try {
                field.setAccessible(true);
                if (field.get(this).equals(null)){


                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
