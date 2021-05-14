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
import java.util.List;

@WebServlet(name = "ShowAllBlogServlet", value = "/ShowAllBlogServlet")
@MultipartConfig
public class ShowAllBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            BlogQueryModel blogQueryModel = new BlogQueryModel();
            List<Blog> list = blogQueryModel.showAllBlog();
            response.setStatus(201);
            out.print(gson.toJson(list));

        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
