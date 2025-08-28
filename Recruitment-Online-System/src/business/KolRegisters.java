/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import tools.Inputter;
import models.KolRegister;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.KolPlatForm;
/**
 *
 * @author zzzdi
 */
public class KolRegisters extends HashSet<KolRegister> implements Workable<KolRegister>, Serializable {

    private String pathFile;
    private boolean saved;

    public KolRegisters(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isDuplicaition(KolRegister t) {
        return this.contains(t);
    }

    @Override
    public void addNew(KolRegister t) {
        if (isDuplicaition(t)) {
            System.err.println("Dupplicate Kol id");
        } else {
            this.add(t);
            this.saved = false;
        }
    }

    @Override
    public void update(KolRegister t) {
        this.remove(t);
        this.add(t);
        this.saved = false;
    }

    @Override
    public KolRegister searchById(String id) {
        for (KolRegister r : this) {
            if (r.getId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public void delete(KolRegister t) {
        this.remove(t);
        this.saved = false;
    }

    public void showAll(List<KolRegister> register, KolPlatForms platForms) {

        if (register == null || register.isEmpty()) {
            register = new ArrayList<>(this);
        }
        
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-20s | %-11s | %-10s | %-12s | %-10s%n",
                "KOL ID", "Name", "Phone", "Platform", "Followers", "Commission");
        System.out.println("-------------------------------------------------------------------------------");
        for (KolRegister r : register) {
            KolPlatForm platForm = platForms.searchById(r.getPlatformCode());
            NumberFormat nf = new DecimalFormat("#,###");
            System.out.printf("%-10s | 1.%-20s | %-11s | %-10s | %-12s | %-10s%n",
                    
                    r.getId(),
                    r.formatString(r.getName()),
                    r.getPhoneNumber(),
                    platForm.getPlatform(),
                    nf.format(r.getFollowerCount()),
                    r.getCommissionRate() + "%");
                    System.out.println("1");

        }
        System.out.println("-------------------------------------------------------------------------------");
    }

    public List<KolRegister> filterByName(String s) {
        List<KolRegister> list = new ArrayList<>();
        for (KolRegister r : this) {
            if (r.getName().toLowerCase().contains(s.toLowerCase())) {
                list.add(r);
            }
        }
        return list;
    }

    public List<KolRegister> filterByCategoty(String s) {
        List<KolRegister> list = new ArrayList<>();
        for (KolRegister r : this) {
            if (r.getId().substring(0, 2).toLowerCase().contains(s.toLowerCase())) {
                list.add(r);
            }
        }
        return list;
    }

    public List<KolRegister> getAll() {
        return new ArrayList<>(this);
    }

    public boolean toSave() {
        return this.saved;
    }

    public void saveToFile() {
        if (toSave()) {
            System.out.println("Register KOL has saved yet");
            return;
        }
        try {
            File f = new File(pathFile);
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            this.saved = true;
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
