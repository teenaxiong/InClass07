package com.example.android.inclass07;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowExpenses extends Fragment {


    public ShowExpenses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Show Expense");
        return inflater.inflate(R.layout.fragment_show_expenses, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        String name = getArguments().getString(MainActivity.NAME);
        String category = getArguments().getString(MainActivity.CATEGORY);
        String amount = getArguments().getString(MainActivity.AMOUNT);
        String date = getArguments().getString(MainActivity.DATE);
        TextView nameText = getActivity().findViewById(R.id.showName);
        nameText.setText(name);

        TextView categoryText = getActivity().findViewById(R.id.showCategory);
        categoryText.setText(category);

        TextView amountText = getActivity().findViewById(R.id.showAmount);
        amountText.append(amount);

        TextView dateText = getActivity().findViewById(R.id.showDate);
        dateText.setText(date);

        getActivity().findViewById(R.id.buttonClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ExpenseApp(), "ExpenseApp").commit();
            }
        });
        super.onActivityCreated(savedInstanceState);
    }
}
