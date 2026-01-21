package com.katza.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentFilterActivity extends AppCompatActivity {
    Button type1, type2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_filter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        type1=findViewById(R.id.btnTypeA);
        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.ACTION_OPEN_SCREEN_TYPE_A");
                startActivity(intent);
            }
        });
        type2 = findViewById(R.id.btnTypeB);
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.ACTION_OPEN_SCREEN_TYPE_B");
                startActivity(intent);
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
        }else if(id==R.id.action_intentFilterActivity){
            Intent intent = new Intent(this,IntentFilterActivity.class);
            startActivity(intent);
        }
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}