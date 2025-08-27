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
import models.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.KOL;
/**
 *
 * @author zzzdi
 */
public class Users extends HashSet<User> implements Workable<User>, Serializable {

    private String pathFile;
    private boolean saved;

    public Users(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public boolean isDuplicaition(User g) {
        return this.contains(g);
    }

    @Override
    public void addNew(User t) {
        if (isDuplicaition(t)) {
            System.err.println("Dupplicate Kol id");
        } else {
            this.add(t);
            this.saved = false;
        }
    }

    @Override
    public void update(User t) {
        this.remove(t);
        this.add(t);
        this.saved = false;
    }

    @Override
    public User searchById(String id) {
        for (User c : this) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    } 
}
