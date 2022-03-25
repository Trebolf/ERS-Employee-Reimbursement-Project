package repositories.hidden;

import models.subclass.ReimbursementListAll;

import java.util.List;

public interface UserFMDAO {
    List<ReimbursementListAll> getAllReimbursement();
    void approveReimbursement(Integer id);
    void denyReimbursement(Integer id);
    void updateReimbursement(Integer id, Integer statId);
}
