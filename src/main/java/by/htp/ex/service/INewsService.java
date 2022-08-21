package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;

public interface INewsService {


    void find() throws ServiceNewsException;

    void update() throws ServiceNewsException;

    List<News> latestList(int count) throws ServiceNewsException;

    List<News> list() throws ServiceNewsException;

    News findById(int id) throws ServiceNewsException;

    boolean save(News news) throws ServiceNewsException;
}