<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>

<%@ page import="eStoreProduct.utility.ProductStockPrice" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Description</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .product-image {
        text-align: center;
        margin-bottom: 20px;
    }

    .product-image img {
        max-width: 100%;
        max-height: 400px;
    }

    .product-details {
        text-align: center;
    }

    .product-name {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 10px;
    }

    .product-description {
        margin-bottom: 20px;
    }

    .product-price {
        font-size: 20px;
        margin-bottom: 20px;
    }
</style>
</head>

<%
ProductStockPrice product = (ProductStockPrice)request.getAttribute("oneproduct");
	//ProdStockDAO ps = new ProdStockDAOImp();
%>

<div class="container">
    <h2>Product Description</h2>
    <div class="product-image">
        <img src="<%= product.getImage_url()%>" alt="<%= product.getProd_title() %>">
    </div>
    <div class="product-details">
       <h5 class="card-title"><%= product.getProd_title() %></h5>
                    <p class="card-text"><%= product.getProd_desc() %></p>
                    <p class="card-text"><%= product.getPrice() %></p> 
    </div>
</div>

</body>
</html>                                                                                   