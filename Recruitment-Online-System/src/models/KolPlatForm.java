/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.text.DecimalFormat;
import java.util.Objects;
/**
 *
 * @author zzzdi
 */
public class KolPlatForm {
    private String code;
    private String Platform;
    private String Description;

    public KolPlatForm() {
    }

    public KolPlatForm(String code, String Platform, String Description) {
        this.code = code;
        this.Platform = Platform;
        this.Description = Description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String Platform) {
        this.Platform = Platform;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "KOL{" + "code=" + code + ", Platform=" + Platform + ", Description=" + Description + '}';
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
        final KolPlatForm other = (KolPlatForm) obj;
        if (!this.code.equalsIgnoreCase(other.code)) {
            return false;
        }
        return true;
    }

    
}

