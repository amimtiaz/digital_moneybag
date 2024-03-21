package com.bongoacademy.digitalmoneybag;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bongoacademy.digitalmoneybag.databinding.ActivityAddDataBinding;
import com.bongoacademy.digitalmoneybag.databinding.ActivityMainBinding;

public class AddData extends AppCompatActivity {

    private ActivityAddDataBinding binding;
    DatabaseHelper dbHelper;

    public static boolean EXPENSE = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        if (EXPENSE == true) binding.tvTile.setText("Add Expense");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  sAmount = binding.edAmount.getText().toString();
                String reason = binding.edReason.getText().toString();
                double amount = Double.parseDouble(sAmount);

                if (EXPENSE == true) {
                    dbHelper.addExpense(amount, reason );
                    binding.tvTile.setText("Expense Added!");
                } else {
                    dbHelper.addIncome(amount, reason );
                    binding.tvTile.setText("Income Added!");
                }

                binding.tvTile.setText("Inserted!!");

            }
        });

    }

    //==================================
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //==================================


    //==================================



}