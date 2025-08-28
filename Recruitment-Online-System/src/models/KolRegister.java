/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import business.KolPlatForms;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author zzzdi
 */
public class KolRegister implements Serializable {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String platformCode;
    private int followerCount;
    private int commissionRate;

    public KolRegister() {
    }

    public KolRegister(String id, String name, String phoneNumber, String email, String platformCode, int followerCount, int commissionRate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.platformCode = platformCode;
        this.followerCount = followerCount;
        this.commissionRate = commissionRate;
    }

    public String getId() {
        String kolCode = this.id.substring(0, 2).toUpperCase() + this.id.substring(2);
        return kolCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(int commissionRate) {
        this.commissionRate = commissionRate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", platformCode=" + platformCode + ", followerCount=" + followerCount + ", rate=" + commissionRate + '}';
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KolRegister other = (KolRegister) obj;
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }
        return true;
    }

    public void display(KolPlatForms kols) {
        System.out.println("KOL Details:");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-12s: %s%n", "KOL ID", this.getId());
        System.out.printf("%-12s: %s%n", "Name", formatString(this.getName()));
        System.out.printf("%-12s: %s%n", "Phone", this.getPhoneNumber());
        KolPlatForm kol = kols.searchById(this.getPlatformCode());
        System.out.printf("%-12s: %s%n", "Platform", kol.getPlatform());
        System.out.printf("%-12s: %,d%n", "Followers", this.getFollowerCount());
        System.out.printf("%-12s: %s%%%n", "Commission", this.getFollowerCount());
        System.out.println("-------------------------------------------------------------------------------");
    }

    public String formatString(String s) {
        String[] parts = s.split("\\s");
        String name = "";
        for (String part : parts) {
            name += part.substring(0, 1).toUpperCase() + part.substring(1).toLowerCase()+" ";
        }
        return name.trim();
    }

}
