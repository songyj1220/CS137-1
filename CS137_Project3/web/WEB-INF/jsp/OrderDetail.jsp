<%-- 
    Document   : OrderDetail
    Created on : May 29, 2016, 12:15:55 PM
    Author     : misoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Beans.*,  Services.*,java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
    </head>
    <body>
       <%@ include file="Header.jsp"%>
       
       <%
        List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
       
              
        %>
        <div class="container" style="margin: auto">
        <h3>Order Detail</h3>
        
        <%
            for (CartItem item : items) {
        %>        
       
                <div class="row" style="margin: auto">

                    <div class="col-md-4">

                        <label> <%=item.getProduct().getName()%> </label>
                        <label> $<%=item.getProduct().getPrice()%> </label>
                    </div>
                    <div class="col-md-2">
                        
                    </div>
                </div>
        
        <%}%>
       <%@ include file="Footer.jsp"%>
    </body>
</html>
