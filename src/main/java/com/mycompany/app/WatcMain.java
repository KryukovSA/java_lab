package com.mycompany.app;

import com.mycompany.app.WatchHM_interface;
import com.mycompany.app.alarm;
import java.util.ArrayList;

//import interfase.WatchHM_interface;

public class WatcMain implements WatchHM_interface {
    private int hours;
    private int minutes;
    private final String company;
    private final double price;
    ArrayList<alarm> alarms;
    
    
    WatcMain(String company_name, int time_hours, int time_minutes, double watch_price) {
        company = company_name;
        hours = time_hours;
        minutes = time_minutes;
        price = watch_price;
        alarms = new ArrayList<alarm>();
    }

    @Override
    public int getHours() {
        return hours;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public void setHours(int H) {
        hours = H;
    }

    @Override
    public void setMinutes( int M) {
        minutes = M;
    }
    
    @Override
    public String getInfo() {
        return company + ": " + hours + ":" + minutes + " price: " + price;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setTime(int start_hours, int start_minutes) {
        if (start_hours < 0 || start_minutes < 0 || start_hours > 23 || start_minutes > 59) {
            throw new RuntimeException("Incorrect time!");
        }
        hours = start_hours;
        minutes = start_minutes;
    }

    @Override
    public void doTime(int do_hours, int do_minutes) {
        if (do_hours < 0 || do_minutes < 0) {
            throw new RuntimeException("Incorrect time!");
        }
        hours = (do_hours + hours) % 24 + (do_minutes + minutes) / 60 % 24;
        minutes = (do_minutes + minutes) % 60;
    }
    
    @Override
    public ArrayList<alarm> getAlarmss(){
        return this.alarms;
    }
   
}
