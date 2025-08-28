/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import java.io.BufferedReader;
import models.KolPlatForm;
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
import models.KolRegister;
/**
 *
 * @author zzzdi
 */
public class KolPlatForms extends HashMap<String, KolPlatForm> {

    private String pathFile;
    private boolean saved;

    public KolPlatForms(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
        readFormFile();
    }

    public KolPlatForm dataToObject(String s) {
        KolPlatForm platForm = null;
        try {
            String[] parts = s.split(",");
            if (parts[0] != null && !parts[0].equals("Code")) {
                platForm = new KolPlatForm();
                platForm.setCode(parts[0].trim());
                platForm.setPlatform(parts[1].trim());
                platForm.setDescription(parts[2].trim());
            }
        } catch (Exception e) {
            platForm = null;
        }
        return platForm;
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
                KolPlatForm platForm = dataToObject(s);
                if (platForm != null) {
                    this.put(platForm.getCode(), platForm);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KolPlatForm searchById(String code) {
        if (code == null) {
            return null;
        }
        code = code.trim();
        for (String s : this.keySet()) {
            if (s.trim().equalsIgnoreCase(code)) {
                return this.get(s);
            }
        }
        return null;
    }

    public void showCategoryByPlatform(List<KolRegister> registers) {
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, Integer> commissionSumMap = new HashMap<>();

        for (KolRegister r : registers) {
            KolPlatForm platForm = searchById(r.getPlatformCode());
            if (platForm == null) {
                continue;
            }

            String platform = platForm.getPlatform() + " (" + platForm.getCode() + ")";

            countMap.put(platform, countMap.getOrDefault(platform, 0) + 1);

            commissionSumMap.put(platform, commissionSumMap.getOrDefault(platform, 0) + r.getCommissionRate());
        }

        System.out.println("Statistics of Registration by Platform:");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-20s | %-14s | %-20s%n", "Platform", "Number of KOLs", "Avg. Commission Rate");
        System.out.println("-------------------------------------------------------------");

        for (String platform : countMap.keySet()) {
            int numberOfKOLs = countMap.get(platform);
            double avgCommission = (double) commissionSumMap.get(platform) / numberOfKOLs;
            System.out.printf("%-20s | %-14d | %.1f%%%n", platform, numberOfKOLs, avgCommission);
        }

        System.out.println("-------------------------------------------------------------");
    }

}
