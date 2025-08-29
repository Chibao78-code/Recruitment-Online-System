package ui;

import business.KolRegisters;
import business.KolPlatForms;
import constants.AppConstants;
import models.KolRegister;
import tools.Inputter;
import java.util.List;
import java.util.Scanner;

/**
 * Handles menu operations and user interactions
 * @author zzzdi
 */
public class MenuHandler {
    
    private final KolRegisters registers;
    private final KolPlatForms platforms;
    private final Inputter inputter;
    private final Scanner scanner;
    private boolean isSaved;
    
    public MenuHandler(KolRegisters registers, KolPlatForms platforms) {
        this.registers = registers;
        this.platforms = platforms;
        this.inputter = new Inputter();
        this.scanner = new Scanner(System.in);
        this.isSaved = false;
    }
    
    public void displayMainMenu() {
        System.out.println("\n========== KOL RECRUITMENT SYSTEM ==========");
        System.out.println("1. New Registration");
        System.out.println("2. Update Registration Information");
        System.out.println("3. Display Registered List");
        System.out.println("4. Delete Registration Information");
        System.out.println("5. Search KOLs by Name");
        System.out.println("6. Filter Data by Category");
        System.out.println("7. Statistics by Platform");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
        System.out.println("============================================");
        System.out.print("Enter your choice: ");
    }
    
    public int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public void handleRegisterKol() {
        boolean continueAdding = true;
        while (continueAdding) {
            KolRegister newKol = inputter.inputRegister(false, registers, platforms);
            if (newKol != null) {
                registers.addNew(newKol);
                isSaved = false;
            }
            continueAdding = askToContinue("adding KOL");
        }
    }
    
    public void handleUpdateRegister() {
        boolean continueUpdating = true;
        while (continueUpdating) {
            KolRegister updatedKol = inputter.inputRegister(true, registers, platforms);
            if (updatedKol != null) {
                registers.update(updatedKol);
                isSaved = false;
            }
            continueUpdating = askToContinue("updating KOL");
        }
    }
    
    public void handleDisplayRegister() {
        if (registers.isEmpty()) {
            System.err.println(AppConstants.NO_KOLS_REGISTERED);
        } else {
            registers.showAll(null, platforms);
        }
    }
    
    public void handleDeleteRegister() {
        System.out.print("Enter KOL code to delete (e.g., BT123456): ");
        String code = scanner.nextLine().trim();
        
        if (!code.matches(AppConstants.ID_PATTERN)) {
            System.err.println("Invalid KOL code format!");
            return;
        }
        
        KolRegister kol = registers.searchById(code);
        if (kol == null) {
            System.err.println(AppConstants.KOL_NOT_FOUND);
            return;
        }
        
        kol.display(platforms);
        System.out.print("Are you sure you want to delete this registration? (Y/N): ");
        String choice = scanner.nextLine().trim();
        
        if (choice.equalsIgnoreCase("Y")) {
            registers.delete(kol);
            System.out.println(AppConstants.DELETE_SUCCESS);
            isSaved = false;
        } else {
            System.out.println(AppConstants.DELETE_CANCELLED);
        }
    }
    
    public void handleSearchByName() {
        System.out.print("Enter name to search: ");
        String keyword = scanner.nextLine().trim();
        
        List<KolRegister> results = registers.filterByName(keyword);
        if (results.isEmpty()) {
            System.out.println("No KOLs found with name containing: " + keyword);
        } else {
            System.out.println("Search results for: " + keyword);
            registers.showAll(results, platforms);
        }
    }
    
    public void handleFilterByCategory() {
        System.out.print("Enter category code (BT/FS/BC/GM/TL): ");
        String category = scanner.nextLine().trim().toUpperCase();
        
        List<KolRegister> results = registers.filterByCategoty(category);
        if (results.isEmpty()) {
            System.out.println("No KOLs found in category: " + category);
        } else {
            System.out.println("KOLs in category: " + category);
            registers.showAll(results, platforms);
        }
    }
    
    public void handleDisplayStatistics() {
        List<KolRegister> allKols = registers.getAll();
        if (allKols.isEmpty()) {
            System.err.println(AppConstants.NO_KOLS_REGISTERED);
        } else {
            platforms.showCategoryByPlatform(allKols);
        }
    }
    
    public void handleSaveData() {
        if (registers.isEmpty()) {
            System.err.println("No data to save!");
        } else {
            registers.saveToFile();
            System.out.println(AppConstants.SAVE_SUCCESS);
            isSaved = true;
        }
    }
    
    public void handleExit() {
        if (!isSaved && !registers.isEmpty()) {
            System.out.print("You have unsaved changes. Save before exit? (Y/N): ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("Y")) {
                registers.saveToFile();
                System.out.println(AppConstants.SAVE_SUCCESS);
            }
        }
        System.out.println("Thank you for using KOL Recruitment System!");
        System.out.println("Goodbye!");
        System.exit(0);
    }
    
    private boolean askToContinue(String action) {
        System.out.println("\n1. Continue " + action);
        System.out.println("2. Return to main menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice == AppConstants.CONTINUE_ACTION;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean isSaved() {
        return isSaved;
    }
}
