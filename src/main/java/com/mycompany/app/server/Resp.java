package com.mycompany.app.server;

import java.util.ArrayList;
/**
 *
 * @author HP-PC
 */
//ответ сервера клиенту  в виде модели 
public class Resp {
    ArrayList<String> listMsg = new ArrayList<>();

    public void add(String s){
        listMsg.add(s);
    }
    
    public void del(String s) {
        for(String time_ : listMsg){
           if (time_.equals(s)) {
           listMsg.remove(time_);
           }
       }
    }

    public ArrayList<String> getListMsg() {
        return listMsg;
    }
}
