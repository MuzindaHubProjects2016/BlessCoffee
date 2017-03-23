package com.example.chiwaura.blesscoffee;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by blessing on 3/12/2017.
 */

public class onNewDevice extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmdetails);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        //final String password = getIntent().getStringExtra("password");
        //final String username = getIntent().getStringExtra("username");
        final EditText editFullName = (EditText) findViewById(R.id.editFullName);
        final EditText editUsername =(EditText)findViewById(R.id.editTextUserName);
        final EditText editPassword =(EditText)findViewById(R.id.editTextPassword);
        final EditText editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        final EditText editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        final EditText editEmail = (EditText) findViewById(R.id.editTextEmail);

        Button confirmDetails = (Button) findViewById(R.id.buttonCreateRecord);
       confirmDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName = editFullName.getText().toString();
                final String Address = editTextAddress.getText().toString();
                final String Phonenumber = editTextPhoneNumber.getText().toString();
                final String username = editUsername.getText().toString();
                final String password = editPassword.getText().toString();

                //mail = editEmail.getText().toString();

                if ( Address.equals("")
                        || Phonenumber.equals("")||fullName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill up all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                loginDataBaseAdapter.insertEntry(username, password, fullName, Address, Phonenumber);

                Intent intentLoggedin = new Intent(getApplicationContext(), loggedin.class);

                String fullname = loginDataBaseAdapter.getFullName(username);
                String address = loginDataBaseAdapter.getAddress(username);
                String phonenumber = loginDataBaseAdapter.getPhonenumber(username);
                //String mail;


                intentLoggedin.putExtra("address", address);
                intentLoggedin.putExtra("phonenumber", phonenumber);
                intentLoggedin.putExtra("fullname", fullname);
                //intentLoggedin.putExtra("email", mail);
                intentLoggedin.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentLoggedin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intentLoggedin);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                finish();

            }
        });



    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        // Close The Database
        loginDataBaseAdapter.close();

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
