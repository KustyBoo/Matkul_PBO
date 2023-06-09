/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asus
 */
class Database {
    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/luxura","root","");
            return connect;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
