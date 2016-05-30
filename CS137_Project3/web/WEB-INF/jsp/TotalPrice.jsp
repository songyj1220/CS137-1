<%-- 
    Document   : TotalPrice
    Created on : May 29, 2016, 8:26:21 PM
    Author     : misoo
--%>



<%@page import="java.math.BigDecimal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>total price</p>
    </body>
</html>-->
<%@ page import = "Beans.*,
	Services.*,
	Constants.*,
	java.sql.Connection,
	java.sql.DriverManager,
	java.sql.ResultSet,
	java.sql.SQLException,
	java.sql.Statement,
	java.util.StringTokenizer,
	java.util.ArrayList,
 	java.util.List"
%>
<% 
    String str_zipcode = request.getParameter("zipcode");
    int zipcode= Integer.parseInt(str_zipcode);
    float tax = CheckoutService.getTaxbyZipcode(zipcode);
    
    List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
  
    float sum = 0;
    for (CartItem item : items) {
             sum += (item.getProduct().getPrice() * item.getQuantity());
             
    }
    float taxCalculate = sum * tax;
    float total = sum + (sum * tax);
    BigDecimal taxResult = CheckoutService.round(taxCalculate, 2);
    BigDecimal totalPrice = CheckoutService.round(total, 2);
  
    session.setAttribute("total-price",totalPrice);
    
    out.println("<b>"+"Tax Rate: "+"</b>"+ tax +"</br>");
    out.println("<b>"+"Tax: "+"</b>"+ taxResult +"</br>");
    out.println("<b>"+"Total Price(tax included): "+"</b>"+ "$" + totalPrice+"</br>");
    
%>