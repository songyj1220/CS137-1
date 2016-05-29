/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

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
public class Test extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String category = request.getParameter("category");
        
        List<Product> products = null;
       
        try {
            products = Products.getProductsByCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Test</title>");  
            out.println("<style>");
            out.println(".contents a {\n" +
"	text-decoration: none;\n" +
"	outline: none;\n" +
"}\n" +
".contents h4{\n" +
"    margin:0;\n" +
"}\n" +
".contents p {\n" +
"	display: inline;\n" +
"}\n" +
"\n" +
".contents hr {\n" +
"	width: 80%;\n" +
"	border-width: 1px;\n" +
"	color: gray;\n" +
"}\n" +
"\n" +
".addToCart img {\n" +
"	margin-top: 15px;\n" +
"}\n" +
"    ");

out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Test at " + request.getContextPath() + "</h1>");
              
    //products = (List<Product>) session.getAttribute("listOfProducts");
      

    out.println("<div class=\"main\">");
    out.println("<div class=\"contents\">");
    out.println("<div align=\"center\">");
                 
    for(Product p : products){
    out.println("<table width=\"800px\" border=\"0\" cellspacing=\"0\" cellpadding=\"10\">");
    out.println("<tbody>");
    out.println("<tr>");
    out.println("<td width=\"200\" rowspan=\"13\">");
    out.println("<div class=\"poster\">");
    out.println("<a href=\"\">");         
    out.println("<img src="+p.getImg_url()+" width=\"138\" height=\"150\">");
    out.println("</a>");
    out.println("</div>");
    out.println("<br>");
    out.println("<div class=\"addToCart\">");
    out.println("<a href=\"\">");
    out.println("<img src=\"img/add-to-cart.png\" alt=\"add to cart\">");
    out.println("</a>");
    out.println("</div>");
    out.println("</td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td style=\"text-align: left\">"
            + "<h4>"+p.getName()+"</h4> "
            + "</td>");
    out.println("</tr>");
    out.println("<tr>\n"
            +"<td style=\"text-align: left\">\n"
            +"<h4>"+p.getPrice()+"</h4>\n"
            +"</td>\n" +"</tr>\n");
   
    out.println("<tr>\n" 
            +"<td style=\"text-align: left\">\n" 
            +"<h4>"+p.getSub_feature()+"</h4>\n" 
            +"</td>\n" 
            +"</tr>");
    out.println("</tbody>");
    out.println("</table>");
    out.println("<hr>");
    }
    out.println("</div>");
    out.println("</div>");
    out.println("</div>");
  
    out.println("</body>");
    out.println("</html>");
    out.println("<%@ include file=\"Footer.jsp\"%>");
            
            

        } finally {
            out.close();
        }
    }

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
       processRequest(request, response);
       
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
        processRequest(request, response);
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
