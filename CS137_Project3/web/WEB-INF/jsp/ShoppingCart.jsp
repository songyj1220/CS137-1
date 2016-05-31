<%-- 
    Document   : ShoppingCart
    Created on : May 28, 2016, 7:26:53 PM
    Author     : misoo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Beans.*,  Services.*,java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart</title>
</head>
<body>
<%@ include file="Header.jsp"%>

<style>
    section{
        height:600px;
        padding:10px;
    }
    
</style>
<%
    List<CartItem> items = (List<CartItem>) session.getAttribute("current-shopping-cart");

    if (items == null)
        items = new ArrayList<CartItem>();


    String action_cart = request.getContextPath() + "/Cart";
    String action_checkout = request.getContextPath() + "/Checkout";
    String action = request.getContextPath() + "/CheckoutServlet";
%>


<div class="container" style="margin: auto">
    <%
            for (CartItem item : items) {
              
    %>

    <div class="row" style="margin: auto">

            <div class="col-md-4">

                    <label> <a href=""><%=item.getProduct().getName()%></a>
                    </label>
                    <label> $<%=item.getProduct().getPrice()%> </label>
            </div>
             
            <form action="<%=action_cart%>" method="post" class="form-inline col-md-8"
                    role="form">
                    <input type="hidden" name="product"
                            value="<%=item.getProduct().getPid()%>"></input> 
                 
                    <div class="form-group">
                            <input type="number" name="quantity"
                                    value="<%=item.getQuantity()%>" class="form-control" id="year"
                                    placeholder="Quantity"> 
                            <input class="btn btn-success form-control" type="submit" name="action"
                                    value="update"> 
                            <input class="btn btn-danger form-control"
                                    type="submit" name="action" value="remove">
                    </div>
            </form>
    </div>
    <%
            }
    %>
</div>

<!--<div class="container" style="margin: auto">
    <form action="<%=action%>" method="post" class="form-horizontal"
            role="form">

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
</div>-->

    
    
<%
    if (items != null && items.size() > 0) {
%>
<br>
<br>
<div class="container" style="margin:auto">
<div class="row">
<form action="<%=action_checkout%>" method="post" class="form-inline"
    role="form">
    <input class="btn btn-default form-control col-md-offset-3 col-md-6" type="submit"
            value="Checkout">
     		
</form>
</div>
</div>
<%
    } else{
%>

    <div class="container" sytle="margin:auto">

            <div class="row"> 
            <div class="alert alert-info">
                    <strong>Notice: </strong> Shopping Cart is Empty. Please go back and add some items.
            </div>
            </div>

    </div>


<% } %>

<%@ include file="Footer.jsp"%>
</body>
</html>