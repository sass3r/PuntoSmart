package com.sawers.cubitrucktest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.view.View;



public class MainActivity extends Activity
{
    private SerialPortFinder serialFinder;
    private ScrollView sv;
    private LinearLayout linearLayout;
    private TextView tv, tv1, tv2, tv3;
    private EditText entry, entry1, entry2;
    private Button boton, boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8;
    private OperacionAT opAt;


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
      entry1 = new EditText(this);
      entry2 = new EditText(this);
      tv = new TextView(this);
      tv1 = new TextView(this);
      tv2 = new TextView(this);
      tv3 = new TextView(this);
      tv1.setText("Marque el Numero al que desea llamar, recargar credito o enviar sms:");
      tv2.setText("Escriba el monto a recargar montos validos 1bs,2bs,5bs,10bs,20bs:");
      tv3.setText("SMS:");
      boton = new Button(this);
      boton.setText("Buscar HWSerial");
      boton1 = new Button(this);
      boton1.setText("Open Predefined Serial");
      boton2 = new Button(this);
      boton2.setText("Llamar");
      tv.setText("Bienvenido");
      boton3 = new Button(this);
      boton3.setText("Colgar");
      boton4 = new Button(this);
      boton4.setText("Recargar 1Bs Tigo");
      boton5 = new Button(this);
      boton5.setText("MiniRecarga Tigo");
      boton6 = new Button(this);
      boton6.setText("Consulta de saldo Tigo");
      boton7 = new Button(this);
      boton7.setText("Enviar SMS");
      boton8 = new Button(this);
      boton8.setText("Contestar Llamada");
      sv = new ScrollView(this);
      linearLayout = new LinearLayout(this);
      linearLayout.setOrientation(LinearLayout.VERTICAL);
      sv.addView(linearLayout);
      linearLayout.addView(tv1);
      linearLayout.addView(entry);
      linearLayout.addView(tv2);
      linearLayout.addView(entry1);
      linearLayout.addView(tv3);
      linearLayout.addView(entry2);
      linearLayout.addView(tv);
      linearLayout.addView(boton);
      linearLayout.addView(boton1);
      linearLayout.addView(boton2);
      linearLayout.addView(boton3);
      linearLayout.addView(boton8);
      linearLayout.addView(boton4);
      linearLayout.addView(boton5);
      linearLayout.addView(boton6);
      linearLayout.addView(boton7);
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
            opAt = new OperacionAT("/dev/ttyS0");
            if(opAt.abrirPuerto()){
              tv.setText("Conectado a /dev/ttyS0");
            }else{
              tv.setText("fail!");
            }
          }
      });

      boton2.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
            llamar();
          }
      });

      boton3.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
            opAt.colgar();
          }
      });

      boton4.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
            recargar1bs();
          }
      });

      boton5.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
              miniCargaTigo();
          }
      });

      boton6.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
              opAt.consultaDeSaldoTigo();
          }
      });

      boton7.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
              enviarSMS();
          }
      });

      boton8.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v){
              opAt.contestar();
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

    public void llamar(){
      String num = entry.getText().toString();
      opAt.llamar(num);
    }

    public void recargar1bs(){
      String num = entry.getText().toString();
      opAt.recargar1bs(num);
    }

    public void miniCargaTigo(){
      String num = entry.getText().toString();
      String mnto = entry1.getText().toString();
      int monto = Integer.parseInt(mnto);
      opAt.miniCargaTigo(num, monto);
    }

    public void enviarSMS(){
      String num = entry.getText().toString();
      String sms = entry2.getText().toString();
      opAt.enviarSMS(num,sms);

    }
}
