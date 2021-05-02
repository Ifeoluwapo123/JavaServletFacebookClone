package com.example.FacebookClone.controller;

import com.example.FacebookClone.DOA.UserDatabase;
import com.example.FacebookClone.dbConnectionProvider.DbConnection;
import com.example.FacebookClone.model.User;
import com.example.FacebookClone.utils.PasswordHashing;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Servlet Registration example" + "</h1>");
        out.println("</body></html>");

        HttpSession httpSession = request.getSession();

        //fetch data from registration page
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String numEmail = request.getParameter("numEmail");
        String password = request.getParameter("password");
        String dob = request.getParameter("date");
        String gender = request.getParameter("gender");

        //check length of data
        if (firstname.length() < 3) {
            httpSession.setAttribute("Registration Error", " firstname cannot be less than 3 character long");
            response.sendRedirect("index.jsp");
            return;
        }

        if (surname.length() < 3) {
            httpSession.setAttribute("Registration Error", "surname cannot be less than 3 character long");
            response.sendRedirect("index.jsp");
            return;
        }


        if(password.length() < 7){
            httpSession.setAttribute("Registration Error", "password cannot be less than 7 character long");
            response.sendRedirect("index.jsp");
            return;
        }

        //Password encryption
        password = PasswordHashing.encryptPassword(password);


        System.out.println("Firstname "+firstname);
        System.out.println("surname "+surname);
        System.out.println("numEmail "+numEmail);
        System.out.println("password "+password);
        System.out.println("dob "+dob);
        System.out.println("gender "+gender);

        User userModel = new User(firstname, surname, numEmail, password, dob, gender);

        UserDatabase regUser = new UserDatabase(DbConnection.getConnection());

        if (!regUser.registerUser(userModel)) {
            String errorMessage = "failed";
            httpSession.setAttribute("Registration Error", errorMessage);
        }else{
            httpSession.setAttribute("Registration Error", "successfully registered");
        }

        response.sendRedirect("index.jsp");
    }
}
