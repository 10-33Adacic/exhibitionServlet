package com.exhibition.controller.command;

import javax.servlet.http.HttpServletRequest;

public class UserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/user/userBasis.jsp";
    }
}