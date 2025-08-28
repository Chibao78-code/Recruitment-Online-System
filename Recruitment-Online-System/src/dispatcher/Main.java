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
import tools.Inputter;
/**
 *
 * @author zzzdi
 */
public class Main {
        private static final int IMPORT_USER = 1;
    private static final int UPDATE_USER = 2;
    private static final int DISPLAY_USER = 3;
    private static final int UPDATE_GUEST = 4;
    private static final int DISPLAY_GUEST = 5;
    private static final int DELETE_GUEST = 6;
    private static final int DISPLAY_VACANT_ROOM = 7;
    private static final int MONTHLY_REVENUE_REPORT = 8;
    private static final int EXIT = 9;
    private static final int RETURN_MAIN__MENU = 2;
    private static final String KOL_FILE = "KOLList.csv";
    private static final String USER_FILE = "kol_registrations.dat";

    private static Inputter inputter;
    private static KolRegisters users;
    private static KolPlatForms kols;
    private static Scanner scanner;

    private static void initializeSystem() {
        inputter = new Inputter();
        kols = new KolPlatForms(KOL_FILE);
        users = new KolRegisters(USER_FILE);
        scanner = new Scanner(System.in);
    }

    private static void displayMainMenu() {
        System.out.println("\n----------MAIN MENU------------");
        System.out.println("1. Register user");
        System.out.println("2. Update user");
        System.out.println("3. Enter Guest Information");
        System.out.println("4. Update Guest Stay Information");
        System.out.println("5. Search Guest by National ID");
        System.out.println("6. Delete Guest Reservation Before Arrival");
        System.out.println("7. List Vacant Rooms");
        System.out.println("8. Monthly Revenue Report");
        System.out.println("9. Exit");
        System.out.print("Enter Test Case No. : ");
    }
        private static void runMainMenu() {
        int testcase;
        do {
            displayMainMenu();
            testcase = getChoice();
            processMenuChoice(testcase);
        } while (testcase != EXIT);
    }

    private static void processMenuChoice(int testCase) {
        switch (testCase) {
            case IMPORT_USER:
                handleRegisterUser();
                break;
            case UPDATE_USER:
                handleUpdateUser();
                break;
            case DISPLAY_USER:
                handleDisplayUser();
                break;
            case EXIT:
                //handleExit();
                break;
            default:
                throw new AssertionError();
        }
    }
        private static void handleRegisterUser() {
        int option;
        do {
            users.addNew(inputter.inputUser(false, users, kols));
            System.out.println("1. Continue add guest");
            System.out.println("2. Return to the main menu");
            System.out.println("Enter your option: ");
            option = Integer.parseInt(scanner.nextLine());
        } while (option != RETURN_MAIN__MENU);
    }

    private static void handleUpdateUser() {
        int option;
        do {
            users.update(inputter.inputUser(true, users, kols));
            System.out.println("1. Continue update guest");
            System.out.println("2. Return to the main menu");
            System.out.println("Enter your option: ");
            option = Integer.parseInt(scanner.nextLine());
        } while (option != RETURN_MAIN__MENU);
    }

    private static void handleDisplayUser() {
        if(users==null||users.isEmpty()){
            System.err.println("No Kols have registered yet");
        }else{
            users.showAll(kols);
        }
    }

    public static void main(String[] args) {
        initializeSystem();
        runMainMenu();
    }

}
