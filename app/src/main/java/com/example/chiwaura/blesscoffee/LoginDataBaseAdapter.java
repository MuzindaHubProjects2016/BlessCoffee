package com.example.chiwaura.blesscoffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.


    //  create a new database.
    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text, FULLNAME text," +
            "ADDRESS text, PHONENUMBER text); ";


    // Variable to hold the database instance
    public  SQLiteDatabase db;


    private final Context context;

    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public  LoginDataBaseAdapter(Context _context){
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public  LoginDataBaseAdapter open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }


    public void close(){

        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance(){
        return db;
    }



    public void insertEntry(String userName,String password, String fullname, String address, String phoneNumber){
        ContentValues newValues = new ContentValues();


        newValues.put("USERNAME", userName);    // Assign values for each menulist.
        newValues.put("PASSWORD",password);
        newValues.put("FULLNAME",fullname);
        newValues.put("ADDRESS",address);
        newValues.put("PHONENUMBER",phoneNumber);

        db.insert("LOGIN", null, newValues);   // Insert the menulist into your table
    }


    public void deleteEntry(String UserName){

        String where="USERNAME=?";     //String id=String.valueOf(ID);
        db.delete("LOGIN", where, new String[]{UserName}) ;
    }



    public String getSinlgeEntry(String userName){

        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }


    public String checkUserName(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";

        }
        else {
            cursor.moveToFirst();
            String username = cursor.getString(cursor.getColumnIndex("USERNAME"));
            cursor.close();
            return username;

        }
    }





    public String getAddress(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        else {
            cursor.moveToFirst();
            String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));

            cursor.close();
            return address;

        }
    }






    public String getPhonenumber(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        else {
            cursor.moveToFirst();
            String phonenumber = cursor.getString(cursor.getColumnIndex("PHONENUMBER"));
            cursor.close();
            return phonenumber;

        }
    }





    public String getFullName(String userName)
    {
        Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        else {
            cursor.moveToFirst();
            String fullname = cursor.getString(cursor.getColumnIndex("FULLNAME"));
            cursor.close();
            return fullname;

        }
    }










    public void  updateEntry(String userName,String password, String fullname, String address, String phoneNumber)
    {
        // Define the updated menulist content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each menulist.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);
        updatedValues.put("FULLNAME", fullname);
        updatedValues.put("ADDRESS",address);
        updatedValues.put("PHONENUMBER",phoneNumber);



        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }


    public void  update(String userName,String password)
    {
        // Define the updated menulist content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each menulist.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);
        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{userName});
    }




}