package com.exhibition.controller.command;

import com.exhibition.model.entity.User;
import com.exhibition.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand implements Command {

    private UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<User> userList = userService.findAllUsers();
        request.setAttribute("userList", userList);

        return "/WEB-INF/super_admin/pages/userList.jsp";
    }
}
