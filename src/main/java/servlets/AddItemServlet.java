package servlets;

import models.Item;
import models.User;
import store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = (User) req.getSession().getAttribute("user");
        DbStore.instOf().add(new Item(req.getParameter("description"), user));
        req.getRequestDispatcher("/getItems.do").forward(req, resp);
    }
}
