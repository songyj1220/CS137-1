<%-- 
    Document   : TotalPrice
    Created on : May 29, 2016, 8:26:21 PM
    Author     : misoo
--%>

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

<% 
    String str_zipcode = request.getParameter("zipcode");
    int zipcode= Integer.parseInt(str_zipcode);
%>