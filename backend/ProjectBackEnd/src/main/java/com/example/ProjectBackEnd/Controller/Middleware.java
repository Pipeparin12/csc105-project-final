package com.example.ProjectBackEnd.Controller;

import com.example.ProjectBackEnd.Model.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Middleware {
    public static int checkAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException, ErrorResponse {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ErrorResponse("Unauthorized", 401);
        }
        String user_Id = session.getAttribute("user_id").toString();
        System.out.println("Middleware" + user_Id);
        return Integer.parseInt(user_Id);
    }

    public static void setCORS(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length");
    }
}

