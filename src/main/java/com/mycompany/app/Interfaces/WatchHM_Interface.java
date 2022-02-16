package com.mycompany.app.Interfaces;

import com.mycompany.app.clock.Alarm;

import java.util.ArrayList;

public interface WatchHM_Interface {
    int getHours();
    int getMinutes();
    void setMinutes(int M);
    void setHours(int H);
    String getInfo();
    String getCompany();
    double getPrice();
    ArrayList<Alarm> getAlarmss();
    void setTime(int start_hours, int start_minutes);
    void doTime(int do_hours, int do_minutes);
}
