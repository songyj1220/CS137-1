<%-- 
    Document   : Checkout
    Created on : May 28, 2016, 10:53:59 PM
    Author     : misoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Beans.*,  Services.*,java.util.*"%>

<%
    List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");
    if (items == null || items.size() == 0) {
            session.setAttribute("error-message", "Shopping Cart is Empty! Please add at least one item.");
            String action_cart = request.getContextPath() + "/Cart";
            response.sendRedirect(action_cart);
            return;
    }
    String action = request.getContextPath() + "/CheckoutServlet";
    String action_cart = request.getContextPath() + "/Cart";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%@ include file="Header.jsp"%>

    <div class="container" style="margin: auto">

        <form action="<%=action%>" method="post" class="form-horizontal"
                role="form">
        <%
                for (CartItem item : items) {

        %>
        <div class="col-sm-4">
            <label>
                <a href=""><%=item.getProduct().getName()%></a>
            </label>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="firstName">Quantity:</label>
            <div class="col-sm-1">
                <input type="number" name="quantity" value="<%=item.getQuantity()%>" 
                   class="form-control" id="quantity" placeholder="Qnt"> 
            </div>
        </div>
        <%
        }
        %>             
            <div class="form-group">
                    <label class="control-label col-md-2" for="firstName">First Name:</label>
                    <div class="col-md-6">
                            <input type="text" name="firstName" class="form-control"
                                    id="firstName" placeholder="Enter First Name ">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="lastName">Last Name:</label>
                    <div class="col-md-6">
                            <input type="text" name="lastName" class="form-control"
                                    id="lastName" placeholder="Enter Last Name">
                    </div>
            </div>

              <div class="form-group">
                    <label class="control-label col-md-2" for="creditcard">Email: </label>
                    <div class="col-md-6">
                            <input type="text" name="creditCard" class="form-control" id="creditcard"
                                    placeholder="Enter Email Address">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Address: </label>
                    <div class="col-md-6">
                            <input type="text" name="expiration" class="form-control" id="expiration"
                                    placeholder="Enter Address">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">City: </label>
                    <div class="col-md-6">
                            <input type="text" name="expiration" class="form-control" id="expiration"
                                    placeholder="Enter City">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">State: </label>
                    <div class="col-md-6">
                            <input type="text" name="expiration" class="form-control" id="expiration"
                                    placeholder="Enter City">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Zipcode: </label>
                    <div class="col-md-6">
                            <input type="text" name="expiration" class="form-control" id="expiration"
                                    placeholder="Enter Zipcode">
                    </div>
            </div>

            <div class="form-group">
                    <div class="col-md-offset-4 col-md-2">
                            <input class="btn btn-primary form-control" type="submit"
                                    name="action" value="checkout">
                    </div>
            </div>
        </form>
    </div>
    <%@ include file="Footer.jsp"%>       
    </body>
</html>
