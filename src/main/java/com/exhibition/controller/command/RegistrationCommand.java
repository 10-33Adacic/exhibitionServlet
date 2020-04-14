package com.exhibition.controller.command;

import com.exhibition.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null)
            return "/registration.jsp";

        userService.addUser(username, password);

        return "redirect:/exhibition/login";
    }
}

