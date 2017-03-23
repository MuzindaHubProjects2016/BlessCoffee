package com.example.chiwaura.blesscoffee;

/**
 * Created by blessing on 1/6/2017.
 */

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderList extends BaseAdapter {

    private  Activity context;
    //private final String[] menuItem;
    private  Integer[] imageId;
    //private final Integer[]price;
    private ArrayList<MenuItem> objectList;
    LayoutInflater inflater;

    public OrderList(Activity context,
                     ArrayList<MenuItem> objectList, Integer[] imageId ){
        //super(context, R.layout.menulist);
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.objectList = objectList;
        //this.menuItem = menuItem;
        this.imageId = imageId;
        //this.price = price;



    }

    public int getCount(){
        return objectList.size();
    }

    public MenuItem getItem (int position) {
        return objectList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder {
        CheckBox ck1;
        ImageView image;
        TextView name;
       // NumberPicker qntty;
        EditText qntty;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.menulist, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.item);
            holder.ck1 = (CheckBox) convertView.findViewById(R.id.checkBox1);
            holder.image = (ImageView) convertView.findViewById(R.id.icon);
            holder.qntty = (EditText) convertView.findViewById(R.id.firstLine);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }



       // holder.qntty.setMaxValue(30);
       // holder.qntty.setMinValue(0);
        convertView.setTag(holder);

        holder.ck1.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                CheckBox cb = (CheckBox) v;
                                                MenuItem object = (MenuItem) cb.getTag();
                                                if (holder.qntty.length()== 0 ){
                                                object.setQty("0");
                                                }
                                                Toast.makeText(context,
                                                        "You have selected:  " + object.getItem() +
                                                                "Price: " + object.getPrice() +
                                                                "Qty: " + object.getQty(),
                                                        Toast.LENGTH_SHORT).show();
                                                object.setSelected(cb.isChecked());
                                            }
                                        }
        );


        /*holder.qntty.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                NumberPicker quantity = picker;
                MenuItem object = (MenuItem) quantity.getTag();
                object.setQty(String.valueOf(newVal));
            }
        });*/
       // final String qnttySet = holder.qntty.getText().toString();




        holder.qntty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               /* MenuItem object = (MenuItem) holder.qntty.getTag();
                if(s.length() != 0) {
                    object.setQty(String.valueOf(count));
                }
                else {
                    Integer zero = 0;
                    object.setQty(String.valueOf(zero));
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                MenuItem object = (MenuItem) holder.qntty.getTag();
                if(s.length() != 0) {
                    object.setQty(String.valueOf(s));
                }

                //object.setQty(qnttySet);

            }
        });




        MenuItem object = objectList.get(position);
        holder.name.setText(object.getItem());
        //object.setQty(qnttySet);
        //holder.txt2.setText(object.getDesc());
       // holder.txt3.setText(object.getPrice());

        holder.qntty.setTag(object);


        holder.image.setImageResource(imageId[position]);
        holder.ck1.setChecked(object.isSelected());
        holder.ck1.setTag(object);

    /*@Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.menulist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        txtTitle.setText(menuItem[position]);
        CheckBox checkbox =(CheckBox)rowView.findViewById(R.id.checkBox1);
        checkbox.setChecked(false);
        EditText editText =(EditText)rowView.findViewById(R.id.firstLine);


        imageView.setImageResource(imageId[position]);


        return rowView;
    }*/

        return convertView;
    }
}
