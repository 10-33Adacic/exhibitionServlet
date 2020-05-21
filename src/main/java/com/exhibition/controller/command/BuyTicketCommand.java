package com.exhibition.controller.command;

import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.ExhibitionService;
import com.exhibition.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BuyTicketCommand implements Command {
    
    private static final String HAVE_TICKET = "haveTicket";

    private static final String HAVE_TICKET_ERROR = "You have already bought this ticket.";

    private ExhibitionService exhibitionService;

    private UserService userService;

    public BuyTicketCommand(ExhibitionService exhibitionService, UserService userService) {
        this.exhibitionService = exhibitionService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userService.findById(userId);
        request.setAttribute("currentUser", user);

        String exhibitionIdStr = request.getParameter("exhibitionId");
        if (exhibitionIdStr != null) {
            Long exhibitionId = Long.valueOf(exhibitionIdStr);
            request.getSession().setAttribute("exhibitionId", exhibitionId);
        }

        Exhibition exhibition = null;
        Object exhibitionIdObj = request.getSession().getAttribute("exhibitionId");
        if (exhibitionIdObj != null) {
            Long exhibitionId = (Long) exhibitionIdObj;
            exhibition = exhibitionService.findById(exhibitionId);
            request.setAttribute("exhibition", exhibition);
        }

        List<Exhibition> exhibitions = exhibitionService.findBoughtTickets(user);
        if (exhibitions != null) {
            if (exhibitions.contains(exhibition)) {
                request.setAttribute(HAVE_TICKET, HAVE_TICKET_ERROR);
            }
        }

        return "/WEB-INF/user/pages/buyTicket.jsp";
    }
}
