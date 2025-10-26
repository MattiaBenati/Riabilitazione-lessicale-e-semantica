package schermata.indovinaimmagine;

import metodi.Costanti;
import metodi.Immagini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class IndovinaImmagineView extends JFrame
{
    // tasti del pannello
    private JPanel schermata;
    private JPanel sxPannello;
    private JPanel pnlUno;
    private JLabel immagineUno;
    private JButton opzioneUno;
    private JPanel pnlDue;
    private JLabel immagineDue;
    private JButton opzioneDue;
    private JPanel pnlTre;
    private JLabel immagineTre;
    private JButton opzioneTre;
    private JPanel pnlQuattro;
    private JLabel immagineQuattro;
    private JButton opzioneQuattro;
    private JPanel dxPannello;
    private JLabel lbIndovina;
    private JLabel lettere;
    private JPanel tastiAvantiIndietro;
    private JButton avanti;
    private JButton indietro;
    private JProgressBar progressBar;
    private JPanel tastiHomeCartellaReset;
    private JButton home;
    private JButton cartella;
    private JButton reset;
    
    // immagini e tasti della schermata
    private final JLabel[] lbImmagini;
    private final JButton[] btOpzioni;
    
    // impostazioni immagini stampate
    final int altezzaImmagini = 150;
    final int larghezzaImmagini = 150;
    
    // costruttore
    public IndovinaImmagineView()
    {
        super(Costanti.TITOLO);
        
        // inizializzazione della lista di tasti e immagini
        this.lbImmagini = new JLabel[]{immagineUno, immagineDue, immagineTre, immagineQuattro};
        this.btOpzioni = new JButton[]{opzioneUno, opzioneDue, opzioneTre, opzioneQuattro};
        
        // creazione JPanel
        setContentPane(schermata);
        setIconImage(Costanti.ICONA.getImage());
        
        // impostazioni visibilità pannello
        setSize(850, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * setter dei JLabel "lbImmagini"
     */
    int getLbImmaginiLength()
    {
        return lbImmagini.length;
    }
    
    void setLbImmagini(ArrayList<ArrayList<File>> immaginiMostrate, ArrayList<ArrayList<Boolean>> analizzateMostrate, ArrayList<Boolean> analizzate, int pos, int numeroImmagini)
    {
        for (int i = 0; i < numeroImmagini; ++i)
        {
            Immagini.setImmagineJLabel(lbImmagini[i], immaginiMostrate.get(pos).get(i), altezzaImmagini, larghezzaImmagini);
            
            // aggiornamento colore dei tasti se già analizzati
            if (analizzate.get(pos))
                setBtOpzioniColori(analizzateMostrate, pos, numeroImmagini);
        }
    }
    
    void resetLbImmagini()
    {
        for(int i = 0; i < lbImmagini.length; ++i)
            lbImmagini[i].setIcon(null);
    }
    
    /**
     * setter dei JButton "btOpzioni"
     */
    void addBtOpzioni(ActionListener listenTastoAvanti, int i)
    {
        btOpzioni[i].addActionListener(listenTastoAvanti);
    }
    int getBtOpzioniLength()
    {
        return btOpzioni.length;
    }
    
    void setLbOpzioni(int numeroImmagini)
    {
        for(int i = 0; i < btOpzioni.length; ++i)
        {
            // tasto disponibile/NON disponibile
            setBtOpzioniEnabled(i < numeroImmagini, i);
        }
    }
    
    void setBtOpzioniEnabled(boolean stato, int i)
    {
        btOpzioni[i].setEnabled(stato);
    }
    
    void setBtOpzioniColori(ArrayList<ArrayList<Boolean>> analizzateMostrate, int pos, int numeroImmagini)
    {
        for (int i = 0; i < numeroImmagini; ++i)
        {
            if (analizzateMostrate.get(pos).get(i))
            {
                btOpzioni[i].setBackground(Color.GREEN);
                btOpzioni[i].setForeground(Color.GREEN);
            }
            
            else
            {
                btOpzioni[i].setBackground(Color.RED);
                btOpzioni[i].setForeground(Color.RED);
            }
        }
    }
    
    void resetBtOpzioniColori()
    {
        for (int i = 0; i < btOpzioni.length; ++i)
        {
            btOpzioni[i].setBackground(new JButton().getBackground());
            btOpzioni[i].setForeground(new JButton().getForeground());
        }
    }
    
    /**
     * setter del JLabel "lettere"
     */
    void setLettere(String lettere)
    {
        this.lettere.setText(lettere);
    }
    
    /**
     * setter della JProgressBar "progressBar"
     */
    void setProgressBar(int pos)
    {
        progressBar.setValue(pos);
    }
    
    void setProgressBarRange(int minimo, int massimo)
    {
        progressBar.setMinimum(minimo);
        progressBar.setMaximum(massimo);
        progressBar.setValue(minimo);
    }
    
    /**
     * setter del JButton "Avanti"
     */
    void addAvanti(ActionListener listenTastoAvanti)
    {
        avanti.addActionListener(listenTastoAvanti);
    }
    
    void setAvantiEnabled(boolean stato)
    {
        avanti.setEnabled(stato);
    }
    
    void doClickAvanti()
    {
        avanti.doClick();
    }
    
    /**
     * setter del JButton "Indietro"
     */
    void addIndietro(ActionListener listenTastoIndietro)
    {
        indietro.addActionListener(listenTastoIndietro);
    }
    
    void setIndietroEnabled(boolean stato)
    {
        indietro.setEnabled(stato);
    }
    
    void doClickIndietro()
    {
        indietro.doClick();
    }
    
    /**
     * setter del JButton "Home"
     */
    void addHome(ActionListener listenTastoHome)
    {
        home.addActionListener(listenTastoHome);
    }
    
    /**
     * setter del JButton "Cartella"
     */
    void addCartella(ActionListener listenTastoCartella)
    {
        cartella.addActionListener(listenTastoCartella);
    }
    
    /**
     * setter del JButton "Reset"
     */
    void addReset(ActionListener listenTastoReset)
    {
        reset.addActionListener(listenTastoReset);
    }
}