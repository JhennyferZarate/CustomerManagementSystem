package com.mycompany.customermanagementsystem.dao;

import com.mycompany.customermanagementsystem.models.Client;
import com.mysql.cj.util.StringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jhenn
 */
public class ClientDAO {
    
    public Connection connection(){
        String bd = "Client";
        String user = "root";
        String password = "";
        String host = "localhost";
        String port = "3306";
        String driver = "com.mysql.cj.jdbc.Driver";
        String conexionUrl = "jdbc:mysql://" + host + ":" + port + "/" + bd + "?useSSL=false";
        
        Connection connection = null;
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(conexionUrl, user, password);
            
        } catch (Exception ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;   
    }
    
    public void addClient(Client client){
        try {
            Connection connection = connection();
            
            String sql = "INSERT INTO `clients` (`id`, `dni`, `name`, `lastname`, `email`, `phone`) VALUES (NULL, '"
                    + client.getDNI() + "', '" + client.getName() +"', '" + client.getLastName() + "', '"+client.getEmail()+"', '" + client.getPhone() + "');";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    public List<Client> list(){
        List<Client> list = new ArrayList<>();
        
        try {
            Connection connection = connection();
            
            String sql = "SELECT * FROM `clients`;";
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while(result.next()){
                Client client = new Client();
                client.setId(result.getString("id"));
                client.setDNI(result.getInt("dni"));
                client.setName(result.getString("name"));
                client.setLastName(result.getString("lastname"));
                client.setEmail(result.getString("email"));
                client.setPhone(result.getString("phone"));
                
                list.add(client);  
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public void deleteClient(String id){
        try {
            Connection connection = connection();
            
            String sql = "DELETE FROM `clients` WHERE `clients`.`id` =" + id;
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateClient(Client client){
        try {
            Connection connection = connection();
            
            String sql = "UPDATE `clients` SET `dni` = '"+ client.getDNI()
                    +"', `name` = '" + client.getName()
                    +"', `lastname` = '" + client.getLastName()
                    +"', `email` = '" + client.getEmail()
                    +"', `phone` = '" + client.getPhone()
                    + "' WHERE `clients`.`id` = " + client.getId() +";";
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    public void save(Client client) {
        if(StringUtils.isEmptyOrWhitespaceOnly(client.getId())){
            addClient(client);
        }else{
            updateClient(client);
        }
    }
}
