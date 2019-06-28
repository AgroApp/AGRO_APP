package com.example.sha.agro;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{


    private Button Signup;
    private EditText Email, Password,confrimpassword;
    private TextView AlreadyHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Email = (EditText) findViewById(R.id.emailregister);
        Password = (EditText) findViewById(R.id.passwordregister);
        confrimpassword =(EditText) findViewById(R.id.confirmpassword);
        Signup = (Button) findViewById(R.id.sign_up1);
        AlreadyHaveAccount = (TextView) findViewById(R.id.haveaccount);


        AlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.sign_up1:
                        registerUser();
                        break;

                    case R.id.haveaccount:
                        SendUserToLoginActivity();
                        break;
                }

            }
        });
    }

    private void registerUser()
    {
        String emailregister = Email.getText().toString().trim();
        String passwordregister = Password.getText().toString().trim();


         if (Email.equals("") || Password.equals("")) {
            Toast.makeText(getBaseContext(), "Enter both the fields", Toast.LENGTH_SHORT).show();



    }}

    private void SendUserToLoginActivity()
    {
        Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(LoginIntent);
    }





}
