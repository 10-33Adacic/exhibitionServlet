package com.exhibition.controller.command;

import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MyExhibitionsCommand implements Command {

    private ExhibitionService exhibitionService;

    public MyExhibitionsCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User author = (User) request.getSession().getAttribute("user");

        List<Exhibition> exhibitions = exhibitionService.findByAuthor(author);

        request.setAttribute("exhibitions", exhibitions);
        request.setAttribute("user", author);

        return "/WEB-INF/admin/pages/myExhibitions.jsp";
    }
}