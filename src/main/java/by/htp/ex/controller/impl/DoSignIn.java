package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.News;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.UserParameter;
import by.htp.ex.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class DoSignIn implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();
    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String GO_TO_NEWS_LIST = "controller?command=go_to_news_list";
    private static final int LAST_NEWS_NUMBER = 5;
    private static final String ERROR_LOGINATION_MESSAGE = "Wrong login or password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<News> latestNews;
        String login = request.getParameter(UserParameter.JSP_LOGIN_PARAM);
        String password = request.getParameter(UserParameter.JSP_PASSWORD_PARAM);
        HttpSession getSession = request.getSession(true);

        try {
            String role = service.signIn(login, password);
            latestNews = newsService.latestList(LAST_NEWS_NUMBER);
            if (!role.equals(UsersRole.GUEST.getTitle())) {
                getSession.setAttribute(AttributesKeys.USER, ConnectionStatus.ACTIVE);
                getSession.setAttribute(AttributesKeys.ROLE, role);
                getSession.removeAttribute(AttributesKeys.REG_USER);
                response.sendRedirect(GO_TO_NEWS_LIST);
            } else {
                getSession.setAttribute(AttributesKeys.USER, ConnectionStatus.NOT_ACTIVE);
                getSession.removeAttribute(AttributesKeys.REG_USER);
                request.setAttribute(AttributesKeys.NEWS, latestNews);
                request.setAttribute(AttributesKeys.ERRORS_LOGINATION_NAME, ERROR_LOGINATION_MESSAGE);
                request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
            }
        } catch (ServiceException e) {
            response.sendRedirect(JspPageName.ERROR_PAGE);
        } catch (ServiceNewsException e) {
            response.sendRedirect(JspPageName.ERROR_PAGE);
        }
    }
}