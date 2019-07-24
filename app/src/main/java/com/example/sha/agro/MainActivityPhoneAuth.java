package com.example.sha.agro;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivityPhoneAuth extends AppCompatActivity
{
private static final String TAG = "MainActivityPhoneAuth";

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private EditText Mobile_Number;
    private EditText Verification_Code;
    private TextView ChangeLang;
    private TextView MobileNo,Verificationtext;
    private Button Send_Verification_Code;
    private Button Verify;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private ProgressDialog loadingBar;
    private String lang= "eng";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main_phone_auth);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Verificationtext = (TextView) findViewById(R.id.verificationtext);
        MobileNo = (TextView) findViewById(R.id.mobiletext);
        Mobile_Number = (EditText) findViewById(R.id.mobile_no);
        Verification_Code = (EditText) findViewById(R.id.verification_code);
        ChangeLang = (TextView) findViewById(R.id.change_language);
        Send_Verification_Code = (Button) findViewById(R.id.send_verification_code);
        Verify = (Button) findViewById(R.id.verify_button);
        loadingBar = new ProgressDialog(this);

        Send_Verification_Code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String MobileNumber = Mobile_Number.getText().toString();

                if (TextUtils.isEmpty(MobileNumber)) {
                    Toast.makeText(MainActivityPhoneAuth.this, "Please enter your phone number first...", Toast.LENGTH_SHORT).show();
                } else
                    {
                        loadingBar.setTitle("Phone Verification");
                        loadingBar.setMessage("please wait, while we are authenticating your phone...");
                        loadingBar.setCanceledOnTouchOutside(false);
                        loadingBar.show();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            MobileNumber,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                            callbacks);        // OnVerificationStateChangedCallbacks
                }
            }
        });

        Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Send_Verification_Code.setVisibility(View.INVISIBLE);
                Mobile_Number.setVisibility(View.INVISIBLE);
                MobileNo.setVisibility(View.INVISIBLE);

                String verificationCode = Verification_Code.getText().toString();

                if(TextUtils.isEmpty(verificationCode))
                {
                    Toast.makeText(MainActivityPhoneAuth.this,"Please write verification code first...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loadingBar.setTitle("Verification Code");
                    loadingBar.setMessage("please wait, while we are verifying verification code...");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                loadingBar.dismiss();
                Toast.makeText(MainActivityPhoneAuth.this,"Invalid Phone Number, Please enter correct phone number with your country code...",Toast.LENGTH_SHORT).show();

                Send_Verification_Code.setVisibility(View.VISIBLE);
                Mobile_Number.setVisibility(View.VISIBLE);
                MobileNo.setVisibility(View.VISIBLE);


                Verify.setVisibility(View.INVISIBLE);
                Verification_Code.setVisibility(View.INVISIBLE);
                Verificationtext.setVisibility(View.INVISIBLE);
            }

            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                loadingBar.dismiss();

                Toast.makeText(MainActivityPhoneAuth.this,"Code has been sent, please check and verify...",Toast.LENGTH_SHORT).show();

                Send_Verification_Code.setVisibility(View.INVISIBLE);
                Mobile_Number.setVisibility(View.INVISIBLE);
                MobileNo.setVisibility(View.INVISIBLE);

                Verify.setVisibility(View.VISIBLE);
                Verification_Code.setVisibility(View.VISIBLE);
                Verificationtext.setVisibility(View.VISIBLE);
            }

        };

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        ChangeLang = (TextView) findViewById(R.id.change_language);
        ChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }

            private void showChangeLanguageDialog() {
                final String[] listItems = {"தமிழ்", "English"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivityPhoneAuth.this);
                mBuilder.setTitle("Choose Language...");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            lang = "tam";
                            setLocale("ta");
                            recreate();
                        } else if (i == 1) {
                            lang = "eng";
                            setLocale("en");
                            recreate();
                        } else
                            lang = "eng";
                            setLocale("en");

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
        private void signInWithPhoneAuthCredential (PhoneAuthCredential credential)
        {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                loadingBar.dismiss();
                                Toast.makeText(MainActivityPhoneAuth.this, "Congratulations, you're logged in successfully...", Toast.LENGTH_SHORT).show();
                                SendUserToMainActivity();

                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(MainActivityPhoneAuth.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                            }
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
        editor.putString("my_lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("my_lang", "");
        setLocale(language);
    }




    @Override
    protected void onStart()
    {
        super.onStart();

        if (CurrentUser != null)
        {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);

            SendUserToMainActivity();
        }
    }

    private void updateUI(FirebaseUser currentUser)
    {


    }

    private void SendUserToMainActivity()

        {

            Intent loginIntent = new Intent(this, Main2Activity.class);
            loginIntent.putExtra("lang",lang);
            startActivity(loginIntent);
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
                Intent profile = new Intent(this, profile.class);
                startActivity(profile);
                break;

            case R.id.set:
                Intent settings = new Intent(this, Main2Activity.class);
                startActivity(settings);
                break;

            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout = new Intent(this, MainActivityPhoneAuth.class);
                startActivity(logout);
                break;
        }
        return true;

    }



}
