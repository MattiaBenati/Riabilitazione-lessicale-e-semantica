package schermata.home;

import metodi.Directory;
import schermata.indovinaimmagine.IndovinaImmagineController;
import schermata.indovinaimmagine.IndovinaImmagineModel;
import schermata.indovinaimmagine.IndovinaImmagineView;
import schermata.indovinaparola.IndovinaParolaController;
import schermata.indovinaparola.IndovinaParolaModel;
import schermata.indovinaparola.IndovinaParolaView;
public class HomeController
{
    private final HomeModel model;
    private final HomeView view;
    
    // costruttore
    public HomeController(HomeModel model, HomeView view)
    {
        this.model = model;
        this.view = view;
        
        // setter dei JButton
        setTasti();
    }
    
    /**
     * setter dei tasti presenti nella schermata
     */
    private void setTasti()
    {
        // setter del JButton "indovinaParola"
        view.addIndovinaParola(e->
        {
            // inizializzazione lista immagini
            Directory directory = Directory.getInstance();
            String indirizzoDirectory = directory.getDirectory();
            
            // verifica numero d'indirizzi
            model.indirizzi = directory.letturaFile(indirizzoDirectory);
            
            if(model.indirizzi == null || model.indirizzi.isEmpty())
                return;
            
            // chiusura della schermata
            view.dispose();
            
            // apertura della nuova modalità
            IndovinaParolaModel modelIndovinaParola = new IndovinaParolaModel();
            IndovinaParolaView viewIndovinaParola = new IndovinaParolaView();
            IndovinaParolaController controllerIndovinaParola = new IndovinaParolaController(modelIndovinaParola, viewIndovinaParola, model.indirizzi, 100, false);
        });
        
        // setter del JButton "indovinaImmagine"
        view.addIndovinaImmagine(e->
        {
            // inizializzazione lista immagini
            Directory directory = Directory.getInstance();
            String indirizzoDirectory = directory.getDirectory();
            
            // verifica numero d'indirizzi
            model.indirizzi = directory.letturaFile(indirizzoDirectory);
            
            if(model.indirizzi == null || model.indirizzi.isEmpty())
                return;
            
            // chiusura della schermata
            view.dispose();
            
            // apertura della nuova modalità
            IndovinaImmagineModel modelIndovinaImmagine = new IndovinaImmagineModel();
            IndovinaImmagineView viewIndovinaImmagine = new IndovinaImmagineView();
            IndovinaImmagineController controllerIndovinaImmagine = new IndovinaImmagineController(modelIndovinaImmagine, viewIndovinaImmagine, model.indirizzi);
        });
    }
}