package com.mycompany.app.Interfaces;

import com.mycompany.app.clock.Alarm;

public interface WatchHMS_Interface extends WatchHM_Interface {
    int getSeconds();
    void setTime(int start_hours, int start_minutes, int start_seconds);
    void doTime(int do_hours, int do_minutes, int do_seconds);
    void tick();
    String getTime();
    void addAlarm(Alarm ouralarm);
}

