package repositories;

import models.subclass.ReimbursementListAll;
import models.subclass.ReimbursementListPersonal;

import java.util.List;

public interface ReimbursementDAO {
    List<ReimbursementListAll> getAllReimbursement();
    List<ReimbursementListPersonal> getAllReimbursementGivenUsername(String username);
    void approveReimbursement(Integer id);
    void denyReimbursement(Integer id);
    void createReimbursementGivenAuthorId(ReimbursementListPersonal reimbursementListPersonal);
    void deleteReimbursement(Integer id);
}
