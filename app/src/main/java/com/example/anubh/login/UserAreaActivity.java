package com.example.anubh.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UserAreaActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etEmail=(EditText)findViewById(R.id.etEmail);
        final TextView welcomeMessage= (TextView)findViewById(R.id.tvwelcomemsg);

        Intent intent = getIntent();
        String name=intent.getStringExtra("name");
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        String email = intent.getStringExtra("email");

        String message = name + " Welcome to UniBuddy!\nWe are thrilled to have you on board!We'll make sure you have an amazing experience while learning with us.\nLet's start!";
        welcomeMessage.setText(message);
        etUsername.setText(username);
        etEmail.setText(email);

        Button button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mreaintent = new Intent(UserAreaActivity.this, MainActivity.class);
                UserAreaActivity.this.startActivity(mreaintent);
            }


    });


    }
}
