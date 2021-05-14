package com.example.ProjectBackEnd.Controller;

import com.example.ProjectBackEnd.Model.ErrorResponse;
import com.example.ProjectBackEnd.Model.User;
import com.example.ProjectBackEnd.Model.UserQueryModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignupServlet", value = "/SignupServlet")
@MultipartConfig

public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);
//        response.setStatus(200);
//        out.print("hello: \"test\" ");
        try {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(firstname + lastname + username + password);
            if(firstname == null || lastname == null || username == null || password == null) {
                ErrorResponse errorResponse = new ErrorResponse("Need to filled all blank", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
//                out.print("hello: \"test\" ");
                return;
            }
            System.out.println("case 1");

            UserQueryModel userQueryModel = new UserQueryModel();
            User existingUser = userQueryModel.getUser(username);
            if (existingUser != null) {
                ErrorResponse errorResponse = new ErrorResponse("Username " + username + " is used", 400);
                response.setStatus(400);
                out.print(gson.toJson(errorResponse));
//                out.print("hello: \"test\" ");
                return;
            }
            System.out.println("case 2");

            User user = userQueryModel.insetNewUser(firstname, lastname, username, password);
            String jsonOfUser = gson.toJson(user);
            response.setStatus(201);
            request.getSession(true);
            out.print(jsonOfUser);
            System.out.println("case 3");
//            out.print("hello: \"test\" ");
        } catch (Exception e) {
            System.out.println("case 4");
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
//            out.print("hello: \"test\" ");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        super.doOptions(req, resp);
    }
}


