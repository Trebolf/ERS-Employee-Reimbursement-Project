package services;

import models.User;
import repositories.UserDAO;
import repositories.UserDAOImp;

public class UserService {

    private UserDAO userDAO;

    public UserService(){
        this.userDAO = new UserDAOImp();
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User validateCredentials(String username, String password) {
        User user = this.userDAO.getUserGivenUsername(username);

        if(user == null)
            return null;

        if(!password.equals(user.getPassword()))
            return null;

        return user;
    }
}
