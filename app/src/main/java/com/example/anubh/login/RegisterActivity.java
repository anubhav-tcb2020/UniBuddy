package com.example.anubh.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etEmail=(EditText)findViewById(R.id.etEmail);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        mProgress =new ProgressDialog(this);
        String titleId="Registering...";
        mProgress.setTitle(titleId);
        mProgress.setMessage("Please Wait...");

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name=etName.getText().toString();
                final String username=etUsername.getText().toString();
                final String password=etPassword.getText().toString();
                final String email=etEmail.getText().toString();
                if(name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fields should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                mProgress.show();

                Response.Listener<String> responseListener= new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success=jsonResponse.getBoolean("success");
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                mProgress.dismiss();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registeration Failed!!")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };


                RegisterRequest registerRequest = new RegisterRequest(name,username,password,email,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });
    }
}
