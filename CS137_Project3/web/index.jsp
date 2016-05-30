<%-- 
    Document   : index
    Created on : May 24, 2016, 12:22:51 AM
    Author     : misoo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Beans.*,  Services.*,java.util.*"%>

<!DOCTYPE html>

<html>
<%@ include file="WEB-INF/jsp/Header.jsp"%> 
<head>
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
        <%
        String path = request.getContextPath()+"/Product&id=";
        List<Product> ViewedProducts = new ArrayList<Product>();
        

        ViewedProducts.add((Product) session.getAttribute("one_prd"));
        if (ViewedProducts.get(0)==null){
            ViewedProducts.remove(0);
        }
        if (ViewedProducts.size()==6){
            ViewedProducts.remove(0);
        }
        if (ViewedProducts.size()==0)
        {
            out.print("<h3> You don't have any recently viewed items.</h3><br>");
        }
        else
        {
            out.print("<h3> Your last viewed items:</h3><br>");
        }
        %>
        
        <div class="main">
        <div class="contents">
            <div align="center">
            
        <%
        for(Product p : ViewedProducts){
        %>
        
        <table width="800px" border="0" cellspacing="0" cellpadding="10">
        <tbody>
            <tr>
                <td width="200" rowspan="13">
                    <div class="poster">
                        <a href="">
                        <img src="<%=p.getImg_url()%>" width="138" height="150">
                        </a>
                    </div>
                    <br>
                    <div class="addToCart">
                        <a href="">
                            <img src="img/add-to-cart.png" alt="add to cart">
                        </a>
                    </div>
                </td>

            </tr>  
            <tr>
                <td style="text-align: left">
                <h4><%=p.getName()%></h4>  
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
    


    

        
<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-danger">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-success">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br>

<div class="container">    
  <div class="row">
    <div class="col-sm-4">
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
    <div class="col-sm-4"> 
      <div class="panel panel-primary">
        <div class="panel-heading">BLACK FRIDAY DEAL</div>
        <div class="panel-body"><img src="http://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image"></div>
        <div class="panel-footer">Buy 50 mobiles and get a gift card</div>
      </div>
    </div>
  </div>
</div><br><br>
        
        
    
    </body>
 <%@ include file="WEB-INF/jsp/Footer.jsp"%>       
</html>


