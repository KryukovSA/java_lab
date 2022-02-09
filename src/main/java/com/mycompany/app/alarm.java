package com.mycompany.app;
//package interfase;

//import interfase.alarm_interface;

/**
 * //чтоб при наступлении времени будильник сработал и вывел сообщение
 * @author HP-PC
 */
public class alarm implements alarm_interface{
    
    private int alarmHour;
    private int alarmMinute;
    private int alarmSecond;
    private boolean condition;
   
    
    alarm (int hours, int minutes, int seconds) {
 
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
    public void workedAlarm(Watch_with_seconds clock1) {
       if(this.condition == true && clock1.getHours() == alarmHour && clock1.getMinutes() == alarmMinute && clock1.getSeconds() == alarmSecond) {
            System.out.println("alarm work, time  " + alarmHour + ":" + alarmMinute);  
            this.condition = false;
       }
    }  
    
    
  /*  public static String getInform1(){
        infStr = new String(this.alarmHour + ":" + this.alarmMinute + ":" + this.alarmSecond);
        return infStr;
    }*/
    
   /* public alarm getALarmFromStr (String s){
        int h =0, m=0, sec=0;
        String tmpstr = new String(); 
        char[] chars = s.toCharArray();
        for(int a = 0; a< 3;) {
        for(int i = 0; i < chars.length; i++)
            if(chars[i] != ':' && chars[i+1] != ':' && i + 1 < chars.length) {
                tmpstr += chars[i];
            } else if ((chars[i] != ':' && chars[i+1] == ':') || i + 1 == chars.length) {
                tmpstr += chars[i];
                if(a == 0){
                h = Integer.parseInt(tmpstr);
                }
                else if (a == 1) {
                m = Integer.parseInt(tmpstr);
                }
                else if (a == 2) {
                    sec = Integer.parseInt(tmpstr);
                }
                a++;
                tmpstr = "";
            } else {
                continue;
            }
        }
        return new alarm(h, m, sec);
            
    }*/
    
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
