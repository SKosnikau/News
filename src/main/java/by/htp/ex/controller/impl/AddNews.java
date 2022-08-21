package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.AttributesKeys;
import by.htp.ex.controller.Command;
import by.htp.ex.controller.JspPageName;
import by.htp.ex.controller.NewsParameter;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceNewsException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AddNews implements Command {

    private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
    private static final String GO_TO_NEWS_LIST = "controller?command=go_to_news_list";
    public static final String COMMAND_DONE = "done";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(NewsParameter.JSP_TITLE);
        String briefNews = request.getParameter(NewsParameter.JSP_BRIEF_NEWS);
        String content = request.getParameter(NewsParameter.JSP_CONTENT);
        String newsData = request.getParameter(NewsParameter.JSP_DATA);

        HttpSession getSession = request.getSession(true);

        News news = new News(title, briefNews, content, newsData);
        System.out.println("news " + news);

        try {
            System.out.println("newsService.save(news) " + newsService.save(news));
            if (newsService.save(news)) {
                getSession.removeAttribute(AttributesKeys.REG_USER);
                getSession.setAttribute(AttributesKeys.ADD_NEWS, COMMAND_DONE);
                response.sendRedirect(GO_TO_NEWS_LIST);
                getSession.removeAttribute(AttributesKeys.ADD_NEWS);
            } else {
                response.sendRedirect(JspPageName.ERROR_PAGE);
            }
        } catch (ServiceNewsException e) {
            response.sendRedirect(JspPageName.ERROR_PAGE);
        }
    }
}