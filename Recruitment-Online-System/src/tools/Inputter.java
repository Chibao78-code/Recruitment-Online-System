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
}
