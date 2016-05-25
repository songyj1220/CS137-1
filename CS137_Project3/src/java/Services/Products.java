/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

import Beans.*;
import Constants.MysqlConfig;
import java.sql.SQLException;
/**
 *
 * @author misoo
 */
public class Products {
    public static Product getProductsByID(int id) throws SQLException{
        Product result = null;
        Statement statement = null;
        Connection dbcon = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
            statement = dbcon.createStatement();

            String query = "SELECT * FROM products where product_id = " + id;

            rs = statement.executeQuery(query);
            while(rs.next()){
                    //int pid = rs.getInt("product_id");
                    double price = rs.getDouble("price");
                    String name = rs.getString("product_name");
                    String description = rs.getString("description");
                    String features = rs.getString("features");
                    String sub_feature = rs.getString("sub_feature");
                    String imgURL = rs.getString("image");
                    String category = rs.getString("category");
                  
                    
                    result = new Product(id,price,name,description,features,sub_feature,imgURL,category);
            }

//            rs.close();
//            statement.close();
//            dbcon.close();

        }
        catch(Exception e){
            e.printStackTrace();
            result = null;
	}
        finally {
            rs.close();
            statement.close();
            dbcon.close();
}
        return result;
    }
    
    public static List<Product> getProductsByCategory(String category) throws SQLException{
        List<Product> results = new ArrayList<Product>();
        Statement statement = null;
        Connection dbcon = null;
        ResultSet rs = null;   
        try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
                statement = dbcon.createStatement();
               
                String query = "SELECT * FROM products where category = "+ "'" + category + "'";

                rs = statement.executeQuery(query);
                while(rs.next()){
                    int pid = rs.getInt("product_id");
                    double price = rs.getDouble("price");
                    String name = rs.getString("product_name");
                    String description = rs.getString("description");
                    String features = rs.getString("features");
                    String sub_feature = rs.getString("sub_feature");
                    String imgURL = rs.getString("image");
                    //String category = rs.getString("category");
                    Product cur = new Product(pid,price,name,description,features,sub_feature,imgURL,category);
                    results.add(cur);
                }

                rs.close();
                statement.close();
                dbcon.close();

        }
         catch(Exception e){
            e.printStackTrace();
            results = null;
	}
        finally {
            rs.close();
            statement.close();
            dbcon.close();
        }
      
        return results;
    }

}

