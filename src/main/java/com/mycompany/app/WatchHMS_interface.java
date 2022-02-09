package com.mycompany.app;

//import com.mycompany.app.WatchHM_interface;

//import interfase.WatchHM_interface;


interface WatchHMS_interface extends WatchHM_interface{
    int getSeconds();
    void setTime(int start_hours, int start_minutes, int start_seconds);
    void doTime(int do_hours, int do_minutes, int do_seconds);
    void tick();
    String getTime();
    void addAlarm(alarm ouralarm);
}

