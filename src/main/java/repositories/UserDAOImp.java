package repositories;

import models.User;
import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImp implements UserDAO {

    @Override
    public User getUserGivenUsername(String username) {
        User user = null;

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from _users where username = ?;";

            //preparing our sql statement
            PreparedStatement ps = conn.prepareStatement(sql);

            //we are adding the username into the question mark in the sql statement
            //parameter index will find the first index where the "?" occurs and replaces it.
            ps.setString(1, username);

            //results will be stored in object "rs"
            //DQL FOR RESULTS USE QUERY
            ResultSet rs = ps.executeQuery();

            //while loop needed to print
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return user;
    }
}