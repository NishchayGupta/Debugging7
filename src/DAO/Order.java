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
public class Order {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            
    public Order(){
     try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
}        
    public void insertOrder(int NO_ORDER, String DATE_ORDER, int NO_CLIENT) {
        try {
                    String sql;
                    
                    sql = "insert into order values(?, to_date(?, 'DD-MM-YYYY'), ?)";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, NO_ORDER);
                    stm.setString(2, DATE_ORDER);
                    stm.setInt(3, NO_CLIENT);
                    
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

    public void updateOrder(int NO_ORDER, String NEW_DATE_ORDER) {
        try {
                    String sql;
                    sql = "update order set dateOrder =to_date(?, 'DD-MM-YYYY') where noClient=?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, NEW_DATE_ORDER);
                    stm.setInt(2, NO_ORDER);
                    
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

    public void deleteOrder(int NO_ORDER) {
     try {
                    String sql;
                    sql = "delete from order where noorder =?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, NO_ORDER);
                    
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

    public void listOrder() {
        try {
                    String sql;
                    sql = "select * from order";
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

    public void singleOrder(int NO_ORDER) {
        try {
                    String sql;
                    sql = "select * from order where noorder=?";
                    // to show that this statement has parameter
                       stm = con.prepareStatement(sql);
                       stm.setInt(1, NO_ORDER);
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