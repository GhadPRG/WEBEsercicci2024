package it.giomrc.altrotest.controller.servlet;

import it.giomrc.altrotest.dao.UserDAO;
import it.giomrc.altrotest.dao.UserDAOImpl;
import it.giomrc.altrotest.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {

    UserService userService;

    boolean DEBUG=true;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        if(DEBUG){
            req.getSession(true).setAttribute("username", "DebugUser"); // Memorizza nella sessione
            resp.setStatus(HttpServletResponse.SC_OK); // Stato HTTP 200
            resp.getWriter().write("{\"message\": \"Login successful\"}");
        }
        else{
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            System.out.println("Nome: "+username+" Password: "+password);

            System.out.println(userService.verifyUser(username, password)?"Nome e Password Corretti":"Nome e Password Non Corretti");

            if(userService.verifyUser(username, password)){            // Login riuscito
                req.getSession(true).setAttribute("username", username); // Memorizza nella sessione
                resp.setStatus(HttpServletResponse.SC_OK); // Stato HTTP 200
                resp.getWriter().write("{\"message\": \"Login successful\"}");
            }
            else{
                // Credenziali errate
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Stato HTTP 401
                resp.getWriter().write("{\"message\": \"Invalid credentials\"}");
            }
        }





    }
}





/*
package com.template.servlet;

import com.template.dao.UserDAOImpl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String pass = request.getParameter("password");



        if(UserDAOImpl.verifyUser(username, pass))
        {
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        }
        else{
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("login.html");
            rs.include(request, response);
        }
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<title>Hello World!</title>");
    }
}
*/