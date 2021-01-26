<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='CreateNewProduct' method='POST'>
<label>Enter new Product Name: <input type='text' name='product-name'></input></label>
<label>Enter If Available: <input type='radio' name='product-available' value='false' />No <input type='radio' name='product-available' value='true' />Yes</label>
<input type='submit'>Create Product


</form>
</body>
</html>