package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.controller.AttributsKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class DoSignIn implements Command {

    private final IUserService service = ServiceProvider.getInstance().getUserService();

    private static final String JSP_LOGIN_PARAM = "login";
    private static final String JSP_PASSWORD_PARAM = "password";
    private static final String ERROR_LOGINATION_MESSAGE = "wrong login or password";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(JSP_LOGIN_PARAM);
        String password = request.getParameter(JSP_PASSWORD_PARAM);
        HttpSession getSession = request.getSession(true);

        try {

            String role = service.signIn(login, password);

            if (!role.equals(UsersRole.GUEST)) {
                getSession.setAttribute(AttributsKeys.USER, ConnectionStatus.ACTIVE);
                getSession.setAttribute(AttributsKeys.ROLE, role);
                getSession.setAttribute(AttributsKeys.REG_USER, ConnectionStatus.REGISTRED);
                response.sendRedirect("controller?command=go_to_news_list");
            } else {
                getSession.setAttribute(AttributsKeys.USER, ConnectionStatus.NOT_ACTIVE);
                request.setAttribute(AttributsKeys.ERRORS_LOGINATION_NAME, ERROR_LOGINATION_MESSAGE);
                request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}