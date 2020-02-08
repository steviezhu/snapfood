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

import java.util.List;

public class ConfirmIngredientAdapters extends RecyclerView.Adapter<ConfirmIngredientAdapters.ViewHolder> {

    private List<String> ingredients;
    private Context context;
    public ConfirmIngredientAdapters(Context context,List<String> ingredients){
        this.ingredients = ingredients;
        this.context=context;
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
    public void onBindViewHolder(@NonNull ConfirmIngredientAdapters.ViewHolder holder, int position) {
        holder.checkBox.setActivated(true);
        holder.textView.setText(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
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
