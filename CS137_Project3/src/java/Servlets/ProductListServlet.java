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
import java.sql.SQLException;
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
public class ProductListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession(true);
        String category = request.getParameter("category");
        
        List<Product> listOfProducts = null;
        try {
            listOfProducts = Products.getProductsByCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(ProductListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.setAttribute("listOfProducts", listOfProducts);
        RequestDispatcher dispatch =  request.getRequestDispatcher("/WEB-INF/jsp/ProductList.jsp");
	dispatch.forward(request, response);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//          processRequest(request, response);
    }

  
}
