package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bongoacademy.digitalmoneybag.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DatabaseHelper dbHelper;

    public static boolean EXPENSE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        binding.tvAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddData.EXPENSE = true;
                startActivity(new Intent(MainActivity.this, AddData.class));

            }
        });


        binding.tvAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData.EXPENSE = false;
                startActivity(new Intent(MainActivity.this, AddData.class));

            }
        });

        binding.tvShowAllDataIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData.EXPENSE = false;
                startActivity(new Intent(MainActivity.this, ShowData.class));

            }
        });

        binding.tvShowAllDataExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData.EXPENSE = true;
                startActivity(new Intent(MainActivity.this, ShowData.class));

            }
        });


        upDateUI();

    }


    public void upDateUI() {
        binding.tvTotalExpense.setText("BDT " + dbHelper.calculateTotalExpense() );
        binding.tvTotalIncome.setText("BDT " + dbHelper.calculateTotalIncome());

        double balance = dbHelper.calculateTotalIncome() - dbHelper.calculateTotalExpense();

        binding.tvFinalBalance.setText("BDT " + balance);

    }


    //===================================================================

    @Override
    protected void onPostResume() {
        super.onPostResume();
        upDateUI();
    }


    //==================================
}