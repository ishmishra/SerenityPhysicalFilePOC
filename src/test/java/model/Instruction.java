package model;

public class Instruction {

    private EntryRecord entryRecord;
    private AdditionalRecord additionalRecord;

    public EntryRecord getEntryRecord() {
        return entryRecord;
    }

    public void setEntryRecord(EntryRecord entryRecord) {
        this.entryRecord = entryRecord;
    }

    public AdditionalRecord getAdditionalRecord() {
        return additionalRecord;
    }

    public void setAdditionalRecord(AdditionalRecord additionalRecord) {
        this.additionalRecord = additionalRecord;
    }
}
