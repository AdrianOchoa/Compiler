/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerlbpe;

import controllers.MenuController;
import controllers.PaneController;
import controllers.FrameController;
import menus.Menu;
import views.Pane;
import views.Frame;

/**
 *
 * @author Adrián
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Pane pane = new Pane();

        MenuController menuController = new MenuController(pane);
        PaneController panelController = new PaneController(pane);
        FrameController frameController = new FrameController();

        pane.setPaneController(panelController);

        Menu menu = new Menu();

        menu.addEvents(menuController);

        Frame frame = new Frame("Compilador LBPE", menu, pane, frameController);

    }

}
