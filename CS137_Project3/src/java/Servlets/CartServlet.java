/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Beans.*;
import Services.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author misoo
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession(true);

        Object currentCart = session.getAttribute("current-shopping-cart");
        List<CartItem> items = null;
        try {
                if(currentCart == null)
                        items = new ArrayList<CartItem>();
                else
                        items = (List<CartItem>) currentCart;


                String action = request.getParameter("action");
                String productID = request.getParameter("product");
                String quantity = request.getParameter("quantity");

                System.out.println("Shopping cart servlet " + action + " " + productID + " " + quantity);
                if(action == null || action.trim().equals("")){
                        RequestDispatcher dispatch =  request.getRequestDispatcher("/WEB-INF/jsp/ShoppingCart.jsp");
                        dispatch.forward(request, response);
                        return;
                }
                else if(action.equalsIgnoreCase("add")){
                        if(productID == null || productID.equals(""))
                                session.setAttribute("error-message", "Specify item to add!");
                        else{
                                int id = Integer.parseInt(productID);
                                int index = -1;
                                for(int i=0;i<items.size();i++)
                                        if(items.get(i).equals(id))
                                                index = i;

                                if(index != -1){
                                        ((CartItem)items.get(index)).addOne();
                                }
                                else
                                {
                                        Product myProduct = Products.getProductsByID(id);
                                        if(myProduct == null)
                                                session.setAttribute("error-message", "item not found with id " + id);
                                        else{
                                                CartItem item = new CartItem(myProduct, 1);
                                                items.add(item);
                                        }
                                }
                        }
                }
                else if(action.equalsIgnoreCase("update")){
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
                }
                else if(action.equalsIgnoreCase("clear")){
                        System.out.println("Cleared Shopping Cart");
                        items.clear();
                }
                else{
                        session.setAttribute("error-message", "Invalid action: " + action);
                }
        } catch (Exception e) {
                e.printStackTrace();
        }

        session.setAttribute("current-shopping-cart", items);
	//RequestDispatcher dispatch =  request.getRequestDispatcher("/WEB-INF/jsp/ShoppingCart.jsp");
	//dispatch.forward(request, response);
        response.sendRedirect(request.getContextPath() + "/Cart");
       
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
        doGet(request,response);
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
