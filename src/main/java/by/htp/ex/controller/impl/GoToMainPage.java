package by.htp.ex.controller.impl;

import by.htp.ex.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // request.getRequestDispatcher(JspPageName.MAIN_PAGE).forward(request, response);
    }
}
