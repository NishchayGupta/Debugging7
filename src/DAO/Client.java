/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import debugging7.FileReader;
import debugging7.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 1895314
 */
public class Client {
            Connection con = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            private JSONObject mainObj;
            private JSONArray clientArr;
            int noClient;
            String nameClient, telephone;
    public Client(){
            
        try {        
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "sales", "anypw");
        } catch (SQLException ex) {
            System.out.println("In catch of constructor");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        
            
    public void insertClient(int noClient, String nameClient, String telePhone) throws IOException {
        System.out.println("Inside insertClient method");
                try {
                    String sql;
                    
                    sql = "insert into client values(?, ?, ?)";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, noClient);
                    stm.setString(2, nameClient);
                    stm.setString(3, telePhone);
                    
                    int rs1 = stm.executeUpdate();
                    //System.out.println(rs1);
                    mainObj = new JSONObject();
                    mainObj.accumulate("message", "Successfully inserted");
                    System.out.println("I am in try");
                } catch (SQLException ex) {
                    System.out.println("I am in catch");
                    mainObj.accumulate("message", "Error inserting");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        //Write the json object as a string in json file
                        FileWriter.saveStringIntoFile("json/clientInsert.json", mainObj.toString());
                        mainObj.clear();
                        //Read the json file
                        String json = FileReader.loadFileIntoString("json/clientInsert.json", "UTF-8");
                        JSONObject myObj = JSONObject.fromObject(json);
                        System.out.println(myObj);
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public void updateClient(String nameClient, int noClient) throws IOException
    {
                try {
                    String sql;
                    sql = "update client set nameclient =? where noclient=?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, nameClient);
                    stm.setInt(2, noClient);
                    
                    int rs2 = stm.executeUpdate();
                    //System.out.println(rs2);
                    mainObj = new JSONObject();
                    mainObj.accumulate("message", "Successfully updated");
                } catch (SQLException ex) {
                    mainObj.accumulate("message", "Error updating");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {                      
                        //Write the json object as a string in json file
                        FileWriter.saveStringIntoFile("json/clientUpdate.json", mainObj.toString());
                        mainObj.clear();
                        //Read the json file
                        String json = FileReader.loadFileIntoString("json/clientUpdate.json", "UTF-8");
                        JSONObject myObj = JSONObject.fromObject(json);
                        System.out.println(myObj);
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void deleteClient(int ID_CLIENT) throws IOException {
                try {
                    String sql;
                    sql = "delete from client where noclient =?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, ID_CLIENT);
                    
                    int rs3 = stm.executeUpdate();
                   // System.out.println(rs3);
                    mainObj = new JSONObject();
                    mainObj.accumulate("message", "Successfully deleted");
                } catch (SQLException ex) {
                    mainObj.accumulate("message", "Error deleting");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {                        
                        //Write the json object as a string in json file
                        FileWriter.saveStringIntoFile("json/clientDelete.json", mainObj.toString());
                        mainObj.clear();
                        //Read the json file
                        String json = FileReader.loadFileIntoString("json/clientDelete.json", "UTF-8");
                        JSONObject myObj = JSONObject.fromObject(json);
                        System.out.println(myObj);
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void listClient() throws IOException {
        System.out.println("Inside listClient method");
        ResultSet rs = null;
                try {
                    String sql;
                    clientArr = new JSONArray();
                    //JSONObject client = new JSONObject();
                    sql = "select * from client";
                    // to show that this statement has parameter
                    stm = con.prepareStatement(sql);
                    rs = stm.executeQuery();
                    while(rs.next())
                    {
                         mainObj = new JSONObject();
                         noClient = rs.getInt(1);
                         nameClient = rs.getString(2);
                         telephone = rs.getString(3);
                         mainObj.accumulate("noClient", noClient);
                         mainObj.accumulate("nameClient", nameClient);
                         mainObj.accumulate("noTelephone", telephone);
                         clientArr.add(mainObj);
                    }
                    //System.out.println(rs);
                } catch (SQLException ex) {
                    mainObj.accumulate("message", "Error");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    try {
                        //Write the json object as a string in json file
                         FileWriter.saveStringIntoFile("json/clientList.json", clientArr.toString());
                        mainObj.clear();
                        //Read the json file
                        String json = FileReader.loadFileIntoString("json/clientList.json", "UTF-8");
                        JSONArray myArr = JSONArray.fromObject(json);
                        System.out.println(myArr);
                        //mainObj.clear();
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }

    public void singleClient(int NO_CLIENT) throws IOException {
        try {
                    String sql;
                    sql = "select * from client where noclient=?";
                    // to show that this statement has parameter
                       stm = con.prepareStatement(sql);
                       stm.setInt(1, NO_CLIENT);
                    rs = stm.executeQuery();
                    //System.out.println(rs);
                    while(rs.next())
                    {
                         noClient = rs.getInt(1);
                         nameClient = rs.getString(2);
                         telephone = rs.getString(3);
                         mainObj.accumulate("noClient", noClient);
                         mainObj.accumulate("nameClient", nameClient);
                         mainObj.accumulate("noTelephone", telephone);
                         mainObj.clear();
                    }
                } catch (SQLException ex) {
                    mainObj.accumulate("message", "Error");
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
        finally{
                    try {
                        //Write the json object as a string in json file
                        FileWriter.saveStringIntoFile("json/clientSingleList.json", mainObj.toString());
                        mainObj.clear();
                        //Read the json file
                        String json = FileReader.loadFileIntoString("json/clientSingleList.json", "UTF-8");
                        mainObj = JSONObject.fromObject(json);
                        System.out.println(mainObj);
                    /*    noClient = mainObj.getInt("noClient");
                        nameClient = mainObj.getString("nameClient");
                        telephone = mainObj.getString("noTelephone");                           
                        System.out.println("{\n noClient: " + noClient + "\n nameClient: " + nameClient + "\n notelephone: " + telephone + "\n}");
                     */ mainObj.clear();
                        rs.close();
                        con.close();
                        stm.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
}