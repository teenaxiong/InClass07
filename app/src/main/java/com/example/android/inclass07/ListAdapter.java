package com.example.android.inclass07;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter<Expense> {
    Context context;
    public ListAdapter(Context context, int resource, ArrayList<Expense> objects) {
        super(context, resource, objects);
        this.context = context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Expense expense = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listlayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = convertView.findViewById(R.id.nameLabel);
            viewHolder.money = convertView.findViewById(R.id.moneyLabel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(expense.getName());
        viewHolder.money.setText(context.getResources().getString(R.string.dollarSign) + String.valueOf(expense.getAmount()));

        return convertView;
    }

    private static class ViewHolder {
        TextView name, money;
    }

    public void NotifyDataSetChanged() {
        notifyDataSetChanged();
    }
}
