<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.example.FacebookClone.model.User" %><%--
  Created by IntelliJ IDEA.
  User: protek
  Date: 5/2/21
  Time: 8:56 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Facebook Home Page</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");

    if(user == null){
        session.setAttribute("Registration Error", "!!!Please Login first");
        response.sendRedirect("index.jsp");
    }else{%>
        <h1>Hello Facebook <%=user.getFirstname() +" "+ user.getSurname() %></h1>
    <%}
%>
</body>
</html>
