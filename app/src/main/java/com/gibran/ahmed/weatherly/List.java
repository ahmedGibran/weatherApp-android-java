package com.gibran.ahmed.weatherly;

public class List {
      String Data ;
      String Type ;

    public List( String type, String data) {
        Data = data;
        Type = type;
    }

    public void setData(String data) {
        Data = data;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getData() {
        return Data;
    }

    public String getType() {
        return Type;
    }
}
