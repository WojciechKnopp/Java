import java.util.ArrayList;

public class AddressBook {
    private ArrayList<Record> records;

    public AddressBook() {
        records = new ArrayList<>();
    }

    public boolean addRecord(Record record) {
        records.add(record);
        return true;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void clear() {
        records.clear();
    }
}
