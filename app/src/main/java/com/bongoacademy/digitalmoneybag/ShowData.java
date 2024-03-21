package com.bongoacademy.digitalmoneybag;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bongoacademy.digitalmoneybag.databinding.ActivityShowDataBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowData extends AppCompatActivity {
    private ActivityShowDataBinding binding;

    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> arrayList;
    HashMap<String, String> hashMap;

    public static boolean EXPENSE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        if (EXPENSE == true) {
            binding.textView.setText("Showing All Expenses");
        } else {
            binding.textView.setText("Showing All Income");

        }

        loadData();


    }

    public void loadData() {

        Cursor cursor = null;

        if (EXPENSE == true) {
            cursor = dbHelper.getExpenseAllData();
        } else {
            cursor = dbHelper.getIncomeAllData();
        }


        if (cursor != null && cursor.getCount() > 0) {

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);

                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id", "" + id);
                hashMap.put("amount", "" + amount);
                hashMap.put("reason", "" + reason);
                hashMap.put("time", "" + time);
                arrayList.add(hashMap);

            }


            binding.listView.setAdapter(new MyAdapter());

        } else {
            binding.textView.append("\nNo Data Found");
        }

    }

    //==================================================

    public class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.item, parent, false);


            TextView tvReason, tvAmount, tvDelete;

            tvReason = myView.findViewById(R.id.tvReason);
            tvAmount = myView.findViewById(R.id.tvAmount);
            tvDelete = myView.findViewById(R.id.tvDelete);

            hashMap = arrayList.get(position);

            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String time = hashMap.get("time");
            String reason = hashMap.get("reason");

            tvAmount.setText("BD " + amount);
            tvReason.setText(reason);

            tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if (EXPENSE == true) {
                       dbHelper.deleteExpense(id);
                       loadData();
                   } else {
                       dbHelper.deleteIncome(id);
                       loadData();
                   }
                }
            });


            return myView;
        }
    }

}