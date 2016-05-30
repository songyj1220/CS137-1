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

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //HttpSession session = request.getSession(true);
       //List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
       //RequestDispatcher dispatch =  request.getRequestDispatcher("/WEB-INF/jsp/Checkout.jsp");
       //dispatch.forward(request, response);
       PrintWriter out = response.getWriter();
       try{
        HttpSession session = request.getSession(true);
        
        List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
        
        String productID = request.getParameter("product");
        
        String quantity   = request.getParameter("quantity");
        String firstName  = request.getParameter("firstName");
        String lastName   = request.getParameter("lastName");
        
        String email   = request.getParameter("email");
        String address   = request.getParameter("address");
        String city   = request.getParameter("city");
        String state   = request.getParameter("state");
        String str_zipcode   = request.getParameter("zipcode");
        int zipcode= Integer.parseInt(str_zipcode );
        
        Customer customer = new Customer(firstName,lastName,email,address,city,state,zipcode);
        if(customer == null){
           
            session.setAttribute("error-message", "Invalid Customer Information. Try Again.");
            String path = request.getContextPath() + "/Checkout";
            response.sendRedirect(path);
            return;
        }
        else{
                int id = Integer.parseInt(productID);
                int qUpdate = Integer.parseInt(quantity);
                
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
            boolean success = CheckoutService.processSale(items,customer);
            if(success){
                    //out.println("Succsefully processed sale");
                    
                    List<CartItem> copy = new ArrayList<CartItem>();
                    copy = items;
                                     
                    session.setAttribute("saled-items", copy);
                    
                    session.setAttribute("current-shopping-cart", null);

                    String path = request.getContextPath() + "/Orderdetail";
                    //session.setAttribute("last-sale", "success");
                    response.sendRedirect(path);
            }
            else{
                    out.println("Failed in procesing sale");
                    session.setAttribute("error-message","Sale did not go through, try again!");
                    String path = request.getContextPath() + "/Checkout";
                    response.sendRedirect(path);
                    return;

            }
        }
        }
        catch(Exception e){
                out.println("Sale failed to go through, try again!");
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
