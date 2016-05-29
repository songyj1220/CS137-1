<%-- 
    Document   : Header
    Created on : May 24, 2016, 11:21:48 AM
    Author     : misoo
--%>
<% 	
    String homePath = request.getContextPath();
    String productListPath = homePath + "/Productlist?category=";

  

    //String cartImage = currentPath + "/img/add-to-cart.png";

    String cartPath = homePath+"/Cart";
	
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
      .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */ 
     .jumbotron {
      margin-bottom: 0;
    }

    /* Add a gray background color and some padding to the footer */
   nav .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}
nav .dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}
/*nav .dropdown-content a:hover {background-color: #f1f1f1;  }*/
nav .dropdown:hover .dropdown-content {
    display: block;
    overflow:visible;
    z-index:100;
}
    
  </style>


<div class="jumbotron">
  <div class="container text-center">
<!--      <img src="img/banner1.jpg" alt="Image" width="100%" height="300px">-->
    <h1>Best Electronics</h1>      
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">BEST Electronics</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="<%=homePath%>">Home</a></li>
        <li><a href="#">About</a></li>
        <li class="dropdown">
        <a href="<%=productListPath+"cellphone"%>" class="dropbtn">Products</a>
        <div class="dropdown-content">
          <a href="<%=productListPath+"cellphone"%>">Cellphone</a>
          <a href="<%=productListPath+"laptop"%>">Laptop</a>
          <a href="<%=productListPath+"tablet"%>">Tablet</a>
        </div>
        </li>
        <li><a href="#">Contact</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<%=cartPath%>"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
      </ul>
    </div>
  </div>
</nav>

        
    
    
    
    
    
    
    
    
    
    
    
    