package com.exhibition.controller.command;

import com.exhibition.controller.util.CommandUtility;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AddMoneyCommand implements Command {

    private UserService userService;

    public AddMoneyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        User user = userService.findById(userId);
        request.setAttribute("currentUser", user);

        String moneyString = request.getParameter("money");
        Long accountMoney = user.getAccountMoney();
        if (moneyString != null) {
            Long money = Long.valueOf(moneyString);
            Long value = accountMoney + money;
            userService.updateBalance(user, value);

            CommandUtility.setUser(request, user);
            return "redirect:/exhibition/user/buy-ticket";
        }

        return "/WEB-INF/user/pages/addMoney.jsp";
    }
}
