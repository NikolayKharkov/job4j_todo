package servlets;

import store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PerformItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DbStore.instOf().performItem(Integer.valueOf(req.getParameter("id")));
        req.getRequestDispatcher("/getItems").forward(req, resp);
    }
}