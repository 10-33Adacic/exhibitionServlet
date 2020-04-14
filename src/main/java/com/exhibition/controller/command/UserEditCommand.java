package com.exhibition.controller.command;

import com.exhibition.model.entity.Role;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserEditCommand implements Command {

    private UserService userService;

    public UserEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Long userId = Long.valueOf(request.getParameter("userId"));
            User user = userService.findById(userId);
            request.setAttribute("editUser", user);

            String newUsername = request.getParameter("newUsername");
            String newRole = request.getParameter("newRole");
            if (newUsername != null && newRole != null) {
                user.setUsername(newUsername);
                user.setRole(Role.valueOf(newRole));
                userService.update(user);
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
        return "/WEB-INF/super_admin/pages/userEdit.jsp";
    }
}
