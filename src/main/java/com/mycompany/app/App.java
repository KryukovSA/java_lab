package com.mycompany.app;


public class App 
{
    public static void main( String[] args )
    {
        WatcMain myClock = new WatcMain("rolex", 10, 10, 5000);
        System.out.println(myClock.getInfo());
        myClock.setTime(22, 10);
        System.out.println(myClock.getInfo());
        myClock.doTime(22, 10);//время перевода на 22ч. 10м вперед
        System.out.println(myClock.getInfo());

       
        
        //alarm myalarm1 = new alarm(15, 15);
   
       // System.out.println(myalarm1.getInform());

        myClock.setTime(15, 10);
        myClock.doTime(0, 5);
        
        //myalarm1.workedAlarm(myClock);
       
       
    
    }
}
