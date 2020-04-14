package com.exhibition.controller.command;

import com.exhibition.controller.util.CommandUtility;
import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ExhibitionPageCommand implements Command {

    private ExhibitionService exhibitionService;

    public ExhibitionPageCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("currentPage", 1);

        String exhibitionIdDelete = request.getParameter("exhibitionId");
        if (exhibitionIdDelete != null) {
            Long idForDelete = Long.valueOf(exhibitionIdDelete);
            exhibitionService.deleteById(idForDelete);
        }

        String showroom = request.getParameter("showroom");
        if (showroom == null || showroom.equals("")) {
            CommandUtility.showPagination(request, exhibitionService);
        } else {
            List<Exhibition> exhibitions = exhibitionService.findByShowroom(showroom);
            request.setAttribute("exhibitionList", exhibitions);
        }

        User currentUser = (User) request.getSession().getAttribute("user");
        request.setAttribute("currentUser", currentUser);

        return "/WEB-INF/user/pages/exhibitionPage.jsp";
    }
}
