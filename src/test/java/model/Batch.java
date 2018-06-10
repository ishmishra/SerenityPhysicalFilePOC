package model;

import java.util.List;

public class Batch {
    private BatchHeader batchHeader;
    List<Instruction> instructionList;
    private BatchControl batchControl;

    public BatchHeader getBatchHeader() {
        return batchHeader;
    }

    public void setBatchHeader(BatchHeader batchHeader) {
        this.batchHeader = batchHeader;
    }

    public List<Instruction> getInstructionList() {
        return instructionList;
    }

    public void setInstructionList(List<Instruction> instructionList) {
        this.instructionList = instructionList;
    }

    public BatchControl getBatchControl() {
        return batchControl;
    }

    public void setBatchControl(BatchControl batchControl) {
        this.batchControl = batchControl;
    }
}
