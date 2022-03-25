package controllers;

import io.javalin.http.Context;
import models.JsonResponse;
import models.User;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImp;
import services.ReimbursementService;
import services.UserService;
import views.AdminDashboardView;
import views.EmployeeDashboardView;

public class UserController {
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void login(Context context) {
        JsonResponse jsonResponse;
        ReimbursementService reimbursementService = new ReimbursementService();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();

        User credentials = context.bodyAsClass(User.class);
        User userFromDB = userService.validateCredentials(credentials.getUsername(), credentials.getPassword());

        if(userFromDB == null) {
            jsonResponse = new JsonResponse(false, "Invalid username or password.", null);
        } else {
            context.sessionAttribute("user", userFromDB);
            if (userFromDB.getUserRoleId().equals(1)) {
                jsonResponse = new JsonResponse(true, "Logged in as admin!", userFromDB);
                //AdminDashboardView.view(userFromDB);
            } else {
                jsonResponse = new JsonResponse(true, "Logged in!", userFromDB);
                //EmployeeDashboardView.view(userFromDB);
            }
        }
        context.json(jsonResponse);
    }

    public void checkSession(Context context) {
        User user = context.sessionAttribute("user");

        if(user == null) {
            context.json(new JsonResponse(false, "You are not logged in.", null));
        } else {
            context.json(new JsonResponse(true, "User is logged in.", user));
        }
    }

    public void logout(Context context) {
        context.sessionAttribute("user", null);

        context.json(new JsonResponse(true, "You are logged out.", null));
    }
}
