/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1895314
 */
public class Client {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
    public Client(){
            
        try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        
            
    public void insertClient(int noClient, String nameClient, String telePhone) {
                try {
                    String sql;
                    
                    sql = "insert into client values(?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, noClient);
                    stm.setString(2, nameClient);
                    stm.setString(3, telePhone);
                    
                    int rs1 = stm.executeUpdate();
                    System.out.println(rs1);
                } catch (SQLException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }  
    
    public void updateClient(String nameClient, int noClient)
    {
                try {
                    String sql;
                    sql = "update client set name_client =? where noclient=?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, nameClient);
                    stm.setInt(2, noClient);
                    
                    int rs2 = stm.executeUpdate();
                    System.out.println(rs2);
                } catch (SQLException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void deleteClient(int ID_CLIENT) {
                try {
                    String sql;
                    sql = "delete from client where noclient =?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, ID_CLIENT);
                    
                    int rs3 = stm.executeUpdate();
                    System.out.println(rs3);
                } catch (SQLException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void listClient() {
                try {
                    String sql;
                    sql = "select * from client";
                    // to show that this statement has parameter

                    rs = stm.executeQuery(sql);
                    System.out.println(rs);
                   
                } catch (SQLException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void singleClient(int NO_CLIENT) {
        try {
                    String sql;
                    sql = "select * from client where noclient=?";
                    // to show that this statement has parameter
                       stm = con.prepareStatement(sql);
                       stm.setInt(1, NO_CLIENT);
                    rs = stm.executeQuery();
                    System.out.println(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        finally{
                    try {
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
}