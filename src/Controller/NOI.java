/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class NOI {
    public  static Connection conn;
    protected NOI() {
        try {
            conn =  DriverManager.getConnection("jdbc:ucanaccess://C:\\CNPMM\\Quan.accdb");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
