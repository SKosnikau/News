package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.controller.AttributsKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToRegistrationPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession getSession = request.getSession(true);
        getSession.setAttribute(AttributsKeys.REG_USER, ConnectionStatus.UNREGISTRED);
        request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
    }
}