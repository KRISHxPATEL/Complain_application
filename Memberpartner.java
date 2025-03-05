package com.spp.shitalsurgicalco;

public class Memberpartner {
    String name , address , reg_name ,time, reg_phone , fulldisp , AMC_expireing , Reg_Date , Email , payment_id , payment_date , spare_part , noMachine , status;

    public Memberpartner() {
    }

    public Memberpartner(String name, String address,String status,String time, String reg_name,String noMachine, String reg_phone, String fulldisp, String AMC_expireing, String Reg_Date , String Email , String payment_id , String payment_date , String spare_part) {
        this.name = name;
        this.address = address;
        this.reg_name = reg_name;
        this.reg_phone = reg_phone;
        this.fulldisp = fulldisp;
        this.AMC_expireing = AMC_expireing;
        this.Reg_Date = Reg_Date;
        this.Email = Email;
        this.noMachine = noMachine;
        this.payment_date = payment_date;
        this.payment_id = payment_id;
        this.spare_part = spare_part;
        this.time = time;
        this.status = status;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public String getSpare_part() {
        return spare_part;
    }

    public void setSpare_part(String spare_part) {
        this.spare_part = spare_part;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getEmail() {
        return Email;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public String getReg_phone() {
        return reg_phone;
    }

    public void setReg_phone(String reg_phone) {
        this.reg_phone = reg_phone;
    }

    public String getFulldisp() {
        return fulldisp;
    }

    public void setFulldisp(String fulldisp) {
        this.fulldisp = fulldisp;
    }

    public String getAMC_expireing() {
        return AMC_expireing;
    }

    public void setAMC_expireing(String AMC_expireing) {
        this.AMC_expireing = AMC_expireing;
    }

    public String getReg_Date() {
        return Reg_Date;
    }

    public void setReg_Date(String Reg_Date) {
        this.Reg_Date = Reg_Date;
    }

    public String getNoMachine() {
        return noMachine;
    }

    public void setNoMachine(String noMachine) {
        this.noMachine = noMachine;
    }

}
