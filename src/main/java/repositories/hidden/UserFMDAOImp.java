package repositories.hidden;

import models.subclass.ReimbursementListAll;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserFMDAOImp implements UserFMDAO{

    @Override
    public List<ReimbursementListAll> getAllReimbursement() {
        List<ReimbursementListAll> reimbursementListAll = new ArrayList<>();

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select\n" +
                    "\tr.reimb_id as reimb_id,\n" +
                    "\tr.reimb_author_fk as author_id,\n" +
                    "\tu.user_first_name as first_name,\n" +
                    "\tu.user_last_name as last_name,\n" +
                    "\trt.reimb_type as type_of_reimb,\n" +
                    "\tr.reimb_amount as amount,\n" +
                    "\tr.reimb_description as description,\n" +
                    "\trs.reimb_stat as status,\n" +
                    "\tr.reimb_submitted as time_submitted,\n" +
                    "\tr.reimb_resolved as time_resolved\n" +
                    "from\n" +
                    "\t\"_users\" as u \n" +
                    "\tinner join \"_reimbursement\"\t\t\tas r  on u.user_id = r.reimb_author_fk\n" +
                    "\tinner join \"_reimbursement_type\" \tas rt on rt.reimb_type_id = r.reimb_type_id_fk \n" +
                    "\tinner join \"_reimbursement_status\"  as rs on rs.reimb_stat_id = r.reimb_status_id_fk\n" +
                    "\torder by status desc;";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                reimbursementListAll.add(new ReimbursementListAll(
                        rs.getInt(1),     //reimb_id
                        rs.getInt(2),     //author_id
                        rs.getString(3),  //first_name
                        rs.getString(4),  //last_name
                        rs.getString(5),  //type
                        rs.getDouble(6),  //amount
                        rs.getString(7),  //description
                        rs.getString(8),  //status
                        rs.getDate(9),    //time_submitted
                        rs.getDate(10))   //time_resolved
                );
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return reimbursementListAll;
    }

    @Override
    public void approveReimbursement(Integer id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update \"_reimbursement\" set reimb_status_id_fk = 2,\n" +
                    "                reimb_resolved = current_timestamp,\n" +
                    "                reimb_resolver_fk = 5\n" +
                    "        where reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void denyReimbursement(Integer id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update \"_reimbursement\" set reimb_status_id_fk = 3,\n" +
                    "                reimb_resolved = current_timestamp,\n" +
                    "                reimb_resolver_fk = 5\n" +
                    "        where reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public void updateReimbursement(Integer id, Integer statId) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update \"_reimbursement\" set reimb_status_id_fk = ?,\n" +
                    "                reimb_resolved = current_timestamp,\n" +
                    "                reimb_resolver_fk = 5\n" +
                    "        where reimb_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statId);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
