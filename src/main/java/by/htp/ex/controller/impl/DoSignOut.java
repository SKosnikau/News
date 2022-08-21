package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSignOut implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(true).setAttribute(UsersRole.USER.getTitle(), ConnectionStatus.NOT_ACTIVE);
        response.sendRedirect(JspPageName.INDEX_PAGE);
    }
}