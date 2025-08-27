/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import java.io.BufferedReader;
import models.KOL;
import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.User;
/**
 *
 * @author zzzdi
 */
public class KOLs extends HashMap<String, KOL> {

    private String pathFile;
    private boolean saved;

    public KOLs(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
        readFormFile();
    }

    public KOL dataToObject(String s) {
        KOL kol = null;
        try {
            String[] parts = s.split(",");
            if (parts[0] != null && !parts[0].equals("Code")) {
                kol = new KOL();
                kol.setCode(parts[0].trim());
                kol.setPlatform(parts[1].trim());
                kol.setDescription(parts[2].trim());
            }
        } catch (Exception e) {
            kol = null;
        }
        return kol;
    }

    public void readFormFile() {
        try {
            File f = new File(pathFile);
            if (!f.exists()) {
                System.out.println("File not found: " + pathFile);
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s = "";
            while ((s = br.readLine()) != null) {
                KOL kol = dataToObject(s);
                if (kol != null) {
                    this.put(kol.getCode(), kol);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public KOL searchById(String code) {
        if (code == null) {
            return null;
        }
        code = code.trim();
        for (String s : this.keySet()) {
            if (s.trim().equalsIgnoreCase(code)) {
                return this.get(s);
            }
        }
        System.err.println("DEBUG: Not found, your input = [" + code + "], keys = " + this.keySet());
        return null;
    }
}
