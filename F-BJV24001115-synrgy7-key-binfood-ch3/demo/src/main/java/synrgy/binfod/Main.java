package synrgy.binfod;

import synrgy.binfod.model.Menu;
import synrgy.binfod.view.MainView;
import synrgy.binfod.controller.MainController;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        MainView view = new MainView();
        MainController controller = new MainController(menu, view);
        controller.run();
    }
}
