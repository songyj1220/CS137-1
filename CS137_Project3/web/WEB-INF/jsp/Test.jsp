<%-- 
    Document   : Test
    Created on : May 23, 2016, 7:59:39 PM
    Author     : misoo
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


 <%@page import="Services.*, Beans.*,java.sql.*" %>
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
        <h1>Products by category</h1>
        
        <%
        List<Product> products = Products.getProductsByCategory("cellphone");
        if(products == null){
            out.println("products are null");
        }
        else{
     
        for(Product p : products){
        out.println(p.getName());
        out.println(p.getPrice());
       
        out.println("----------------");
    }
        }
 
%>   
    </body>
</html>
