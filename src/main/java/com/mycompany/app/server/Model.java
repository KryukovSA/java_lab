package com.mycompany.app.server;
import com.mycompany.app.Interfaces.IObserver;
import com.mycompany.app.clock.Alarm;
import com.mycompany.app.clock.WatchWithSeconds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP-PC
 */
public class Model {
    ArrayList<IObserver> all_o =  new ArrayList<>();
    WatchWithSeconds watch = Server.watch; //= new Watch_with_seconds("rolex", 10, 10, 10, 5000);
    String deletedTxt;
    Connection c;

    void update(){
        for(IObserver o : all_o){
        o.update(this);
        }
    }

    public void addToDB(String alrm){
        saveAlarmToDB(alrm);
        update();
    }
  
    public void add(Alarm a){
        watch.alarms.add(a);
        update();
    }

//в wcs формировался моссив строк из будильников
    public ArrayList<Alarm> getAll_alrm() {
        return watch.alarms;
    }

//информация о последнем добавленном будильнике для базы данных
    public String lastDB(){
        ArrayList<String> tmp = getInfoFromDB();
        return tmp.get((tmp.size() - 1));
    }

    public String delAndSeeRemoveAlarm() {
        for(Alarm time_ : watch.alarms){
                if (time_.getInform().equals(deletedTxt)) {
                watch.alarms.remove(time_);
                }
        }
        update();
        return deletedTxt;
    }

//информация о последнем добавленном будильнике
    public String last(){
        return watch.alarms.get((watch.alarms.size() - 1)).getInform();
    }

//последний добавленный будильник
    public Alarm lastAlarm(){
        return watch.alarms.get((watch.alarms.size() - 1));
    }

    public void addObserver(IObserver o){
    all_o.add(o);
}

    public void setWatch(WatchWithSeconds watch1) {
        this.watch = watch1;
    }

    public WatchWithSeconds getWatch() {
    return watch;
}

    void connect() {
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(
            "jdbc:sqlite:C:/Users/HP-PC/Downloads/sqlitestudio-3.3.3/SQLiteStudio/alarms.db");
            System.out.println("open succes");
        } catch (ClassNotFoundException ex) {
            System.out.println("нет драйвера");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             System.out.println("проблема с подключением");
        }
    }

//для получения всей информации из бд
    public ArrayList<String> getInfoFromDB() {
        ArrayList<String> resList = new ArrayList<>();
          try {
              Statement st = c.createStatement();
              ResultSet res = st.executeQuery("select * from alarms");
              while(res.next()) {
                  resList.add(res.getString("time"));
              }
          } catch (SQLException ex) {
              Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
          }
          return resList;
    }

    public ArrayList<String> getAllAlarmInfo() {
        return getInfoFromDB();
    }

    void saveAlarmToDB(String s) {
          try {
              PreparedStatement pst =
                      c.prepareStatement("INSERT INTO alarms(time) VALUES (?) ");
          pst.setString(1, s);
          pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    void deleteAlarmFromDB(String s) {
        try {
              PreparedStatement pst =
                      c.prepareStatement("delete from alarms where time = ? ");
          pst.setString(1, s);
          pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    //для удаления будильника
    public void removeFromDB(String alrm){
        deleteAlarmFromDB(alrm);
        update();
    }

    public Model(){
        connect();
    }

    public void setDelTxt(String s) {
        this.deletedTxt = s;
    }
}
