package it.giomrc.altrotest.controller.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false); // Ottieni la sessione esistente, se c'è
        if (session != null) {
            session.invalidate(); // Invalida la sessione
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/login.html");
        dispatcher.forward(req, resp);// Reindirizza al login
    }
}