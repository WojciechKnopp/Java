import java.util.Scanner;

public class Main {

    static void showMenu() {
        System.out.println("1. Load from file");
        System.out.println("2. Save to file");
        System.out.println("3. Add entry");
        System.out.println("4. Print book");
        System.out.println("5. Remove entry");
        System.out.println("6. Edit entry");
        System.out.println("7. Sort the address book");
        System.out.println("8. Search for a specific entry");
        System.out.println("9. Quit");
    }

    static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    public static void main(String[] args) {
        int option;
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        // program loop
        do{
            clearScreen();
            showMenu();
            option = 0;
            while(option < 1 || option > 9) {
                System.out.print("Choose what you'd like to do: ");
                option = scanner.nextLine().charAt(0) - '0';
            }

            switch (option){
                case 1:
                    System.out.println("Load from file");
                    System.out.println("Not implemented yet");
                    break;

                case 2:
                    System.out.println("Save to file");
                    System.out.println("Not implemented yet");
                    break;

                case 3:
                    System.out.println("Add entry");
                    String firstName, lastName, address, phone, email;
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
                    Record record3 = new Record(firstName, lastName, address, phone, email);
                    addressBook.addRecord(record3);
                    break;

                case 4:
                    System.out.println("Print book");
                    for(Record record4 : addressBook.getRecords()) {
                        System.out.println(record4);
                    }
                    break;

                case 5:
                    System.out.println("Remove entry");
                    System.out.println("Not implemented yet");
                    break;
                case 6:

                    System.out.println("Edit entry");
                    System.out.println("Not implemented yet");
                    break;

                case 7:
                    System.out.println("Sort the address book");
                    System.out.println("Not implemented yet");
                    break;

                case 8:
                    System.out.println("Search for a specific entry");
                    System.out.println("Not implemented yet");
                    break;

                case 9:
                    System.out.println("Quit");
                    break;

            }
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }while(option != 9);
    }
}