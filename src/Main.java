import schermata.home.HomeController;
import schermata.home.HomeModel;
import schermata.home.HomeView;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        // impostazione tema
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception ignored) {}
        
        // inizializzazione del frame
        HomeModel homeModel = new HomeModel();
        HomeView homeView = new HomeView();
        HomeController homeController = new HomeController(homeModel, homeView);
    }
}