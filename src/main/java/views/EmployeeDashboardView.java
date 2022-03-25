package views;

import models.subclass.ReimbursementListPersonal;
import models.User;
import services.ReimbursementService;
import util.Print;

import java.util.Scanner;

public class EmployeeDashboardView {
    public static void view(User user) {
        ReimbursementService reimbursementService = new ReimbursementService();
        Scanner sc = new Scanner(System.in);
        Boolean running = true;

        while(running) {
            Print.dashboard(user);
            String input = sc.nextLine();
            switch (input) {
                case "1" -> {
                    ReimbursementListPersonal rlp = new ReimbursementListPersonal();
                    System.out.print("Reimbursement Amount: ");
                    String amount = sc.nextLine();
                    rlp.setAmount(Double.parseDouble(amount));

                    System.out.print("Description (Optional): ");
                    rlp.setDescription(sc.nextLine());

                    rlp.setAuthorId(user.getId());

                    System.out.println("Type:");
                    System.out.println(" 1 - Lodging");
                    System.out.println(" 2 - Food");
                    System.out.println(" 3 - Travel");
                    rlp.setTypeId(Integer.parseInt(sc.nextLine()));

                    reimbursementService.createReimbursementGivenAuthorId(rlp);

                    System.out.println("Reimbursement request for $" + rlp.getAmount() + " is successfully created.");
                }
                case "2" -> {
                    ReimbursementListPersonal rlpDelete = new ReimbursementListPersonal();
                    System.out.print("Reimbursement ID: ");
                    rlpDelete.setId(sc.nextInt());

                    reimbursementService.deleteReimbursement(rlpDelete.getId());

                    System.out.println("Reimbursement of ID: " + rlpDelete.getId() + " is deleted.");
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid input.");
            }
        }
    }
}
