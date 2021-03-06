package servlets;

import store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetAllNotPerformItems extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("items", new ArrayList<>(DbStore.instOf().findAllNotPerformed()));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}