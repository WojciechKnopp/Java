import java.io.FileReader;
import java.util.*;

public class Main {

    static void showMenu(String currentBook) {
        System.out.println("Current address book: " + currentBook);
        System.out.print("Choose what you'd like to do: ");
        System.out.println("1. Manage Address Books");
        System.out.println("2. Load from file");
        System.out.println("3. Save to file");
        System.out.println("4. Add entry");
        System.out.println("5. Print book");
        System.out.println("6. Remove entry");
        System.out.println("7. Edit entry");
        System.out.println("8. Sort the address book");
        System.out.println("9. Search for a specific entry");
        System.out.println("10. Quit");
    }

    static void showBookMenu() {
        System.out.println("1. Create a new address book");
        System.out.println("2. Rename an address book");
        System.out.println("3. Delete an address book");
        System.out.println("4. List all address books");
        System.out.println("5. Switch current address book");
        System.out.println("6. Go back to main menu");
    }

    static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    public static void main(String[] args) {
        // initializing
        SortedMap<String, AddressBook> addressBook = new TreeMap<>();
        addressBook.put("default", new AddressBook());
        Scanner scanner = new Scanner(System.in);
        String currentBook = "default";

        int option;
        // program loop
        do{
            clearScreen();
            showMenu(currentBook);
            option = Integer.parseInt(scanner.nextLine());
            switch (option){
                case 1:
                    // Managing Address Books
                    showBookMenu();
                    int option2 = 0;
                    while(option2 < 1 || option2 > 6) {
                        option2 = Integer.parseInt(scanner.nextLine());
                    }
                    switch (option2){
                        case 1:
                            System.out.println("Create a new address book");
                            System.out.print("Enter the name of the new address book: ");
                            String newBookName = scanner.nextLine();
                            addressBook.put(newBookName, new AddressBook());
                            System.out.println("Created address book " + newBookName + "and switched to it");
                            currentBook = newBookName;
                            break;

                        case 2:
                            System.out.println("Rename an address book");
                            System.out.print("Enter the new name of the address book: ");
                            String newBookName2 = scanner.nextLine();
                            if(addressBook.containsKey(newBookName2))
                                System.out.println("Address book already exists");
                            else {
                                addressBook.put(newBookName2, addressBook.get(currentBook));
                                addressBook.remove(currentBook);
                                currentBook = newBookName2;
                                System.out.println("Renamed address book to " + newBookName2);
                            }
                            break;

                        case 3:
                            System.out.println("Delete an address book");
                            System.out.print("Enter the name of the address book you want to delete: ");
                            String bookName = scanner.nextLine();
                            if(addressBook.size() > 1)
                                if(addressBook.containsKey(bookName)) {
                                    System.out.println("Deleting address book " + bookName);
                                    addressBook.remove(bookName);

                                    //change current book if it was deleted
                                    if (currentBook.equals(bookName))
                                        currentBook = addressBook.firstKey();
                                }
                                else
                                    System.out.println("Address book not found");
                            else
                                System.out.println("Cannot delete the only address book");
                            break;

                        case 4:
                            System.out.println("List all address books");
                            for(String book : addressBook.keySet()) {
                                System.out.println(book);
                            }
                            break;

                        case 5:
                            System.out.println("Switch current address book");
                            System.out.print("Enter the name of the address book you want to switch to: ");
                            String bookName2 = scanner.nextLine();
                            if(addressBook.containsKey(bookName2))
                                currentBook = bookName2;
                            else
                                System.out.println("Address book not found");
                            break;

                        case 6:
                            System.out.println("Go back to main menu");
                            break;
                    }
                    break;

                //Back to normal Menu options
                case 2:
                    System.out.println("Load from file");
                    System.out.print("Enter the name of the file you want to load: ");
                    String fileName = scanner.nextLine();
                    try{
                        Scanner fileScanner = new Scanner(new FileReader(fileName));
                        while(fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            String[] data = line.split(",");
                            Record record = new Record(data[0], data[1], data[2], data[3], data[4], data[5]);
                            addressBook.get(currentBook).addRecord(record);
                        }
                    }catch (Exception e) {
                        System.out.println("File not found");
                    }
                    break;

                case 3:
                    System.out.println("Save to file");
                    System.out.println("Not implemented yet");
                    break;

                case 4:
                    System.out.println("Add entry");
                    String id, firstName, lastName, address, phone, email;
                    System.out.print("Enter id: ");
                    id = scanner.nextLine();
                    System.out.print("Enter first name: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter address: ");
                    address = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    phone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    Record record3 = new Record(id, firstName, lastName, address, phone, email);
                    addressBook.get(currentBook).addRecord(record3);
                    break;

                case 5:
                    System.out.println("Print book");
                    for(Record rec : addressBook.get(currentBook).getRecords()) {
                        System.out.println(rec);
                    }
                    break;

                case 6:
                    System.out.println("Remove entry");
                    System.out.println("Not implemented yet");
                    break;

                case 7:
                    System.out.println("Edit entry");
                    System.out.println("Not implemented yet");
                    break;

                case 8:
                    System.out.println("Sort the address book");
                    System.out.println("Not implemented yet");
                    break;

                case 9:
                    System.out.println("Search for a specific entry");
                    System.out.println("Not implemented yet");
                    break;

                case 10:
                    System.out.println("Quit");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }while(option != 10);
    }
}