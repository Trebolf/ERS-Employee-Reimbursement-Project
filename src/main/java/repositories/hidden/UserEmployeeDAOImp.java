package repositories.hidden;

import models.subclass.ReimbursementListPersonal;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEmployeeDAOImp implements UserEmployeeDAO {
    /*String url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/ERS-project";
    String username = System.getenv("RDS_USERNAME");
    String password = System.getenv("RDS_PASSWORD");*/

    @Override
    public List<ReimbursementListPersonal> getAllReimbursementGivenUsername(String username) {
        List<ReimbursementListPersonal> reimbursementListPersonal = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            //1. CONNECT
            //2. PREPARE
            //3. EXECUTE / UPDATE
            String sql = "select\n" +
                    "\tr.reimb_id as id,\n" +
                    "\trt.reimb_type as type_of_reimb,\n" +
                    "\tr.reimb_amount as amount,\n" +
                    "\tr.reimb_description as description,\n" +
                    "\trs.reimb_stat as status,\n" +
                    "\tr.reimb_submitted as time_submitted\n" +
                    "from\n" +
                    "\t\"_users\" as u \n" +
                    "\tinner join \"_reimbursement\"\t\t\tas r  on u.user_id = r.reimb_author_fk\n" +
                    "\tinner join \"_reimbursement_type\" \tas rt on rt.reimb_type_id = r.reimb_type_id_fk \n" +
                    "\tinner join \"_reimbursement_status\"  as rs on rs.reimb_stat_id = r.reimb_status_id_fk\n" +
                    "\twhere u.username = ?\n" +
                    "\torder by status desc;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursementListPersonal.add(new ReimbursementListPersonal(
                        rs.getInt(1),     //id
                        rs.getString(2),  //type
                        rs.getDouble(3),  //amount
                        rs.getString(4),  //description
                        rs.getString(5),  //status
                        rs.getDate(6),    //time submitted
                        rs.getDate(7))     //time_submitted
                );
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursementListPersonal;
    }

    @Override
    public void createReimbursementGivenAuthorId(ReimbursementListPersonal reimbursementListPersonal) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into \"_reimbursement\" (reimb_amount, reimb_description, reimb_author_fk,\n" +
                    "\t\t\t\t\t\t\t  reimb_type_id_fk) values (?, ?,\n" +
                    "\t\t\t\t\t\t\t  ?, ?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, reimbursementListPersonal.getAmount());
            ps.setString(2, reimbursementListPersonal.getDescription());
            ps.setInt(3, reimbursementListPersonal.getAuthorId());
            ps.setInt(4, reimbursementListPersonal.getTypeId());

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void deleteReimbursement(Integer id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "delete from \"_reimbursement\"  where reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
