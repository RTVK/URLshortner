<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TinyUrl</title>
</head>
<body>
  <h1>Tiny Url Generator</h1>
  <hr>

  <h2>Tiny Url : ${tinyUrl}</h2>
  <br>
  <p>Link to navigate: <a href="${tinyUrl}"> Link to navigate: <c:out value="Tiny Url" /> </a></p>
</body>
</html>