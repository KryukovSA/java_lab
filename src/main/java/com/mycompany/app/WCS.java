/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author HP-PC
 */
//для взаимодействия с клиентом
public class WCS extends Thread implements Iobserver{
    Socket cs;
    model mod;
    
    InputStream is; 
    OutputStream os ;
    DataInputStream dis ;
    DataOutputStream dos;
   
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    
    public WCS(Socket cs, model mod) { //модель связывает сокеты м-ду собой
          this.cs = cs;
          this.mod = mod;
        try {
          os = cs.getOutputStream();
          dos = new DataOutputStream(os);
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }   
        mod.addObserver(this);
        this.start();
        
        //-------------
        
        ArrayList<String> tmp = new ArrayList<>();
        /*for(int i =0; i< mod.watch.alarms.size(); i++){
           tmp.add(mod.watch.alarms.get(i).getInform());
        }
        allsend(tmp); */
        
        send("клиент starts");
        allsend(mod.getAllAlarmInfo());
        
        
  
        
        }
    
    public void send(String s) {
        try {
            Resp r = new Resp();//чтоб строки со старыми будильниками тоже видны
            r.add(s);
             String s_string = gson.toJson(r);//получаем ответ
                dos.writeUTF(s_string);
           
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete(String s) {
        try {
            Resp r = new Resp();//чтоб строки со старыми будильниками тоже видны
            r.del(s);
             String s_string = gson.toJson(r);//получаем ответ
                dos.writeUTF(s_string);
           
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void allsend(ArrayList<String> aMsg) {
        try {
            Resp r = new Resp();//чтоб строки со старыми будильниками тоже видны
           for(String aMg : aMsg)
            r.add(aMg);
             String s_string = gson.toJson(r);//получаем ответ
             dos.writeUTF(s_string);
         
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
        //проверка информации то что от клиента поступили сообщения
        @Override
        public void run() {
            try {
                is = cs.getInputStream();
                dis = new DataInputStream(is);
                //приходят от пользователей данные с формы и тем самым изменяется модель
                while(true) {
                    
                        String cl_msg = dis.readUTF();
                        ClientMsg msg = gson.fromJson(cl_msg, ClientMsg.class); //appol
                       alarm a = new alarm(msg.getH(), msg.getM(), msg.getS());
                    //mod.add(a);
                     mod.addToDB(a.getInform());
                  
///  mod.watch.alarms.
                  /*  int h, m, s;
                    h = dis.readInt();
                    m = dis.readInt();
                    s = dis.readInt();
                    ClientMsg msg = new ClientMsg(gson.fromJson(Integer.toString(h), int.class ), gson.fromJson(Integer.toString(m), int.class ), gson.fromJson(Integer.toString(s), int.class ));

                    alarm a = new alarm(msg.getH(), msg.getM(), msg.getS());
                    mod.add(a);*/

                 //   mod.watch.setTime(Server.m.watch.getHours(), Server.m.watch.getMinutes(),  Server.m.watch.getSeconds());//добавил
                }


                } catch (IOException ex) {
                  Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
//наблюдатели оповощены об изменении модели
        //и начнут выполнять действия ниже
    @Override
    public void update(model m) {
      /* try {
            dos.writeUTF(mod.last());//отправка клиенту
          /*   /dos.writeInt(m.lastAlarm().getAlarmHour());
            dos.writeInt(m.lastAlarm().getAlarmMinute());
            dos.writeInt(m.lastAlarm().getAlarmSecond());*
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //send(m.last());
        
        send(m.lastDB());
       // delete(m.delAndSeeRemoveAlarm());
    }
        
        
        
    
}
