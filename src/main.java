import Controller.MainController;
import Database.DatabaseController;

public class main {

    public static void main(String[] args) {
        DatabaseController databaseController = new DatabaseController();
        MainController mainController = new MainController(databaseController);
    }
}
