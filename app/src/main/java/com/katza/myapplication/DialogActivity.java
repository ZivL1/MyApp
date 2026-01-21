package com.katza.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogActivity extends AppCompatActivity {
    SharedPreferences sp;
    Dialog d;
    EditText etUserName, etPass;
    Button btnCustomLogin, btnLogin, btnShowSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dialog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLogin=findViewById(R.id.btnLogin);
        sp=getSharedPreferences("details1",0);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLoginDialog();
            }
        });
        btnShowSP = findViewById(R.id.btnShowSP);
        btnShowSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struname = sp.getString("userName",null);
                String strpass = sp.getString("password",null);
                if(struname!=null&&strpass!=null){
                    Toast.makeText(DialogActivity.this, "username: " + struname + " \npassword: " + strpass, Toast.LENGTH_SHORT).show();
                }
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
    public void createLoginDialog(){
        d=new Dialog(this);
        d.setContentView(R.layout.save_dialog);
        d.setTitle("login");
        d.setCancelable(false);
        etUserName=d.findViewById(R.id.etUserName);
        etPass=d.findViewById(R.id.etPassword);
        btnCustomLogin=d.findViewById(R.id.btnDialogLogin);

        btnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle("are you sure?");
                builder.setMessage("are you sure you wanna log in?(this action will override the last login details)");
                builder.setCancelable(false);
                builder.setPositiveButton("I agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userName",etUserName.getText().toString());
                        editor.putString("password",etPass.getText().toString());
                        editor.commit();
                        Toast.makeText(DialogActivity.this, "username password saved", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        d.dismiss();
                    }
                });
                builder.setNegativeButton("I don't agree", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        d.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        d.show();
    }
}