package com.sawers.cubitrucktest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.File;



public class MainActivity extends Activity
{
    private SerialPortFinder serialFinder;
    private ScrollView sv;
    private LinearLayout linearLayout;
    private TextView tv;
    private EditText entry;
    private Button boton, boton1, boton2;
    public SerialPort serialPort;
    private InputStream input;
    private OutputStream output;
    private OutputStreamWriter writer;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initGui();
        serialFinder = new SerialPortFinder();
        actions();
        setContentView(sv);
    }

    public void initGui(){
      entry = new EditText(this);
      tv = new TextView(this);
      boton = new Button(this);
      boton.setText("Buscar HWSerial");
      boton1 = new Button(this);
      boton1.setText("Open Predefined Serial");
      boton2 = new Button(this);
      boton2.setText("Llamar");
      tv.setText("Bienvenido");
      sv = new ScrollView(this);
      linearLayout = new LinearLayout(this);
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      sv.addView(linearLayout);
      linearLayout.addView(entry);
      linearLayout.addView(tv);
      linearLayout.addView(boton);
      linearLayout.addView(boton1);
      linearLayout.addView(boton2);
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

      boton2.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
            llamar();
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

    public void llamar(){
      String num = entry.getText().toString();
      String cmd = "";
      writer = new OutputStreamWriter(output);
      cmd = "ATD" + num + ";" + "\r\n";
      try{
        writer.write(cmd,0,cmd.length());
        writer.flush();

      }catch(IOException ioe){

      }
    }
}
