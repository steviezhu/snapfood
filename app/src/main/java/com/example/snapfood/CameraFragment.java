package com.example.snapfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.snapfood.caloriemama.FoodRecognitionException;
import com.example.snapfood.caloriemama.FoodRecognitionTask;
import com.example.snapfood.caloriemama.FoodServiceCallback;
import com.example.snapfood.caloriemama.FoodTask;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.controls.PictureFormat;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.cameraview.size.SizeSelector;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CameraFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static CameraFragment newInstance(int page, String title) {
        CameraFragment fragmentFirst = new CameraFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
       final CameraView cameraview = view.findViewById(R.id.camera);
        cameraview.setLifecycleOwner(getViewLifecycleOwner());

        cameraview.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(PictureResult result) {
                final ProgressDialog progressDialog = ProgressDialog.show(getActivity(),"Please wait...", "Recognizing food");
                progressDialog.setCancelable(true);

                // A Picture was taken!
                result.toBitmap(544, 544, new BitmapCallback() {
                    @Override
                    public void onBitmapReady(@Nullable Bitmap bitmap) {
                        FoodTask foodTask = new FoodTask( bitmap);
                       new FoodRecognitionTask(new FoodServiceCallback<JSONObject>() {
                           @Override
                           public void finishRecognition(JSONObject response, FoodRecognitionException exception) {
                               progressDialog.cancel();
                               if (exception != null) {
                                   // handle exception gracefully
                                   Log.d("bugg","exception on foodtask: "+ exception.getMessage());
                               } else {
                                   Intent intent = new Intent(getActivity(),IngredientActivity.class);
                                   intent.putExtra("json",response.toString());
                                   startActivity(intent);
                                   Log.d("bugg","response: "+ response.toString());
                               }
                           }
                       }).execute(foodTask);
                    }
                });

            }

            @Override
            public void onVideoTaken(VideoResult result) {
                // A Video was taken!
            }

            // And much more
        });
        Button camerTakeButton = view.findViewById(R.id.cameraButton);
        camerTakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraview.takePicture();
            }
        });

        cameraview.setPictureFormat(PictureFormat.JPEG);




        return view;
    }

}
