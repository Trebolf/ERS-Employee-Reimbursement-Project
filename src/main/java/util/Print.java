package util;

import models.User;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImp;

public class Print {

    public static void dashboard(User user) {
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();
        System.out.println();
        System.out.println(reimbursementDAO.getAllReimbursementGivenUsername(user.getUsername()));
        System.out.println();
        System.out.println("1) Create a new reimbursement request.");
        System.out.println("2) Delete a request.");
        System.out.println("0) Logout");
    }

    public static void dashboardAdmin(User admin) {
        ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();
        System.out.println();
        System.out.println(reimbursementDAO.getAllReimbursement());
        System.out.println();
        System.out.println("1) Approve a request.");
        System.out.println("2) Deny a request.");
        System.out.println("0) Logout");
    }
}
