package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.UserParameter;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {
    private final IUserService service = ServiceProvider.getInstance().getUserService();
    private static final String ERROR_REGISTRATION_MESSAGE = "Invalid data entered";
    private static final String DO_NOT_SHOW_NEWS = "noShow";
    private static final String GO_TO_NEWS_LIST = "controller?command=go_to_news_list";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(UserParameter.JSP_NAME_PARAM);
        String usersurname = request.getParameter(UserParameter.JSP_SURNAME_PARAM);
        String login = request.getParameter(UserParameter.JSP_LOGIN_PARAM);
        String password = request.getParameter(UserParameter.JSP_PASSWORD_PARAM);
        String email = request.getParameter(UserParameter.JSP_EMAIL_PARAM);
        String role = UsersRole.USER.getTitle();
        HttpSession getSession = request.getSession(true);

        NewUserInfo userData = new NewUserInfo(username, usersurname, login, password, email, role);

        try {
            if (service.registration(userData)) {
                getSession.setAttribute(AttributesKeys.USER, ConnectionStatus.ACTIVE);
                getSession.setAttribute(AttributesKeys.REG_USER, ConnectionStatus.REGISTERED);
                getSession.setAttribute(AttributesKeys.ROLE, role);
                response.sendRedirect(GO_TO_NEWS_LIST);
                request.removeAttribute(AttributesKeys.REG_USER);
            } else {
                getSession.setAttribute(AttributesKeys.REG_USER, ConnectionStatus.UNREGISTERED);
                request.setAttribute(AttributesKeys.ERRORS_REGISTRATION_NAME, ERROR_REGISTRATION_MESSAGE);
                request.setAttribute(AttributesKeys.SHOW_NEWS, DO_NOT_SHOW_NEWS);
                request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
            }
        } catch (ServiceException e) {
            response.sendRedirect(JspPageName.ERROR_PAGE);
        }
    }
}