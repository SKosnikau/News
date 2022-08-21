package by.htp.ex.controller.impl;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.UserParameter;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class GoToAddNewsPage implements Command {
    private final IUserService service = ServiceProvider.getInstance().getUserService();
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    public static final String ADD_NEWS = "addNews";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession getSession = request.getSession();
        getSession.setAttribute(AttributesKeys.USER, ConnectionStatus.ACTIVE);
        getSession.setAttribute(AttributesKeys.NEWS_COMMANDS_NAME, ADD_NEWS);
        request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
    }
}