package com.katza.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicActivity extends AppCompatActivity {
    SharedPreferences sp;
    Button btnSave;
    EditText etFname,etLname;
    TextView tvDisplay;
    ImageView iv;
    LinearLayout main;
    LinearLayout linearLayoutHorizontal;
    LinearLayout linearLayoutVertical;
    HorizontalScrollView HorizontalScrollView;
    ScrollView ScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.l1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        main = findViewById(R.id.l1);
        main.setOrientation(LinearLayout.VERTICAL);

        HorizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        HorizontalScrollView.setLayoutParams(hLayoutParams);

        linearLayoutHorizontal = new LinearLayout(this);
        linearLayoutHorizontal.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutHorizontal.setLayoutParams(hLayoutParams);
        loadPicsX();

        ScrollView = new ScrollView(this);
        LinearLayout.LayoutParams vLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        ScrollView.setLayoutParams(vLayoutParams);
        ScrollView.setFillViewport(true);

        linearLayoutVertical = new LinearLayout(this);
        linearLayoutVertical.setOrientation(LinearLayout.VERTICAL);
        linearLayoutVertical.setLayoutParams(vLayoutParams);
        loadPicsY();
    }

    public void loadPicsX(){
        for (int i = 0; i<100; i++){
            iv = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,500);
            layoutParams.setMargins(300,5,0,0);
            iv.setLayoutParams(layoutParams);
            int imageKey = getResources().getIdentifier("img"+i%4,"drawable",getPackageName());
            iv.setImageResource(imageKey);
            linearLayoutHorizontal.addView(iv);
        }
        HorizontalScrollView.addView(linearLayoutHorizontal);
        main.addView(HorizontalScrollView);

    }
    public void loadPicsY(){
        for (int i = 0; i<100; i++){
            iv = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,500);
            layoutParams.setMargins(300,5,0,0);
            iv.setLayoutParams(layoutParams);
            int imageKey = getResources().getIdentifier("img"+i%4,"drawable",getPackageName());
            iv.setImageResource(imageKey);
            linearLayoutVertical.addView(iv);
        }
        ScrollView.addView(linearLayoutVertical);
        main.addView(ScrollView);

    }
}