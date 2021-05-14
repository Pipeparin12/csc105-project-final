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

@WebServlet(name = "GetBlogInformationServlet", value = "/GetBlogInformationServlet")
@MultipartConfig
public class GetBlogInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            Map<String, Object> map = new HashMap<>();
            int user_id = Middleware.checkAuthentication(request, response);
            int blog_id = Integer.parseInt(request.getParameter("blog_id"));
            BlogQueryModel blogQueryModel = new BlogQueryModel();
            Blog blog = blogQueryModel.getBlogInformation(blog_id);
            response.setStatus(200);
            map.put("blog", blog);
            map.put("Owner", (user_id == blog.getUser_id())?true:false);
            out.print(gson.toJson(map));

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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            int blog_id = Integer.parseInt(request.getParameter("blog_id"));
            String title = request.getParameter("title");
            String image_url = request.getParameter("image_url");
            String content = request.getParameter("content");
            BlogQueryModel blogQueryModel = new BlogQueryModel();
            blogQueryModel.updateBlogInformation(blog_id, title, image_url, content);
            response.setStatus(204);


        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(e.toString(), 500);
            response.setStatus(500);
            out.print(gson.toJson(errorResponse));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        response.setContentType("application/json");
        Middleware.setCORS(request, response);

        try{
            int blog_id = Integer.parseInt(request.getParameter("blog_id"));
            BlogQueryModel blogQueryModel = new BlogQueryModel();
            Blog blog = blogQueryModel.deleteBlog(blog_id);
            response.setStatus(200);

        }catch (Exception e){
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
