/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dispatcher;

import business.KolRegisters;
import business.KolPlatForms;
import constants.AppConstants;
import ui.MenuHandler;
/**
 * Main entry point for KOL Recruitment System
 * Refactored to use better structure and separation of concerns
 * @author zzzdi
 */
public class Main {
    
    /**
     * Main method - starts the application
     */
    public static void main(String[] args) {
        try {
            // Initialize system components
            KolPlatForms platforms = new KolPlatForms(AppConstants.PLATFORM_FILE);
            KolRegisters registers = new KolRegisters(AppConstants.REGISTER_FILE);
            
            // Create and run menu handler
            MenuHandler menuHandler = new MenuHandler(registers, platforms);
            
            // Display welcome message
            displayWelcomeMessage();
            
            // Run the main menu loop
            runApplication(menuHandler);
            
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Display welcome message
     */
    private static void displayWelcomeMessage() {
        System.out.println("==================================================");
        System.out.println("     WELCOME TO KOL RECRUITMENT SYSTEM");
        System.out.println("==================================================");
        System.out.println("Managing KOL (Key Opinion Leader) Registrations");
        System.out.println();
    }
    
    /**
     * Run the main application loop
     */
    private static void runApplication(MenuHandler menuHandler) {
        boolean running = true;
        
        while (running) {
            menuHandler.displayMainMenu();
            int choice = menuHandler.getUserChoice();
            
            switch (choice) {
                case AppConstants.REGISTER_KOL:
                    menuHandler.handleRegisterKol();
                    break;
                    
                case AppConstants.UPDATE_REGISTER:
                    menuHandler.handleUpdateRegister();
                    break;
                    
                case AppConstants.DISPLAY_REGISTERS:
                    menuHandler.handleDisplayRegister();
                    break;
                    
                case AppConstants.DELETE_REGISTER:
                    menuHandler.handleDeleteRegister();
                    break;
                    
                case AppConstants.SEARCH_BY_NAME:
                    menuHandler.handleSearchByName();
                    break;
                    
                case AppConstants.FILTER_BY_CATEGORY:
                    menuHandler.handleFilterByCategory();
                    break;
                    
                case AppConstants.DISPLAY_STATISTICS:
                    menuHandler.handleDisplayStatistics();
                    break;
                    
                case AppConstants.SAVE_DATA:
                    menuHandler.handleSaveData();
                    break;
                    
                case AppConstants.EXIT:
                    menuHandler.handleExit();
                    running = false;
                    break;
                    
                default:
                    System.out.println(AppConstants.INVALID_CHOICE);
                    break;
            }
        }
    }

}

