/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app;

//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author HP-PC
 */


public class ControlClock {
    
    Watch_with_seconds clock1;
    Thread t;
    boolean f1 = false;
    boolean pause = false;
    JLabel l, alar;
    
    
    //alarm Alarm = new alarm(10, 10, 20);

    ControlClock(Watch_with_seconds clock) {
      
        clock1 = clock;
       
        
    }
     
    public void start() {
  
        Server.m.watch.setTime(Global.h1, Global.m1, Global.s1);
        
        if(t == null){
            t = new Thread() {
                @Override
                public void run() {
                    f1 = true;
                    while(f1){
                    //действия с часами
                    try {
                        if(pause) {
                            synchronized(Server.m.watch){
                                l.setText("pause");
                                Server.m.watch.wait();
                            }
                            pause = false;
                        }
                        Server.m.watch.tick();
                    
                        Thread.sleep(1000);
                        l.setText(Server.m.watch.getTime());
                        /*Global.h1 = Server.m.watch.getHours();
                        Global.m1 = Server.m.watch.getMinutes();
                        Global.s1 = Server.m.watch.getSeconds();*/
                       
                        //проверка на срабатывание
                        
             //загрузка будильников из БД
                      for(int i = 0; i < Server.m.getInfoFromDB().size(); i++)
                        {
                            alarm tmpalarm = Server.m.watch.getALarmFromStr(Server.m.getInfoFromDB().get(i)); 
                            System.out.println(tmpalarm.getInform());
                          
                           Server.m.watch.alarms.add(tmpalarm);
                        }          
                      for(int i = 0; i < Server.m.watch.alarms.size(); i++){
                        Server.m.watch.alarms.get(i).workedAlarm(Server.m.watch);
                        if(Server.m.watch.alarms.get(i).getCondition() == false){
                            alar.setText(i + " " + "Alarm Work");
                            Server.m.removeFromDB(Server.m.watch.alarms.get(i).getInform());
                        }
                     }
                      
                    /*
                      for(int i = 0; i < Server.m.watch.alarms.size(); i++)
                        {
                        Server.m.watch.alarms.get(i).workedAlarm(Server.m.watch);
                        if(Server.m.watch.alarms.get(i).getCondition() == false){
                            alar.setText(i + " " + "Alarm Work");
                            Server.m.removeFromDB(Server.m.watch.alarms.get(i).getInform());
                        }
                        }
                        */
                        
                        
                        
                        } catch (InterruptedException ex) {
                           f1 = false; 
                        }
                    }
                    Server.m.watch.setTime(0, 0, 0);
                    l.setText(Server.m.watch.getTime());
            }
        };
        t.start();
        }
    }
     
    public void stop() {
        if(t != null) {
            t.interrupt();
            t = null;
            
        }
    }
    public void resume() {
        synchronized(Server.m.watch){
        Server.m.watch.notifyAll();
        }
    }
    
    public void pause() {
        pause = true;
    }
}
