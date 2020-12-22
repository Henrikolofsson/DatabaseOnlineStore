package Controller;

import Database.DatabaseController;
import Entities.Admin;
import Entities.User;
import Enums.Countries;
import GUI.ApplicationFrame;
import Usercreation.CreateAccountFrame;

public class MainController {
    private DatabaseController databaseController;
    private ApplicationFrame applicationFrame;
    private CreateAccountFrame createAccountFrame;

    public MainController(DatabaseController databaseController) {
        this.databaseController = databaseController;
        applicationFrame = new ApplicationFrame(this);

    }

    public Countries[] getCountries() {
        return Countries.values();
    }

    public void openCreateAccountWindow() {
        createAccountFrame = new CreateAccountFrame(this);
    }

    public boolean createAdminUser(User user, String adminPassword) {
        if(databaseController.checkAdminPassword(adminPassword)) {
            databaseController.createAdminUser(user);
            createAccountFrame.dispose();
            return true;
        }
        return false;
    }

    public boolean createNormalUser(User user) {
        if(databaseController.createNormalUser(user)) {
            createAccountFrame.dispose();
            return true;
        }
        return false;
    }
}
