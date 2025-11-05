package com.katza.myapplication;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.zip.InflaterInputStream;

public class MainActivity extends AppCompatActivity{
    Button btn1;
    Button btn2;
    Button btn3;
    TextView txt1;
    TextView txt2;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    SeekBar skb1;
    EditText EditText1;
    Switch switch1;
    int clickAmount = 0;
    int randomNum = (int)(Math.random()*10)+1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        initListeners();
        
    }

    private void initListeners() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        switch1 = findViewById(R.id.switch1);
        skb1 = findViewById(R.id.skb1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt2.getVisibility()==INVISIBLE){
                    txt2.setVisibility(VISIBLE);
                    txt1.setVisibility(INVISIBLE);
                    Toast.makeText(MainActivity.this, "Showing txt2", Toast.LENGTH_SHORT).show();
                }else{
                    txt2.setVisibility(INVISIBLE);
                    txt1.setVisibility(VISIBLE);
                    Toast.makeText(MainActivity.this, "Showing txt1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAmount++;
                if (clickAmount<4){
                    Toast.makeText(MainActivity.this, "This button does nothing", Toast.LENGTH_SHORT).show();
                }
                if(clickAmount==4){
                    Toast.makeText(MainActivity.this, "Dont click one more time!", Toast.LENGTH_SHORT).show();
                }
                if(clickAmount==5){
                    Toast.makeText(MainActivity.this, "Fine this is it", Toast.LENGTH_SHORT).show();
                    img1.setVisibility(VISIBLE);
                }
                if(clickAmount>5){
                    Toast.makeText(MainActivity.this, "Bye Bye", Toast.LENGTH_SHORT).show();
                    img1.setVisibility(INVISIBLE);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = EditText1.getText().toString();
                if (num.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int userGuess;
                try {
                    userGuess = Integer.parseInt(num);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userGuess>10||userGuess<1){
                    Toast.makeText(MainActivity.this, "input numbers between 1-10 only", Toast.LENGTH_SHORT).show();
                }
                if (userGuess == randomNum) {
                    Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else if (userGuess < randomNum) {
                    Toast.makeText(MainActivity.this, "Too low!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Too high!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    img2.setVisibility(VISIBLE);
                }else{
                    img2.setVisibility(INVISIBLE);
                }
            }
        });
        skb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                img3.setAlpha(progress/100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "נלחץ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "נעזב", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        img1 = findViewById(R.id.img1);
        img1.setVisibility(INVISIBLE);
        img2 = findViewById(R.id.img2);
        img2.setVisibility(INVISIBLE);
        img3 = findViewById(R.id.img3);
        img3.setAlpha(0f);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt2.setVisibility(INVISIBLE);
        EditText1 = findViewById(R.id.EditText1);
    }

}