package me.lphix.essentialsl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String host = "localhost";
    private String port = "3306";
    private String database = "hellomysql";
    private String username = "root";
    private String password = "";

    private Connection connection;

    public boolean isConnected(){
        return(connection!=null);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        if(isConnected()) return;
        connection = DriverManager.getConnection("jdbc:mysql://" +
                host + ":" + port + "/" + database + "?userSSL=false", username, password);
    }
    public void disconnect(){
        if(!isConnected()) return;
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() {return connection;}
}
