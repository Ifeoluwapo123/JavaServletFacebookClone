<%@ page import="com.example.FacebookClone.model.User" %>
<%@ page import="com.example.FacebookClone.DOA.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.FacebookClone.DOA.PostDatabase" %>
<%@ page import="com.example.FacebookClone.dbConnectionProvider.DbConnection" %><%--
  Created by IntelliJ IDEA.
  User: protek
  Date: 5/4/21
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comment Facebook Post</title>

    <meta charset="utf-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <title>timeline</title>
    <!-- JQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"
    ></script>
</head>
<body>
<%
    String query = request.getQueryString();
    int postId = Integer.parseInt(query.substring(query.indexOf("=")+1));
    PostDatabase postDatabase = new PostDatabase(DbConnection.getConnection());
    List<Comment> commentList = postDatabase.getComments(postId);
%>
<nav style="background: #3b5998" class="navbar navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand" href="#" style="color:#fff;"><h1>Facebook</h1></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" style="color: #fff;" href="/home.jsp"><h3>Home</h3></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%--    <%User user = (User) session.getAttribute("user");%>--%>
<%--        <%--%>
<%--            if(user != null){%>--%>
<%--        <h4 style="color: #fff">Welcome <%= user.getSurname() %></h4>--%>
<%--    <%}--%>
<%--    %>--%>
<section style="margin: 60px auto; width: 70%; border: 2px solid #3b5998; padding: 15px">
    <%
        if(commentList.size() != 0){
            for (Comment comment:commentList) {%>
                <p><%=comment.getUsername() %> </p>
                <h4> <p><%=comment.getTitle() %> </p></h4>
                <p></p>
                <p> <p><%=comment.getComment()%> </p></p>
                <p></p>
                <hr/>
            <%}
        }else{ %>
            <h1>No Comments !!!!</h1>
        <%}
    %>

</section>
<script>
    window.onload = ()=> {
        const params = new URLSearchParams(window.location.search);
        document.getElementById("input").value =  params.get("post");
    }
</script>
</body>
</html>
