package com.mycompany.app.Interfaces;

import com.mycompany.app.clock.WatchWithSeconds;

public interface AlarmInterface {
    void workedAlarm(WatchWithSeconds clock1);
    void setAlarm (int hours, int minutes, int seconds);
    String getInform();
    boolean getCondition();
}

