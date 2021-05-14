package com.example.ProjectBackEnd.Controller;

import com.example.ProjectBackEnd.Model.BlogQueryModel;
import com.example.ProjectBackEnd.Model.ErrorResponse;
import com.example.ProjectBackEnd.Model.User;
import com.example.ProjectBackEnd.Model.UserQueryModel;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateBlogServlet", value = "/CreateBlogServlet")
@MultipartConfig
public class CreateBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            String title = request.getParameter("title");
            String image_url = request.getParameter("image_url");
            String content = request.getParameter("content");
            int user_id = Middleware.checkAuthentication(request, response);
            System.out.println("Servlet" + user_id);

            System.out.println(title);
            System.out.println(image_url);
            System.out.println(content);

            BlogQueryModel blogQueryModel = new BlogQueryModel();
            blogQueryModel.createBlog(title, image_url, content, user_id);
            response.setStatus(201);

        }catch (Exception | ErrorResponse e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Middleware.setCORS(req, resp);
        resp.setStatus(200);
    }
}
