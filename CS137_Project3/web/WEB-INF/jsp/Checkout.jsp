<%-- 
    Document   : Checkout
    Created on : May 28, 2016, 10:53:59 PM
    Author     : misoo
--%>

<%@page import="java.math.BigDecimal"%>
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
    String pathToPrice = request.getContextPath() + "/TotalPrice?zipcode=";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%@ include file="Header.jsp"%>
    <%
        float sum = 0;
        for (CartItem it : items) {
             sum += (it.getProduct().getPrice() * it.getQuantity());
             
        }
        BigDecimal result;
        result= CheckoutService.round(sum, 2);
    %>
    <div class="container" style="margin: auto">
       	<div class="total" id ="total">
            <b>Total Price:</b> $<%=result%>
        </div>				
        <form action="<%=action%>" method="post" class="form-horizontal"
                role="form">
            
        <%
                for (CartItem item : items) {

        %>
        
        <div class="col-sm-6">
            <label>
                <a href=""><%=item.getProduct().getName()%></a>
                &nbsp; $<%=item.getProduct().getPrice()%>
            </label>
            
        </div>
        
      
         <input type="hidden" name="product"
                            value="<%=item.getProduct().getPid()%>"></input> 
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
                    <label class="control-label col-md-2" for="firstName">Name:</label>
                    <div class="col-md-6">
                            <input type="text" name="name" class="form-control"
                                    id="name" placeholder="Enter First Name ">
                    </div>
            </div>


              <div class="form-group">
                    <label class="control-label col-md-2" for="creditcard">Email: </label>
                    <div class="col-md-6">
                            <input type="text" name="email" class="form-control" id="email"
                                    placeholder="Enter Email Address">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Address: </label>
                    <div class="col-md-6">
                            <input type="text" name="address" class="form-control" id="address"
                                    placeholder="Enter Address">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">City: </label>
                    <div class="col-md-6">
                            <input type="text" name="city" class="form-control" id="city"
                                    placeholder="Enter City">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">State: </label>
                    <div class="col-md-6">
                            <input type="text" name="state" class="form-control" id="state"
                                    placeholder="Enter City">
                    </div>
            </div>

            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Zip Code: </label>
                    <div class="col-md-6">
                            <input type="text" name="zipcode" class="form-control" id="zipcode" onkeyup="calculate(this.value)"
                                    placeholder="Enter Zipcode">
                    </div>
            </div>

            
            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Credit Card: </label>
                    <div class="col-md-6">
                            <input type="text" name="creditCard" class="form-control" id="creditCard"
                                    placeholder="Enter Credit card number">
                    </div>
            </div>
            
            <div class="form-group">
                    <label class="control-label col-md-2" for="expiration">Expiration Date: </label>
                    <div class="col-md-6">
                            <input type="text" name="expiration" class="form-control" id="expiration"
                                    placeholder="Credit Card Expiration Date (YYYY-MM-DD)">
                    </div>
            </div>
            
            <div class="form-group">
                    <div class="col-md-offset-4 col-md-2">
                            <input class="btn btn-primary form-control" type="submit"
                                    name="action" value="submit">
                    </div>
            </div>
        </form>
    </div>
    <script>
    function calculate(zipcode) {
	var ajaxRequest;
	if (zipcode.length == 0) {
		document.getElementById("suggestions").innerHTML = "";
		//document.getElementById("suggestions").setAttribute("style", "display: none;");
		return;
	}
	else {
		try{
			// Opera 8.0+, Firefox, Safari
			ajaxRequest = new XMLHttpRequest();
		} 
		catch (e) {
			// Internet Explorer Browsers
			try{
				ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} 
			catch (e) {
				try{
					ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} 
				catch (e) {
					// Something went wrong
					alert("Update your browser!");
					return false;
				}
			}
		}
		ajaxRequest.onreadystatechange = function() {
			if (ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			
			 		//$("div.suggestionBox").html(ajaxRequest.responseText);
					document.getElementById("total").innerHTML = ajaxRequest.responseText;
					document.getElementById("total").setAttribute("style", "display: block;");
			ajaxRequest.responseType
			}
		}; //end anonymous function
		
		
		ajaxRequest.open("GET", "<%=pathToPrice%>" + zipcode, true);
		ajaxRequest.send();
	}
}
</script>
            
            
            
            
    <%@ include file="Footer.jsp"%>       
    </body>
</html>
