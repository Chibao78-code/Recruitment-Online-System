/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tools;

/**
 *
 * @author zzzdi
 */
public interface Acceptable {
     public static String idRegex = "^(BT|bt|FS|fs|BC|bc|GM|gm|TL|tl)\\d{6}$";
    public static String nameRegex = "^.{5,30}$";
    public static String phoneRegex = "^0(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])\\d{7}$";
    public static String emailRegex = "^[a-zA-Z0-9_+.-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
    public static String anything = ".*";

    public static boolean isValid(String data, String regex) {
        return data.matches(regex);
    }
}
