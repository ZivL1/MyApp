package com.katza.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Calendar;


public class ActivitySecond extends AppCompatActivity {
    TextView tvMale, tvAge, tvName;
    EditText editYear;
    Button btn, sendYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
    }

    private void initViews() {
        tvMale = findViewById(R.id.tvMale);
        tvAge=findViewById(R.id.tvAge);
        tvName=findViewById(R.id.tvName);
        Intent intent = getIntent();
        if(intent.hasExtra("isMale")) {
            tvMale.append(" " + intent.getBooleanExtra("isMale", false));
        }
        if(intent.hasExtra("age")) {
            tvAge.append(" " + intent.getIntExtra("age", 0));
        }
        if(intent.hasExtra("name")) {
            tvName.append(intent.getStringExtra("name"));
        }

        btn = findViewById(R.id.returnToActivityFirst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editYear = findViewById(R.id.editYear);
        sendYear = findViewById(R.id.sendYear);
        sendYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearText = editYear.getText().toString();
                if(!yearText.isEmpty()){
                    int birthYear = Integer.parseInt(yearText);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int age = currentYear-birthYear;
                    Intent intent = new Intent();
                    intent.putExtra("age", age);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}