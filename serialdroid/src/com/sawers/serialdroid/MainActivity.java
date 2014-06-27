package com.sawers.serialdroid;

import android.app.Activity;
import android.widget.TextView;
import android.widget.Button;
import android.os.Bundle;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import android.view.View;

public class MainActivity extends Activity{

  private File fichero;
  private InputStream input;
  private OutputStream output;
  private SerialPort serialPort;
  private SerialPortFinder serialFinder;
  private String[] devices;
  private String device;
  private Button boton1,boton2;
  private TextView label;
  private WriteBasic wb;
  private ATCall atc;


  public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    label = (TextView) findViewById(R.id.label);
    boton1 = (Button) findViewById(R.id.boton1);
    boton2 = (Button) findViewById(R.id.boton2);
    conectarSerial();
    asociarAcciones();


  }

  public void conectarSerial(){
    serialFinder = new SerialPortFinder();
    devices = serialFinder.getAllDevicesPath();
    device = parcearDevice(concatDevices(devices));
    fichero = new File(device);

    try{
      serialPort = new SerialPort(fichero,9600,0);
      input = serialPort.getInputStream();
      output = serialPort.getOutputStream();
      label.setText(device);
    }catch(IOException e ){


    }
  }

  private String concatDevices(String[] dev){
    String res = "" ;
    for(int i = 0; i < devices.length; i++){
        res += " " + dev[i];
    }
    return res;

  }

  private String parcearDevice(String dev){
    String res = dev.substring(0,13);
    res = res.trim();
    return res;

  }

  private void asociarAcciones(){
    boton1.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        wb = new WriteBasic(output);
        wb.escribirEntero(1);
      }
    });

    boton2.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v){
        atc = new ATCall(output);
        atc.llamar("75992745");
      }
    });
  }

}
