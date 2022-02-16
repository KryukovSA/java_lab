package com.mycompany.app;

import com.mycompany.app.clock.WatchMain;

public class App
{
    public static void main( String[] args )
    {
        WatchMain myClock = new WatchMain("rolex", 10, 10, 5000);
        System.out.println(myClock.getInfo());
        myClock.setTime(22, 10);
        System.out.println(myClock.getInfo());
        myClock.doTime(22, 10);//время перевода на 22ч. 10м вперед
        System.out.println(myClock.getInfo());
        myClock.setTime(15, 10);
        myClock.doTime(0, 5);
    }
}
