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
public class ProductType {
    private String IDType,TypeName,Size;

    public ProductType() {
    }

    public ProductType(String IDType, String TypeName, String Size) {
        this.IDType = IDType;
        this.TypeName = TypeName;
        this.Size = Size;
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }
    
}
