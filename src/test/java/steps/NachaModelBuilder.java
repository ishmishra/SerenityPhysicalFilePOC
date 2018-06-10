package steps;

import model.*;
import net.thucydides.core.annotations.Step;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NachaModelBuilder {

    private PhysicalFile physicalFile = new PhysicalFile();
    private List<LogicalFile> logicalFiles = new ArrayList<>();
    private List<Batch> batches;
    private List<Instruction> instructions;
    //
    private List<FileHeader> fileHeaderList;
    private List<BatchHeader> batchHeaderList;
    private List<EntryRecord> entryRecordList;
    private List<AdditionalRecord> additionalRecordList;
    private List<BatchControl> batchControlList;
    private List<FileControl> fileControlList;

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
    public void setupFileHeader(int logicalFileNumber, List<Map<String, String>> fileHeaderMapList) {
        //create list of batches for this specific logical file
        batches = new ArrayList<>();

        logicalFileNumber = logicalFileNumber - 1;
        //logicalFileNumber- represents the logical file to which this file header belongs to

        fileHeaderList = new ArrayList<>();//A list is not really required , but in order to keep setFields method generic
        fileHeaderList.add(new FileHeader());
        setFields(fileHeaderList, fileHeaderMapList);
        logicalFiles.get(logicalFileNumber).setFileHeader(fileHeaderList.get(0));
    }

    @Step
    public void setupBatchHeader(int batchNumberSize, int logicalFileNumber, List<Map<String, String>> batchHeaderMapList) {
        logicalFileNumber = logicalFileNumber - 1;

        batchHeaderList = new ArrayList<>();
        for (int i = 0; i < batchHeaderMapList.size(); i++) {
            batchHeaderList.add(new BatchHeader());
        }
        setFields(batchHeaderList, batchHeaderMapList);

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
    public void setupEntryRecord(int instructionSize, int batchNumber, int logicalFileNumber, List<Map<String, String>> entryRecordMapList) {

        entryRecordList = new ArrayList<>();
        for (int i = 0; i < entryRecordMapList.size(); i++) {
            entryRecordList.add(new EntryRecord());
        }
        setFields(entryRecordList, entryRecordMapList);
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
    public void setupAdditionalRecord(int batchNumber, int logicalFileNumber, List<Map<String, String>> additionalRecordMapList) {

        additionalRecordList = new ArrayList<>();
        for (int i = 0; i < additionalRecordMapList.size(); i++) {
            additionalRecordList.add(new AdditionalRecord());
        }
        setFields(additionalRecordList, additionalRecordMapList);

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
    public void setupBatchControl(int logicalFileNumber, List<Map<String, String>> batchControlMapList) {

        batchControlList = new ArrayList<>();
        for (int i = 0; i < batchControlMapList.size(); i++) {
            batchControlList.add(new BatchControl());
        }
        setFields(batchControlList, batchControlMapList);

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
    public void setupFileControl(int logicalFileNumber,List<Map<String, String>> fileControlMapList) {

        fileControlList = new ArrayList<>();
        for (int i = 0; i < fileControlMapList.size(); i++) {
            fileControlList.add(new FileControl());
        }
        setFields(fileControlList, fileControlMapList);

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
    private void setFields(List recordList, List<Map<String, String>> listOfDataTable) {

        assert recordList.size() == listOfDataTable.size();
        for (int i = 0; i < recordList.size(); i++) {

            try {
                for (Field field : recordList.get(i).getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (Optional.ofNullable(listOfDataTable.get(i).get(field.getName())).isPresent() && !Optional.ofNullable(listOfDataTable.get(i).get(field.getName())).get().equals("") ) {
                        field.set(recordList.get(i), listOfDataTable.get(i).get(field.getName()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
