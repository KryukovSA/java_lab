 
package com.mycompany.app;

import com.mycompany.app.Watch_with_seconds;

/**
 *
 * @author HP-PC
 */
interface alarm_interface {
    void workedAlarm(Watch_with_seconds clock1);
    void setAlarm (int hours, int minutes, int seconds);
    String getInform();
    boolean getCondition();
  //  void setTime(int H, int M, int S);
}

