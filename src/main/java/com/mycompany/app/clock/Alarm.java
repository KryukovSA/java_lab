package com.mycompany.app.clock;

import com.mycompany.app.Interfaces.AlarmInterface;
import com.mycompany.app.clock.WatchWithSeconds;

public class Alarm implements AlarmInterface {
    private int alarmHour;
    private int alarmMinute;
    private int alarmSecond;
    private boolean condition;
    
    public Alarm(int hours, int minutes, int seconds) {
      alarmHour = hours;
      alarmMinute = minutes;
      alarmSecond = seconds;
      if (alarmHour < 0 || alarmMinute < 0 || alarmHour > 23 || alarmMinute > 59) {
            throw new RuntimeException("Incorrect time!");
        }
      condition = true; //включен
    }
    
    @Override
    public void setAlarm (int hours, int minutes, int seconds) {
         alarmHour = hours;
      alarmMinute = minutes;
      alarmSecond = seconds;
    }
    
    @Override
    public void workedAlarm(WatchWithSeconds clock1) {
       if(this.condition == true && clock1.getHours() == alarmHour && clock1.getMinutes() == alarmMinute && clock1.getSeconds() == alarmSecond) {
            System.out.println("alarm work, time  " + alarmHour + ":" + alarmMinute);  
            this.condition = false;
       }
    }
    
    @Override
    public  String getInform(){
        return new String(this.alarmHour + ":" + this.alarmMinute + ":" + this.alarmSecond);
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public int getAlarmMinute() {
        return alarmMinute;
    }

    public int getAlarmSecond() {
        return alarmSecond;
    }
    
    @Override
    public boolean getCondition(){
        return this.condition;
    }
}
