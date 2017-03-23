package com.example.chiwaura.blesscoffee;

/**
 * Created by blessing on 1/6/2017.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order1 extends Activity {
     ListView list;

    Button orderbtn;
    ArrayList<String> rows;
    ArrayList<MenuItem> itemList, selectedItemsList;
    ListView listView1;
    static int total = 0;
    static StringBuilder orderedItems = new StringBuilder();
    //String dateTimeOfOrder = DateFormat.getDateTimeInstance().format(new Date());
    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
    int day = now.get(Calendar.DAY_OF_MONTH);
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int minute = now.get(Calendar.MINUTE);
    int second = now.get(Calendar.SECOND);
    String dateTimeOfOrder = String.valueOf(day) +" " + String.valueOf(month) + " " + String.valueOf(year) +
                             " " + String.valueOf(hour) +":" +String.valueOf(minute)+":" + String.valueOf(second);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordering);
        list = (ListView) findViewById(R.id.list);
        //orderbtn = (Button) findViewById(R.id.Ordebtn);




        final String fullname = getIntent().getStringExtra("fullname");
        final String phonenumber =getIntent().getStringExtra("phonenumber");
        final String address = getIntent().getStringExtra("address");



        rows = new ArrayList<String>();

        itemList = new ArrayList<MenuItem>();
        itemList.add(new MenuItem("Coffee Drink", "A vietnamese coffee with sweetened condensed milk. A 60ml cup at $1",
                "1", "Quantity"));
        itemList.add(new MenuItem("Coffee Capsules", "Enjoy genuine expresso at home with these optimal dose of illy legendary nine " +
                "bean arabic blend for $2 a satchet", "2", "Quantity"));
        itemList.add(new MenuItem("Iced Tea", "A refreshing Pitcher sweetened/unsweetened mixed with flavoured syrup" +
                "at $1 a glass", "1", "Quantity"));
        itemList.add(new MenuItem("Milky Glass", "A hot glass of milk for $1", "1", "Quantity"));
        itemList.add(new MenuItem("Pastry", "Savoury pieces with eggs and milk and butter", "2", "Quantity"));
        itemList.add(new MenuItem("Burger", "Served with ham slices of meat patty with toppings of lettuce, onion, mayonnaise" +
                " and mustard for ony $1", "1", "Quantity"));
        itemList.add(new MenuItem("Beans Topping", "Fried corn tortilla topped with refried beans, shredded cheese and sa" +
                "isa", "1", "Quantity"));
        //itemList.add(new MenuItem("","description","9","Quantity"));

        Integer[] imageId = {R.drawable.coffeedrink, R.drawable.coffeecapsules, R.drawable.icedtea, R.drawable.milkglass,
                R.drawable.pastry, R.drawable.burger, R.drawable.beanstopping};




        // listView1 = (ListView) findViewById(R.id.listView1);
        OrderList adapter = new
                OrderList(Order1.this, itemList, imageId);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);



        orderbtn = (Button) findViewById(R.id.OrderBtn);
        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //StringBuilder orderedItems = new StringBuilder();
                orderedItems = new StringBuilder();
                orderedItems.append("Selected Items:\n");
                selectedItemsList = new ArrayList<MenuItem>();
                total = 0;
                for (int i = 0; i < itemList.size(); i++) {
                    MenuItem object = itemList.get(i);
                    if (object.isSelected()) {

                        orderedItems.append(object.getQty() + " " + object.getItem() + " @ $" +
                                object.getPrice() + "\n");
                        selectedItemsList.add(object);

                        //calculate total amount
                        total = total + Integer.parseInt(object.getQty()) * Integer.parseInt(object.getPrice());

                    }
                }

               // Toast.makeText(getApplicationContext(), orderedItems + " $" + total,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder selectedItems = new AlertDialog.Builder(Order1.this, R.style.MyAlertDialogStyle);
                selectedItems.setMessage(orderedItems +"Total costs  " + "$" + total + "\nTime: " + dateTimeOfOrder )
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final AlertDialog.Builder confirmOrder = new AlertDialog.Builder(Order1.this, R.style.MyAlertDialogStyle);
                                confirmOrder.setMessage("Are you sure you want the order?");
                                confirmOrder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent confirmOrderIntent = new Intent(getApplicationContext(), Payment.class);
                                        confirmOrderIntent.putExtra("total costs", total);
                                        confirmOrderIntent.putExtra("fullname", fullname);
                                        confirmOrderIntent.putExtra("phonenumber", phonenumber);
                                        confirmOrderIntent.putExtra("address", address);
                                        startActivity(confirmOrderIntent);
                                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                        finish();
                                        dialog.dismiss();
                                    }
                                })

                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                         Intent rejectOrderIntent = new Intent(getApplicationContext(), MainActivity.class);
                                         startActivity(rejectOrderIntent);
                                         overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                                         dialog.dismiss();
                                            }
                                        });

                                AlertDialog confirmOrderDialog = confirmOrder.create();
                                confirmOrderDialog .getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                                confirmOrderDialog.show();
                            }
                        });
                //Displaying the message on the toast
                //Toast.makeText(getApplicationContext(), orderedItems.toString(), Toast.LENGTH_LONG).show();
                AlertDialog selectedItemsDialog = selectedItems.create();
                selectedItemsDialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                selectedItemsDialog.show();



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



