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
    private Button boton, boton1, boton2, boton3, boton4, boton5, boton6, write;
    public SerialPort serialPort;
    public SerialPort serialPort1;
    public SerialPort serialPort2;
    public SerialPort serialPort3;
    public SerialPort serialPort4;
    public SerialPort serialPort5;
    public InputStream input;
    public OutputStream output;
    public InputStream input1;
    public OutputStream output1;
    public InputStream input2;
    public OutputStream output2;
    public InputStream input3;
    public OutputStream output3;
    public InputStream input4;
    public OutputStream output4;
    public InputStream input5;
    public OutputStream output5;



    String res;


    public void onCreate(Bundle savedInstanceState)
    {
        res = "";

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
      boton1.setText("Open /dev/ttyGS3");
      tv.setText("Bienvenido");
      boton2 = new Button(this);
      boton2.setText("Open /dev/ttyGS2");
      boton3 = new Button(this);
      boton3.setText("Open /dev/ttyGS1");
      boton4 = new Button(this);
      boton4.setText("Open /dev/ttyGS0");
      boton5 = new Button(this);
      boton5.setText("Open /dev/ttyS2");
      boton6 = new Button(this);
      boton6.setText("Open /dev/ttyS0");
      write = new Button(this);
      write.setText("Write");

      sv = new ScrollView(this);
      linearLayout = new LinearLayout(this);
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      sv.addView(linearLayout);
      linearLayout.addView(tv);
      linearLayout.addView(boton);
      linearLayout.addView(boton1);
      linearLayout.addView(boton2);
      linearLayout.addView(boton3);
      linearLayout.addView(boton4);
      linearLayout.addView(boton5);
      linearLayout.addView(boton6);
      linearLayout.addView(write);
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
            conectarSerial("/dev/ttyGS3", serialPort, input, output);
            if(input != null && output != null ){
              boton1.setText("Conected");
            }else{
              boton1.setText("Ugly");
            }
          }
      });

      boton2.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          conectarSerial("/dev/ttyGS2", serialPort1, input1, output1);
          if(input1 != null && output1 != null ){
            boton2.setText("Conected");
          }else{
            boton2.setText("Ugly");
          }
        }
      });

      boton3.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          conectarSerial("/dev/ttyGS1", serialPort2, input2, output2);
          if(input2 != null && output2 != null ){
            boton3.setText("Conected");
          }else{
            boton3.setText("Ugly");
          }
        }
      });

      boton4.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          conectarSerial("/dev/ttyGS0", serialPort3, input3, output3);
          if(input3 != null && output3 != null ){
            boton4.setText("Conected");
          }else{
            boton4.setText("Ugly");
          }
        }
      });

      boton5.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          conectarSerial("/dev/ttyS2", serialPort4, input4, output4);
          if(input4 != null && output4 != null ){
            boton5.setText("Conected");
          }else{
            boton5.setText("Ugly");
          }
        }
      });

      boton6.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          conectarSerial("/dev/ttyS0", serialPort5, input5, output5);
          if(input5 != null && output5 != null ){
            boton6.setText("Conected");
          }else{
            boton6.setText("Ugly");
          }
        }
      });

      write.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
          escribirBasic(output5);/*
          escribirBasic(output1);
          escribirBasic(output2);
          escribirBasic(output3);
          escribirBasic(output4);
          escribirBasic(output5);*/
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

    private void conectarSerial(String ruta, SerialPort ser, InputStream in, OutputStream out){
        File file = new File(ruta);
        try{
          ser = new SerialPort(file,9600,0);
          out = ser.getOutputStream();
          in = ser.getInputStream();
        }catch(IOException ioe){


        }

        res = res + ruta + " ";
        tv.setText(res);

    }

    private void escribirBasic(OutputStream out){
      try{
        out.write(1);
      }catch(IOException ioe){

      }
    }
}
