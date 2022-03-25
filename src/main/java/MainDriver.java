import controllers.ReimbursementController;
import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import models.JsonResponse;
import models.User;
import models.subclass.ReimbursementListPersonal;
import repositories.*;
import repositories.hidden.UserEmployeeDAO;
import repositories.hidden.UserEmployeeDAOImp;
import views.LoginView;

public class MainDriver {
    public static void main(String[] args) {
        //UserEmployeeDAO userEmployeeDAO = new UserEmployeeDAOImp();
        //UserFMDAO userFMDAO = new UserFMDAOImp();
        //ReimbursementDAO reimbursementDAO = new ReimbursementDAOImp();
        //UserDAO userDAO = new UserDAOImp();

        //todo UserEmployee Function testing
        //userEmployeeDAO.createReimbursementGivenAuthorId(new ReimbursementListPersonal(69.00, "Hehe XD", 8, 1));
        //userEmployeeDAO.deleteReimbursement(13);
        //System.out.println(userEmployeeDAO.getAllReimbursementGivenUsername("Djohnson77"));

        //todo UserFM Function Testing
        //userFMDAO.approveReimbursement(4);
        //userFMDAO.denyReimbursement(4);
        //userFMDAO.updateReimbursement(4, 2);

        //todo reimbursementDAO
        //reimbursementDAO.createReimbursementGivenAuthorId(new ReimbursementListPersonal(99.00, "Say no more, fam", 9, 1));
        //reimbursementDAO.updateReimbursement(4,2);

        //todo sout
        //System.out.println(reimbursementDAO.getAllReimbursementGivenUsername("Djohnson77"));
        //System.out.println(reimbursementDAO.getAllReimbursement());

        //LoginView.view();

        //JAVALIN
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/", Location.CLASSPATH);
        }).start(4242);

        UserController userController = new UserController();
        ReimbursementController reimbursementController = new ReimbursementController();

        app.post("/login", userController::login);
        app.get("/check-session", userController::checkSession);
        app.delete("/logout", userController::logout);

        app.get("/list/all", reimbursementController::displayAllReimbursementAsAdmin);
        app.patch("/approve/{id}", reimbursementController::approveReimbursement);
        app.patch("/deny/{id}", reimbursementController::denyReimbursement);

        app.get("/list/{username}", reimbursementController::displayUserReimbursement);
        app.post("/list/{username}", reimbursementController::createReimbursementAsUser);
        app.delete("/list/{id}", reimbursementController::deleteReimbursement);
    }
}
