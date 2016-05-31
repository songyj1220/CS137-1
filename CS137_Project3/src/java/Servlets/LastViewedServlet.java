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
public class LastViewedServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
 
        HttpSession session = request.getSession(true);
        Integer id = Integer.valueOf(request.getParameter("id"));
        
        Product one_prd=null;
        //List<Product> listOfProducts = null;
        try {
            one_prd = Products.getProductsByID(id);
        } catch (SQLException ex) {
            Logger.getLogger(LastViewedServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        session.setAttribute("one_prd", one_prd);
        RequestDispatcher dispatch =  request.getRequestDispatcher("/index.jsp");
        //RequestDispatcher dispatch =  request.getRequestDispatcher("/WEB-INF/jsp/ProductDetail.jsp");
	dispatch.include(request, response);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//          processRequest(request, response);
    }

  
}

