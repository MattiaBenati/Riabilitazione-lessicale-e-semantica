package schermata.indovinaimmagine;

import metodi.Directory;
import parola.Parola;
import parola.ParolaBuilder;
import schermata.home.HomeController;
import schermata.home.HomeModel;
import schermata.home.HomeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class IndovinaImmagineController
{
    private final IndovinaImmagineModel model;
    private final IndovinaImmagineView view;
    private ArrayList<File> indirizzi;
    private int pos;
    
    // costruttore
    public IndovinaImmagineController(IndovinaImmagineModel model, IndovinaImmagineView view, ArrayList<File> indirizzi)
    {
        this.model = model;
        this.view = view;
        this.indirizzi = indirizzi;
        this.pos = 0;
        
        // inizializzazione liste
        setListaParole();
        setListaAnalizzate();
        setListaImmaginiMostrate();
        setListaAnalizzateMostrate();
        
        // setter dei tasti
        setTasti();
        setDisplay();
    }
    
    /**
     * setter della schermata a inizio gioco
     */
    private void setDisplay()
    {
        /*
            il metodo di verifica della correttezza dell'immagine selezionata si basa sul fatto
            che, a partire dall'elenco di "parole" generate (già riordinate in ordine casuale),
            una volta che l'utente selezionerà un JButton tra quelli disponibili (con un'immagine
            stampata sopra), nel caso quest'immagine (indirizzo File) fosse uguale a "parola" analizzata
            (indirizzo File), allora significherebbe che l'utente ha selezionato la parola corretta
            (altrimenti no in caso opposto)
        */
        
        // visualizzazione immagini mostrate
        view.setLbImmagini(model.immaginiMostrate, model.analizzateMostrate, model.analizzate, pos, numeroImmagini());
        view.setLbOpzioni(numeroImmagini());
        
        // visualizzazione parola analizzata
        view.setLettere(model.parole.get(pos).getParola());
        view.setProgressBarRange(0, model.parole.size() - 1);
        
        // disabilitazione tasti
        view.setAvantiEnabled(model.analizzate.get(pos));
        view.setIndietroEnabled(model.analizzate.get(pos));
    }
    
    /**
     * setter della lista parole
     */
    private void setListaParole()
    {
        // creazione della lista parole
        model.parole = new ArrayList<Parola>();
        
        for (File file : indirizzi)
        {
            // aggiunta dell'indirizzo alla lista parole
            Parola parola = new ParolaBuilder()
                    .setIndirizzo(file)
                    .setParola()
                    .setVocali(false)
                    .setSimboli()
                    .setDifficolta(100)
                    .setLettere()
                    .build();
            
            // salvataggio della parola nella lista
            model.parole.add(parola);
        }
        
        // riordino casuale della lista parole
        Collections.shuffle(model.parole);
    }
    
    /**
     * setter della lista parole analizzate
     */
    private void setListaAnalizzate()
    {
        // creazione della lista analizzate
        model.analizzate = new ArrayList<Boolean>();
        
        for(int i = 0; i < model.parole.size(); ++i)
            model.analizzate.add(false);
    }
    
    /**
     * setter della lista-doppia immagini mostrate
     */
    private void setListaImmaginiMostrate()
    {
        int min_val = 0;
        int max_val = model.parole.size();
        
        // creazione della lista-doppia delle immagini mostrate
        model.immaginiMostrate = new ArrayList<>();
        
        for(int i = 0; i < model.parole.size(); ++i)
        {
            model.immaginiMostrate.add(new ArrayList<>());
            
            // aggiunta immagine da indovinare
            model.immaginiMostrate.get(i).add(model.parole.get(i).getImmagine());
            
            // aggiunta immagini errate
            for(int j = 0; j < Math.min(model.parole.size(), view.getLbImmaginiLength()) - 1; ++j)
            {
                while(true)
                {
                    boolean check = true;
                    
                    // generazione numero casuale
                    ThreadLocalRandom random = ThreadLocalRandom.current();
                    int random_num = random.nextInt(min_val, max_val);
                    
                    // verifica posizione libera (NIENTE IMMAGINI DOPPIE stampate)
                    for(int k = 0; k < model.immaginiMostrate.get(i).size(); ++k)
                    {
                        // immagine già presente nella lista
                        if(model.immaginiMostrate.get(i).get(k) == model.parole.get(random_num).getImmagine())
                        {
                            check = false;
                            break;
                        }
                    }
                    
                    // verifica corretta
                    if (check)
                    {
                        model.immaginiMostrate.get(i).add(model.parole.get(random_num).getImmagine());
                        break;
                    }
                }
            }
            
            // riordino casuale della lista parole
            Collections.shuffle(model.immaginiMostrate.get(i));
        }
    }
    
    /**
     * setter della lista-doppia immagini mostrate analizzate
     */
    private void setListaAnalizzateMostrate()
    {
        model.analizzateMostrate = new ArrayList<>();
        
        for(int i = 0; i < model.parole.size(); ++i)
        {
            model.analizzateMostrate.add(new ArrayList<>());
            
            for (int j = 0; j < Math.min(model.parole.size(), view.getLbImmaginiLength()); ++j)
                model.analizzateMostrate.get(i).add(false);
        }
    }
    
    /**
     * reset della lista parole
     */
    private void resetListaParole()
    {
        model.parole.clear();
    }
    
    /**
     * reset della lista analizzate
     */
    private void resetListaAnalizzate()
    {
        model.analizzate.clear();
    }
    
    /**
     * reset della lista-doppia immagini mostrate
     */
    private void resetListaImmaginiMostrate()
    {
        for(int i = 0; i < model.immaginiMostrate.size(); ++i)
            model.immaginiMostrate.get(i).clear();
        
        model.immaginiMostrate.clear();
    }
    
    /**
     * reset della lista-doppia immagini mostrate analizzate
     */
    private void resetListaAnalizzateMostrate()
    {
        for(int i = 0; i < model.analizzateMostrate.size(); ++i)
            model.analizzateMostrate.get(i).clear();
        
        model.analizzateMostrate.clear();
    }
    
    /**
     * setter dei tasti presenti nella schermata
     */
    private void setTasti()
    {
        // setter del JButton "Avanti"
        view.addAvanti(e ->
        {
            // aggiornamento posizione
            pos += 1;
            view.setProgressBar(pos);
            
            // disabilitazione tasto
            if(!model.analizzate.get(pos))
            {
                view.setAvantiEnabled(false);
                
                // reset colore originale
                view.resetBtOpzioniColori();
            }
            
            // tasto abilitato
            else
                view.setBtOpzioniColori(model.analizzateMostrate, pos, numeroImmagini());
            
            // aggiornamento immagini e lettere
            view.setLbImmagini(model.immaginiMostrate, model.analizzateMostrate, model.analizzate, pos, numeroImmagini());
            view.setLettere(model.parole.get(pos).getParola());
            
            // abilitazione tasto indietro
            view.setIndietroEnabled(true);
            
            if(model.analizzate.size() - 1 == pos)
                view.setAvantiEnabled(false);
        });
        
        // setter del JButton "Indietro"
        view.addIndietro(e ->
        {
            // aggiornamento posizione
            pos -= 1;
            view.setProgressBar(pos);
            
            // aggiornamento immagini e lettere
            view.setLbImmagini(model.immaginiMostrate, model.analizzateMostrate, model.analizzate, pos, numeroImmagini());
            view.setLettere(model.parole.get(pos).getParola());
            view.setBtOpzioniColori(model.analizzateMostrate, pos, numeroImmagini());
            
            // abilitazione tasto avanti
            view.setAvantiEnabled(true);
            
            if(pos == 0)
                view.setIndietroEnabled(false);
        });
        
        // setter del JButton "Home"
        view.addHome(e ->
        {
            // chiusura della schermata (distruzione JFrame corrispondente)
            view.dispose();
            
            // apertura della nuova schermata
            HomeModel modelHome = new HomeModel();
            HomeView viewHome = new HomeView();
            HomeController controllerHome = new HomeController(modelHome, viewHome);
        });
        
        // setter del JButton "Cartella"
        view.addCartella(e ->
        {
            // inizializzazione lista immagini
            Directory directory = Directory.getInstance();
            String indirizzoDirectory = directory.getDirectory();
            
            // verifica numero d'indirizzi
            ArrayList<File> indirizzi_verifica = directory.letturaFile(indirizzoDirectory);
            
            if(indirizzi_verifica == null || indirizzi_verifica.isEmpty())
                return;
            
            // verifica corretta
            indirizzi = indirizzi_verifica;
            
            // reset delle posizioni analizzate
            pos = 0;
            resetListaParole();
            resetListaAnalizzate();
            resetListaImmaginiMostrate();
            resetListaAnalizzateMostrate();
            
            // inizializzazione lista parole e immagini
            setListaParole();
            setListaAnalizzate();
            setListaImmaginiMostrate();
            setListaAnalizzateMostrate();
            
            // reset dei comandi (tasti sulla schermata)
            view.resetLbImmagini();
            view.resetBtOpzioniColori();
            
            // setter dei comandi (tasti sulla schermata)
            view.setLbImmagini(model.immaginiMostrate, model.analizzateMostrate, model.analizzate, pos, numeroImmagini());
            view.setLbOpzioni(numeroImmagini());
            view.setLettere(model.parole.get(pos).getParola());
            view.setProgressBarRange(0, model.parole.size() - 1);
            view.setAvantiEnabled(false);
            view.setIndietroEnabled(false);
        });
        
        // setter del JButton "Reset"
        view.addReset(e ->
        {
            // reset delle posizioni analizzate
            pos = 0;
            resetListaParole();
            resetListaAnalizzate();
            resetListaImmaginiMostrate();
            resetListaAnalizzateMostrate();
            
            // inizializzazione lista parole e immagini
            setListaParole();
            setListaAnalizzate();
            setListaImmaginiMostrate();
            setListaAnalizzateMostrate();
            
            // reset dei comandi (tasti sulla schermata)
            view.resetLbImmagini();
            view.resetBtOpzioniColori();
            
            // setter dei comandi (tasti sulla schermata)
            view.setLbImmagini(model.immaginiMostrate, model.analizzateMostrate, model.analizzate, pos, numeroImmagini());
            view.setLbOpzioni(numeroImmagini());
            view.setLettere(model.parole.get(pos).getParola());
            view.setProgressBar(pos);
            view.setAvantiEnabled(false);
            view.setIndietroEnabled(false);
        });
        
        // setter dei JButton "btOpzioni"
        for(int i = 0; i < view.getBtOpzioniLength(); ++i)
        {
            /*
                posizione analizzata partendo da in alto a sinistra (0-esimo JButton)
                e andando verso destra, fino a ripartire dalla riga sottostante,
                sempre da sinistra e continuando così via con il conteggio
            */
            
            final int posizione = i;
            
            view.addBtOpzioni(e->{
                
                // attivazione posizione analizzata
                model.analizzate.set(pos, true);
                
                // verifica e salvataggio del tasto premuto
                // (true = immagine corretta, false = immagine errata)
                verificaOpzioni();
                
                // aggiornamento colore dei pulsanti
                view.setBtOpzioniColori(model.analizzateMostrate, pos, numeroImmagini());
                
                // verifica attivazione tasto avanti
                view.setAvantiEnabled(true);
                
                // penultima posizione raggiunta
                if(model.analizzate.size() - 1 == pos)
                    view.setAvantiEnabled(false);
                    
                    // parola indovinata e quindi vai avanti automaticamente
                else if (model.immaginiMostrate.get(pos).get(posizione) == model.parole.get(pos).getImmagine())
                    view.doClickAvanti();
            }, i);
        }
    }
    
    /**
     * aggiornamento della lista-doppia immagini mostrate
     * analizzate nella posizione corrente
     */
    private void verificaOpzioni()
    {
        for(int i = 0; i < numeroImmagini(); ++i)
        {
            // immagine corretta
            if(model.immaginiMostrate.get(pos).get(i) == model.parole.get(pos).getImmagine())
                model.analizzateMostrate.get(pos).set(i, true);
            
            // immagine errata
            else
                model.analizzateMostrate.get(pos).set(i, false);
        }
    }
    
    /**
     * getter del numero minimo di immagini da stampare
     *
     * @return ritorna il numero minimo di immagini da stampare
     * confrontando il numero di JLabel disponibili e il numero
     * di immagini che erano presenti nella directory selezionata
     */
    private int numeroImmagini()
    {
        // numero d'immagini da stampare (massimo "lbImmagini.length")
        return Math.min(model.parole.size(), view.getLbImmaginiLength());
    }
}