package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.ConnectionStatus;
import by.htp.ex.bean.News;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<News> latestNews;
        try {
            latestNews = newsService.latestList(5);
            request.setAttribute(AttributesKeys.USER, ConnectionStatus.NOT_ACTIVE);
            request.setAttribute(AttributesKeys.NEWS, latestNews);
            request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}