package com.example.chiwaura.blesscoffee;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by  Engineer CHIWAURA on 10/23/2016.
 */
public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ImageView coffeedrink=(ImageView)findViewById(R.id.coffeeDrink);
        coffeedrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder coffeedrinkAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                coffeedrinkAlert.setMessage("A vietnamese coffee with sweetened condensed milk. A 60ml cup at $1");
                coffeedrinkAlert.create().show();
            }
        });


        ImageView coffeecapsules=(ImageView)findViewById(R.id.CoffeeCapsules);
        coffeecapsules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder coffeecapsuleAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                coffeecapsuleAlert.setMessage("Enjoy genuine expresso at home with these optimal dose of illy legendary nine " +
                        "bean arabic blend for $2 a satchet");
                coffeecapsuleAlert.create().show();
            }
        });





        ImageView burger=(ImageView)findViewById(R.id.Burger);
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder burgerAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                burgerAlert.setMessage("Served with ham slices of meat patty with toppings of lettuce, onion, mayonnaise " +
                        "and mustard for ony $1");
                burgerAlert.create().show();
            }
        });


        ImageView beanstopping=(ImageView)findViewById(R.id.beansTopping);
        beanstopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder beansAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                beansAlert.setMessage("Fried corn tortilla topped with refried beans, shredded cheese and saisa");
                beansAlert.create().show();

            }
        });


        ImageView icedtea=(ImageView)findViewById(R.id.IcedTea);
          icedtea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder icedTeaAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                icedTeaAlert.setMessage("A refreshing Pitcher sweetened/unsweetened mixed with flavoured syrup");
                icedTeaAlert.create().show();

            }
        });

        ImageView pastry=(ImageView)findViewById(R.id.Pastry);
        pastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder pastryAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                pastryAlert.setMessage("Savoury pieces with eggs and milk and butter");
                pastryAlert.create().show();

            }
        });

        ImageView milkyglass=(ImageView)findViewById(R.id.MilkGlass);
        milkyglass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder milkyAlert = new AlertDialog.Builder(Menu.this, R.style.MyAlertDialogStyle);

                milkyAlert.setMessage("A hot glass of milk for $1");
                milkyAlert.create().show();

            }
        });

        Button btnBack = (Button)findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                finish();
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


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}