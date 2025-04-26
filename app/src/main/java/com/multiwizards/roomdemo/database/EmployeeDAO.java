package com.multiwizards.roomdemo.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Insert
    long insert_data( Employee employee);

    @Query("SELECT * FROM employee_data")
    List<Employee> get_employeeData();

    @Query("SELECT * FROM employee_data WHERE employee_id = :id")
    Employee getById(int id);

    @Delete
    void delete_employee_data(Employee employee);

    @Query("DELETE FROM employee_data WHERE employee_id = :employeeId")
    void deleteEmployeeById(int employeeId);

    @Update
    void update(Employee employee);

}
