package com.sawers.serialdroid;

import java.io.OutputStream;
import java.io.IOException;

public class WriteBasic{

  private OutputStream output;

  public WriteBasic(OutputStream out){
    output = out;

  }

  public void escribirEntero(int i){
    try{
      output.write(i);
    }catch(IOException e){


    }

  }


}
