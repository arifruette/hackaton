package com.example.hackaton.feature.adapters;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackaton.R;
import com.example.hackaton.model.Banner;
import com.example.hackaton.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter  extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{
    private List<Order> localDataSet;
    public ArrayList<Order> korza = new ArrayList<>();
    int pointer = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getName() {
            return name;
        }

        public TextView getCol() {
            return days;
        }

        public TextView getSum() {
            return sum;
        }

        private final TextView name;
        private final TextView days;
        private final TextView sum;
        private final AppCompatButton button;

        public AppCompatButton getButton() {
            return button;
        }

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            name = (TextView) view.findViewById(R.id.namespace);
            days = (TextView) view.findViewById(R.id.colvo);
            sum = (TextView) view.findViewById(R.id.price);
            button = (AppCompatButton) view.findViewById(R.id.btnn);

        }


    }


    public CatalogAdapter(List<Order> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardforcat, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Order b = localDataSet.get(position);
        if(b.getName().length() > 30) {
            viewHolder.getName().setText(b.getName().substring(0, 28) + "...");
            viewHolder.getCol().setText(b.getTime_result());
            viewHolder.getSum().setText(b.getPrice() + "₽");
        } else {
            viewHolder.getName().setText(b.getName());
            viewHolder.getCol().setText(b.getTime_result());
            viewHolder.getSum().setText(b.getPrice() + "₽");
        }
        viewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pointer == 0) {
                    pointer = 1;
                    korza.add(b);
                    viewHolder.getButton().setBackgroundResource(R.drawable.delforcat);
                    viewHolder.getButton().setTextColor(Color.BLUE);
                    viewHolder.getButton().setText("Убрать");
                } else {
                    pointer = 0;
                    korza.remove(b);
                    viewHolder.getButton().setBackgroundResource(R.drawable.btn);
                    viewHolder.getButton().setTextColor(Color.WHITE);
                    viewHolder.getButton().setText("Добавить");
                }
            }
        });

    }

    public void setLocalDataSet(List<Order> lst){
        localDataSet = lst;
        notifyDataSetChanged();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
