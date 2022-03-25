package views;

import models.User;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImp;
import services.UserService;

import java.util.Scanner;

public class LoginView {
    public static void view() {
        UserService userService = new UserService();
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();
        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("LOGIN VIEW");
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            User user = userService.validateCredentials(username, password);

            if (user == null) {
                System.out.println("Incorrect username or password.");
            } else {
                if (user.getUserRoleId().equals(1)) {
                    AdminDashboardView.view(user);
                } else {
                    EmployeeDashboardView.view(user);
                }
                //print list
                //System.out.println(reimbursementDAO.getAllReimbursementGivenUsername(username));
            }
        }
    }
}
