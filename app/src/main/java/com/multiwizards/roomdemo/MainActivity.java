package com.multiwizards.roomdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.multiwizards.roomdemo.adapter.EmployeeAdapter;
import com.multiwizards.roomdemo.database.Employee;
import com.multiwizards.roomdemo.database.EmployeeDAO;
import com.multiwizards.roomdemo.database.EmployeeDatabase;
import com.multiwizards.roomdemo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    EmployeeDatabase employeeDatabase;
    EmployeeAdapter employeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView (mainBinding.getRoot());
        employeeDatabase= EmployeeDatabase.getINSTANCE(this);
        setupRecyclerView();
        mainBinding.btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Employee employee = new Employee(
                        mainBinding.editName.getText().toString(), mainBinding.editDepartment.getText().toString() ,  mainBinding.editPhone.getText().toString(),
                        "vishwjeetbharti@gmail.com",  mainBinding.editAddress.getText().toString(),mainBinding.editAge.getText().toString());
                new Thread(() -> {
                    long id=  employeeDatabase.employeeDAO().insert_data(employee);
                    String insertMessage;
                    if (id > 0) {
                        insertMessage = "Employee inserted successfully with ID: " + id;
                    } else {
                        insertMessage = "Failed to insert employee!";
                    }
                    Log.d("InsertMessage", insertMessage);
                    List<Employee> data = employeeDatabase.employeeDAO().get_employeeData();
                    runOnUiThread(()->{
                        mainBinding.editName.setText("");
                        mainBinding.editDepartment.setText("");
                        mainBinding.editPhone.setText("");
                        mainBinding.editAddress.setText("");
                        Toast.makeText(MainActivity.this, insertMessage, Toast.LENGTH_SHORT).show();
                        employeeAdapter.updateData(data);
                    });
                }).start();
            }
        });
        mainBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeAdapter.update_employeeData(mainBinding.editName.getText().toString(), mainBinding.editDepartment.getText().toString() ,  mainBinding.editPhone.getText().toString()
                         ,mainBinding.editAddress.getText().toString(),mainBinding.editAge.getText().toString());
            }
        });
    }
    private void setupRecyclerView() {
        mainBinding.rvEmployee.setLayoutManager(new LinearLayoutManager(this));
        employeeAdapter = new EmployeeAdapter(this, employeeDatabase.employeeDAO().get_employeeData(),employeeDatabase,mainBinding);
        mainBinding.rvEmployee.setAdapter(employeeAdapter);
    }
}