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

        if (username == null
                || username.equals("")
                || password == null
                || password.equals(""))
            return "/registration.jsp";
        try {
            if (userService.findByUsername(username).isPresent()) {
                request.setAttribute("error", true);
                return "/registration.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userService.addUser(username, password);

        return "redirect:/exhibition/login";
    }
}

