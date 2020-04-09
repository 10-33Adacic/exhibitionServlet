package com.exhibition.controller;

import com.exhibition.controller.command.*;
import com.exhibition.model.service.ExhibitionService;
import com.exhibition.model.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static com.exhibition.controller.util.Constants.*;

public class Servlet extends HttpServlet {

    //TODO delete after tests
//    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        UserService userService = new UserService();
        ExhibitionService exhibitionService = new ExhibitionService();

        servletConfig.getServletContext()
                .setAttribute(LOGGED_USERS, new HashSet<String>());

        commands.put(URL_INDEX, new IndexCommand());
        commands.put(URL_LOGIN, new LoginCommand(userService));
        commands.put(URL_REGISTRATION, new RegistrationCommand(userService));
        commands.put(URL_LOGOUT, new LogOutCommand());
        commands.put(URL_SERVER_ERROR, new ServerErrorCommand());

        commands.put(URL_SUPER_ADMIN, new SuperAdminCommand());
        commands.put(URL_USER_LIST, new UserListCommand(userService));
        commands.put(URL_EDIT, new UserEditCommand(userService));

        commands.put(URL_ADMIN, new AdminCommand());
        commands.put(URL_EXHIBITION_ADD, new ExhibitionAddCommand(exhibitionService));
        commands.put(URL_EXHIBITION_EDIT, new ExhibitionEditCommand(exhibitionService));
        commands.put(URL_MY_EXHIBITIONS, new MyExhibitionsCommand(exhibitionService));

        commands.put(URL_USER, new UserCommand());
        commands.put(URL_EXHIBITIONS, new ExhibitionPageCommand(exhibitionService));
        commands.put(URL_EDIT_PROFILE, new EditProfileCommand(userService));
        commands.put(URL_BUY_TICKET, new BuyTicketCommand(exhibitionService, userService));
        commands.put(URL_BOUGHT_TICKETS, new BoughtTicketsCommand(exhibitionService, userService));
        commands.put(URL_ADD_MONEY, new AddMoneyCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp
    ) throws ServletException, IOException {

        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp
    ) throws ServletException, IOException {

        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response
    ) throws IOException {
        try {
            String path = request.getRequestURI();
            path = path.replaceAll(".*/exhibition/", "");

            Command command = commands.getOrDefault(path, (r) -> PAGE_ERROR_404);

            String page = command.execute(request);
            if (page.contains("redirect")) {
                response.sendRedirect(page.replace("redirect:", ""));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (Exception e) {

            response.sendRedirect("/exhibition/" + URL_SERVER_ERROR);
        }
    }
}
