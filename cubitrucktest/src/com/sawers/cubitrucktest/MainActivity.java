package com.sawers.cubitrucktest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;



public class MainActivity extends Activity
{
    private SerialPortFinder serialFinder;
    private ScrollView sv;
    private LinearLayout linearLayout;
    private TextView tv;
    private Button boton, boton1;
    public SerialPort serialPort;
    private InputStream input;
    private OutputStream output;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initGui();
        serialFinder = new SerialPortFinder();
        actions();
        setContentView(sv);
    }

    public void initGui(){
      tv = new TextView(this);
      boton = new Button(this);
      boton.setText("Buscar HWSerial");
      boton1 = new Button(this);
      boton1.setText("Open Predefined Serial");
      tv.setText("Bienvenido");
      sv = new ScrollView(this);
      linearLayout = new LinearLayout(this);
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      sv.addView(linearLayout);
      linearLayout.addView(tv);
      linearLayout.addView(boton);
      linearLayout.addView(boton1);

    }

    public void actions(){
      boton.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          String[] devices = serialFinder.getAllDevicesPath();
          String deviceString = concatDevices(devices);
          tv.setText(deviceString);
        }
      });

      boton1.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
            conectarSerial();
          }
      });
    }

    private String concatDevices(String[] dev){
      String res = "" ;
      for(int i = 0; i < dev.length; i++){
          res += " " + dev[i];
      }
      return res;

    }

    private void conectarSerial(){
        File file = new File("/dev/ttyS0");
        try{
          serialPort = new SerialPort(file,9600,0);
          output = serialPort.getOutputStream();
          input = serialPort.getInputStream();
        }catch(IOException ioe){


        }

        tv.setText("Conectado a /dev/ttyS0");

    }
}
