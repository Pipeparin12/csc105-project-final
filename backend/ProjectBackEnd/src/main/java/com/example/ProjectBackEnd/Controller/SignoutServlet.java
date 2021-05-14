package com.example.ProjectBackEnd.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignoutServlet", value = "/SignoutServlet")
public class SignoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Middleware.setCORS(request, response);
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
            response.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
