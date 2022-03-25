package views;

import models.subclass.ReimbursementListAll;
import models.User;
import services.ReimbursementService;
import util.Print;

import java.util.Scanner;

public class AdminDashboardView {
    public static void view(User user) {
        ReimbursementService reimbursementService = new ReimbursementService();
        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while (running) {
            Print.dashboardAdmin(user);
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    ReimbursementListAll rla1 = new ReimbursementListAll();
                    System.out.print("Reimbursement ID: ");
                    /*rla1.setId(Integer.parseInt(sc.nextLine()));

                    reimbursementService.approveReimbursement(rla1.getId());

                    System.out.println("Reimbursement at ID: " + rla1.getId() + " is successfully approved!");*/

                    if (sc.hasNextInt()) {
                        rla1.setId(Integer.parseInt(sc.nextLine()));

                        reimbursementService.approveReimbursement(rla1.getId());

                        System.out.println("Reimbursement at ID: " + rla1.getId() + " is successfully approved!");
                    } else {
                        System.out.println(sc.nextLine() + " is not a valid ID.");
                    }

                }
                case "2" -> {
                    ReimbursementListAll rla2 = new ReimbursementListAll();
                    System.out.print("Reimbursement ID: ");
                    /*rla2.setId(Integer.parseInt(sc.nextLine()));

                    reimbursementService.denyReimbursement(rla2.getId());

                    System.out.println("Reimbursement at ID: " + rla2.getId() + " is successfully denied.");*/

                    if (sc.hasNextInt()) {
                        rla2.setId(Integer.parseInt(sc.nextLine()));

                        reimbursementService.denyReimbursement(rla2.getId());

                        System.out.println("Reimbursement at ID: " + rla2.getId() + " is successfully denied!");
                    } else {
                        System.out.println(sc.nextLine() + " is not a valid ID.");
                    }
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid input.");
            }
        }
    }
}
