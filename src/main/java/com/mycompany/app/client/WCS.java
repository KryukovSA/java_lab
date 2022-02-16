package com.mycompany.app.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mycompany.app.Interfaces.IObserver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mycompany.app.server.Model;
import com.mycompany.app.server.Resp;
import com.mycompany.app.clock.Alarm;

import java.util.ArrayList;

/**
 *
 * @author HP-PC
 */
//для взаимодействия с клиентом
public class WCS extends Thread implements IObserver {
    Socket cs;
    Model mod;
    
    InputStream is; 
    OutputStream os ;
    DataInputStream dis ;
    DataOutputStream dos;
   
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
    
    public WCS(Socket cs, Model mod) { //модель связывает сокеты м-ду собой
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
        ArrayList<String> tmp = new ArrayList<>();
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
                Alarm a = new Alarm(msg.getH(), msg.getM(), msg.getS());
                mod.addToDB(a.getInform());
            }
        } catch (IOException ex) {
            Logger.getLogger(WCS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //наблюдатели оповощены об изменении модели и начнут выполнять действия ниже
    @Override
    public void update(Model m) {
        send(m.lastDB());
    }
}
