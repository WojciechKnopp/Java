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

    public boolean removeRecordById(int id) {
        for (Record record : records) {
            if (record.getId() == id) {
                records.remove(record);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void clear() {
        records.clear();
    }
}
