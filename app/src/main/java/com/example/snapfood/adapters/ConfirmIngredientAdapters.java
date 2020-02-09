package com.example.snapfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snapfood.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConfirmIngredientAdapters extends RecyclerView.Adapter<ConfirmIngredientAdapters.ViewHolder> {

    private List<String> ingredients;
    private HashSet<String> actualIngridients;
    private Context context;
    public ConfirmIngredientAdapters(Context context,List<String> ingredients){
        this.ingredients = ingredients;
        this.context=context;
        this.actualIngridients = new HashSet<>(ingredients);
    }

    @NonNull
    @Override
    public ConfirmIngredientAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.dialog_listview_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ConfirmIngredientAdapters.ViewHolder holder, int position) {
        holder.textView.setText(ingredients.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!holder.checkBox.isChecked()){
                    actualIngridients.remove(holder.textView.getText().toString());
                } else {
                    actualIngridients.add(holder.textView.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public HashSet<String> getMyData() {
        return actualIngridients;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.ingredCheckbox);
            textView = itemView.findViewById(R.id.ingredText);
        }
    }
}
