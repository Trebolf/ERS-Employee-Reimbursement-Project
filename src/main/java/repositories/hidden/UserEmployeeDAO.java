package repositories.hidden;

import models.subclass.ReimbursementListPersonal;

import java.util.List;

public interface UserEmployeeDAO {
    List<ReimbursementListPersonal> getAllReimbursementGivenUsername(String username);
    void createReimbursementGivenAuthorId(ReimbursementListPersonal reimbursementListPersonal);
    void deleteReimbursement(Integer id);
}
