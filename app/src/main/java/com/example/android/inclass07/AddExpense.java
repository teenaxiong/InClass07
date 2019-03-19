package com.example.android.inclass07;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddExpense.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddExpense#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpense extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public AddExpense() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpense.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpense newInstance(String param1, String param2) {
        AddExpense fragment = new AddExpense();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        final Spinner spinner = getActivity().findViewById(R.id.spinner);
        getActivity().setTitle("Add Expense");
        List<String> category = new ArrayList<>();
        category.add("Groceries");
        category.add("Invoices");
        category.add("Transportation");
        category.add("Shopping");
        category.add("Rent");
        category.add("Trips");
        category.add("Utilities");
        category.add("Others");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.addExpense).setOnClickListener(new View.OnClickListener() {
            EditText name = getActivity().findViewById(R.id.userExpenseName);
            EditText money = getActivity().findViewById(R.id.userMoney);
            String mytime = (DateFormat.format("MM-dd-yyyy", new java.util.Date()).toString());

            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Sorry, Please enter a name", Toast.LENGTH_SHORT).show();
                } else if (money.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Sorry, Please enter an amount", Toast.LENGTH_SHORT).show();
                } else {
                    String moneyString = money.getText().toString().trim();
                    double myDouble = Double.valueOf(moneyString);
                    Expense expense = new Expense(name.getText().toString(), spinner.getSelectedItem().toString(), myDouble, mytime);

                    mListener.addExpenseToArray(expense);
                }
            }
        });

        getActivity().findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack("ExpenseApp", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void addExpenseToArray(Expense expense);
    }
}
