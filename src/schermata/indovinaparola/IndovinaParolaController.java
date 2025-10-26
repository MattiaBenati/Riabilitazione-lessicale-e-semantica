package schermata.indovinaparola;

import metodi.Caratteri;
import metodi.Directory;
import parola.Parola;
import parola.ParolaBuilder;
import schermata.home.HomeController;
import schermata.home.HomeModel;
import schermata.home.HomeView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class IndovinaParolaController
{
    private final IndovinaParolaModel model;
    private final IndovinaParolaView view;
    private ArrayList<File> indirizzi;
    private int difficolta;
    private boolean vocali;
    private int pos;
    
    // costruttore
    public IndovinaParolaController(IndovinaParolaModel model, IndovinaParolaView view, ArrayList<File> indirizzi, int difficolta, boolean vocali)
    {
        this.model = model;
        this.view = view;
        this.indirizzi = indirizzi;
        this.difficolta = difficolta;
        this.vocali = vocali;
        this.pos = 0;
        
        // inizializzazione liste
        setListaParole();
        setListaAnalizzate();
        
        // inizializzazione modalità
        setDifficile();
        setDisattivate();
        
        // setter dei tasti
        setTasti();
        setDisplay();
    }
    
    /**
     * setter della schermata a inizio gioco
     */
    private void setDisplay()
    {
        // visualizzazione immagine analizzata
        view.setImmagine(model.parole.get(pos).getIndirizzo());
        
        // visualizzazione parola analizzata
        view.setLettere(model.parole.get(pos).getLettere());
        view.setSimboli(model.parole.get(pos).getSimboli());
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
                    .setVocali(vocali)
                    .setSimboli()
                    .setDifficolta(difficolta)
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
        
        for (int i = 0; i < indirizzi.size(); ++i)
            model.analizzate.add(false);
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
     * setter delle lettere analizzate
     */
    private void setLettere()
    {
        view.setLettere(model.parole.get(pos).getLettere());
    }
    
    /**
     * setter dei simboli analizzati
     */
    private void setSimboli()
    {
        view.setSimboli(model.parole.get(pos).getSimboli());
    }
    
    /**
     * setter della difficoltà 'difficile'
     */
    private void setDifficile()
    {
        difficolta = 100;
        
        // selezione del tasto
        view.setDifficileSelected(true);
        view.setMedioSelected(false);
        view.setFacileSelected(false);
        
        // disattivazione del tasto (e attivazione degli altri)
        view.setDifficileEnabled(false);
        view.setMedioEnabled(true);
        view.setFacileEnabled(true);
    }
    
    /**
     * setter della difficoltà 'medio'
     */
    private void setMedio()
    {
        difficolta = 50;
        
        // selezione del tasto
        view.setDifficileSelected(false);
        view.setMedioSelected(true);
        view.setFacileSelected(false);
        
        // disattivazione del tasto (e attivazione degli altri)
        view.setDifficileEnabled(true);
        view.setMedioEnabled(false);
        view.setFacileEnabled(true);
    }
    
    /**
     * setter della difficoltà 'facile'
     */
    private void setFacile()
    {
        difficolta = 25;
        
        // selezione del tasto
        view.setDifficileSelected(false);
        view.setMedioSelected(false);
        view.setFacileSelected(true);
        
        // disattivazione del tasto (e attivazione degli altri)
        view.setDifficileEnabled(true);
        view.setMedioEnabled(true);
        view.setFacileEnabled(false);
    }
    
    /**
     * setter delle vocali 'attive'
     */
    private void setAttive()
    {
        vocali = true;
        
        // selezione del tasto
        view.setAttiveSelected(true);
        view.setDisattivateSelected(false);
        
        // disattivazione del tasto (e attivazione degli altri)
        view.setAttiveEnabled(false);
        view.setDisattivateEnabled(true);
    }
    
    /**
     * setter delle vocali 'disattivate'
     */
    private void setDisattivate()
    {
        vocali = false;
        
        // selezione del tasto
        view.setAttiveSelected(false);
        view.setDisattivateSelected(true);
        
        // disattivazione del tasto (e attivazione degli altri)
        view.setAttiveEnabled(true);
        view.setDisattivateEnabled(false);
    }
    
    /**
     * setter dei tasti presenti nella schermata
     */
    private void setTasti()
    {
        // setter del JTextField "inputUtente"
        view.addInputUtente(e ->
        {
            String input = view.getTextFieldValue();
            
            // pulizia del testo stampato
            view.eraseTextFieldValue();
            
            // l'utente non ha inserito alcun carattere (o solo spazi vuoti)
            if(input.isEmpty())
                return;
            
            // conversione in lettere minuscole
            StringBuilder tmp = new StringBuilder(input);
            Caratteri.conversioneMinuscole(tmp);
            input = tmp.toString();
            
            // aggiornamento lettere da stampare
            Object[] risultato = Caratteri.aggiornamentoLettere(input, model.parole.get(pos).getLettere(), model.parole.get(pos).getParola());
            
            int check = (int) risultato[0];
            String lettere = (String) risultato[1];
            
            // aggiornamento lettere
            model.parole.get(pos).updateLettere(lettere);
            view.setLettere(model.parole.get(pos).getLettere());
            
            // parola indovinata e quindi vai AVANTI
            if(check == 1 || check == 2)
                model.analizzate.set(pos, true);
            
            if(check >= 0 && model.parole.size() - 1 != pos)
            {
                view.setAvantiEnabled(true);
                
                if(check == 1)
                    view.doClickAvanti();
            }
        });
        
        // setter del JButton "Avanti"
        view.addAvanti(e ->
        {
            // aggiornamento posizione
            pos += 1;
            view.setProgressBar(pos);
            
            // abilitazione o disabilitazione tasto
            view.setAvantiEnabled(model.analizzate.get(pos));
            
            // getter e setter dei comandi (tasti sulla schermata)
            view.setImmagine(model.parole.get(pos).getIndirizzo());
            view.setLettere(model.parole.get(pos).getLettere());
            view.setSimboli(model.parole.get(pos).getSimboli());
            
            // abilitazione tasto INDIETRO
            view.setIndietroEnabled(true);
            
            if (model.parole.size() - 1 == pos)
                view.setAvantiEnabled(false);
        });
        
        // setter del JButton "Indietro"
        view.addIndietro(e ->
        {
            // aggiornamento posizione
            pos -= 1;
            view.setProgressBar(pos);
            
            // getter e setter dei comandi (tasti sulla schermata)
            view.setImmagine(model.parole.get(pos).getIndirizzo());
            view.setLettere(model.parole.get(pos).getLettere());
            view.setSimboli(model.parole.get(pos).getSimboli());
            
            // abilitazione tasto AVANTI
            view.setAvantiEnabled(true);
            
            if (pos == 0)
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
            
            // inizializzazione lista immagini e parole
            setListaParole();
            setListaAnalizzate();
            
            // setter dei comandi (tasti sulla schermata)
            view.setImmagine(model.parole.get(pos).getIndirizzo());
            view.setLettere(model.parole.get(pos).getLettere());
            view.setSimboli(model.parole.get(pos).getSimboli());
            view.setProgressBarRange(0, model.parole.size() - 1);
            view.setAvantiEnabled(false);
            view.setIndietroEnabled(false);
        });
        
        // setter del JButton "Reset"
        view.addReset(e ->
        {
            // reset delle posizioni analizzate
            resetListaAnalizzate();
            
            // reset posizione
            pos = 0;
            view.setAvantiEnabled(false);
            view.setIndietroEnabled(false);
            
            // riordino casuale della lista indirizzi delle immagini
            Collections.shuffle(indirizzi);
            
            // inizializzazione della nuova lista parole
            setListaParole();
            setListaAnalizzate();
            
            // setter dei comandi (tasti sulla schermata)
            view.setImmagine(model.parole.get(pos).getIndirizzo());
            view.setLettere(model.parole.get(pos).getLettere());
            view.setSimboli(model.parole.get(pos).getSimboli());
            view.setProgressBar(pos);
        });
        
        // setter del JRadioButton "Facile"
        view.addFacile(e ->
        {
            // aggiornamento selezione tasto
            setFacile();
            
            // reset delle lettere (solo a quelle NON ANCORA INDOVINATE)
            for(int i = 0; i < model.analizzate.size(); ++i)
            {
                if(!model.analizzate.get(i))
                {
                    // ricreazione delle parole ancora da indovinare
                    Parola parola = new ParolaBuilder()
                            .setIndirizzo(model.parole.get(i).getIndirizzo())
                            .setParola()
                            .setVocali(vocali)
                            .setSimboli()
                            .setDifficolta(difficolta)
                            .setLettere()
                            .build();
                    
                    // riscrittura della parola nella lista
                    model.parole.set(i, parola);
                }
            }
            
            // aggiornamento lettere stampate
            setLettere();
        });
        
        // setter del JRadioButton "Medio"
        view.addMedio(e ->
        {
            // aggiornamento selezione tasto
            setMedio();
            
            // reset delle lettere (solo a quelle NON ANCORA INDOVINATE)
            for(int i = 0; i < model.analizzate.size(); ++i)
            {
                if(!model.analizzate.get(i))
                {
                    // ricreazione delle parole ancora da indovinare
                    Parola parola = new ParolaBuilder()
                            .setIndirizzo(model.parole.get(i).getIndirizzo())
                            .setParola()
                            .setVocali(vocali)
                            .setSimboli()
                            .setDifficolta(difficolta)
                            .setLettere()
                            .build();
                    
                    // riscrittura della parola nella lista
                    model.parole.set(i, parola);
                }
            }
            
            // aggiornamento lettere stampate
            setLettere();
        });
        
        // setter del JRadioButton "Difficile"
        view.addDifficile(e ->
        {
            // aggiornamento selezione tasto
            setDifficile();
            
            // reset delle lettere (solo a quelle NON ANCORA INDOVINATE)
            for(int i = 0; i < model.analizzate.size(); ++i)
            {
                if(!model.analizzate.get(i))
                {
                    // ricreazione delle parole ancora da indovinare
                    Parola parola = new ParolaBuilder()
                            .setIndirizzo(model.parole.get(i).getIndirizzo())
                            .setParola()
                            .setVocali(vocali)
                            .setSimboli()
                            .setDifficolta(difficolta)
                            .setLettere()
                            .build();
                    
                    // riscrittura della parola nella lista
                    model.parole.set(i, parola);
                }
            }
            
            // aggiornamento lettere stampate
            setLettere();
        });
        
        // setter del JRadioButton "Attive"
        view.addAttive(e ->
        {
            // aggiornamento selezione tasto
            setAttive();
            
            // aggiornamento dei simboli
            for (int i = 0; i < model.parole.size(); ++i)
            {
                String parola = model.parole.get(i).getParola();
                String simboli = Caratteri.setSimboli(parola, vocali);
                model.parole.get(i).updateSimboli(simboli);
            }
            
            // aggiornamento dei simboli sulla schermata
            setSimboli();
        });
        
        // setter del JRadioButton "Disattivate"
        view.addDisattivate(e ->
        {
            // aggiornamento selezione tasto
            setDisattivate();
            
            // aggiornamento dei simboli
            for (int i = 0; i < model.parole.size(); ++i)
            {
                String parola = model.parole.get(i).getParola();
                String simboli = Caratteri.setSimboli(parola, vocali);
                model.parole.get(i).updateSimboli(simboli);
            }
            
            // aggiornamento dei simboli sulla schermata
            setSimboli();
        });
    }
}