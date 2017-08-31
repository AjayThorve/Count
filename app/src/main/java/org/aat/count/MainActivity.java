package org.aat.count;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private float count;
    private int Categories=2,n=2;
    private Button b1,b2;
    private TextView t1;
    private EditText e;
    private RecyclerView counterView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AlertDialog alertDialog;

    public static float increment=1;
    private List<items_counter> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        increment=1;
        Intent intent=getIntent();
        n=intent.getIntExtra("Count",0);

        counterView=(RecyclerView)findViewById(R.id.my_counters_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        counterView.setHasFixedSize(false);

        //use a linear layout manager
        if(n<=3){
            layoutManager=new LinearLayoutManager(this);
        }else{
            layoutManager=new GridLayoutManager(this,2);
        }

        counterView.setLayoutManager(layoutManager);


        list=new ArrayList<>();

        for(int i=0;i<n;i++){
            items_counter item=new items_counter(count,"Category "+(i+1));
            list.add(item);
        }

        //specify an adapter
        mAdapter=new CounterAdapter(list,this);
        counterView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void EditSettings(){
        final EditText e=new EditText(this);
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Enter Increment");
        alertDialog.setMessage("Enter only numbers please");
        alertDialog.setView(e, 100 ,50, 100 , 50);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                float num;
                if (TextUtils.isEmpty(e.getText().toString())) {

                } else {
                    try {
                        num = Float.parseFloat(e.getText().toString().trim());
                        increment=num;

                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Please enter only numbers", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    public void ResetAll(){
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.edit:
                EditSettings();
                return true;
            case R.id.resetAll:
                ResetAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}

