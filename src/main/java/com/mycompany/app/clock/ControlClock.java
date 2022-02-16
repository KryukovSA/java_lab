package com.mycompany.app.clock;
import com.mycompany.app.server.Server;

import javax.swing.JLabel;
/**
 *
 * @author HP-PC
 */
public class ControlClock {
    WatchWithSeconds clock1;
    Thread t;
    boolean f1 = false;
    boolean pause = false;
    public JLabel l;
    public JLabel alar;

    public ControlClock(WatchWithSeconds clock) {
        clock1 = clock;
    }
     
    public void start() {
        Server.m.getWatch().setTime(Global.h1, Global.m1, Global.s1);
        if(t == null){
            t = new Thread() {
                @Override
                public void run() {
                    f1 = true;
                    while(f1){
                    //действия с часами
                    try {
                        if(pause) {
                            synchronized(Server.m.getWatch()){
                                l.setText("pause");
                                Server.m.getWatch().wait();
                            }
                            pause = false;
                        }
                        Server.m.getWatch().tick();
                        Thread.sleep(1000);
                        l.setText(Server.m.getWatch().getTime());
                        //проверка на срабатывание
                        //загрузка будильников из БД
                        for(int i = 0; i < Server.m.getInfoFromDB().size(); i++)
                        {
                            Alarm tmpalarm = Server.m.getWatch().getALarmFromStr(Server.m.getInfoFromDB().get(i));
                            System.out.println(tmpalarm.getInform());
                          
                            Server.m.getWatch().alarms.add(tmpalarm);
                        }          
                        for(int i = 0; i < Server.m.getWatch().alarms.size(); i++){
                            Server.m.getWatch().alarms.get(i).workedAlarm(Server.m.getWatch());
                            if(Server.m.getWatch().alarms.get(i).getCondition() == false){
                            alar.setText(i + " " + "Alarm Work");
                            Server.m.removeFromDB(Server.m.getWatch().alarms.get(i).getInform());
                        }
                        }
                    } catch (InterruptedException ex) {
                        f1 = false;
                    }
                    }
                    Server.m.getWatch().setTime(0, 0, 0);
                    l.setText(Server.m.getWatch().getTime());
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
        synchronized(Server.m.getWatch()){
        Server.m.getWatch().notifyAll();
        }
    }
    
    public void pause() {
        pause = true;
    }
}
