package com.example.chiwaura.blesscoffee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by  Engineer CHIWAURA on 10/24/2016.
 */
public class loggedin extends Activity {
    Button BtnMenu, BtnPlaceAnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggedin);

        TextView logUsername =(TextView)findViewById(R.id.Welcome);
        logUsername.setText( "Loggedin as  " + getIntent().getStringExtra("fullname"));
        final String fullname = getIntent().getStringExtra("fullname");
        final String address = getIntent().getStringExtra("address");
        final String phonenumber = getIntent().getStringExtra("phonenumber");


        BtnMenu = (Button) findViewById(R.id.Menu);
       // textViewPayment = (TextView) findViewById(R.id.Payment);
        BtnPlaceAnOrder = (Button) findViewById(R.id.Order);

        BtnMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity and Start The Activity
                Intent intentMenu = new Intent(getApplicationContext(), Menu.class);
                startActivity(intentMenu);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        /*textViewPayment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity and Start The Activity
                Intent intentPayment = new Intent(getApplicationContext(), Payment.class);
                startActivity(intentPayment);
            }
        });*/


        BtnPlaceAnOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
// TODO Auto-generated method stub

/// Create Intent for SignUpActivity and Start The Activity
                Intent intentPlaceAnOrder = new Intent(getApplicationContext(), Order1.class);

                intentPlaceAnOrder.putExtra("fullname", fullname);
                intentPlaceAnOrder.putExtra("phonenumber",phonenumber);
                intentPlaceAnOrder.putExtra("address", address);
                startActivity(intentPlaceAnOrder);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                intentPlaceAnOrder.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intentPlaceAnOrder.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });

        Button contactUs = (Button)findViewById(R.id.Contact);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ContactDetails.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        Button logout = (Button)findViewById(R.id.btn_back);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
            }
        });
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

