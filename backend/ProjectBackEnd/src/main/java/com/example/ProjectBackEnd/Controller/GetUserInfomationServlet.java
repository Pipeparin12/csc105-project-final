package com.example.ProjectBackEnd.Controller;

import com.example.ProjectBackEnd.Model.Blog;
import com.example.ProjectBackEnd.Model.BlogQueryModel;
import com.example.ProjectBackEnd.Model.ErrorResponse;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "GetUserInfomationServlet", value = "/GetUserInfomationServlet")
public class GetUserInfomationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            int user_id = Middleware.checkAuthentication(request, response);
            BlogQueryModel blogQueryModel = new BlogQueryModel();
            blogQueryModel.getAuthorInformation(user_id);
            response.setStatus(200);
            out.print(gson.toJson(blogQueryModel));

        }catch (Exception | ErrorResponse e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        resp.setStatus(200);
    }
}
