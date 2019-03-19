package com.example.android.inclass07;
/*
Teena Xiong
In Class 07
 */
import android.app.Fragment;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpenseApp.OnFragmentInteractionListener, AddExpense.OnFragmentInteractionListener {

    ArrayList<Expense> array;
    static String NAME = "NAME";
    static String CATEGORY = "CATEGORY";
    static String DATE = "DATE";
    static String AMOUNT = "AMOUNT";
    ListAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        getSupportFragmentManager().beginTransaction().add(R.id.container, new ExpenseApp(), "ExpenseApp").commit();
    }

    @Override
    public void checkArrayList() {
        if(array.isEmpty() || array.size()<0){
            findViewById(R.id.emptyArrayMessage).setVisibility(View.VISIBLE);
        }else{
             listView = findViewById(R.id.listView);
             adapter = new ListAdapter(this, R.layout.listlayout, array);
            listView.setAdapter(adapter);}
        }

    @Override
    public void showExpenses(int i) {
        ShowExpenses showExpenses = new ShowExpenses ();
        Bundle args = new Bundle();
        args.putString(NAME, array.get(i).getName());
        args.putString(CATEGORY, array.get(i).getCategory());
        args.putString(DATE, array.get(i).getDate());
        args.putString(AMOUNT, String.valueOf(array.get(i).getAmount()));
        showExpenses.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, showExpenses, "ShowExpenses")
                .addToBackStack("ExpenseApp").commit();
    }

    @Override
    public void removeExpense(int i) {
        array.remove(i);
        adapter.NotifyDataSetChanged();

    }

    @Override
    public void addExpenseToArray(Expense expense) {
        array.add(expense);
        getSupportFragmentManager().getBackStackEntryCount();
        getSupportFragmentManager().popBackStack("ExpenseApp",  FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
