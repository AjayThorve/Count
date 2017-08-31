package org.aat.count;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Start extends AppCompatActivity {

    private int Count=0;
    private EditText e1;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        e1=(EditText)findViewById(R.id.editTextCount);
        b1=(Button) findViewById(R.id.SubmitButton);
        final Intent intent=new Intent(this,MainActivity.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ed_text=e1.getText().toString().trim();
                if(ed_text.isEmpty() || ed_text.length() == 0 || ed_text.equals("") || ed_text == null)
                {
                    Count=0;
                    Toast.makeText(Start.this, "Enter a value", Toast.LENGTH_SHORT).show();
                }else{
                    Count=Integer.parseInt(ed_text);
                    intent.putExtra("Count",Count);
                    if(Count>0){
                        startActivity(intent);
                    }else{
                        Toast.makeText(Start.this, "Enter a value greater than 0", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

}
