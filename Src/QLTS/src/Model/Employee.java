/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Hiep Vu
 */
public class Employee {
    private String userNameEmp;
    private String passWord;
    private String nameEmp;
    private String gender;
    private String birthDay;
    private String phone;
    private String email;
    private String address;
    private String hinh;

    public Employee() {
    }
    

    public Employee(String userNameEmp, String passWord, String nameEmp, String gender, String birthDay, String phone, String email, String address, String hinh) {
        this.userNameEmp = userNameEmp;
        this.passWord = passWord;
        this.nameEmp = nameEmp;
        this.gender = gender;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.hinh = hinh;
    }

    
    public String getUserNameEmp() {
        return userNameEmp;
    }

    public void setUserNameEmp(String userNameEmp) {
        this.userNameEmp = userNameEmp;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
    
}