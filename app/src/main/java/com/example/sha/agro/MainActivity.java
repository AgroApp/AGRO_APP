package com.example.sha.agro;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

    private FirebaseUser CurrentUser;
    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private Button Signup;
    private Button changeLang;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);


        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        Info = (TextView) findViewById(R.id.forget);
        Login = (Button) findViewById(R.id.log_in);
        Signup = (Button) findViewById(R.id.sign_up);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToRegisterActivity();
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        changeLang = (Button) findViewById(R.id.change);
        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }

            private void showChangeLanguageDialog() {
                final String[] listItems = {"தமிழ்", "English"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("Choose Language...");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            setLocale("ta");
                            recreate();
                        } else if (i == 1) {
                            setLocale("en");
                            recreate();
                        } else
                            setLocale("en");

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });


    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }

    private void validate(String uremail, String urpassword) {
        if (uremail.equals("agro") && urpassword.equals("1234")) {

        } else if (uremail.equals("") || urpassword.equals("")) {
            Toast.makeText(getBaseContext(), "Enter both the fields", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Wrong entry", Toast.LENGTH_SHORT).show();


        }

    }


    @Override
    protected void onStart()
    {
        super.onStart();

        if (CurrentUser != null)
        {
            SendUserToMainActivity();
        }
    }

    private void SendUserToMainActivity()

        {
            Intent loginIntent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(loginIntent);
        }

    private void SendUserToRegisterActivity() {
        Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.profile:
                Intent profile = new Intent(this, Main2Activity.class);
                startActivity(profile);
                break;

            case R.id.set:
                Intent settings = new Intent(this, Main2Activity.class);
                startActivity(settings);
                break;

            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout = new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
        }
        return true;

    }














}
