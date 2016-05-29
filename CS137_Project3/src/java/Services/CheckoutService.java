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
/**
 *
 * @author misoo
 */
public class CheckoutService {
//   public static boolean processSale(List<CartItem> items ){
//		Connection dbcon = null;
//		try{
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			dbcon = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASS);
//			dbcon.setAutoCommit(false);
//			Date saleDate = new Date();
//			
//			if(items == null)
//				throw new Exception("Customer or items is null");
//			
//			String preparedQuery = "INSERT into sales "
//					+ "(customer_id, movie_id, sale_date)"
//					+ " values (?,?,?)";
//			
//			for(int i=0;i<items.size();i++){
//				int qty = items.get(i).getQuantity();
//				for(int j=0;j<qty;j++){
//					PreparedStatement statement = dbcon.prepareStatement(preparedQuery);
//					Product curMovie = items.get(i).getMovie();
//					statement.setString(1, customer.getID()+"");
//					statement.setString(2, curMovie.getID()+"");
//					java.sql.Date sqlDate = new java.sql.Date(saleDate.getTime());
//					statement.setDate(3, sqlDate);
//					
//					int returnStatus = statement.executeUpdate();
//					statement.close();
//					System.out.println(returnStatus);
//				}
//			}
//			System.out.println("Before commit");
//			dbcon.commit();
//			System.out.println("Succesful commit");
//			dbcon.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//			if(dbcon != null){
//				try {
//					System.out.println("Failed to insert rollingback");
//					dbcon.rollback();
//				} catch (SQLException e1) {
//					System.out.println("Failed to rollback\n");
//					e1.printStackTrace();
//				}
//			}
//			else
//				System.out.println("Connection was null");
//			return false;
//		}
//		return true;
//	}
}
