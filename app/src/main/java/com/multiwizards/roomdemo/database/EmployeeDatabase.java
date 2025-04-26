package com.multiwizards.roomdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class},version = 1,exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {

    private static final String DATABASE_NAME= "employee_db";
    private static EmployeeDatabase INSTANCE;
    public abstract EmployeeDAO employeeDAO();

    public static synchronized EmployeeDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EmployeeDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
