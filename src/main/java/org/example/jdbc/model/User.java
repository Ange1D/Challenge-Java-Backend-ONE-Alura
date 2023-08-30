package org.example.jdbc.model;

import org.example.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private String user;
    private String password;

    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static boolean validateUser(String user , String password){
        ConnectionFactory con = new ConnectionFactory();
        Connection connec = null;
        PreparedStatement state = null;
        ResultSet result = null;

        try {
            connec = con.recuperarConexion();
            state = connec.prepareStatement("SELECT * FROM users WHERE user = ? and password = ?");
            state.setString(1, user);
            state.setString(2, password);
            result = state.executeQuery();

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (result != null)
                    result.close();
                if (state != null);
                state.close();
                if (connec != null);
                connec.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }
}
