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
public class Revenue {
    private String Date,Money;
    private int IDRevenue;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public int getIDRevenue() {
        return IDRevenue;
    }

    public void setIDRevenue(int IDRevenue) {
        this.IDRevenue = IDRevenue;
    }

    public Revenue(String Date, String Money, int IDRevenue) {
        this.Date = Date;
        this.Money = Money;
        this.IDRevenue = IDRevenue;
    }

    public Revenue() {
    }
}
