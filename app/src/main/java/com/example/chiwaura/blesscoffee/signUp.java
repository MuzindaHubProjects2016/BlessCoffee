package com.example.chiwaura.blesscoffee;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by  Engineer CHIWAURA on 10/23/2016.
 */
public class signUp extends Activity {
    EditText editTextUserName, editTextPassword, editTextConfirmPassword, editTextAddress,
            editTextPhoneNumber, editFullName, editEmail;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

// get Instance of Database Adapter
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        auth = FirebaseAuth.getInstance();

// Get Refferences of Views
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editFullName = (EditText) findViewById(R.id.editFullName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// TODO Auto-generated method stub

                final String userName = editTextUserName.getText().toString();
                final String password = editTextPassword.getText().toString();
                final String confirmPassword = editTextConfirmPassword.getText().toString();
                final String address = editTextAddress.getText().toString();
                final String fullname = editFullName.getText().toString();
                final String phonenumber = editTextPhoneNumber.getText().toString();
                final String email = editEmail.getText().toString();




// check if any of the fields are vaccant
                if (userName.equals("") || password.equals("") || confirmPassword.equals("") || address.equals("")
                        || phonenumber.equals("")||fullname.equals("")||email.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill up all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                // check if both password matches
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();

                } else {
// Save the Data in Database
                    loginDataBaseAdapter.insertEntry(userName, password, fullname, address, phonenumber);
                    progressBar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Toast.makeText(signUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {

                                        final AlertDialog.Builder accountCreated = new AlertDialog.Builder(signUp.this, R.style.MyAlertDialogStyle);
                                        accountCreated.setMessage("Registration failed." + task.getException());
                                        accountCreated.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                        Dialog createdAccount =accountCreated.create();
                                        createdAccount.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                                        createdAccount.show();



                                        //Toast.makeText(signUp.this, "Authentication failed." + task.getException(),
                                        //        Toast.LENGTH_SHORT).show();
                                    } else {
                                        startActivity(new Intent(signUp.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                        finish();
                                    }
                                }
                            });


                }

            }
        });
        Button btnBack = (Button) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }

    @Override
    public void onBackPressed() {

        goBack();
    }

    public void goBack() {
        Intent intent =new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
        finish();

    }



}