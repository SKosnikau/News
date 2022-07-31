package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.AttributsKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String NEWS_LIST = "newsList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<News> newsList;
        try {
            newsList = newsService.list();
            request.setAttribute(AttributsKeys.NEWS, newsList);
            request.setAttribute(AttributsKeys.PRESENTATION, NEWS_LIST);

            request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}