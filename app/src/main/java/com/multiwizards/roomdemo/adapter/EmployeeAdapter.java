package com.multiwizards.roomdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.multiwizards.roomdemo.R;
import com.multiwizards.roomdemo.database.Employee;
import com.multiwizards.roomdemo.database.EmployeeDatabase;
import com.multiwizards.roomdemo.databinding.ActivityMainBinding;
import com.multiwizards.roomdemo.databinding.EmployeeSingleBinding;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.employeeViewHolder> {

    Context context;
    List<Employee> data;
    EmployeeDatabase employeeDatabase;
    ActivityMainBinding mainBinding;
    int employee_id =0;
    public EmployeeAdapter(Context context, List<Employee> data, EmployeeDatabase employeeDatabase,ActivityMainBinding mainBinding) {
        this.context = context;
        this.data = data;
        this.employeeDatabase = employeeDatabase;
        this.mainBinding = mainBinding;
    }

    @NonNull
    @Override
    public employeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use binding to inflate the layout
        EmployeeSingleBinding binding = EmployeeSingleBinding.inflate(LayoutInflater.from(context), parent, false);
        return new employeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull employeeViewHolder holder, int position) {
        Employee employee = data.get(position);

        // Bind data using binding
        holder.binding.tvName.setText(employee.employee_name);
        holder.binding.tvDepartment.setText(employee.employee_department);
//        holder.binding.tvEmployeeSalary.setText(String.valueOf(employee.salary));
//        holder.binding.tvEmployeePosition.setText(employee.position);
        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeDatabase.employeeDAO().deleteEmployeeById(employee.employee_id);
                updateData(employeeDatabase.employeeDAO().get_employeeData());
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
            }
        });
        holder.binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employee_id = employee.employee_id;
                mainBinding.editName.setText(employee.employee_name);
                mainBinding.editDepartment.setText(employee.employee_department);
                mainBinding.editPhone.setText(employee.employee_phone_no);
                mainBinding.editAddress.setText(employee.employee_address);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void updateData(List<Employee> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }
    public void update_employeeData(String name, String department, String phoneno, String address) {
        if (employee_id!=0){
            Employee newdata= new Employee(employee_id,name,department,phoneno,"viswajeetbharti@gmail.com",address);
            employeeDatabase.employeeDAO().update(newdata);
            employee_id=0;
            mainBinding.editName.setText("");
            mainBinding.editDepartment.setText("");
            mainBinding.editPhone.setText("");
            mainBinding.editAddress.setText("");
            updateData(employeeDatabase.employeeDAO().get_employeeData());
        }else {
            Toast.makeText(context, "not able to update data", Toast.LENGTH_SHORT).show();
        }
    }

    public static class employeeViewHolder extends RecyclerView.ViewHolder {
        EmployeeSingleBinding binding;
        public employeeViewHolder(@NonNull  EmployeeSingleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}


