/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;
import business.KolRegisters;
import business.KolPlatForms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import models.KolRegister;
import models.KolPlatForm;
import tools.Acceptable;
import tools.Inputter;
/**
 *
 * @author zzzdi
 */
public class Main {
    private static final int REGISTER_KOL = 1;
    private static final int UPDATE_REGISTER = 2;
    private static final int DISPLAY_REGISTERS = 3;
    private static final int DELETE_REGISTER = 4;
    private static final int SEARCH_BY_NAME = 5;
    private static final int FILTER_BY_CATEGORY = 6;
    private static final int DISPLAY_REGISTER_NUMBERS_BY_FLATFORM = 7;
    private static final int SAVE_REGISTER = 8;
    private static final int EXIT = 9;
    private static final int RETURN_MAIN_MENU = 2;
    private static final String PLATFORM_FILE = "KOLList.csv";
    private static final String REGISTER_FILE = "kol_registrations.dat";
    private static boolean isSaved = false;

    private static Inputter inputter;
    private static KolRegisters registers;
    private static KolPlatForms platforms;
    private static Scanner scanner;

    private static void initializeSystem() {
        inputter = new Inputter();
        platforms = new KolPlatForms(PLATFORM_FILE);
        registers = new KolRegisters(REGISTER_FILE);
        scanner = new Scanner(System.in);
    }

    private static void displayMainMenu() {
        System.out.println("\n----------MAIN MENU------------");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search KOLs by Name");
        System.out.println("6. Filter Data by Category");
        System.out.println("7. Statistics of Registration Numbers by Platform");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
        System.out.print("Enter choice: ");
    }

    private static int getChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void runMainMenu() {
        while (true) {
            displayMainMenu();
            int choice = getChoice();
            processMenuChoice(choice);
        }
    }

    private static void processMenuChoice(int testCase) {
        switch (testCase) {
            case REGISTER_KOL:
                handleRegisterKol();
                break;
            case UPDATE_REGISTER:
                handleUpdateRegister();
                break;
            case DISPLAY_REGISTERS:
                handleDisplayRegister();
                break;
            case DELETE_REGISTER:
                handleDeleteRegister();
                break;
            case SEARCH_BY_NAME:
                handleSearchByName();
                break;
            case FILTER_BY_CATEGORY:
                handleFilterByCategory();
                break;
            case DISPLAY_REGISTER_NUMBERS_BY_FLATFORM:
                handleDisplayRegisterNumbersByPlatform();
                break;
            case SAVE_REGISTER:
                handleSaveRegister();
                isSaved = true;
                break;
            case EXIT:
                handleExit(isSaved);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 9.");
                break;
        }
    }

    private static void handleRegisterKol() {
        int option;
        do {
            registers.addNew(inputter.inputRegister(false, registers, platforms));
            System.out.println("1. Continue adding KOL");
            System.out.println("2. Return to the main menu");
            System.out.println("Enter your option: ");
            option = Integer.parseInt(scanner.nextLine());
        } while (option != RETURN_MAIN_MENU);
    }

    private static void handleUpdateRegister() {
        int option;
        do {
            registers.update(inputter.inputRegister(true, registers, platforms));
            System.out.println("1. Continue updating KOL");
            System.out.println("2. Return to the main menu");
            System.out.println("Enter your option: ");
            option = Integer.parseInt(scanner.nextLine());
        } while (option != RETURN_MAIN_MENU);
    }

    private static void handleDisplayRegister() {
        if (registers == null || registers.isEmpty()) {
            System.err.println("No KOLs have registered yet");
        } else {
            registers.showAll(null, platforms);
        }
    }

    private static void handleDeleteRegister() {
        String keyword = "";
        while (true) {
            System.out.println("Input kol code for delete(kol code must start with(BT,FS,BC,GM,TL) and 6 numberics after): ");
            keyword = scanner.nextLine();
            if (keyword.matches(Acceptable.idRegex)) {
                break;
            } else {
                System.err.println("kol code must start with(BT,FS,BC,GM,TL) and 6 numberics after");
            }
        }
        KolRegister user = registers.searchById(keyword);
        if (user == null) {
            System.err.println("This KOL has not registered yet");
        } else {
            user.display(platforms);
            System.out.println("Are you sure you want to delete this registration? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("Y")) {
                registers.delete(user);
                System.out.println("The registration has been successfully deleted.");
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.println("The registration has NOT been deleted.");
            } else {
                System.out.println("Invalid choice. No action taken.");
            }
        }
    }

    private static void handleSearchByName() {
        System.out.println("Input name for search: ");
        String keyword = scanner.nextLine();
        List<KolRegister> list = registers.filterByName(keyword);
        if (list == null || list.isEmpty()) {
            System.err.println("No one matches the search criteria!");
        } else {
            registers.showAll(list, platforms);
        }
    }

    private static void handleFilterByCategory() {
        System.out.println("Input category code(e.g., BT,FS,BC,GM,TL) for search: ");
        String keyword = scanner.nextLine();
        List<KolRegister> list = registers.filterByCategoty(keyword);
        if (list == null || list.isEmpty()) {
            System.out.println("No category has registered under this category.");
        } else {
            registers.showAll(list, platforms);
        }
    }

    private static void handleDisplayRegisterNumbersByPlatform() {
        List<KolRegister> list = registers.getAll();
        if (list == null || list.isEmpty()) {
            System.err.println("No kol has registered yet!");
        } else {
            platforms.showCategoryByPlatform(list);
        }
    }

    private static void handleSaveRegister() {
        if (registers == null || registers.isEmpty()) {
            System.err.println("No kol registered yet to save");
        } else {
            registers.saveToFile();
            System.out.println("Save all of registers successfully!");
        }
    }

    private static void handleExit(boolean isSaved) {
        if (registers == null || registers.isEmpty()) {
            System.out.println("No data to save. Exiting program...");
            System.exit(0);
        } else if (isSaved) {
            System.out.println("All changes already saved. Exiting program...");
            System.exit(0);
        } else {
            System.out.print("Do you want to save the changes before exiting? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("Y")) {
                registers.saveToFile();
                System.out.println("Data saved successfully. Exiting program...");
                System.exit(0);
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.print("You have unsaved changes. Are you sure you want to exit without saving? (Y/N): ");
                String confirm = scanner.nextLine().trim();
                if (confirm.equalsIgnoreCase("Y")) {
                    System.out.println("Exiting without saving...");
                    System.exit(0);
                } else {
                    System.out.println("Exit cancelled. Returning to main menu...");
                }
            } else {
                System.out.println("Invalid input. Returning to main menu...");
            }
        }
    }

    public static void main(String[] args) {
        initializeSystem();
        runMainMenu();
    }

}

