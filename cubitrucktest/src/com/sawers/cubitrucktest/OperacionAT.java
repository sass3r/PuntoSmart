package com.sawers.cubitrucktest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.File;


class OperacionAT{
  private String puerto;
  private SerialPort serial;
  private OutputStream output;
  private InputStream input;
  private OutputStreamWriter writer;
  private String cmd;

  public OperacionAT(String port){
    puerto = port;
    serial = null;
    output = null;
    input = null;
    writer = null;
    cmd = "";

  }

  public boolean abrirPuerto(){
    boolean res = false;
    File fichero = new File(puerto);
    try{
      serial = new SerialPort(fichero,9600,0);
      output = serial.getOutputStream();
      input = serial.getInputStream();
    }catch(IOException ioe){

    }
    if(output != null && input != null){
      writer = new OutputStreamWriter(output);
      res = true;
    }
    return res;
  }

  public void cerrarPuerto(){
      serial.close();
      input = null;
      output = null;
  }

  public void llamar(String num){
    cmd = "ATD" + num + ";\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void colgar(){
    cmd = "ATH\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void contestar(){
    cmd = "ATA\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }

  }

  public void recargar1bs(String num){
    cmd = "AT+CUSD=1,\"*66#\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1," + "\"" + num + "\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"1\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(4500);
    }catch(Exception e){}

    cmd = "AT+CUSD=1,\"2\"\r\n";

    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}


  }

  public void miniCargaTigo(String num, int mnto){
      if(validarMnto(mnto)){
          cmd = "AT+CUSD=1,\"*66#\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"1\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1," + "\"" + num + "\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          switch(mnto){
            case 1:
                    cmd = "AT+CUSD=1,\"1\"\r\n";
                    break;
            case 2:
                    cmd = "AT+CUSD=1,\"2\"\r\n";
                    break;
            case 5:
                    cmd = "AT+CUSD=1,\"3\"\r\n";
                    break;
            case 10:
                    cmd = "AT+CUSD=1,\"4\"\r\n";
                    break;

            case 20:
                    cmd = "AT+CUSD=1,\"5\"\r\n";
                    break;
          }

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"1\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

          try{
            Thread.currentThread().sleep(4500);
          }catch(Exception e){}

          cmd = "AT+CUSD=1,\"1\"\r\n";

          try{
            writer.write(cmd,0,cmd.length());
            writer.flush();
          }catch(IOException ioe){}

      }else{
          //monto no valido
      }
  }

  public boolean validarMnto(int mnto){
    boolean res = false;
    if(mnto == 1){
      res = true;
    }else{
      if(mnto == 2){
        res = true;
      }else{
        if(mnto == 5){
          res = true;
        }else{
          if(mnto == 10){
            res = true;
          }else{
            if(mnto == 20){
              res = true;
            }
          }
        }
      }
    }
    return res;
  }

  public void consultaDeSaldoTigo(){
    cmd = "AT+CUSD=1,\"*123#\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){

    }
  }

  public void enviarSMS(String num, String texto){
    cmd = "AT+CMGS=\"" + num  + "\"\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      Thread.currentThread().sleep(2000);
    }catch(Exception e){}


    cmd = texto;
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException ioe){}

    try{
      writer.write((char)26);
      writer.flush();
    }catch(IOException ioe){}


  }

}
