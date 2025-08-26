/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import business.KOLs;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author zzzdi
 */
public class User implements Serializable {

    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String platformCode;
    private int followerCount;
    private int rate;

    public User() {
    }

    public User(String id, String name, String phoneNumber, String email, String platformCode, int followerCount, int rate) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.platformCode = platformCode;
        this.followerCount = followerCount;
        this.rate = rate;
    }

    public String getId() {
        return id;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", platformCode=" + platformCode + ", followerCount=" + followerCount + ", rate=" + rate + '}';
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
        final User other = (User) obj;
        if (!this.id.equalsIgnoreCase(other.getId())) {
            return false;
        }
        return true;
    }

}

