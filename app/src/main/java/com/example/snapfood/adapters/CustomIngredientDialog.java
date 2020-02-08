package com.example.snapfood.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snapfood.R;

import java.util.List;

public class CustomIngredientDialog extends Dialog {
    Context context;
    List<String> ingredients;
    Button yesButton, noButton;
    public CustomIngredientDialog(@NonNull Context context, List<String> strings) {
        super(context);
        this.context=context;
        this.ingredients=strings;
    }

    public CustomIngredientDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    protected CustomIngredientDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diaglog_confirm_ingredient);
        RecyclerView recyclerView = findViewById(R.id.confirmIngredientRecyclerview);
        ConfirmIngredientAdapters confirmIngredientAdapters = new ConfirmIngredientAdapters(context,ingredients);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(confirmIngredientAdapters);
        confirmIngredientAdapters.notifyDataSetChanged();

        yesButton = findViewById(R.id.okbutton);
        noButton = findViewById(R.id.cancelButton);


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get all the recipe list and make the api call
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
