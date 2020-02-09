package com.example.snapfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snapfood.R;
import com.example.snapfood.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    List<Recipe> recipes;
    Context context;
    public RecipeAdapter(List<Recipe> recipes, Context context){

        this.context=context;
        this.recipes=recipes;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recipe_view, parent, false);

        // Return a new holder instance
        RecipeAdapter.ViewHolder viewHolder = new RecipeAdapter.ViewHolder(contactView);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        StringBuilder hasing= new StringBuilder();
        if (recipe != null && recipe.getHasIngredients() != null) {
            for (int i = 0; i < recipe.getHasIngredients().size();i++){
                hasing.append(recipe.getHasIngredients().get(i)+ ",  ");
        }
    }
        StringBuilder needing = new StringBuilder();
        if (recipe != null && recipe.getNeedIngredients() != null) {
            for (int i = 0; i < recipe.getNeedIngredients().size();i++){
                needing.append(recipe.getNeedIngredients().get(i)+ ",  ");
            }
        }
        holder.needtxt.setText(needing.toString());
        holder.havetext.setText(hasing.toString());
        holder.title.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView havetext, needtxt,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            havetext = itemView.findViewById(R.id.youhavetext);
            needtxt = itemView.findViewById(R.id.youneedtxt);
            title = itemView.findViewById(R.id.title);
        }
    }
}
