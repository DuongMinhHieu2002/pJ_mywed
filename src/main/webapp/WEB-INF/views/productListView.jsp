<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Beans.Product" %>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Product List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Product List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="0" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Code</th>
          <th>Name</th>
          <th>Price</th>
          <th>Edit</th>
          <th>Delete</th>
       </tr>
       <%
    List<Product> productList = (List<Product>) request.getAttribute("productList");
    for (Product product : productList) {
%>
    <tr>
        <td><%= product.getCode() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><a href="editProduct?code=<%= product.getCode() %>">Edit</a></td>
        <td><a href="deleteProduct?code=<%= product.getCode() %>">Delete</a></td>
    </tr>
<%
    }
%>

    </table>
 
    <a href="createProduct1" >Create Product</a>
 
 
 </body>
</html>