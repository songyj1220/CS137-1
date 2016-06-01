/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.*;
import Services.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author misoo
 */
public class CheckoutServlet extends HttpServlet {

    private String nullify(String s){
        if(s == null || s.isEmpty()){
            s=null;
        }
        return s;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
       PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        
        List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
        
        String action = request.getParameter("action");
        String productID = request.getParameter("product");
        String quantity = request.getParameter("quantity");
    
        try{
            if(action.equalsIgnoreCase("update")){
                int id = Integer.parseInt(productID);
                int qUpdate = Integer.parseInt(quantity);
                if(qUpdate < 0)
                        throw new Exception("Quantity cannot be negative: " + qUpdate);

                int index = -1;
                for(int i=0;i<items.size();i++)
                        if(items.get(i).equals(id))
                                index = i;

                if(index != -1){
                        CartItem item = (CartItem)items.get(index);
                        item.setQuantity(qUpdate);
                        if(qUpdate == 0)
                                items.remove(index);
                }
                
                else{
                        session.setAttribute("error-message", "No product with id " + id + " in the shopping cart!" );
                }
                response.sendRedirect(request.getContextPath() + "/Checkout");
            }
            else if(action.equalsIgnoreCase("remove")){
                    int id = Integer.parseInt(productID);
                    int index = -1;
                    for(int i=0;i<items.size();i++)
                            if(items.get(i).equals(id))
                                    index = i;
                    if(index != -1){
                            items.remove(index);
                    }
                    else{
                            session.setAttribute("error-message", "No product with id " + id + " in the shopping cart!" );
                    }
                    response.sendRedirect(request.getContextPath() + "/Checkout");
            }
            else if(action.equalsIgnoreCase("clear")){
                    System.out.println("Cleared Shopping Cart");
                    items.clear();
            }
            else if(action.equalsIgnoreCase("submit")){
                try {
                       String name = nullify(request.getParameter("name"));

                        String email = nullify(request.getParameter("email"));
                        String address = nullify(request.getParameter("address"));
                        String city = nullify(request.getParameter("city"));
                        String state = nullify(request.getParameter("state"));
                        String str_zipcode = nullify(request.getParameter("zipcode"));
                        int zipcode = Integer.parseInt(str_zipcode);
                        Customer customer = new Customer(name,email,address,city,state,zipcode);
  
                        CheckoutService.InsertCustomer(customer);
                        session.setAttribute("this-customer", customer);
                        // session.setAttribute("current-shopping-cart", items);
                        List<CartItem> copy = new ArrayList<CartItem>();
                        copy = items;
                   
                        session.setAttribute("saled-items", copy);
                        session.setAttribute("current-shopping-cart", null);
                   
                        String path = request.getContextPath() + "/Orderdetail";

                        response.sendRedirect(path);
               } catch (SQLException ex) {
                   Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
            else{
                    session.setAttribute("error-message", "Invalid action: " + action);
            }
        }catch(Exception e) {
                e.printStackTrace();
        }
      

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
