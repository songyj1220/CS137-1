<%-- 
    Document   : Test
    Created on : May 23, 2016, 7:59:39 PM
    Author     : misoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <%@page import="Services.*, Beans.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TEST</title>
    </head>
    <body>
        <h1>Product by ID</h1>
        <%
        Product product = null;
		try {

                    product = (Product) session.getAttribute("this-product");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 	//out.println(product.getImg_url());
        out.println(product.getPid());
        out.println(product.getName());
        out.println(product.getPrice());
        out.println(product.getFeature());
	
        %>
        
    </body>
</html>
