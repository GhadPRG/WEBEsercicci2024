package it.giomrc.altrotest.controller.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class IsLogged implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Sono in IsLogged");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String loginURI = req.getContextPath() +"/views/login.html"; // Gestito da Thymeleaf
        String doLoginURI = req.getContextPath() + "/doLogin"; // Endpoint del login

        boolean loggedIn = (session != null && session.getAttribute("username") != null);
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean isStaticResource = req.getRequestURI().matches(".*(\\.css|\\.js|\\.png|\\.jpg)$");
        boolean loginProcessRequest = req.getRequestURI().equals(doLoginURI);

        if (loggedIn || loginRequest || isStaticResource || loginProcessRequest) {
            chain.doFilter(request, response); // Continua con la richiesta
        } else {
            resp.sendRedirect(loginURI); // Reindirizza alla pagina di login
        }
    }
}