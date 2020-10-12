package com.gibran.ahmed.weatherly;

public class DataShow {
    String city ;
    String time ;
    String temp ;
    String Speed ;
    String Sunrise ;
    String Sunset ;
    String Pressure ;
    String Humidity ;
    String Temp_min ;
    String temp_max ;

    public DataShow(String city, String time, String temp, String speed, String sunrise, String sunset, String pressure, String humidity, String temp_min, String temp_max) {
        this.city = city;
        this.time = time;
        this.temp = temp;
        Speed = speed;
        Sunrise = sunrise;
        Sunset = sunset;
        Pressure = pressure;
        Humidity = humidity;
        Temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public DataShow(String city, String time, String temp, String speed) {
        this.city = city;
        this.time = time;
        this.temp = temp;
        this.Speed = speed;
    }
    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }

    public String getSunrise() {
        return Sunrise;
    }

    public void setSunrise(String sunrise) {
        Sunrise = sunrise;
    }

    public String getSunset() {
        return Sunset;
    }

    public void setSunset(String sunset) {
        Sunset = sunset;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getTemp_min() {
        return Temp_min;
    }

    public void setTemp_min(String temp_min) {
        Temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }


}
