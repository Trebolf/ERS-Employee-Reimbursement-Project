package services;

import models.ReimbursementList;
import models.User;
import models.subclass.ReimbursementListAll;
import models.subclass.ReimbursementListPersonal;
import repositories.ReimbursementDAO;
import repositories.ReimbursementDAOImp;

import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(){
        this.reimbursementDAO = new ReimbursementDAOImp();
    }

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public List<ReimbursementListAll> getAllReimbursement() {
        return this.reimbursementDAO.getAllReimbursement();
    }

    public List<ReimbursementListPersonal> getAllReimbursementGivenUsername(String username) {
        return this.reimbursementDAO.getAllReimbursementGivenUsername(username);
    }

    public void approveReimbursement(Integer RId) {
        this.reimbursementDAO.approveReimbursement(RId);
    }

    public void denyReimbursement(Integer RId) {
        this.reimbursementDAO.denyReimbursement(RId);
    }

    public void createReimbursementGivenAuthorId(ReimbursementListPersonal reimbursementListPersonal) {
        this.reimbursementDAO.createReimbursementGivenAuthorId(reimbursementListPersonal);
    }

    public void deleteReimbursement(Integer RId) {
        this.reimbursementDAO.deleteReimbursement(RId);
    }
}