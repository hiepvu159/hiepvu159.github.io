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
public class Order {
    private String IDOrder,DateOrder,TimeOrder,UsernameEmp;

    public Order() {
    }

    public Order(String IDOrder, String DateOrder, String TimeOrder, String UsernameEmp) {
        this.IDOrder = IDOrder;
        this.DateOrder = DateOrder;
        this.TimeOrder = TimeOrder;
        this.UsernameEmp = UsernameEmp;
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public String getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(String DateOrder) {
        this.DateOrder = DateOrder;
    }

    public String getTimeOrder() {
        return TimeOrder;
    }

    public void setTimeOrder(String TimeOrder) {
        this.TimeOrder = TimeOrder;
    }

    public String getUsernameEmp() {
        return UsernameEmp;
    }

    public void setUsernameEmp(String UsernameEmp) {
        this.UsernameEmp = UsernameEmp;
    }
}
