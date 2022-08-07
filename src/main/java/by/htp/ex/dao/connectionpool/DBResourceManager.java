package by.htp.ex.dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle =
            ResourceBundle.getBundle("db");
    //получает доступ к файлу db.properties >>>  _java._se._07._connectionpool

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}