package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPage implements Command {
    private static final String DO_NOT_SHOW_NEWS = "not_show";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession getSession = request.getSession(true);
        request.setAttribute(AttributesKeys.SHOW_NEWS, DO_NOT_SHOW_NEWS);
        getSession.setAttribute(AttributesKeys.REG_USER, ConnectionStatus.UNREGISTERED);
        request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
    }
}