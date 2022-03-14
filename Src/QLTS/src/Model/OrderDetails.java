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
public class OrderDetails {
    private String IDOrder,IDProduct,CusName,NamePromo;
    private int Quantity;

    public String getIDOrder() {
        return IDOrder;
    }

    public void setIDOrder(String IDOrder) {
        this.IDOrder = IDOrder;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public String getNamePromo() {
        return NamePromo;
    }

    public void setNamePromo(String NamePromo) {
        this.NamePromo = NamePromo;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public OrderDetails(String IDOrder, String IDProduct, String CusName, String NamePromo, int Quantity) {
        this.IDOrder = IDOrder;
        this.IDProduct = IDProduct;
        this.CusName = CusName;
        this.NamePromo = NamePromo;
        this.Quantity = Quantity;
    }

    public OrderDetails() {
    }
}
