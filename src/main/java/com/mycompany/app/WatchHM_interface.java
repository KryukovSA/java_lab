package com.mycompany.app;

import java.util.ArrayList;

interface WatchHM_interface {
    int getHours();
    int getMinutes();
    void setMinutes(int M);
    void setHours(int H);
    String getInfo();
    String getCompany();
    double getPrice();
    ArrayList<alarm> getAlarmss();
    void setTime(int start_hours, int start_minutes);
    void doTime(int do_hours, int do_minutes);
}
