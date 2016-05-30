<%-- 
    Document   : ProductList
    Created on : May 24, 2016, 10:41:43 PM
    Author     : misoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Beans.*,  Services.*,java.util.*"%>





<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    <style>
.contents a {
	text-decoration: none;
	outline: none;
}
.contents h4{
    margin:0;
}
.contents p {
	display: inline;
}

.contents hr {
	width: 80%;
	border-width: 1px;
	color: gray;
}

.addToCart img {
	margin-top: 15px;
}
    
</style>
    </head>
    <body>
    <%@ include file="Header.jsp"%>
    
    <%
    String addr = request.getContextPath();
    String pathToProduct = request.getContextPath()+"/ProductDetail?id=";
    String cartToPath = addr + "/Cart?action=add&quantity=1&product=";
    List<Product> products = null;


    products = (List<Product>) session.getAttribute("listOfProducts");
      

    %>   
    <div class="main">
        <div class="contents">
            <div align="center">
<%
        for(Product p : products){
           
%>
    <table width="800px" border="0" cellspacing="0" cellpadding="10">
        <tbody>
            <tr>
                <td width="200" rowspan="13">
                    <div class="poster">
                        <a href="<%=pathToProduct+p.getPid()%>">
                        <img src="<%=p.getImg_url()%>" width="138" height="150">
                        </a>
                    </div>
                    <br>
                    <div class="addToCart">
                        <a href=<%=cartToPath + p.getPid()%>>
                            <img src="img/add-to-cart.png" alt="add to cart">
                        </a>
                    </div>
                </td>

            </tr>  
            <tr>
                <td style="text-align: left">
                <a href="<%=pathToProduct+p.getPid()%>">
                <h4><%=p.getName()%></h4>  
                </a>
                </td>

            </tr>
            <tr>
                <td style="text-align: left">
                <h4>$<%=p.getPrice()%></h4>  
                </td>
            </tr>
            <tr>
               <td style="text-align: left">
                <p><%=p.getSub_feature()%></p>  
                </td>
            </tr>
        </tbody>
    </table>
 <hr>
<%}%>
            </div>
        </div>
    </div>  
    </body>
</html>
<%@ include file="Footer.jsp"%>