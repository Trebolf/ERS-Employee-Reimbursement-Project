package controllers;

import io.javalin.http.Context;
import models.JsonResponse;
import models.subclass.ReimbursementListAll;
import models.subclass.ReimbursementListPersonal;
import services.ReimbursementService;

import java.util.List;

public class ReimbursementController {
    ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
    }

    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    // ADMIN
    public void displayAllReimbursementAsAdmin(Context context) {
        List<ReimbursementListAll> rla = reimbursementService.getAllReimbursement();

        context.json(new JsonResponse(true, "Listing all reimbursements.", rla));
    }

    public void approveReimbursement(Context context) {
        Integer id = Integer.parseInt(context.pathParam("id"));

        reimbursementService.approveReimbursement(id);

        context.json(new JsonResponse(true, "Reimbursement at ID: " + id + " was successfully approved.", null));
    }

    public void denyReimbursement(Context context) {
        Integer id = Integer.parseInt(context.pathParam("id"));

        reimbursementService.denyReimbursement(id);

        context.json(new JsonResponse(true, "Reimbursement at ID: " + id + " was denied.", null));
    }


    // USER
    public void displayUserReimbursement(Context context) {
        String username = context.pathParam("username");

        List<ReimbursementListPersonal> rlp = reimbursementService.getAllReimbursementGivenUsername(username);

        context.json(new JsonResponse(true, "Listing user reimbursements.", rlp));
    }

    public void createReimbursementAsUser(Context context) {
        ReimbursementListPersonal rlp = context.bodyAsClass(ReimbursementListPersonal.class);

        reimbursementService.createReimbursementGivenAuthorId(rlp);

        context.json(new JsonResponse(true, "Reimbursement for " + rlp.getAmount() + " is successfully created!", null));
    }

    public void deleteReimbursement(Context context) {
        Integer id = Integer.parseInt(context.pathParam("id"));

        reimbursementService.deleteReimbursement(id);

        context.json(new JsonResponse(true, "Reimbursement at ID: " + id + " was deleted.", null));
    }
}
