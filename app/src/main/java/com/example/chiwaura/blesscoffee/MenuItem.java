package com.example.chiwaura.blesscoffee;

/**
 * Created by blessing on 1/7/2017.
 */

public class MenuItem {
    private int id;
    private String item;
    private String price;
    private String desc;
    private String qty;
    private Boolean selected = false;

    public MenuItem() {

    }


    public MenuItem(String item, String desc, String price, String qty) {
        this.item = item;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
    }

    public MenuItem(int id, String item, String desc, String price, String qty) {
        this.id = id;
        this.item = item;
        this.desc = desc;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public String getQty() {
        return qty;
    }


    public void setQty(String qty) {
        this.qty = qty;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
