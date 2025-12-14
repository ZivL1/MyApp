package com.katza.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName",etUserName.getText().toString());
                editor.putString("password",etPass.getText().toString());
                editor.commit();
                Toast.makeText(DialogActivity.this, "username password saved", Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });
        d.show();
    }
}