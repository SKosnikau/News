package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.AttributsKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String VIEW_LIST = "viewNews";
    private static final String NEWS_PARAMETER_ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        News news;
        String id;

        id = request.getParameter(NEWS_PARAMETER_ID);

        try {
            news = newsService.findById(Integer.parseInt(id));
            request.setAttribute(AttributsKeys.NEWS, news);
            request.setAttribute(AttributsKeys.PRESENTATION, VIEW_LIST);
            request.getRequestDispatcher(JspPageName.BASE_PAGE_LAYOUT).forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}