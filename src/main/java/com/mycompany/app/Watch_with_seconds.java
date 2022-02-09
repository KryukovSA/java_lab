package com.mycompany.app;

//import com.mycompany.app.WatcMain;


public class Watch_with_seconds extends WatcMain implements WatchHMS_interface {
    private int seconds;

    Watch_with_seconds(String company_name, int time_hours, int time_minutes, int time_seconds, double watch_price) {
        super(company_name, time_hours, time_minutes, watch_price);
        seconds = time_seconds;
    }

    @Override
    public int getSeconds() {
        return seconds;
    }

    @Override
    public void setTime(int start_hours, int start_minutes, int start_seconds) {
        super.setTime(start_hours, start_minutes);
        if (seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Wrong seconds!");
        }
        seconds = start_seconds;
    }

    @Override
    public void doTime(int do_hours, int do_minutes, int do_seconds) {
        super.doTime(do_hours, do_minutes);
        super.doTime(0, (seconds + do_seconds) / 60);
        seconds = (seconds + do_seconds) % 60;
    }

    @Override
    public String getInfo() {
        return this.getCompany() + ": " + this.getHours() + ":" + this.getMinutes() + ":" + seconds + " price: "
                + this.getPrice();
    }
    
    @Override
    public String getTime() {
        return this.getHours() + ":" + this.getMinutes() + ":" + seconds;
    }
    
    @Override
    public void tick() {
        seconds = seconds +1;
        if(seconds >= 60) {
            this.setMinutes(this.getMinutes() + 1);
            seconds = 0;
        }
        if(this.getMinutes() >= 60) {
            this.setMinutes(0);
            this.setHours(this.getHours() + 1);
        }
        if(this.getHours() >=24) {
            this.setHours(0);
        }
                             /*   Global.h1 = this.getHours();
                        Global.m1 = this.getMinutes();
                        Global.s1 = seconds;*/
        
    }
    
    @Override
    public void addAlarm(alarm ouralarm) {
        this.alarms.add(ouralarm);
    }
    
    public alarm getALarmFromStr (String s){
        int h =0, m=0, sec=0;
        String tmpstr = new String(); 
        char[] chars = s.toCharArray();

        int a = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] != ':'){
                tmpstr += chars[i];
            } else {
                if (a == 0){
                h = Integer.parseInt(tmpstr);
                 a++;
                tmpstr = "";
                }
                else if (a == 1) {
                m = Integer.parseInt(tmpstr);
                 a++;
                tmpstr = "";
                }
                else if (a == 2){
                    sec = Integer.parseInt(tmpstr);
                     a++;
                tmpstr = "";
                }
            
            }
            if(i == chars.length - 1)
                sec = Integer.parseInt(tmpstr);
           
  
             /*if(chars[i] != ':' && chars[i+1] != ':') {
                tmpstr += chars[i];
                
            } else if((chars[i] != ':' && chars[i+1] == ':')  || i + 2 == chars.length){
                tmpstr += chars[i];
                if (a == 0){
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
        }*/
        
        }
        return new alarm(h, m, sec);
            
    }
}
