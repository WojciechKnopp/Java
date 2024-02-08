// Author: Wojciech Knopp
// Creation date: 06.01.2024
// Last modification date: 08.02.2024
// Description: Terminal application for managing address books

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static void showMenu(String currentBook) {
        System.out.println("Current address book: " + currentBook);
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
        System.out.print("Choose what you'd like to do: ");
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

    static void loadBook(AddressBook addressBook, String fileName) {
        try{
            Scanner fileScanner = new Scanner(new FileReader(fileName));
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                Record record = new Record(data[0], data[1], data[2], data[3], data[4], data[5]);
                addressBook.addRecord(record);
            }
            System.out.println("Loaded file " + fileName);
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    static void saveBook(AddressBook addressBook, String fileName) {
        try{
            File file = new File(fileName);
            if(file.createNewFile()) {
                System.out.println("Created file " + fileName);
                Formatter formatter = new Formatter(fileName);
                for (Record rec : addressBook.getRecords()) {
                    formatter.format("%s,%s,%s,%s,%s,%s\n", rec.getId(), rec.getFirstName(), rec.getLastName(), rec.getPhoneNumber(), rec.getAddress(), rec.getEmailAddress());
                }
                formatter.close();
                System.out.println("Saved to file " + fileName);
            }
            else{
                //rewrite the file
                Formatter formatter = new Formatter(fileName);
                for (Record rec : addressBook.getRecords()) {
                    formatter.format("%s,%s,%s,%s,%s,%s\n", rec.getId(), rec.getFirstName(), rec.getLastName(), rec.getPhoneNumber(), rec.getAddress(), rec.getEmailAddress());
                }
                formatter.close();
                System.out.println("Saved to file " + fileName);
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
        }
    }

    static Optional<String> listBooks(String dataPath) {
        File folder = new File(dataPath);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles == null){
            return Optional.empty();
        }
        StringBuilder books = new StringBuilder();
        for(File file : listOfFiles) {
            if(file.isFile()) {
                books.append(file.getName()).append("\n");
            }
        }
        return Optional.of(books.toString());
    }

    static String chooseBook(AddressBook addressBook, String dataPath) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the address book from:");
        Optional<String> books = listBooks(dataPath);
        if(books.isEmpty()) {
            System.out.println("No address books found");
            return null;
        }
        System.out.println(books.get());
        String bookName = scanner.nextLine();
        //check if file exists
        File file = new File(dataPath + "/" + bookName);
        if(file.exists()) {
            loadBook(addressBook, dataPath + "/" + bookName);
            return bookName;
        }
        else
            System.out.println("Address book not found");
        return null;
    }


    public static void main(String[] args) {
        // initializing
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        String currentBook = null;
        String dataPath = "Data";

        //choose the address book
        currentBook = chooseBook(addressBook, dataPath);
        //create a new address book if none exists or none was chosen
        if(currentBook == null) {
            System.out.println("Creating a new address book");
            System.out.print("Enter the name of the new address book: ");
            String newBookName = scanner.nextLine();
            File newBook = new File(dataPath + "/" + newBookName);
            try {
                if(newBook.createNewFile()) {
                    System.out.println("Created address book " + newBookName);
                    currentBook = newBookName;
                }
                else {
                    System.out.println("Address book already exists");
                    currentBook = newBookName;
                    loadBook(addressBook, dataPath + "/" + currentBook);
                }
            } catch (IOException e) {
                System.out.println("Error creating address book");
            }
        }

        int option;
        // program loop
        do{
            clearScreen();
            showMenu(currentBook);
            option = Integer.parseInt(scanner.nextLine());
            switch (option){
                case 1:
                    // Managing Address Books
                    clearScreen();
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
                            File newBook = new File(dataPath + "/" + newBookName);
                            try {
                                if(newBook.createNewFile()) {
                                    System.out.println("Created address book " + newBookName + " and switched to it");
                                    saveBook(addressBook, dataPath + "/" + currentBook);
                                    loadBook(addressBook, dataPath + "/" + newBookName);
                                    currentBook = newBookName;
                                }
                                else
                                    System.out.println("Address book already exists");
                            } catch (IOException e) {
                                System.out.println("Error creating address book");
                            }
                            break;

                        case 2:
                            System.out.println("Rename current address book: '" + currentBook + "'");
                            File renamedBook = new File(dataPath + "/" + currentBook);
                            System.out.print("Enter the new name of the address book: ");
                            String newBookName2 = scanner.nextLine();
                            if(renamedBook.renameTo(new File(dataPath + "/" + newBookName2))) {
                                System.out.println("Renamed address book to " + newBookName2);
                                currentBook = newBookName2;
                            }
                            else
                                System.out.println("Error renaming address book");
                            break;

                        case 3:
                            System.out.println("Delete an address book");
                            System.out.print("Enter the name of the address book you want to delete: ");
                            String bookName = scanner.nextLine();
                            //find all files in dataPath
                            File dataFolder = new File(dataPath);
                            File[] listOfAllFiles = dataFolder.listFiles();
                            if(listOfAllFiles != null) {
                                if(listOfAllFiles.length > 1) {
                                    for(File file : listOfAllFiles) {
                                        if(file.getName().equals(bookName)) {
                                            System.out.println("Deleting address book " + bookName);
                                            if(file.delete()) {
                                                if (currentBook.equals(bookName))
                                                    if (listOfAllFiles[0].getName().equals(bookName))
                                                        currentBook = listOfAllFiles[1].getName();
                                                    else
                                                        currentBook = listOfAllFiles[0].getName();
                                                break;
                                            }
                                            else
                                                System.out.println("Error deleting address book");
                                        }
                                    }
                                }
                                else
                                    System.out.println("Cannot delete the only address book");
                            }
                            else
                                System.out.println("No address books found");
                            break;

                        case 4:
                            System.out.println("List all address books");
                            //find all files in dataPath
                            File folder = new File(dataPath);
                            File[] listOfFiles = folder.listFiles();
                            if(listOfFiles == null){
                                System.out.println("No address books found");
                                break;
                            }
                            for(File file : listOfFiles) {
                                if(file.isFile()) {
                                    System.out.println(file.getName());
                                }
                            }
                            break;

                        case 5:
                            System.out.println("Switch current address book");
                            System.out.print("Enter the name of the address book you want to switch to: ");
                            String bookName2 = scanner.nextLine();
                            //check if file exists
                            File file = new File(dataPath + "/" + bookName2);
                            if(file.exists()) {
                                saveBook(addressBook, dataPath + "/" + currentBook);
                                loadBook(addressBook, dataPath + "/" + bookName2);
                                currentBook = bookName2;
                            }
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
                    clearScreen();
                    System.out.println("Load from file");
                    System.out.print("Enter the name of the file you want to load: ");
                    String fileName = scanner.nextLine();
                    loadBook(addressBook, dataPath + "/" + fileName);
                    break;

                case 3:
                    clearScreen();
                    System.out.println("Save to file");
                    System.out.print("Enter the name of the file you want to save to: ");
                    String fileName2 = scanner.nextLine();
                    saveBook(addressBook, dataPath + "/" + fileName2);
                    break;

                case 4:
                    clearScreen();
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
                    addressBook.addRecord(record3);
                    break;

                case 5:
                    clearScreen();
                    System.out.println("Print book");
                    for(Record rec : addressBook.getRecords()) {
                        System.out.println(rec.getId() + " " + rec.getFirstName() + " " + rec.getLastName() + " " + rec.getPhoneNumber() + " " + rec.getAddress() + " " + rec.getEmailAddress());
                    }
                    break;

                case 6:
                    clearScreen();
                    System.out.println("Remove entry");
                    System.out.println("Not implemented yet");
                    break;

                case 7:
                    clearScreen();
                    System.out.println("Edit entry");
                    System.out.println("Not implemented yet");
                    break;

                case 8:
                    clearScreen();
                    System.out.println("Sort the address book");
                    System.out.println("Not implemented yet");
                    break;

                case 9:
                    clearScreen();
                    System.out.println("Search for a specific entry");
                    System.out.println("Not implemented yet");
                    break;

                case 10:
                    clearScreen();
                    System.out.println("Quit");
                    break;

                default:
                    clearScreen();
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }while(option != 10);
    }
}