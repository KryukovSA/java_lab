 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.mycompany.app;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP-PC
 */

public class Server {
    int port = 3124;
    InetAddress host;
   // model m = new model();
    static Watch_with_seconds watch = new Watch_with_seconds("rolex", 10, 10, 10, 5000);
    static model m = new model();
    static ControlClock control = new ControlClock(Server.watch);
   

    
    public Server() {
        m.setWatch(watch);
        
        try{
            host = InetAddress.getLocalHost();    
        } catch (UnknownHostException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try { 
            ServerSocket ss = new ServerSocket(port, 0, host);
            System.out.println("server start"); 
            
            int count = 0;
            
            while(true) {
                Socket cs = ss.accept();
                count++;
                System.out.println("new client connect");
                WCS wcs = new WCS(cs, m);//взаимод с каждым в отдельном потоке
                
                  Global.h1 = Server.watch.getHours();
                        Global.m1 = Server.watch.getMinutes();
                        Global.s1 = Server.watch.getSeconds();
            } 
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void main(String[] args) {
        new Server();
    }
    
    
    
}
