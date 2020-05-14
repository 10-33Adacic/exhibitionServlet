package com.exhibition.controller.command;

import com.exhibition.controller.util.CommandUtility;
import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.ExhibitionService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;

public class ExhibitionEditCommand implements Command {

    private static final String DATE_ERROR = "You choose date before today.";

    private static final String EXHIBITION_EDIT_ERROR = "You cannot edit this exhibition now.";

    private ExhibitionService exhibitionService;

    public ExhibitionEditCommand(ExhibitionService exhibitionService) {
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

            Long exhibitionId = Long.valueOf(request.getParameter("exhibitionId"));

            if (CommandUtility.correctInput(name, showroom, description, priceStr, dateStr)) {
                BigDecimal price = new BigDecimal(priceStr);
                Date date = Date.valueOf(dateStr);

                if (!CommandUtility.dataBeforeToday(date)) {
                    User author = (User) request.getSession().getAttribute("user");
                    Exhibition exhibition = Exhibition.builder()
                            .id(exhibitionId)
                            .name(name)
                            .showroom(showroom)
                            .description(description)
                            .author(author)
                            .price(price)
                            .date(date)
                            .build();

                    exhibitionService.update(exhibition);
                } else {
                    request.setAttribute("error", DATE_ERROR);
                }
            }

            Exhibition currentExhibition = exhibitionService.findById(exhibitionId);
            if (currentExhibition != null) {
                request.setAttribute("exhibition", currentExhibition);
            }
        } catch (Exception e) {
            request.setAttribute("error", EXHIBITION_EDIT_ERROR);
        }

        return "/WEB-INF/admin/pages/exhibitionEdit.jsp";
    }
}
