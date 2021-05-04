package lk.sliit.spendee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import lk.sliit.spendee.activity.DashboardActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button goButton;

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
         text1= findViewById(R.id.txt1) ;
         text2= findViewById(R.id.txt2) ;
         text3= findViewById(R.id.txt3) ;
         text4= findViewById(R.id.txt5) ;


        goButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);


    }
}