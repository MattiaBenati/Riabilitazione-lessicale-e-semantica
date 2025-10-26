package schermata.indovinaparola;

import metodi.Costanti;
import metodi.Immagini;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class IndovinaParolaView extends JFrame
{
    // tasti del pannello
    private JPanel schermata;
    private JPanel sxPannello;
    private JPanel dxPannello;
    private JLabel immagine;
    private JLabel lettere;
    private JLabel simboli;
    private JTextField inputUtente;
    private JPanel tastiAvantiIndietro;
    private JButton avanti;
    private JButton indietro;
    private JProgressBar progressBar;
    private JPanel tastiHomeCartellaReset;
    private JButton home;
    private JButton cartella;
    private JButton reset;
    private JPanel tastiFacileMedioDifficile;
    private JLabel lbDifficolta;
    private JRadioButton difficile;
    private JRadioButton medio;
    private JRadioButton facile;
    private JPanel tastiAttiveDisattivate;
    private JLabel lbVocali;
    private JRadioButton attive;
    private JRadioButton disattivate;
    private JLabel lbIndovina;
    
    // impostazioni immagine stampata
    final int altezzaImmagine = 400;
    final int larghezzaImmagine = 400;
    
    // costruttore
    public IndovinaParolaView()
    {
        super(Costanti.TITOLO);
        
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
     * setter della JLabel "immagine"
     */
    void setImmagine(File file)
    {
        Immagini.setImmagineJLabel(immagine, file, altezzaImmagine, larghezzaImmagine);
    }
    
    /**
     * setter del JLabel "lettere"
     */
    void setLettere(String lettere)
    {
        this.lettere.setText(lettere);
    }
    
    /**
     * setter del JLabel "simboli"
     */
    void setSimboli(String simboli)
    {
        this.simboli.setText(simboli);
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
     * setter del JTextField "inputUtente"
     */
    void addInputUtente(ActionListener listenInputUtente)
    {
        inputUtente.addActionListener(listenInputUtente);
    }
    
    String getTextFieldValue()
    {
        return inputUtente.getText().trim();
    }
    
    void eraseTextFieldValue()
    {
        inputUtente.setText(null);
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
    
    /**
     * setter del JRadioButton "Facile"
     */
    void addFacile(ActionListener listenTastoFacile)
    {
        facile.addActionListener(listenTastoFacile);
    }
    
    void setFacileSelected(boolean stato)
    {
        facile.setSelected(stato);
    }
    
    void setFacileEnabled(boolean stato)
    {
        facile.setEnabled(stato);
    }
    
    /**
     * setter del JRadioButton "Medio"
     */
    void addMedio(ActionListener listenTastoMedio)
    {
        medio.addActionListener(listenTastoMedio);
    }
    
    void setMedioSelected(boolean stato)
    {
        medio.setSelected(stato);
    }
    
    void setMedioEnabled(boolean stato)
    {
        medio.setEnabled(stato);
    }
    
    /**
     * setter del JRadioButton "Difficile"
     */
    void addDifficile(ActionListener listenTastoDifficile)
    {
        difficile.addActionListener(listenTastoDifficile);
    }
    
    void setDifficileSelected(boolean stato)
    {
        difficile.setSelected(stato);
    }
    
    void setDifficileEnabled(boolean stato)
    {
        difficile.setEnabled(stato);
    }
    
    /**
     * setter del JRadioButton "Attive"
     */
    void addAttive(ActionListener listenTastoAttive)
    {
        attive.addActionListener(listenTastoAttive);
    }
    
    void setAttiveSelected(boolean stato)
    {
        attive.setSelected(stato);
    }
    
    void setAttiveEnabled(boolean stato)
    {
        attive.setEnabled(stato);
    }
    
    /**
     * setter del JRadioButton "Disattivate"
     */
    void addDisattivate(ActionListener listenTastoDisattivate)
    {
        disattivate.addActionListener(listenTastoDisattivate);
    }
    
    void setDisattivateSelected(boolean stato)
    {
        disattivate.setSelected(stato);
    }
    
    void setDisattivateEnabled(boolean stato)
    {
        disattivate.setEnabled(stato);
    }
}