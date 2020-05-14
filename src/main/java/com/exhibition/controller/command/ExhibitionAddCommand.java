package com.exhibition.controller.command;

import com.exhibition.controller.util.CommandUtility;
import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

public class ExhibitionAddCommand implements Command {

    private static final String DATE_ERROR = "You choose date before today.";

    private static final String EXHIBITION_ADD_ERROR = "You cannot add this exhibition now.";

    private ExhibitionService exhibitionService;

    public ExhibitionAddCommand(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String showroom = request.getParameter("showroom");
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String dateStr = request.getParameter("date");

            if (CommandUtility.correctInput(name, showroom, description, priceStr, dateStr)) {
                BigDecimal price = new BigDecimal(priceStr);
                Date date = Date.valueOf(dateStr);

                if (CommandUtility.dataBeforeToday(date)) {
                    request.setAttribute("name", name);
                    request.setAttribute("showroom", showroom);
                    request.setAttribute("description", description);
                    request.setAttribute("price", price);
                    request.setAttribute("error", DATE_ERROR);
                    return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
                }

                User author = (User) request.getSession().getAttribute("user");
                Exhibition exhibition = Exhibition.builder()
                        .name(name)
                        .showroom(showroom)
                        .description(description)
                        .author(author)
                        .price(price)
                        .date(date)
                        .build();

                exhibitionService.add(exhibition);

                return "redirect:/exhibition/user/exhibitions";
            }
            return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
        } catch (Exception e) {
            request.setAttribute("error", EXHIBITION_ADD_ERROR);
            return "/WEB-INF/admin/pages/exhibitionAdd.jsp";
        }
    }
}
