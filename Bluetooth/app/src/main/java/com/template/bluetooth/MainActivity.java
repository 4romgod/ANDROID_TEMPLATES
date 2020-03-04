package com.template.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //VIEWS
    Button btnTurnOn, btnTurnOff, btnVisible, btnDevices;
    ListView lvDevices;


    private BluetoothAdapter adapterBT;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapterBT = BluetoothAdapter.getDefaultAdapter();

        //INIT VIEWS
        btnTurnOn = (Button) findViewById(R.id.btnTurnOn);
        btnTurnOff = (Button) findViewById(R.id.btnTurnOff);
        btnVisible = (Button) findViewById(R.id.btnVisible);
        btnDevices = (Button) findViewById(R.id.btnDevices);
        lvDevices = (ListView) findViewById(R.id.lvDevices);

        btnTurnOn.setOnClickListener(this);
        btnTurnOff.setOnClickListener(this);
        btnVisible.setOnClickListener(this);
        btnDevices.setOnClickListener(this);

    }       //end onCreate()

    public void turnON(View v){
        if(!adapterBT.isEnabled()){
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned on", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
        }

    }       //end turnON()


    public void turnOFF(View v){
        adapterBT.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_SHORT).show();
    }       //end turnON()


    public void getVisible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }       //end turnON()


    public void getDevices(View v){
        pairedDevices = adapterBT.getBondedDevices();

        ArrayList listDevices = new ArrayList();

        for(BluetoothDevice device: pairedDevices){
            listDevices.add(device.getName());
        }

        Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, listDevices);

        lvDevices.setAdapter(adapter);
    }       //end turnON()


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnTurnOn: {
                turnON(v);
                break;
            }

            case R.id.btnTurnOff: {
                turnOFF(v);
                break;
            }

            case R.id.btnVisible: {
                getVisible(v);
                break;
            }

            case R.id.btnDevices: {
                getDevices(v);
                break;
            }

        }       //end switch()

    }       //end onClick()


}       //end class
