package com.example.FacebookClone.controller;

import com.example.FacebookClone.DOA.PostDatabase;
import com.example.FacebookClone.dbConnectionProvider.DbConnection;
import com.example.FacebookClone.model.Post;
import com.example.FacebookClone.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeletePostServlet", value = "/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            int postId = Integer.parseInt(request.getParameter("postId"));

            PostDatabase postDatabase = new PostDatabase(DbConnection.getConnection());

            if(postDatabase.deletePost(postId)){
                response.getWriter().write("Success deleting post");
            }else{
                out.print("500 error");
                response.getWriter().write("Failed do delete post");
            }

            response.sendRedirect("home.jsp");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
