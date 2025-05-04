package com.multiwizards.roomdemo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Employee.class},version = 3,exportSchema = false)
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
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    static final Migration MIGRATION_1_2 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Adding the new column with a default value
//            database.execSQL("ALTER TABLE employee_data ADD COLUMN employee_age TEXT");
            database.execSQL("ALTER TABLE employee_data ADD COLUMN employee_fees TEXT DEFAULT 'SEX' ");
        }
    };

}
