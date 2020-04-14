package com.exhibition.controller.command;

import com.exhibition.controller.util.CommandUtility;
import com.exhibition.model.entity.Role;
import com.exhibition.model.entity.User;
import com.exhibition.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String DATA_ERROR = "Invalid username or password.";

    private static final String LOGGED_ERROR = "You has already logged.";

    private static final String LOGGED_SUCCESS = " logged successfully.";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null)
            return "/login.jsp";

        Optional<User> user = userService.findUser(username, password);

        if (!user.isPresent()) {
            request.setAttribute("error", DATA_ERROR);
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, username)) {
            request.setAttribute("error", LOGGED_ERROR);
            return "/login.jsp";
        }

        if (user.get().getRole().equals(Role.ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/admin";
        } else if (user.get().getRole().equals(Role.SUPER_ADMIN)) {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/super_admin";
        } else {
            CommandUtility.setUser(request, user.get());

            LOGGER.info(user.get() + LOGGED_SUCCESS);

            return "redirect:/exhibition/user";
        }

    }
}
