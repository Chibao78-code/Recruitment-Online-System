/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;
import business.Users;
import business.KOLs;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import models.User;
import models.KOL;
/**
 *
 * @author zzzdi
 */
public class Inputter {
    private Scanner scanner;

    public Inputter() {
        scanner = new Scanner(System.in);
    }

    public String input(String message, String errorMsg, String regex) {
        String result = "";
        boolean reInputter = false;
        do {
            System.out.println(message);
            result = scanner.nextLine();
            reInputter = !Acceptable.isValid(result, regex);
            if (reInputter) {
                System.out.println(errorMsg + ". Plese re-enter ... ");
            }
        } while (reInputter);
        return result;
    }
        public User inputUser(boolean isUpdate, Users users, KOLs kols) {
        User uTemp = null;

        String id = "";
        String message = isUpdate ? "Input kol id" : "Input kol id(id must start with(BT,FS,BC,GM,TL) adn 6 numberics after):";
        String errorMsg = "Id must start with(BT,FS,BC,GM,TL) adn 6 numberics after";
        String regex = Acceptable.idRegex;
        id = input(message, errorMsg, regex);
        if (isUpdate) {
            uTemp = users.searchById(id);
            if (uTemp == null) {
                System.err.println("This KOL has not registered yet");
                return uTemp;
            }
        }
                String name = "";
        while (true) {
            message = isUpdate ? "Input name(keep old information: " + uTemp.getName() + ")" : "Input name(name must be contain from 5 to 30 characteric):";
            errorMsg = "Name must be contain from 5 to 30 characteric";
            regex = Acceptable.anything;
            name = input(message, errorMsg, regex);
            if (isUpdate && name.isEmpty()) {
                name = uTemp.getName();
                break;
            }
            if (name.matches(Acceptable.nameRegex)) {
                break;
            } else {
                System.err.println(errorMsg);
            }
        }

        String phone = "";
        while (true) {
            message = isUpdate ? "Input phone(keep old information: " + uTemp.getPhoneNumber() + ")" : "Input phone(phone must be 10 numberics and valid in vietnamese):";
            errorMsg = "Phone must be 10 numberics and valid in vietnamese";
            regex = Acceptable.anything;
            phone = input(message, errorMsg, regex);
            if (isUpdate && phone.isEmpty()) {
                phone = uTemp.getPhoneNumber();
                break;
            }
            if (phone.matches(Acceptable.phoneRegex)) {
                break;
            } else {
                System.err.println(errorMsg);
            }
        }
                String email = "";
        while (true) {
            message = isUpdate ? "Input email(keep old information: " + uTemp.getEmail() + ")" : "Input email(email must be follow example@gmail.com):";
            errorMsg = "Email must be follow example@gmail.com";
            regex = Acceptable.anything;
            email = input(message, errorMsg, regex);
            if (isUpdate && email.isEmpty()) {
                email = uTemp.getEmail();
                break;
            }
            if (email.matches(Acceptable.emailRegex)) {
                break;
            } else {
                System.err.println(errorMsg);
            }
        }

        String platFormCode = "";
        while (true) {
            message = isUpdate ? "Input platform code(keep old information: " + uTemp.getPlatformCode() + ")" : "Input platform code(code must be (e.g., TK01,FB01,IG01,YT01)):";
            errorMsg = "Code must be (e.g., TK01,FB01,IG01,YT01)";
            regex = Acceptable.anything;
            platFormCode = input(message, errorMsg, regex);
            if (isUpdate && platFormCode.isEmpty()) {
                platFormCode = uTemp.getPlatformCode();
                break;
            }
            if (kols.searchById(platFormCode) != null) {
                break;
            } else {
                System.err.println("Your input platform code doesn't exits in list KOL");
            }
        }
                int count = 0;
        while (true) {
            try {
                message = isUpdate ? "Input count(keep old information: " + uTemp.getFollowerCount() + "):" : "Input count(count must be positive integer):";
                errorMsg = "Code must geater than zero!";
                regex = Acceptable.anything;
                String inputCount = input(message, errorMsg, regex);
                if (isUpdate && inputCount.isEmpty()) {
                    count = uTemp.getFollowerCount();
                    break;
                }
                count = Integer.parseInt(inputCount);
                if (count > 0) {
                    break;
                } else {
                    System.err.println(errorMsg);
                }
            } catch (Exception e) {
                System.err.println("Rental day must be a numberic!");
            }
        }

        int rate = count > 1000000 ? 25 : 20;

        User user = new User(id, name, phone, email, platFormCode, count, rate);

        return user;
    }
}
