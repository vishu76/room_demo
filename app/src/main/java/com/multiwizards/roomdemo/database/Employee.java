package com.multiwizards.roomdemo.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_data")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public int employee_id;
    public String employee_name;
    public String employee_department;
    public String employee_phone_no;
    public String employee_email;
    public String employee_address;

    public Employee(String employee_name, String employee_department, String employee_phone_no, String employee_email, String employee_address) {
        this.employee_name = employee_name;
        this.employee_department = employee_department;
        this.employee_phone_no = employee_phone_no;
        this.employee_email = employee_email;
        this.employee_address = employee_address;
    }
    @Ignore
    public Employee(int employee_id, String employee_name, String employee_department, String employee_phone_no, String employee_email, String employee_address) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_department = employee_department;
        this.employee_phone_no = employee_phone_no;
        this.employee_email = employee_email;
        this.employee_address = employee_address;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_department() {
        return employee_department;
    }

    public void setEmployee_department(String employee_department) {
        this.employee_department = employee_department;
    }

    public String getEmployee_phone_no() {
        return employee_phone_no;
    }

    public void setEmployee_phone_no(String employee_phone_no) {
        this.employee_phone_no = employee_phone_no;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_address() {
        return employee_address;
    }

    public void setEmployee_address(String employee_address) {
        this.employee_address = employee_address;
    }
}
