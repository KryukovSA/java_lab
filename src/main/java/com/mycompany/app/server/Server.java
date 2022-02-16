package com.mycompany.app.server;
import com.mycompany.app.client.WCS;
import com.mycompany.app.clock.ControlClock;
import com.mycompany.app.clock.Global;
import com.mycompany.app.clock.WatchWithSeconds;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mycompany.app.utils.Constanse.PORT;

public class Server {
    InetAddress host;
    public static WatchWithSeconds watch = new WatchWithSeconds("rolex", 10, 10, 10, 5000);
    public static Model m = new Model();
    public static ControlClock control = new ControlClock(Server.watch);
    
    public Server() {
        m.setWatch(watch);
        try{
            host = InetAddress.getLocalHost();    
        } catch (UnknownHostException ex){
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try { 
            ServerSocket ss = new ServerSocket(PORT, 0, host);
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
}
