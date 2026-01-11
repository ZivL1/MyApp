package com.katza.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedPreferencesActivity extends AppCompatActivity {
    SharedPreferences sp;
    Button btnSave;
    EditText etFname,etLname;
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etFname=findViewById(R.id.etFname);
        etLname=findViewById(R.id.etLname);
        tvDisplay=findViewById(R.id.tvDisplay);
        btnSave = findViewById(R.id.btnSubmit);
        sp=getSharedPreferences("details1",0);
        String strfname = sp.getString("fName",null);
        String strlname = sp.getString("lName",null);
        if(strlname!=null&&strfname!=null){
            tvDisplay.setText("Welcome " + strfname + " " + strlname);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("fName",etFname.getText().toString());
                    editor.putString("lName",etLname.getText().toString());
                    editor.commit();

            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id==R.id.action_DialogActivity){
            Intent intent = new Intent(this, DialogActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_mainActivity){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_DynamicActivity){
            Intent intent = new Intent(this, DynamicActivity.class);
            startActivity(intent);
        }else if(id==R.id.action_SharedPreferencesActivity){
            Intent intent = new Intent(this, SharedPreferencesActivity.class);
            startActivity(intent);
        }
        finish();
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}