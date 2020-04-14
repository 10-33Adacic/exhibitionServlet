package com.exhibition.controller.command;

import javax.servlet.http.HttpServletRequest;

public class SuperAdminCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/super_admin/superAdminBasis.jsp";
    }
}
