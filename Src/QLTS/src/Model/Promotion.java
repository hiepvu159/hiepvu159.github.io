/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hiep Vu
 */
public class Promotion {
    private String NamePromo,StartPromo,EndPromo,Description;
    private int IDPromo,DiscountPromo;

    public String getNamePromo() {
        return NamePromo;
    }

    public void setNamePromo(String NamePromo) {
        this.NamePromo = NamePromo;
    }

    public String getStartPromo() {
        return StartPromo;
    }

    public void setStartPromo(String StartPromo) {
        this.StartPromo = StartPromo;
    }

    public String getEndPromo() {
        return EndPromo;
    }

    public void setEndPromo(String EndPromo) {
        this.EndPromo = EndPromo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getIDPromo() {
        return IDPromo;
    }

    public void setIDPromo(int IDPromo) {
        this.IDPromo = IDPromo;
    }

    public int getDiscountPromo() {
        return DiscountPromo;
    }

    public void setDiscountPromo(int DiscountPromo) {
        this.DiscountPromo = DiscountPromo;
    }

    public Promotion(String NamePromo, String StartPromo, String EndPromo, String Description, int IDPromo, int DiscountPromo) {
        this.NamePromo = NamePromo;
        this.StartPromo = StartPromo;
        this.EndPromo = EndPromo;
        this.Description = Description;
        this.IDPromo = IDPromo;
        this.DiscountPromo = DiscountPromo;
    }

    public Promotion() {
    }
}
