package com.example.chiwaura.blesscoffee;

/**
 * Created by  Engineer CHIWAURA on 10/23/2016.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.net.URLEncoder.encode;


public class Payment extends Activity {

    private Button  DoneBtn, Logout;
    int total = Order1.total;
    String hash = Uri.encode("#");
    StringBuilder orderedItems = Order1.orderedItems;
    final String amountDue =String.valueOf(total);
    final ProgressBar progressBar =(ProgressBar) findViewById(R.id.progressBar);


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        final FirebaseDatabase Firebase = FirebaseDatabase.getInstance();
       // Firebase.setAndroidContext(this);
       final String name = getIntent().getStringExtra("fullname");
       final String phonenumber =getIntent().getStringExtra("phonenumber");
       final  String address = getIntent().getStringExtra("address");
        final StringBuilder customerDetails =new StringBuilder();


        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        final String dateTimeOfOrder = String.valueOf(day) +" " + String.valueOf(month) + " " + String.valueOf(year) +
                " " + String.valueOf(hour) +":" +String.valueOf(minute)+":" + String.valueOf(second);


        customerDetails.append( "Name: " + name + "\n" + "Phonenumber: " + phonenumber + "\n"+ "Address: " + address );



        final DatabaseReference myRef = Firebase.getReference("OrderTransactions");




        ImageButton cash =(ImageButton)findViewById(R.id.cash);
        cash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // final DatabaseReference myRef = Firebase.getReference("OrderTransactions");
                OrderTransactions orderTransactions = new OrderTransactions();
                orderTransactions.setCustomerDetails(customerDetails.toString());
                orderTransactions.setOrderedItems(orderedItems.toString());
                orderTransactions.setAmountDue(amountDue);
                //orderTransactions.setDateTimeOrdered(DateFormat.getDateTimeInstance().format(new Date()));
                orderTransactions.setDateTimeOrdered(dateTimeOfOrder);
                orderTransactions.setPaymentMethod("cash");
                String orderId = myRef.push().getKey();
                //DatabaseReference newRef =myRef.child("OrderTransactions").push();
                myRef.child(orderId).setValue(orderTransactions);



                Toast.makeText(getApplicationContext(), "Thank you  " + name + "  for choosing us. We will call you shortly ",
                        Toast.LENGTH_LONG).show();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+263774608254", null, name + "  of  " + address + "  cell number " + phonenumber +
                        " placed  an order of  " + orderedItems + " and has chosen to pay with cash", null, null);

            }
        });


        ImageView paynow = (ImageView)findViewById(R.id.paynow);
        paynow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String paynowUrl = Paynow();
                OrderTransactions orderTransactions = new OrderTransactions();
                orderTransactions.setCustomerDetails(customerDetails.toString());
                orderTransactions.setOrderedItems(orderedItems.toString());
                orderTransactions.setAmountDue(amountDue);
                orderTransactions.setDateTimeOrdered(DateFormat.getDateTimeInstance().format(new Date()));
                orderTransactions.setPaymentMethod("paynow");
                String orderId = myRef.push().getKey();
                //DatabaseReference newRef =myRef.child("OrderTransactions").push();
                myRef.child(orderId).setValue(orderTransactions);

                if (isOnline()) {
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(getApplicationContext(), PaynowWebview.class);
                    startActivity(intent);
                   // intent.setAction(Intent.ACTION_VIEW);
                    //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                   // intent.setData(Uri.parse(paynowUrl));
                   // startActivity(intent);
                    overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                    //progressBar.setVisibility(View.GONE);

                }
                else {


                Toast.makeText(getApplicationContext(), "Check your Internet connection and click again",
                        Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                }



            }
        });









        Logout = (Button) findViewById(R.id.Logout);

        // add button listener
        Logout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_LONG).show();
                finish();



            }

        });

    }

    @Override
    public void onResume (){
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




    private String Paynow(){
      String merchantUrl = "search=blesschews%40gmail.com&amount="+ amountDue + "&reference=blessCoffee&I=1";
      String codedMerchantUrl = Base64.encodeToString(merchantUrl.getBytes(),Base64.DEFAULT);
      String encodedurl="";
      try {

          encodedurl = encode (codedMerchantUrl,"UTF-8");
          //Log.d("TEST", encodedurl);
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      String paynowUrl = "https://www.paynow.co.zw/payment/link/?q=" + encodedurl;
      return  paynowUrl;


  }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }





}


