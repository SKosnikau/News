package by.htp.ex.controller;

import java.io.IOException;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CommandProvider provider = new CommandProvider();

    public FrontController() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
        }
    }

    protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        Command command = provider.getCommand(commandName);
        command.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWork(request, response);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().clearConnectionQueue();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}