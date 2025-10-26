package schermata.home;

import metodi.Costanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomeView extends JFrame
{
    // tasti del pannello
    private JPanel schermata;
    private JPanel sxPannello;
    private JLabel immagine;
    private JPanel dxPannello;
    private JButton indovinaParola;
    private JButton indovinaImmagine;
    private JLabel lbScelta;
    private JLabel lbTitolo;
    
    // impostazioni immagine stampata
    final int altezzaImmagine = 300;
    final int larghezzaImmagine = 300;
    
    // costruttore
    public HomeView()
    {
        super(Costanti.TITOLO);
        
        // setter dei comandi
        stampaImmagine(immagine, Costanti.HOME, altezzaImmagine, larghezzaImmagine);
        
        // creazione JPanel
        setContentPane(schermata);
        setIconImage(Costanti.ICONA.getImage());
        
        // impostazioni visibilità pannello
        setSize(650, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * stampa dell'immagine home
     *
     * @param label
     * @param immagine
     * @param altezza
     * @param larghezza
     */
    void stampaImmagine(JLabel label, ImageIcon immagine, int altezza, int larghezza)
    {
        Image immagineImage = immagine.getImage();
        immagineImage = immagineImage.getScaledInstance(larghezza, altezza, Image.SCALE_SMOOTH);
        
        // creazione dell'icona corrispondente all'immagine da stampare
        ImageIcon immagineIcona = new ImageIcon(immagineImage);
        
        // impostazione icona alla label corrente
        label.setIcon(immagineIcona);
    }
    
    /**
     * setter del JButton "Indovina Parola"
     */
    void addIndovinaParola(ActionListener listener)
    {
        indovinaParola.addActionListener(listener);
    }
    
    /**
     * setter del JButton "Indovina Immagine"
     */
    void addIndovinaImmagine(ActionListener listener)
    {
        indovinaImmagine.addActionListener(listener);
    }
}