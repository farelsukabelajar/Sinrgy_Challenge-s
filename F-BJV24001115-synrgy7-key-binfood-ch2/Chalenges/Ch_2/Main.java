package Chalenges.Ch_2;

import Chalenges.Ch_2.model.Menu;
import Chalenges.Ch_2.view.MainView;
import Chalenges.Ch_2.controller.MainController;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        MainView view = new MainView();
        MainController controller = new MainController(menu, view);
        controller.run();
    }
}
