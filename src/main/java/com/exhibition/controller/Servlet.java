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

public class Servlet extends HttpServlet {

    //TODO delete after tests
//    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {
        UserService userService = new UserService();
        ExhibitionService exhibitionService = new ExhibitionService();

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("index", new IndexCommand());
        commands.put("login", new LoginCommand(userService));
        commands.put("registration", new RegistrationCommand(userService));
        commands.put("logout", new LogOutCommand());
        commands.put("server-error", new ServerErrorCommand());

        commands.put("super_admin", new SuperAdminCommand());
        commands.put("super_admin/user-list", new UserListCommand(userService));
        commands.put("super_admin/edit", new UserEditCommand(userService));

        commands.put("admin", new AdminCommand());
        commands.put("admin/exhibitions/add", new ExhibitionAddCommand(exhibitionService));
        commands.put("admin/exhibitions/edit", new ExhibitionEditCommand(exhibitionService));
        commands.put("admin/my_exhibitions", new MyExhibitionsCommand(exhibitionService));

        commands.put("user", new UserCommand());
        commands.put("user/exhibitions", new ExhibitionPageCommand(exhibitionService));
        commands.put("user/edit-profile", new EditProfileCommand(userService));
        commands.put("user/buy-ticket", new BuyTicketCommand(exhibitionService, userService));
        commands.put("user/bought-tickets", new BoughtTicketsCommand(exhibitionService, userService));
        commands.put("user/add-money", new AddMoneyCommand(userService));
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

            Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/error404.jsp");

            String page = command.execute(request);
            if (page.contains("redirect")) {
                response.sendRedirect(page.replace("redirect:", ""));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (Exception e) {

            response.sendRedirect("/exhibition/server-error");
        }
    }
}
