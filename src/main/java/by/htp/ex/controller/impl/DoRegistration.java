package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.controller.AttributsKeys;
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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(UserParameter.JSP_NAME_PARAM);
        String usersurname = request.getParameter(UserParameter.JSP_SURNAME_PARAM);
        String login = request.getParameter(UserParameter.JSP_LOGIN_PARAM);
        String password = request.getParameter(UserParameter.JSP_PASSWORD_PARAM);
        String email = request.getParameter(UserParameter.JSP_EMAIL_PARAM);
        String role = UsersRole.USER;
        HttpSession getSession = request.getSession(true);

        NewUserInfo userData = new NewUserInfo(username, usersurname, login, password, email, role);
        try {
            if (service.registration(userData)) {
                getSession.setAttribute(AttributsKeys.USER, ConnectionStatus.ACTIVE);
                getSession.setAttribute(AttributsKeys.REG_USER, ConnectionStatus.REGISTRED);
                getSession.setAttribute(AttributsKeys.ROLE, role);
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                getSession.setAttribute(AttributsKeys.REG_USER, ConnectionStatus.UNREGISTRED);
                request.setAttribute(AttributsKeys.ERRORS_REGISTRATION_NAME, ERROR_REGISTRATION_MESSAGE);
                request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}