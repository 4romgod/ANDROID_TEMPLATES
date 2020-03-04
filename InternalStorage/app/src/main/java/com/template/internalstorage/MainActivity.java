package com.template.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnSave, btnLoad;
    EditText etMessage;

    TextView tvMessage;

    private String fileName = "myInternalFile";
    String messsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMessage = (EditText) findViewById(R.id.etMessage);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messsage = etMessage.getText().toString();

                try {
                    FileOutputStream fos = openFileOutput(fileName, MODE_APPEND);
                    fos.write(messsage.getBytes());
                    fos.close();
                    Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_SHORT).show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }

            }
        });


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(fileName);
                    int c;
                    String temp = "";

                    while((c = fis.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }

                    tvMessage.setText(temp);
                    Toast.makeText(getBaseContext(), "File Read", Toast.LENGTH_SHORT).show();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });


    }       //end onCreate()


}       //end class
