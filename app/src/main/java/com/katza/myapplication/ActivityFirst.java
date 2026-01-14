package com.katza.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityFirst extends AppCompatActivity {

    Button btn, editAge;
    EditText ETage, ETname;
    Switch Smale;
    TextView tvAge;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        int age = data.getIntExtra("age", 0);
                        tvAge.setText("גיל: " + age);
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvAge =findViewById(R.id.displayAge);
        btn = findViewById(R.id.submitForm);
        ETage = findViewById(R.id.ageInput);
        ETname = findViewById(R.id.nameInput);
        Smale = findViewById(R.id.maleInput);
        editAge = findViewById(R.id.editAge);
        editAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
                launcher.launch(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ETname.getText().toString();
                String ageString = ETage.getText().toString();


                int age = 0;
                if (!ageString.isEmpty()) {
                    age = Integer.parseInt(ageString);
                } else {
                    ETage.setError("Please enter a number");
                    return;
                }

                boolean male = Smale.isChecked();
                Intent intent = new Intent(ActivityFirst.this, ActivitySecond.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                intent.putExtra("isMale", male);
                launcher.launch(intent);
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
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}