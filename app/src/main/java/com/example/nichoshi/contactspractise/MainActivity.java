package com.example.nichoshi.contactspractise;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView contactsListView;
    private List<HashMap<String,String>> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsList = new ArrayList<>();
        getContacts();
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        SimpleAdapter adapter = new SimpleAdapter(this,contactsList,R.layout.list_item_layout,new String[]{"name","number"},new int[]{R.id.itemName,R.id.itemNumber});
        contactsListView.setAdapter(adapter);
    }

    public void getContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                HashMap<String,String> contact = new HashMap<>();
                //Log.d("yoyoyo",name +" "+ number);
                contact.put("name",name);
                contact.put("number",number);
                contactsList.add(contact);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
           if(cursor != null){
               cursor.close();
           }
        }


    }
}
