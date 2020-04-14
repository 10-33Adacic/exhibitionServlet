package com.exhibition.controller.command;

import com.exhibition.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            User currentUser
    ) {
        HttpSession session = request.getSession();
        session.setAttribute("user", currentUser);
        session.setAttribute("username", currentUser.getUsername());
        session.setAttribute("userId", currentUser.getId());
        session.setAttribute("role", currentUser.getRole());
        session.setAttribute("access", currentUser.getRole().name());
    }

    static boolean checkUserIsLogged(HttpServletRequest request,
                                     String username
    ) {
        @SuppressWarnings("unchecked")
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");

        if (loggedUsers == null) { return false; }

        if (loggedUsers.stream().anyMatch(username::equalsIgnoreCase)) { return true; }

        loggedUsers.add(username);

        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

        return false;
    }
}
