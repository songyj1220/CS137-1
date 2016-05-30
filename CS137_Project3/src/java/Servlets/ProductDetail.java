package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jessicazeng1127
 */

import Beans.Product;
import Services.Products;
import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;




public class ProductDetail extends HttpServlet{

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       int pid = 111111;
        //int pid = request.getParameter("productid");
       
        
       HttpSession session = request.getSession(true);
       ServletContext servContext =  getServletConfig().getServletContext();
       
        Product product = null;
        try{
            product = Products.getProductsByID(pid);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
       session.setAttribute("current_product_detail",product);
       

    //if the user has not viewed any product or has not viewed this product
       if (session.getAttribute("last_product_detail") == null ||session.getAttribute("last_product_detail") != session.getAttribute("current_product_detail")){
            
      //get context object
       
        HashMap users =  (HashMap) servContext.getAttribute("users"); 
        
        //create hashmap
        if (users == null){
            
            users = new HashMap();
           
        }
        
        //the first user of this product
        else if (users.get(pid) == null ){
                
               users.put(pid,1);
               request.setAttribute("customers",1);
        }
        
        //add user number of the product
        else{
            int val;
            val = (Integer)users.get(pid);
            users.put(pid,val+1);
            request.setAttribute("customers",val+1);
        }
        // update the hashmap in context object
       
        servContext.setAttribute("users", users);
       }
        
        
       //record the last product this user view
       session.setAttribute("last_product_detail",product);
       
       //int number = request.setParameter("pid");
        
    
       
       
        request.getRequestDispatcher("/WEB-INF/jsp/Header.jsp").include(request, response);
        request.getRequestDispatcher("/WEB-INF/jsp/ProductDetail.jsp").include(request, response);
        request.getRequestDispatcher("/WEB-INF/jsp/Footer.jsp").include(request, response);
       
   
        
        
    }
}

