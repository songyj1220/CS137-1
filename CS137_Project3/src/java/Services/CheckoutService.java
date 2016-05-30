/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Beans.*;
import Services.*;
import Constants.*;
import java.math.BigDecimal;
/**
 *
 * @author misoo
 */
public class CheckoutService {
    
    public static float getTaxbyZipcode(int zipcode) throws SQLException{
        float tax = 0;
        Statement statement = null;
        Connection dbcon = null;
        ResultSet rs = null;   
        try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
                statement = dbcon.createStatement();
               
                String query = "SELECT tax_rate FROM tax where zip_code = " + zipcode;

                rs = statement.executeQuery(query);
                while(rs.next()){
                  tax = rs.getFloat("tax_rate");
                   
                }

                rs.close();
                statement.close();
                dbcon.close();

        }
         catch(Exception e){
            e.printStackTrace();
            
	}
        finally {
            rs.close();
            statement.close();
            dbcon.close();
        }
      
        return tax;
    }

   public static boolean processSale(List<CartItem> items, Customer customer ) throws SQLException{
    Connection dbcon = null;
    PreparedStatement statement = null;
    try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
            dbcon.setAutoCommit(false);
            Date saleDate = new Date();
           
            if(items == null)
                    throw new Exception("Customer or items is null");
            //INSERT INTO `order`( `productID`, `firstName`, `lastName`, `email`, `address`, `city`, `state`, `zipcode`, `date`, `total_price`) VALUES (123,'a','a','a','a','a','a',123456,1,123.11)
            String preparedQuery = "INSERT INTO 'order' "
                            + "( 'productID', 'qnt', 'firstName', 'lastName', 'email', 'address', 'city', 'state', 'zipcode', 'date')"
                            + " VALUES (?,?,?,?,?,?,?,?,?,?)";

            for(int i=0;i<items.size();i++){
                 
                  
                            statement = dbcon.prepareStatement(preparedQuery);
                            Product curItem = items.get(i).getProduct();
                            statement.setString(1, curItem.getPid()+"");
                            statement.setString(2, items.get(i).getQuantity()+"");
                            statement.setString(3, customer.getFirst_name());
                            statement.setString(4, customer.getLast_name());
                            statement.setString(5, customer.getEmail());
                            statement.setString(6, customer.getAddress());
                            statement.setString(7, customer.getCity());
                            statement.setString(8, customer.getState());
                            statement.setInt(9, customer.getZipcode());

                            java.sql.Date sqlDate = new java.sql.Date(saleDate.getTime());
                            statement.setDate(10, sqlDate);


                            statement.executeUpdate();
                            dbcon.commit();

                    
            }


        }
	catch (Exception e ) {
           e.printStackTrace();
            if (dbcon != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    dbcon.rollback();
                } catch(SQLException excep) {
                    excep.printStackTrace();
                }
            }
            else
                return false;
        } finally {
            if (statement != null) {
                statement.close();
            }

            dbcon.setAutoCommit(true);
        }
    
        return true;  
    }
    
//    public static boolean processSale(List<CartItem> items, Customer customer ) throws SQLException{
//        //List<Product> results = new ArrayList<Product>();
//        boolean result = false;
//        Statement statement = null;
//        Connection dbcon = null;
//        ResultSet rs = null;   
//        try{
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
//                statement = dbcon.createStatement();
//               
//                String query = "SELECT * FROM products where category = "+ "'" + category + "'";
//                for(int i=0;i<items.size();i++){
//                int qty = items.get(i).getQuantity();
//                String preparedQuery = "INSERT INTO 'order'" 
//                           + "( `productID`,`qnt`, `firstName`, `lastName`, `email`, `address`, `city`, `state`, `zipcode`, `date`)"
//                            + " VALUES ("+"'"+items.get(i).getQuantity()+"'"+","+"'"
//                        + ",?,?,?,?,?,?,?,?,?)";    
//                            
//                }
//              
//        }
//         catch(Exception e){
//            e.printStackTrace();
//            return result;
//	}
//        finally {
//            rs.close();
//            statement.close();
//            dbcon.close();
//        }
//      
//        result = true;
//        return result;
//    }
    
}
