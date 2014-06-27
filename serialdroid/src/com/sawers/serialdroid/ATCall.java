package com.sawers.serialdroid;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class ATCall{
  private String cmd;
  private OutputStream output;
  private OutputStreamWriter writer;

  public ATCall(OutputStream out){
    output = out;
    writer = new OutputStreamWriter(output);
    cmd = "";
  }

  public void llamar(String num){
    cmd = "ATD" + num + "\r\n";
    try{
      writer.write(cmd,0,cmd.length());
      writer.flush();
    }catch(IOException e){

    }

  }
}
